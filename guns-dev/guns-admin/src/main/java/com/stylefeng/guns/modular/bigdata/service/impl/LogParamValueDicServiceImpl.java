package com.stylefeng.guns.modular.bigdata.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.modular.bigdata.service.ILogParamValueDicService;
import com.stylefeng.guns.modular.system.dao.LogParamValueDicMapper;
import com.stylefeng.guns.modular.system.model.LogParamValueDic;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tgshi123
 * @since 2018-07-06
 */
@Service
public class LogParamValueDicServiceImpl extends ServiceImpl<LogParamValueDicMapper, LogParamValueDic> implements ILogParamValueDicService {
    @Resource
    private LogParamValueDicMapper logParamValueDicMapper;
    /**
     * 设置标记位为1，达到删除ParamValue明细的目的
     * @param id event主键
     * @return
     */
    @Override
    public boolean deleteParamValueById(int id) {
        int rownum=logParamValueDicMapper.deleteParamValue(id);
        if(rownum>0){
            return true;
        }
        return false;
    }
}
