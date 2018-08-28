package com.stylefeng.guns.modular.bigdata.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.modular.bigdata.service.ILogParamKeyDicService;
import com.stylefeng.guns.modular.system.dao.LogParamKeyDicMapper;
import com.stylefeng.guns.modular.system.model.LogParamKeyDic;
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
public class LogParamKeyDicServiceImpl extends ServiceImpl<LogParamKeyDicMapper, LogParamKeyDic> implements ILogParamKeyDicService {
    @Resource
    private LogParamKeyDicMapper logParamKeyDicMapper;
    /**
     * 设置标记位为1，达到删除ParamKey明细的目的
     * @param id event主键
     * @return
     */
    @Override
    public boolean deleteParamKeyById(int id) {
        int rownum=logParamKeyDicMapper.deleteParamKey(id);
        if(rownum>0){
            return true;
        }
        return false;
    }
}
