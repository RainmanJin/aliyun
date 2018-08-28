package com.stylefeng.guns.modular.system.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.stylefeng.guns.modular.system.model.PageLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 打点事件code表 Mapper 接口
 * </p>
 *
 * @author ygma123
 * @since 2018-04-18
 */
public interface PageLogCheckMapper extends BaseMapper<PageLog> {

    List<PageLog> getList(@Param("tableName") String tableName,
                          @Param("limitCount") int limitCount,
                          @Param("page") String page,
                          @Param("project") String project,
                          @Param("param") String param,
                          @Param("event") String event,
                          @Param("userId") String userId,
                          @Param("user_id") String user_id,
                          @Param("eventtime_start") String eventtime_start,
                          @Param("eventtime_end") String eventtime_end,
                          @Param("createtime_start") String createtime_start,
                          @Param("createtime_end") String createtime_end);
}
