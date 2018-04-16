package com.shixi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shixi.dao.TrainMapper;
import com.shixi.entity.Train;

/**
 * @Description: 培训计划ServiceImpl
 * @author: hw
 * @date: 2018年3月29日 下午12:24:41
 */
@Service
public class TrainServiceImpl implements ITrainSercice {

	@Autowired
	TrainMapper trainMapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return trainMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Train record) {
		return trainMapper.insert(record);
	}

	@Override
	public int insertSelective(Train record) {
		return trainMapper.insertSelective(record);
	}

	@Override
	public Train selectByPrimaryKey(Integer id) {
		return trainMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Train record) {
		return trainMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKeyWithBLOBs(Train record) {
		return trainMapper.updateByPrimaryKeyWithBLOBs(record);
	}

	@Override
	public int updateByPrimaryKey(Train record) {
		return trainMapper.updateByPrimaryKey(record);
	}

	@Override
	public Train selectByCompanyId(Integer id) {
		return trainMapper.selectByCompanyId(id);
	}

}
