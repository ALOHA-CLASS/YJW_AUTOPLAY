package com.aloha.autoplay.service;

import java.util.List;

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

    @Autowired private UseTimeMapper UseTimeMapper;

    @Override
    public List<UseTime> getList() {
        return this.list();
    }

    @Override
    public PageInfo<UseTime> getPageList(int page, int pageSize) {
        PageHelper.startPage(page, pageSize);
        List<UseTime> list = UseTimeMapper.selectList(null);
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
    
}
