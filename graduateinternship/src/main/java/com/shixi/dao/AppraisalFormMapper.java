package com.shixi.dao;

import com.shixi.entity.AppraisalForm;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppraisalFormMapper {
    int deleteByPrimaryKey(Integer id);

    int deleteByIds(List<Integer> ids);

    int insert(AppraisalForm appraisalForm);

    int insertSelective(AppraisalForm appraisalForm);

    AppraisalForm select();

    int updateByPrimaryKeySelective(AppraisalForm appraisalForm);

    int updateByPrimaryKey(AppraisalForm appraisalForm);
}
