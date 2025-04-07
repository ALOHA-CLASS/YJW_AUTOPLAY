package com.aloha.autoplay.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aloha.autoplay.domain.Movies;
import com.aloha.autoplay.mapper.MovieMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MovieServiceImpl extends ServiceImpl<MovieMapper, Movies> implements MovieService  {

    @Autowired private MovieMapper movieMapper;

    @Override
    public List<Movies> getList() {
        return this.list();
    }

    @Override
    public PageInfo<Movies> getPageList(int page, int pageSize) {
        PageHelper.startPage(page, pageSize);
        List<Movies> list = movieMapper.selectList(null);
        PageInfo<Movies> pageInfo = new PageInfo<Movies>(list);
        log.info("pageInfo: {}", pageInfo);
        return pageInfo;
    }

    @Override
    public Movies get(Long no) {
        QueryWrapper<Movies> queryWrapper = new QueryWrapper<Movies>();
        queryWrapper.eq("no", no);
        return this.getOne(queryWrapper);
    }

    @Override
    public Movies getById(String id) {
        QueryWrapper<Movies> queryWrapper = new QueryWrapper<Movies>();
        queryWrapper.eq("id", id);
        return this.getOne(queryWrapper);
    }

    @Override
    public boolean create(Movies entity) {
        return this.save(entity);
    }

    @Override
    public boolean update(Movies entity) {
        QueryWrapper<Movies> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("no", entity.getNo());
        return this.update(entity, queryWrapper);
    }

    @Override
    public boolean updateById(Movies entity) {
        QueryWrapper<Movies> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", entity.getId());
        return this.update(entity, queryWrapper);
    }

    @Override
    @Transactional
    public boolean delete(Long no) {
        QueryWrapper<Movies> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("no", no);
        return this.remove(queryWrapper);
    }

    @Override
    @Transactional
    public boolean deleteById(String id) {
        QueryWrapper<Movies> queryWrapper = new QueryWrapper<>();
        if( id.contains(",")) {
            String[] ids = id.split(",");
            for (String i : ids) {
                queryWrapper.or().eq("id", i);
            }
        } else {
            queryWrapper.eq("id", id);
        }
        return this.remove(queryWrapper);
    }

    @Override
    public long count() {
        return movieMapper.selectCount(null);
    }

    @Override
    public long todayCount() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'todayCount'");
    }
    
}
