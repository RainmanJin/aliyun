/**
 * 初始化打点日志详情对话框
 */
var BhvLogInfoDlg = {
    bhvLogInfoData : {}
};

/**
 * 清除数据
 */
BhvLogInfoDlg.clearData = function() {
    this.bhvLogInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BhvLogInfoDlg.set = function(key, val) {
    this.bhvLogInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BhvLogInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
BhvLogInfoDlg.close = function() {
    parent.layer.close(window.parent.BhvLog.layerIndex);
}

/**
 * 收集数据
 */
BhvLogInfoDlg.collectData = function() {
    this
    .set('id')
    .set('pageEventParamId')
    .set('pageName')
    .set('pageCode')
    .set('eventName')
    .set('eventCode')
    .set('paramName')
    .set('paramCode')
    .set('paramValue')
    .set('discribe')
    .set('label')
    .set('createTime')
    .set('updateTime');
}

/**
 * 提交添加
 */
BhvLogInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/bhvLog/add", function(data){
        Feng.success("添加成功!");
        window.parent.BhvLog.table.refresh();
        BhvLogInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.bhvLogInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
BhvLogInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/bhvLog/update", function(data){
        Feng.success("修改成功!");
        window.parent.BhvLog.table.refresh();
        BhvLogInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.bhvLogInfoData);
    ajax.start();
}

$(function() {

});
