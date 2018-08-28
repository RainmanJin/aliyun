/**
 * 初始化打点页面事件参数关联详情对话框
 */
var BhvPageEventParamInfoDlg = {
    bhvPageEventParamInfoData : {}
};

/**
 * 清除数据
 */
BhvPageEventParamInfoDlg.clearData = function() {
    this.bhvPageEventParamInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BhvPageEventParamInfoDlg.set = function(key, val) {
    this.bhvPageEventParamInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BhvPageEventParamInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
BhvPageEventParamInfoDlg.close = function() {
    parent.layer.close(window.parent.BhvPageEventParam.layerIndex);
}

/**
 * 收集数据
 */
BhvPageEventParamInfoDlg.collectData = function() {
    this
    .set('id')
    .set('pageEventId')
    .set('paramId')
    .set('label')
    .set('discribe')
    .set('createTime')
    .set('updateTime');
}

/**
 * 提交添加
 */
BhvPageEventParamInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/bhvPageEventParam/add", function(data){
        Feng.success("添加成功!");
        window.parent.BhvPageEventParam.table.refresh();
        BhvPageEventParamInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.bhvPageEventParamInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
BhvPageEventParamInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/bhvPageEventParam/update", function(data){
        Feng.success("修改成功!");
        window.parent.BhvPageEventParam.table.refresh();
        BhvPageEventParamInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.bhvPageEventParamInfoData);
    ajax.start();
}

$(function() {

});
