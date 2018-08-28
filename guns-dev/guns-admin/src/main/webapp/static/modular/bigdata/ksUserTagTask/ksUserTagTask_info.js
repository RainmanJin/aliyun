/**
 * 初始化标签推送任务详情对话框
 */
var KsUserTagTaskInfoDlg = {
    id: "KsUserTagTaskLogTable",	//表格id
    ksUserTagTaskInfoData: {},
    table: null,
    layerIndex: -1
};

/**
 * 清除数据
 */
KsUserTagTaskInfoDlg.clearData = function () {
    this.ksUserTagTaskInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
KsUserTagTaskInfoDlg.set = function (key, val) {
    this.ksUserTagTaskInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
KsUserTagTaskInfoDlg.get = function (key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
KsUserTagTaskInfoDlg.close = function () {
    parent.layer.close(window.parent.KsUserTagTask.layerIndex);
}

/**
 * 收集数据
 */
KsUserTagTaskInfoDlg.collectData = function () {
    this
        .set('set_name')
        .set('hour')
        .set('minutes')
        .set('incremental');
    KsUserTagTaskInfoDlg.ksUserTagTaskInfoData['tag_id'] = $('#tag_id option:selected').val();
    KsUserTagTaskInfoDlg.ksUserTagTaskInfoData['datasource_id'] = $('#datasource_id option:selected').val();

}

/**
 * 提交添加
 */
KsUserTagTaskInfoDlg.addSubmit = function () {

    this.clearData();
    this.collectData();

    var tag_id = KsUserTagTaskInfoDlg.ksUserTagTaskInfoData['tag_id'];
    var datasource_id = KsUserTagTaskInfoDlg.ksUserTagTaskInfoData['datasource_id'];
    var set_name = KsUserTagTaskInfoDlg.ksUserTagTaskInfoData['set_name'];
    var hour = KsUserTagTaskInfoDlg.ksUserTagTaskInfoData['hour'];
    var minutes = KsUserTagTaskInfoDlg.ksUserTagTaskInfoData['minutes'];
    var incremental = KsUserTagTaskInfoDlg.ksUserTagTaskInfoData['incremental'];
    //标签
    if (tag_id == null || tag_id == "" || tag_id == 'undefined') {
        Feng.error("请选择标签!");
        return false;
    }
    //数据源
    if (datasource_id == null || datasource_id == "" || datasource_id == 'undefined') {
        Feng.error("请选择数据源!");
        return false;
    }
    //数据源
    if (incremental == null || incremental == "" || incremental == 'undefined') {
        Feng.error("请选择任务类型!");
        return false;
    }
    //目标表（key）
    if (set_name == null || set_name == "" || set_name == 'undefined') {
        Feng.error("请填写目标表（key）!");
        return false;
    }
    if ($('#tag_id option:selected').attr("name") == '2') {
        if (hour == "" || minutes == "") {
            Feng.error("请填写执行时间!");
            return false;
        }
    }
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/ksUserTagTask/add", function (data) {

        if (data.status == '1') {
            Feng.success("添加成功!");
            window.parent.KsUserTagTask.table.refresh();
            KsUserTagTaskInfoDlg.close();
        } else {
            Feng.error(data.msg);
        }
    }, function (data) {
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.ksUserTagTaskInfoData);
    ajax.start();
}

$(function () {
    KsUserTagTaskInfoDlg.selectInit();
    $("#tag_id").change(function () {
        //选中的值
        if ($('#tag_id option:selected').attr("name") == '2') {
            $(".hideDiv").hide();
            $(".hideDiv").hide();
            $('#hour').val("");
            $('#minutes').val("");
        } else {
            $(".hideDiv").show();
            $(".hideDiv").show();
        }
    });
});
/**
 * 加载首页的select下拉菜单
 */
KsUserTagTaskInfoDlg.selectInit = function () {
    var ajax = new $ax(Feng.ctxPath + "/ksUserTagManage/list", function (data) {
        //加载page下拉菜单
        $("#tag_id option").remove();
        $("#tag_id").prepend("<option value='' selected>请选择</option>");//添加第一个option值
        for (var i = 0; i < data.length; i++) {
            $("#tag_id").append("<option name='" + data[i].tag_status + "' value='" + data[i].tag_id + "'>" + data[i].tag_name + "</option>");
        }
    }, function (data) {
        Feng.error("加载标签下拉菜单失败!" + data.responseJSON.message + "!");
    });
    ajax.start();
    var ajax1 = new $ax(Feng.ctxPath + "/ksUserTagDatasource/list", function (data) {
        //加载page下拉菜单
        $("#datasource_id option").remove();
        $("#datasource_id").prepend("<option value='' selected>请选择</option>");//添加第一个option值
        for (var i = 0; i < data.length; i++) {
            $("#datasource_id").append("<option value='" + data[i].id + "'>" + data[i].datasource_name + "</option>");
        }
    }, function (data) {
        Feng.error("加载数据源下拉菜单失败!" + data.responseJSON.message + "!");
    });
    ajax1.start();
    if ($("#flag").val() == '1') {
        KsUserTagTaskInfoDlg.initTable();
    }
};
/**
 * 初始化表格的列
 */
KsUserTagTaskInfoDlg.initColumn = function () {
    return [
        {
            title: 'id',
            formatter: function (value, row, index) {
                return index + 1;
            }, align: 'center', valign: 'middle'
        },
        {title: '同步数据条数', field: 'datacount', visible: true, align: 'center', valign: 'middle'},
        {title: '执行时间', field: 'executetime', visible: true, align: 'center', valign: 'middle'}
    ];
};

KsUserTagTaskInfoDlg.initTable = function () {
    var defaultColunms = KsUserTagTaskInfoDlg.initColumn();
    var task_id = $("#task_id").val();
    var table = new BSTable(KsUserTagTaskInfoDlg.id, "/ksUserTagTask/logList?ksUserTagTaskId=" + task_id, defaultColunms);
    table.setPaginationType("client");
    KsUserTagTaskInfoDlg.table = table.init();
}
