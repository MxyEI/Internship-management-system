package com.shixi.dao;

import com.shixi.entity.Resume;
import com.shixi.entity.User;
import org.springframework.stereotype.Repository;

/**
 * @Description: 简历Mapper
 * @author: hw
 * @date: 2018年4月1日 下午12:51:22
 */
@Repository
public interface ResumeMapper {

    /**
     * 根据学生id查询简历
     */

    Resume selectResumeByStudentID(Integer studentid);
    int insertSelective(Resume resume);
    int updateByPrimaryKeyWithResume(Resume resume);
    Resume selectByPrimaryKey(Integer id);

}
