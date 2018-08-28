package com.stylefeng.guns.modular.bigdata.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.modular.system.model.KsUserTagClassifyManage;
import com.stylefeng.guns.modular.system.model.RespResult;
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
 * 标签分类控制器
 *
 * @author fengshuonan
 * @Date 2018-08-01 18:40:30
 */
@Controller
@RequestMapping("/ksUserTagClassifyManage")
public class KsUserTagClassifyManageController extends BaseController {

    private String PREFIX = "/bigdata/ksUserTagClassifyManage/";
    @Value("${out-server.tag-server}")
    private String requestAddr;
    HttpHeaders headers = new HttpHeaders();
    MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
    @Autowired
    private RestTemplate restTemplate;

    /**
     * 跳转到标签分类首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "ksUserTagClassifyManage.html";
    }

    /**
     * 跳转到添加标签分类
     */
    @RequestMapping("/ksUserTagClassifyManage_add")
    public String ksUserTagClassifyManageAdd() {
        return PREFIX + "ksUserTagClassifyManage_add.html";
    }

    /**
     * 跳转到修改标签分类
     */
    @RequestMapping("/ksUserTagClassifyManage_update/{ksUserTagClassifyManageId}")
    public String ksUserTagClassifyManageUpdate(@PathVariable Integer ksUserTagClassifyManageId, Model model) {
        KsUserTagClassifyManage ksUserTagClassifyManage = this.restTemplate.getForObject(requestAddr + "usertagclassify/selectusertagclassifybyclassifyid?usertagbyclassifyid=" + ksUserTagClassifyManageId, KsUserTagClassifyManage.class);
        model.addAttribute("item", ksUserTagClassifyManage);
        LogObjectHolder.me().set(ksUserTagClassifyManage);
        return PREFIX + "ksUserTagClassifyManage_edit.html";
    }

    /**
     * 获取标签分类列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String tag_classify_name) {
        tag_classify_name = tag_classify_name == null ? "" : tag_classify_name;
        KsUserTagClassifyManage[] responseArray = restTemplate.postForObject(requestAddr + "/usertagclassify/seltagclassifybyname?tag_classify_name=" + tag_classify_name, null, KsUserTagClassifyManage[].class);

        List<KsUserTagClassifyManage> responseList = new ArrayList<KsUserTagClassifyManage>();
        if (responseArray != null) {
            responseList = Arrays.asList(responseArray);
            for (KsUserTagClassifyManage ksUserTagClassifyManage1 : responseList) {
                if (ksUserTagClassifyManage1.getCreatetime() != null && !"".equals(ksUserTagClassifyManage1.getCreatetime())) {
                    ksUserTagClassifyManage1.setCreatetime(DateFormat.TimeStamp2Date(ksUserTagClassifyManage1.getCreatetime()));
                }
            }
        }
        return responseList;
    }

    /**
     * 新增标签分类
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(KsUserTagClassifyManage ksUserTagClassifyManage) {
        headers.setContentType(type);
        ksUserTagClassifyManage.setCreatetime(DateFormat.dateFormat(null, "yyyy-MM-dd'T'HH:mm:ss.SSSZ"));
        HttpEntity entity = new HttpEntity(ksUserTagClassifyManage, headers);
        ResponseEntity<RespResult> responseEntity = restTemplate.postForEntity(requestAddr + "usertagclassify/addtagclassify", entity, RespResult.class);
        return responseEntity.getBody();
    }

    /**
     * 删除标签分类
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam String ksUserTagClassifyManageId) {
        String linshi = ksUserTagClassifyManageId.replaceAll("_", ",");
        ResponseEntity<RespResult> responseEntity = restTemplate.postForEntity(requestAddr + "usertagclassify/deltagclassify?usertagclassifyids=" + linshi, null, RespResult.class);
        return responseEntity.getBody();
    }

    /**
     * 修改标签分类
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(KsUserTagClassifyManage ksUserTagClassifyManage) {
        headers.setContentType(type);
        HttpEntity entity = new HttpEntity(ksUserTagClassifyManage, headers);
        ResponseEntity<RespResult> responseEntity = restTemplate.postForEntity(requestAddr + "usertagclassify/updatetagclassify", entity, RespResult.class);
        return responseEntity.getBody();
    }

    /**
     * 标签分类详情
     */
    @RequestMapping(value = "/detail/{ksUserTagClassifyManageId}")
    @ResponseBody
    public Object detail(@PathVariable("ksUserTagClassifyManageId") Integer ksUserTagClassifyManageId) {
        KsUserTagClassifyManage ksUserTagClassifyManage = this.restTemplate.getForObject(requestAddr + "usertagclassify/selalltagclassifybyclassifyid?usertagbyclassifyid=" + ksUserTagClassifyManageId, KsUserTagClassifyManage.class);

        return ksUserTagClassifyManage;
    }
}
