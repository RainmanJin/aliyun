package com.stylefeng.guns.modular.bigdata.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.base.tips.SuccessTip;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.modular.bigdata.service.ILogCheckDeviceService;
import com.stylefeng.guns.modular.system.model.LogCheckDevice;
import com.stylefeng.guns.util.IdTypeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 用户设备号控制器
 *
 * @author fengshuonan
 * @Date 2018-08-07 14:39:23
 */
@Controller
@RequestMapping("/logCheckDevice")
public class LogCheckDeviceController extends BaseController {

    private String PREFIX = "/bigdata/logCheckDevice/";

    @Autowired
    private ILogCheckDeviceService logCheckDeviceService;

    /**
     * 跳转到用户设备号首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "logCheckDevice.html";
    }


    /**
     * 跳转到添加用户设备号
     */
    @RequestMapping("/logCheckDevice_add")
    public String logCheckDeviceAdd() {
        return PREFIX + "logCheckDevice_add.html";
    }

    /**
     * 跳转到设备号码快查
     */
    @RequestMapping("/logCheckDevice_fastSelect_jump")
    public String logCheckDevice_fastSelect_jump() {
        return PREFIX + "logCheckDevice_fastSelect.html";
    }

    /**
     * 跳转到修改用户设备号
     */
    @RequestMapping("/logCheckDevice_update/{logCheckDeviceId}")
    public String logCheckDeviceUpdate(@PathVariable Integer logCheckDeviceId, Model model) {
        LogCheckDevice logCheckDevice = logCheckDeviceService.selectById(logCheckDeviceId);
        model.addAttribute("item", logCheckDevice);
        LogObjectHolder.me().set(logCheckDevice);
        return PREFIX + "logCheckDevice_edit.html";
    }

    /**
     * 获取用户设备号列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String deviceId, String userName) {
        EntityWrapper<LogCheckDevice> en = new EntityWrapper<LogCheckDevice>();
        LogCheckDevice le = new LogCheckDevice();
        en.setEntity(le);
        if (deviceId != null) {
            en.like(true, "deviceId", deviceId);
        }
        if (userName != null) {
            en.like(true, "userName", userName);
        }
        return logCheckDeviceService.selectList(en);
    }

    /**
     * 设备号码快查
     */
    @RequestMapping("/fastSelect")
    @ResponseBody
    public Object fastSelect(String platform, String appid) {

        List<Map<String,String>> deviceStringList = new ArrayList<Map<String,String>>();
        if (appid != null && !"".equals(appid)) {
            long appuserIdL = IdTypeHandler.decode(appid);
            if ("prod".equals(platform)) {
                deviceStringList = logCheckDeviceService.getDeviceIdListByUserId(appuserIdL);
            } else {
                deviceStringList = logCheckDeviceService.getTestDeviceIdListByUserId(appuserIdL);
            }
        }

        return deviceStringList;
    }

    /**
     * 新增用户设备号
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(String deviceId, String userName, String idType) {
        //添加的是用户号码（app我的下面的，此时需要解码）
        if ("userid".equals(idType)) {
            //解析获得真正的userid
            long userid = IdTypeHandler.decode(deviceId);
            //用userid去请求设备id
            List<Map<String,String>> deviceidList = logCheckDeviceService.getDeviceIdListByUserId(userid);
            if (deviceidList != null && deviceidList.size() != 0) {
                for (Map<String,String> deviceid : deviceidList) {
                    LogCheckDevice logCheckDevice_new = new LogCheckDevice();
                    logCheckDevice_new.setDeviceId(deviceid.get("deviceid"));
                    logCheckDevice_new.setUserName(userName);
                    if (logCheckDeviceService.checkExist(deviceid.get("deviceid"))) {
                        logCheckDeviceService.insert(logCheckDevice_new);
                    }
                }
            } else {
                SuccessTip SUCCESS_TIP1 = new SuccessTip();
                SUCCESS_TIP1.setCode(410);
                SUCCESS_TIP1.setMessage("您输入的设备id不正确，请重新输入！");
                return SUCCESS_TIP1;
            }
        }//当用户输入的是设备id的时候
        else {
            if (logCheckDeviceService.checkExist(deviceId)) {
                LogCheckDevice logCheckDevice = new LogCheckDevice();
                logCheckDevice.setDeviceId(deviceId);
                logCheckDevice.setUserName(userName);
                logCheckDeviceService.insert(logCheckDevice);
            }
        }
        return SUCCESS_TIP;
    }

    /**
     * 删除用户设备号
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer logCheckDeviceId) {
        logCheckDeviceService.deleteById(logCheckDeviceId);
        return SUCCESS_TIP;
    }

    /**
     * 修改用户设备号
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(LogCheckDevice logCheckDevice) {
        logCheckDeviceService.updateById(logCheckDevice);
        return SUCCESS_TIP;
    }

    /**
     * 用户设备号详情
     */
    @RequestMapping(value = "/detail/{logCheckDeviceId}")
    @ResponseBody
    public Object detail(@PathVariable("logCheckDeviceId") Integer logCheckDeviceId) {
        return logCheckDeviceService.selectById(logCheckDeviceId);
    }
//    public static void main(String[] args) {
//        String deviceid="1517658618";
//        long userid = IdTypeHandler.decode(deviceid);
//        System.out.print(userid);
//    }
}
