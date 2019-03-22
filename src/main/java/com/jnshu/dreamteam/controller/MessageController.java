package com.jnshu.dreamteam.controller;


import com.jnshu.dreamteam.pojo.Message;
import com.jnshu.dreamteam.pojo.Response;
import com.jnshu.dreamteam.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.Map;

/**
 * @author draper_hxy
 */

@RestController
public class MessageController {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MessageService messageService;

    /**
     * 参数有 page size title标题 sendType发送人群 messageType消息类型
     */
    @RequestMapping(value = "/a/u/message/search", method = RequestMethod.GET)
    public Response search(@RequestParam Map<String, Object> params) {
        try {
            return new Response(200, "OK", messageService.search(params));
        } catch (Throwable t) {
            t.printStackTrace();
            LOGGER.error(t.getMessage());
            return Response.error();
        }
    }

    /**
     * sendType 0立即 1定时
     * messageType
     */
    @RequestMapping(value = "/a/u/message", method = RequestMethod.POST)
    public Response insert(@RequestBody Message message) {
        try {
            return new Response(200, "OK", messageService.insert(message));
        } catch (Throwable t) {
            t.printStackTrace();
            LOGGER.error(t.getMessage());
            return Response.error();
        }
    }

    @RequestMapping(value = "/a/u/message/{id}", method = RequestMethod.GET)
    public Response select(@PathVariable Long id) {
        try {
            return new Response(200, "OK", messageService.select(id));
        } catch (Throwable t) {
            t.printStackTrace();
            LOGGER.error(t.getMessage());
            return Response.error();
        }
    }

}
