package com.stylefeng.guns.modular.bigdata.service;

import com.baomidou.mybatisplus.service.IService;
import com.stylefeng.guns.modular.system.model.LogCheckDevice;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author tgshi123
 * @since 2018-08-07
 */
public interface ILogCheckDeviceService extends IService<LogCheckDevice> {
    /**
     * 根绝用户id获取所有的设备id(线上环境)
     *
     * @return
     */
    public List<Map<String,String>> getDeviceIdListByUserId(long userId);
    /**
     * 根绝用户id获取所有的设备id(测试环境)
     *
     * @return
     */
    public List<Map<String,String>> getTestDeviceIdListByUserId(long userId);

    /**
     *
     */
    public boolean checkExist(String deviceId);
}
