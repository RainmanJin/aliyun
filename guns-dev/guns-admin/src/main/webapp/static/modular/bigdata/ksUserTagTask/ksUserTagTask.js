/**
 * 标签推送任务管理初始化
 */
var KsUserTagTask = {
    id: "KsUserTagTaskTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
KsUserTagTask.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: '标签组', field: 'tag_classify_name', visible: true, align: 'center', valign: 'middle'},
        {title: '标签', field: 'tag_name', visible: true, align: 'center', valign: 'middle'},
        {title: '执行策略', field: 'cron_expression', visible: true, align: 'center', valign: 'middle'},
        {title: '目标数据源', field: 'datasource_name', visible: true, align: 'center', valign: 'middle'},
        {title: '目标数据类型', field: 'datasource_type', visible: true, align: 'center', valign: 'middle'},
        {title: '目标表（键）名称', field: 'set_name', visible: true, align: 'center', valign: 'middle'},
        {title: '任务类型', field: 'incremental', visible: true, align: 'center', valign: 'middle'},
        {title: '任务状态', field: 'status', visible: true, align: 'center', valign: 'middle'},
        {title: '创建时间', field: 'createtime', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
KsUserTagTask.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
        KsUserTagTask.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加标签推送任务
 */
KsUserTagTask.openAddKsUserTagTask = function () {
    var index = layer.open({
        type: 2,
        title: '添加标签推送任务',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/ksUserTagTask/ksUserTagTask_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看标签推送任务详情
 */
KsUserTagTask.openKsUserTagTaskDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '标签推送任务详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/ksUserTagTask/ksUserTagTask_update/' + KsUserTagTask.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 打开查看标签推送任务执行详情
 */
KsUserTagTask.openKsUserTagTaskExeLog = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '标签推送任务详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/ksUserTagTask/ksUserTagTask_log/' + KsUserTagTask.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除标签推送任务
 */
KsUserTagTask.delete = function () {
    if (this.check()) {
        var deleteItem = function () {
            var ajax = new $ax(Feng.ctxPath + "/ksUserTagTask/delete", function (data) {
                if (data.status == '1') {
                    Feng.success("删除成功!");
                    KsUserTagTask.table.refresh();
                } else {
                    Feng.error(data.msg);
                }
            }, function (data) {
                Feng.error("删除失败，系统错误!");
            });
            ajax.set("ksUserTagTaskId", KsUserTagTask.seItem.id);
            ajax.start();
        }
        Feng.confirm("是否删除任务明细？", deleteItem);
    }
};
/**
 * 删除标签推送任务
 */
KsUserTagTask.start = function () {
    if (this.check()) {
        var deleteItem = function () {
            var ajax = new $ax(Feng.ctxPath + "/ksUserTagTask/start", function (data) {
                if (data.status == '1') {
                    Feng.success("启动成功!");
                    KsUserTagTask.table.refresh();
                } else {
                    Feng.error(data.msg);
                }
            }, function (data) {
                Feng.error("启动失败，系统错误!");
            });
            ajax.set("ksUserTagTaskId", KsUserTagTask.seItem.id);
            ajax.start();
        }
        Feng.confirm("是否启动该任务？", deleteItem);
    }
};

/**
 * 查询标签推送任务列表
 */
KsUserTagTask.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    KsUserTagTask.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = KsUserTagTask.initColumn();
    var table = new BSTable(KsUserTagTask.id, "/ksUserTagTask/list", defaultColunms);
    table.setPaginationType("client");
    KsUserTagTask.table = table.init();
});
