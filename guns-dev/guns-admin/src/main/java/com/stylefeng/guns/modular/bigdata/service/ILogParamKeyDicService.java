package com.stylefeng.guns.modular.bigdata.service;

import com.stylefeng.guns.modular.system.model.LogParamKeyDic;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tgshi
 * @since 2018-07-06
 */
public interface ILogParamKeyDicService extends IService<LogParamKeyDic> {
    /**
     * 根据id删除明细
     * @param id
     * @return
     */
    boolean deleteParamKeyById(int id);
}
