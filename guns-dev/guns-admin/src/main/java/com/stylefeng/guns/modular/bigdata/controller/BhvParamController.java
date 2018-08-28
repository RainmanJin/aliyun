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
import com.stylefeng.guns.modular.system.model.BhvParam;
import com.stylefeng.guns.modular.bigdata.service.IBhvParamService;

/**
 * 打点参数关联控制器
 *
 * @author fengshuonan
 * @Date 2018-04-18 18:17:32
 */
@Controller
@RequestMapping("/bhvParam")
public class BhvParamController extends BaseController {

    private String PREFIX = "/bigdata/bhvParam/";

    @Autowired
    private IBhvParamService bhvParamService;

    /**
     * 跳转到打点参数关联首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "bhvParam.html";
    }

    /**
     * 跳转到添加打点参数关联
     */
    @RequestMapping("/bhvParam_add")
    public String bhvParamAdd() {
        return PREFIX + "bhvParam_add.html";
    }

    /**
     * 跳转到修改打点参数关联
     */
    @RequestMapping("/bhvParam_update/{bhvParamId}")
    public String bhvParamUpdate(@PathVariable Integer bhvParamId, Model model) {
        BhvParam bhvParam = bhvParamService.selectById(bhvParamId);
        model.addAttribute("item",bhvParam);
        LogObjectHolder.me().set(bhvParam);
        return PREFIX + "bhvParam_edit.html";
    }

    /**
     * 获取打点参数关联列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return bhvParamService.selectList(null);
    }

    /**
     * 新增打点参数关联
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(BhvParam bhvParam) {
        bhvParamService.insert(bhvParam);
        return SUCCESS_TIP;
    }

    /**
     * 删除打点参数关联
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer bhvParamId) {
        bhvParamService.deleteById(bhvParamId);
        return SUCCESS_TIP;
    }

    /**
     * 修改打点参数关联
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(BhvParam bhvParam) {
        bhvParamService.updateById(bhvParam);
        return SUCCESS_TIP;
    }

    /**
     * 打点参数关联详情
     */
    @RequestMapping(value = "/detail/{bhvParamId}")
    @ResponseBody
    public Object detail(@PathVariable("bhvParamId") Integer bhvParamId) {
        return bhvParamService.selectById(bhvParamId);
    }
}
