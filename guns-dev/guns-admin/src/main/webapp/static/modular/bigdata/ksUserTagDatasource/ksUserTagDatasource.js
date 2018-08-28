/**
 * 推送数据源管理初始化
 */
var KsUserTagDatasource = {
    id: "KsUserTagDatasourceTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
KsUserTagDatasource.initColumn = function () {
    return [
        {field: 'selectItem', checkbox: true},
            // {title: '主键', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: 'ip地址', field: 'ip_address', visible: true, align: 'center', valign: 'middle'},
            {title: '端口号', field: 'port', visible: true, align: 'center', valign: 'middle'},
            {title: '用户名', field: 'username', visible: true, align: 'center', valign: 'middle'},
            // {title: '密码', field: 'password', visible: true, align: 'center', valign: 'middle'},
            {title: '数据源类型', field: 'datasource_type', visible: true, align: 'center', valign: 'middle'},
            {title: '数据源名称', field: 'datasource_name', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
KsUserTagDatasource.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        KsUserTagDatasource.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加推送数据源
 */
KsUserTagDatasource.openAddKsUserTagDatasource = function () {
    var index = layer.open({
        type: 2,
        title: '添加推送数据源',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/ksUserTagDatasource/ksUserTagDatasource_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看推送数据源详情
 */
KsUserTagDatasource.openKsUserTagDatasourceDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '推送数据源详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/ksUserTagDatasource/ksUserTagDatasource_update/' + KsUserTagDatasource.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除推送数据源
 */
KsUserTagDatasource.delete = function () {
    var id = "";
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    for (var i = 0; i < selected.length; i++) {
        id += selected[i].id;
        id += "_";
    }
    if (id != "") {
        id = id.substr(0, id.length - 1);
    } else {
        Feng.info("请选择任意行!");
        return;
    }

    var deleteItem = function () {
        var ajax = new $ax(Feng.ctxPath + "/ksUserTagDatasource/delete", function (data) {
            if (data.status == '1') {
                Feng.success("删除成功!");
                KsUserTagDatasource.table.refresh();
            } else {
                Feng.error(data.msg);
            }
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("ksUserTagDatasourceId",id);
        ajax.start();
    }
    Feng.confirm("是否删除连接明细？", deleteItem);
};

/**
 * 查询推送数据源列表
 */
KsUserTagDatasource.search = function () {
    var queryData = {};
    KsUserTagDatasource.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = KsUserTagDatasource.initColumn();
    var table = new BSTable(KsUserTagDatasource.id, "/ksUserTagDatasource/list", defaultColunms);
    table.setPaginationType("client");
    KsUserTagDatasource.table = table.init();
});
