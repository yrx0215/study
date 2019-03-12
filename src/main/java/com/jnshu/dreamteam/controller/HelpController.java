package com.jnshu.dreamteam.controller;

import com.jnshu.dreamteam.pojo.Response;
import com.jnshu.dreamteam.service.HelpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author draper_hxy
 */
@RestController
public class HelpController {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private HelpService helpService;

    @RequestMapping("/a/u/help/{id}")
    public Response selectObject(@PathVariable Long id) {
        try {
            return new Response();
        } catch (Throwable t) {
            t.printStackTrace();
            LOGGER.error(t.getMessage());
            return Response.error();
        }
    }


}
