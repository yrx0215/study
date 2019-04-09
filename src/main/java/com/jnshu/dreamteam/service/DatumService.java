package com.jnshu.dreamteam.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jnshu.dreamteam.pojo.Datum;

public interface DatumService {

    IPage<Datum> selectAllDatum(IPage iPage);

    IPage<Datum> selectDatumFuzzy(IPage iPage,
                                  String subjectName,
                                  String courseName,
                                  String lessonName);

    Datum selectDatumById( Long id);

    Boolean updateDatumBuId(Datum datum);

    Boolean delectDatumById(Long id);

    Long addDatum(Datum datum);

    IPage<Datum> selectDatumByCourseId(IPage iPage,
                                       Long courseId);
}
