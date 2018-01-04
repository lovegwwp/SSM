<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %> 
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";  
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>商品分类管理首页</title>
		
		<link rel="stylesheet" type="text/css" href="easyui/themes/default/easyui.css">
		<link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">
		<script type="text/javascript" src="js/jquery-1.8.0.min.js"></script>
		<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="js/common.js"></script>
	</head>
	 <script type="text/javascript"> 
	  
	  $( function() {	
	     
	  } );
    
      function reGoodCategoryFlash(){
         //reload:重新执行url，condition是url中的参数  
            $("#searchGoodCategoryFm").form("clear"); 
             $("#GoodCategoryDg").datagrid({
	           url:"getGoodCategory.action"      	     
	         }); 

        
        }
      function doGoodCategorySearch(){
	      $("#GoodCategoryDg").datagrid({
	           url:"getGoodCategoryBy.action",      
	           queryParams: {  
	              name: $("#name1").val(),  	            
	          }  
	      });  
	   } 
    function addGoodCategoryWin(){
         setClear();
         $("#GoodCategoryDg").datagrid("uncheckAll");         
         $( "#addGoodCategory" ).window("open").window("setTitle", "新增");
         setClear();
         //$( "#addGoodCategory" ).window("open"); 
      }
    function closeGoodCategoryWin(){
         $( "#addGoodCategory" ).window("close");
         $("#GoodCategoryFm").form("clear"); 
      }

    function saveGoodCategory(){
	     var grid = $("#GoodCategoryDg"); 
	     var fm = $("#GoodCategoryFm");	   
	     var addWin = $( "#addGoodCategory" );
	     //var fValue = $("#myFile").val(); 
	     //var fValue = $("#pics1").val();
	    // var fValue = $("#pics2").filebox('getValue');
	     //alert($("#pics1").filebox('getValue'));
 	     /* fm.form("submit", {
	    	 type: "post",
	    	 url: "${pageContext.request.contextPath}/addGoodCategory.action",
	    	 success: function(data) {
	    		 
	    	 }
	     });  */
	   // if(checkFileExt(fValue)){
	    	 commonSaveUpload(fm,"${pageContext.request.contextPath}/addGoodCategory.action",addWin,grid);  
      	 //}else{
      	 	//$.messager.alert("系统提示", "上传文件类型不合法！");
      	// }        
     }
     
    
    function deleteGoodCategory() {
        var grid = $("#GoodCategoryDg"); 
     	commonBatchOperate(grid, "${pageContext.request.contextPath}/delGoodCategory.action","确认删除所选数据吗？");
     }
    
    function editGoodCategoryWin(){
       var grid = $("#GoodCategoryDg"); 
	   var fm = $("#GoodCategoryFm");
	   var addWin = $( "#addGoodCategory" );  
       openEditWin(grid,addWin,fm);
	     
     }
     
     function setClear(){
        $("#id").val("0");
     	$("#name").val("");
     }
     
    </script>  
	<body class="easyui-layout">
		<table id="GoodCategoryDg" title="新闻列表" class="easyui-datagrid" style="width:1750px;height:865px"
			url="getGoodCategory.action"
			toolbar="#toolbar" pagination="true" rownumbers="true"  singleSelect="false">
			<thead>
				<tr>
				    <th field="ck" checkbox="true"></th>
					<th field="id" width="80">分类ID</th>				
					<th field="name" width="200">分类名称</th>
					<th field="cjsj" width="150">创建时间</th>
				    <th field="xgsj" width="150">修改时间</th>

				</tr>
			</thead>
		</table>
		<div id="toolbar" style="padding:3px">
			<div style="padding:3px">
				<a href="#" class="easyui-linkbutton" iconCls="icon-add"   onclick="addGoodCategoryWin()">新增分类</a>
				<a href="#" class="easyui-linkbutton" iconCls="icon-edit"  onclick="editGoodCategoryWin()">修改分类</a>
				<a href="#" class="easyui-linkbutton" iconCls="icon-remove"   onclick="deleteGoodCategory()">删除分类</a>
		    </div>
		    <div style="padding:3px">
		        <form id="searchGoodCategoryFm">  
					<span>标题:</span>
					<input id="name1" name="name1" style="line-height:18px;border:1px solid #95b9e7">&nbsp;&nbsp;					
					<a href="#" class="easyui-linkbutton" iconCls="icon-search"  onclick="doGoodCategorySearch()">搜索</a>&nbsp;&nbsp;				
					<a href="#" class="easyui-linkbutton" iconCls="icon-reload"  onclick="reGoodCategoryFlash()">刷新</a>
				 </form>
			</div>
		</div>
       <div id="addGoodCategory" class="easyui-window" title="新增分类"  closed = "true" style="width:400px;height:200px;">
			 <form method="post" id="GoodCategoryFm"  enctype="multipart/form-data" text-align:left>
                <table cellspacing="8px;"> 
                   <tr>                       
                        <td>
                        	<input type="hidden" id="id" name="id" value="0"/>
                        </td>
                    </tr>                            
                    <tr>
                        <td>商品分类：</td>
                        <td><input type="text" id="name" name="name"
                            class="easyui-validatebox" required="true" />&nbsp;<span
                            style="color: red">*</span>
                        </td>
                    </tr>          
                        
                </table>
				<div style="padding:5px;text-align:center;">
					<a href="#" class="easyui-linkbutton" icon="icon-ok" onclick="saveGoodCategory()">保存</a>&nbsp;&nbsp;&nbsp;&nbsp;
					<a href="#" class="easyui-linkbutton" icon="icon-cancel"  onclick="closeGoodCategoryWin()">取消</a>
				</div>
			</form>
	  </div>
	  	  
	<style>
	  	#GoodCategoryFm{
	  		margin:0 auto !important;
	  		width: 300px
	  	}
	  .datagrid-btable tr{height: 32px;}
	  	
	  </style>
  </body>

	
</html>
