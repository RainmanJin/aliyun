/**
 * 打点日志管理初始化
 */
var BhvLog = {
    id: "BhvLogTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
BhvLog.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: 'id', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '页面事件参数关联ID', field: 'pageEventParamId', visible: true, align: 'center', valign: 'middle'},
            {title: '页面名称', field: 'pageName', visible: true, align: 'center', valign: 'middle'},
            {title: '页面编码', field: 'pageCode', visible: true, align: 'center', valign: 'middle'},
            {title: '事件名称', field: 'eventName', visible: true, align: 'center', valign: 'middle'},
            {title: '事件编码', field: 'eventCode', visible: true, align: 'center', valign: 'middle'},
            {title: '参数名称', field: 'paramName', visible: true, align: 'center', valign: 'middle'},
            {title: '参数编码', field: 'paramCode', visible: true, align: 'center', valign: 'middle'},
            {title: '参数内容', field: 'paramValue', visible: true, align: 'center', valign: 'middle'},
            {title: '描述', field: 'discribe', visible: true, align: 'center', valign: 'middle'},
            {title: '标记', field: 'label', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'createTime', visible: true, align: 'center', valign: 'middle'},
            {title: '修改时间', field: 'updateTime', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
BhvLog.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        BhvLog.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加打点日志
 */
BhvLog.openAddBhvLog = function () {
    var index = layer.open({
        type: 2,
        title: '添加打点日志',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/bhvLog/bhvLog_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看打点日志详情
 */
BhvLog.openBhvLogDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '打点日志详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/bhvLog/bhvLog_update/' + BhvLog.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除打点日志
 */
BhvLog.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/bhvLog/delete", function (data) {
            Feng.success("删除成功!");
            BhvLog.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("bhvLogId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询打点日志列表
 */
BhvLog.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    BhvLog.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = BhvLog.initColumn();
    var table = new BSTable(BhvLog.id, "/bhvLog/list", defaultColunms);
    table.setPaginationType("client");
    BhvLog.table = table.init();
});
