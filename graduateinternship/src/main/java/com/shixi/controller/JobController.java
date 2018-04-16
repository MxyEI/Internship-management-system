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
import com.shixi.entity.Job;
import com.shixi.entity.PageBean;
import com.shixi.service.IJobService;
import com.shixi.util.DateUtil;
import com.shixi.util.ResponseUtil;
import com.shixi.util.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @Description:岗位
 * @author: hw
 * @date: 2018年3月21日 下午8:34:02
 */
@Controller
@RequestMapping("/jobs")
public class JobController {

	@Autowired
	private IJobService jobService;
	private static final Logger log = Logger.getLogger(JobController.class);// 日志文件

	/**
	 * @Description: 列出公司的岗位
	 * @author: hw
	 * @date: 2018年3月28日 下午1:44:15
	 */
	@RequestMapping(value = "/datagrid", method = RequestMethod.GET)
	public String list(@RequestParam(value = "page", required = false) String page,
			@RequestParam(value = "rows", required = false) String rows,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order, Job job, HttpServletResponse response)
			throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		if (page != null && rows != null) {
			PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
			map.put("start", pageBean.getStart());
			map.put("size", pageBean.getPageSize());
		}
		if (job != null && job.getCompanyId() != null) {
			map.put("companyId", job.getCompanyId());
		}
		if (job != null && job.getName() != null) {
			map.put("name", StringUtil.formatLike(job.getName()));
		}
		if (sort != null && order != null) {
			map.put("order", order);
			map.put("sort", sort);
		}
		List<Job> jobList = jobService.selectJobsByName(map);
		Long total = jobService.getTotalJobsByName(map);
		JSONObject result = new JSONObject();
		JSONArray jsonArray = JSONArray.fromObject(jobList);
		result.put("rows", jsonArray);
		result.put("total", total);
		log.info("request: job/list , map: " + map.toString());
		ResponseUtil.write(response, result);
		return null;
	}

	/**
	 * @Description: 添加岗位信息
	 * @author: hw
	 * @date: 2018年3月28日 下午1:44:31
	 */
	@RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseBody
	public Result add(Job job) throws Exception {
		log.info(job.getLapse());
		int resultTotal = 0;
		job.setGmtCreate(DateUtil.getCurrentDateStr());
		resultTotal = jobService.insert(job);
		log.info("request: job/save , " + job.toString());

		if (resultTotal > 0) {
			return ResultGenerator.genSuccessResult();
		} else {
			return ResultGenerator.genFailResult("添加失败");
		}
	}

	/**
	 * @Description: 更新岗位信息
	 * @author: hw
	 * @date: 2018年3月28日 下午1:44:44
	 */
	@RequestMapping(value = "", method = RequestMethod.PATCH)
	@ResponseBody
	public Result update(Job job) throws Exception {
		int resultTotal = 0;
		job.setGmtModify(DateUtil.getCurrentDateStr());
		resultTotal = jobService.updateByPrimaryKeyWithBLOBs(job);
		log.info("request: job/update , " + job.toString());
		if (resultTotal > 0) {
			return ResultGenerator.genSuccessResult();
		} else {
			return ResultGenerator.genFailResult("修改失败");
		}
	}

	/**
	 * @Description: 查询岗位信息
	 * @author: hw
	 * @date: 2018年3月28日 下午1:44:52
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Result get(@PathVariable("id") Integer id) throws Exception {
		Job job = jobService.selectByPrimaryKey(id);
		Result result = ResultGenerator.genSuccessResult();
		result.setData(job);
		log.info("request: job/findById");
		return result;
	}

	/**
	 * @Description: 删除岗位信息
	 * @param id
	 *            岗位id 若userjob用户岗位关联表中没有该岗位id,则直接删除，否则设为失效
	 * @author: hw
	 * @date: 2018年3月28日 下午1:45:07
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Result delete(@PathVariable("id") Integer id) throws Exception {
		if (id == null) {
			return ResultGenerator.genFailResult("ERROR");
		}
		int resultCode = jobService.deleteByPrimaryKey(id);
		log.info("request: job/delete , id: " + id);
		if (resultCode == 0) {
			Job j = new Job();
			j.setId(id);
			j.setLapse(true);
			jobService.updateByPrimaryKeySelective(j);
			return ResultGenerator.genFailResult("已经有学生申请这个岗位，不能删除，将设置为失效");
		}
		return ResultGenerator.genSuccessResult();
	}
}
