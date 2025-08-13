package com.dimo.academic_affairs_system.service.impl;

import com.dimo.academic_affairs_system.mapper.SeriesMapper;
import com.dimo.academic_affairs_system.pojo.Series;
import com.dimo.academic_affairs_system.pojo.StandardException;
import com.dimo.academic_affairs_system.service.SeriesService;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SeriesServiceImpl implements SeriesService {
    private final SeriesMapper seriesMapper;

    public SeriesServiceImpl(SeriesMapper seriesMapper) {
        this.seriesMapper = seriesMapper;
    }

    @Override
    public void create_series(Series series) {
        try {
            seriesMapper.create_series(series);
        }
        catch (DuplicateKeyException e){
            throw new StandardException(400, "Series already exist");
        }
    }

    @Override
    public List<Series> get_allSeries(){
        List<Series> series = seriesMapper.get_allSeries();
        return series;
    }

    @Override
    public void update_series(Integer seriesId, Series series) {
        if(series.getName() == null){
            throw new StandardException(400, "name cannot be null");
        }
        seriesMapper.update_series(seriesId, series);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete_series(Integer seriesId) {
        seriesMapper.delete_series(seriesId);
    }
}
