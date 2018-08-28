/**
 * 标签管理管理初始化
 */
var KsUserTagManage = {
    id: "KsUserTagManageTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
KsUserTagManage.initColumn = function () {
    return [
        {field: 'selectItem', checkbox: true},
        // {title: '标签id', field: 'tagId', visible: true, align: 'center', valign: 'middle'},
        {title: '标签类别', field: 'tag_classify_name', visible: true, align: 'center', valign: 'middle'},
        {title: '标签名称', field: 'tag_name', visible: true, align: 'center', valign: 'middle'},
        {title: '标签值', field: 'tag_value', visible: true, align: 'center', valign: 'middle'},
        // {title: '标签描述', field: 'tag_describe', visible: true, align: 'center', valign: 'middle'},
        {title: '影响用户数', field: 'tag_user_count', visible: true, align: 'center', valign: 'middle'},
        // {title: '标签类型', field: 'tag_type', visible: true, align: 'center', valign: 'middle'},
        // {title: '标签状态', field: 'tag_status', visible: true, align: 'center', valign: 'middle'},
        // {title: '开始时间', field: 'starttime', visible: true, align: 'center', valign: 'middle'},
        // {title: '结束时间', field: 'endtime', visible: true, align: 'center', valign: 'middle'},
        {title: '创建时间', field: 'createtime', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
KsUserTagManage.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Feng.info("请先选中表格中的记录！");
        return false;
    } else {
        KsUserTagManage.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加标签管理
 */
KsUserTagManage.openAddKsUserTagManage = function () {
    var index = layer.open({
        type: 2,
        title: '添加标签',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/ksUserTagManage/ksUserTagManage_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看标签管理详情
 */
KsUserTagManage.openKsUserTagManageDetail = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length > 1) {
        Feng.info("修改时只允许选择一条明细!");
        return;
    }
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '修改标签',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/ksUserTagManage/ksUserTagManage_update/' + KsUserTagManage.seItem.tag_id
        });
        this.layerIndex = index;
    }
};


/**
 * 打开查看标签管理详情
 */
KsUserTagManage.openKsUserTagManageReview = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length > 1) {
        Feng.info("预览时只允许选择一条明细!");
        return;
    }
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '预览标签',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/ksUserTagManage/ksUserTagManage_detail/' + KsUserTagManage.seItem.tag_id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除标签管理
 */
KsUserTagManage.delete = function () {
    var id = "";

    var selected = $('#' + this.id).bootstrapTable('getSelections');
    for (var i = 0; i < selected.length; i++) {
        id += selected[i].tag_id;
        id += "_";
    }
    if (id != "") {
        id = id.substr(0, id.length - 1);
    } else {
        return;
        Feng.info("请选择任意行!");
    }
    var deleteItem = function () {
        var ajax = new $ax(Feng.ctxPath + "/ksUserTagManage/delete", function (data) {
            if (data.status == '1') {
                Feng.success("删除成功!");
                KsUserTagManage.table.refresh();
            } else {
                Feng.error(data.msg);
            }
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("ksUserTagManageId", id);
        ajax.start();
    }
    Feng.confirm("是否删除标签明细？", deleteItem);

};

/**
 * 查询标签管理列表
 */
KsUserTagManage.search = function () {
    var queryData = {};
    queryData['tag_classify_id'] = $('#tag_classify_id option:selected').val();
    queryData['tag_name'] = $("#tag_name").val();
    KsUserTagManage.table.refresh({query: queryData});
};
//加载标签分类下拉菜单
KsUserTagManage.selectInit = function () {
    var queryData = {};
    queryData['tag_classify_name'] ="";
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/ksUserTagClassifyManage/list", function (data) {
        //加载page下拉菜单
        $("#tag_classify_id option").remove();
        $("#tag_classify_id").prepend("<option value=''>全部分类</option>");//添加第一个option值
        for (var i = 0; i < data.length; i++) {
            $("#tag_classify_id").append("<option value='" + data[i].tag_classify_id + "'>" + data[i].tag_classify_name + "</option>");
        }
    }, function (data) {
        Feng.error("加载标签分类下拉菜单失败!" + data.responseJSON.message + "!");
    });
    ajax.set(queryData);
    ajax.start();
};
$(function () {
    var defaultColunms = KsUserTagManage.initColumn();
    KsUserTagManage.selectInit();
    var table = new BSTable(KsUserTagManage.id, "/ksUserTagManage/list", defaultColunms);
    table.setPaginationType("client");
    KsUserTagManage.table = table.init();
});
