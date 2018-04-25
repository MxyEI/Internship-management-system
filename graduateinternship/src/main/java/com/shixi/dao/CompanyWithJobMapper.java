package com.shixi.dao;

import com.shixi.entity.vo.CompanyJobVO;
import com.shixi.entity.vo.CompanyVO;
import com.shixi.entity.vo.UserJobVO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Description: 公司岗位关联AstMapper
 * @author: hw
 * @date: 2018年3月28日 下午1:30:01
 */
@Repository
public interface CompanyWithJobMapper {

	/**
	 * @Description: 管理部门查询实习单位发布的新岗位信息并审核
	 * @author: hw
	 * @date: 2018年4月25日 下午12:54:40
	 */
	List<CompanyJobVO> getAllJobInfoWithNewAdd(Map<String, Object> map);

	/**
	 * @Description: 管理部门查询实习单位发布的新岗位信息的数量
	 * @author: hw
	 * @date: 2018年4月25日 下午12:54:40
	 */
	Long getAllJobInfoWithNewAddCounts(Map<String, Object> map);
}
