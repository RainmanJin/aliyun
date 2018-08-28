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
import com.stylefeng.guns.modular.system.model.BhvPageEvent;
import com.stylefeng.guns.modular.bigdata.service.IBhvPageEventService;

/**
 * 打点页面与事件关联控制器
 *
 * @author fengshuonan
 * @Date 2018-04-18 18:16:51
 */
@Controller
@RequestMapping("/bhvPageEvent")
public class BhvPageEventController extends BaseController {

    private String PREFIX = "/bigdata/bhvPageEvent/";

    @Autowired
    private IBhvPageEventService bhvPageEventService;

    /**
     * 跳转到打点页面与事件关联首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "bhvPageEvent.html";
    }

    /**
     * 跳转到添加打点页面与事件关联
     */
    @RequestMapping("/bhvPageEvent_add")
    public String bhvPageEventAdd() {
        return PREFIX + "bhvPageEvent_add.html";
    }

    /**
     * 跳转到修改打点页面与事件关联
     */
    @RequestMapping("/bhvPageEvent_update/{bhvPageEventId}")
    public String bhvPageEventUpdate(@PathVariable Integer bhvPageEventId, Model model) {
        BhvPageEvent bhvPageEvent = bhvPageEventService.selectById(bhvPageEventId);
        model.addAttribute("item",bhvPageEvent);
        LogObjectHolder.me().set(bhvPageEvent);
        return PREFIX + "bhvPageEvent_edit.html";
    }

    /**
     * 获取打点页面与事件关联列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return bhvPageEventService.selectList(null);
    }

    /**
     * 新增打点页面与事件关联
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(BhvPageEvent bhvPageEvent) {
        bhvPageEventService.insert(bhvPageEvent);
        return SUCCESS_TIP;
    }

    /**
     * 删除打点页面与事件关联
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer bhvPageEventId) {
        bhvPageEventService.deleteById(bhvPageEventId);
        return SUCCESS_TIP;
    }

    /**
     * 修改打点页面与事件关联
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(BhvPageEvent bhvPageEvent) {
        bhvPageEventService.updateById(bhvPageEvent);
        return SUCCESS_TIP;
    }

    /**
     * 打点页面与事件关联详情
     */
    @RequestMapping(value = "/detail/{bhvPageEventId}")
    @ResponseBody
    public Object detail(@PathVariable("bhvPageEventId") Integer bhvPageEventId) {
        return bhvPageEventService.selectById(bhvPageEventId);
    }
}
