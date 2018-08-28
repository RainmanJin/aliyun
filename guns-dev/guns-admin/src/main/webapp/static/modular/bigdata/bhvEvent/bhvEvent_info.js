/**
 * 初始化打点事件code详情对话框
 */
var BhvEventInfoDlg = {
    bhvEventInfoData : {}
};

/**
 * 清除数据
 */
BhvEventInfoDlg.clearData = function() {
    this.bhvEventInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BhvEventInfoDlg.set = function(key, val) {
    this.bhvEventInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BhvEventInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
BhvEventInfoDlg.close = function() {
    parent.layer.close(window.parent.BhvEvent.layerIndex);
}

/**
 * 收集数据
 */
BhvEventInfoDlg.collectData = function() {
    this
    .set('id')
    .set('name')
    .set('code')
    .set('discribe')
    .set('createTime')
    .set('updateTime');
}

/**
 * 提交添加
 */
BhvEventInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/bhvEvent/add", function(data){
        Feng.success("添加成功!");
        window.parent.BhvEvent.table.refresh();
        BhvEventInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.bhvEventInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
BhvEventInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/bhvEvent/update", function(data){
        Feng.success("修改成功!");
        window.parent.BhvEvent.table.refresh();
        BhvEventInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.bhvEventInfoData);
    ajax.start();
}

$(function() {

});
