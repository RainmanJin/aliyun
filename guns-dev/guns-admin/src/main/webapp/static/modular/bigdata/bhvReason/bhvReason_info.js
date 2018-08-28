/**
 * 初始化打点原因关联详情对话框
 */
var BhvReasonInfoDlg = {
    bhvReasonInfoData : {}
};

/**
 * 清除数据
 */
BhvReasonInfoDlg.clearData = function() {
    this.bhvReasonInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BhvReasonInfoDlg.set = function(key, val) {
    this.bhvReasonInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BhvReasonInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
BhvReasonInfoDlg.close = function() {
    parent.layer.close(window.parent.BhvReason.layerIndex);
}

/**
 * 收集数据
 */
BhvReasonInfoDlg.collectData = function() {
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
BhvReasonInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/bhvReason/add", function(data){
        Feng.success("添加成功!");
        window.parent.BhvReason.table.refresh();
        BhvReasonInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.bhvReasonInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
BhvReasonInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/bhvReason/update", function(data){
        Feng.success("修改成功!");
        window.parent.BhvReason.table.refresh();
        BhvReasonInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.bhvReasonInfoData);
    ajax.start();
}

$(function() {

});
