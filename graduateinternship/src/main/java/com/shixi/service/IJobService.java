package com.shixi.service;

import java.util.List;
import java.util.Map;

import com.shixi.entity.Job;

/**
 * @Description:岗位IService
 * @author: hw
 * @date: 2018年3月21日 下午8:30:53
 */
public interface IJobService {
	int deleteByPrimaryKey(Integer id);

	int insert(Job record);

	int insertSelective(Job record);

	Job selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Job record);

	int updateByPrimaryKeyWithBLOBs(Job record);

	int updateByPrimaryKey(Job record);

	List<Job> selectJobsByName(Map<String, Object> map);

	Long getTotalJobsByName(Map<String, Object> map);
}
