package com.stylefeng.guns.modular.bigdata.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.modular.bigdata.service.ILogDetailsService;
import com.stylefeng.guns.modular.bigdata.service.ILogEventDicService;
import com.stylefeng.guns.modular.system.model.LogDetails;
import com.stylefeng.guns.modular.system.model.LogEventDic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 事件管理控制器
 *
 * @author fengshuonan
 * @Date 2018-07-06 19:23:56
 */
@Controller
@RequestMapping("/logEventDic")
public class LogEventDicController extends BaseController {

    private String PREFIX = "/bigdata/logEventDic/";

    @Autowired
    private ILogEventDicService logEventDicService;
    @Autowired
    private ILogDetailsService logDetailsService;

    /**
     * 跳转到事件管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "logEventDic.html";
    }

    /**
     * 跳转到添加事件管理
     */
    @RequestMapping("/logEventDic_add")
    public String logEventDicAdd() {
        return PREFIX + "logEventDic_add.html";
    }

    /**
     * 跳转到修改事件管理
     */
    @RequestMapping("/logEventDic_update/{logEventDicId}")
    public String logEventDicUpdate(@PathVariable Integer logEventDicId, Model model) {
        LogEventDic logEventDic = logEventDicService.selectById(logEventDicId);
        model.addAttribute("item",logEventDic);
        LogObjectHolder.me().set(logEventDic);
        return PREFIX + "logEventDic_edit.html";
    }

    /**
     * 获取事件管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String eventcode,String eventname) {
        EntityWrapper<LogEventDic> en=new EntityWrapper<LogEventDic>();
        LogEventDic le=new LogEventDic();
        le.setIsDelete("0");
        en.setEntity(le);
        en.like(true,"event_code",eventcode);
        en.like(true,"event_name",eventname);
        return logEventDicService.selectList(en);
    }

    /**
     * 新增事件管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(LogEventDic logEventDic) {
        logEventDicService.insert(logEventDic);
        return SUCCESS_TIP;
    }

    /**
     * 删除事件管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer logEventDicId) {
        EntityWrapper<LogDetails> en = new EntityWrapper<LogDetails>();
        LogDetails lp = new LogDetails();
        lp.setIsDelete("0");
        en.setEntity(lp);
        en.eq(true, "event_num", logEventDicId + "");
        int rownum = logDetailsService.selectCount(en);
        if (rownum > 0) {
            SUCCESS_TIP.setCode(409);
            SUCCESS_TIP.setMessage("该event正在被使用，请删除相关的依赖明细！");
        } else {
            SUCCESS_TIP.setCode(200);
            SUCCESS_TIP.setMessage("删除成功");
            logEventDicService.deleteEventById(logEventDicId);
        }
        return SUCCESS_TIP;
    }

    /**
     * 修改事件管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(LogEventDic logEventDic) {
        logEventDicService.updateById(logEventDic);
        return SUCCESS_TIP;
    }

    /**
     * 事件管理详情
     */
    @RequestMapping(value = "/detail/{logEventDicId}")
    @ResponseBody
    public Object detail(@PathVariable("logEventDicId") Integer logEventDicId) {
        return logEventDicService.selectById(logEventDicId);
    }
}
