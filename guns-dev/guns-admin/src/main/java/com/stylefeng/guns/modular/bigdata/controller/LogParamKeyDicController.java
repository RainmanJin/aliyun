package com.stylefeng.guns.modular.bigdata.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.modular.bigdata.service.ILogParamKeyDicService;
import com.stylefeng.guns.modular.system.model.LogParamKeyDic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 参数key管理控制器
 *
 * @author fengshuonan
 * @Date 2018-07-06 19:27:01
 */
@Controller
@RequestMapping("/logParamKeyDic")
public class LogParamKeyDicController extends BaseController {

    private String PREFIX = "/bigdata/logParamKeyDic/";

    @Autowired
    private ILogParamKeyDicService logParamKeyDicService;

    /**
     * 跳转到参数key管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "logParamKeyDic.html";
    }

    /**
     * 跳转到添加参数key管理
     */
    @RequestMapping("/logParamKeyDic_add")
    public String logParamKeyDicAdd() {
        return PREFIX + "logParamKeyDic_add.html";
    }

    /**
     * 跳转到修改参数key管理
     */
    @RequestMapping("/logParamKeyDic_update/{logParamKeyDicId}")
    public String logParamKeyDicUpdate(@PathVariable Integer logParamKeyDicId, Model model) {
        LogParamKeyDic logParamKeyDic = logParamKeyDicService.selectById(logParamKeyDicId);
        model.addAttribute("item",logParamKeyDic);
        LogObjectHolder.me().set(logParamKeyDic);
        return PREFIX + "logParamKeyDic_edit.html";
    }

    /**
     * 获取参数key管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String paramkeycode,String paramkeyname) {
        EntityWrapper<LogParamKeyDic> en=new EntityWrapper<LogParamKeyDic>();
        LogParamKeyDic lpk=new LogParamKeyDic();
        lpk.setIsDelete("0");
        en.setEntity(lpk);
        en.like(true,"param_key_code",paramkeycode);
        en.like(true,"param_key_name",paramkeyname);
        return logParamKeyDicService.selectList(en);
    }

    /**
     * 新增参数key管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(LogParamKeyDic logParamKeyDic) {
        logParamKeyDicService.insert(logParamKeyDic);
        return SUCCESS_TIP;
    }

    /**
     * 删除参数key管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer logParamKeyDicId) {
        logParamKeyDicService.deleteParamKeyById(logParamKeyDicId);
        return SUCCESS_TIP;
    }

    /**
     * 修改参数key管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(LogParamKeyDic logParamKeyDic) {
        logParamKeyDicService.updateById(logParamKeyDic);
        return SUCCESS_TIP;
    }

    /**
     * 参数key管理详情
     */
    @RequestMapping(value = "/detail/{logParamKeyDicId}")
    @ResponseBody
    public Object detail(@PathVariable("logParamKeyDicId") Integer logParamKeyDicId) {
        return logParamKeyDicService.selectById(logParamKeyDicId);
    }
}
