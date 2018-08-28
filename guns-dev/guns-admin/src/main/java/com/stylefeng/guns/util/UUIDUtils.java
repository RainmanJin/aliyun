package com.stylefeng.guns.util;

import java.util.UUID;

/**
 * Created by stg on 2018/7/6.
 */
public class UUIDUtils {
    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-", "");
    }
}
