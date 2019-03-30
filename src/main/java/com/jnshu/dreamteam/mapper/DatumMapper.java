package com.jnshu.dreamteam.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jnshu.dreamteam.pojo.Datum;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
public interface DatumMapper {

    IPage<Datum> selectAllDatum(IPage iPage);

    IPage<Datum> selectDatumFuzzy(IPage iPage,
                                  @Param("subjectName")String subjectName,
                                  @Param("courseName")String courseName,
                                  @Param("lessonName")String lessonName);

    Datum selectDatumById(@Param("id") Long id);

    Boolean updateDatumBuId(Datum datum);

    Boolean delectDatumById(@Param("id")Long id);

    Long addDatum(Datum datum);

}
