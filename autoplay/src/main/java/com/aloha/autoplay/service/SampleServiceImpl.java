package com.aloha.autoplay.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aloha.autoplay.domain.Sample;
import com.aloha.autoplay.mapper.SampleMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SampleServiceImpl extends ServiceImpl<SampleMapper, Sample> implements SampleService  {

    @Autowired private SampleMapper sampleMapper;

    @Override
    public List<Sample> getList() {
        return this.list();
    }

    @Override
    public PageInfo<Sample> getPageList(int page, int pageSize) {
        PageHelper.startPage(page, pageSize);
        List<Sample> list = sampleMapper.selectList(null);
        PageInfo<Sample> pageInfo = new PageInfo<Sample>(list);
        log.info("pageInfo: {}", pageInfo);
        return pageInfo;
    }

    @Override
    public Sample get(Long no) {
        QueryWrapper<Sample> queryWrapper = new QueryWrapper<Sample>();
        queryWrapper.eq("no", no);
        return this.getOne(queryWrapper);
    }

    @Override
    public Sample getById(String id) {
        QueryWrapper<Sample> queryWrapper = new QueryWrapper<Sample>();
        queryWrapper.eq("id", id);
        return this.getOne(queryWrapper);
    }

    @Override
    public boolean create(Sample entity) {
        return this.save(entity);
    }

    @Override
    public boolean update(Sample entity) {
        QueryWrapper<Sample> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("no", entity.getNo());
        return this.update(entity, queryWrapper);
    }

    @Override
    public boolean updateById(Sample entity) {
        QueryWrapper<Sample> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", entity.getId());
        return this.update(entity, queryWrapper);
    }

    @Override
    @Transactional
    public boolean delete(Long no) {
        QueryWrapper<Sample> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("no", no);
        return this.remove(queryWrapper);
    }

    @Override
    @Transactional
    public boolean deleteById(String id) {
        QueryWrapper<Sample> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        return this.remove(queryWrapper);
    }

    @Override
    public long count() {
        return sampleMapper.selectCount(null);
    }

    @Override
    public long todayCount() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'todayCount'");
    }
    
}
