package com.stylefeng.guns.modular.bigdata.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.modular.bigdata.service.ILogParamGroupService;
import com.stylefeng.guns.modular.system.model.LogParamGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 参数组管理控制器
 *
 * @author fengshuonan
 * @Date 2018-07-12 10:50:32
 */
@Controller
@RequestMapping("/logParamGroup")
public class LogParamGroupController extends BaseController {

    private String PREFIX = "/bigdata/logParamGroup/";

    @Autowired
    private ILogParamGroupService logParamGroupService;

    /**
     * 跳转到参数组管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "logParamGroup.html";
    }

    /**
     * 跳转到添加参数组管理
     */
    @RequestMapping("/logParamGroup_add")
    public String logParamGroupAdd() {
        return PREFIX + "logParamGroup_add.html";
    }

    /**
     * 跳转到修改参数组管理
     */
    @RequestMapping("/logParamGroup_update/{logParamGroupId}")
    public String logParamGroupUpdate(@PathVariable Integer logParamGroupId, Model model) {
        LogParamGroup logParamGroup = logParamGroupService.selectById(logParamGroupId);
        model.addAttribute("item", logParamGroup);
        LogObjectHolder.me().set(logParamGroup);
        return PREFIX + "logParamGroup_edit.html";
    }

    /**
     * 获取参数组管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String groupName, String groupDescription) {
        EntityWrapper<LogParamGroup> en = new EntityWrapper<LogParamGroup>();
        LogParamGroup lpg = new LogParamGroup();
        lpg.setIsDelete("0");
        en.setEntity(lpg);
        if(groupName!=null){
            en.like(true, "group_name", groupName);
        }
       if(groupDescription!=null){
           en.like(true, "group_description", groupDescription);
       }
        return logParamGroupService.selectList(en);
    }

    /**
     * 新增参数组管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(LogParamGroup logParamGroup) {
        logParamGroupService.insert(logParamGroup);
        return SUCCESS_TIP;
    }

    /**
     * 删除参数组管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer logParamGroupId) {
//        logParamGroupService.deleteById(logParamGroupId);
        LogParamGroup logParamGroup = new LogParamGroup();
        logParamGroup.setIsDelete("1");
        logParamGroup.setId(logParamGroupId);
        logParamGroupService.updateById(logParamGroup);
        return SUCCESS_TIP;
    }

    /**
     * 修改参数组管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(LogParamGroup logParamGroup) {
        logParamGroupService.updateById(logParamGroup);
        return SUCCESS_TIP;
    }

    /**
     * 参数组管理详情
     */
    @RequestMapping(value = "/detail/{logParamGroupId}")
    @ResponseBody
    public Object detail(@PathVariable("logParamGroupId") Integer logParamGroupId) {
        return logParamGroupService.selectById(logParamGroupId);
    }

    /**
     * 预览数据
     */
    @RequestMapping(value = "/preview")
    @ResponseBody
    public Object detail(String param_key_value,String paramGroupId) {

        String previewStr = logParamGroupService.getParamPreview(param_key_value,paramGroupId,"1");
        return previewStr;
    }
}
