/**
 * 初始化参数key管理详情对话框
 */
var LogParamKeyDicInfoDlg = {
    logParamKeyDicInfoData : {}
};

/**
 * 清除数据
 */
LogParamKeyDicInfoDlg.clearData = function() {
    this.logParamKeyDicInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
LogParamKeyDicInfoDlg.set = function(key, val) {
    this.logParamKeyDicInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
LogParamKeyDicInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
LogParamKeyDicInfoDlg.close = function() {
    parent.layer.close(window.parent.LogParamKeyDic.layerIndex);
}

/**
 * 收集数据
 */
LogParamKeyDicInfoDlg.collectData = function() {
    this
    .set('id')
    .set('paramKeyNum')
    .set('paramKeyCode')
    .set('paramKeyName')
    .set('isDelete');
}

/**
 * 提交添加
 */
LogParamKeyDicInfoDlg.addSubmit = function() {
    if($("#paramKeyCode").val()==""){
        Feng.error("请填写param_key值!");
        return;
    }
    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/logParamKeyDic/add", function(data){
        Feng.success("添加成功!");
        window.parent.LogParamKeyDic.table.refresh();
        LogParamKeyDicInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.logParamKeyDicInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
LogParamKeyDicInfoDlg.editSubmit = function() {
    if($("#paramKeyCode").val()==""){
        Feng.error("请填写param_key值!");
        return;
    }
    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/logParamKeyDic/update", function(data){
        Feng.success("修改成功!");
        window.parent.LogParamKeyDic.table.refresh();
        LogParamKeyDicInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.logParamKeyDicInfoData);
    ajax.start();
}

$(function() {

});
