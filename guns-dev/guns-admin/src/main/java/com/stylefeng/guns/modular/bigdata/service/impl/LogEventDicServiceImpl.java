package com.stylefeng.guns.modular.bigdata.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.modular.bigdata.service.ILogEventDicService;
import com.stylefeng.guns.modular.system.dao.LogEventDicMapper;
import com.stylefeng.guns.modular.system.model.LogEventDic;
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
public class LogEventDicServiceImpl extends ServiceImpl<LogEventDicMapper, LogEventDic> implements ILogEventDicService {
    @Resource
    private LogEventDicMapper logEventDicMapper;
    /**
     * 设置标记位为1，达到删除event明细的目的
     * @param id event主键
     * @return
     */
    @Override
    public boolean deleteEventById(int id) {
        int rownum=logEventDicMapper.deleteEvent(id);
        if(rownum>0){
            return true;
        }
        return false;
    }
}
