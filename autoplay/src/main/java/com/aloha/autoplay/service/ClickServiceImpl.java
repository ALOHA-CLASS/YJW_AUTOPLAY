package com.aloha.autoplay.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aloha.autoplay.domain.Click;
import com.aloha.autoplay.mapper.AutoPlayMapper;
import com.aloha.autoplay.mapper.ClickMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ClickServiceImpl extends ServiceImpl<ClickMapper, Click> implements ClickService  {

    @Autowired private ClickMapper clickMapper;
    @Autowired private AutoPlayMapper autoPlayMapper;

    @Override
    public List<Click> getList() {
        return this.list();
    }

    @Override
    public PageInfo<Click> getPageList(int page, int pageSize) {
        PageHelper.startPage(page, pageSize);
        List<Click> list = clickMapper.selectList(null);
        PageInfo<Click> pageInfo = new PageInfo<Click>(list);
        log.info("pageInfo: {}", pageInfo);
        return pageInfo;
    }

    @Override
    public Click get(Long no) {
        QueryWrapper<Click> queryWrapper = new QueryWrapper<Click>();
        queryWrapper.eq("no", no);
        return this.getOne(queryWrapper);
    }

    @Override
    public Click getById(String id) {
        QueryWrapper<Click> queryWrapper = new QueryWrapper<Click>();
        queryWrapper.eq("id", id);
        return this.getOne(queryWrapper);
    }

    @Override
    public boolean create(Click entity) {
        return this.save(entity);
    }

    @Override
    public boolean update(Click entity) {
        QueryWrapper<Click> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("no", entity.getNo());
        return this.update(entity, queryWrapper);
    }

    @Override
    public boolean updateById(Click entity) {
        QueryWrapper<Click> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", entity.getId());
        return this.update(entity, queryWrapper);
    }

    @Override
    @Transactional
    public boolean delete(Long no) {
        QueryWrapper<Click> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("no", no);
        return this.remove(queryWrapper);
    }

    @Override
    @Transactional
    public boolean deleteById(String id) {
        QueryWrapper<Click> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        return this.remove(queryWrapper);
    }

    @Override
    public long count() {
        return clickMapper.selectCount(null);
    }

    @Override
    public long todayCount() {
        QueryWrapper<Click> queryWrapper = new QueryWrapper<>();
        queryWrapper.apply("DATE_FORMAT(`CREATED_AT`, '%y%m%d') = DATE_FORMAT(NOW(), '%y%m%d')");
        return this.count(queryWrapper);
    }

    @Override
    public Double clickRate() {
        Double clickRate = clickMapper.clickRate();
        return clickRate != null ? clickRate : 0.0;
    }

    @Override
    public Map<String, Long> genderCount() throws Exception {
        List<Map<String, Object>> results = clickMapper.genderCount();
        return results.stream().collect(Collectors.toMap(
            result -> (String) result.get("gender"),
            result -> ((BigInteger) result.get("count")).longValue()
        ));
    }

    @Override
    public Map<String, Long> ageCount() throws Exception {
        List<Map<String, Object>> results = clickMapper.ageCount();
        return results.stream().collect(Collectors.toMap(
            result -> result.get("age").toString(),
            result -> ((BigInteger) result.get("count")).longValue()
        ));
    }

    @Override
    public Map<String, Double> genderAvg() throws Exception {
        List<Map<String, Object>> results = clickMapper.genderAvg();
        return results.stream().collect(Collectors.toMap(
            result -> (String) result.get("gender"),
            result -> result.get("avg_click") != null ? ((BigDecimal) result.get("avg_click")).doubleValue() : 0.0
        ));
    }

    @Override
    public Map<String, Double> ageAvg() throws Exception {
        List<Map<String, Object>> results = clickMapper.ageAvg();
        return results.stream().collect(Collectors.toMap(
            result -> result.get("age").toString(),
            result -> result.get("avg_click") != null ? ((BigDecimal) result.get("avg_click")).doubleValue() : 0.0
        ));
    }
}
