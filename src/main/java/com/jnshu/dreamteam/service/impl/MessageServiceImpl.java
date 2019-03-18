package com.jnshu.dreamteam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jnshu.dreamteam.mapper.MessageMapper;
import com.jnshu.dreamteam.pojo.Message;
import com.jnshu.dreamteam.service.MessageService;
import com.jnshu.dreamteam.service.QueryWrapperBaseService;
import com.jnshu.dreamteam.service.job.MessageJob;
import com.jnshu.dreamteam.utils.MyPage;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/**
 * @author draper_hxy
 */
@Service
public class MessageServiceImpl extends QueryWrapperBaseService<Message> implements MessageService {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Resource
    private MessageMapper messageMapper;

    @Override
    public IPage<Message> search(Map<String, Object> params) {
        initParams(params);
        LOGGER.trace("get message page, page = {}, size = {}", params.get("page"), params.get("size"));
        Long currentPage = Long.valueOf(params.get("page").toString());
        Long size = Long.valueOf(params.get("size").toString());
        MyPage<Message> page = new MyPage<>(currentPage, size);
        QueryWrapper<Message> queryWrapper = new QueryWrapper<>();
        queryWrapper = buildQueryWrapper(queryWrapper, params);
        return messageMapper.selectPage(page, queryWrapper);
    }

    @Override
    @Transactional
    public Boolean insert(Message message) throws SchedulerException, InterruptedException {
        LOGGER.trace("insert message");
        Long date = System.currentTimeMillis();
        message.setCreateAt(date);
        message.setUpdateAt(date);

        int messageType = message.getMessageType();

        switch (messageType) {
            case 0:
                // TODO: 2019-03-18 立即发送消息
                break;
            case 1:
                BuildMessageJob(message);
                break;
        }


        return getResult(messageMapper.insert(message));
    }

    private void BuildMessageJob(Message message) throws SchedulerException {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        JobDetail jobDetail = JobBuilder.newJob(MessageJob.class)
                .storeDurably()
                .withIdentity("Message", "SMS")
                .build();

        jobDetail.getJobDataMap().put("title", message.getTitle());
        jobDetail.getJobDataMap().put("message", message.getMessage());
        jobDetail.getJobDataMap().put("sendType", message.getSendType());

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("Message", "SMS")
                .withSchedule(CronScheduleBuilder.cronSchedule("0 * * * * ? 2019"))
                .build();

        scheduler.scheduleJob(jobDetail, trigger);
        scheduler.start();
    }

    public static void main(String[] args) {
        System.out.println(new MessageServiceImpl().cronBuilder(System.currentTimeMillis()));
    }

    private String cronBuilder(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(time));
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        return "0 " + minute + " " + hour + " " + month + " ? " + year;
    }

    @Override
    public Message select(Long id) {
        LOGGER.trace("select message, id = {}", id);
        return messageMapper.selectById(id);
    }

    @Override
    protected QueryWrapper<Message> buildQueryWrapper(QueryWrapper<Message> queryWrapper, Map<String, Object> params) {
        if (params.get("title") != null) {
            queryWrapper.lambda().like(Message::getTitle, params.get("title"));
        }
        if (params.get("sendType") != null) {
            queryWrapper.lambda().eq(Message::getSendType, params.get("sendType"));
        }
        if (params.get("messageType") != null) {
            queryWrapper.lambda().eq(Message::getMessageType, params.get("messageType"));
        }
        return queryWrapper;
    }

}
