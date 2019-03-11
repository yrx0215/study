package com.jnshu.dreamteam.service;


import com.jnshu.dreamteam.pojo.Subject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SubjectService {

    Long addSubject(Subject subject);

    Subject selectSubject(Long id);

    Boolean updateSubject(Subject subject);

    Boolean delectSubject(Long id);

    List<Subject> selectAllSubject();

    List<Subject> selectSubjectByStutasOrName(@Param("subjectStatus") Integer subjectStatus,
                                              @Param("subjectName") Integer subjectName);

}
