/**
 * 参数key管理管理初始化
 */
var LogParamKeyDic = {
    id: "LogParamKeyDicTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
LogParamKeyDic.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            // {title: '主键', field: 'id', visible: true, align: 'center', valign: 'middle'},
            // {title: '字典码（唯一）', field: 'paramKeyNum', visible: true, align: 'center', valign: 'middle'},
            {title: 'param_key值', field: 'paramKeyCode', visible: true, align: 'center', valign: 'middle'},
            {title: 'param_key名字', field: 'paramKeyName', visible: true, align: 'center', valign: 'middle'},
            // {title: '是否删除', field: 'isDelete', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
LogParamKeyDic.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        LogParamKeyDic.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加参数key管理
 */
LogParamKeyDic.openAddLogParamKeyDic = function () {
    var index = layer.open({
        type: 2,
        title: '添加参数key',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/logParamKeyDic/logParamKeyDic_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看参数key管理详情
 */
LogParamKeyDic.openLogParamKeyDicDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '参数key详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/logParamKeyDic/logParamKeyDic_update/' + LogParamKeyDic.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除参数key管理
 */
LogParamKeyDic.delete = function () {
    if (this.check()) {
        var deleteItem = function () {
            var ajax = new $ax(Feng.ctxPath + "/logParamKeyDic/delete", function (data) {
                Feng.success("删除成功!");
                LogParamKeyDic.table.refresh();
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("logParamKeyDicId", LogParamKeyDic.seItem.id);
            ajax.start();
        }
        Feng.confirm("是否删除该明细？", deleteItem);
    }
};

/**
 * 查询参数key管理列表
 */
LogParamKeyDic.search = function () {
    var queryData = {};
    queryData['paramkeycode'] = $("#paramkeycodeInput").val();
    queryData['paramkeyname'] = $("#paramkeynameInput").val();
    LogParamKeyDic.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = LogParamKeyDic.initColumn();
    var table = new BSTable(LogParamKeyDic.id, "/logParamKeyDic/list", defaultColunms);
    table.setPaginationType("client");
    LogParamKeyDic.table = table.init();
});
