package com.shixi.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.Result;
import com.common.ResultGenerator;
import com.shixi.entity.PageBean;
import com.shixi.entity.Score;
import com.shixi.entity.vo.ScoreVO;
import com.shixi.service.IScoreService;
import com.shixi.util.DateUtil;
import com.shixi.util.ResponseUtil;
import com.shixi.util.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @Description: 评价Controller
 * @author: hw
 * @date: 2018年3月29日 下午12:26:38
 */
@Controller
@RequestMapping("/scores")
public class ScoreController {

	@Autowired
	IScoreService scoreService;
	private static final Logger log = Logger.getLogger(ScoreController.class);// 日志文件

	/**
	 * @Description: 查询评分信息
	 * @author: hw
	 * @date: 2018年3月29日 下午3:13:28
	 */
	@RequestMapping(value = "/datagrid", method = RequestMethod.GET)
	public String list(@RequestParam(value = "page", required = false) String page,
			@RequestParam(value = "rows", required = false) String rows,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order, ScoreVO scoreVO,
			HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		if (page != null && rows != null) {
			PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
			map.put("start", pageBean.getStart());
			map.put("size", pageBean.getPageSize());
		}
		if (scoreVO.getCompanyId() != null) {
			map.put("companyId", scoreVO.getCompanyId());
		}
		if (scoreVO.getUser() != null && scoreVO.getUser().getRealname() != null) {
			map.put("realname", StringUtil.formatLike(scoreVO.getUser().getRealname()));
		}
		if (sort != null && order != null) {
			map.put("order", order);
			map.put("sort", sort);
		}
		List<ScoreVO> list = scoreService.findScoreOfUsers(map);
		Long total = scoreService.getTotalScoreOfUsers(map);
		JSONObject result = new JSONObject();
		JSONArray jsonArray = JSONArray.fromObject(list);
		result.put("rows", jsonArray);
		result.put("total", total);
		log.info("request: score/list , map: " + map.toString());
		ResponseUtil.write(response, result);
		return null;
	}

	/**
	 * @Description: 添加评价信息
	 * @author: hw
	 * @date: 2018年3月29日 下午2:15:41
	 */
	@RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseBody
	public Result add(Score score) throws Exception {
		int resultTotal = 0;
		score.setGmtCreate(DateUtil.getCurrentDateStr());
		resultTotal = scoreService.insert(score);
		log.info("request: score/save , " + score.toString());
		if (resultTotal > 0) {
			return ResultGenerator.genSuccessResult();
		} else {
			return ResultGenerator.genFailResult("添加失败");
		}
	}

	/**
	 * @Description: 更新评价信息
	 * @author: hw
	 * @date: 2018年3月29日 下午2:15:55
	 */
	@RequestMapping(value = "", method = RequestMethod.PATCH)
	@ResponseBody
	public Result update(Score score) throws Exception {
		int resultTotal = 0;
		score.setGmtModify(DateUtil.getCurrentDateStr());
		resultTotal = scoreService.updateByPrimaryKeySelective(score);
		log.info("request: score/update , " + score.toString());
		if (resultTotal > 0) {
			return ResultGenerator.genSuccessResult();
		} else {
			return ResultGenerator.genFailResult("修改失败");
		}
	}

	/**
	 * @Description: 查询评价信息
	 * @author: hw
	 * @date: 2018年3月29日 下午2:16:08
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Result get(@PathVariable("id") Integer id) throws Exception {
		Score score = scoreService.selectByPrimaryKey(id);
		Result result = ResultGenerator.genSuccessResult();
		result.setData(score);
		log.info("request: score/findById");
		return result;
	}

	/**
	 * @Description: 刪除评价信息
	 * @author: hw
	 * @date: 2018年3月29日 下午2:16:17
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Result delete(@PathVariable("id") Integer id) throws Exception {
		if (id == null) {
			return ResultGenerator.genFailResult("ERROR");
		}
		scoreService.deleteByPrimaryKey(id);
		log.info("request: score/delete , id: " + id);
		return ResultGenerator.genSuccessResult();
	}

	/**
	 * @Description: 查询用户和评价信息
	 * @author: hw
	 * @date: 2018年3月29日 下午2:16:08
	 */
	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Result findUserScoreInfo(@PathVariable("id") Integer id) throws Exception {
		ScoreVO score = scoreService.getUserScoreInfo(id);
		Result result = ResultGenerator.genSuccessResult();
		result.setData(score);
		log.info("request: score/findById");
		return result;
	}
}
