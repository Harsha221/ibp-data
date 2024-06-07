<%@ page import="com.ibp.admin.NewsCategory; com.ibp.admin.Association"%>




<div class="panel">

%{--    <div class="panel-heading" id="searching">--}%
%{--        <h3 class="panel-title" ><i class="fa fa-fw ti-search"></i> Search</h3>--}%
%{--        <span class="pull-right">--}%
%{--          --}%%{--  <i class="fa fa-fw ti-angle-down"></i>--}%
%{--          --}%%{--  --}%%{--<i class="fa fa-fw ti-close removepanel clickable"></i>--}%
%{--        </span>--}%
%{--    </div>--}%

    <div class="panel-body">
        <form name="categorySearchForm">

            <div class="row">

                <div class="col-sm-2">
                    <div class="form-group">
                        <input type="text" name="name" id="name" class="form-control input-md" placeholder="Title"
                               value="${params?.name}">
                    </div>
                </div>

                <div class="col-sm-2">
                    <div class="form-group ">

                        <g:select
                                name="searchNewsStatus"
                                class="form-control"
                                from="['Activated', 'Deactivated']"
                                id="searchNewsStatus"
                                noSelection="['': ' -- Select Status -- ']"/>
                    </div>
                </div>

                <div class="col-md-3">
                    <div class="form-group">
                        <g:select
                                id="associationId"
                                name="associationId"
                                class="form-control"
                                from="${Association.findAllByActive(true)}"
                                optionValue="name"
                                optionKey="id"
                                noSelection="['': ' -- Select Association -- ']"/>
                    </div>
                </div>

                <div class="col-sm-2">
                    <div class="form-group">

                        <g:select
                                name="searchNewsCategory"
                                class="form-control"
                                from="${com.ibp.admin.NewsCategory.findAllByStatus(true)}"
                                id="searchNewsCategory"
                                optionKey="id"
                                optionValue="name"
                                noSelection="['': ' -- Select Category -- ']"/>
                    </div>
                </div>

                <div class="col-sm-2">
                    <div class="form-group ">

                        <g:select
                                name="isTopNews"
                                class="form-control"
                                from="['Yes', 'No']"
                                id="isTopNews"
                                noSelection="['': ' -- Top News -- ']"/>
                    </div>
                </div>





                <div class="col-sm-1">
                    <div class="form-group">
                    <g:submitToRemote
                            class="btn btn-success pull-right"
                            value="Search"
                            before="showLoader('news-list-div')"
                            update="news-list-div" />
                    </div>
                </div>
            </div>
        </form>
    </div>

</div>

<script>
    $(document).ready(function (){
        $('#searchNewsStatus').select2()
        $('#associationId').select2()
        $('#associationId').select2()
        $('#isTopNews').select2()
        $('#searchNewsCategory').select2()



        let selectedCategory = $("#searchNewsCategory").val()
        let ids = $("#associationId").val()
        var selectedName = $("#associationId").find("option:selected").text();
        if(selectedName === 'GCCI') {
            $.ajax({
                url: "${createLink(controller: 'news', action: 'getCategoriesByAssociations')}",
                type: 'GET',

                data: {id: ids},
                success: function (response) {
                    populateDropdownEdit(response);
                },
                error: function (xhr, status, error) {
                }
            });
        } else {
            $.ajax({
                url: "${createLink(controller: 'news', action: 'getOldCategoriesByAssociations')}",
                type: 'GET',

                data: {id: ids},
                success: function (response) {
                    populateDropdownEdit(response);
                },
                error: function (xhr, status, error) {
                }
            });

        }
        function populateDropdownEdit(data) {
            var select = $('#searchNewsCategory');

            select.empty();

            select.append('<option value=""> -- Select Category-- </option>');
            $.each(data, function (index, option) {
                if (option?.name === selectedCategory) {
                    select.append('<option selected value="' + option.name + '">' + option.name + '</option>');
                } else {
                    select.append('<option value="' + option.name + '">' + option.name + '</option>');
                }
            });
        }


        $("#associationId").on("change", function () {
            let ids = $(this).val()

            // let name = $(this)
            var selectedName = $(this).find("option:selected").text();
            console.log("selectedName: "+selectedName)
            if(selectedName === 'GCCI') {
                $.ajax({
                    url: "${createLink(controller: 'news', action: 'getCategoriesByAssociations')}",
                    type: 'GET',

                    data: {id: ids},
                    success: function (response) {
                        populateDropdown(response);
                    },
                    error: function (xhr, status, error) {
                    }
                });
            } else {
                $.ajax({
                    url: "${createLink(controller: 'news', action: 'getOldCategoriesByAssociations')}",
                    type: 'GET',

                    data: {id: ids},
                    success: function (response) {
                        populateDropdown(response);
                    },
                    error: function (xhr, status, error) {

                    }
                });

            }
        });


        function populateDropdown(data) {
            var select = $('#searchNewsCategory');  // Replace 'yourDropdownId' with the actual ID of your select dropdown
            // Clear existing options
            select.empty();
            // Iterate over the data and add options to the dropdown
            select.append('<option value=""> -- Select Category-- </option>');
            $.each(data, function (index, option) {
                select.append('<option value="' + option.name + '">' + option.name + '</option>');
            });
        }



      /*  $('#searching').click(function (){
            var children = $('#searching').children(".pull-right").children(".clickable")
            var $this = $(this);
            if (!$this.hasClass('panel-collapsed')) {
                $this.parents('.panel').find('.panel-body').slideUp();
                $this.addClass('panel-collapsed');
                children.removeClass('ti-angle-up').addClass('ti-angle-down')
                $('.showhide').attr('title', 'Show Panel content');
            } else {
                $this.parents('.panel').find('.panel-body').slideDown();
                $this.removeClass('panel-collapsed');
                children.removeClass('ti-angle-down').addClass('ti-angle-up')
                $('.showhide').attr('title', 'Hide Panel content');
            }
        })*/
    })
</script>