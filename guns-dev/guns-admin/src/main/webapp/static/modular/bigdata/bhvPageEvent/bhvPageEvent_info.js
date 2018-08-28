/**
 * 初始化打点页面与事件关联详情对话框
 */
var BhvPageEventInfoDlg = {
    bhvPageEventInfoData : {}
};

/**
 * 清除数据
 */
BhvPageEventInfoDlg.clearData = function() {
    this.bhvPageEventInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BhvPageEventInfoDlg.set = function(key, val) {
    this.bhvPageEventInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BhvPageEventInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
BhvPageEventInfoDlg.close = function() {
    parent.layer.close(window.parent.BhvPageEvent.layerIndex);
}

/**
 * 收集数据
 */
BhvPageEventInfoDlg.collectData = function() {
    this
    .set('id')
    .set('pageId')
    .set('eventId')
    .set('label')
    .set('discribe')
    .set('createTime')
    .set('updateTime');
}

/**
 * 提交添加
 */
BhvPageEventInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/bhvPageEvent/add", function(data){
        Feng.success("添加成功!");
        window.parent.BhvPageEvent.table.refresh();
        BhvPageEventInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.bhvPageEventInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
BhvPageEventInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/bhvPageEvent/update", function(data){
        Feng.success("修改成功!");
        window.parent.BhvPageEvent.table.refresh();
        BhvPageEventInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.bhvPageEventInfoData);
    ajax.start();
}

$(function() {

});
