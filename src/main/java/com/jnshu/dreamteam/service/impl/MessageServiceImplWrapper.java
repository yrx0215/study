package com.jnshu.dreamteam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jnshu.dreamteam.mapper.MessageMapper;
import com.jnshu.dreamteam.pojo.Message;
import com.jnshu.dreamteam.service.MessageService;
import com.jnshu.dreamteam.service.QueryWrapperBaseService;
import com.jnshu.dreamteam.utils.MyPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author draper_hxy
 */
@Service
public class MessageServiceImplWrapper extends QueryWrapperBaseService<Message> implements MessageService {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Resource
    private MessageMapper messageMapper;

    @Override
    public IPage<Message> search(Map<String, Object> params) {
        initParams(params);
        LOGGER.trace("get message page, page = {}, size = {}", params.get("page"), params.get("size"));
        Long currentPage = Long.valueOf(params.get("page").toString());
        Long size = Long.valueOf(params.get("size").toString());
        MyPage<Message> page = new MyPage<>(currentPage, size);
        QueryWrapper<Message> queryWrapper = new QueryWrapper<>();
        queryWrapper = buildQueryWrapper(queryWrapper, params);
        return messageMapper.selectPage(page, queryWrapper);
    }

    @Override
    @Transactional
    public Boolean insert(Message message) {
        LOGGER.trace("insert message");
        Long date = System.currentTimeMillis();
        message.setCreateAt(date);
        message.setUpdateAt(date);
        return getResult(messageMapper.insert(message));
    }

    @Override
    public Message select(Long id) {
        LOGGER.trace("select message, id = {}", id);
        return messageMapper.selectById(id);
    }

    @Override
    protected QueryWrapper<Message> buildQueryWrapper(QueryWrapper<Message> queryWrapper, Map<String, Object> params) {
        if (params.get("title") != null) {
            queryWrapper.lambda().like(Message::getTitle, params.get("title"));
        }
        if (params.get("sendType") != null) {
            queryWrapper.lambda().eq(Message::getSendType, params.get("sendType"));
        }
        if (params.get("messageType") != null) {
            queryWrapper.lambda().eq(Message::getMessageType, params.get("messageType"));
        }
        return queryWrapper;
    }

}
