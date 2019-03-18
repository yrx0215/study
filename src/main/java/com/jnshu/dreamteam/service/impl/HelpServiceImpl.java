package com.jnshu.dreamteam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.jnshu.dreamteam.mapper.HelpMapper;
import com.jnshu.dreamteam.pojo.Help;
import com.jnshu.dreamteam.service.BaseService;
import com.jnshu.dreamteam.service.HelpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author draper_hxy
 */
@Service
public class HelpServiceImpl extends BaseService implements HelpService {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Resource
    private HelpMapper helpMapper;

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
