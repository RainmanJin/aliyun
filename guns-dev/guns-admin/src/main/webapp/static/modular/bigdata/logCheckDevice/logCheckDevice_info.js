/**
 * 初始化用户设备号详情对话框
 */
var LogCheckDeviceInfoDlg = {
    logCheckDeviceInfoData: {}
};

/**
 * 清除数据
 */
LogCheckDeviceInfoDlg.clearData = function () {
    this.logCheckDeviceInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
LogCheckDeviceInfoDlg.set = function (key, val) {
    this.logCheckDeviceInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
LogCheckDeviceInfoDlg.get = function (key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
LogCheckDeviceInfoDlg.close = function () {
    parent.layer.close(window.parent.LogCheckDevice.layerIndex);
}
/**
 * 快速查询
 */
LogCheckDeviceInfoDlg.fastSelect = function () {
    var platform = $('#platform option:selected').val();
    var appid = $('#appid').val();
    var param = {'platform': platform, 'appid': appid};
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/logCheckDevice/fastSelect", function (data) {
        $("#deviceIdTable tr").remove();
        $("#deviceIdTable").prepend("<tr><th class='text-center'>用户ID</th><th class='text-center'>设备ID</th></tr>");//添加第一个option值
        for (var i = 0; i < data.length; i++) {
            $("#deviceIdTable").append("<tr><td  class='text-center'>" + data[i].userid + "</td><td  class='text-center'>" + data[i].deviceid + "</td></tr>");
        }

    }, function (data) {
        Feng.error("查询失败!" + data.responseJSON.message + "!");
    });
    ajax.set(param);
    ajax.start();

}

/**
 * 收集数据
 */
LogCheckDeviceInfoDlg.collectData = function () {
    this
        .set('id')
        .set('deviceId')
        .set('userName');
     LogCheckDeviceInfoDlg.logCheckDeviceInfoData['idType']=$('#idType option:selected').val();

}

/**
 * 提交添加
 */
LogCheckDeviceInfoDlg.addSubmit = function () {

    this.clearData();
    this.collectData();
    var deviceId = this.logCheckDeviceInfoData['deviceId'];
    var userName = this.logCheckDeviceInfoData['userName'];
    //设备ID
    if (deviceId == null || deviceId == "" || deviceId == 'undefined') {
        Feng.error("ID不能为空!");
        return false;
    }
    //设备ID
    if (userName == null || userName == "" || userName == 'undefined') {
        Feng.error("用户名不能为空!");
        return false;
    }
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/logCheckDevice/add", function (data) {
        if (data.code == 200) {
            Feng.success("添加成功!");
            window.parent.LogCheckDevice.table.refresh();
            LogCheckDeviceInfoDlg.close();
        } else {
            Feng.error(data.message);
        }
    }, function (data) {
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.logCheckDeviceInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
LogCheckDeviceInfoDlg.editSubmit = function () {

    this.clearData();
    this.collectData();
    var deviceId = this.logCheckDeviceInfoData['deviceId'];
    //设备ID
    if (deviceId == null || deviceId == "" || deviceId == 'undefined') {
        Feng.error("设备ID不能为空!");
        return false;
    }
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/logCheckDevice/update", function (data) {
        Feng.success("修改成功!");
        window.parent.LogCheckDevice.table.refresh();
        LogCheckDeviceInfoDlg.close();
    }, function (data) {
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.logCheckDeviceInfoData);
    ajax.start();
}

$(function () {

});
