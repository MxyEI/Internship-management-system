package com.shixi.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shixi.dao.ScoreAstMapper;
import com.shixi.dao.ScoreMapper;
import com.shixi.entity.Score;
import com.shixi.entity.vo.ScoreVO;

/**
 * @Description:评价ServiceImpl
 * @author: hw
 * @date: 2018年3月29日 下午2:11:25
 */
@Service
public class ScoreServiceImpl implements IScoreService {

	@Autowired
	ScoreMapper scoreMapper;

	@Autowired
	ScoreAstMapper scoreAstMapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return scoreMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Score record) {
		return scoreMapper.insert(record);
	}

	@Override
	public int insertSelective(Score record) {
		return scoreMapper.insertSelective(record);
	}

	@Override
	public Score selectByPrimaryKey(Integer id) {
		return scoreMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Score record) {
		return scoreMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Score record) {
		return scoreMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<ScoreVO> findScoreOfUsers(Map<String, Object> map) {
		return scoreAstMapper.findScoreOfUsers(map);
	}

	@Override
	public Long getTotalScoreOfUsers(Map<String, Object> map) {
		return scoreAstMapper.getTotalScoreOfUsers(map);
	}

	@Override
	public ScoreVO getUserScoreInfo(Integer id) {
		return scoreAstMapper.getUserScoreInfo(id);
	}

}
