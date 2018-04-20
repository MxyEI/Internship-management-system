package com.shixi.controller;

import com.common.Result;
import com.common.ResultGenerator;
import com.shixi.entity.AppraisalForm;
import com.shixi.service.IAppraisalFormService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 实习鉴定标准
 * @author: hw
 * @date: 2018年3月21日 下午6:12:41
 */
@Controller
@RequestMapping("/appraisalform")
public class AppraisalFormController {

    @Autowired
    private IAppraisalFormService iAppraisalFormService;
    private static final Logger log = Logger.getLogger(AppraisalFormController.class);

    /**
     * @Description: 添加实习鉴定表
     * @author: hw
     *
     * @date: 2018年3月28日 下午1:43:43
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public Result add(AppraisalForm appraisalForm) {
        int resultCode = 0;
        resultCode = iAppraisalFormService.insert(appraisalForm);
        //log.info("request: appraisalForm/add , appraisalForm: " + appraisalForm.toString());
        if (resultCode > 0) {
            return ResultGenerator.genSuccessResult();
        }
        return ResultGenerator.genFailResult("添加失败！");
    }

    /**
     * @Description: 更新实习鉴定表
     * @author: hw
     * @date: 2018年3月28日 下午1:43:52
     */
    @RequestMapping(value = "", method = RequestMethod.PATCH)
    @ResponseBody
    public Result update(AppraisalForm appraisalForm) throws Exception {
        int resultTotal = 0;
        resultTotal = iAppraisalFormService.updateByPrimaryKeySelective(appraisalForm);
        //log.info("request: appraisalForm/update , " + appraisalForm.toString());
        if (resultTotal > 0) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("修改失败");
        }
    }


    /**
     * @Description: 获取实习鉴定标准
     * @author: hw
     * @date: 2018年3月28日 下午1:46:06
     */
    @RequestMapping(value = "/content", method = RequestMethod.GET)
    @ResponseBody
    public Result get() throws Exception {

        AppraisalForm result = iAppraisalFormService.select();
        //log.info("request: AppraisalForm/get , content ");
        if (result != null) {
            Map<String, AppraisalForm> data = new HashMap<String, AppraisalForm>();
            data.put("currentAppraisalForm", result);
            return ResultGenerator.genSuccessResult(data);
        } else {
            return ResultGenerator.genFailResult("FAIL");
        }
    }

}
