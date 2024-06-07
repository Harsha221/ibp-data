<%@ page import="com.ibp.admin.Association" %>
<%
    List<com.ibp.admin.Association> associations = com.ibp.admin.Association.findAllByActive(true)
%>
<div class="form-group">
    <label class="col-md-2 control-label" for="title">
        Title<span class="text-danger">*</span>
    </label>
    <div class="col-md-8">
        <form:input
                type="text"
                id="title"
                name="title"
                value="${data?.title}"
                bean="${data}"
                class="form-control"
                placeholder="Enter Title"
                required="true" />
    </div>
</div>

<div class="form-group">
    <label class="col-md-2 control-label" for="association">
        Associations<span class="text-danger">*</span>
    </label>
    <div class="col-md-8">
        <form:select
                id="association"
                name="association"
                value="${data?.associationId}"
                bean="${data}"
                class="form-control resize_vertical"
                from="${Association.findAllByActive(true)}"
                optionValue="name"
                optionKey="id"
                noSelection="['':' -- Select Association -- ']"
                required="true" />
    </div>
</div>

<div class="form-group">
    <label class="col-md-2 control-label" for="youtubeVideoUrl">
        YouTube Video Url<span class="text-danger">*</span>
    </label>
    <div class="col-md-8">
        <form:input
                type="text"
                id="youtubeVideoUrl"
                name="youtubeVideoUrl"
                value="${data?.youtubeVideoUrl}"
                bean="${data}"
                class="form-control"
                placeholder="Enter YouTube Video Url"
                required="true" />
    </div>
</div>

<div class="form-group">
    <label class="col-md-2 control-label" for="tags">
        Tags
    </label>
    <div class="col-md-8">
        <input type="text" name="tags" id="tags"
               value="${data?.tags}" data-role="tagsinput"/>
    </div>
</div>

<div class="form-group">
    <label class="col-md-2 control-label" for="active">
        Active<span class="text-danger"></span>
    </label>
    <div class="col-md-8">
        <g:checkBox
                name="active"
                class="bs-switch"
                value="${data?.status}"
                data-size="mini"

        />


    </div>
</div>

<div class="form-group form-actions">
    <div class="col-md-8 col-md-offset-4">
        <button type="submit" class="btn btn-effect-ripple btn-primary">${actionName in ['create', 'save'] ? 'Create' : 'Update'}</button>
        <g:link action="index" class="btn btn-effect-ripple btn-default reset_btn" >Back</g:link>
    </div>
</div>

