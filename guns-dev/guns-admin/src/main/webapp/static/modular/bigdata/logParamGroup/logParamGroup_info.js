/**
 * 初始化参数组管理详情对话框
 */
var LogParamGroupInfoDlg = {
    logParamGroupInfoData: {},
    itemTemplate: $("#itemTemplate").html(),
    count: $("#itemSize").val()
};

/**
 * 清除数据
 */
LogParamGroupInfoDlg.clearData = function () {
    this.logParamGroupInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
LogParamGroupInfoDlg.set = function (key, val) {
    this.logParamGroupInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
LogParamGroupInfoDlg.get = function (key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
LogParamGroupInfoDlg.close = function () {
    parent.layer.close(window.parent.LogParamGroup.layerIndex);
}

/**
 * 收集数据
 */
LogParamGroupInfoDlg.collectData = function () {
    this
        .set('id')
        .set('groupName')
        .set('groupDescription')
    // .set('paramGroupContent')
    // .set('isDelete');
    this.logParamGroupInfoData['paramGroupContent'] = LogParamGroupInfoDlg.getParamDate();
}
/**
 * 获取私有参数拼成值
 */
LogParamGroupInfoDlg.getParamDate = function () {
    var data = "";
    var keyArray = new Array();
    var valueArray = new Array();
    var i = 0;
    $("div[name='logDetailItem']").each(function () {
        var key = $(this).find("[id^=paramKey]").find("option:selected").val();
        var value = $(this).find("[id^=paramValue]").find("option:selected").val();
        var pat = key + "_" + value;
        data = data + pat + "-";
        keyArray[i] = key;
        valueArray[i] = value;
        i++;
    });
    if (data.charAt(data.length - 1) == '-') {
        data = data.substr(0, data.length - 1);
    }
    //判断有没有重复的key
    var keyArray1 = keyArray.sort();

    for (var i = 0; i < keyArray.length; i++) {
        if (keyArray[i] == "" || valueArray[i] == "") {
//不允许空值存在
            return "-1";
        }

        if (keyArray1[i] == keyArray1[i + 1]) {

//不允许有重复的key
            return "-2";

        }

    }

    return data;
}
/**
 * 数据提交check
 */
LogParamGroupInfoDlg.submitCheck = function () {
    if (this.logParamGroupInfoData['paramGroupContent'] == '-1') {
        Feng.error("私有参数不允许有空值！");
        return false;
    }
    if (this.logParamGroupInfoData['paramGroupContent'] == '-2') {
        Feng.error("不允许有重复的参数名！");
        return false;
    }
    return true;
}

/**
 * 提交添加
 */
LogParamGroupInfoDlg.addSubmit = function () {
    if ($("#groupName").val() == "") {
        Feng.error("请填写数组名称!");
        return;
    }

    this.clearData();
    this.collectData();

    if (!LogParamGroupInfoDlg.submitCheck()) {
        return false;
    }
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/logParamGroup/add", function (data) {
        Feng.success("添加成功!");
        window.parent.LogParamGroup.table.refresh();
        LogParamGroupInfoDlg.close();
    }, function (data) {
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.logParamGroupInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
LogParamGroupInfoDlg.editSubmit = function () {
    if ($("#groupName").val() == "") {
        Feng.error("请填写数组名称!");
        return;
    }
    this.clearData();
    this.collectData();
    if (!LogParamGroupInfoDlg.submitCheck()) {
        return false;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/logParamGroup/update", function (data) {
        Feng.success("修改成功!");
        window.parent.LogParamGroup.table.refresh();
        LogParamGroupInfoDlg.close();
    }, function (data) {
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.logParamGroupInfoData);
    ajax.start();
}

$(function () {
    LogParamGroupInfoDlg.paramSelectInit();
    LogParamGroupInfoDlg.editParamFormat();

});

/**
 * 初始化修改页面的内容
 */
LogParamGroupInfoDlg.editParamFormat = function () {
    //处理参数，字符串分割
    var tempstr = $("#paramGroupContent").val();
    if (tempstr != "" && tempstr != null && tempstr != undefined) {
        paramarray = tempstr.split("-");
        for (var i = 0; i < paramarray.length; i++) {
            //添加一个param key-value对
            LogParamGroupInfoDlg.addItem();

            var paramKeySelectId = "paramKey" + this.count;
            var paramValueSelectId = "paramValue" + this.count;
            //paramKey
            var key_option_value = paramarray[i].split("_")[0];
            //paramValue
            var value_option_value = paramarray[i].split("_")[1];
            $("#" + paramKeySelectId).find("option[value='" + key_option_value + "']").attr("selected", true);
            $("#" + paramValueSelectId).find("option[value='" + value_option_value + "']").attr("selected", true);

        }
        $(".selectpicker").selectpicker('refresh');
    }

}
/**
 * 删除item
 */
LogParamGroupInfoDlg.deleteItem = function (event) {
    var obj = Feng.eventParseObject(event);
    obj = obj.is('button') ? obj : obj.parent();
    obj.parent().parent().parent().remove();
    this.count--;
};
/**
 * 添加条目
 */
LogParamGroupInfoDlg.addItem = function () {
    $("#itemsArea").append(this.itemTemplate);
    LogParamGroupInfoDlg.newId();
    $("#logDetailItem").attr("id", "logDetailItem" + this.count);
    $("#paramKey").attr("id", "paramKey" + this.count);
    $("#paramValue").attr("id", "paramValue" + this.count);
    LogParamGroupInfoDlg.putDateToParamSelect();
    //刷新下拉菜单控件
    $('.selectpicker').selectpicker({
        'selectedText': 'cat'
    });
};
/**
 * item获取新的id
 */
LogParamGroupInfoDlg.newId = function () {
    if (this.count == undefined) {
        this.count = 0;
    }
    this.count = this.count + 1;
};
LogParamGroupInfoDlg.putDateToParamSelect = function () {
    var paramKeySelectId = "paramKey" + this.count;
    var paramValueSelectId = "paramValue" + this.count;
    //加载paramKey下拉菜单
    $("#" + paramKeySelectId + " option").remove();
    $("#" + paramKeySelectId).prepend("<option value=''>请选择</option>");//添加第一个option值
    for (var i = 0; i < paramdata.paramKeySelectList.length; i++) {
        $("#" + paramKeySelectId).append("<option value='" + paramdata.paramKeySelectList[i].id + "'>" + paramdata.paramKeySelectList[i].param_key_code + "</option>");
    }
    //加载paramValue下拉菜单
    $("#" + paramValueSelectId + " option").remove();
    $("#" + paramValueSelectId).prepend("<option value=''>请选择</option>");//添加第一个option值
    for (var i = 0; i < paramdata.paramValueSelectList.length; i++) {
        $("#" + paramValueSelectId).append("<option value='" + paramdata.paramValueSelectList[i].id + "'>" + paramdata.paramValueSelectList[i].param_value_code + "</option>");
    }
}
/**
 * 加载首页的select下拉菜单
 */
LogParamGroupInfoDlg.paramSelectInit = function () {
    var ajax = new $ax(Feng.ctxPath + "/logDetails/paramSelectInit", function (data) {
        paramdata = data;
    }, function (data) {
        Feng.error("加载param下拉菜单失败!" + data.responseJSON.message + "!");
    });
    ajax.start();
};
/**
 * 预览参数组json
 */
LogParamGroupInfoDlg.preview = function () {
    $("#previewAir").html("");
    this.clearData();
    this.collectData();
    if (this.logParamGroupInfoData['paramGroupContent'] == '-1') {
        Feng.error("私有参数不允许有空值！");
        return false;
    }
    if (this.logParamGroupInfoData['paramGroupContent'] == '-2') {
        Feng.error("不允许有重复的参数名！");
        return false;
    }
    var ajax = new $ax(Feng.ctxPath + "/logParamGroup/preview", function (data) {

        $("#previewAir").html(data);
    }, function (data) {
        Feng.error("预览失败!" + data.responseJSON.message + "!");
    });
    ajax.set({param_key_value: this.logParamGroupInfoData['paramGroupContent']});
    ajax.start();
}

