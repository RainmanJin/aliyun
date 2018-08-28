/**
 * 标签快查初始化
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
        // {field: 'selectItem', radio: false},
        {title: '用户id', field: 'userid', visible: true, align: 'center', valign: 'middle'},
        {title: '标签类别', field: 'tag_classify_name', visible: true, align: 'center', valign: 'middle'},
        {title: '标签名称', field: 'tag_name', visible: true, align: 'center', valign: 'middle'},
        {title: '标签值', field: 'tag_value', visible: true, align: 'center', valign: 'middle'},
        // {title: '影响用户数', field: 'tag_user_count', visible: true, align: 'center', valign: 'middle'},
        {title: '标签状态', field: 'tag_status', visible: true, align: 'center', valign: 'middle'},
        {title: '创建时间', field: 'createtime', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 查询列表
 */
KsUserTagManage.search = function () {
    var queryData = {};
    queryData['userid'] = $("#userid").val();
    KsUserTagManage.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = KsUserTagManage.initColumn();
    var table = new BSTable(KsUserTagManage.id, "/ksUserTagManage/fastList", defaultColunms);
    table.setPaginationType("client");
    KsUserTagManage.table = table.init();
});
