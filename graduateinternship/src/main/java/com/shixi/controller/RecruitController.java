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
import com.shixi.entity.Recruit;
import com.shixi.service.IRecruitService;
import com.shixi.util.DateUtil;

/**
 * @Description: 招聘信息
 * @author: hw
 * @date: 2018年3月23日 上午9:48:17
 */
@Controller
@RequestMapping("/recruits")
public class RecruitController {

	@Autowired
	private IRecruitService recruitService;
	private static final Logger log = Logger.getLogger(RecruitController.class);

	/**
	 * @Description: 添加招聘信息
	 * @author: hw   
	 * @date: 2018年3月28日 下午1:45:17 
	 */
	@RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseBody
	public Result add(Recruit recruit) throws Exception {
		int resultCode = 0;
		recruit.setGmtCreate(DateUtil.getCurrentDateStr());
		resultCode = recruitService.insert(recruit);
		log.info("request: recruit/update , recruit: " + recruit.toString());
		if (resultCode > 0) {
			return ResultGenerator.genSuccessResult();
		}
		return ResultGenerator.genFailResult("添加失败！");
	}

	/**
	 * @Description: 更新招聘信息
	 * @author: hw   
	 * @date: 2018年3月28日 下午1:45:29 
	 */
	@RequestMapping(value = "", method = RequestMethod.PATCH)
	@ResponseBody
	public Result update(Recruit recruit) throws Exception {
		int resultTotal = 0;
		resultTotal = recruitService.updateByPrimaryKeyWithBLOBs(recruit);
		log.info("request: recruit/update , " + recruit.toString());
		if (resultTotal > 0) {
			return ResultGenerator.genSuccessResult();
		} else {
			return ResultGenerator.genFailResult("修改失败");
		}
	}

	/**
	 * @Description: 获取招聘信息
	 * @author: hw   
	 * @date: 2018年3月28日 下午1:45:39 
	 */
	@RequestMapping(value = "/{companyId}", method = RequestMethod.GET)
	@ResponseBody
	public Result get(@PathVariable(value = "companyId") Integer companyId) throws Exception {
		if (companyId == null) {
			return ResultGenerator.genFailResult("ERROR");
		}
		Recruit result = recruitService.selectByCompanyId(companyId);
		log.info("request: recruit/get , companyId " + companyId);
		if (result != null) {
			Map<String, Recruit> data = new HashMap<String, Recruit>();
			data.put("currentRecruit", result);
			return ResultGenerator.genSuccessResult(data);
		} else {
			return ResultGenerator.genFailResult("FAIL");
		}
	}
}
