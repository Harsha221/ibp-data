<div class="panel ">
%{--    <div class="panel-heading panel-collapsed"  id="searching">--}%
%{--        <h3 class="panel-title" ><i class="fa fa-fw ti-search"></i> Search</h3>--}%
%{--        <span class="pull-right">--}%
%{--            <i class="fa fa-fw ti-angle-down panel-collapsed clickable"></i>--}%
%{--            --}%%{--<i class="fa fa-fw ti-close removepanel clickable"></i>--}%
%{--        </span>--}%
%{--    </div>--}%
    <div class="panel-body" >
        <g:render template="searchForm" />
    </div>
</div>

%{--<script>--}%
%{--    $(document).ready(function (){--}%
%{--        $('#searching').click(function (){--}%
%{--            var children = $('#searching').children(".pull-right").children(".clickable")--}%
%{--            var $this = $(this);--}%
%{--            if (!$this.hasClass('panel-collapsed')) {--}%
%{--                $this.parents('.panel').find('.panel-body').slideUp();--}%
%{--                $this.addClass('panel-collapsed');--}%
%{--                children.removeClass('ti-angle-up').addClass('ti-angle-down')--}%
%{--                $('.showhide').attr('title', 'Show Panel content');--}%
%{--            } else {--}%
%{--                $this.parents('.panel').find('.panel-body').slideDown();--}%
%{--                $this.removeClass('panel-collapsed');--}%
%{--                children.removeClass('ti-angle-down').addClass('ti-angle-up')--}%
%{--                $('.showhide').attr('title', 'Hide Panel content');--}%
%{--            }--}%
%{--        })--}%
%{--    })--}%
%{--</script>--}%