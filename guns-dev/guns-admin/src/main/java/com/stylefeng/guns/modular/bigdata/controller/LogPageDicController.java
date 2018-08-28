package com.stylefeng.guns.modular.bigdata.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.modular.bigdata.service.ILogDetailsService;
import com.stylefeng.guns.modular.bigdata.service.ILogPageDicService;
import com.stylefeng.guns.modular.system.model.LogDetails;
import com.stylefeng.guns.modular.system.model.LogPageDic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 页面管理控制器
 *
 * @author fengshuonan
 * @Date 2018-07-06 14:09:17
 */
@Controller
@RequestMapping("/logPageDic")
public class LogPageDicController extends BaseController {

    private String PREFIX = "/bigdata/logPageDic/";

    @Autowired
    private ILogPageDicService logPageDicService;
    @Autowired
    private ILogDetailsService logDetailsService;

    /**
     * 跳转到页面管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "logPageDic.html";
    }

    /**
     * 跳转到添加页面管理
     */
    @RequestMapping("/logPageDic_add")
    public String logPageDicAdd() {
        return PREFIX + "logPageDic_add.html";
    }

    /**
     * 跳转到修改页面管理
     */
    @RequestMapping("/logPageDic_update/{logPageDicId}")
    public String logPageDicUpdate(@PathVariable Integer logPageDicId, Model model) {
        LogPageDic logPageDic = logPageDicService.selectById(logPageDicId);
        model.addAttribute("item", logPageDic);
        LogObjectHolder.me().set(logPageDic);
        return PREFIX + "logPageDic_edit.html";
    }

    /**
     * 获取页面管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String pagecode, String pagename) {
        EntityWrapper<LogPageDic> en = new EntityWrapper<LogPageDic>();
        LogPageDic lp = new LogPageDic();
        lp.setIsDelete("0");
        en.setEntity(lp);
        en.like(true, "page_code", pagecode);
        en.like(true, "page_name", pagename);
        return logPageDicService.selectList(en);
    }

    /**
     * 新增页面管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(LogPageDic logPageDic) {
        logPageDicService.insert(logPageDic);
        return SUCCESS_TIP;
    }

    /**
     * 删除页面管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer logPageDicId) {
        EntityWrapper<LogDetails> en = new EntityWrapper<LogDetails>();
        LogDetails lp = new LogDetails();
        lp.setIsDelete("0");
        en.setEntity(lp);
        en.eq(true, "page_num", logPageDicId + "");
        int rownum = logDetailsService.selectCount(en);
        if (rownum > 0) {
            SUCCESS_TIP.setCode(409);
            SUCCESS_TIP.setMessage("该page正在被使用，请删除相关的依赖明细！");
        } else {
            SUCCESS_TIP.setCode(200);
            SUCCESS_TIP.setMessage("删除成功");
            logPageDicService.deletePageById(logPageDicId);
        }
        return SUCCESS_TIP;


    }

    /**
     * 修改页面管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(LogPageDic logPageDic) {
        logPageDicService.updateById(logPageDic);
        return SUCCESS_TIP;
    }

    /**
     * 页面管理详情
     */
    @RequestMapping(value = "/detail/{logPageDicId}")
    @ResponseBody
    public Object detail(@PathVariable("logPageDicId") Integer logPageDicId) {
        return logPageDicService.selectById(logPageDicId);
    }
}
