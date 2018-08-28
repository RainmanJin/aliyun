/**
 * 初始化打点管理详情对话框
 */
var LogDetailsInfoDlg = {
    logDetailsInfoData: {},
    itemTemplate: $("#itemTemplate").html(),
    count: $("#itemSize").val()
};

/**
 * 清除数据
 */
LogDetailsInfoDlg.clearData = function () {
    this.logDetailsInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
LogDetailsInfoDlg.set = function (key, val) {
    this.logDetailsInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
LogDetailsInfoDlg.get = function (key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
LogDetailsInfoDlg.close = function () {
    parent.layer.close(window.parent.LogDetails.layerIndex);
}
/**
 * 获取私有参数拼成值
 */
LogDetailsInfoDlg.getParamDate = function () {
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
 * 收集数据
 */
LogDetailsInfoDlg.collectData = function () {
    this.logDetailsInfoData['id'] = $("#id_input").val();
    this.logDetailsInfoData['pageNum'] = $("#pageName option:selected").val();
    this.logDetailsInfoData['eventNum'] = $("#eventName option:selected").val();
    this.logDetailsInfoData['type'] = $("#type option:selected").val();
    this.logDetailsInfoData['versionId'] = $("#version option:selected").val();
    this.logDetailsInfoData['fitPlatformId'] ="";
    this.logDetailsInfoData['logContent'] = LogDetailsInfoDlg.getParamDate();
    this.logDetailsInfoData['paramGroupId'] = "";
    this.logDetailsInfoData['logDescription'] = $("#logDescription").val();
    // this.logDetailsInfoData['createTime']=$("#create_time").val();

    //获取多选的参数组id,进行处理
    if ($("#paramGroup").val() == "" || $("#paramGroup").val() == null) {
    } else {
        var paramGroupArray = $("#paramGroup").val();
        for (var i = 0; i < paramGroupArray.length; i++) {
            this.logDetailsInfoData['paramGroupId'] += paramGroupArray[i];
            this.logDetailsInfoData['paramGroupId'] += ",";
        }
        if (this.logDetailsInfoData['paramGroupId'] != "") {
            this.logDetailsInfoData['paramGroupId'] = this.logDetailsInfoData['paramGroupId'].substr(0, this.logDetailsInfoData['paramGroupId'].length - 1)
        }
    }
    //适用平台id,进行处理
    if ($("#fit_platform").val() == "" || $("#fit_platform").val() == null) {
    } else {
        var fitPlatformArray = $("#fit_platform").val();
        for (var i = 0; i < fitPlatformArray.length; i++) {
            if (fitPlatformArray[i] != "") {
                this.logDetailsInfoData['fitPlatformId'] += fitPlatformArray[i];
                this.logDetailsInfoData['fitPlatformId'] += ",";
            }
        }
        if (this.logDetailsInfoData['fitPlatformId'] != "") {
            this.logDetailsInfoData['fitPlatformId'] = this.logDetailsInfoData['fitPlatformId'].substr(0, this.logDetailsInfoData['fitPlatformId'].length - 1)
        }
    }


}
/**
 * 数据提交check
 */
LogDetailsInfoDlg.submitCheck = function () {
    if (this.logDetailsInfoData['pageNum'] == '' || this.logDetailsInfoData['eventNum'] == '' || this.logDetailsInfoData['type'] == '' || this.logDetailsInfoData['versionId'] == '' || this.logDetailsInfoData['fitPlatformId'] == '') {
        Feng.error("请填写页面的空值！");
        return false;
    }
    if (this.logDetailsInfoData['logContent'] == '-1') {
        Feng.error("私有参数不允许有空值！");
        return false;
    }
    if (this.logDetailsInfoData['logContent'] == '-2') {
        Feng.error("不允许有重复的参数名！");
        return false;
    }
    return true;
}

/**
 * 提交添加
 */
LogDetailsInfoDlg.addSubmit = function () {

    LogDetailsInfoDlg.getParamDate();
    this.clearData();
    this.collectData();
    if (!LogDetailsInfoDlg.submitCheck()) {
        return false;
    }
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/logDetails/add", function (data) {
        Feng.success("添加成功!");
        window.parent.LogDetails.table.refresh();
        LogDetailsInfoDlg.close();
    }, function (data) {
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.logDetailsInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
LogDetailsInfoDlg.editSubmit = function () {
    LogDetailsInfoDlg.getParamDate();
    this.clearData();
    this.collectData();
    //表单检查
    if (!LogDetailsInfoDlg.submitCheck()) {
        return false;
    }
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/logDetails/update", function (data) {
        Feng.success("修改成功!");
        window.parent.LogDetails.table.refresh();
        LogDetailsInfoDlg.close();
    }, function (data) {
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.logDetailsInfoData);
    ajax.start();
}
/**
 * 加载首页的select下拉菜单
 */
LogDetailsInfoDlg.selectInit = function () {
    var ajax = new $ax(Feng.ctxPath + "/logDetails/selectInit", function (data) {
        //加载page下拉菜单
        $("#pageName option").remove();
        $("#pageName").prepend("<option value=''>请选择</option>");//添加第一个option值
        for (var i = 0; i < data.pageList.length; i++) {
            $("#pageName").append("<option value='" + data.pageList[i].page_num + "'>" + data.pageList[i].page_code + "</option>");
        }
        //加载event下拉菜单
        $("#eventName option").remove();
        $("#eventName").prepend("<option value=''>请选择</option>");//添加第一个option值
        for (var i = 0; i < data.eventList.length; i++) {
            $("#eventName").append("<option value='" + data.eventList[i].event_num + "'>" + data.eventList[i].event_code + "</option>");
        }
        //加载type下拉菜单
        $("#type option").remove();
        $("#type").prepend("<option value=''>请选择</option>");//添加第一个option值
        for (var i = 0; i < data.typeList.length; i++) {
            $("#type").append("<option value='" + data.typeList[i].id + "'>" + data.typeList[i].name + "</option>");
        }
        //加载打点版本下拉菜单
        $("#version option").remove();
        $("#version").prepend("<option value=''>请选择</option>");//添加第一个option值
        for (var i = 0; i < data.versionList.length; i++) {
            $("#version").append("<option value='" + data.versionList[i].id + "'>" + data.versionList[i].name + "</option>");
        }
        //加载打点适用平台下拉菜单
        $("#fit_platform option").remove();
        $("#fit_platform").prepend("<option value=''>请选择</option>");//添加第一个option值
        for (var i = 0; i < data.fitPlatformList.length; i++) {
            $("#fit_platform").append("<option value='" + data.fitPlatformList[i].id + "'>" + data.fitPlatformList[i].name + "</option>");
        }
        $(".selectpicker").selectpicker('refresh');
    }, function (data) {
        Feng.error("加载下拉菜单失败!" + data.responseJSON.message + "!");
    });
    ajax.start();
};


/**
 * 加载首页的select下拉菜单
 */
LogDetailsInfoDlg.paramSelectInit = function () {
    var ajax = new $ax(Feng.ctxPath + "/logDetails/paramSelectInit", function (data) {
        paramdata = data;
    }, function (data) {
        Feng.error("加载param下拉菜单失败!" + data.responseJSON.message + "!");
    });
    ajax.start();
};


$(function () {
    LogDetailsInfoDlg.selectInit();
    LogDetailsInfoDlg.paramSelectInit();
    var ajax = new $ax(Feng.ctxPath + "/logParamGroup/list", function (data) {
        //事件名和参数组的关联
        $("#paramGroup option").remove();
        $("#paramGroup").prepend("<option value=''>请选择</option>");//添加第一个option值
        for (var i = 0; i < data.length; i++) {
            $("#paramGroup").append("<option value='" + data[i].id + "'>" + data[i].groupName + "</option>");
        }
    }, function (data) {
        Feng.error("加载用户组失败!" + data.responseJSON.message + "!");
    });
    ajax.start();
    LogDetailsInfoDlg.editDataInit();


});

/**
 * 加载修改页面的初始值
 */
LogDetailsInfoDlg.editDataInit = function () {
    //加载页面参数下拉菜单
    var pageNum_input = $("#pageNum_input").val();
    $("#pageName").find("option[value='" + pageNum_input + "']").attr("selected", true);
    //加载事件参数下拉菜单
    var eventNum_input = $("#eventNum_input").val();
    $("#eventName").find("option[value='" + eventNum_input + "']").attr("selected", true);

    //加载打点种类下拉菜单
    var type_input = $("#type_input").val();
    $("#type").find("option[value='" + type_input + "']").attr("selected", true);

    //加载版本下拉菜单
    var versionId_input = $("#versionId_input").val();
    $("#version").find("option[value='" + versionId_input + "']").attr("selected", true);

    //加载适用平台下拉菜单
    // var fitPlatformId_input = $("#fitPlatformId_input").val();
    // $("#fit_platform").find("option[value='" + fitPlatformId_input + "']").attr("selected", true);
    //加载打点须知
    var logDescription_input = $("#logDescription_input").val();
    $("#logDescription").val(logDescription_input)

    //加载私有参数，处理参数，字符串分割
    var tempstr = $("#logContent_input").val();
    if (tempstr != "" && tempstr != null && tempstr != undefined) {
        paramarray = tempstr.split("-");
        for (var i = 0; i < paramarray.length; i++) {
            //添加一个param key-value对
            LogDetailsInfoDlg.addItem();

            var paramKeySelectId = "paramKey" + this.count;
            var paramValueSelectId = "paramValue" + this.count;
            //paramKey
            var key_option_value = paramarray[i].split("_")[0];
            //paramValue
            var value_option_value = paramarray[i].split("_")[1];
            $("#" + paramKeySelectId).find("option[value='" + key_option_value + "']").attr("selected", true);
            // $("#" + paramKeySelectId).selectpicker('refresh');
            $("#" + paramValueSelectId).find("option[value='" + value_option_value + "']").attr("selected", true);
            // $("#" + paramValueSelectId).selectpicker('refresh');
        }
    }
    $(".selectpicker").selectpicker('refresh');

};
window.onload = function () {
    //加载参数组
    var groupArray = $("#paramGroupId_input").val().split(",");
    $("#paramGroup").selectpicker('val', groupArray).trigger("change");
    var fitPlatformIdArray = $("#fitPlatformId_input").val().split(",");
    $("#fit_platform").selectpicker('val', fitPlatformIdArray).trigger("change");

};


/**
 * 删除item
 */
LogDetailsInfoDlg.deleteItem = function (event) {
    var obj = Feng.eventParseObject(event);
    obj = obj.is('button') ? obj : obj.parent();
    obj.parent().parent().parent().remove();
    this.count--;
};
/**
 * 添加条目
 */
LogDetailsInfoDlg.addItem = function () {
    $("#itemsArea").append(this.itemTemplate);
    LogDetailsInfoDlg.newId();
    $("#logDetailItem").attr("id", "logDetailItem" + this.count);
    $("#paramKey").attr("id", "paramKey" + this.count);
    $("#paramValue").attr("id", "paramValue" + this.count);
    LogDetailsInfoDlg.putDateToParamSelect();
    $('.selectpicker').selectpicker({
        'selectedText': 'cat'
    });

};
/**
 * item获取新的id
 */
LogDetailsInfoDlg.newId = function () {
    if (this.count == undefined) {
        this.count = 0;
    }
    this.count = this.count + 1;
};
LogDetailsInfoDlg.putDateToParamSelect = function () {
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
 * 预览参数组json
 */
LogDetailsInfoDlg.preview = function () {

    $("#previewAir").html("");
    this.clearData();
    this.collectData();
    if (this.logDetailsInfoData['logContent'] == '-1') {
        Feng.error("私有参数不允许有空值！");
        return false;
    }
    if (this.logDetailsInfoData['logContent'] == '-2') {
        Feng.error("不允许有重复的参数名！");
        return false;
    }
    var ajax = new $ax(Feng.ctxPath + "/logParamGroup/preview", function (data) {

        $("#previewAir").html(data);
    }, function (data) {
        Feng.error("预览失败!" + data.responseJSON.message + "!");
    });
    ajax.set({
        param_key_value: this.logDetailsInfoData['logContent'],
        paramGroupId: this.logDetailsInfoData['paramGroupId']
    });
    ajax.start();
}


