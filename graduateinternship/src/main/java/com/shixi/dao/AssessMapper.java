package com.shixi.dao;

import com.shixi.entity.Assess;
import org.springframework.stereotype.Repository;

@Repository
public interface AssessMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Assess assess);

    int insertSelective(Assess assess);

    Assess selectByPrimaryKey(Integer pk);

    int updateByPrimaryKeySelective(Assess assess);

    int updateByPrimaryKey(Assess assess);

    Assess selectByUserid(Integer pk);
}
