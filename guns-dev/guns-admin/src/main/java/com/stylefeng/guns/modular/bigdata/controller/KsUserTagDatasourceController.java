

package com.stylefeng.guns.modular.bigdata.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.modular.system.model.KsUserTagDatasource;
import com.stylefeng.guns.modular.system.model.RespResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@Controller
@RequestMapping({"/ksUserTagDatasource"})
public class KsUserTagDatasourceController extends BaseController {
    @Value("${out-server.tag-server}")
    private String requestAddr;
    HttpHeaders headers = new HttpHeaders();
    MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
    private String PREFIX = "/bigdata/ksUserTagDatasource/";
    @Autowired
    private RestTemplate restTemplate;

    public KsUserTagDatasourceController() {
    }

    @RequestMapping({""})
    public String index() {
        return this.PREFIX + "ksUserTagDatasource.html";
    }

    @RequestMapping({"/ksUserTagDatasource_add"})
    public String ksUserTagDatasourceAdd() {
        return this.PREFIX + "ksUserTagDatasource_add.html";
    }

    @RequestMapping({"/list"})
    @ResponseBody
    public Object list() {
        this.headers.setContentType(this.type);
        KsUserTagDatasource[] responseArray = (KsUserTagDatasource[]) this.restTemplate.postForObject(requestAddr + "usertagdatasource/selalldatasource", (Object) null, KsUserTagDatasource[].class, new Object[0]);
        Object responseList = new ArrayList();
        if (responseArray != null) {
            responseList = Arrays.asList(responseArray);
            Iterator var3 = ((List) responseList).iterator();

            while (var3.hasNext()) {
                KsUserTagDatasource ksUserTagDatasource = (KsUserTagDatasource) var3.next();
                if ("1".equals(ksUserTagDatasource.getDatasource_type())) {
                    ksUserTagDatasource.setDatasource_type("mysql");
                } else {
                    ksUserTagDatasource.setDatasource_type("redise");
                }

            }
        }

        return responseList;
    }

    @RequestMapping({"/add"})
    @ResponseBody
    public Object add(KsUserTagDatasource ksUserTagDatasource) {
        this.headers.setContentType(this.type);
        HttpEntity entity = new HttpEntity(ksUserTagDatasource, this.headers);
        ResponseEntity responseEntity = this.restTemplate.postForEntity(requestAddr + "usertagdatasource/adddatasource", entity, RespResult.class, new Object[0]);
        return responseEntity.getBody();
    }

    @RequestMapping({"/delete"})
    @ResponseBody
    public Object delete(@RequestParam String ksUserTagDatasourceId) {
        String linshi = ksUserTagDatasourceId.replaceAll("_", ",");
        ResponseEntity responseEntity = this.restTemplate.postForEntity(requestAddr + "usertagdatasource/deteledatasource?datasourceids=" + linshi, (Object) null, RespResult.class, new Object[0]);
        return responseEntity.getBody();
    }

    @RequestMapping({"/test"})
    @ResponseBody
    public Object update(KsUserTagDatasource ksUserTagDatasource) {
        this.headers.setContentType(this.type);
        HttpEntity entity = new HttpEntity(ksUserTagDatasource, this.headers);
        ResponseEntity responseEntity = this.restTemplate.postForEntity(requestAddr + "usertagdatasource/testdatasource", entity, RespResult.class, new Object[0]);
        return responseEntity.getBody();
    }


}
