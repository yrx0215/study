package com.jnshu.dreamteam.controller;

import com.jnshu.dreamteam.pojo.Response;
import com.jnshu.dreamteam.pojo.Version;
import com.jnshu.dreamteam.service.VersionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.Map;

/**
 * @author draper_hxy
 */

@RestController
public class VersionController {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private VersionService versionService;

    @RequestMapping(value = "/a/u/version", method = RequestMethod.GET)
    public Response selectPages(@RequestParam Map<String, Object> params) {
        try {
            return new Response(200, "OK", versionService.selectPages(params));
        } catch (Throwable t) {
            t.printStackTrace();
            LOGGER.error(t.getMessage());
            return Response.error();
        }
    }

    @RequestMapping(value = "/a/u/version/{id}", method = RequestMethod.GET)
    public Response select(@PathVariable Long id) {
        try {
            return new Response(200, "OK", versionService.select(id));
        } catch (Throwable t) {
            t.printStackTrace();
            LOGGER.error(t.getMessage());
            return Response.error();
        }
    }

    @RequestMapping(value = "/a/u/version", method = RequestMethod.POST)
    public Response insert(@RequestBody Version version) {
        try {
            return new Response(200, "OK", versionService.insert(version));
        } catch (Throwable t) {
            t.printStackTrace();
            LOGGER.error(t.getMessage());
            return Response.error();
        }
    }

}
