package com.stylefeng.guns.modular.bigdata.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.modular.bigdata.service.ILogParamGroupService;
import com.stylefeng.guns.modular.system.dao.LogParamGroupMapper;
import com.stylefeng.guns.modular.system.model.LogParamGroup;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author tgshi123
 * @since 2018-07-12
 */
@Service
public class LogParamGroupServiceImpl extends ServiceImpl<LogParamGroupMapper, LogParamGroup> implements ILogParamGroupService {
    @Resource
    private LogParamGroupMapper logParamGroupMapper;

    /**
     * 预览拼成的json串
     *
     * @param paramContent 拼成的id串
     * @param paramGroupId 参数组拼成的串
     * @param flag         0:打点预览页面的请求 1：其他页面的请求
     * @return
     */
    @Override
    public String getParamPreview(String paramContent, String paramGroupId, String flag) {
        String returnStr = "";
        if (paramGroupId != null && !"".equals(paramGroupId)) {
            String groupParam = this.getGroupParam(paramGroupId);
            if (!"".equals(groupParam)) {
//                returnStr += "公有参数:<br/>";
                returnStr += "<label class='control-label'>公有参数:</label><br/>";
                returnStr += "<table class=\"table table-bordered\"><tr><th>序号</th><th>参数key</th><th>参数value</th><th>参数说明</th></tr>" + groupParam + "</table>";
            }
        }
        if (paramContent != null && !"".equals(paramContent)) {
            String privateParam = this.getPrivateParam(paramContent, new int[]{0});
            if (!"".equals(privateParam)) {

//                returnStr += "私有参数:<br/>";
                if ("0".equals(flag)) {
                    returnStr += "<label class='control-label'>私有参数:</label><br/>";
                }
                returnStr += "<table class=\"table table-bordered\"><tr><th>序号</th><th>参数key</th><th>参数value</th><th>参数说明</th></tr>" + privateParam + "</table>";
            }
        }
        return returnStr;
    }

    /**
     * 获取参数组参数
     *
     * @return
     */
    public String getGroupParam(String paramGroupId) {
        String returnStr = "";
        int[] i = {0};
        //拆分字符串
        //参数组
        if (paramGroupId != null && !"".equals(paramGroupId)) {
            String[] groupId = paramGroupId.split(",");
            for (String id : groupId) {
                LogParamGroup logParamGroup = logParamGroupMapper.selectById(id);
                //递归
                if (logParamGroup != null) {
                    returnStr += this.getPrivateParam(logParamGroup.getParamGroupContent(), i);
                }
            }

        }
        return returnStr;
    }

    /**
     * 获取私有参数
     *
     * @return
     */
    public String getPrivateParam(String paramContent, int[] j) {
        String returnStr = "";
        //拆分字符串
        if (paramContent != null && !"".equals(paramContent)) {
            String[] coupArray = paramContent.split("-");
            for (int i = 0; i < coupArray.length; i++) {
                String coup[] = coupArray[i].split("_");
                String key_id = coup[0];
                String value_id = coup[1];
                //获得key:value
//                String tempstr = logParamGroupMapper.getParamKeyAndValue(Integer.parseInt(key_id), Integer.parseInt(value_id));
                Map<String, Object> tempMap = logParamGroupMapper.getParamKeyAndValue(Integer.parseInt(key_id), Integer.parseInt(value_id));
                j[0] = j[0] + 1;
                returnStr += "<tr><td>" + j[0] + "</td><td>" + tempMap.get("param_key_code") + "</td><td>" + tempMap.get("param_value_code") + "</td><td>" + tempMap.get("param_value_name") + "</td></tr>";
//                returnStr += ((i + 1) + "." + tempstr + "<br/>");
            }
        }
//        if (returnStr != "") {
//            returnStr = returnStr.substring(0, returnStr.length() - 5);
//        }
        return returnStr;
    }
}
