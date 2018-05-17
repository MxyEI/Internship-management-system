package com.shixi.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.shixi.entity.vo.CompanyJobVO;
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
import com.shixi.entity.Company;
import com.shixi.entity.PageBean;
import com.shixi.entity.vo.CompanyVO;
import com.shixi.service.ICompanyService;
import com.shixi.util.ResponseUtil;
import com.shixi.util.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @Description: 公司
 * @author: hw
 * @date: 2018年3月21日 下午6:12:41
 */
@Controller
@RequestMapping("/companys")
public class CompanyController {

	@Autowired
	private ICompanyService companyService;
	private static final Logger log = Logger.getLogger(CompanyController.class);

	/**
	 * @Description: 查询所有公司招聘信息
	 * @author: hw
	 * @date: 2018年3月26日 下午6:48:58
	 */
	@RequestMapping(value = "/datagrid", method = RequestMethod.GET)
	public String list(@RequestParam(value = "page", required = false) String page,
			@RequestParam(value = "rows", required = false) String rows,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order,
			@RequestParam(value = "startTime", required = false) String startTime,
			@RequestParam(value = "endTime", required = false) String endTime, HttpServletResponse response)
			throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringUtil.isNotEmpty(page) && StringUtil.isNotEmpty(rows)) {
			PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
			map.put("start", pageBean.getStart());
			map.put("size", pageBean.getPageSize());
		}
		if (StringUtil.isNotEmpty(startTime)) {
			map.put("startTime", startTime);
		}
		if (StringUtil.isNotEmpty(endTime)) {
			map.put("endTime", endTime);
		}
		if (StringUtil.isNotEmpty(sort) && StringUtil.isNotEmpty(order)) {
			map.put("order", order);
			map.put("sort", sort);
		}
		List<CompanyVO> companyVOList = companyService.findCompanyRecruit(map);
		Long total = companyService.getTotalCompanyRecruit(map);
		JSONObject result = new JSONObject();
		JSONArray jsonArray = JSONArray.fromObject(companyVOList);
		result.put("rows", jsonArray);
		result.put("total", total);
		log.info("request: companyVO/list , map: " + map.toString());
		ResponseUtil.write(response, result);
		return null;
	}

	/**
	 * @Description: 添加公司信息
	 * @author: hw
	 *
	 * @date: 2018年3月28日 下午1:43:43
	 */
	@RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseBody
	public Result add(Company company) {
		int resultCode = 0;
		resultCode = companyService.insert(company);
		log.info("request: company/update , company: " + company.toString());
		if (resultCode > 0) {
			return ResultGenerator.genSuccessResult();
		}
		return ResultGenerator.genFailResult("添加失败！");
	}

	/**
	 * @Description: 更新公司信息
	 * @author: hw
	 * @date: 2018年3月28日 下午1:43:52
	 */
	@RequestMapping(value = "", method = RequestMethod.PATCH)
	@ResponseBody
	public Result update(Company company) throws Exception {
		int resultTotal = 0;
		resultTotal = companyService.updateByPrimaryKeyWithBLOBs(company);
		log.info("request: company/update , " + company.toString());
		if (resultTotal > 0) {
			return ResultGenerator.genSuccessResult();
		} else {
			return ResultGenerator.genFailResult("修改失败");
		}
	}


	/**
	 * @Description: 审核实习公司的信息
	 * @author: hw
	 * @date: 2018年3月28日 下午1:43:52
	 */
	@RequestMapping(value = "/shenhecominfo", method = RequestMethod.POST)
	@ResponseBody
	public Result shenhecominfo(Company company) throws Exception {
		int resultTotal = 0;
		resultTotal = companyService.updateByPrimaryKeySelective(company);
		log.info("request: company/shenhecominfo , " + company.toString());
		if (resultTotal > 0) {
			return ResultGenerator.genSuccessResult();
		} else {
			return ResultGenerator.genFailResult("修改失败");
		}
	}

	/**
	 * @Description: 查询某个公司信息
	 * @author: hw
	 * @date: 2018年3月26日 下午6:48:23
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Result get(@PathVariable(value = "id") Integer id) throws Exception {
		if (id == null) {
			return ResultGenerator.genFailResult("ERROR");
		}
		Company result = companyService.selectByPrimaryKey(id);
		log.info("request: recruit/get , id " + id);
		if (result != null) {
			Map<String, Company> data = new HashMap<String, Company>();
			data.put("currentCompany", result);
			return ResultGenerator.genSuccessResult(data);
		} else {
			return ResultGenerator.genFailResult("FAIL");
		}
	}

	/**
	 * @Description: 查询某个公司的招聘详情
	 * 失效的岗位信息不会查询出来
	 * @author: hw
	 * @date: 2018年3月26日 下午6:48:03
	 */
	@RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Result getDetail(@PathVariable(value = "id") Integer id) throws Exception {
		if (id == null) {
			return ResultGenerator.genFailResult("ERROR");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		List<CompanyVO> result = companyService.findCompanyRecruitJobs(map);
		log.info("request: companyVO/get , id " + id);
		if (result != null && result.size() > 0) {
			Map<String, CompanyVO> data = new HashMap<String, CompanyVO>();
			data.put("data", result.get(0));
			return ResultGenerator.genSuccessResult(data);
		} else {
			return ResultGenerator.genFailResult("此公司无在招岗位");
		}
	}

	/**
	 * @Description: 查询某个公司某个岗位的招聘详情
	 * @author: hw
	 * @date: 2018年3月26日 下午6:48:03
	 */
	@RequestMapping(value = "/job/{jId}", method = RequestMethod.GET)
	@ResponseBody
	public Result getJobDetail(@PathVariable(value = "jId") Integer jId) throws Exception {
		if (jId == null) {
			return ResultGenerator.genFailResult("ERROR");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("jobId", jId);
		CompanyVO result = companyService.findJobDetail(map);
		log.info("request: companyVO/get , jid " + jId);
		if (result != null) {
			Map<String, CompanyVO> data = new HashMap<String, CompanyVO>();
			data.put("data", result);
			return ResultGenerator.genSuccessResult(data);
		} else {
			return ResultGenerator.genFailResult("FAIL");
		}
	}

	/**
	 * @Description: 查询某个学生所在的公司信息
	 * @author: hw
	 * @date: 2018年3月26日 下午6:48:03
	 */
	@RequestMapping(value = "/user/{userId}", method = RequestMethod.GET)
	@ResponseBody
	public Result getCompanyInfoByUserId(@PathVariable(value = "userId") Integer userId) throws Exception {
		if (userId == null) {
			return ResultGenerator.genFailResult("ERROR");
		}
		CompanyVO result = companyService.findCompanyInfoByUserId(userId);
		log.info("request: companyVO/user/get , userId " + userId);
		if (result != null) {
			Map<String, CompanyVO> data = new HashMap<String, CompanyVO>();
			data.put("data", result);
			return ResultGenerator.genSuccessResult(data);
		} else {
			return ResultGenerator.genFailResult("FAIL");
		}
	}

	/**
	 * @Description: 管理部门查看所有公司申请发布的岗位信息
	 * @author: hw
	 * @date: 2018年4月25日 下午1:46:21
	 */
	@RequestMapping(value = "/datagridwithshenhe", method = RequestMethod.GET)
	public String listwithshenhe(@RequestParam(value = "page", required = false) String page,
								 @RequestParam(value = "rows", required = false) String rows,
								 @RequestParam(value = "sort", required = false) String sort,
								 @RequestParam(value = "order", required = false) String order, CompanyJobVO companyJobVO,
								 HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		if (page != null && rows != null) {
			PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
			map.put("start", pageBean.getStart());
			map.put("size", pageBean.getPageSize());
		}
		if (companyJobVO.getJob() != null&&companyJobVO.getJob().getName()!="") {
			map.put("jobname", companyJobVO.getJob().getName());
		}
		if (companyJobVO.getCompany() != null&&companyJobVO.getCompany().getName()!="") {
			map.put("comname", companyJobVO.getCompany().getName());
		}

		List<CompanyJobVO> list = companyService.getAllJobInfoWithNewAdd(map);
		Long total = companyService.getAllJobInfoWithNewAddCounts(map);
		JSONObject result = new JSONObject();
		JSONArray jsonArray = JSONArray.fromObject(list);
		result.put("rows", jsonArray);
		result.put("total", total);
		log.info("request: userjobs/datagridwithshenhe , map: " + map.toString());
		ResponseUtil.write(response, result);
		return null;
	}


	/**
	 * @Description: 审核新注册的实习单位信息
	 * @author: mxy
	 * @date: 2018年5月18日 下午1:46:21
	 */
	@RequestMapping(value = "/datagridwithnewcom", method = RequestMethod.GET)
	public String listwithnewcom(@RequestParam(value = "page", required = false) String page,
								 @RequestParam(value = "rows", required = false) String rows,
								 @RequestParam(value = "sort", required = false) String sort,
								 @RequestParam(value = "order", required = false) String order, Company company,
								 HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		if (page != null && rows != null) {
			PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
			map.put("start", pageBean.getStart());
			map.put("size", pageBean.getPageSize());
		}
		if (company.getName() != null && company.getName()!="") {
			map.put("name", company.getName());
		}

		if (company.getAddress() != null && company.getAddress() !="") {
			map.put("address", company.getAddress());
		}


		List<Company> list = companyService.getAllComInfoWithNewAdd(map);
		Long total = companyService.getAllComInfoWithNewAddCounts(map);
		JSONObject result = new JSONObject();
		JSONArray jsonArray = JSONArray.fromObject(list);
		result.put("rows", jsonArray);
		result.put("total", total);
		log.info("request: cominfo/datagridwithnewcom , map: " + map.toString());
		ResponseUtil.write(response, result);
		return null;
	}


}
