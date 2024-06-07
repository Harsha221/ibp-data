<section class="sidebar" style="max-height: calc(153vh - 445px); overflow-y: auto;">
    <div id="menu" role="navigation">
        <ul class="navigation">
        %{--<li class="${controllerName == 'home' ? 'active' : ''}">
            <g:link url="/">
                <i class="menu-icon fa fa-dashboard"></i>
                <span class="mm-text ">Dashboard</span>
            </g:link>
        </li>--}%
            <sec:access url="/watiIntegration/*">
                <li class="${controllerName == 'watiIntegration' ? 'active' : ''}">
                    <g:link controller="watiIntegration">
                        <i class="menu-icon fa fa-user"></i><span class="mm-text ">watiIntegration</span>
                    </g:link>
                </li>
            </sec:access>
            <sec:access url="/vendors/*">
                <li class="menu-dropdown ${controllerName == 'vendors' ? 'active' : ''}">
                    <a href="javascript:void(0)">
                        <i class="menu-icon fa fa-group"></i>
                        <span>Vendor Data Verification</span>
                        <span class="fa arrow"></span>
                    </a>
                    <ul class="sub-menu">
                        <li class="${controllerName == 'vendors' && (actionName == 'index' || actionName == 'create' || actionName == 'edit') ? 'active' : ''}">
                            <g:link controller="vendors">
                                <i class="menu-icon fa fa-list-alt"></i><span class="mm-text ">List</span>
                            </g:link>
                        </li>
                        <li class="${controllerName == 'vendors' && actionName == 'uploads' ? 'active' : ''}">
                            <sec:link controller="vendors" action="uploads">
                                <i class="menu-icon fa fa-upload"></i><span class="mm-text ">Uploads</span>
                            </sec:link>
                        </li>
                        <li class="${controllerName == 'vendors' && (actionName == 'businessList') ? 'active' : ''}">
                            <g:link controller="vendors" action="businessList">
                                <i class="menu-icon fa fa-list-alt"></i><span class="mm-text ">Business List</span>
                            </g:link>
                        </li>
                    </ul>
                </li>
            </sec:access>
            <sec:access url="/role/*">
                <li class="${controllerName == 'role' ? 'active' : ''}">
                    <g:link controller="role">
                        <i class="menu-icon fa fa-user"></i><span class="mm-text ">Role Management</span>
                    </g:link>
                </li>
            </sec:access>
            <sec:access url="/user/*">
                <li class="${controllerName == 'user' ? 'active' : ''}">
                    <g:link controller="user">
                        <i class="menu-icon fa fa-user"></i><span class="mm-text ">User Management</span>
                    </g:link>
                </li>
            </sec:access>
            <sec:access url="/category/*">
                <li class="menu-dropdown ${controllerName == 'category' ? 'active' : ''}">
                    <a href="javascript:void(0)">
                        <i class="menu-icon fa fa-tags"></i>
                        <span>Category Management</span>
                        <span class="fa arrow"></span>
                    </a>
                    <ul class="sub-menu">
                        <li class="${controllerName == 'category' && actionName == 'index' ? 'active' : ''}">
                            <g:link controller="category">
                                <i class="menu-icon fa fa-tag"></i><span class="mm-text ">Category</span>
                            </g:link>
                        </li>
                        <sec:access url="/category/subcategory/*">
                            <li class="${controllerName == 'category' && actionName == 'subcategory' ? 'active' : ''}">
                                <g:link controller="category" action="subcategory">
                                    <i class="menu-icon fa fa-tag"></i><span class="mm-text ">SubCategory</span>
                                </g:link>
                            </li>
                        </sec:access>
                    </ul>
                </li>
            </sec:access>
            <sec:access url="/product/*">
                <li class="menu-dropdown ${controllerName == 'product' ? 'active' : ''}">
                    <a href="javascript:void(0)">
                        <i class="menu-icon fa fa-list-ul"></i>
                        <span>Product Management</span>
                        <span class="fa arrow"></span>
                    </a>
                    <ul class="sub-menu">
                        <li class="${controllerName == 'product' && actionName == 'index' ? 'active' : ''}">
                            <g:link controller="product">
                                <i class="menu-icon fa fa-list-alt"></i><span class="mm-text ">List</span>
                            </g:link>
                        </li>
                        <sec:access url="//product/uploads/**">
                            <li class="${controllerName == 'product' && actionName == 'createUpload' ? 'active' : ''}">
                                <g:link controller="product" action="createUpload">
                                    <i class="menu-icon fa fa-upload"></i><span class="mm-text ">Uploads</span>
                                </g:link>
                            </li>
                        </sec:access>
                    </ul>
                </li>
            </sec:access>
            <sec:access url="/advertisement/*">
                <li class="${controllerName == 'advertisement' ? 'active' : ''}">
                    <g:link controller="advertisement">
                        <i class="menu-icon fa fa-newspaper-o"></i>
                        <span class="mm-text ">Advertisements</span>
                    </g:link>
                </li>
            </sec:access>
            <sec:access url="/association/*">
                <li class="${controllerName == 'association' ? 'active' : ''}">
                    <g:link controller="association">
                        <i class="menu-icon ti-desktop"></i>
                        <span class="mm-text ">Association</span>
                    </g:link>
                </li>
            </sec:access>


        %{--        <sec:access url="/association/*">--}%
        %{--            <li class="menu-dropdown ${controllerName == 'association' || 'homeBanners' || 'aboutCommittees' ? 'active' : ''}">--}%
        %{--                <a href="javascript:void(0)">--}%
        %{--                    <i class="menu-icon ti-check-box"></i>--}%
        %{--                    <span>Association Management</span>--}%
        %{--                    <span class="fa arrow"></span>--}%
        %{--                </a>--}%
        %{--                <ul class="sub-menu">--}%
        %{--                    <li class="${controllerName == 'association' ? 'active' : ''}">--}%
        %{--                        <g:link controller="association">--}%
        %{--                            <i class="menu-icon ti-desktop"></i><span class="mm-text ">Association</span>--}%
        %{--                        </g:link>--}%
        %{--                    </li>--}%
        %{--                    <li class="${controllerName == 'homeBanners' && actionName == 'index' ? 'active' : ''}">--}%
        %{--                        <g:link controller="homeBanners">--}%
        %{--                            <i class="menu-icon ti-layout-list-large-image"></i><span class="mm-text ">Home Banners</span>--}%
        %{--                        </g:link>--}%
        %{--                    </li>--}%
        %{--                    <li class="${controllerName == 'aboutCommittees' && actionName == 'index' ? 'active' : ''}">--}%
        %{--                        <g:link controller="aboutCommittees">--}%
        %{--                            <i class="menu-icon ti-layout-list-large-image"></i><span class="mm-text ">About Committees</span>--}%
        %{--                        </g:link>--}%
        %{--                    </li>--}%
        %{--                    <li class="${controllerName == 'event' && actionName == 'index' ? 'active' : ''}">--}%
        %{--                        <g:link controller="event">--}%
        %{--                            <i class="menu-icon ti-layout-list-large-image"></i><span class="mm-text ">Event</span>--}%
        %{--                        </g:link>--}%
        %{--                    </li>--}%
        %{--                    <li class="${controllerName == 'bulletins' && actionName == 'index' ? 'active' : ''}">--}%
        %{--                        <g:link controller="bulletins">--}%
        %{--                            <i class="menu-icon ti-layout-list-large-image"></i><span class="mm-text ">Bulletins</span>--}%
        %{--                        </g:link>--}%
        %{--                    </li>--}%
        %{--                    <li class="${controllerName == 'videoGallery' && actionName == 'index' ? 'active' : ''}">--}%
        %{--                        <g:link controller="videoGallery">--}%
        %{--                            <i class="menu-icon ti-layout-list-large-image"></i><span class="mm-text ">Video Gallery</span>--}%
        %{--                        </g:link>--}%
        %{--                    </li>--}%
        %{--                </ul>--}%
        %{--            </li>--}%

        %{--        </sec:access>--}%
        %{-- <li class="${controllerName == 'leadingClients' ? 'active' : ''}">
             <g:link url="/">
                 <i class="menu-icon ti-desktop"></i>
                 <span class="mm-text ">Leading Clients</span>
             </g:link>
         </li>
         <li class="${controllerName == 'leadingClients' ? 'active' : ''}">
             <g:link url="/">
                 <i class="menu-icon ti-desktop"></i>
                 <span class="mm-text ">Business Listing</span>
             </g:link>
         </li>--}%


        %{--            <sec:access url="/news/*" >--}%
        %{--            <li class="menu-dropdown ${controllerName == 'news' && 'newsCategory' ? 'active' : ''}">--}%
        %{--                <a href="javascript:void(0)">--}%
        %{--                    <i class="menu-icon ti-check-box"></i>--}%
        %{--                    <span>News Management</span>--}%
        %{--                    <span class="fa arrow"></span>--}%
        %{--                </a>--}%

        %{--                <ul class="sub-menu">--}%
        %{--                    <sec:access url="/newsCategory/*">--}%
        %{--                        <li class="${controllerName == 'newsCategory' && actionName == 'index' ? 'active' : ''}">--}%
        %{--                            <g:link controller="newsCategory">--}%
        %{--                                <i class="menu-icon ti-layout-list-large-image"></i><span class="mm-text ">News Category</span>--}%
        %{--                            </g:link>--}%
        %{--                        </li>--}%
        %{--                    </sec:access>--}%


        %{--                 <li class="${controllerName == 'news' && actionName == 'index' ? 'active' : ''}">--}%
        %{--                   <g:link controller="news">--}%
        %{--                      <i class="menu-icon fa ti-layout-list-large-image"></i><span class="mm-text ">News</span>--}%
        %{--                   </g:link>--}%
        %{--                 </li>--}%
        %{--                </ul>--}%

        %{--            </li>--}%
        %{--        </sec:access>--}%
        %{--            <sec:access url="/homeBanners/*">--}%
        %{--                <li class="${controllerName == 'homeBanners' ? 'active' : ''}">--}%
        %{--                    <g:link controller="homeBanners">--}%
        %{--                        <i class="menu-icon fa fa-desktop"></i><span class="mm-text ">Home Banners</span>--}%
        %{--                    </g:link>--}%
        %{--                </li>--}%
        %{--            </sec:access>--}%
        %{--            <sec:access url="/blog/*">--}%
        %{--                <li class="${controllerName == 'blog' ? 'active' : ''}">--}%
        %{--                    <g:link controller="blog">--}%
        %{--                        <i class="menu-icon fa fa-bold"></i><span class="mm-text ">Blog</span>--}%
        %{--                    </g:link>--}%
        %{--                </li>--}%
        %{--            </sec:access>--}%

        %{--            <sec:access url="/event/*">--}%
        %{--                <li class="${controllerName == 'event' ? 'active' : ''}">--}%
        %{--                    <g:link controller="event">--}%
        %{--                        <i class="menu-icon ti-desktop"></i>--}%
        %{--                        <span class="mm-text ">Event</span>--}%
        %{--                    </g:link>--}%
        %{--                </li>--}%
        %{--            </sec:access>--}%

        %{--            <sec:access url="/department/*">--}%
        %{--            <li class="${controllerName == 'department' ? 'active' : ''}">--}%
        %{--                <g:link controller="department">--}%
        %{--                    <i class="menu-icon ti-layout-list-large-image"></i><span class="mm-text ">Department Management</span>--}%
        %{--                </g:link>--}%
        %{--            </li>--}%
        %{--            </sec:access>--}%
        </ul>
        <!-- / .navigation -->
    </div>
    <!-- menu -->
</section>
