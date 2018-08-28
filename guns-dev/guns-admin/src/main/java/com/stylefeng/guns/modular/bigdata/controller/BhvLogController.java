package com.stylefeng.guns.modular.bigdata.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.stylefeng.guns.modular.system.model.BhvLog;
import com.stylefeng.guns.modular.bigdata.service.IBhvLogService;

/**
 * 打点日志控制器
 *
 * @author fengshuonan
 * @Date 2018-04-18 18:15:45
 */
@Controller
@RequestMapping("/bhvLog")
public class BhvLogController extends BaseController {

    private String PREFIX = "/bigdata/bhvLog/";

    @Autowired
    private IBhvLogService bhvLogService;

    /**
     * 跳转到打点日志首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "bhvLog.html";
    }

    /**
     * 跳转到添加打点日志
     */
    @RequestMapping("/bhvLog_add")
    public String bhvLogAdd() {
        return PREFIX + "bhvLog_add.html";
    }

    /**
     * 跳转到修改打点日志
     */
    @RequestMapping("/bhvLog_update/{bhvLogId}")
    public String bhvLogUpdate(@PathVariable Integer bhvLogId, Model model) {
        BhvLog bhvLog = bhvLogService.selectById(bhvLogId);
        model.addAttribute("item",bhvLog);
        LogObjectHolder.me().set(bhvLog);
        return PREFIX + "bhvLog_edit.html";
    }

    /**
     * 获取打点日志列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return bhvLogService.selectList(null);
    }

    /**
     * 新增打点日志
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(BhvLog bhvLog) {
        bhvLogService.insert(bhvLog);
        return SUCCESS_TIP;
    }

    /**
     * 删除打点日志
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer bhvLogId) {
        bhvLogService.deleteById(bhvLogId);
        return SUCCESS_TIP;
    }

    /**
     * 修改打点日志
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(BhvLog bhvLog) {
        bhvLogService.updateById(bhvLog);
        return SUCCESS_TIP;
    }

    /**
     * 打点日志详情
     */
    @RequestMapping(value = "/detail/{bhvLogId}")
    @ResponseBody
    public Object detail(@PathVariable("bhvLogId") Integer bhvLogId) {
        return bhvLogService.selectById(bhvLogId);
    }
}
