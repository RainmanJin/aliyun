package com.stylefeng.guns.modular.bigdata.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.modular.bigdata.service.ILogPageDicService;
import com.stylefeng.guns.modular.system.dao.LogPageDicMapper;
import com.stylefeng.guns.modular.system.model.LogPageDic;
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
public class LogPageDicServiceImpl extends ServiceImpl<LogPageDicMapper, LogPageDic> implements ILogPageDicService {
    @Resource
    private LogPageDicMapper logPageDicMapper;

    /**
     * 设置标记位为1，达到删除明细的目的
     * @param id page的主键
     * @return
     */
    @Override
    public boolean deletePageById(int id) {
        int rownum=logPageDicMapper.deletePage(id);
        if(rownum>0){
            return true;
        }
        return false;
    }


}
