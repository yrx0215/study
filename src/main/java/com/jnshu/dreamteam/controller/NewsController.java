package com.jnshu.dreamteam.controller;

import com.jnshu.dreamteam.pojo.News;
import com.jnshu.dreamteam.pojo.Response;
import com.jnshu.dreamteam.service.NewsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author draper_hxy
 */
@RestController
public class NewsController {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private NewsService newsService;

    @RequestMapping(value = "/a/u/news", method = RequestMethod.POST)
    public Response insert(@RequestBody News news) {
        try {
            return new Response(200, "OK", newsService.insert(news));
        } catch (Throwable t) {
            t.printStackTrace();
            LOGGER.error(t.getMessage());
            return Response.error();
        }
    }

    @RequestMapping(value = "/a/u/news/{id}", method = RequestMethod.GET)
    public Response select(@PathVariable Long id) {
        try {
            return new Response(200, "OK", newsService.select(id));
        } catch (Throwable t) {
            t.printStackTrace();
            LOGGER.error(t.getMessage());
            return Response.error();
        }
    }

    @RequestMapping(value = "/a/u/news", method = RequestMethod.PUT)
    public Response update(@RequestBody News news) {
        try {
            return new Response(200, "OK", newsService.update(news));
        } catch (Throwable t) {
            t.printStackTrace();
            LOGGER.error(t.getMessage());
            return Response.error();
        }
    }

}
