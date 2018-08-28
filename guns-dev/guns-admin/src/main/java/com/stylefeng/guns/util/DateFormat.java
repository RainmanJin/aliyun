package com.stylefeng.guns.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by stg on 2018/7/30.
 */
public class DateFormat {
    /**
     * 转换时间
     *
     * @param dateStr   需要转换的参数字符串
     * @param formatStr 转换的格式
     * @return
     */
    public static String dateFormat(String dateStr, String formatStr) {
        SimpleDateFormat formatterTmp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String returnDateString = "";
        try {
            Date data_temp =new Date();
            //先把2018-01-01 00:00:00转换成 yyyy-MM-dd HH:mm:ss 时间
            if(dateStr!=null && !"".equals(dateStr)){
                 data_temp = formatterTmp.parse(dateStr);
            }

            SimpleDateFormat formatter = new SimpleDateFormat(formatStr);
            //最终要返回的时间
            returnDateString = formatter.format(data_temp);
        } catch (ParseException e1) {
            System.out.println("更新失败");
            e1.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


        return returnDateString;
    }

    //时间戳转成时间
    public static String TimeStamp2Date(String timestampString){
        Long timestamp = Long.parseLong(timestampString);
        String date = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date(timestamp));
        return date;
    }

}
