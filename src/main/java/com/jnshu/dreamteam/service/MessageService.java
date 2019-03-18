package com.jnshu.dreamteam.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jnshu.dreamteam.pojo.Message;

import java.util.Map;

/**
 * @author draper_hxy
 */
public interface MessageService {

    IPage<Message> search(Map<String, Object> params);

    Boolean insert(Message message);

    Message select(Long id);

}
