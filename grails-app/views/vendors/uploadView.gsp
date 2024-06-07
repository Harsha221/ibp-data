<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
</head>
<body>
<div class="row">
    <div>
        <div  class="col-sm-6 col-md-6 col-lg-3 up_data">
            <div  class="widget-bg-color-icon card-box">
                <div class="bg-icon pull-left">
                    <i class="fa fa-exclamation-circle text-info"></i>
                </div>
                <div class="col text-right">
                    <h3 class="text-info row-md-6"><b id="widget_count1">${vendorUpload?.totalCount ?: '0'}</b></h3>
                    <p >Total</p>
                </div>
            </div>
        </div>
        <div  class="col-sm-6 col-md-6 col-lg-3 up_data">
            <div  class="widget-bg-color-icon card-box border_success">
                <div class="bg-icon pull-left">
                    <i class="fa fa-check-circle text-success"></i>
                </div>
                <div class="text-right">
                    <h3 class="text-success"><b id="widget_count3">${vendorUpload?.successCount ?: '0'}</b></h3>
                    <p>Processed</p>
                </div>
            </div>
        </div>
        <div  class="col-sm-6 col-md-6 col-lg-3 up_data">
            <div  class="widget-bg-color-icon card-box border_danger">
                <div  class="bg-icon pull-left">
                    <i class="fa fa-times-circle-o text-danger"></i>
                </div>
                <div class="text-right">
                    <h3 class="text-danger"><b id="widget_count4">${vendorUpload?.failedCount ?: 0}</b></h3>
                    <p>Failed</p>
                </div>
            </div>
        </div>
    </div>
    <div class="col-md-12">
        <g:render template="searchUploadData"/>
    </div>

    <div class="col-md-12" style="display: flex;justify-content: space-between">
        <label style="display: flex; padding-right: 10px">Export Failed : <export:formats formats="['csv']"
                                                                                              action="exportFailedCSV"
                                                                                              params="['id': vendorUpload?.id]" />
        </label>
    </div>
    <div class="col-md-12 m-t-10" id="vendor-upload-data-list-div">
        <g:render template="uploadDataList" model="[vendorUploadDataList: vendorUploadDataList]"/>
    </div>
</div>
</body>
</html>
