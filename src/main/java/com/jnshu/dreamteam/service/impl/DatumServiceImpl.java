package com.jnshu.dreamteam.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jnshu.dreamteam.mapper.DatumMapper;
import com.jnshu.dreamteam.pojo.Datum;
import com.jnshu.dreamteam.service.DatumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DatumServiceImpl implements DatumService {

    @Autowired
    private DatumMapper datumMapper;
    @Override
    public IPage<Datum> selectAllDatum(IPage iPage) {
        return datumMapper.selectAllDatum(iPage);
    }

    @Override
    public IPage<Datum> selectDatumFuzzy(IPage iPage, String subjectName, String courseName, String lessonName) {
        return datumMapper.selectDatumFuzzy(iPage,subjectName,courseName,lessonName);
    }

    @Override
    public Datum selectDatumById(Long id) {
        return datumMapper.selectDatumById(id);
    }

    @Override
    public Boolean updateDatumBuId(Datum datum) {
        return datumMapper.updateDatumBuId(datum);
    }

    @Override
    public Boolean delectDatumById(Long id) {
        return datumMapper.delectDatumById(id);
    }

    @Override
    public Long addDatum(Datum datum) {
        return datumMapper.addDatum(datum);
    }
}
