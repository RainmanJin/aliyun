package com.stylefeng.guns.util;

import org.apache.ibatis.type.Alias;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 实体层的id主键使用String，db schema则使用long自增。本类则负责其间的可逆加密转换。
 *
 * @author jamesp
 */
@Alias("idHandler")
public class IdTypeHandler extends BaseTypeHandler<String> {
    final Logger log = LoggerFactory.getLogger(IdTypeHandler.class);

    public static void main(String[] args) {
        /**
         System.out.println("1:" + decode("9"));
         long l = 8999931455676754L;
         String e = encode(l);
         System.out.println(l + ": " + e + ", " + decode(e));
         l = 676254L;
         e = encode(l);
         System.out.println(l + ": " + e + ", " + decode(e));
         l = 676255L;
         e = encode(l);
         System.out.println(l + ": " + e + ", " + decode(e));
         l = 676262L;
         e = encode(l);
         System.out.println(l + ": " + e + ", " + decode(e));
         */
        IdTypeHandler id=new IdTypeHandler();
//       System.out.println("59leivhv:" + id.decode(279400736));
        String encode = id.encode(712);
        Long decode = id.decode(encode);
        System.out.println("208 encoe:"+encode+",decode:"+decode);
//      //  System.out.println("65191:" + getDecode(16594));
    }

    /**
     * long -> string 混淆加密
     * 如果是特殊Id(带负号)，则原值返回 by yangjian
     *
     * @param l
     * @return
     */
    public static String encode(long l) {
        if (l < 0)
            return Long.toString(l);
            //log.error("Fatal: id混淆超界");
        else {
            l = mix(l);
            return Long.toString(l, 10);
        }
    }

    /**
     * string -> long 解密
     * 如果是特殊Id(带负号)，则原值返回 by yangjian
     *
     * @param s
     * @return
     */
    public static long decode(Object s) {
        if (s instanceof Long) {
            return (Long) s;
        }
        if (s.toString().startsWith("-"))
            return Long.parseLong(s.toString());
        else
            return demix(Long.parseLong(s.toString(), 10));
    }

    /**
     * 解密混洗过的字符串 ，返回字符串
     *
     * @param @param  s
     * @param @return
     * @author 张文杰
     * @date 2015-6-5 上午10:54:25
     */
    public String decodeToString(Object s) {
        if (s instanceof Long) {
            return (String) s;
        }
        if (s.toString().startsWith("-"))
            return s.toString();
        else
            return demix(Long.parseLong(s.toString(), 10)) + "";
    }

    /**
     * 带版本的混淆
     */
    private static long mix(long l) {
        long[] vs = doMix(l);
        return setVersion(vs);
    }

    /**
     * 当前版本的mix算法. <b>注意不要数值越界成负数</b>
     */
    private static long[] doMix(long l) {
        final long version = 1L; // 当前混淆算法版本号

//        // 8进制位
//        long ret = l;
//        int digit = 0;
//        while (ret > 0) {
//            digit++;
//            ret = ret >> 3;
//        }
//        // 每5位插值, 插值位
//        int i = 0, md = (digit - 1) / 5 + 1, mix = (int) (l & ((1 << (3 * md)) - 1));
//        ret = 0;
//        while (digit > 0) {
//            ret += (((l & ((1 << 15) - 1)) + ((mix & (((1 << 3) - 1) << (3 * --md))) << (15 - 3 * md))) << i);
//            l = (l >> 15);
//            digit -= 5;
//            i += 18;
//        }
//        l = ret;

        return new long[]{version, l};
    }

    private static long demix(long l) {
        long[] vs = getVersion(l);
        l = vs[1];
//        switch ((int) vs[0]) {
//            case 1:
//                long dig = 0,
//                        ret = 0;
//                while (l > 0) {
//                    ret += ((l & ((1 << 15) - 1)) << dig);
//                    l = (l >> 18);
//                    dig += 15;
//                }
//                l = ret;
//                break;
//        }
        return l;
    }

    /**
     * 16进制下，将版本号保留在百位数
     *
     * @param vs
     */
    public static long setVersion(long[] vs) {
        // return vs[1] / 256 * 4096 + vs[0] * 256 + vs[1] % 256;
        return ((vs[1] >> 8) << 12) + (vs[0] << 8) + (vs[1] & 255);
    }


//    public    long setEncode(long vs) {
//        // return vs[1] / 256 * 4096 + vs[0] * 256 + vs[1] % 256;
//        return ((vs >> 8) << 12)  + (vs & 255);
//    }

    /**
     * 16进制下，将版本号保留在百位
     *
     * @return [version, value]
     */
    private static long[] getVersion(long l) {
        // return new long[] { (l / 256) % 16, (l / 4096) * 256 + l % 256 };
        return new long[]{(l >> 8) & 15, ((l >> 12) << 8) + (l & 255)};
    }

//    private   long getDecode(long l) {
//        // return new long[] { (l / 256) % 16, (l / 4096) * 256 + l % 256 };
//        return  ((l >> 12) << 8) + (l & 255) ;
//    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType)
            throws SQLException {
        ps.setLong(i, decode(parameter));
    }

    @Override
    public String getNullableResult(ResultSet rs, String columnName) throws SQLException {
        long l = rs.getLong(columnName);
        return encode(l);
    }

    @Override
    public String getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        long l = rs.getLong(columnIndex);
        return encode(l);
    }

    @Override
    public String getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        long l = cs.getLong(columnIndex);
        return encode(l);
    }

}

