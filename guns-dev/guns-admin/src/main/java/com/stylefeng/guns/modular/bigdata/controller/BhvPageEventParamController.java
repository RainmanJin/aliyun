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
import com.stylefeng.guns.modular.system.model.BhvPageEventParam;
import com.stylefeng.guns.modular.bigdata.service.IBhvPageEventParamService;

/**
 * 打点页面事件参数关联控制器
 *
 * @author fengshuonan
 * @Date 2018-04-18 18:50:19
 */
@Controller
@RequestMapping("/bhvPageEventParam")
public class BhvPageEventParamController extends BaseController {

    private String PREFIX = "/bigdata/bhvPageEventParam/";

    @Autowired
    private IBhvPageEventParamService bhvPageEventParamService;

    /**
     * 跳转到打点页面事件参数关联首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "bhvPageEventParam.html";
    }

    /**
     * 跳转到添加打点页面事件参数关联
     */
    @RequestMapping("/bhvPageEventParam_add")
    public String bhvPageEventParamAdd() {
        return PREFIX + "bhvPageEventParam_add.html";
    }

    /**
     * 跳转到修改打点页面事件参数关联
     */
    @RequestMapping("/bhvPageEventParam_update/{bhvPageEventParamId}")
    public String bhvPageEventParamUpdate(@PathVariable Integer bhvPageEventParamId, Model model) {
        BhvPageEventParam bhvPageEventParam = bhvPageEventParamService.selectById(bhvPageEventParamId);
        model.addAttribute("item",bhvPageEventParam);
        LogObjectHolder.me().set(bhvPageEventParam);
        return PREFIX + "bhvPageEventParam_edit.html";
    }

    /**
     * 获取打点页面事件参数关联列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return bhvPageEventParamService.selectList(null);
    }

    /**
     * 新增打点页面事件参数关联
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(BhvPageEventParam bhvPageEventParam) {
        bhvPageEventParamService.insert(bhvPageEventParam);
        return SUCCESS_TIP;
    }

    /**
     * 删除打点页面事件参数关联
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer bhvPageEventParamId) {
        bhvPageEventParamService.deleteById(bhvPageEventParamId);
        return SUCCESS_TIP;
    }

    /**
     * 修改打点页面事件参数关联
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(BhvPageEventParam bhvPageEventParam) {
        bhvPageEventParamService.updateById(bhvPageEventParam);
        return SUCCESS_TIP;
    }

    /**
     * 打点页面事件参数关联详情
     */
    @RequestMapping(value = "/detail/{bhvPageEventParamId}")
    @ResponseBody
    public Object detail(@PathVariable("bhvPageEventParamId") Integer bhvPageEventParamId) {
        return bhvPageEventParamService.selectById(bhvPageEventParamId);
    }
}
