package com.shixi.service;

import java.util.List;
import java.util.Map;

import com.shixi.entity.User;

/**
 * @Description: 用户IService
 * @author: hw
 * @date: 2018年3月20日 下午1:41:54
 */
public interface IUserService {
	int deleteByPrimaryKey(Integer pk);

	int deleteByIds(List<Integer> ids);

	int insert(User record);

	int insertSelective(User record);

	User selectByPrimaryKey(Integer pk);

	int updateByPrimaryKeySelective(User record);

	int updateByPrimaryKey(User record);

	User login(User user);

	User checkPwd(User user);

	int checkName(String name);

	List<User> findUsers(Map<String, Object> map);

	Long getTotalUser(Map<String, Object> map);
}
