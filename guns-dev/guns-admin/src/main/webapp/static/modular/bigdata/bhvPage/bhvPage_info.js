/**
 * 初始化打点页面Code详情对话框
 */
var BhvPageInfoDlg = {
    bhvPageInfoData : {}
};

/**
 * 清除数据
 */
BhvPageInfoDlg.clearData = function() {
    this.bhvPageInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BhvPageInfoDlg.set = function(key, val) {
    this.bhvPageInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BhvPageInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
BhvPageInfoDlg.close = function() {
    parent.layer.close(window.parent.BhvPage.layerIndex);
}

/**
 * 收集数据
 */
BhvPageInfoDlg.collectData = function() {
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
BhvPageInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/bhvPage/add", function(data){
        Feng.success("添加成功!");
        window.parent.BhvPage.table.refresh();
        BhvPageInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.bhvPageInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
BhvPageInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/bhvPage/update", function(data){
        Feng.success("修改成功!");
        window.parent.BhvPage.table.refresh();
        BhvPageInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.bhvPageInfoData);
    ajax.start();
}

$(function() {

});
