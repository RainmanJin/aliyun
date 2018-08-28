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
import com.stylefeng.guns.modular.system.model.BhvEvent;
import com.stylefeng.guns.modular.bigdata.service.IBhvEventService;

/**
 * 打点事件code控制器
 *
 * @author fengshuonan
 * @Date 2018-04-18 18:15:11
 */
@Controller
@RequestMapping("/bhvEvent")
public class BhvEventController extends BaseController {

    private String PREFIX = "/bigdata/bhvEvent/";

    @Autowired
    private IBhvEventService bhvEventService;

    /**
     * 跳转到打点事件code首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "bhvEvent.html";
    }

    /**
     * 跳转到添加打点事件code
     */
    @RequestMapping("/bhvEvent_add")
    public String bhvEventAdd() {
        return PREFIX + "bhvEvent_add.html";
    }

    /**
     * 跳转到修改打点事件code
     */
    @RequestMapping("/bhvEvent_update/{bhvEventId}")
    public String bhvEventUpdate(@PathVariable Integer bhvEventId, Model model) {
        BhvEvent bhvEvent = bhvEventService.selectById(bhvEventId);
        model.addAttribute("item",bhvEvent);
        LogObjectHolder.me().set(bhvEvent);
        return PREFIX + "bhvEvent_edit.html";
    }

    /**
     * 获取打点事件code列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return bhvEventService.selectList(null);
    }

    /**
     * 新增打点事件code
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(BhvEvent bhvEvent) {
        bhvEventService.insert(bhvEvent);
        return SUCCESS_TIP;
    }

    /**
     * 删除打点事件code
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer bhvEventId) {
        bhvEventService.deleteById(bhvEventId);
        return SUCCESS_TIP;
    }

    /**
     * 修改打点事件code
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(BhvEvent bhvEvent) {
        bhvEventService.updateById(bhvEvent);
        return SUCCESS_TIP;
    }

    /**
     * 打点事件code详情
     */
    @RequestMapping(value = "/detail/{bhvEventId}")
    @ResponseBody
    public Object detail(@PathVariable("bhvEventId") Integer bhvEventId) {
        return bhvEventService.selectById(bhvEventId);
    }
}
