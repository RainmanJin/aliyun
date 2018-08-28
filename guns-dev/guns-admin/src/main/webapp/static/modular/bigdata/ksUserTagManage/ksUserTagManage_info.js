/**
 * 初始化标签管理详情对话框
 */
var KsUserTagManageInfoDlg = {
    ksUserTagManageInfoData: {}
};

/**
 * 清除数据
 */
KsUserTagManageInfoDlg.clearData = function () {
    this.ksUserTagManageInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
KsUserTagManageInfoDlg.set = function (key, val) {
    this.ksUserTagManageInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
KsUserTagManageInfoDlg.get = function (key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
KsUserTagManageInfoDlg.close = function () {
    parent.layer.close(window.parent.KsUserTagManage.layerIndex);
}

/**
 * 收集数据
 */
KsUserTagManageInfoDlg.collectData = function () {
    this
        .set('tag_id')
        .set('tag_name')
        .set('tag_value')
        .set('tag_user_count')
        .set('starttime')
        .set('endtime');
    KsUserTagManageInfoDlg.ksUserTagManageInfoData['tag_classify_id'] = $('#tag_classify_id option:selected').val();
    KsUserTagManageInfoDlg.ksUserTagManageInfoData['tag_type'] = $('#tag_type option:selected').val();
    KsUserTagManageInfoDlg.ksUserTagManageInfoData['tag_status'] = $('#tag_status option:selected').val();

}

/**
 * 提交添加
 */
KsUserTagManageInfoDlg.addSubmit = function () {

    this.clearData();
    this.collectData();
    //数据check
    {
        var tag_name = KsUserTagManageInfoDlg.ksUserTagManageInfoData['tag_name'];
        var starttime = KsUserTagManageInfoDlg.ksUserTagManageInfoData['starttime'];
        var tag_status = KsUserTagManageInfoDlg.ksUserTagManageInfoData['tag_status'];
        var endtime = KsUserTagManageInfoDlg.ksUserTagManageInfoData['endtime'];
        var endtime = KsUserTagManageInfoDlg.ksUserTagManageInfoData['endtime'];
        var tag_value = KsUserTagManageInfoDlg.ksUserTagManageInfoData['tag_value'];
        //标签名字
        if (tag_name == null || tag_name == "" || tag_name == 'undefined') {
            Feng.error("标签名称不能为空!");
            return false;
        }
        //标签名字
        if (tag_value == null || tag_value == "" || tag_value == 'undefined') {
            Feng.error("标签值不能为空!");
            return false;
        }
        if(tag_status=='2'){
            if((starttime == null || starttime == "" || starttime == 'undefined')||(endtime == null || endtime == "" || endtime == 'undefined')){
                Feng.error("静态标签开始时间和结束时间不能为空!");
                return false;
            }
        }

    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/ksUserTagManage/add", function (data) {
        if (data.status == '1') {
            Feng.success("添加成功!");
            window.parent.KsUserTagManage.table.refresh();
            KsUserTagManageInfoDlg.close();
        } else {
            Feng.error(data.msg);
        }
    }, function (data) {
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.ksUserTagManageInfoData);
    ajax.start();
}
//加载标签分类下拉菜单
KsUserTagManageInfoDlg.selectInit = function () {
    var queryData = {};
    queryData['tag_classify_name'] ="";
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/ksUserTagClassifyManage/list", function (data) {
        //加载page下拉菜单
        $("#tag_classify_id option").remove();
        for (var i = 0; i < data.length; i++) {
            $("#tag_classify_id").append("<option value='" + data[i].tag_classify_id + "'>" + data[i].tag_classify_name + "</option>");
        }
    }, function (data) {
        Feng.error("加载标签分类下拉菜单失败!" + data.responseJSON.message + "!");
    });
    ajax.set(queryData);
    ajax.start();
};

/**
 * 提交修改
 */
KsUserTagManageInfoDlg.editSubmit = function () {

    this.clearData();
    this.collectData();
    //数据check
    {
        var tag_name = KsUserTagManageInfoDlg.ksUserTagManageInfoData['tag_name'];
        var starttime = KsUserTagManageInfoDlg.ksUserTagManageInfoData['starttime'];
        var tag_status = KsUserTagManageInfoDlg.ksUserTagManageInfoData['tag_status'];
        var endtime = KsUserTagManageInfoDlg.ksUserTagManageInfoData['endtime'];
        var endtime = KsUserTagManageInfoDlg.ksUserTagManageInfoData['endtime'];
        var tag_value = KsUserTagManageInfoDlg.ksUserTagManageInfoData['tag_value'];
        var tag_user_count = KsUserTagManageInfoDlg.ksUserTagManageInfoData['tag_user_count'];
        //标签名字
        if (tag_name == null || tag_name == "" || tag_name == 'undefined') {
            Feng.error("标签名称不能为空!");
            return false;
        }
        //标签值
        if (tag_value == null || tag_value == "" || tag_value == 'undefined') {
            Feng.error("标签值不能为空!");
            return false;
        }

        if(tag_status=='2'){
            if((starttime == null || starttime == "" || starttime == 'undefined')||(endtime == null || endtime == "" || endtime == 'undefined')){
                Feng.error("静态标签开始时间和结束时间不能为空!");
                return false;
            }
        }
        //拥有人数
        var reg=/^([1-9]\d*|[0]{1,1})$/; //含0正整数
            if(!reg.test(tag_user_count)){
                Feng.error("标签所拥有人数必须是数字!");
                return false;
            }
    }
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/ksUserTagManage/update", function (data) {
        if (data.status == '1') {
            Feng.success("修改成功!");
            window.parent.KsUserTagManage.table.refresh();
            KsUserTagManageInfoDlg.close();
        } else {
            Feng.error(data.msg);
        }
    }, function (data) {
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.ksUserTagManageInfoData);
    ajax.start();
}

$(function () {
    $("#tag_status").change(function () {
        ;//选中的值
        if ($('#tag_status option:selected').val() == '1') {
            $(".hideDiv").hide();
            $(".hideDiv").hide();
            $('#starttime').val("");
            $('#endtime').val("");
        } else {
            $(".hideDiv").show();
            $(".hideDiv").show();
        }
    });
    KsUserTagManageInfoDlg.selectInit();
    if ($('#tag_id').val() != null && $('#tag_id').val() != "" && $('#tag_id').val() != 'undefined') {
        $("#tag_type").find("option[value='" + $("#tag_type_s").val() + "']").attr("selected", true);
        $("#tag_status").find("option[value='" + $("#tag_status_s").val() + "']").attr("selected", true);
        $("#tag_classify_id").find("option[value='" + $("#tag_classify_id_s").val() + "']").attr("selected", true);
    }
    $("#tag_status").change();
});
