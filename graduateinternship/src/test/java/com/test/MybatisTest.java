package com.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.shixi.dao.UserWithJobAstMapper;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.shixi.dao.CompanyAstMapper;
import com.shixi.dao.ScoreAstMapper;
import com.shixi.entity.User;
import com.shixi.entity.vo.ScoreVO;
import com.shixi.entity.vo.UserJobVO;
import com.shixi.service.IUserService;
import com.shixi.util.DateUtil;
import com.shixi.util.MD5Util;

@RunWith(SpringJUnit4ClassRunner.class) // 使用junit4进行测试
@ContextConfiguration(locations = { "classpath*:/*.xml" })
public class MybatisTest {
	private static Logger logger = Logger.getLogger(MybatisTest.class);

	@Autowired
	IUserService userService;

	@Autowired
	CompanyAstMapper companyAstMapper;

	@Autowired
	UserWithJobAstMapper userJobAstMapper;

	@Autowired
	ScoreAstMapper scoreAstMapper;

	// 根据Id查询用户信息，得到一条记录结果
	/*
	 * @Test public void addUser() { User user = new User();
	 * user.setUsername("company2"); user.setPassword(MD5Util.MD5Encode("123",
	 * "UTF-8")); user.setUsertype("company"); try {
	 * user.setGmtCreate(DateUtil.getCurrentDateStr()); } catch (Exception e) {
	 * e.printStackTrace(); } userService.insertSelective(user); }
	 */

	/**
	 * @Description: 更新用户信息
	 * @author: hw
	 * @throws Exception
	 * @date: 2018年3月28日 下午2:36:20
	 */
	@Test
	public void updateUser() throws Exception {
		User t = new User();
		t.setUsername("teacher");
		t.setUsertype("teacher");
		t.setPassword(MD5Util.MD5Encode("1", "UTF-8"));
		t.setGmtCreate(DateUtil.getCurrentDateStr());
		userService.insert(t);
		/*
		 * t.setUsername("student"); User user = userService.login(t);
		 * user.setGmtModify(DateUtil.getCurrentDateStr());
		 * user.setPassword(MD5Util.MD5Encode("1", "UTF-8"));
		 * userService.updateByPrimaryKeySelective(user);
		 */

	}

	/*
	 * @Test public void getJobCompanyInfo() { List<Job>
	 * jobs=jobCustomerMapper.findJobCompanyResultMap(); logger.info(jobs.size()); }
	 */

	/*
	 * @Test public void getCompanyJobsInfo() { 奇怪，mybatis不支持重载吗 CompanyVO
	 * companyVO=new CompanyVO(); companyVO.setId(Integer.valueOf(1)); Recruit
	 * recruit=new Recruit(); recruit.setStartTime("2018-02-30");
	 * companyVO.setRecruit(recruit); List<CompanyVO>
	 * companys=companyCustomerMapper.findDetailByCompany(companyVO);
	 * logger.info(companys); }
	 */

	@Test
	public void getCompanyAscUsers() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("companyId", Integer.valueOf("5"));
		map.put("userId", Integer.valueOf("3"));
		List<UserJobVO> lists = userJobAstMapper.findAscUserJobs(map);
		logger.info(lists.size());
	}

	@Test
	public void getSocreInfo() {
		List<ScoreVO> list = scoreAstMapper.findScoreOfUsers(null);
		logger.info(list.size());
	}
}
