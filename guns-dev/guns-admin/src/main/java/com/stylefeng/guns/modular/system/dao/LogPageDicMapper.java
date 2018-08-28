package com.stylefeng.guns.modular.system.dao;

import com.stylefeng.guns.modular.system.model.LogPageDic;
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
public interface LogPageDicMapper extends BaseMapper<LogPageDic> {
     int deletePage(@Param("id") int id);
}
