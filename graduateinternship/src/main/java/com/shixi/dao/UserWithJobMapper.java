package com.shixi.dao;

import com.shixi.entity.UserWithJob;

import java.util.Map;

/**
 * @Description: 用户岗位关联Mapper
 * @author: hw
 * @date: 2018年4月1日 下午12:52:47
 */
public interface UserWithJobMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(UserWithJob record);

	int insertSelective(UserWithJob record);

	UserWithJob selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(UserWithJob record);

	int updateByPrimaryKey(UserWithJob record);

	UserWithJob selectByuidjid(Map map);
}