package com.shixi.service;

import java.util.List;
import java.util.Map;

import com.shixi.dao.CompanyWithJobMapper;
import com.shixi.entity.vo.CompanyJobVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shixi.dao.CompanyAstMapper;
import com.shixi.dao.CompanyMapper;
import com.shixi.entity.Company;
import com.shixi.entity.vo.CompanyVO;

/**
 * @Description:公司ServiceImpl
 * @author: hw
 * @date: 2018年3月21日 下午5:32:14
 */
@Service
public class CompanyServiceImpl implements ICompanyService {

	@Autowired
	private CompanyMapper companyMapper;

	@Autowired
	private CompanyAstMapper companyAstMapper;

	@Autowired
	private CompanyWithJobMapper companyWithJobMapper;

	@Override
	public int insert(Company record) {
		return companyMapper.insert(record);
	}

	@Override
	public int insertSelective(Company record) {
		return companyMapper.insertSelective(record);
	}

	@Override
	public Company selectByPrimaryKey(Integer id) {
		return companyMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Company> getAllComInfoWithNewAdd(Map<String, Object> map) {
		return companyMapper.getAllComInfoWithNewAdd(map);
	}

	@Override
	public Long getAllComInfoWithNewAddCounts(Map<String, Object> map) {
		return companyMapper.getAllComInfoWithNewAddCounts(map);
	}

	@Override
	public int updateByPrimaryKeySelective(Company record) {
		return companyMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKeyWithBLOBs(Company record) {
		return companyMapper.updateByPrimaryKeyWithBLOBs(record);
	}

	@Override
	public int updateByPrimaryKey(Company record) {
		return companyMapper.updateByPrimaryKey(record);
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return companyMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<CompanyVO> findCompanyRecruit(Map<String, Object> map) {
		return companyAstMapper.findCompanyRecruit(map);
	}

	@Override
	public Long getTotalCompanyRecruit(Map<String, Object> map) {
		return companyAstMapper.getTotalCompanyRecruit(map);
	}

	@Override
	public CompanyVO findJobDetail(Map<String, Object> map) {
		return companyAstMapper.findJobDetail(map);
	}

	@Override
	public List<CompanyVO> findCompanyRecruitJobs(Map<String, Object> map) {
		return companyAstMapper.findCompanyRecruitJobs(map);
	}

	@Override
	public CompanyVO findCompanyInfoByUserId(Integer userId) {
		return companyAstMapper.findCompanyInfoByUserId(userId);
	}

	@Override
	public List<CompanyJobVO> getAllJobInfoWithNewAdd(Map<String, Object> map) {
		return companyWithJobMapper.getAllJobInfoWithNewAdd(map);
	}

	@Override
	public Long getAllJobInfoWithNewAddCounts(Map<String, Object> map) {
		return companyWithJobMapper.getAllJobInfoWithNewAddCounts(map);
	}
}
