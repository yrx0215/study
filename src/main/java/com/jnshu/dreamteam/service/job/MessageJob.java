package com.jnshu.dreamteam.service.job;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jnshu.dreamteam.mapper.StudentMapper;
import com.jnshu.dreamteam.mapper.UserMapper;
import com.jnshu.dreamteam.pojo.Student;
import com.jnshu.dreamteam.pojo.User;
import com.jnshu.dreamteam.service.QueryWrapperBaseService;
import com.jnshu.dreamteam.service.StudentService;
import com.jnshu.dreamteam.utils.MessageUtil;
import com.jnshu.dreamteam.utils.SpringContextUtil;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author draper_hxy
 */
// FIXME: 2019-03-19 Quartz 任务未持久化
@Component
public class MessageJob extends QueryWrapperBaseService<Student> implements Job {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());


    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        StudentService studentService = (StudentService) SpringContextUtil.getBean("studentServiceImpl");
        JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();

        int grade = Integer.valueOf(jobDataMap.get("sendType").toString());
        String gradeStr = null;
        String msgStr = jobDataMap.get("message").toString();
        switch (grade) {
            case 0:
                gradeStr = "小升初";
            case 1:
                gradeStr = "一年级";
                break;
            case 2:
                gradeStr = "二年级";
                break;
            case 3:
                gradeStr = "三年级";
                break;
            case 4:
                gradeStr = "四年级";
                break;
            case 5:
                gradeStr = "五年级";
                break;
            case 6:
                gradeStr = "六年级";
                break;
            default:
                break;
        }

        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda();
//                .eq(Student::getGrade, "");
        // TODO: 2019-03-19 查找 sendType 的人群的手机号，进行发送信息
        Student student = studentService.selectStudentById(1L);
        List<Student> studentList = new ArrayList<>();

        studentList.forEach(s -> {
            try {
                // TODO: 2019-03-17 发送 SMS
                LOGGER.debug("send SMS {} to {}", s.getPhone(), msgStr);
//                MessageUtil.sendMessage(String.valueOf(s.getPhone()), msgStr);
            } catch (Exception e) {
                e.printStackTrace();
                LOGGER.error(e.getMessage());
            }
        });

        LOGGER.error("发送消息成功");
    }

    @Override
    protected QueryWrapper<Student> buildQueryWrapper(QueryWrapper<Student> queryWrapper, Map<String, Object> params) {
        throw new UnsupportedOperationException();
    }

    private static MessageJob messageJob;

    @PostConstruct //通过@PostConstruct实现初始化bean之前进行的操作
    public void init() {
        messageJob = this;
//        messageJob.studentService = this.studentService;
    }
}
