/**
 * 打点原因关联管理初始化
 */
var pageLogCheck = {
    id: "PageLogCheckTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
pageLogCheck.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: '触发时间', field: 'eventTime', visible: true, align: 'center', valign: 'middle'},
        {title: '平台', field: 'project', visible: true, align: 'center', valign: 'middle'},
        {title: 'pageCode', field: 'page', visible: true, align: 'center', valign: 'middle'},
        {title: 'eventCode', field: 'event', visible: true, align: 'center', valign: 'middle'},
        {title: 'param', field: 'param', visible: true, align: 'left', valign: 'middle'},
        {title: '设备ID', field: 'userId', visible: true, align: 'center', valign: 'middle'},
        {title: '来源', field: 'source', visible: true, align: 'center', valign: 'middle'},
        {title: '入库时间', field: 'createTime', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
pageLogCheck.check = function () {
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
pageLogCheck.openAddBhvReason = function () {
    var index = layer.open({
        type: 2,
        title: '添加打点原因关联',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/pageLogCheck/pageLogCheck_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看打点原因关联详情
 */
pageLogCheck.openBhvReasonDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '打点原因关联详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/pageLogCheck/pageLogCheck_update/' + pageLogCheck.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除打点原因关联
 */
pageLogCheck.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/pageLogCheck/delete", function (data) {
            Feng.success("删除成功!");
            pageLogCheck.table.refresh();
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
pageLogCheck.search = function () {
    var queryData = {};
    queryData['param'] = $("#param").val();
    queryData['page'] = $("#page").val();
    queryData['event'] = $("#event").val();
    queryData['userId'] = $("#userId").val();
    queryData['platform']  =$('#platform option:selected').val();
    queryData['project']  = $('#project option:selected').val();
    queryData['appuserId']  = $('#appuserId').val();
    queryData['eventtime_start']  = $('#eventtime_start').val();
    queryData['eventtime_end']  = $('#eventtime_end').val();
    queryData['createtime_start']  = $('#createtime_start').val();
    queryData['createtime_end']  = $('#createtime_end').val();
    pageLogCheck.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = pageLogCheck.initColumn();
    var table = new BSTable(pageLogCheck.id, "/pageLogCheck/list", defaultColunms);
    table.setPaginationType("client");
    pageLogCheck.table = table.init();
});
