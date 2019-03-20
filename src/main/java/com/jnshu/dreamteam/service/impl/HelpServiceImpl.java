package com.jnshu.dreamteam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jnshu.dreamteam.mapper.HelpMapper;
import com.jnshu.dreamteam.pojo.Help;
import com.jnshu.dreamteam.pojo.Message;
import com.jnshu.dreamteam.service.BaseService;
import com.jnshu.dreamteam.service.BaseServiceWrapper;
import com.jnshu.dreamteam.service.HelpService;
import com.jnshu.dreamteam.utils.MyPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author draper_hxy
 */
@Service
public class HelpServiceImpl extends BaseServiceWrapper<Help> implements HelpService {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Resource
    private HelpMapper helpMapper;

    @Override
    public IPage<Help> selectPages(Map<String, Object> params) {
        initParams(params);
        LOGGER.trace("get help page, page = {}, size = {}", params.get("page"), params.get("size"));
        Long currentPage = Long.valueOf(params.get("page").toString());
        Long size = Long.valueOf(params.get("size").toString());
        MyPage<Help> page = new MyPage<>(currentPage, size);
        QueryWrapper<Help> queryWrapper = buildQueryWrapper(null, params);
        return helpMapper.selectPage(page, queryWrapper);
    }

    @Override
    public Help select(Long id) {
        LOGGER.trace("select help, id = {}", id);
        Help help = helpMapper.selectById(id);
        return help;
    }

    @Override
    public Boolean insert(Help help) {
        LOGGER.trace("insert help");
        Long date = System.currentTimeMillis();
        help.setCreateAt(date);
        help.setUpdateAt(date);
        return getResult(helpMapper.insert(help));
    }

    @Override
    public Boolean update(Help help) {
        LOGGER.trace("update help, id = {}", help.getId());
        UpdateWrapper<Help> wrapper = new UpdateWrapper<>();
        wrapper.lambda()
                .set(Help::getUpdateAt, System.currentTimeMillis())
                .set(Help::getTitle, help.getTitle())
                .set(Help::getHelp, help.getHelp())
                .eq(Help::getId, help.getId());
        return getResult(helpMapper.update(help, wrapper));
    }

    @Override
    public Boolean delete(Long id) {
        LOGGER.trace("delete help, id = {}", id);
        QueryWrapper<Help> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(Help::getId, id);
        return getResult(helpMapper.delete(wrapper));
    }

}
