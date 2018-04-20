package com.shixi.service;

import com.shixi.dao.AppraisalFormMapper;
import com.shixi.dao.AssessMapper;
import com.shixi.entity.AppraisalForm;
import com.shixi.entity.Assess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AssessServiceImpl implements IAssessService{

    @Autowired
    private AssessMapper assessMapper;

    @Override
    public int deleteByPrimaryKey(Integer pk) {
        return assessMapper.deleteByPrimaryKey(pk);
    }


    @Override
    public int insert(Assess assess) {
        return assessMapper.insert(assess);
    }

    @Override
    public int insertSelective(Assess assess) {
        return assessMapper.insertSelective(assess);
    }

    @Override
    public Assess selectByPrimaryKey(Integer pk) {
        return assessMapper.selectByPrimaryKey(pk);
    }

    @Override
    public int updateByPrimaryKeySelective(Assess assess) {
        return assessMapper.updateByPrimaryKeySelective(assess);
    }

    @Override
    public int updateByPrimaryKey(Assess assess) {
        return assessMapper.updateByPrimaryKey(assess);
    }

    @Override
    public Assess selectByUserid(Integer pk) {
        return assessMapper.selectByUserid(pk);
    }
}
