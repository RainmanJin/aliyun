package com.stylefeng.guns.modular.bigdata.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.modular.bigdata.service.ILogDetailsService;
import com.stylefeng.guns.modular.system.model.LogDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * 打点管理控制器
 *
 * @author fengshuonan
 * @Date 2018-07-09 15:16:31
 */
@Controller
@RequestMapping("/logDetails")
public class LogDetailsController extends BaseController {

    private String PREFIX = "/bigdata/logDetails/";

    @Autowired
    private ILogDetailsService logDetailsService;

    /**
     * 跳转到打点管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "logDetails.html";
    }

    /**
     * 跳转到添加打点管理
     */
    @RequestMapping("/logDetails_add")
    public String logDetailsAdd() {
        return PREFIX + "logDetails_add.html";
    }

    /**
     * 跳转到修改打点管理
     */
    @RequestMapping("/logDetails_update/{logDetailsId}")
    public String logDetailsUpdate(@PathVariable Integer logDetailsId, Model model) {
        LogDetails logDetails = logDetailsService.selectById(logDetailsId);
        model.addAttribute("item", logDetails);
        LogObjectHolder.me().set(logDetails);
        return PREFIX + "logDetails_edit.html";
    }
    /**
     * 跳转到预览打点明细
     */
    @RequestMapping("/logDetails_previewItem/{logDetailsId}")
    public String logDetailsreviewItem(@PathVariable Integer logDetailsId, Model model) {
        LogDetails logDetails = logDetailsService.selectLogDetailsById(logDetailsId.toString());
        String logItem=logDetailsService.previewParam(logDetailsId.toString());
        model.addAttribute("item", logDetails);
        model.addAttribute("logItem", logItem);
        LogObjectHolder.me().set(logDetails);
        return PREFIX + "logDetails_previewItem.html";
    }

    /**
     * 获取打点管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String pageNum, String eventNum, String type, String versionId, String fitPlatformId, String startTime, String endTime, String isDelete) {
        return logDetailsService.initList(pageNum, eventNum, type, versionId, fitPlatformId, startTime, endTime, isDelete);
    }

    /**
     * 新增打点管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(LogDetails logDetails) {
        logDetails.setCreateTime(new Date());
        logDetails.setUpdateTime(new Date());
        logDetailsService.insert(logDetails);
        return SUCCESS_TIP;
    }

    /**
     * 删除打点管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer logDetailsId) {
//        logDetailsService.deleteById(logDetailsId);
        LogDetails logDetails = new LogDetails();
        logDetails.setIsDelete("1");
        logDetails.setId(logDetailsId);
        logDetailsService.updateById(logDetails);
        return SUCCESS_TIP;
    }

    /**
     * 修改打点管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(LogDetails logDetails) {
        logDetails.setUpdateTime(new Date());
        //更新
        logDetailsService.updateById(logDetails);
        return SUCCESS_TIP;
    }

    /**
     * 打点管理详情
     */
    @RequestMapping(value = "/detail/{logDetailsId}")
    @ResponseBody
    public Object detail(@PathVariable("logDetailsId") Integer logDetailsId) {
        return logDetailsService.selectById(logDetailsId);
    }

    /**
     * 打点日志页面加载select
     */
    @RequestMapping(value = "/selectInit")
    @ResponseBody
    public Object selectInit() {
        return logDetailsService.selectInit();
    }


    /**
     * 打点日志添加页面加载select
     */
    @RequestMapping(value = "/paramSelectInit")
    @ResponseBody
    public Object paramSelectInit() {
        return logDetailsService.paramSelectInit();
    }

    /**
     * 预览页面
     */
    @RequestMapping(value = "/previewParam")
    @ResponseBody
    public Object previewParam(List<String> logDetailsIdList) {
        return logDetailsService.paramSelectInit();
    }
}
