/**
 * 打点原因关联管理初始化
 */
var BhvReason = {
    id: "BhvReasonTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
BhvReason.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: 'id', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '打点原因名称', field: 'name', visible: true, align: 'center', valign: 'middle'},
            {title: '打点原因编码', field: 'code', visible: true, align: 'center', valign: 'middle'},
            {title: '打点原因描述', field: 'discribe', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'createTime', visible: true, align: 'center', valign: 'middle'},
            {title: '修改时间', field: 'updateTime', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
BhvReason.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        BhvReason.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加打点原因关联
 */
BhvReason.openAddBhvReason = function () {
    var index = layer.open({
        type: 2,
        title: '添加打点原因关联',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/bhvReason/bhvReason_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看打点原因关联详情
 */
BhvReason.openBhvReasonDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '打点原因关联详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/bhvReason/bhvReason_update/' + BhvReason.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除打点原因关联
 */
BhvReason.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/bhvReason/delete", function (data) {
            Feng.success("删除成功!");
            BhvReason.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("bhvReasonId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询打点原因关联列表
 */
BhvReason.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    BhvReason.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = BhvReason.initColumn();
    var table = new BSTable(BhvReason.id, "/bhvReason/list", defaultColunms);
    table.setPaginationType("client");
    BhvReason.table = table.init();
});
