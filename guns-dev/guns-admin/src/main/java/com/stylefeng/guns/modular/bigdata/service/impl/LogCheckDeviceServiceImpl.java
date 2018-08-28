package com.stylefeng.guns.modular.bigdata.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.core.common.constant.DatasourceEnum;
import com.stylefeng.guns.core.mutidatasource.annotion.DataSource;
import com.stylefeng.guns.modular.bigdata.service.ILogCheckDeviceService;
import com.stylefeng.guns.modular.system.dao.KsDeviceUserMapper;
import com.stylefeng.guns.modular.system.dao.LogCheckDeviceMapper;
import com.stylefeng.guns.modular.system.model.KsDeviceUser;
import com.stylefeng.guns.modular.system.model.LogCheckDevice;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author tgshi123
 * @since 2018-08-07
 */
@Service
public class LogCheckDeviceServiceImpl extends ServiceImpl<LogCheckDeviceMapper, LogCheckDevice> implements ILogCheckDeviceService {
    @Resource
    KsDeviceUserMapper ksDeviceUserMapper;
    @Resource
    LogCheckDeviceMapper logCheckDeviceMapper;

    /**
     * 根据userid获取设备id（生产环境）
     *
     * @param userId
     * @return
     */
    @Override
    @DataSource(name = DatasourceEnum.PROD_DATA_SOURCE)
    public List<Map<String,String>> getDeviceIdListByUserId(long userId) {
        List<Map<String,String>> deviceIdlist = new ArrayList<Map<String,String>>();
        EntityWrapper<KsDeviceUser> en = new EntityWrapper<KsDeviceUser>();
        KsDeviceUser ku = new KsDeviceUser();
        ku.setUser_id((int) userId);
        en.setEntity(ku);
        List<KsDeviceUser> get = ksDeviceUserMapper.selectList(en);
        for (KsDeviceUser ksDeviceUser : get) {
            Map<String,String> map=new HashMap<String,String>();
            map.put("userid",userId+"");
            map.put("deviceid",ksDeviceUser.getDevice_id());
            deviceIdlist.add(map);
        }
        return deviceIdlist;
    }
    /**
     * 根据userid获取设备id（生产环境）
     *
     * @param userId
     * @return
     */
    @Override
    @DataSource(name = DatasourceEnum.TEST_DATA_SOURCE)
    public List<Map<String,String>> getTestDeviceIdListByUserId(long userId) {
        List<Map<String,String>> deviceIdlist = new ArrayList<Map<String,String>>();
        EntityWrapper<KsDeviceUser> en = new EntityWrapper<KsDeviceUser>();
        KsDeviceUser ku = new KsDeviceUser();
        ku.setUser_id((int) userId);
        en.setEntity(ku);
        List<KsDeviceUser> get = ksDeviceUserMapper.selectList(en);
        for (KsDeviceUser ksDeviceUser : get) {
            Map<String,String> map=new HashMap<String,String>();
            map.put("userid",userId+"");
            map.put("deviceid",ksDeviceUser.getDevice_id());
            deviceIdlist.add(map);
        }
        return deviceIdlist;
    }

    @Override
    public boolean checkExist(String deviceId) {
        EntityWrapper<LogCheckDevice> en = new EntityWrapper<LogCheckDevice>();
        LogCheckDevice lcd = new LogCheckDevice();
        lcd.setDeviceId(deviceId);
        en.setEntity(lcd);
        List<LogCheckDevice> returnList = logCheckDeviceMapper.selectList(en);
        //该设备id已经存在
        if (returnList != null && returnList.size() > 0) {
            return false;
        }
        //设备id不存在
        return true;
    }
}
