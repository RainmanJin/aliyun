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
import com.stylefeng.guns.modular.system.model.BhvPage;
import com.stylefeng.guns.modular.bigdata.service.IBhvPageService;

/**
 * 打点页面Code控制器
 *
 * @author fengshuonan
 * @Date 2018-04-18 18:16:21
 */
@Controller
@RequestMapping("/bhvPage")
public class BhvPageController extends BaseController {

    private String PREFIX = "/bigdata/bhvPage/";

    @Autowired
    private IBhvPageService bhvPageService;

    /**
     * 跳转到打点页面Code首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "bhvPage.html";
    }

    /**
     * 跳转到添加打点页面Code
     */
    @RequestMapping("/bhvPage_add")
    public String bhvPageAdd() {
        return PREFIX + "bhvPage_add.html";
    }

    /**
     * 跳转到修改打点页面Code
     */
    @RequestMapping("/bhvPage_update/{bhvPageId}")
    public String bhvPageUpdate(@PathVariable Integer bhvPageId, Model model) {
        BhvPage bhvPage = bhvPageService.selectById(bhvPageId);
        model.addAttribute("item",bhvPage);
        LogObjectHolder.me().set(bhvPage);
        return PREFIX + "bhvPage_edit.html";
    }

    /**
     * 获取打点页面Code列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return bhvPageService.selectList(null);
    }

    /**
     * 新增打点页面Code
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(BhvPage bhvPage) {
        bhvPageService.insert(bhvPage);
        return SUCCESS_TIP;
    }

    /**
     * 删除打点页面Code
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer bhvPageId) {
        bhvPageService.deleteById(bhvPageId);
        return SUCCESS_TIP;
    }

    /**
     * 修改打点页面Code
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(BhvPage bhvPage) {
        bhvPageService.updateById(bhvPage);
        return SUCCESS_TIP;
    }

    /**
     * 打点页面Code详情
     */
    @RequestMapping(value = "/detail/{bhvPageId}")
    @ResponseBody
    public Object detail(@PathVariable("bhvPageId") Integer bhvPageId) {
        return bhvPageService.selectById(bhvPageId);
    }
}
