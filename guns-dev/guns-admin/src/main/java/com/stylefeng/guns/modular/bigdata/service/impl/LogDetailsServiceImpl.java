package com.stylefeng.guns.modular.bigdata.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.modular.bigdata.service.ILogDetailsService;
import com.stylefeng.guns.modular.bigdata.service.ILogParamGroupService;
import com.stylefeng.guns.modular.system.dao.DictMapper;
import com.stylefeng.guns.modular.system.dao.LogDetailsMapper;
import com.stylefeng.guns.modular.system.dao.LogPageDicMapper;
import com.stylefeng.guns.modular.system.model.Dict;
import com.stylefeng.guns.modular.system.model.LogDetails;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 打点明细服务类
 * </p>
 *
 * @author tgshi123
 * @since 2018-07-09
 */
@Service
public class LogDetailsServiceImpl extends ServiceImpl<LogDetailsMapper, LogDetails> implements ILogDetailsService {
    @Resource
    LogPageDicMapper logPageDicMapper;
    @Resource
    LogDetailsMapper logDetailsMapper;
    @Resource
    ILogParamGroupService logParamGroupService;
    @Resource
    DictMapper dictMapper;

    /**
     * 初始画首页的下拉菜单
     *
     * @return
     */
    @Override
    public Map<String, List<Map<String, Object>>> selectInit() {
        Map<String, List<Map<String, Object>>> returnMap = new HashMap<String, List<Map<String, Object>>>();
        //page下拉菜单加载
        List<Map<String, Object>> pageSelectList = logDetailsMapper.pageInit();
        //event下拉菜单加载
        List<Map<String, Object>> eventSelectList = logDetailsMapper.eventInit();
        //打点类型下拉菜单加载
        List<Map<String, Object>> typeSelectList = logDetailsMapper.typeInit();
        //打点版本下拉菜单加载
        List<Map<String, Object>> versionSelectList = logDetailsMapper.versionInit();
        //打点适用平台下拉菜单加载
        List<Map<String, Object>> fitPlatformSelectList = logDetailsMapper.fitPlatformInit();
        returnMap.put("pageList", pageSelectList);
        returnMap.put("eventList", eventSelectList);
        returnMap.put("typeList", typeSelectList);
        returnMap.put("versionList", versionSelectList);
        returnMap.put("fitPlatformList", fitPlatformSelectList);
        return returnMap;
    }

    /**
     * 参数key和参数value参数下载
     *
     * @return
     */
    @Override
    public Map<String, List<Map<String, Object>>> paramSelectInit() {
        Map<String, List<Map<String, Object>>> returnMap = new HashMap<String, List<Map<String, Object>>>();
        //paramKey下拉菜单加载
        List<Map<String, Object>> paramKeySelectList = logDetailsMapper.paramKeyInit();
        //paramValue下拉菜单加载
        List<Map<String, Object>> paramValueSelectList = logDetailsMapper.paramValueInit();
        returnMap.put("paramKeySelectList", paramKeySelectList);
        returnMap.put("paramValueSelectList", paramValueSelectList);
        return returnMap;
    }

    /**
     * @param pageNum       页面id
     * @param eventNum      eventid
     * @param type          种类
     * @param versionId     打点版本
     * @param fitPlatformId 适用平台
     * @param startTime     开始时间
     * @param endTime       结束时间
     * @param isDelete      是否已启用
     * @return
     */
    @Override
    public List<LogDetails> initList(String pageNum, String eventNum, String type, String versionId, String fitPlatformId, String startTime, String endTime, String isDelete) {
        if (isDelete == null || "".equals(isDelete)) {
            isDelete = "0";
        }
        List<LogDetails> returnList = logDetailsMapper.selectLogDetailsList(pageNum, eventNum, type, versionId, fitPlatformId, startTime, endTime, isDelete);
        //遍历list
        for (LogDetails logDetails : returnList) {

            getDicName(logDetails);
        }
        return returnList;
    }

    /**
     * @param logDetailsId 打点日志明细id
     * @return
     */
    @Override
    public String previewParam(String logDetailsId) {
        LogDetails logDetails = logDetailsMapper.selectById(logDetailsId);
        String returnStr = logParamGroupService.getParamPreview(logDetails.getLogContent(), logDetails.getParamGroupId(),"0");
//        if (returnStr != "") {
//            returnStr = returnStr.substring(0, returnStr.length() - 3);
//        }
        return returnStr;

    }

    /**
     * 根据id获取明细详细信息
     *
     * @param detailId
     * @return
     */
    @Override
    public LogDetails selectLogDetailsById(String detailId) {
        LogDetails logDetails = logDetailsMapper.selectLogDetailsById(detailId);
        getDicName(logDetails);
        return logDetails;
    }

    /**
     * 根据id更新数据
     *
     * @param logDetails
     */
    @Override
    public void updateById_def(LogDetails logDetails) {
        logDetailsMapper.updateById_def(logDetails.getId(), logDetails.getPageNum(), logDetails.getEventNum(), logDetails.getType(), logDetails.getVersionId(), logDetails.getFitPlatformId(), logDetails.getLogContent(), logDetails.getParamGroupId(), logDetails.getLogDescription());
    }

    /**
     * 更换platForm
     * @param logDetails
     */
    public void getDicName(LogDetails logDetails) {
        String fitPlatform = logDetails.getFitPlatformId();
        if (fitPlatform != null) {
            String platform = "";
            String[] fitPlatformArray = fitPlatform.split(",");
            for (String id : fitPlatformArray) {
                Dict dict = dictMapper.selectById(Integer.parseInt(id));
                platform += dict.getName() + ",";
            }
            //去掉最后一个逗号,形成展示字符串
            if (!"".equals(platform)) {
                platform = platform.substring(0, platform.length() - 1);
            }
            logDetails.setFitPlatformId(platform);

        }

    }
}
