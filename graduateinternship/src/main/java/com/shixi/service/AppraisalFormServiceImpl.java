package com.shixi.service;

import com.shixi.dao.AppraisalFormMapper;
import com.shixi.entity.AppraisalForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AppraisalFormServiceImpl implements IAppraisalFormService{

    @Autowired
    private AppraisalFormMapper appraisalFormMapper;

    @Override
    public int deleteByPrimaryKey(Integer pk) {
        return appraisalFormMapper.deleteByPrimaryKey(pk);
    }


    @Override
    public int insert(AppraisalForm appraisalForm) {
        return appraisalFormMapper.insert(appraisalForm);
    }

    @Override
    public int insertSelective(AppraisalForm appraisalForm) {
        return appraisalFormMapper.insertSelective(appraisalForm);
    }

    @Override
    public AppraisalForm select() {
        return appraisalFormMapper.select();
    }

    @Override
    public int updateByPrimaryKeySelective(AppraisalForm appraisalForm) {
        return appraisalFormMapper.updateByPrimaryKeySelective(appraisalForm);
    }

    @Override
    public int updateByPrimaryKey(AppraisalForm appraisalForm) {
        return appraisalFormMapper.updateByPrimaryKey(appraisalForm);
    }
}
