/**
 * 标签分类管理初始化
 */
var KsUserTagClassifyManage = {
    id: "KsUserTagClassifyManageTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
KsUserTagClassifyManage.initColumn = function () {
    return [
        {field: 'selectItem', checkbox: true},
        {title: '分类名称', field: 'tag_classify_name', visible: true, align: 'center', valign: 'middle'},
        {title: '分类描述', field: 'tag_classify_describe', visible: true, align: 'center', valign: 'middle'},
        {title: '创建时间', field: 'createtime', visible: true, align: 'center', valign: 'middle'}
    ];
};



/**
 * 检查是否选中
 */
KsUserTagClassifyManage.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
        KsUserTagClassifyManage.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加标签分类
 */
KsUserTagClassifyManage.openAddKsUserTagClassifyManage = function () {
    var index = layer.open({
        type: 2,
        title: '添加标签分类',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/ksUserTagClassifyManage/ksUserTagClassifyManage_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看标签分类详情
 */
KsUserTagClassifyManage.openKsUserTagClassifyManageDetail = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length > 1) {
        Feng.info("修改时只允许选择一条明细!");
        return;
    }
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '修改标签分类',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/ksUserTagClassifyManage/ksUserTagClassifyManage_update/' + KsUserTagClassifyManage.seItem.tag_classify_id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除标签分类
 */
KsUserTagClassifyManage.delete = function () {
    var id = "";
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    for (var i = 0; i < selected.length; i++) {
        id += selected[i].tag_classify_id;
        id += "_";
    }
    if (id != "") {
        id = id.substr(0, id.length - 1);
    } else {
        return;
        Feng.info("请选择任意行!");
    }

    var deleteItem = function () {
        var ajax = new $ax(Feng.ctxPath + "/ksUserTagClassifyManage/delete", function (data) {
            if (data.status == '1') {
                Feng.success("删除成功!");
                KsUserTagClassifyManage.table.refresh();
            } else {
                Feng.error(data.msg);
            }
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("ksUserTagClassifyManageId", id);
        ajax.start();
    }
    Feng.confirm("是否删除分类明细？", deleteItem);
};

/**
 * 查询标签分类列表
 */
KsUserTagClassifyManage.search = function () {
    var queryData = {};
    queryData['tag_classify_name'] = $("#tag_classify_name").val();
    KsUserTagClassifyManage.table.refresh({query: queryData});
};


$(function () {
    var defaultColunms = KsUserTagClassifyManage.initColumn();
    var table = new BSTable(KsUserTagClassifyManage.id, "/ksUserTagClassifyManage/list", defaultColunms);
    table.setPaginationType("client");
    KsUserTagClassifyManage.table = table.init();
});
