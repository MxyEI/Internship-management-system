package com.shixi.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shixi.dao.UserJobAstMapper;
import com.shixi.dao.UserJobMapper;
import com.shixi.entity.UserJob;
import com.shixi.entity.vo.UserJobVO;

/**
 * @Description:用户岗位ServiceImpl
 * @author: hw
 * @date: 2018年3月27日 下午5:02:09
 */
@Service
public class UserJobServiceImpl implements IUserJobService {

	@Autowired
	UserJobMapper userJobMapper;

	@Autowired
	UserJobAstMapper userJobAstMapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return userJobMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(UserJob record) {
		return userJobMapper.insert(record);
	}

	@Override
	public int insertSelective(UserJob record) {
		return userJobMapper.insertSelective(record);
	}

	@Override
	public UserJob selectByPrimaryKey(Integer id) {
		return userJobMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(UserJob record) {
		return userJobMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(UserJob record) {
		return userJobMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<UserJobVO> findAscUserJobs(Map<String, Object> map) {
		return userJobAstMapper.findAscUserJobs(map);
	}

	@Override
	public List<UserJobVO> findAscUserJobsWithSuccess(Map<String, Object> map) {
		return userJobAstMapper.findAscUserJobsWithSuccess(map);
	}

	@Override
	public Long getTotlaAscUserJobs(Map<String, Object> map) {
		return userJobAstMapper.getTotlaAscUserJobs(map);
	}

	@Override
	public Long getTotlaAscUserJobsWithSuccess(Map<String, Object> map) {
		return userJobAstMapper.getTotlaAscUserJobsWithSuccess(map);
	}

}
