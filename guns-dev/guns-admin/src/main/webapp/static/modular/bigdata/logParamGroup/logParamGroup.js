/**
 * 参数组管理管理初始化
 */
var LogParamGroup = {
    id: "LogParamGroupTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
LogParamGroup.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            // {title: '主键', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '参数组名称', field: 'groupName', visible: true, align: 'center', valign: 'middle'},
            {title: '数据组描述', field: 'groupDescription', visible: true, align: 'center', valign: 'middle'}
            // {title: '存储包含参数对', field: 'paramGroupContent', visible: true, align: 'center', valign: 'middle'},
            // {title: '', field: 'isDelete', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
LogParamGroup.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        LogParamGroup.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加参数组管理
 */
LogParamGroup.openAddLogParamGroup = function () {
    var index = layer.open({
        type: 2,
        title: '添加参数组',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/logParamGroup/logParamGroup_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看参数组管理详情
 */
LogParamGroup.openLogParamGroupDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '参数组详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/logParamGroup/logParamGroup_update/' + LogParamGroup.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除参数组管理
 */
LogParamGroup.delete = function () {
    if (this.check()) {
        var deleteItem = function () {
            var ajax = new $ax(Feng.ctxPath + "/logParamGroup/delete", function (data) {
                Feng.success("删除成功!");
                LogParamGroup.table.refresh();
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("logParamGroupId", LogParamGroup.seItem.id);
            ajax.start();
        }
        Feng.confirm("是否删除该明细？", deleteItem);
    }
};

/**
 * 查询参数组管理列表
 */
LogParamGroup.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    LogParamGroup.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = LogParamGroup.initColumn();
    var table = new BSTable(LogParamGroup.id, "/logParamGroup/list", defaultColunms);
    table.setPaginationType("client");
    LogParamGroup.table = table.init();
});
