package com.aloha.autoplay.service;

import java.util.Collections;
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

    @Override
    public List<Movies> getListByType(String type) throws Exception {
        QueryWrapper<Movies> queryWrapper = new QueryWrapper<Movies>();
        queryWrapper.eq("type", type);
        return this.list(queryWrapper);
    }

    @Override
    public Movies getNextMovie(String id) throws Exception {
        QueryWrapper<Movies> queryWrapper = new QueryWrapper<Movies>();
        queryWrapper.eq("id", id);
        Movies movie = this.getOne(queryWrapper);
        if (movie == null) {
            return null;
        }
        String type = movie.getType();
        queryWrapper.clear();
        queryWrapper.eq("type", type);
        queryWrapper.gt("no", movie.getNo());
        List<Movies> list = this.list(queryWrapper);
        if (list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }

    /**
     * id 로 해당 영화 조회하고,
     * 해다 영화의 type 과 같은 영화 리스트 조회
     */
    @Override
    public List<Movies> getRelatedMovie(String id) throws Exception {
        QueryWrapper<Movies> queryWrapper = new QueryWrapper<Movies>();
        queryWrapper.eq("id", id);
        Movies movie = this.getOne(queryWrapper);
        if (movie == null) {
            return null;
        }
        String type = movie.getType();
        queryWrapper.clear();
        queryWrapper.eq("type", type);
        queryWrapper.ne("id", id);
        List<Movies> list = this.list(queryWrapper);
        Collections.shuffle(list);
        return list;
    }
    
}
