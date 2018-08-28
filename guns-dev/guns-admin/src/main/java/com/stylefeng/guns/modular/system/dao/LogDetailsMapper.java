package com.stylefeng.guns.modular.system.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.stylefeng.guns.modular.system.model.LogDetails;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author tgshi123
 * @since 2018-07-09
 */
public interface LogDetailsMapper extends BaseMapper<LogDetails> {
    /**
     * 加载页面下拉菜单
     *
     * @return
     */
    public List<Map<String, Object>> pageInit();

    /**
     * 加载事件下拉菜单
     *
     * @return
     */
    public List<Map<String, Object>> eventInit();

    /**
     * 加载打点类型下拉菜单
     *
     * @return
     */
    public List<Map<String, Object>> typeInit();

    /**
     * 加载打点版本下拉菜单
     *
     * @return
     */
    public List<Map<String, Object>> versionInit();

    /**
     * 加载适用平台下拉菜单
     *
     * @return
     */
    public List<Map<String, Object>> fitPlatformInit();

    /**
     * 加载参数key下拉菜单
     *
     * @return
     */
    public List<Map<String, Object>> paramKeyInit();

    /**
     * 加载参数value下拉菜单
     *
     * @return
     */
    public List<Map<String, Object>> paramValueInit();

    /**
     * 根据条件加载logdetail明细
     *
     * @param pageNum
     * @param eventNum
     * @param type
     * @param versionId
     * @param fitPlatformId
     * @param startTime
     * @param endTime
     * @param isDelete
     * @return
     */
    public List<LogDetails> selectLogDetailsList(@Param("pageNum") String pageNum, @Param("eventNum") String eventNum, @Param("type") String type, @Param("versionId") String versionId, @Param("fitPlatformId") String fitPlatformId, @Param("startTime") String startTime, @Param("endTime") String endTime, @Param("isDelete") String isDelete);

    /**
     * 根据id获取明细详细信息
     * @param id
     * @return
     */
    public LogDetails selectLogDetailsById(@Param("id") String id);

    /**
     * 更新明细
     * @param id
     * @param pageNum
     * @param eventNum
     * @param type
     * @param versionId
     * @param fitPlatformId
     * @param logContent
     * @param paramGroupId
     * @param logDescription
     * @return
     */
    public int updateById_def(@Param("id") int id,@Param("pageNum") String pageNum, @Param("eventNum") String eventNum, @Param("type") String type, @Param("versionId") String versionId, @Param("fitPlatformId") String fitPlatformId, @Param("logContent") String logContent,@Param("paramGroupId") String paramGroupId,@Param("logDescription") String logDescription);



}
