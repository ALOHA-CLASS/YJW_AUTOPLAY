package com.aloha.autoplay.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aloha.autoplay.domain.AutoPlay;
import com.aloha.autoplay.mapper.AutoPlayMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AutoPlayServiceImpl extends ServiceImpl<AutoPlayMapper, AutoPlay> implements AutoPlayService  {

    @Autowired private AutoPlayMapper autoPlayMapper;

    @Override
    public List<AutoPlay> getList() {
        return this.list();
    }

    @Override
    public PageInfo<AutoPlay> getPageList(int page, int pageSize) {
        PageHelper.startPage(page, pageSize);
        List<AutoPlay> list = autoPlayMapper.selectList(null);
        PageInfo<AutoPlay> pageInfo = new PageInfo<AutoPlay>(list);
        log.info("pageInfo: {}", pageInfo);
        return pageInfo;
    }

    @Override
    public AutoPlay get(Long no) {
        QueryWrapper<AutoPlay> queryWrapper = new QueryWrapper<AutoPlay>();
        queryWrapper.eq("no", no);
        return this.getOne(queryWrapper);
    }

    @Override
    public AutoPlay getById(String id) {
        QueryWrapper<AutoPlay> queryWrapper = new QueryWrapper<AutoPlay>();
        queryWrapper.eq("id", id);
        return this.getOne(queryWrapper);
    }

    @Override
    public boolean create(AutoPlay entity) {
        return this.save(entity);
    }

    @Override
    public boolean update(AutoPlay entity) {
        QueryWrapper<AutoPlay> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("no", entity.getNo());
        return this.update(entity, queryWrapper);
    }

    @Override
    public boolean updateById(AutoPlay entity) {
        QueryWrapper<AutoPlay> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", entity.getId());
        return this.update(entity, queryWrapper);
    }

    @Override
    @Transactional
    public boolean delete(Long no) {
        QueryWrapper<AutoPlay> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("no", no);
        return this.remove(queryWrapper);
    }

    @Override
    @Transactional
    public boolean deleteById(String id) {
        QueryWrapper<AutoPlay> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        return this.remove(queryWrapper);
    }

    @Override
    public long count() {
        return autoPlayMapper.selectCount(null);
    }

    @Override
    public long todayCount() {
        QueryWrapper<AutoPlay> queryWrapper = new QueryWrapper<>();
        queryWrapper.apply("DATE_FORMAT(`created_at`, '%Y%m%d') = DATE_FORMAT(NOW(), '%Y%m%d')");
        return this.count(queryWrapper);
    }

    @Override
    public String total() {
        QueryWrapper<AutoPlay> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("SUM(play_time) as total");
        AutoPlay autoPlay = autoPlayMapper.selectOne(queryWrapper);
        if( autoPlay == null ) {
            return "00:00:00";
        }
        Long totalPlayTime = autoPlay.getTotal();
        totalPlayTime = totalPlayTime == null ? 0 : totalPlayTime;

        long hours = totalPlayTime / 3600000;
        long minutes = (totalPlayTime % 3600000) / 60000;
        long seconds = ((totalPlayTime % 3600000) % 60000) / 1000;

        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    @Override
    public String today() {
        QueryWrapper<AutoPlay> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("SUM(play_time) as today");
        queryWrapper.apply("DATE_FORMAT(`created_at`, '%Y%m%d') = DATE_FORMAT(NOW(), '%Y%m%d')");
        AutoPlay autoPlay = autoPlayMapper.selectOne(queryWrapper);
        if( autoPlay == null ) {
            return "00:00:00";
        }
        Long todayPlayTime = autoPlay.getToday();
        todayPlayTime = todayPlayTime == null ? 0 : todayPlayTime;

        long hours = todayPlayTime / 3600000;
        long minutes = (todayPlayTime % 3600000) / 60000;
        long seconds = ((todayPlayTime % 3600000) % 60000) / 1000;

        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    /**
     * 단위별 오토플레이
     * - 5분, 10분, 15분, 20분, 25분, 30분이상
     * - play_time을 5분 단위로 그룹핑하여 count
     */
    @Override
    public Map<String, Long> groupCount() throws Exception {
        List<Map<String, Object>> results = autoPlayMapper.groupCount();
        return results.stream().collect(Collectors.toMap(
            result -> (String) result.get("play_time_range"),
            result -> (Long) result.get("count")
        ));
        
    }

    @Override
    public Map<String, Long> genderAvg() throws Exception {
        List<Map<String, Object>> results = autoPlayMapper.genderAvg();
        return results.stream().collect(Collectors.toMap(
            result -> (String) result.get("gender"),
            result -> ((BigInteger) result.get("avg_play_time")).longValue()
        ));
    }

    @Override
    public Map<String, Long> ageAvg() throws Exception {
        List<Map<String, Object>> results = autoPlayMapper.ageAvg();
        return results.stream().collect(Collectors.toMap(
            result -> result.get("age").toString(),
            result -> ((BigInteger) result.get("avg_play_time")).longValue()
        ));
    }
    
}
