package com.stylefeng.guns.modular.bigdata.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.core.common.constant.DatasourceEnum;
import com.stylefeng.guns.core.mutidatasource.annotion.DataSource;
import com.stylefeng.guns.modular.bigdata.service.IPageLogCheckService;
import com.stylefeng.guns.modular.system.dao.PageLogCheckMapper;
import com.stylefeng.guns.modular.system.model.PageLog;
import com.stylefeng.guns.util.JsonUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 打点事件code表 服务实现类
 * </p>
 *
 * @author ygma123
 * @since 2018-04-18
 */
@Service
public class PageLogCheckServiceImpl extends ServiceImpl<PageLogCheckMapper, PageLog> implements IPageLogCheckService {

    @Resource
    private PageLogCheckMapper pageLogCheckMapper;

    @Override
    @DataSource(name = DatasourceEnum.DEV_DATA_SOURCE)
    public List<PageLog> getList(String tableName, Integer limitCount, String page,
                                 String project, String param, String event,
                                 String userId ,String deviceIdArray,String eventtime_start,String eventtime_end,String createtime_start,String createtime_end) {
        if (tableName == null || "".equals(tableName)) {
            tableName = "wx_pagelog";
        }
        if (limitCount == null || limitCount <= 0) {
            limitCount = 200;
        }
        List<PageLog> data = pageLogCheckMapper.getList(tableName, limitCount, page,
                project, param, event, userId,deviceIdArray,eventtime_start,eventtime_end,createtime_start,createtime_end);
        doData(data);
        return data;
    }

    @Override
    @DataSource(name = DatasourceEnum.PROD_LOG_CHECK_DATA_SOURCE)
    public List<PageLog> getList(String tableName, Integer limitCount, String page, String project, String param, String event, String userId, String flag,String deviceIdArray,String eventtime_start,String eventtime_end,String createtime_start,String createtime_end) {
        if (tableName == null || "".equals(tableName)) {
            tableName = "wx_pagelog";
        }
        if (limitCount == null || limitCount <= 0) {
            limitCount = 200;
        }
        List<PageLog> data = pageLogCheckMapper.getList(tableName, limitCount, page,
                project, param, event, userId,deviceIdArray,eventtime_start,eventtime_end,createtime_start,createtime_end);
        doData(data);
        return data;
    }

    /**
     * 格式化param的json
     *
     * @param data
     */
    private void doData(List<PageLog> data) {
        data.stream().forEach(d -> {
            String param = d.getParam();
            d.setParam(JsonUtil.JsonFormat(param));
        });
    }

}
