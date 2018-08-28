/**
 * 打点页面Code管理初始化
 */
var BhvPage = {
    id: "BhvPageTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
BhvPage.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: 'id', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '页面名称', field: 'name', visible: true, align: 'center', valign: 'middle'},
            {title: '页面编码', field: 'code', visible: true, align: 'center', valign: 'middle'},
            {title: '描述', field: 'discribe', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'createTime', visible: true, align: 'center', valign: 'middle'},
            {title: '修改时间', field: 'updateTime', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
BhvPage.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        BhvPage.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加打点页面Code
 */
BhvPage.openAddBhvPage = function () {
    var index = layer.open({
        type: 2,
        title: '添加打点页面Code',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/bhvPage/bhvPage_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看打点页面Code详情
 */
BhvPage.openBhvPageDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '打点页面Code详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/bhvPage/bhvPage_update/' + BhvPage.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除打点页面Code
 */
BhvPage.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/bhvPage/delete", function (data) {
            Feng.success("删除成功!");
            BhvPage.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("bhvPageId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询打点页面Code列表
 */
BhvPage.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    BhvPage.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = BhvPage.initColumn();
    var table = new BSTable(BhvPage.id, "/bhvPage/list", defaultColunms);
    table.setPaginationType("client");
    BhvPage.table = table.init();
});
