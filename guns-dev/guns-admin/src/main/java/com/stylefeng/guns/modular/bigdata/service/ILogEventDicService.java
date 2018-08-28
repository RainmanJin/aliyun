package com.stylefeng.guns.modular.bigdata.service;

import com.stylefeng.guns.modular.system.model.LogEventDic;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tgshi123
 * @since 2018-07-06
 */
public interface ILogEventDicService extends IService<LogEventDic> {
    /**
     * 根据id删除明细
     * @param id
     * @return
     */
    boolean deleteEventById(int id);
}
