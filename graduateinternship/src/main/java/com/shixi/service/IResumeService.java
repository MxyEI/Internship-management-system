package com.shixi.service;

import com.shixi.entity.Resume;

/**
 * @Description: 评价IService
 * @author: hw
 * @date: 2018年4月19日 下午2:10:50
 */
public interface IResumeService {
    Resume selectResumeByStudentID(Integer studentid);

    int insertResume(Resume resume);
    int updateByPrimaryKeyWithResume(Resume resume);
    Resume selectByPrimaryKey(Integer id);

}
