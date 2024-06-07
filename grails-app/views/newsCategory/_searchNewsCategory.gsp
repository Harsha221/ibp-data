<%@ page import="com.ibp.admin.Association;"%>




<div class="panel ">

%{--    <div class="panel-heading panel-collapsed" id="searching">--}%
%{--        <h3 class="panel-title" ><i class="fa fa-fw ti-search"></i> Search</h3>--}%
%{--        <span class="pull-right">--}%
%{--            <i class="fa fa-fw ti-angle-down panel-collapsed clickable"></i>--}%
%{--            --}%%{----}%%{--<i class="fa fa-fw ti-close removepanel clickable"></i>--}%
%{--        </span>--}%
%{--    </div>--}%

    <div class="panel-body" >
        <form name="categorySearchForm">

            <div class="row">

                <div class="col-sm-2">
                    <div class="form-group">
                        <input type="text" name="name" id="name" class="form-control input-md" placeholder="Name"
                               value="${params?.name}">
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
                                name="searchNewsCategoryStatus"
                                class="form-control"
                                from="['Activated', 'Deactivated']"
                                id="searchNewsCategoryStatus"
                                noSelection="['': ' -- Select Status -- ']"/>
                    </div>
                </div>







                <div class="col-sm-1">
                    <g:submitToRemote
                            class="btn btn-success pull-right"
                            value="Search"
                            before="showLoader('newsCategory-list-div')"
                            update="newsCategory-list-div" />
                </div>
            </div>
        </form>
    </div>

</div>

<script>
    $(document).ready(function (){
        $('#associationId').select2()
        $('#searchNewsCategoryStatus').select2()
    })
</script>