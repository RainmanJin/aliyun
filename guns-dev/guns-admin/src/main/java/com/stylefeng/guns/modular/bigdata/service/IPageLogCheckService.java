package com.stylefeng.guns.modular.bigdata.service;

import com.baomidou.mybatisplus.service.IService;
import com.stylefeng.guns.modular.system.model.PageLog;

import java.util.List;

/**
 * <p>
 * 打点事件code表 服务类
 * </p>
 *
 * @author ygma123
 * @since 2018-04-18
 */
public interface IPageLogCheckService extends IService<PageLog> {

    List<PageLog> getList(String tableName, Integer limitCount, String page,
                          String project, String param, String event,
                          String userId,String user_id,String eventtime_start,String eventtime_end,String createtime_start,String createtime_end);

    List<PageLog> getList(String tableName, Integer limitCount, String page,
                          String project, String param, String event,
                          String userId, String flag,String user_id,String eventtime_start,String eventtime_end,String createtime_start,String createtime_end);

}
