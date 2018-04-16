package com.shixi.dao;

import java.util.List;
import java.util.Map;

import com.shixi.entity.vo.ScoreVO;

/**
 * @Description: 评分AstMapper
 * @author: hw
 * @date: 2018年3月29日 下午2:38:39
 */
public interface ScoreAstMapper {

	/**
	 * @Description: 查询某个公司下用户评分信息
	 * @author: hw
	 * @date: 2018年4月1日 下午12:56:19
	 */
	List<ScoreVO> findScoreOfUsers(Map<String, Object> map);

	/**
	 * @Description: 查询某个公司下用户数量
	 * @author: hw
	 * @date: 2018年4月1日 下午12:56:53
	 */
	Long getTotalScoreOfUsers(Map<String, Object> map);

	/**
	 * @Description: 查询用户和评价信息
	 * @author: hw
	 * @date: 2018年4月1日 下午12:59:37
	 */
	ScoreVO getUserScoreInfo(Integer id);
}
