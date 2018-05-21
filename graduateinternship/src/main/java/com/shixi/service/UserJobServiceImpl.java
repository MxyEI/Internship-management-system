package com.shixi.service;

import java.util.List;
import java.util.Map;

import com.shixi.dao.UserWithJobAstMapper;
import com.shixi.dao.UserWithJobMapper;
import com.shixi.entity.UserWithJob;
import com.shixi.entity.vo.CompanyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.shixi.entity.vo.UserJobVO;

/**
 * @Description:用户岗位ServiceImpl
 * @author: hw
 * @date: 2018年3月27日 下午5:02:09
 */
@Service
public class UserJobServiceImpl implements IUserJobService {

	@Autowired
	UserWithJobMapper userJobMapper;

	@Autowired
	UserWithJobAstMapper userJobAstMapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return userJobMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(UserWithJob record) {
		return userJobMapper.insert(record);
	}

	@Override
	public int insertSelective(UserWithJob record) {
		return userJobMapper.insertSelective(record);
	}

	@Override
	public UserWithJob selectByPrimaryKey(Integer id) {
		return userJobMapper.selectByPrimaryKey(id);
	}

	@Override
	public UserWithJob selectByuidjid(Map map) {
		return userJobMapper.selectByuidjid(map);
	}

	@Override
	public int updateByPrimaryKeySelective(UserWithJob record) {
		return userJobMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(UserWithJob record) {
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

    @Override
    public List<UserJobVO> findAscUserJobsByUserid(Map<String, Object> map) {
        return userJobAstMapper.findAscUserJobsByUserid(map);
    }

	@Override
	public List<UserJobVO> findAscUserJobsWithUserid(Map<String,Object> map) {
		return userJobAstMapper.findAscUserJobsWithUserid(map);
	}

	@Override
	public Long getTotlaAscUserJobsWithuser(Map<String,Object> map) {
		return userJobAstMapper.getTotlaAscUserJobsWithuser(map);
	}


}
