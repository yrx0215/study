package com.jnshu.dreamteam.controller;

import com.jnshu.dreamteam.pojo.Response;
import com.jnshu.dreamteam.pojo.Subject;
import com.jnshu.dreamteam.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * subject 科目相关接口
 *
 */
@RestController
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    /**
     *
     *  查询所有科目信息,
     * @return 返回值为 所有subject的集合
     */
    @RequestMapping(value = "/a/u/allSubject", method = RequestMethod.GET)
    public Response getAllSubject(){
        List<Subject> subjects = subjectService.selectAllSubject();
        return new Response(0,"success",subjects);
    }

    /**
     *
     *  根据科目状态和科目名称查询相关的数据;
     * @param subjectStatus 科目状态值 0为下架, 1位上架, 默认下架, 没有设置为空
     * @param subjectName 科目名称, 1:语文 2:数学 3:英语, 没有设置为空
     * @return 根据科目状态和名称 返回对应的subject集合
     */
    @RequestMapping(value = "/a/u/statusOrName", method = RequestMethod.GET)
    public Response getSubjectByStatusOrName(Integer subjectStatus, Integer subjectName){
        List<Subject> subjects = subjectService.selectSubjectByStutasOrName(subjectStatus, subjectName);
        return new Response(0,"success",subjects);
    }

    /**
     * 根据科目id查询对应科目信息
     * @param id 科目对应id
     * @return 科目对象信息
     */
    @RequestMapping(value = "/a/u/subject/{id}", method = RequestMethod.GET)
    public Response getSubjectById(@PathVariable("id") Long id){
        Subject subject = subjectService.selectSubject(id);
        return new Response(0,"success",subject);
    }

    /**
     *  更新方法
     * @param subject 更新后的subject对象
     * @return 返回更新后的subject对象
     */
    @RequestMapping(value = "/a/u/subject",method = RequestMethod.PUT)
    public Response updateSubject(Subject subject){
        subject.setUpdateAt(System.currentTimeMillis());
        Boolean b = subjectService.updateSubject(subject);

        if (b){
            return new Response(0,"success",subject);
        }

        return new Response(-1,"更新失败",subject);
    }

    /**
     *  添加方法 subject科目
     * @param subject 新增subject对象
     * @return 返回值为新增subject对象
     */
    @RequestMapping(value = "/a/u/subject",method = RequestMethod.POST)
    public Response addSubject(Subject subject){
        subject.setCreateAt(System.currentTimeMillis());
        subjectService.addSubject(subject);
        Long id = subject.getId();
        return new Response(0,"success",id);
    }


    @RequestMapping(value = "/a/u/subject/{id}",method = RequestMethod.DELETE)
    public Response delectSubject(@PathVariable("id") Long id){
        Boolean b = subjectService.delectSubject(id);
        if (b){
            return new Response(0,"success",b);
        }
        return new Response(0,"Delect fail",b);

    }


}
