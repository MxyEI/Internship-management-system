package com.shixi.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shixi.dao.JobMapper;
import com.shixi.entity.Job;

/**
 * @Description: 岗位ServiceImpl
 * @author: hw   
 * @date: 2018年3月21日 下午8:31:03 
 */
@Service
public class JobServiceImpl implements IJobService{
	@Autowired
	private JobMapper jobMapper;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return jobMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Job record) {
		return jobMapper.insert(record);
	}

	@Override
	public int insertSelective(Job record) {
		return jobMapper.insertSelective(record);
	}

	@Override
	public Job selectByPrimaryKey(Integer id) {
		return jobMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Job record) {
		return jobMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKeyWithBLOBs(Job record) {
		return jobMapper.updateByPrimaryKeyWithBLOBs(record);
	}

	@Override
	public int updateByPrimaryKey(Job record) {
		return jobMapper.updateByPrimaryKey(record);
	}
	
	@Override
	public List<Job> selectJobsByName(Map<String,Object> map){
		return jobMapper.selectJobsByName(map);
	}
	
	public Long getTotalJobsByName(Map<String,Object> map) {
		return jobMapper.getTotalJobsByName(map);
	}

}
