package com.stylefeng.guns.modular.bigdata.service;

import com.stylefeng.guns.modular.system.model.LogParamGroup;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tgshi123
 * @since 2018-07-12
 */
public interface ILogParamGroupService extends IService<LogParamGroup> {
    public String getParamPreview(String paramContent,String paramGroupId,String flag);

}
