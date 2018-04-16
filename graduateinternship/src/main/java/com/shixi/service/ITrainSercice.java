package com.shixi.service;

import com.shixi.entity.Train;

/**
 * @Description: 培训计划IService
 * @author: hw
 * @date: 2018年3月29日 下午12:23:48
 */
public interface ITrainSercice {
	int deleteByPrimaryKey(Integer id);

	int insert(Train record);

	int insertSelective(Train record);

	Train selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Train record);

	int updateByPrimaryKeyWithBLOBs(Train record);

	int updateByPrimaryKey(Train record);

	Train selectByCompanyId(Integer id);

}
