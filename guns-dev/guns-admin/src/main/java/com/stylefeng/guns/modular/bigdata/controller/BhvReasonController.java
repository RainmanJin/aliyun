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
import com.stylefeng.guns.modular.system.model.BhvReason;
import com.stylefeng.guns.modular.bigdata.service.IBhvReasonService;

/**
 * 打点原因关联控制器
 *
 * @author fengshuonan
 * @Date 2018-04-18 18:17:50
 */
@Controller
@RequestMapping("/bhvReason")
public class BhvReasonController extends BaseController {

    private String PREFIX = "/bigdata/bhvReason/";

    @Autowired
    private IBhvReasonService bhvReasonService;

    /**
     * 跳转到打点原因关联首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "bhvReason.html";
    }

    /**
     * 跳转到添加打点原因关联
     */
    @RequestMapping("/bhvReason_add")
    public String bhvReasonAdd() {
        return PREFIX + "bhvReason_add.html";
    }

    /**
     * 跳转到修改打点原因关联
     */
    @RequestMapping("/bhvReason_update/{bhvReasonId}")
    public String bhvReasonUpdate(@PathVariable Integer bhvReasonId, Model model) {
        BhvReason bhvReason = bhvReasonService.selectById(bhvReasonId);
        model.addAttribute("item",bhvReason);
        LogObjectHolder.me().set(bhvReason);
        return PREFIX + "bhvReason_edit.html";
    }

    /**
     * 获取打点原因关联列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return bhvReasonService.selectList(null);
    }

    /**
     * 新增打点原因关联
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(BhvReason bhvReason) {
        bhvReasonService.insert(bhvReason);
        return SUCCESS_TIP;
    }

    /**
     * 删除打点原因关联
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer bhvReasonId) {
        bhvReasonService.deleteById(bhvReasonId);
        return SUCCESS_TIP;
    }

    /**
     * 修改打点原因关联
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(BhvReason bhvReason) {
        bhvReasonService.updateById(bhvReason);
        return SUCCESS_TIP;
    }

    /**
     * 打点原因关联详情
     */
    @RequestMapping(value = "/detail/{bhvReasonId}")
    @ResponseBody
    public Object detail(@PathVariable("bhvReasonId") Integer bhvReasonId) {
        return bhvReasonService.selectById(bhvReasonId);
    }
}
