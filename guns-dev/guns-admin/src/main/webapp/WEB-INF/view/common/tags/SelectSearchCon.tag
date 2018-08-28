@/*
    选择查询条件标签的参数说明:

    name : 查询条件的名称
    id : 查询内容的input框id
    可以模糊查询
@*/
<div class="input-group">
    <div class="input-group-btn">
        <button data-toggle="dropdown" class="btn btn-white dropdown-toggle" type="button">
            ${name}
        </button>
    </div>
    <select id="${id}" class="selectpicker show-tick form-control" data-live-search="true" style="background-color:white">
        ${tagBody!}
    </select>
</div>