package com.dimo.academic_affairs_system.controller;

import com.dimo.academic_affairs_system.pojo.Result;
import com.dimo.academic_affairs_system.pojo.Series;
import com.dimo.academic_affairs_system.service.SeriesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class SeriesController {
    private final SeriesService seriesService;

    public SeriesController(SeriesService seriesService) {
        this.seriesService = seriesService;
    }

    @PostMapping("/api/series")
    public Result create_series(@RequestBody Series series){
        log.info("Now creating series: {}", series.getName());
        seriesService.create_series(series);

        //Insert success
        log.info("create series: {} success", series.getName());
        return Result.success();
    }

    @GetMapping("/api/series")
    public Result get_allSeries(){
        log.info("Now getting all series");
        List<Series> series = seriesService.get_allSeries();

        log.info("listing success");
        return Result.success(series);
    }

    @PutMapping("/api/series/{series_id}")
    public Result update_series(@PathVariable Integer series_id, @RequestBody Series series){
        log.info("now updating series info");
        seriesService.update_series(series_id, series);
        log.info("update success");
        return Result.success();
    }

    @DeleteMapping("/api/series/{series_id}")
    public Result delete_series(@PathVariable Integer series_id){
        log.info("now deleting series: {}", series_id);
        seriesService.delete_series(series_id);
        log.info("delete success");
        return Result.success();
    }
}
