package com.jnshu.dreamteam.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jnshu.dreamteam.pojo.Response;
import com.jnshu.dreamteam.pojo.Subject;
import com.jnshu.dreamteam.service.SubjectService;
import com.jnshu.dreamteam.utils.MyPage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


/**
 * subject 科目相关接口
 *
 * @author yrx
 */
@RestController
@Slf4j
public class SubjectController {

    private final SubjectService subjectService;

    @Autowired
    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    /**
     * 查询所有科目信息,
     *
     * @return 返回值为 所有subject的集合
     */
    @RequestMapping(value = "/a/u/allSubject", method = RequestMethod.GET)

    public Response<IPage<Subject>> getAllSubject(@RequestParam(value = "page", required = false) Integer page,
                                                  @RequestParam(value = "size", required = false) Integer size) {
        page = page == null || page <= 0 ? 1 : page;
        size = size == null || size <= 0 ? 10 : size;
        IPage<Subject> subjects = subjectService.selectAllSubject(page, size);
        return new Response<>(200, "success", subjects);
    }

    /**
     * 根据科目状态和科目名称查询相关的数据;
     *
     * @param subjectStatus 科目状态值 0为下架, 1位上架, 默认下架, 没有设置为空
     * @param subjectName   科目名称, 1:语文 2:数学 3:英语, 没有设置为空
     * @return 根据科目状态和名称 返回对应的subject集合
     */
    @RequestMapping(value = "/a/u/statusOrName", method = RequestMethod.GET)

    public Response<IPage<Subject>> getSubjectByStatusOrName(@RequestParam(value = "page", required = false) Integer page,
                                                             @RequestParam(value = "size", required = false) Integer size,
                                                             @RequestParam(value = "subjectStatus", required = false) Integer subjectStatus,
                                                             @RequestParam(value = "subjectName", required = false) String subjectName) {
        IPage myPage = new MyPage(page, size);
        IPage<Subject> subjects = subjectService.selectSubjectByStutasOrName(myPage, subjectStatus, subjectName);
        return new Response<>(0, "success", subjects);
    }

    /**
     * 根据科目id查询对应科目信息
     *
     * @param id 科目对应id
     * @return 科目对象信息
     */
    @RequestMapping(value = "/a/u/subject/{id}", method = RequestMethod.GET)
    public Response<Subject> getSubjectById(@PathVariable("id") Long id) {
        Subject subject = subjectService.selectSubject(id);
        return new Response<>(0, "success", subject);
    }

    /**
     * 更新方法
     *
     * @param subject 更新后的subject对象
     * @return 返回更新后的subject对象
     */
    @RequestMapping(value = "/a/u/subject", method = RequestMethod.PUT)
    public Response updateSubject(Subject subject) {
        log.info("update subject, id = " + subject.getId());
        subject.setUpdateAt(System.currentTimeMillis());
        Boolean b = subjectService.updateSubject(subject);
        if (b) {
            log.info("update success, subjcet id = " + subject.getId());
            return Response.ok();
        }
        return new Response<>(-1, "更新失败", subject);
    }

    /**
     * 添加方法 subject科目
     *
     * @param subject 新增subject对象
     * @param grades 年级集合
     * @return 返回值为新增subject对象
     */
    @RequestMapping(value = "/a/u/subject", method = RequestMethod.POST)
    public Response<List<Long>> addSubject(Subject subject ,@RequestParam("grades") List<Integer> grades) {
        log.info("添加方法, 年级列表的大小为 {}",grades.size());
        List<Long> ids = new ArrayList<>();
        for (Integer grade : grades) {
            subject.setGrade(grade);
            subject.setCreateAt(System.currentTimeMillis());
            subjectService.addSubject(subject);
            Long id = subject.getId();
            ids.add(id);
        }
        log.info("id的集合为 " + ids);

        return new Response<>(200, "success", ids);
    }


    /**
     * 删除科目方法
     *
     * @param id 删除的科目id
     * @return 成功返回true 失败返回false
     */
    @RequestMapping(value = "/a/u/subject/{id}", method = RequestMethod.DELETE)
    public Response<Boolean> delectSubject(@PathVariable("id") Long id) {
        Boolean b = subjectService.delectSubject(id);
        if (b) {
            return new Response<>(0, "success", true);
        }
        return new Response<>(0, "Delect fail", b);
    }


    /**
     * 修改上下架状态
     * @param id 科目id信息
     * @return 返回值
     */
    @RequestMapping(value = "/a/u/subjectStatus",method = RequestMethod.PUT)
    public Response updateSubjectStatus(Long id){
        log.info("开始修改上下架状态=====科目id是" + id);
        Subject subject = subjectService.selectSubject(id);
        log.info("修改的科目详细信息为: " + subject);
        //判断当前状态是否为0;
        if (subject.getSubjectStatus() == 0){
            subject.setSubjectStatus(1);
        } else {
            subject.setSubjectStatus(0);
        }
        //设置信息
        subject.setId(id);
        subject.setUpdateAt(System.currentTimeMillis());
        Boolean b = subjectService.updateSubjectStatus(subject);
        if (!b) {
            return Response.error();
        }
        return new Response<>(200, "success",true);
    }


    @RequestMapping(value = "/a/u/allSubjectName",method = RequestMethod.GET)
    public Response<List> getAllSubjectName(){
        log.info("查询所有的科目名称=======");
        List list = subjectService.selectAllSubjectName();
        log.info("所有科目列表长度为: " + list.size());
        return new Response<>(200,"success",list);
    }


}

