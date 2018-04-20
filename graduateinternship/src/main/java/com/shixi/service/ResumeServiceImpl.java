package com.shixi.service;

import com.shixi.dao.ResumeMapper;
import com.shixi.entity.Resume;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResumeServiceImpl implements IResumeService{
    @Autowired
    private ResumeMapper resumeMapper;

    @Override
    public Resume selectResumeByStudentID(Integer studentid) {
        return resumeMapper.selectResumeByStudentID(studentid);
    }

    @Override
    public int insertResume(Resume resume) {
        return resumeMapper.insertSelective(resume);
    }

    @Override
    public int updateByPrimaryKeyWithResume(Resume resume) {
        return resumeMapper.updateByPrimaryKeyWithResume(resume);
    }

    @Override
    public Resume selectByPrimaryKey(Integer id) {
        return resumeMapper.selectByPrimaryKey(id);
    }

}
