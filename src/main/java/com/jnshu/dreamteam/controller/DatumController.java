package com.jnshu.dreamteam.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jnshu.dreamteam.pojo.Datum;
import com.jnshu.dreamteam.pojo.Response;
import com.jnshu.dreamteam.service.CourseService;
import com.jnshu.dreamteam.service.DatumService;
import com.jnshu.dreamteam.service.LessonService;
import com.jnshu.dreamteam.service.SubjectService;
import com.jnshu.dreamteam.utils.EmptyUtil;
import com.jnshu.dreamteam.utils.MyPage;
import com.jnshu.dreamteam.utils.UploadPic;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@Slf4j
public class DatumController {

    @Autowired
    private DatumService datumService;
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private LessonService lessonService;

    @RequestMapping(value = "/a/u/allDatum", method = RequestMethod.GET)
    public Response getAllDatum(@RequestParam(value = "page",required = false)Integer page,
                                @RequestParam(value = "size",required = false) Integer size){
        log.info("查询所有资料 ========= page {} size {]",page,size);
        page = page == null || page <= 0 ? 1 : page;
        size = size == null || size <= 0 ? 10 : size;
        IPage iPage = new MyPage(page, size);
        IPage mypage = datumService.selectAllDatum(iPage);
        log.info("查询的资料总数为: {}",mypage.getTotal());
        if (EmptyUtil.isEmpty(mypage.getTotal())){
            return new Response(-1,"没有找到对应的数据");
        }
        return new Response(200,"success",mypage);
    }


    @RequestMapping(value = "/a/u/datumFuzzy", method = RequestMethod.GET)
    public Response getDatumFuzzy(@RequestParam(value = "page",required = false)Integer page,
                                  @RequestParam(value = "size", required = false)Integer size,
                                  @RequestParam(value = "subjectName", required = false)String subjectName,
                                  @RequestParam(value = "courseName", required = false)String courseName,
                                  @RequestParam(value = "lessonName", required = false)String lessonName){

        log.info("资料 选择查询 subjectname{}, coursename{}, lessonName{}",subjectName,courseName,lessonName);
        page = page == null || page <= 0 ? 1 : page;
        size = size == null || size <= 0 ? 10 : size;
        IPage iPage = new MyPage(page, size);
        IPage mypage = datumService.selectDatumFuzzy(iPage,subjectName,courseName,lessonName);
        if (EmptyUtil.isEmpty(mypage)){
            return new Response(-1,"没有找到对应数据");
        }
        log.info("根据选择的对应数据量为 : {}",mypage.getTotal());
        return new Response(200,"success",mypage);

    }

    @RequestMapping(value = "/a/u/datum/{id}",method = RequestMethod.GET)
    public Response getDatumById(@PathVariable("id")Long id ){

        log.info("根据id查询datum数据, id为: {}",id);
        Datum datum = datumService.selectDatumById(id);
        log.info("查询的数据为:{}",datum);
        if (EmptyUtil.isEmpty(datum)){
            return new Response(-1,"没有找到对应数据, id为" + id);
        }
        return new Response(200,"success",datum);
    }

    @RequestMapping(value = "/a/u/datum",method = RequestMethod.PUT)
    public Response updateDatumById(Datum datum){
        log.info("修改datum数据 :修改为{}",datum);
        datum.setUpdateAt(System.currentTimeMillis());
        Boolean isSuccess = datumService.updateDatumBuId(datum);
        if (!isSuccess){
            return Response.error();
        }
        return new Response(200,"success",datum);
    }

    @RequestMapping(value = "/a/u/datum/{id}",method = RequestMethod.DELETE)
    public Response delectDatumById(@PathVariable("id")Long id){
        log.info("删除datum数据 id为: {}",id);
        Boolean isSuccess = datumService.delectDatumById(id);
        if (!isSuccess){
            return new Response(-1,"删除失败");
        }
        return new Response(200,"success",id);
    }

    @RequestMapping(value = "/a/u/datum",method = RequestMethod.POST)
    public Response addDatum(Long subjectId, Long courseId,Long lessonId, Datum datum){
        log.info("新增datum资料 数据为{},subjectId {},courseId {}, lessonId {}",datum,subjectId,courseId, lessonId);
        String subjectName = subjectService.selectSubject(subjectId).getSubjectName();
        String courseName = courseService.selectCourseById(courseId).getCourseName();
        String lessonName = lessonService.getLessonById(lessonId).getLessonName();

        if (EmptyUtil.isEmpty(subjectName)|| EmptyUtil.isEmpty(courseName)|| EmptyUtil.isEmpty(lessonName)){
            return new Response(-1,"输入参数有误");
        }
        datum.setSubjectName(subjectName);
        datum.setCourseName(courseName);
        datum.setLessonName(lessonName);
        datum.setCreateAt(System.currentTimeMillis());
        Long id = datumService.addDatum(datum);
        if (id == 0 ){
            return new Response(-1 , "新增数据失败");
        }
        return new Response(200,"success",datum.getId());
    }


    @PostMapping("/a/u/datum/img")
    public Response<String> uploadDatumImg(@RequestParam("file") MultipartFile file) throws IOException {
        String pictureId = System.currentTimeMillis()+"";
        //使用course图片的桶 , 懒得去开新桶
        String url = UploadPic.uploadFactory(file,pictureId,"course");
        return new Response<>(200,"上传资料成功","资料地址为：" + url);
    }

}
