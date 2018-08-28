/**
 * 初始化参数value管理详情对话框
 */
var LogParamValueDicInfoDlg = {
    logParamValueDicInfoData : {}
};

/**
 * 清除数据
 */
LogParamValueDicInfoDlg.clearData = function() {
    this.logParamValueDicInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
LogParamValueDicInfoDlg.set = function(key, val) {
    this.logParamValueDicInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
LogParamValueDicInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
LogParamValueDicInfoDlg.close = function() {
    parent.layer.close(window.parent.LogParamValueDic.layerIndex);
}

/**
 * 收集数据
 */
LogParamValueDicInfoDlg.collectData = function() {
    this
    .set('id')
    .set('paramValueNum')
    .set('paramValueCode')
    .set('paramValueName')
    .set('isDelete');
}

/**
 * 提交添加
 */
LogParamValueDicInfoDlg.addSubmit = function() {
    if($("#paramValueCode").val()==""){
        Feng.error("请填写param_value值!");
        return;
    }
    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/logParamValueDic/add", function(data){
        Feng.success("添加成功!");
        window.parent.LogParamValueDic.table.refresh();
        LogParamValueDicInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.logParamValueDicInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
LogParamValueDicInfoDlg.editSubmit = function() {
    if($("#paramValueCode").val()==""){
        Feng.error("请填写param_value值!");
        return;
    }
    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/logParamValueDic/update", function(data){
        Feng.success("修改成功!");
        window.parent.LogParamValueDic.table.refresh();
        LogParamValueDicInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.logParamValueDicInfoData);
    ajax.start();
}

$(function() {

});
