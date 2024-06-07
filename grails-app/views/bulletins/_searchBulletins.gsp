<%@ page import="com.ibp.admin.Association;"%>




<div class="panel ">

    <div class="panel-heading panel-collapsed" id="searching">
        <h3 class="panel-title" ><i class="fa fa-fw ti-search"></i> Search</h3>
        <span class="pull-right">
            <i class="fa fa-fw ti-angle-down panel-collapsed clickable"></i>
            %{--<i class="fa fa-fw ti-close removepanel clickable"></i>--}%
        </span>
    </div>

    <div class="panel-body" style="display: none">
        <form name="categorySearchForm">

            <div class="row">

                <div class="col-sm-2">
                    <div class="form-group">
                        <input type="text" name="name" id="name" class="form-control input-md" placeholder="Title"
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
                                name="searchBulletinsStatus"
                                class="form-control"
                                from="['Activated', 'Deactivated']"
                                id="searchBulletinsStatus"
                                noSelection="['': ' -- Select Status -- ']"/>
                    </div>
                </div>







                <div class="col-sm-12">
                    <g:submitToRemote
                            class="btn btn-success pull-right"
                            value="Search"
                            before="showLoader('bulletins-list-div')"
                            update="bulletins-list-div" />
                </div>
            </div>
        </form>
    </div>

</div>

<script>
    $(document).ready(function (){
        $('#searching').click(function (){
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
        })
    })
</script>