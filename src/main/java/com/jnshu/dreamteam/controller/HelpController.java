package com.jnshu.dreamteam.controller;

import com.jnshu.dreamteam.pojo.Help;
import com.jnshu.dreamteam.pojo.Response;
import com.jnshu.dreamteam.service.HelpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author draper_hxy
 */
@RestController
public class HelpController {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private HelpService helpService;

    @RequestMapping(value = "/a/u/help", method = RequestMethod.GET)
    public Response selectPages(@RequestParam Map<String, Object> params) {
        try {
            return new Response(200, "OK", helpService.selectPages(params));
        } catch (Throwable t) {
            t.printStackTrace();
            LOGGER.error(t.getMessage());
            return Response.error();
        }
    }

    @RequestMapping("/a/u/help/{id}")
    public Response select(@PathVariable Long id) {
        try {
            return new Response(200, "OK", helpService.select(id));
        } catch (Throwable t) {
            t.printStackTrace();
            LOGGER.error(t.getMessage());
            return Response.error();
        }
    }

    @RequestMapping(value = "/a/u/help", method = RequestMethod.POST)
    public Response insert(@RequestBody Help help) {
        try {
            return new Response(200, "OK", helpService.insert(help));
        } catch (Throwable t) {
            t.printStackTrace();
            LOGGER.error(t.getMessage());
            return Response.error();
        }
    }

    @RequestMapping(value = "/a/u/help", method = RequestMethod.PUT)
    public Response update(@RequestBody Help help) {
        try {
            return new Response(200, "OK", helpService.update(help));
        } catch (Throwable t) {
            t.printStackTrace();
            LOGGER.error(t.getMessage());
            return Response.error();
        }
    }

    @RequestMapping(value = "/a/u/help/{id}", method = RequestMethod.DELETE)
    public Response delete(@PathVariable Long id) {
        try {
            return new Response(200, "OK", helpService.delete(id));
        } catch (Throwable t) {
            t.printStackTrace();
            LOGGER.trace(t.getMessage());
            return Response.error();
        }
    }

}
