/**
 * 参数value管理管理初始化
 */
var LogParamValueDic = {
    id: "LogParamValueDicTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
LogParamValueDic.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            // {title: '主键', field: 'id', visible: true, align: 'center', valign: 'middle'},
            // {title: '字典码（唯一值）', field: 'paramValueNum', visible: true, align: 'center', valign: 'middle'},
            {title: 'param_value的值', field: 'paramValueCode', visible: true, align: 'center', valign: 'middle'},
            {title: 'param_value_name的说明', field: 'paramValueName', visible: true, align: 'center', valign: 'middle'},
            // {title: '是否删除', field: 'isDelete', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
LogParamValueDic.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        LogParamValueDic.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加参数value管理
 */
LogParamValueDic.openAddLogParamValueDic = function () {
    var index = layer.open({
        type: 2,
        title: '添加参数value',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/logParamValueDic/logParamValueDic_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看参数value管理详情
 */
LogParamValueDic.openLogParamValueDicDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '参数value管理',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/logParamValueDic/logParamValueDic_update/' + LogParamValueDic.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除参数value管理
 */
LogParamValueDic.delete = function () {
    if (this.check()) {
        var deleteItem = function () {
            var ajax = new $ax(Feng.ctxPath + "/logParamValueDic/delete", function (data) {
                Feng.success("删除成功!");
                LogParamValueDic.table.refresh();
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("logParamValueDicId", LogParamValueDic.seItem.id);
            ajax.start();
        }
        Feng.confirm("是否删除该明细？", deleteItem);
    }
};

/**
 * 查询参数value管理列表
 */
LogParamValueDic.search = function () {
    var queryData = {};
    queryData['paramvaluecode'] = $("#paramvaluecodeInput").val();
    queryData['paramvaluename'] = $("#paramvaluenameInput").val();
    LogParamValueDic.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = LogParamValueDic.initColumn();
    var table = new BSTable(LogParamValueDic.id, "/logParamValueDic/list", defaultColunms);
    table.setPaginationType("client");
    LogParamValueDic.table = table.init();
});
