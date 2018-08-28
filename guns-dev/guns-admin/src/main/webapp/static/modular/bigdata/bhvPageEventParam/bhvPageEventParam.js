/**
 * 打点页面事件参数关联管理初始化
 */
var BhvPageEventParam = {
    id: "BhvPageEventParamTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
BhvPageEventParam.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: 'id', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '页面事件关联表的ID', field: 'pageEventId', visible: true, align: 'center', valign: 'middle'},
            {title: '参数ID', field: 'paramId', visible: true, align: 'center', valign: 'middle'},
            {title: '标记', field: 'label', visible: true, align: 'center', valign: 'middle'},
            {title: '描述', field: 'discribe', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'createTime', visible: true, align: 'center', valign: 'middle'},
            {title: '修改时间', field: 'updateTime', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
BhvPageEventParam.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        BhvPageEventParam.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加打点页面事件参数关联
 */
BhvPageEventParam.openAddBhvPageEventParam = function () {
    var index = layer.open({
        type: 2,
        title: '添加打点页面事件参数关联',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/bhvPageEventParam/bhvPageEventParam_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看打点页面事件参数关联详情
 */
BhvPageEventParam.openBhvPageEventParamDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '打点页面事件参数关联详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/bhvPageEventParam/bhvPageEventParam_update/' + BhvPageEventParam.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除打点页面事件参数关联
 */
BhvPageEventParam.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/bhvPageEventParam/delete", function (data) {
            Feng.success("删除成功!");
            BhvPageEventParam.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("bhvPageEventParamId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询打点页面事件参数关联列表
 */
BhvPageEventParam.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    BhvPageEventParam.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = BhvPageEventParam.initColumn();
    var table = new BSTable(BhvPageEventParam.id, "/bhvPageEventParam/list", defaultColunms);
    table.setPaginationType("client");
    BhvPageEventParam.table = table.init();
});
