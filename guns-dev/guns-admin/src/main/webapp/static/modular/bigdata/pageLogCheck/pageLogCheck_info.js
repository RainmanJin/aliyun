/**
 * 初始化打点原因关联详情对话框
 */
var PageLogCheckInfoDlg = {
    pageLogCheckInfoData : {}
};

/**
 * 清除数据
 */
PageLogCheckInfoDlg.clearData = function() {
    this.pageLogCheckInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
PageLogCheckInfoDlg.set = function(key, val) {
    this.pageLogCheckInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
PageLogCheckInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
PageLogCheckInfoDlg.close = function() {
    parent.layer.close(window.parent.PageLogCheck.layerIndex);
}

/**
 * 收集数据
 */
PageLogCheckInfoDlg.collectData = function() {
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
PageLogCheckInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/pageLogCheck/add", function(data){
        Feng.success("添加成功!");
        window.parent.PageLogCheck.table.refresh();
        PageLogCheckInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.pageLogCheckInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
PageLogCheckInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/pageLogCheck/update", function(data){
        Feng.success("修改成功!");
        window.parent.PageLogCheck.table.refresh();
        PageLogCheckInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.pageLogCheckInfoData);
    ajax.start();
}

$(function() {

});
