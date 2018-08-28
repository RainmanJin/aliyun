package com.stylefeng.guns.modular.system.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.stylefeng.guns.modular.system.model.LogParamGroup;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author tgshi123
 * @since 2018-07-12
 */
public interface LogParamGroupMapper extends BaseMapper<LogParamGroup> {
    /**
     * 获取参数对的keycode:valuecode
     *
     * @param keyid
     * @param valueid
     * @return
     */
//    public String getParamKeyAndValue(@Param("keyid") int keyid, @Param("valueid") int valueid);
    public Map<String, Object> getParamKeyAndValue(@Param("keyid") int keyid, @Param("valueid") int valueid);
}
