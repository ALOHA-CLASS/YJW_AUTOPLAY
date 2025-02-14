package com.aloha.autoplay.service;

import java.util.List;

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

    @Autowired private AutoPlayMapper AutoPlayMapper;

    @Override
    public List<AutoPlay> getList() {
        return this.list();
    }

    @Override
    public PageInfo<AutoPlay> getPageList(int page, int pageSize) {
        PageHelper.startPage(page, pageSize);
        List<AutoPlay> list = AutoPlayMapper.selectList(null);
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
    
}
