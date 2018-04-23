package com.shixi.dao;

import com.shixi.entity.UserJob;

/**
 * @Description: 用户岗位关联Mapper
 * @author: hw
 * @date: 2018年4月1日 下午12:52:47
 */
public interface UserWithJobMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(UserJob record);

	int insertSelective(UserJob record);

	UserJob selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(UserJob record);

	int updateByPrimaryKey(UserJob record);
}