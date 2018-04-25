package com.shixi.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.shixi.entity.UserWithJob;
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
import com.shixi.entity.PageBean;
import com.shixi.entity.UserJob;
import com.shixi.entity.vo.UserJobVO;
import com.shixi.service.IUserJobService;
import com.shixi.util.DateUtil;
import com.shixi.util.ResponseUtil;
import com.shixi.util.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @Description: 用户岗位关联Controller
 * @author: hw
 * @date: 2018年3月27日 下午5:04:06
 */
@Controller
@RequestMapping("/userJobs")
public class UserJobController {

	@Autowired
	IUserJobService userJobService;
	private static final Logger log = Logger.getLogger(UserJobController.class);

	/**
	 * @Description: 列出某个公司的申请用户和申请岗位信息
	 * @author: hw
	 * @date: 2018年3月28日 下午1:46:21
	 */
	@RequestMapping(value = "/datagrid", method = RequestMethod.GET)
	public String list(@RequestParam(value = "page", required = false) String page,
			@RequestParam(value = "rows", required = false) String rows,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "order", required = false) String order, UserJobVO userJob,
			HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		if (page != null && rows != null) {
			PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
			map.put("start", pageBean.getStart());
			map.put("size", pageBean.getPageSize());
		}
		if (userJob.getCompanyId() != null) {
			map.put("companyId", userJob.getCompanyId());
		}
		if (sort != null && order != null) {
			map.put("order", order);
			// 表格的字段名和数据库的字段名不一样，所以需要将sort转换成数据库中对应的字段名
			if (sort.indexOf(".") < 0) {
				sort = "v." + sort;
			} else {
				String pre = sort.substring(0, sort.indexOf("."));
				String tableName = (pre.equals("user") ? "u" : pre.equals("job") ? "j" : "v");
				sort = tableName + sort.substring(sort.indexOf("."));
			}
			map.put("sort", sort);
		}
		if (userJob.getUser() != null && userJob.getUser().getRealname() != null) {
			map.put("realname", StringUtil.formatLike(userJob.getUser().getRealname()));
		}
		if (userJob.getJob() != null && userJob.getJob().getName() != null) {
			map.put("jobname", StringUtil.formatLike(userJob.getJob().getName()));
		}
		//log.info(userJob);
		List<UserJobVO> list = userJobService.findAscUserJobs(map);
		Long total = userJobService.getTotlaAscUserJobs(map);
		JSONObject result = new JSONObject();
		JSONArray jsonArray = JSONArray.fromObject(list);
		result.put("rows", jsonArray);
		result.put("total", total);
		log.info("request: userjobs/list , map: " + map.toString());
		ResponseUtil.write(response, result);
		return null;
	}


	/**
	 * @Description: 列出某个公司的申请成功的用户和申请岗位信息
	 * @author: hw
	 * @date: 2018年3月28日 下午1:46:21
	 */
	@RequestMapping(value = "/datagridwithsuccess", method = RequestMethod.GET)
	public String listwithsuccess(@RequestParam(value = "page", required = false) String page,
					   @RequestParam(value = "rows", required = false) String rows,
					   @RequestParam(value = "sort", required = false) String sort,
					   @RequestParam(value = "order", required = false) String order, UserJobVO userJob,
					   HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		if (page != null && rows != null) {
			PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
			map.put("start", pageBean.getStart());
			map.put("size", pageBean.getPageSize());
		}
		if (userJob.getCompanyId() != null) {
			map.put("companyId", userJob.getCompanyId());
		}
		if (sort != null && order != null) {
			map.put("order", order);
			// 表格的字段名和数据库的字段名不一样，所以需要将sort转换成数据库中对应的字段名
			if (sort.indexOf(".") < 0) {
				sort = "v." + sort;
			} else {
				String pre = sort.substring(0, sort.indexOf("."));
				String tableName = (pre.equals("user") ? "u" : pre.equals("job") ? "j" : "v");
				sort = tableName + sort.substring(sort.indexOf("."));
			}
			map.put("sort", sort);
		}
		if (userJob.getUser() != null && userJob.getUser().getRealname() != null) {
			map.put("realname", StringUtil.formatLike(userJob.getUser().getRealname()));
		}
		if (userJob.getJob() != null && userJob.getJob().getName() != null) {
			map.put("jobname", StringUtil.formatLike(userJob.getJob().getName()));
		}
		//log.info(userJob);
		List<UserJobVO> list = userJobService.findAscUserJobsByUserid(map);
		Long total = userJobService.getTotlaAscUserJobsWithSuccess(map);
		JSONObject result = new JSONObject();
		JSONArray jsonArray = JSONArray.fromObject(list);
		result.put("rows", jsonArray);
		result.put("total", total);
		log.info("request: userjobs/list , map: " + map.toString());
		ResponseUtil.write(response, result);
		return null;
	}




	/**
	 * @Description: 列出某个用户所有的申请信息
	 * @author: hw
	 * @date: 2018年4月28日 下午1:46:21
	 */
	@RequestMapping(value = "/datagridwithmyapply/{userid}", method = RequestMethod.GET)
	public String listwithusersapply(@PathVariable(value = "userid") Integer userid,@RequestParam(value = "page", required = false) String page,
								  @RequestParam(value = "rows", required = false) String rows,
								  @RequestParam(value = "sort", required = false) String sort,
								  @RequestParam(value = "order", required = false) String order, UserJobVO userJob,
								  HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		if (page != null && rows != null) {
			PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
			map.put("start", pageBean.getStart());
			map.put("size", pageBean.getPageSize());
		}
//		if (userJob.getUserId() != null) {
//			map.put("userid", userid);
//		}
		map.put("userid", userid);
		if (sort != null && order != null) {
			map.put("order", order);
			// 表格的字段名和数据库的字段名不一样，所以需要将sort转换成数据库中对应的字段名
			if (sort.indexOf(".") < 0) {
				sort = "v." + sort;
			} else {
				String pre = sort.substring(0, sort.indexOf("."));
				String tableName = (pre.equals("user") ? "u" : pre.equals("job") ? "j" : "v");
				sort = tableName + sort.substring(sort.indexOf("."));
			}
			map.put("sort", sort);
		}
		if (userJob.getUser() != null && userJob.getUser().getRealname() != null) {
			map.put("realname", StringUtil.formatLike(userJob.getUser().getRealname()));
		}
		if (userJob.getJob() != null && userJob.getJob().getName() != null) {
			map.put("jobname", StringUtil.formatLike(userJob.getJob().getName()));
		}
		//log.info(userJob);
		List<UserJobVO> list = userJobService.findAscUserJobsWithUserid(map);
		Long total = userJobService.getTotlaAscUserJobsWithuser(map);
		JSONObject result = new JSONObject();
		JSONArray jsonArray = JSONArray.fromObject(list);
		result.put("rows", jsonArray);
		result.put("total", total);
		log.info("request: userjobs/list , map: " + map.toString());
		ResponseUtil.write(response, result);
		return null;
	}




	/**
	 * @Description: 申请岗位
	 * @author: hw
	 * @date: 2018年3月27日 下午5:46:42
	 */
	@RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseBody
	public Result add(UserWithJob userJob) throws Exception {
		int resultTotal = 0;
		userJob.setGmtCreate(DateUtil.getCurrentDateStr());
		resultTotal = userJobService.insert(userJob);
		log.info("request: job/save , " + userJob.toString());
		if (resultTotal > 0) {
			return ResultGenerator.genSuccessResult();
		} else {
			return ResultGenerator.genFailResult("添加失败");
		}
	}

	/**
	 * @Description: 获取用户申请的岗位
	 * @param 用户工作关联表id:vId
	 * @author: hw
	 * @date: 2018年3月28日 下午1:53:07
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Result get(@PathVariable(value = "id") Integer id) throws Exception {
		if (id == null) {
			return ResultGenerator.genFailResult("ERROR");
		}
		UserWithJob result = userJobService.selectByPrimaryKey(id);
		log.info("request: userjob/get , id " + id);
		if (result != null) {
			Map<String, UserWithJob> data = new HashMap<String, UserWithJob>();
			data.put("data", result);
			return ResultGenerator.genSuccessResult(data);
		} else {
			return ResultGenerator.genFailResult("FAIL");
		}
	}

	/**
	 * @Description: 取消申请
	 * @author: hw
	 * @date: 2018年3月27日 下午6:17:22
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Result delete(@PathVariable(value = "id") Integer id) throws Exception {
		if (id == null) {
			return ResultGenerator.genFailResult("ERROR");
		}
		int resultCode = userJobService.deleteByPrimaryKey(id);
		log.info("request: userjob/delete , id: " + id);
		if (resultCode > 0) {
			return ResultGenerator.genSuccessResult();
		}
		return ResultGenerator.genFailResult("ERROR");
	}

	/**
	 * @Description: 更新用户岗位信息
	 * @author: hw
	 * @date: 2018年3月28日 下午3:16:57
	 */
	@RequestMapping(value = "", method = RequestMethod.PATCH)
	@ResponseBody
	public Result update(UserWithJob userJob) throws Exception {
		userJob.setGmtModify(DateUtil.getCurrentDateStr());
		int resultTotal = userJobService.updateByPrimaryKeySelective(userJob);
		log.info("request: userJob/update , userJob: " + userJob.toString());
		if (resultTotal > 0) {
			return ResultGenerator.genSuccessResult();
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
					   @RequestParam(value = "order", required = false) String order, UserJobVO userJob,
					   HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		if (page != null && rows != null) {
			PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
			map.put("start", pageBean.getStart());
			map.put("size", pageBean.getPageSize());
		}

		List<CompanyJobVO> list = userJobService.getAllJobInfoWithNewAdd(map);
		Long total = userJobService.getAllJobInfoWithNewAddCounts();
		JSONObject result = new JSONObject();
		JSONArray jsonArray = JSONArray.fromObject(list);
		result.put("rows", jsonArray);
		result.put("total", total);
		log.info("request: userjobs/datagridwithshenhe , map: " + map.toString());
		ResponseUtil.write(response, result);
		return null;
	}


}
