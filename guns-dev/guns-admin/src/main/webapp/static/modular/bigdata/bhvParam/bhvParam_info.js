/**
 * 初始化打点参数关联详情对话框
 */
var BhvParamInfoDlg = {
    bhvParamInfoData : {}
};

/**
 * 清除数据
 */
BhvParamInfoDlg.clearData = function() {
    this.bhvParamInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BhvParamInfoDlg.set = function(key, val) {
    this.bhvParamInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BhvParamInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
BhvParamInfoDlg.close = function() {
    parent.layer.close(window.parent.BhvParam.layerIndex);
}

/**
 * 收集数据
 */
BhvParamInfoDlg.collectData = function() {
    this
    .set('id')
    .set('name')
    .set('code')
    .set('value')
    .set('discribe')
    .set('createTime')
    .set('updateTime');
}

/**
 * 提交添加
 */
BhvParamInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/bhvParam/add", function(data){
        Feng.success("添加成功!");
        window.parent.BhvParam.table.refresh();
        BhvParamInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.bhvParamInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
BhvParamInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/bhvParam/update", function(data){
        Feng.success("修改成功!");
        window.parent.BhvParam.table.refresh();
        BhvParamInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.bhvParamInfoData);
    ajax.start();
}

$(function() {

});
