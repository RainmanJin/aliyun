package com.stylefeng.guns.modular.bigdata.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.modular.bigdata.service.ILogCheckDeviceService;
import com.stylefeng.guns.modular.bigdata.service.IPageLogCheckService;
import com.stylefeng.guns.modular.system.model.PageLog;
import com.stylefeng.guns.util.IdTypeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * 打点事件code控制器
 *
 * @author fengshuonan
 * @Date 2018-04-18 18:15:11
 */
@Controller
@RequestMapping("/pageLogCheck")
public class PageLogCheckController extends BaseController {

    private String PREFIX = "/bigdata/pageLogCheck/";

    @Autowired
    private IPageLogCheckService pageLogCheckService;
    @Autowired
    private ILogCheckDeviceService logCheckDeviceService;

    /**
     * 跳转到打点事件code首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "pageLogCheck.html";
    }

    /**
     * 跳转到添加打点事件code
     */
    @RequestMapping("/pageLogCheck_add")
    public String bhvEventAdd() {
        return PREFIX + "pageLogCheck_add.html";
    }

    /**
     * 跳转到修改打点事件code
     */
    @RequestMapping("/pageLogCheck_update/{pageLogCheckId}")
    public String bhvEventUpdate(@PathVariable Integer pageLogCheckId, Model model) {
        PageLog bhvEvent = pageLogCheckService.selectById(pageLogCheckId);
        model.addAttribute("item", bhvEvent);
        LogObjectHolder.me().set(bhvEvent);
        return PREFIX + "pageLogCheck_edit.html";
    }

    /**
     * 获取打点事件code列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String project, String param, String page,
                       String event, String userId, String platform, String appuserId, String eventtime_start, String eventtime_end, String createtime_start, String createtime_end) {
        List<PageLog> data = new ArrayList<PageLog>();
        String user_id = "";
        if (appuserId != null && !"".equals(appuserId)) {
            user_id = IdTypeHandler.decode(appuserId) + "";
        }
        if ("test".equals(platform)) {
            data = pageLogCheckService.getList("wx_pagelog", null, page,
                    project, param, event, userId, user_id, eventtime_start, eventtime_end, createtime_start, createtime_end);
        } else {
            data = pageLogCheckService.getList("wx_pagelog", null, page,
                    project, param, event, userId, platform, user_id, eventtime_start, eventtime_end, createtime_start, createtime_end);
        }
        return data;
    }

    /**
     * 新增打点事件code
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(PageLog bhvEvent) {
        pageLogCheckService.insert(bhvEvent);
        return SUCCESS_TIP;
    }

    /**
     * 删除打点事件code
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer bhvEventId) {
        pageLogCheckService.deleteById(bhvEventId);
        return SUCCESS_TIP;
    }

    /**
     * 修改打点事件code
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(PageLog pageLog) {
        pageLogCheckService.updateById(pageLog);
        return SUCCESS_TIP;
    }

    /**
     * 打点事件code详情
     */
    @RequestMapping(value = "/detail/pageLogCheckId}")
    @ResponseBody
    public Object detail(@PathVariable("pageLogCheckId") Integer pageLogCheckId) {
        return pageLogCheckService.selectById(pageLogCheckId);
    }
}
