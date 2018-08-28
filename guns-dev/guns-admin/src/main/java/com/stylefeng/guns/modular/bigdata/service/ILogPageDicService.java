package com.stylefeng.guns.modular.bigdata.service;

import com.stylefeng.guns.modular.system.model.LogPageDic;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tgshi
 * @since 2018-07-06
 */
public interface ILogPageDicService extends IService<LogPageDic> {
     /**
      * 根据id删除明细
      * @param id
      * @return
      */
     boolean deletePageById(int id);
}
