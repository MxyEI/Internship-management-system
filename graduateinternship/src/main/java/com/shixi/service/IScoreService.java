package com.shixi.service;

import java.util.List;
import java.util.Map;

import com.shixi.entity.Score;
import com.shixi.entity.vo.ScoreVO;

/**
 * @Description: 评价IService
 * @author: hw
 * @date: 2018年3月29日 下午2:10:50
 */
public interface IScoreService {
	int deleteByPrimaryKey(Integer id);

	int insert(Score record);

	int insertSelective(Score record);

	Score selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Score record);

	int updateByPrimaryKey(Score record);

	List<ScoreVO> findScoreOfUsers(Map<String, Object> map);

	Long getTotalScoreOfUsers(Map<String, Object> map);

	ScoreVO getUserScoreInfo(Integer id);
}
