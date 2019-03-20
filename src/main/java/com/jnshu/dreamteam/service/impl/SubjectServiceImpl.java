package com.jnshu.dreamteam.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jnshu.dreamteam.mapper.SubjectMapper;
import com.jnshu.dreamteam.pojo.Subject;
import com.jnshu.dreamteam.service.SubjectService;
import com.jnshu.dreamteam.utils.MyPage;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yrx
 */
@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private SubjectMapper subjectMapper;

    @Override
    public Long addSubject(Subject subject) {
        Boolean b = subjectMapper.addSubject(subject);
        if (!b){
            return  0L;
        }
        return subject.getId();
    }

    @Override
    public Subject selectSubject(Long id) {
        return subjectMapper.selectSubject(id);
    }

    @Override
    public Boolean updateSubject(Subject subject) {
        return subjectMapper.updateSubject(subject);
    }

    @Override
    public Boolean delectSubject(Long id) {
        return subjectMapper.delectSubject(id);
    }

    @Override
    public IPage<Subject> selectAllSubject(Integer page, Integer size) {
        page = page==null||page<=0?1:page;
        size = size==null||size<=0?10:size;
        IPage mypage = new MyPage(page,size);
        return subjectMapper.selectAllSubject(mypage);
    }

    @Override
    public IPage<Subject> selectSubjectByStutasOrName(IPage iPage,
                                                     @Param("subjectStatus") Integer subjectStatus,
                                                     @Param("subjectName") String subjectName) {
        return subjectMapper.selectSubjectStatusOrName(iPage,subjectStatus, subjectName);
    }

    @Override
    public Boolean updateSubjectStatus(Subject subject) {
        return subjectMapper.updateSubjectStatus(subject);
    }

    @Override
    public List selectAllSubjectName() {
        return subjectMapper.selectAllSubjectName();
    }
}
