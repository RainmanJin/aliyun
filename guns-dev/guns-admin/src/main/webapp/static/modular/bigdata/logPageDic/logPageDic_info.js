/**
 * 初始化页面管理详情对话框
 */
var LogPageDicInfoDlg = {
    logPageDicInfoData : {}
};

/**
 * 清除数据
 */
LogPageDicInfoDlg.clearData = function() {
    this.logPageDicInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
LogPageDicInfoDlg.set = function(key, val) {
    this.logPageDicInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
LogPageDicInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
LogPageDicInfoDlg.close = function() {
    parent.layer.close(window.parent.LogPageDic.layerIndex);
}

/**
 * 收集数据
 */
LogPageDicInfoDlg.collectData = function() {
    this
    .set('id')
    .set('pageNum')
    .set('pageCode')
    .set('pageName')
    .set('isDelete');
}

/**
 * 提交添加
 */
LogPageDicInfoDlg.addSubmit = function() {
    if($("#pageCode").val()==""){
        Feng.error("请填写页面字典code!");
        return;
    }
    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/logPageDic/add", function(data){
        Feng.success("添加成功!");
        window.parent.LogPageDic.table.refresh();
        LogPageDicInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.logPageDicInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
LogPageDicInfoDlg.editSubmit = function() {
    if($("#pageCode").val()==""){
        Feng.error("请填写页面字典code!");
        return;
    }
    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/logPageDic/update", function(data){
        Feng.success("修改成功!");
        window.parent.LogPageDic.table.refresh();
        LogPageDicInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.logPageDicInfoData);
    ajax.start();
}

$(function() {

});
