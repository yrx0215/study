package com.jnshu.dreamteam.service.impl;

import com.jnshu.dreamteam.mapper.SubjectMapper;
import com.jnshu.dreamteam.pojo.Subject;
import com.jnshu.dreamteam.service.SubjectService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private SubjectMapper subjectMapper;

    @Override
    public Long addSubject(Subject subject) {
        Boolean b = subjectMapper.addSubject(subject);
        if (b == false){
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
    public List<Subject> selectAllSubject() {
        return subjectMapper.selectAllSubject();
    }

    @Override
    public List<Subject> selectSubjectByStutasOrName(@Param("subjectStatus") Integer subjectStatus,
                                                     @Param("subjectName") Integer subjectName) {
        return subjectMapper.selectSubjectStatusOrName(subjectStatus, subjectName);
    }
}
