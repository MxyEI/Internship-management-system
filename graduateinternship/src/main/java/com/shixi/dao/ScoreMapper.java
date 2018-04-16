package com.shixi.dao;

import com.shixi.entity.Score;

/**
 * @Description: 评价Mapper
 * @author: hw
 * @date: 2018年4月1日 下午12:55:23
 */
public interface ScoreMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(Score record);

	int insertSelective(Score record);

	Score selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Score record);

	int updateByPrimaryKey(Score record);
}