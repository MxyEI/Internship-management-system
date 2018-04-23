package com.shixi.dao;

import com.shixi.entity.UserJob;
import com.shixi.entity.UserWithJob;

/**
 * @Description: 用户岗位关联Mapper
 * @author: hw
 * @date: 2018年4月1日 下午12:52:47
 */
public interface UserWithJobMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(UserWithJob record);

	int insertSelective(UserWithJob record);

	UserWithJob selectByPrimaryKey(Integer userid);

	int updateByPrimaryKeySelective(UserWithJob record);

	int updateByPrimaryKey(UserWithJob record);
}