package com.stylefeng.guns.modular.bigdata.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.modular.system.model.KsUserTagTask;
import com.stylefeng.guns.modular.system.model.RespResult;
import com.stylefeng.guns.modular.system.model.ksUserTagExecuteInfo;
import com.stylefeng.guns.util.DateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 标签推送任务控制器
 *
 * @author fengshuonan
 * @Date 2018-08-13 15:40:34
 */
@Controller
@RequestMapping("/ksUserTagTask")
public class KsUserTagTaskController extends BaseController {
    @Value("${out-server.tag-server}")
    private String requestAddr;

    HttpHeaders headers = new HttpHeaders();
    MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");

    private String PREFIX = "/bigdata/ksUserTagTask/";
    @Autowired
    private RestTemplate restTemplate;

    /**
     * 跳转到标签推送任务首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "ksUserTagTask.html";
    }

    /**
     * 跳转到添加标签推送任务
     */
    @RequestMapping("/ksUserTagTask_add")
    public String ksUserTagTaskAdd() {
        return PREFIX + "ksUserTagTask_add.html";
    }


    /**
     * 跳转到标签推送日志页面
     */
    @RequestMapping("/ksUserTagTask_log/{ksUserTagTaskId}")
    public Object openKsUserTagTaskExeLog(@PathVariable Integer ksUserTagTaskId, Model model) {
        model.addAttribute("item", ksUserTagTaskId);
        LogObjectHolder.me().set(ksUserTagTaskId);
        return PREFIX + "ksUserTagTask_log.html";
    }

    /**
     * 获取日志信息
     */
    @RequestMapping(value = "/logList")
    @ResponseBody
    public Object getTagTaskExeLog(String ksUserTagTaskId) {
        ksUserTagExecuteInfo[] responseArray = restTemplate.postForObject(requestAddr + "usertagtask/viewusertagtaskexeinfo?task_id=" + ksUserTagTaskId, null, ksUserTagExecuteInfo[].class);
        List<ksUserTagExecuteInfo> responseList = new ArrayList<ksUserTagExecuteInfo>();
        if (responseArray != null) {
            responseList = Arrays.asList(responseArray);
            for (ksUserTagExecuteInfo ksUserTagExecuteInfoin : responseList) {
                if (ksUserTagExecuteInfoin.getExecutetime() != null && !"".equals(ksUserTagExecuteInfoin.getExecutetime())) {
                    ksUserTagExecuteInfoin.setExecutetime(DateFormat.TimeStamp2Date(ksUserTagExecuteInfoin.getExecutetime()));
                }
            }
        }

        return responseList;
    }


    /**
     * 获取标签推送任务列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list() {
        headers.setContentType(type);
        KsUserTagTask[] responseArray = restTemplate.postForObject(requestAddr + "usertagtask/viewallusertagtask", null, KsUserTagTask[].class);
        List<KsUserTagTask> responseList = new ArrayList<KsUserTagTask>();
        if (responseArray != null) {
            responseList = Arrays.asList(responseArray);
            for (KsUserTagTask ksUserTagTask : responseList) {
                if ("1".equals(ksUserTagTask.getStatus())) {
                    ksUserTagTask.setStatus("启用");
                } else {
                    ksUserTagTask.setStatus("停用");
                }
                if ("1".equals(ksUserTagTask.getDatasource_type())) {
                    ksUserTagTask.setDatasource_type("mysql");
                } else {
                    ksUserTagTask.setDatasource_type("redise");
                }
                if ("0".equals(ksUserTagTask.getIncremental())) {
                    ksUserTagTask.setIncremental("全量");
                } else {
                    ksUserTagTask.setIncremental("增量");
                }
                if (ksUserTagTask.getCreatetime() != null && !"".equals(ksUserTagTask.getCreatetime())) {
                    ksUserTagTask.setCreatetime(DateFormat.TimeStamp2Date(ksUserTagTask.getCreatetime()));
                }

            }
        }

        return responseList;
    }

    /**
     * 新增标签推送任务
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(KsUserTagTask ksUserTagTask) {
        headers.setContentType(type);

        ksUserTagTask.setCreatetime(DateFormat.dateFormat(ksUserTagTask.getCreatetime(), "yyyy-MM-dd'T'HH:mm:ss.SSSZ"));
        if (ksUserTagTask.getHour() != null && !"".equals(ksUserTagTask.getHour())) {
            ksUserTagTask.setCron_expression("0 " + ksUserTagTask.getMinutes() + " " + ksUserTagTask.getHour() + " * * ?");
        }
        HttpEntity entity = new HttpEntity(ksUserTagTask, headers);
        ResponseEntity<RespResult> responseEntity = restTemplate.postForEntity(requestAddr + "usertagtask/addusertagtask", entity, RespResult.class);
        return responseEntity.getBody();
    }

    /**
     * 删除标签推送任务
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer ksUserTagTaskId) {
        ResponseEntity<RespResult> responseEntity = restTemplate.postForEntity(requestAddr + "usertagtask/dropusertagtask?tagtaskid=" + ksUserTagTaskId, null, RespResult.class);
        return responseEntity.getBody();
    }

    /**
     * 启动任务
     */
    @RequestMapping(value = "/start")
    @ResponseBody
    public Object start(@RequestParam Integer ksUserTagTaskId) {
        ResponseEntity<RespResult> responseEntity = restTemplate.postForEntity(requestAddr + "usertagtask/startusertagtask?tagtaskid=" + ksUserTagTaskId, null, RespResult.class);
        return responseEntity.getBody();
    }


}
