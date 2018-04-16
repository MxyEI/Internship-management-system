package com.shixi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shixi.dao.RecruitMapper;
import com.shixi.entity.Recruit;

/**
 * @Description: 招聘信息ServiceImpl
 * @author: hw   
 * @date: 2018年3月28日 下午1:50:56 
 */
@Service
public class RecruitServiceImpl implements IRecruitService {

	@Autowired
	private RecruitMapper recruitMapper;

	@Override
	public int insert(Recruit record) {
		return recruitMapper.insert(record);
	}

	@Override
	public int insertSelective(Recruit record) {
		return recruitMapper.insertSelective(record);
	}

	@Override
	public Recruit selectByPrimaryKey(Integer id) {
		return recruitMapper.selectByPrimaryKey(id);
	}

	@Override
	public Recruit selectByCompanyId(Integer id) {
		return recruitMapper.selectByCompanyId(id);
	}
	
	@Override
	public int updateByPrimaryKeySelective(Recruit record) {
		return recruitMapper.updateByPrimaryKey(record);
	}

	@Override
	public int updateByPrimaryKeyWithBLOBs(Recruit record) {
		return recruitMapper.updateByPrimaryKeyWithBLOBs(record);
	}

	@Override
	public int updateByPrimaryKey(Recruit record) {
		return recruitMapper.updateByPrimaryKey(record);
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return recruitMapper.deleteByPrimaryKey(id);
	}

}
