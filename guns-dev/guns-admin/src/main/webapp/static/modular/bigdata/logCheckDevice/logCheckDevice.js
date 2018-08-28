/**
 * 用户设备号管理初始化
 */
var LogCheckDevice = {
    id: "LogCheckDeviceTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
LogCheckDevice.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: '设备ID', field: 'deviceId', visible: true, align: 'center', valign: 'middle'},
        {title: '用户名', field: 'userName', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
LogCheckDevice.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
        LogCheckDevice.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加用户设备号
 */
LogCheckDevice.openAddLogCheckDevice = function () {
    var index = layer.open({
        type: 2,
        title: '添加用户设备号',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/logCheckDevice/logCheckDevice_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看用户设备号详情
 */
LogCheckDevice.openLogCheckDeviceDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '用户设备号详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/logCheckDevice/logCheckDevice_update/' + LogCheckDevice.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除用户设备号
 */
LogCheckDevice.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/logCheckDevice/delete", function (data) {
            Feng.success("删除成功!");
            LogCheckDevice.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("logCheckDeviceId", this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询用户设备号列表
 */
LogCheckDevice.search = function () {
    var queryData = {};
    queryData['deviceId'] = $("#deviceId").val();
    queryData['userName'] = $("#userName").val();
    LogCheckDevice.table.refresh({query: queryData});
};



/**
 * 根据appuserid查询 设备id
 */
LogCheckDevice.fastSelect = function () {
    var index = layer.open({
        type: 2,
        title: '设备号快查',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/logCheckDevice/logCheckDevice_fastSelect_jump'
    });
    this.layerIndex = index;
};

$(function () {
    var defaultColunms = LogCheckDevice.initColumn();
    var table = new BSTable(LogCheckDevice.id, "/logCheckDevice/list", defaultColunms);
    table.setPaginationType("client");
    LogCheckDevice.table = table.init();
});
