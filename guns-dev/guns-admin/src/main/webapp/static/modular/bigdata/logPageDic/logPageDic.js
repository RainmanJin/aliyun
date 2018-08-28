/**
 * 页面管理管理初始化
 */
var LogPageDic = {
    id: "LogPageDicTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
LogPageDic.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        // {title: '主键', field: 'id', visible: true, align: 'center', valign: 'middle'},
        // {title: '字典码（唯一）', field: 'pageNum', visible: true, align: 'center', valign: 'middle'},
        {title: '页面字典code', field: 'pageCode', visible: true, align: 'center', valign: 'middle'},
        {title: '页面名字', field: 'pageName', visible: true, align: 'center', valign: 'middle'}
        // {title: '是否删除', field: 'isDelete', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
LogPageDic.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
        LogPageDic.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加页面管理
 */
LogPageDic.openAddLogPageDic = function () {
    var index = layer.open({
        type: 2,
        title: '添加页面',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/logPageDic/logPageDic_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看页面管理详情
 */
LogPageDic.openLogPageDicDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '页面详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/logPageDic/logPageDic_update/' + LogPageDic.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除页面管理
 */
LogPageDic.delete = function () {
    if (this.check()) {
        var deleteItem = function () {
            var ajax = new $ax(Feng.ctxPath + "/logPageDic/delete", function (data) {
                if (data.code == '200') {
                    Feng.success(data.message);
                    LogPageDic.table.refresh();
                } else {
                    Feng.error(data.message)
                }
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("logPageDicId", LogPageDic.seItem.id);
            ajax.start();
        }
        Feng.confirm("是否删除该明细？", deleteItem);
    }
};

/**
 * 查询页面管理列表
 */
LogPageDic.search = function () {
    var queryData = {};
    queryData['pagecode'] = $("#pagecodeInput").val();
    queryData['pagename'] = $("#pagenameInput").val();
    LogPageDic.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = LogPageDic.initColumn();
    var table = new BSTable(LogPageDic.id, "/logPageDic/list", defaultColunms);
    table.setPaginationType("client");
    LogPageDic.table = table.init();
});
