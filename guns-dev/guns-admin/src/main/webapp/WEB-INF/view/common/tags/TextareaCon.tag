@/*
    名称查询条件标签的参数说明:

    name : 文本域的名称
    id : 文本域id
@*/
<div class="input-group">
    <div class="input-group-btn">
        <button data-toggle="dropdown" class="btn btn-white dropdown-toggle"
                type="button">${name}
        </button>
    </div>
    <textarea class="form-control" rows="3" id="${id}" placeholder="${placeholder!}" value="${value!}"></textarea>
</div>