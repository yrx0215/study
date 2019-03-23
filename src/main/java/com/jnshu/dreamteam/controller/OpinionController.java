package com.jnshu.dreamteam.controller;

import com.jnshu.dreamteam.pojo.Opinion;
import com.jnshu.dreamteam.pojo.Response;
import com.jnshu.dreamteam.service.OpinionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.Map;

/**
 * @author draper_hxy
 */

@RestController
public class OpinionController {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private OpinionService opinionService;

    @RequestMapping(value = "/a/u/opinion", method = RequestMethod.GET)
    public Response selectPages(@RequestParam Map<String, Object> params) {
        try {
            return new Response(200, "OK", opinionService.selectPages(params));
        } catch (Throwable t) {
            t.printStackTrace();
            LOGGER.error(t.getMessage());
            return Response.error();
        }
    }

    @RequestMapping(value = "/a/u/opinion/{id}", method = RequestMethod.GET)
    public Response select(@PathVariable Long id) {
        try {
            return new Response(200, "OK", opinionService.select(id));
        } catch (Throwable t) {
            t.printStackTrace();
            LOGGER.error(t.getMessage());
            return Response.error();
        }
    }

    @RequestMapping(value = "/a/u/opinion/{id}", method = RequestMethod.DELETE)
    public Response delete(@PathVariable Long id) {
        try {
            return new Response(200, "OK", opinionService.delete(id));
        } catch (Throwable t) {
            t.printStackTrace();
            LOGGER.error(t.getMessage());
            return Response.error();
        }
    }

    @RequestMapping(value = "/a/u/opinion", method = RequestMethod.POST)
    public Response insert(@RequestBody Opinion opinion) {
        try {
            return new Response(200, "OK", opinionService.insert(opinion));
        } catch (Throwable t){
            t.printStackTrace();
            LOGGER.error(t.getMessage());
            return Response.error();
        }
    }

}
