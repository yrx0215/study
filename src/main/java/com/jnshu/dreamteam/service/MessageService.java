package com.jnshu.dreamteam.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jnshu.dreamteam.pojo.Message;
import org.quartz.SchedulerException;

import java.util.Map;

/**
 * @author draper_hxy
 */
public interface MessageService {

    IPage<Message> search(Map<String, Object> params);

    Boolean insert(Message message) throws SchedulerException, InterruptedException;

    Message select(Long id);

}
