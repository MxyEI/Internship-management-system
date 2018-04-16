package com.shixi.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shixi.dao.UserMapper;
import com.shixi.entity.User;

/**
 * @Description: 用户ServiceImpl
 * @author: hw
 * @date: 2018年3月20日 下午1:42:10
 */
@Service
public class UserServiceImpl implements IUserService {
	@Autowired
	private UserMapper userMapper;

	@Override
	public int deleteByPrimaryKey(Integer pk) {
		return userMapper.deleteByPrimaryKey(pk);
	}

	public int deleteByIds(List<Integer> ids) {
		return userMapper.deleteByIds(ids);
	}

	@Override
	public int insert(User record) {
		return userMapper.insert(record);
	}

	@Override
	public int insertSelective(User record) {
		return userMapper.insertSelective(record);
	}

	@Override
	public User selectByPrimaryKey(Integer pk) {
		return (User) userMapper.selectByPrimaryKey(pk);
	}

	@Override
	public int updateByPrimaryKeySelective(User record) {
		return userMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(User record) {
		return userMapper.updateByPrimaryKey(record);
	}

	@Override
	public User login(User user) {
		return userMapper.login(user);
	}

	@Override
	public User checkPwd(User user) {
		return userMapper.checkPwd(user);
	}
	
	@Override
	public int checkName(String name) {
		return userMapper.checkName(name);
	}

	@Override
	public List<User> findUsers(Map<String, Object> map) {
		return userMapper.findUsers(map);
	}

	@Override
	public Long getTotalUser(Map<String, Object> map) {
		return userMapper.getTotalUser(map);
	}
}
