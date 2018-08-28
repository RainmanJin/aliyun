/**
 * 打点页面与事件关联管理初始化
 */
var BhvPageEvent = {
    id: "BhvPageEventTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
BhvPageEvent.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: 'id', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '页面ID', field: 'pageId', visible: true, align: 'center', valign: 'middle'},
            {title: '事件ID', field: 'eventId', visible: true, align: 'center', valign: 'middle'},
            {title: '标记', field: 'label', visible: true, align: 'center', valign: 'middle'},
            {title: '描述', field: 'discribe', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'createTime', visible: true, align: 'center', valign: 'middle'},
            {title: '修改时间', field: 'updateTime', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
BhvPageEvent.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        BhvPageEvent.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加打点页面与事件关联
 */
BhvPageEvent.openAddBhvPageEvent = function () {
    var index = layer.open({
        type: 2,
        title: '添加打点页面与事件关联',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/bhvPageEvent/bhvPageEvent_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看打点页面与事件关联详情
 */
BhvPageEvent.openBhvPageEventDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '打点页面与事件关联详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/bhvPageEvent/bhvPageEvent_update/' + BhvPageEvent.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除打点页面与事件关联
 */
BhvPageEvent.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/bhvPageEvent/delete", function (data) {
            Feng.success("删除成功!");
            BhvPageEvent.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("bhvPageEventId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询打点页面与事件关联列表
 */
BhvPageEvent.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    BhvPageEvent.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = BhvPageEvent.initColumn();
    var table = new BSTable(BhvPageEvent.id, "/bhvPageEvent/list", defaultColunms);
    table.setPaginationType("client");
    BhvPageEvent.table = table.init();
});
