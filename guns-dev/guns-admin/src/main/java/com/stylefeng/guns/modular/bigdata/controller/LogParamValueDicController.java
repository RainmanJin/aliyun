package com.stylefeng.guns.modular.bigdata.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.modular.bigdata.service.ILogParamValueDicService;
import com.stylefeng.guns.modular.system.model.LogParamValueDic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 参数value管理控制器
 *
 * @author fengshuonan
 * @Date 2018-07-06 19:28:12
 */
@Controller
@RequestMapping("/logParamValueDic")
public class LogParamValueDicController extends BaseController {

    private String PREFIX = "/bigdata/logParamValueDic/";

    @Autowired
    private ILogParamValueDicService logParamValueDicService;

    /**
     * 跳转到参数value管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "logParamValueDic.html";
    }

    /**
     * 跳转到添加参数value管理
     */
    @RequestMapping("/logParamValueDic_add")
    public String logParamValueDicAdd() {
        return PREFIX + "logParamValueDic_add.html";
    }

    /**
     * 跳转到修改参数value管理
     */
    @RequestMapping("/logParamValueDic_update/{logParamValueDicId}")
    public String logParamValueDicUpdate(@PathVariable Integer logParamValueDicId, Model model) {
        LogParamValueDic logParamValueDic = logParamValueDicService.selectById(logParamValueDicId);
        model.addAttribute("item",logParamValueDic);
        LogObjectHolder.me().set(logParamValueDic);
        return PREFIX + "logParamValueDic_edit.html";
    }

    /**
     * 获取参数value管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String paramvaluecode,String paramvaluename) {
        EntityWrapper<LogParamValueDic> en=new EntityWrapper<LogParamValueDic>();
        LogParamValueDic lpv=new LogParamValueDic();
        lpv.setIsDelete("0");
        en.setEntity(lpv);
        en.like(true,"param_value_code",paramvaluecode);
        en.like(true,"param_value_name",paramvaluename);
        return logParamValueDicService.selectList(en);
    }

    /**
     * 新增参数value管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(LogParamValueDic logParamValueDic) {
        logParamValueDicService.insert(logParamValueDic);
        return SUCCESS_TIP;
    }

    /**
     * 删除参数value管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer logParamValueDicId) {
        logParamValueDicService.deleteParamValueById(logParamValueDicId);
        return SUCCESS_TIP;
    }

    /**
     * 修改参数value管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(LogParamValueDic logParamValueDic) {
        logParamValueDicService.updateById(logParamValueDic);
        return SUCCESS_TIP;
    }

    /**
     * 参数value管理详情
     */
    @RequestMapping(value = "/detail/{logParamValueDicId}")
    @ResponseBody
    public Object detail(@PathVariable("logParamValueDicId") Integer logParamValueDicId) {
        return logParamValueDicService.selectById(logParamValueDicId);
    }
}
