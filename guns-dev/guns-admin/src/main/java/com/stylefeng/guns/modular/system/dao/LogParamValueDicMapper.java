package com.stylefeng.guns.modular.system.dao;

import com.stylefeng.guns.modular.system.model.LogParamValueDic;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author tgshi123
 * @since 2018-07-06
 */
public interface LogParamValueDicMapper extends BaseMapper<LogParamValueDic> {
    /**
     * 跟据id删除明细（设置isdelete='1'）
     * @param id
     * @return
     */
    int deleteParamValue(@Param("id") int id);
}
