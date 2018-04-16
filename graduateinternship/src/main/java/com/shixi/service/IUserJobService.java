package com.shixi.service;

import java.util.List;
import java.util.Map;

import com.shixi.entity.UserJob;
import com.shixi.entity.vo.UserJobVO;

/**
 * @Description: 用户岗位service接口
 * @author: hw
 * @date: 2018年3月27日 下午5:01:16
 */
public interface IUserJobService {
	int deleteByPrimaryKey(Integer id);

	int insert(UserJob record);

	int insertSelective(UserJob record);

	UserJob selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(UserJob record);

	int updateByPrimaryKey(UserJob record);

	List<UserJobVO> findAscUserJobs(Map<String, Object> map);
	
	Long getTotlaAscUserJobs(Map<String,Object> map);
}
