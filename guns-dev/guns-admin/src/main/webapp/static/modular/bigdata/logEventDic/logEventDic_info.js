/**
 * 初始化事件管理详情对话框
 */
var LogEventDicInfoDlg = {
    logEventDicInfoData : {}
};

/**
 * 清除数据
 */
LogEventDicInfoDlg.clearData = function() {
    this.logEventDicInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
LogEventDicInfoDlg.set = function(key, val) {
    this.logEventDicInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
LogEventDicInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
LogEventDicInfoDlg.close = function() {
    parent.layer.close(window.parent.LogEventDic.layerIndex);
}

/**
 * 收集数据
 */
LogEventDicInfoDlg.collectData = function() {
    this
    .set('id')
    .set('eventNum')
    .set('eventCode')
    .set('eventName')
    .set('isDelete');
}

/**
 * 提交添加
 */
LogEventDicInfoDlg.addSubmit = function() {

    if($("#eventCode").val()==""){
        Feng.error("请填写事件code!");
        return;
    }
    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/logEventDic/add", function(data){
        Feng.success("添加成功!");
        window.parent.LogEventDic.table.refresh();
        LogEventDicInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.logEventDicInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
LogEventDicInfoDlg.editSubmit = function() {
    if($("#eventCode").val()==""){
        Feng.error("请填写事件code!");
        return;
    }
    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/logEventDic/update", function(data){
        Feng.success("修改成功!");
        window.parent.LogEventDic.table.refresh();
        LogEventDicInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.logEventDicInfoData);
    ajax.start();
}

$(function() {

});
