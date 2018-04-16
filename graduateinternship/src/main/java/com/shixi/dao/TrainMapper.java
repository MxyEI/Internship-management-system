package com.shixi.dao;

import com.shixi.entity.Train;

/**
 * @Description: 培训计划Mapper
 * @author: hw
 * @date: 2018年3月29日 下午12:23:04
 */
public interface TrainMapper {

	/**
	 * @Description: 查询公司培训计划
	 * @author: hw
	 * @date: 2018年4月1日 下午12:54:56
	 */
	Train selectByCompanyId(Integer id);

	int deleteByPrimaryKey(Integer id);

	int insert(Train record);

	int insertSelective(Train record);

	Train selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Train record);

	int updateByPrimaryKeyWithBLOBs(Train record);

	int updateByPrimaryKey(Train record);
}