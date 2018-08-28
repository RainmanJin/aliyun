/**
 * 打点管理管理初始化
 */
var LogDetails = {
    id: "LogDetailsTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
LogDetails.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        // {title: '主键', field: 'id', visible: true, align: 'center', valign: 'middle'},
        {title: '页面code', field: 'pageNum', visible: true, align: 'center', valign: 'middle', sortable: true},
        {title: '事件code', field: 'eventNum', visible: true, align: 'center', valign: 'middle', sortable: true},
        {title: '打点种类', field: 'type', visible: true, align: 'center', valign: 'middle', sortable: true},
        {title: '打点版本', field: 'versionId', visible: true, align: 'center', valign: 'middle', sortable: true},
        {title: '打点适用的平台', field: 'fitPlatformId', visible: true, align: 'center', valign: 'middle', sortable: true},
        // {title: '日志内容', field: 'logContent', visible: true, align: 'center', valign: 'middle'},
        {title: '最后更新时间', field: 'updateTime', visible: true, align: 'center', valign: 'middle', sortable: true},
        {title: '创建时间', field: 'createTime', visible: true, align: 'center', valign: 'middle', sortable: true}
    ];
};

/**
 * 检查是否选中
 */
LogDetails.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
        LogDetails.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加打点管理
 */
LogDetails.openAddLogDetails = function () {
    var index = layer.open({
        type: 2,
        title: '添加打点',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/logDetails/logDetails_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看打点管理详情
 */
LogDetails.openLogDetailsDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '打点详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/logDetails/logDetails_update/' + LogDetails.seItem.id
        });
        this.layerIndex = index;
    }
};


/**
 * 打开打点预览页面
 */
LogDetails.previewItem = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '打点预览',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/logDetails/logDetails_previewItem/' + LogDetails.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除打点管理
 */
LogDetails.delete = function () {
    if (this.check()) {
        var deleteItem = function () {
            var ajax = new $ax(Feng.ctxPath + "/logDetails/delete", function (data) {
                Feng.success("废弃成功!");
                LogDetails.table.refresh();
            }, function (data) {
                Feng.error("废弃失败!" + data.responseJSON.message + "!");
            });
            ajax.set("logDetailsId", LogDetails.seItem.id);
            ajax.start();
        }
        Feng.confirm("是否废弃该打点明细？", deleteItem);

    }
};
/**
 * 删除明细
 */
var deleteItem = function () {
    var ajax = new $ax(Feng.ctxPath + "/logDetails/delete", function (data) {
        Feng.success("删除成功!");
        LogDetails.table.refresh();
    }, function (data) {
        Feng.error("删除失败!" + data.responseJSON.message + "!");
    });
    ajax.set("logDetailsId", LogDetails.seItem.id);
    ajax.start();
}
/**
 * 加载首页的select下拉菜单
 */
LogDetails.selectInit = function () {
    var ajax = new $ax(Feng.ctxPath + "/logDetails/selectInit", function (data) {
        // //加载page下拉菜单
        // $("#pageName option").remove();
        // $("#pageName").prepend("<option value=''>请选择</option>");//添加第一个option值
        // for (var i = 0; i < data.pageList.length; i++) {
        //     $("#pageName").append("<option value='" + data.pageList[i].page_num + "'>" + data.pageList[i].page_code + "</option>");
        // }
        // //加载event下拉菜单
        // $("#eventName option").remove();
        // $("#eventName").prepend("<option value=''>请选择</option>");//添加第一个option值
        // for (var i = 0; i < data.eventList.length; i++) {
        //     $("#eventName").append("<option value='" + data.eventList[i].event_num + "'>" + data.eventList[i].event_code + "</option>");
        // }
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
    }, function (data) {
        Feng.error("加载下拉菜单失败!" + data.responseJSON.message + "!");
    });
    ajax.start();
};

/**
 * 查询打点管理列表
 */
LogDetails.search = function () {
    var queryData = {};
    // queryData['pageNum'] = $("#pageName option:selected").val();
    // queryData['eventNum'] = $("#eventName option:selected").val();
    queryData['pageNum'] = $("#pageName").val();
    queryData['eventNum'] = $("#eventName").val();
    queryData['type'] = $("#type option:selected").val();
    queryData['versionId'] = $("#version option:selected").val();
    queryData['fitPlatformId'] = $("#fit_platform option:selected").val();
    queryData['startTime'] = $("#startTime").val();
    queryData['endTime'] = $("#endTime").val();
    queryData['isDelete'] = $("#is_delete option:selected").val();
    debugger;
    LogDetails.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = LogDetails.initColumn();
    var table = new BSTable(LogDetails.id, "/logDetails/list", defaultColunms);
    table.setPaginationType("client");
    LogDetails.table = table.init();
    LogDetails.selectInit();
});
