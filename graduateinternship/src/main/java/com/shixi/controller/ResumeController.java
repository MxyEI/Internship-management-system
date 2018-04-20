package com.shixi.controller;

import com.common.Result;
import com.common.ResultGenerator;
import com.shixi.entity.Resume;
import com.shixi.service.IResumeService;
import com.shixi.util.DateUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 个人简历
 * @author: hw
 * @date: 2018年4月19日 上午9:48:17
 */
@Controller
@RequestMapping("/personresume")
public class ResumeController {

    @Autowired
    private IResumeService iResumeService;
    private static final Logger log = Logger.getLogger(ResumeController.class);


    /**
     * @Description: 添加个人简历
     * @author: hw
     * @date: 2018年3月28日 下午1:45:17
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public Result add(Resume resume) throws Exception {
        int resultCode = 0;
        resume.setGmtCreate(DateUtil.getCurrentDateStr());
        resultCode = iResumeService.insertResume(resume);
        log.info("request: resume/add , resume: " + resume.toString());
        if (resultCode > 0) {
            return ResultGenerator.genSuccessResult();
        }
        return ResultGenerator.genFailResult("添加失败！");
    }

    /**
     * @Description: 更新个人简历
     * @author: hw
     * @date: 2018年3月28日 下午1:45:29
     */
    @RequestMapping(value = "", method = RequestMethod.PATCH)
    @ResponseBody
    public Result update(Resume resume) throws Exception {
        int resultTotal = 0;
        resultTotal = iResumeService.updateByPrimaryKeyWithResume(resume);
        log.info("request: resume/update , " + resume.toString());
        if (resultTotal > 0) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("修改失败");
        }
    }


    /**
     * @Description: 获取简历信息
     * @author: hw
     * @date: 2018年3月28日 下午1:45:39
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Result get(@PathVariable(value = "id") Integer id) throws Exception {
        if (id == null) {
            return ResultGenerator.genFailResult("ERROR");
        }
        Resume result = iResumeService.selectResumeByStudentID(id);
        log.info("request: resume/get , id " + id);
        if (result != null) {
            Map<String, Resume> data = new HashMap<String, Resume>();
            data.put("currentResume", result);
            return ResultGenerator.genSuccessResult(data);
        } else {
            return ResultGenerator.genFailResult("FAIL");
        }
    }


}
