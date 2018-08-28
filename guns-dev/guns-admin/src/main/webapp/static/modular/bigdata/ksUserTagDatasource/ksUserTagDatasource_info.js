/**
 * 初始化推送数据源详情对话框
 */
var KsUserTagDatasourceInfoDlg = {
    ksUserTagDatasourceInfoData : {}
};

/**
 * 清除数据
 */
KsUserTagDatasourceInfoDlg.clearData = function() {
    this.ksUserTagDatasourceInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
KsUserTagDatasourceInfoDlg.set = function(key, val) {
    this.ksUserTagDatasourceInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
KsUserTagDatasourceInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
KsUserTagDatasourceInfoDlg.close = function() {
    parent.layer.close(window.parent.KsUserTagDatasource.layerIndex);
}

/**
 * 收集数据
 */
KsUserTagDatasourceInfoDlg.collectData = function() {
    this
    .set('id')
    .set('ip_address')
    .set('port')
    .set('username')
    .set('password')
    .set('datasource_type')
    .set('datasource_name');
     KsUserTagDatasourceInfoDlg.ksUserTagDatasourceInfoData['datasource_type']=$('#datasource_type option:selected').val();

}

/**
 * 提交添加
 */
KsUserTagDatasourceInfoDlg.addSubmit = function() {
    this.clearData();
    this.collectData();
    var ip_address = KsUserTagDatasourceInfoDlg.ksUserTagDatasourceInfoData['ip_address'];
    var port = KsUserTagDatasourceInfoDlg.ksUserTagDatasourceInfoData['port'];
    var username = KsUserTagDatasourceInfoDlg.ksUserTagDatasourceInfoData['username'];
    var password = KsUserTagDatasourceInfoDlg.ksUserTagDatasourceInfoData['password'];
    var datasource_name = KsUserTagDatasourceInfoDlg.ksUserTagDatasourceInfoData['datasource_name'];

    if (ip_address == null || ip_address == "" || ip_address == 'undefined') {
        Feng.error("ip不能为空!");
        return false;
    }
    if (port == null || port == "" || port == 'undefined') {
        Feng.error("端口号不能为空!");
        return false;
    }
    if (username == null || username == "" || username == 'undefined') {
        Feng.error("用户名不能为空!");
        return false;
    }
    if (password == null || password == "" || password == 'undefined') {
        Feng.error("密码不能为空!");
        return false;
    }
    if (datasource_name == null || datasource_name == "" || datasource_name == 'undefined') {
        Feng.error("连接名不能为空!");
        return false;
    }
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/ksUserTagDatasource/add", function(data){
        if (data.status == '1') {
            Feng.success("添加成功!");
            window.parent.KsUserTagDatasource.table.refresh();
            KsUserTagDatasourceInfoDlg.close();
        } else {
            Feng.error(data.msg);
        }
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.ksUserTagDatasourceInfoData);
    ajax.start();
}

/**
 * 测试连通性
 */
KsUserTagDatasourceInfoDlg.test = function() {
    this.clearData();
    this.collectData();
    var ip_address = KsUserTagDatasourceInfoDlg.ksUserTagDatasourceInfoData['ip_address'];
    var port = KsUserTagDatasourceInfoDlg.ksUserTagDatasourceInfoData['port'];
    var username = KsUserTagDatasourceInfoDlg.ksUserTagDatasourceInfoData['username'];
    var password = KsUserTagDatasourceInfoDlg.ksUserTagDatasourceInfoData['password'];
    var datasource_name = KsUserTagDatasourceInfoDlg.ksUserTagDatasourceInfoData['datasource_name'];
    if (ip_address == null || ip_address == "" || ip_address == 'undefined') {
        Feng.error("ip不能为空!");
        return false;
    }
    if (port == null || port == "" || port == 'undefined') {
        Feng.error("端口号不能为空!");
        return false;
    }
    if (username == null || username == "" || username == 'undefined') {
        Feng.error("用户名不能为空!");
        return false;
    }
    if (password == null || password == "" || password == 'undefined') {
        Feng.error("密码不能为空!");
        return false;
    }
    if (datasource_name == null || datasource_name == "" || datasource_name == 'undefined') {
        Feng.error("连接名不能为空!");
        return false;
    }
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/ksUserTagDatasource/test", function(data){
        if (data.status == '1') {
            Feng.success("连接成功!");
        } else {
            Feng.error(data.msg);
        }
    },function(data){
        Feng.error("失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.ksUserTagDatasourceInfoData);
    ajax.start();
}

$(function() {

});
