package com.stylefeng.guns.modular.bigdata.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.modular.system.model.KsUserTagManage;
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
 * 标签管理控制器
 *
 * @author fengshuonan
 * @Date 2018-07-29 21:08:58
 */
@Controller
@RequestMapping("/ksUserTagManage")
public class KsUserTagManageController extends BaseController {

    private String PREFIX = "/bigdata/ksUserTagManage/";
    @Value("${out-server.tag-server}")
    private String requestAddr;

    HttpHeaders headers = new HttpHeaders();
    MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
    @Autowired
    private RestTemplate restTemplate;

    /**
     * 跳转到标签管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "ksUserTagManage.html";
    }

    /**
     * 跳转到标签快查页
     */
    @RequestMapping("/fasttips")
    public String fasttips() {
        return PREFIX + "ksUserTagManage_fasttips.html";
    }

    /**
     * 跳转到添加标签管理
     */
    @RequestMapping("/ksUserTagManage_add")
    public String ksUserTagManageAdd() {
        return PREFIX + "ksUserTagManage_add.html";
    }

    /**
     * 跳转到修改标签管理
     */
    @RequestMapping("/ksUserTagManage_update/{ksUserTagManageId}")
    public String ksUserTagManageUpdate(@PathVariable Integer ksUserTagManageId, Model model) {
        KsUserTagManage ksUserTagManage = this.restTemplate.getForObject(requestAddr + "usertag/seltag?user_tagid=" + ksUserTagManageId, KsUserTagManage.class);
        if (ksUserTagManage.getStarttime() != null && !"".equals(ksUserTagManage.getStarttime())) {
            ksUserTagManage.setStarttime(DateFormat.TimeStamp2Date(ksUserTagManage.getStarttime()));
        }
        if (ksUserTagManage.getEndtime() != null && !"".equals(ksUserTagManage.getEndtime())) {
            ksUserTagManage.setEndtime(DateFormat.TimeStamp2Date(ksUserTagManage.getEndtime()));
        }

        model.addAttribute("item", ksUserTagManage);
        LogObjectHolder.me().set(ksUserTagManage);
        return PREFIX + "ksUserTagManage_edit.html";
    }

    /**
     * 获取标签管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public List<KsUserTagManage> list(KsUserTagManage ksUserTagManage) {
        headers.setContentType(type);
        ksUserTagManage.setTag_classify_id(ksUserTagManage.getTag_classify_id() == null ? "" : ksUserTagManage.getTag_classify_id());
        ksUserTagManage.setTag_name(ksUserTagManage.getTag_name() == null ? "" : ksUserTagManage.getTag_name());
        HttpEntity entity = new HttpEntity(ksUserTagManage, headers);
        KsUserTagManage[] responseArray = restTemplate.postForObject(requestAddr + "usertag/termquerytag", entity, KsUserTagManage[].class);
        List<KsUserTagManage> responseList = new ArrayList<KsUserTagManage>();
        if (responseArray != null) {
            responseList = Arrays.asList(responseArray);
            for (KsUserTagManage ksUserTagManage1 : responseList) {
                if (ksUserTagManage1.getCreatetime() != null && !"".equals(ksUserTagManage1.getCreatetime())) {
                    ksUserTagManage1.setCreatetime(DateFormat.TimeStamp2Date(ksUserTagManage1.getCreatetime()));
                }
            }
        }

        return responseList;
    }

    /**
     * 获取标签快速查询列表
     */
    @RequestMapping(value = "/fastList")
    @ResponseBody
    public List<KsUserTagManage> fastList(String userid) {
        headers.setContentType(type);
        String selAddr = "usertag/defaulttaginfo";
        if (userid != null && !"".equals(userid)) {
            selAddr = "usertag/seltaginfo?user_id=" + userid;
        }
        KsUserTagManage[] responseArray = restTemplate.postForObject(requestAddr + selAddr, null, KsUserTagManage[].class);
        List<KsUserTagManage> responseList = new ArrayList<KsUserTagManage>();
        if (responseArray != null) {
            responseList = Arrays.asList(responseArray);
            for (KsUserTagManage ksUserTagManage1 : responseList) {
                if (ksUserTagManage1.getCreatetime() != null && !"".equals(ksUserTagManage1.getCreatetime())) {
                    ksUserTagManage1.setCreatetime(DateFormat.TimeStamp2Date(ksUserTagManage1.getCreatetime()));
                }
                if ("1".equals(ksUserTagManage1.getTag_status())) {
                    ksUserTagManage1.setTag_status("动态");
                } else {
                    ksUserTagManage1.setTag_status("静态");
                }
            }
        }

        return responseList;
    }


    /**
     * 获取标签预览页
     */
    @RequestMapping(value = "/ksUserTagManage_detail/{ksUserTagManageId}")
    public Object getTagById(@PathVariable Integer ksUserTagManageId, Model model) {
        KsUserTagManage ksUserTagManage = this.restTemplate.getForObject("http://172.17.8.124:9074/usertag/seltag?user_tagid=" + ksUserTagManageId, KsUserTagManage.class);
        if (ksUserTagManage.getStarttime() != null && !"".equals(ksUserTagManage.getStarttime())) {
            ksUserTagManage.setStarttime(DateFormat.TimeStamp2Date(ksUserTagManage.getStarttime()));
        }
        if (ksUserTagManage.getEndtime() != null && !"".equals(ksUserTagManage.getEndtime())) {
            ksUserTagManage.setEndtime(DateFormat.TimeStamp2Date(ksUserTagManage.getEndtime()));
        }
        if (ksUserTagManage.getCreatetime() != null && !"".equals(ksUserTagManage.getCreatetime())) {
            ksUserTagManage.setCreatetime(DateFormat.TimeStamp2Date(ksUserTagManage.getCreatetime()));
        }
        if ("1".equals(ksUserTagManage.getTag_status())) {
            ksUserTagManage.setTag_status("动态");
            ksUserTagManage.setStarttime("无");
            ksUserTagManage.setEndtime("无");
        } else {
            ksUserTagManage.setTag_status("静态");
        }
        if ("1".equals(ksUserTagManage.getTag_type())) {
            ksUserTagManage.setTag_type("以用户id分组");
        } else if ("2".equals(ksUserTagManage.getTag_type())) {
            ksUserTagManage.setTag_type("以设备id分组");
        } else {
            ksUserTagManage.setTag_type("不限分组");
        }

        model.addAttribute("item", ksUserTagManage);
        LogObjectHolder.me().set(ksUserTagManage);
        return PREFIX + "ksUserTagManage_detail.html";
    }

    /**
     * 新增标签管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(KsUserTagManage ksUserTagManage) {

        headers.setContentType(type);

        ksUserTagManage.setStarttime(DateFormat.dateFormat(ksUserTagManage.getStarttime(), "yyyy-MM-dd'T'HH:mm:ss.SSSZ"));
        ksUserTagManage.setEndtime(DateFormat.dateFormat(ksUserTagManage.getEndtime(), "yyyy-MM-dd'T'HH:mm:ss.SSSZ"));
        ksUserTagManage.setCreatetime(DateFormat.dateFormat(null, "yyyy-MM-dd'T'HH:mm:ss.SSSZ"));
        HttpEntity entity = new HttpEntity(ksUserTagManage, headers);
        ResponseEntity<RespResult> responseEntity = restTemplate.postForEntity(requestAddr + "usertag/addtag", entity, RespResult.class);


        return responseEntity.getBody();
    }

    /**
     * 删除标签管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam String ksUserTagManageId) {
        String linshi = ksUserTagManageId.replaceAll("_", ",");
        ResponseEntity<RespResult> responseEntity = restTemplate.postForEntity(requestAddr + "usertag/deltag?usertagids=" + linshi, null, RespResult.class);
        return responseEntity.getBody();
    }

    /**
     * 修改标签管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(KsUserTagManage ksUserTagManage) {
        headers.setContentType(type);
        ksUserTagManage.setStarttime(DateFormat.dateFormat(ksUserTagManage.getStarttime(), "yyyy-MM-dd'T'HH:mm:ss.SSSZ"));
        ksUserTagManage.setEndtime(DateFormat.dateFormat(ksUserTagManage.getEndtime(), "yyyy-MM-dd'T'HH:mm:ss.SSSZ"));
        ksUserTagManage.setCreatetime(DateFormat.dateFormat(null, "yyyy-MM-dd'T'HH:mm:ss.SSSZ"));
        HttpEntity entity = new HttpEntity(ksUserTagManage, headers);
        ResponseEntity<RespResult> responseEntity = restTemplate.postForEntity(requestAddr + "usertag/updatetag", entity, RespResult.class);
        return responseEntity.getBody();
    }

}
