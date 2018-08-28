/**
 * 打点参数关联管理初始化
 */
var BhvParam = {
    id: "BhvParamTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
BhvParam.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: 'id', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '参数名称', field: 'name', visible: true, align: 'center', valign: 'middle'},
            {title: '参数编码', field: 'code', visible: true, align: 'center', valign: 'middle'},
            {title: '取值, 字典ID', field: 'value', visible: true, align: 'center', valign: 'middle'},
            {title: '描述', field: 'discribe', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'createTime', visible: true, align: 'center', valign: 'middle'},
            {title: '修改时间', field: 'updateTime', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
BhvParam.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        BhvParam.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加打点参数关联
 */
BhvParam.openAddBhvParam = function () {
    var index = layer.open({
        type: 2,
        title: '添加打点参数关联',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/bhvParam/bhvParam_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看打点参数关联详情
 */
BhvParam.openBhvParamDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '打点参数关联详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/bhvParam/bhvParam_update/' + BhvParam.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除打点参数关联
 */
BhvParam.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/bhvParam/delete", function (data) {
            Feng.success("删除成功!");
            BhvParam.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("bhvParamId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询打点参数关联列表
 */
BhvParam.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    BhvParam.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = BhvParam.initColumn();
    var table = new BSTable(BhvParam.id, "/bhvParam/list", defaultColunms);
    table.setPaginationType("client");
    BhvParam.table = table.init();
});
