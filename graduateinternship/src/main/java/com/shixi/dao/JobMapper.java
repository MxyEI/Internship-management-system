package com.shixi.dao;

import java.util.List;
import java.util.Map;

import com.shixi.entity.Job;

/**
 * @Description: 岗位信息Mapper
 * @author: hw
 * @date: 2018年4月1日 下午1:00:06
 */
public interface JobMapper {

	/**
	 * @Description: 根据岗位名模糊查询岗位信息
	 * @author: hw
	 * @date: 2018年4月1日 下午1:00:25
	 */
	List<Job> selectJobsByName(Map<String, Object> map);

	/**
	 * @Description: 根据岗位名模糊查询岗位数量
	 * @author: hw
	 * @date: 2018年4月1日 下午1:00:25
	 */
	Long getTotalJobsByName(Map<String, Object> map);

	int deleteByPrimaryKey(Integer id);

	int insert(Job record);

	int insertSelective(Job record);

	Job selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Job record);

	int updateByPrimaryKeyWithBLOBs(Job record);

	int updateByPrimaryKey(Job record);
}