package com.shixi.service;

import com.shixi.entity.AppraisalForm;

import java.util.List;

public interface IAppraisalFormService {
    int deleteByPrimaryKey(Integer pk);

    int insert(AppraisalForm appraisalForm);

    int insertSelective(AppraisalForm appraisalForm);

    AppraisalForm select();

    int updateByPrimaryKeySelective(AppraisalForm appraisalForm);

    int updateByPrimaryKey(AppraisalForm appraisalForm);
}
