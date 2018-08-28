/**
 * 打点事件code管理初始化
 */
var BhvEvent = {
    id: "BhvEventTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
BhvEvent.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: 'id', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '事件名称', field: 'name', visible: true, align: 'center', valign: 'middle'},
            {title: '事件编码', field: 'code', visible: true, align: 'center', valign: 'middle'},
            {title: '描述', field: 'discribe', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'createTime', visible: true, align: 'center', valign: 'middle'},
            {title: '修改时间', field: 'updateTime', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
BhvEvent.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        BhvEvent.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加打点事件code
 */
BhvEvent.openAddBhvEvent = function () {
    var index = layer.open({
        type: 2,
        title: '添加打点事件code',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/bhvEvent/bhvEvent_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看打点事件code详情
 */
BhvEvent.openBhvEventDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '打点事件code详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/bhvEvent/bhvEvent_update/' + BhvEvent.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除打点事件code
 */
BhvEvent.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/bhvEvent/delete", function (data) {
            Feng.success("删除成功!");
            BhvEvent.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("bhvEventId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询打点事件code列表
 */
BhvEvent.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    BhvEvent.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = BhvEvent.initColumn();
    var table = new BSTable(BhvEvent.id, "/bhvEvent/list", defaultColunms);
    table.setPaginationType("client");
    BhvEvent.table = table.init();
});
