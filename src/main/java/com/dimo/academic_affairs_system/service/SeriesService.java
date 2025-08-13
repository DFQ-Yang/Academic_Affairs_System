package com.dimo.academic_affairs_system.service;

import com.dimo.academic_affairs_system.pojo.Series;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SeriesService {
    /**
     * create subject with name
     * @param series series info with name
     */
    void create_series(Series series);

    /**
     * list all series in the database
     * @return list of all series
     */
    List<Series> get_allSeries();

    /**
     * Update series info
     * @param seriesId series that needs to be updated
     * @param series contains "name" which needs to be updated
     */
    void update_series(Integer seriesId, Series series);

    /**
     * Delete the whole series AND ALL COURSES UNDER THE SERIES
     * @param seriesId the series id needs to be deleted
     */
    void delete_series(Integer seriesId);
}
