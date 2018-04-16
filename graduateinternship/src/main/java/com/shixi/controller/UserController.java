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
import com.shixi.entity.User;
import com.shixi.service.IUserService;
import com.shixi.util.DateUtil;
import com.shixi.util.MD5Util;
import com.shixi.util.ResponseUtil;
import com.shixi.util.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @Description: 用户Controller
 * @author: hw
 * @date: 2018年3月20日 下午1:40:51
 */
@Controller
@RequestMapping("/users")
public class UserController {

	@Autowired
	private IUserService userService;
	private static final Logger log = Logger.getLogger(UserController.class);// 日志文件

	/**
	 * @Description: 登录
	 * @author: hw
	 * @date: 2018年3月20日 下午1:41:05
	 */
	@RequestMapping(value = "/cookie", method = RequestMethod.GET)
	@ResponseBody
	public Result login(User user) {
		try {
			user.setPassword(MD5Util.MD5Encode(user.getPassword(), "UTF-8"));
		} catch (Exception e) {
			user.setPassword("");
		}
		User resultUser = userService.login(user);
		log.info("request: users/login , user: " + user.toString());
		if (resultUser == null) {
			return ResultGenerator.genFailResult("请认真核对账号、密码！");
		} else {
			resultUser.setPassword("PASSWORD");
			Map<String, User> data = new HashMap<String, User>();
			data.put("currentUser", resultUser);
			return ResultGenerator.genSuccessResult(data);
		}
	}

	/**
	 * @Description:添加用户
	 * @author: hw
	 * @date: 2018年3月30日 下午1:22:44
	 */
	@RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseBody
	public Result add(User user) throws Exception {
		String MD5pwd = MD5Util.MD5Encode(user.getPassword(), "UTF-8");
		user.setPassword(MD5pwd);
		user.setGmtModify(DateUtil.getCurrentDateStr());
		int userNum = userService.checkName(user.getUsername());
		if (userNum > 0) {
			return ResultGenerator.genFailResult("该用户名已经存在");
		}
		int resultTotal = userService.insertSelective(user);
		if (resultTotal > 0) {
			return ResultGenerator.genSuccessResult();
		} else {
			return ResultGenerator.genFailResult("FAIL");
		}
	}

	/**
	 * @Description: 更新信息
	 * @author: hw
	 * @date: 2018年3月28日 下午1:45:59
	 */
	@RequestMapping(value = "", method = RequestMethod.PATCH)
	@ResponseBody
	public Result update(User user) throws Exception {
		// 管理员管理用户时，显示的是隐藏的密码，如果管理员没有设置新的密码，就将密码设为Null
		if (user.getPassword() != null && user.getPassword().equals("******")) {
			user.setPassword(null);
		} else {
			user.setPassword(MD5Util.MD5Encode(user.getPassword(), "UTF-8"));
		}
		user.setGmtModify(DateUtil.getCurrentDateStr());
		int resultTotal = userService.updateByPrimaryKeySelective(user);
		log.info("request: user/update , user: " + user.toString());
		if (resultTotal > 0) {
			return ResultGenerator.genSuccessResult();
		} else {
			return ResultGenerator.genFailResult("FAIL");
		}
	}

	/**
	 * @Description: 获取用户信息
	 * @author: hw
	 * @date: 2018年3月28日 下午1:46:06
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Result get(@PathVariable(value = "id") Integer id) throws Exception {
		if (id == null) {
			return ResultGenerator.genFailResult("ERROR");
		}
		User resultUser = userService.selectByPrimaryKey(id);
		log.info("request: user/get , id " + id);
		if (resultUser != null) {
			Map<String, User> data = new HashMap<String, User>();
			data.put("currentUser", resultUser);
			return ResultGenerator.genSuccessResult(data);
		} else {
			return ResultGenerator.genFailResult("FAIL");
		}
	}

	@RequestMapping(value = "/datagrid", method = RequestMethod.GET)
	public String list(@RequestParam(value = "page", required = false) String page,
			@RequestParam(value = "rows", required = false) String rows, User s_user, HttpServletResponse response)
			throws Exception {
		PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("username", StringUtil.formatLike(s_user.getUsername()));
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		List<User> userList = userService.findUsers(map);
		Long total = userService.getTotalUser(map);
		JSONObject result = new JSONObject();
		JSONArray jsonArray = JSONArray.fromObject(userList);
		result.put("rows", jsonArray);
		result.put("total", total);
		log.info("request: user/list , map: " + map.toString());
		ResponseUtil.write(response, result);
		return null;
	}

	/**
	 * @Description: 批量删除user
	 * @param ids
	 *            user ids id1,id2,id3
	 * @author: hw
	 * @date: 2018年3月30日 下午1:35:35
	 */
	@RequestMapping(value = "/{ids}", method = RequestMethod.DELETE)
	@ResponseBody
	public Result delete(@PathVariable(value = "ids") String ids) throws Exception {
		if (ids.trim().length() == 0) {
			return ResultGenerator.genFailResult("ERROR");
		}
		userService.deleteByIds(StringUtil.formatIdsToList(ids));
		log.info("request: article/delete , ids: " + ids);
		return ResultGenerator.genSuccessResult();
	}

	/**
	 * @Description: 校验密码
	 * @author: hw
	 * @date: 2018年3月31日 下午7:08:28
	 */
	@RequestMapping(value = "/verifyPwd", method = RequestMethod.GET)
	@ResponseBody
	public Result checkPwd(User user) {
		try {
			user.setPassword(MD5Util.MD5Encode(user.getPassword(), "UTF-8"));
		} catch (Exception e) {
			user.setPassword("");
		}
		User resultUser = userService.checkPwd(user);
		log.info("request: users/verifyPwd , user: " + user.toString());
		if (resultUser == null) {
			return ResultGenerator.genFailResult("密码错误");
		} else {
			resultUser.setPassword("PASSWORD");
			Map<String, User> data = new HashMap<String, User>();
			data.put("currentUser", resultUser);
			return ResultGenerator.genSuccessResult(data);
		}
	}

}