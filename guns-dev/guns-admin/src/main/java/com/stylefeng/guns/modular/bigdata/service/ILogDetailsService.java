package com.stylefeng.guns.modular.bigdata.service;

import com.baomidou.mybatisplus.service.IService;
import com.stylefeng.guns.modular.system.model.LogDetails;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tgshi123
 * @since 2018-07-09
 */
public interface ILogDetailsService extends IService<LogDetails> {
    /**
     * 首页下拉菜单填充数据
     * @return
     */
    public Map<String,List<Map<String, Object>>> selectInit();
    /**
     * 添加页下拉菜单填充数据
     * @return
     */
    public Map<String,List<Map<String, Object>>> paramSelectInit();

    /**
     *
     * @param pageNum 页面id
     * @param eventNum eventid
     * @param type 种类
     * @param versionId 打点版本
     * @param fitPlatformId 适用平台
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param isDelete 是否已启用
     * @return
     */
    public List<LogDetails> initList(String pageNum, String eventNum, String type, String versionId, String fitPlatformId, String startTime, String endTime,String isDelete);

    /**
     * 预览页面
     * @param logDetailsId 打点日志明细id
     * @return 参数明细
     */
    public String previewParam(String logDetailsId);
    /**
     * 根据id获取明细详细信息
     * @param id
     * @return
     */
    public LogDetails selectLogDetailsById(String id);

    /**
     * 根据id更新数据
     * @param logDetails
     */
    public void updateById_def(LogDetails logDetails);

}
