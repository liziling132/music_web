package com.music.demo.mapper;

import com.music.demo.dao.Mv;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface MvMapper {

    Mv findById(Long id);

    List<Mv> findRecommendedMvsByCategoryId(@Param("categoryId") Long categoryId);
}
