/**
 * 事件管理管理初始化
 */
var LogEventDic = {
    id: "LogEventDicTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
LogEventDic.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        // {title: '主键', field: 'id', visible: true, align: 'center', valign: 'middle'},
        // {title: '字典码(唯一)', field: 'eventNum', visible: true, align: 'center', valign: 'middle'},
        {title: '事件code', field: 'eventCode', visible: true, align: 'center', valign: 'middle'},
        {title: '事件中文名', field: 'eventName', visible: true, align: 'center', valign: 'middle'}
        // {title: '是否删除', field: 'isDelete', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
LogEventDic.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
        LogEventDic.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加事件管理
 */
LogEventDic.openAddLogEventDic = function () {
    var index = layer.open({
        type: 2,
        title: '添加事件',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/logEventDic/logEventDic_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看事件管理详情
 */
LogEventDic.openLogEventDicDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '事件详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/logEventDic/logEventDic_update/' + LogEventDic.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除事件管理
 */
LogEventDic.delete = function () {
    if (this.check()) {
        var deleteItem = function () {
            var ajax = new $ax(Feng.ctxPath + "/logEventDic/delete", function (data) {
                if (data.code == '200') {
                    Feng.success(data.message);
                    LogEventDic.table.refresh();
                } else {
                    Feng.error(data.message)
                }
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("logEventDicId", LogEventDic.seItem.id);
            ajax.start();
        }
        Feng.confirm("是否删除该明细？", deleteItem);
    }
};

/**
 * 查询事件管理列表
 */
LogEventDic.search = function () {
    var queryData = {};
    queryData['eventcode'] = $("#eventcodeInput").val();
    queryData['eventname'] = $("#eventnameInput").val();
    LogEventDic.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = LogEventDic.initColumn();
    var table = new BSTable(LogEventDic.id, "/logEventDic/list", defaultColunms);
    table.setPaginationType("client");
    LogEventDic.table = table.init();
});
