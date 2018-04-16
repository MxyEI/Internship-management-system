package com.shixi.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.Result;
import com.common.ResultGenerator;
import com.shixi.entity.Train;
import com.shixi.service.ITrainSercice;
import com.shixi.util.DateUtil;

/**
 * @Description: 培训计划Controller
 * @author: hw
 * @date: 2018年3月29日 下午12:26:38
 */
@Controller
@RequestMapping("/trains")
public class TrainController {

	@Autowired
	ITrainSercice trainService;
	private static final Logger log = Logger.getLogger(TrainController.class);// 日志文件

	/**
	 * @Description: 添加培训计划
	 * @author: hw
	 * @date: 2018年3月29日 下午12:29:09
	 */
	@RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseBody
	public Result add(Train train) throws Exception {
		int resultTotal = 0;
		train.setGmtCreate(DateUtil.getCurrentDateStr());
		resultTotal = trainService.insert(train);
		log.info("request: train/save , " + train.toString());
		if (resultTotal > 0) {
			return ResultGenerator.genSuccessResult();
		} else {
			return ResultGenerator.genFailResult("添加失败");
		}
	}

	/**
	 * @Description: 更新培训计划
	 * @author: hw
	 * @date: 2018年3月29日 下午12:36:02
	 */
	@RequestMapping(value = "", method = RequestMethod.PATCH)
	@ResponseBody
	public Result update(Train train) throws Exception {
		int resultTotal = 0;
		train.setGmtModify(DateUtil.getCurrentDateStr());
		resultTotal = trainService.updateByPrimaryKeyWithBLOBs(train);
		log.info("request: train/update , " + train.toString());
		if (resultTotal > 0) {
			return ResultGenerator.genSuccessResult();
		} else {
			return ResultGenerator.genFailResult("修改失败");
		}
	}

	/**
	 * @Description: 查询培训计划
	 * @param
	 * @author: hw
	 * @date: 2018年3月29日 下午12:36:19
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Result get(@PathVariable("id") Integer id) throws Exception {
		Train train = trainService.selectByPrimaryKey(id);
		Result result = ResultGenerator.genSuccessResult();
		result.setData(train);
		log.info("request: train/findById");
		return result;
	}

	/**
	 * @Description: 删除培训计划
	 * @author: hw
	 * @date: 2018年3月29日 下午12:36:29
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Result delete(@PathVariable("id") Integer id) throws Exception {
		if (id == null) {
			return ResultGenerator.genFailResult("ERROR");
		}
		trainService.deleteByPrimaryKey(id);
		log.info("request: train/delete , id: " + id);
		return ResultGenerator.genSuccessResult();
	}

	/**
	 * @Description: 查询培训计划
	 * @param id:公司id
	 * @author: hw
	 * @date: 2018年3月29日 下午12:36:19
	 */
	@RequestMapping(value = "/company/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Result findByCompanyId(@PathVariable("id") Integer id) throws Exception {
		if (id == null) {
			return ResultGenerator.genFailResult("ERROR");
		}
		Train result = trainService.selectByCompanyId(id);
		log.info("request: train/company/get , id " + id);
		if (result != null) {
			Map<String, Train> data = new HashMap<String, Train>();
			data.put("data", result);
			return ResultGenerator.genSuccessResult(data);
		} else {
			return ResultGenerator.genFailResult("FAIL");
		}
	}
}
