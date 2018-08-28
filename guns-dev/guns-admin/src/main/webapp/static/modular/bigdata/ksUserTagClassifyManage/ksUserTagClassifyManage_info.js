/**
 * 初始化标签分类详情对话框
 */
var KsUserTagClassifyManageInfoDlg = {
    ksUserTagClassifyManageInfoData : {}
};

/**
 * 清除数据
 */
KsUserTagClassifyManageInfoDlg.clearData = function() {
    this.ksUserTagClassifyManageInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
KsUserTagClassifyManageInfoDlg.set = function(key, val) {
    this.ksUserTagClassifyManageInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
KsUserTagClassifyManageInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
KsUserTagClassifyManageInfoDlg.close = function() {
    parent.layer.close(window.parent.KsUserTagClassifyManage.layerIndex);
}

/**
 * 收集数据
 */
KsUserTagClassifyManageInfoDlg.collectData = function() {
    this
    .set('tag_classify_id')
    .set('tag_classify_name')
        .set('tag_classify_describe');
}

/**
 * 提交添加
 */
KsUserTagClassifyManageInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();
    if($("#tag_classify_name").val()==null||$("#tag_classify_name").val()==''||$("#tag_classify_name").val()=='undefined'){
        Feng.error("分类名称不允许为空");
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/ksUserTagClassifyManage/add", function(data){
        if (data.status == '1') {
            Feng.success("添加成功!");
            window.parent.KsUserTagClassifyManage.table.refresh();
            KsUserTagClassifyManageInfoDlg.close();
        } else {
            Feng.error(data.msg);
        }
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.ksUserTagClassifyManageInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
KsUserTagClassifyManageInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();
    if($("#tag_classify_name").val()==null||$("#tag_classify_name").val()==''||$("#tag_classify_name").val()=='undefined'){
        Feng.error("分类名称不允许为空");
    }
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/ksUserTagClassifyManage/update", function(data){
        if (data.status == '1') {
            Feng.success("修改成功!");
            window.parent.KsUserTagClassifyManage.table.refresh();
            KsUserTagClassifyManageInfoDlg.close();
        } else {
            Feng.error(data.msg);
        }
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.ksUserTagClassifyManageInfoData);
    ajax.start();
}

$(function() {

});
