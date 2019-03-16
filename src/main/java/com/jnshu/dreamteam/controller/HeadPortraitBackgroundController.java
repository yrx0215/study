package com.jnshu.dreamteam.controller;

import com.jnshu.dreamteam.pojo.HeadPortraitBackground;
import com.jnshu.dreamteam.pojo.Response;
import com.jnshu.dreamteam.service.HeadPortraitBackgroundService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author draper_hxy
 */
@RestController
public class HeadPortraitBackgroundController {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private HeadPortraitBackgroundService headService;

    @RequestMapping(value = "/a/u/headBg/page", method = RequestMethod.GET)
    public Response selectHeadPages(@RequestParam Map<String, Object> params) {
        try {
            return new Response(200, "OK", headService.selectPages(params));
        } catch (Throwable t) {
            t.printStackTrace();
            LOGGER.error(t.getMessage());
            return Response.error();
        }
    }

    @RequestMapping(value = "/a/u/headBg", method = RequestMethod.POST)
    public Response insert(@RequestBody HeadPortraitBackground headPortraitBackground) {
        try {
            return new Response(200, "OK", headService.insert(headPortraitBackground));
        } catch (Throwable t) {
            t.printStackTrace();
            LOGGER.error(t.getMessage());
            return Response.error();
        }
    }

    @RequestMapping(value = "/a/u/headBg/{id}", method = RequestMethod.DELETE)
    public Response delete(@PathVariable Long id) {
        try {
            return new Response(200, "OK", headService.delete(id));
        } catch (Throwable t) {
            t.printStackTrace();
            LOGGER.error(t.getMessage());
            return Response.error();
        }
    }

    @RequestMapping(value = "/a/u/headBg/{id}", method = RequestMethod.GET)
    public Response selectObject(@PathVariable Long id) {
        try {
            return new Response(200, "OK", headService.selectObject(id));
        } catch (Throwable t) {
            t.printStackTrace();
            LOGGER.error(t.getMessage());
            return Response.error();
        }
    }

    @RequestMapping(value = "/a/u/headBg", method = RequestMethod.PUT)
    public Response updateObject( @RequestBody HeadPortraitBackground headBg){
        try {
            return new Response(200, "OK", headService.update(headBg));
        } catch (Throwable t){
            t.printStackTrace();
            LOGGER.error(t.getMessage());
            return Response.error();
        }
    }

}
