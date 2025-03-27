package com.aloha.autoplay.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aloha.autoplay.domain.UseTime;
import com.aloha.autoplay.mapper.UseTimeMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UseTimeServiceImpl extends ServiceImpl<UseTimeMapper, UseTime> implements UseTimeService  {

    @Autowired private UseTimeMapper useTimeMapper;

    @Override
    public List<UseTime> getList() {
        return this.list();
    }

    @Override
    public PageInfo<UseTime> getPageList(int page, int pageSize) {
        PageHelper.startPage(page, pageSize);
        List<UseTime> list = useTimeMapper.selectList(null);
        PageInfo<UseTime> pageInfo = new PageInfo<UseTime>(list);
        log.info("pageInfo: {}", pageInfo);
        return pageInfo;
    }

    @Override
    public UseTime get(Long no) {
        QueryWrapper<UseTime> queryWrapper = new QueryWrapper<UseTime>();
        queryWrapper.eq("no", no);
        return this.getOne(queryWrapper);
    }

    @Override
    public UseTime getById(String id) {
        QueryWrapper<UseTime> queryWrapper = new QueryWrapper<UseTime>();
        queryWrapper.eq("id", id);
        return this.getOne(queryWrapper);
    }

    @Override
    public boolean create(UseTime entity) {
        return this.save(entity);
    }

    @Override
    public boolean update(UseTime entity) {
        QueryWrapper<UseTime> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("no", entity.getNo());
        return this.update(entity, queryWrapper);
    }

    @Override
    public boolean updateById(UseTime entity) {
        QueryWrapper<UseTime> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", entity.getId());
        return this.update(entity, queryWrapper);
    }

    @Override
    @Transactional
    public boolean delete(Long no) {
        QueryWrapper<UseTime> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("no", no);
        return this.remove(queryWrapper);
    }

    @Override
    @Transactional
    public boolean deleteById(String id) {
        QueryWrapper<UseTime> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        return this.remove(queryWrapper);
    }

    @Override
    public long count() {
        return useTimeMapper.selectCount(null);
    }

    @Override
    public long todayCount() {
        QueryWrapper<UseTime> queryWrapper = new QueryWrapper<>();
        queryWrapper.apply("DATE_FORMAT(`created_at`, '%Y%m%d') = DATE_FORMAT(NOW(), '%Y%m%d')");
        return this.count(queryWrapper);
    }

    @Override
    public String total() {
        QueryWrapper<UseTime> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("SUM(use_time) as total");
        UseTime useTime = useTimeMapper.selectOne(queryWrapper);
        if (useTime == null) {
            return "00:00:00";
        }
        Long totalUseTime = useTime.getTotal();
        totalUseTime = totalUseTime == null ? 0 : totalUseTime;

        long hours = totalUseTime / 3600000;
        long minutes = (totalUseTime % 3600000) / 60000;
        long seconds = ((totalUseTime % 3600000) % 60000) / 1000;

        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    @Override
    public String today() {
        QueryWrapper<UseTime> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("SUM(use_time) as today");
        queryWrapper.apply("DATE_FORMAT(`created_at`, '%Y%m%d') = DATE_FORMAT(NOW(), '%Y%m%d')");
        UseTime useTime = useTimeMapper.selectOne(queryWrapper);
        if (useTime == null) {
            return "00:00:00";
        }
        Long todayUseTime = useTime.getToday();
        todayUseTime = todayUseTime == null ? 0 : todayUseTime;

        long hours = todayUseTime / 3600000;
        long minutes = (todayUseTime % 3600000) / 60000;
        long seconds = ((todayUseTime % 3600000) % 60000) / 1000;

        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    @Override
    public Map<String, Long> groupCount() throws Exception {
        List<Map<String, Object>> results = useTimeMapper.groupCount();
        return results.stream().collect(Collectors.toMap(
            result -> (String) result.get("use_time_range"),
            result -> (Long) result.get("count")
        ));
    }

    @Override
    public Map<String, Long> genderAvg() throws Exception {
        List<Map<String, Object>> results = useTimeMapper.genderAvg();
        return results.stream().collect(Collectors.toMap(
            result -> (String) result.get("gender"),
            result -> ((BigInteger) result.get("avg_use_time")).longValue()
        ));
    }

    @Override
    public Map<String, Long> ageAvg() throws Exception {
        List<Map<String, Object>> results = useTimeMapper.ageAvg();
        return results.stream().collect(Collectors.toMap(
            result -> result.get("age").toString(),
            result -> ((BigInteger) result.get("avg_use_time")).longValue()
        ));
    }

    @Override
    public Map<String, Long> groupCount(UseTime useTime) throws Exception {
        List<Map<String, Object>> results = useTimeMapper.groupCountByTypeAndPreview(useTime);
        return results.stream().collect(Collectors.toMap(
            result -> (String) result.get("use_time_range"),
            result -> (Long) result.get("count")
        ));
    }

    @Override
    public Map<String, Long> genderAvg(UseTime useTime) throws Exception {
         List<Map<String, Object>> results = useTimeMapper.genderAvgByTypeAndPreview(useTime);
        return results.stream().collect(Collectors.toMap(
            result -> (String) result.get("gender"),
            result -> ((BigInteger) result.get("avg_use_time")).longValue()
        ));
    }

    @Override
    public Map<String, Long> ageAvg(UseTime useTime) throws Exception {
        List<Map<String, Object>> results = useTimeMapper.ageAvgByTypeAndPreview(useTime);
        return results.stream().collect(Collectors.toMap(
            result -> result.get("age").toString(),
            result -> ((BigInteger) result.get("avg_use_time")).longValue()
        ));
    }
}
