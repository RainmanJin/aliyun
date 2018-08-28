package com.stylefeng.guns.modular.system.model;

import com.google.common.base.Objects;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author stylefeng123
 * @since 2018-08-13
 */
public class KsUserTagDatasource {

    @ApiModelProperty(value = "数据源id", name = "id", example = "1")
    private String id;

    @ApiModelProperty(value = "ip地址", name = "ip_address", example = "rm-2ze30r012o24384ge137.mysql.rds.aliyuncs.com")
    private String ip_address;

    @ApiModelProperty(value = "端口号", name = "port", example = "3306")
    private String port;

    @ApiModelProperty(value = "用户名", name = "username", example = "bdp")
    private String username;

    @ApiModelProperty(value = "密码", name = "password", example = "******")
    private String password;

    @ApiModelProperty(value = "数据源类型 1: mysql 2:redise", name = "datasource_type", example = "1")
    private String datasource_type;  //数据源类型 1: mysql 2:redise

    @ApiModelProperty(value = "datasource_name", name = "数据源名称", example = "bdp mysql数据库")
    private String datasource_name;


    public String getIp_address() {
        return ip_address;
    }

    public void setIp_address(String ip_address) {
        this.ip_address = ip_address;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDatasource_type() {
        return datasource_type;
    }

    public void setDatasource_type(String datasource_type) {
        this.datasource_type = datasource_type;
    }

    public String getDatasource_name() {
        return datasource_name;
    }

    public void setDatasource_name(String datasource_name) {
        this.datasource_name = datasource_name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    @Override
    public String toString() {

        return Objects.toStringHelper(this)
                .add("id", id)
                .add("ip_address", ip_address)
                .add("port", port)
                .add("username", username)
                .add("password", password)
                .add("datasource_type", datasource_type)
                .add("datasource_name", datasource_name)
                .toString();
    }
}
