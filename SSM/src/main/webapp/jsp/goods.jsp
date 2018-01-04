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
		<title>商品管理首页</title>
		
		<link rel="stylesheet" type="text/css" href="easyui/themes/default/easyui.css">
		<link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">
		<script type="text/javascript" src="js/jquery-1.8.0.min.js"></script>
		<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="js/common.js"></script>
	</head>
	 <script type="text/javascript"> 
	  
	  $( function() {	
	     
	  } );
    
      function reGoodsFlash(){
         //reload:重新执行url，condition是url中的参数  
            $("#searchGoodsFm").form("clear"); 
             $("#GoodsDg").datagrid({
	           url:"getGoods.action"      	     
	         }); 

        
        }
      function doGoodsSearch(){
	      $("#GoodsDg").datagrid({
	           url:"getGoodsBy.action",      
	           queryParams: {  
	              name: $("#name1").val(),
	              categoryId: $("#categoryId1").val(),
	          }  
	      });  
	   } 
    function addGoodsWin(){
         setClear();
         $("#GoodsDg").datagrid("uncheckAll");         
         $( "#addGoods" ).window("open").window("setTitle", "新增");
         //$( "#addGoods" ).window("open"); 
      }
    function closeGoodsWin(){
         $( "#addGoods" ).window("close");
         $("#GoodsFm").form("clear"); 
      }

    function saveGoods(){
	     var grid = $("#GoodsDg"); 
	     var fm = $("#GoodsFm");	   
	     var addWin = $( "#addGoods" );
	     //var fValue = $("#myFile").val(); 
	     //var fValue = $("#pics1").val();
	     var fValue = $("#pics2").filebox('getValue');
	     //alert($("#pics1").filebox('getValue'));
 	     /* fm.form("submit", {
	    	 type: "post",
	    	 url: "${pageContext.request.contextPath}/addGoods.action",
	    	 success: function(data) {
	    		 
	    	 }
	     });  */
	    if(checkFileExt(fValue)){
	    	 commonSaveUpload(fm,"${pageContext.request.contextPath}/addGoods.action",addWin,grid);  
      	 }else{
      	 	$.messager.alert("系统提示", "上传文件类型不合法！");
      	 }        
     }
     
    
    function deleteGoods() {
        var grid = $("#GoodsDg"); 
     	commonBatchOperate(grid, "${pageContext.request.contextPath}/delGoods.action","确认删除所选数据吗？");
     }
    
    function editGoodsWin(){
       var grid = $("#GoodsDg"); 
	   var fm = $("#GoodsFm");
	   var addWin = $( "#addGoods" );  
       openEditWin(grid,addWin,fm);
       /* var editWin = addWin;
	   var selectedRows = grid.datagrid("getSelections");
	     if (selectedRows.length != 1) {
	         $.messager.alert("系统提示", "请选择一条要编辑的数据！");
	         return;
	     }
	     var row = selectedRows[0];
	     editWin.window("open").window("setTitle", "编辑信息");
	     fm.form("load", row);
	     $('#pics').attr('src',row.pics1); */
	     
     }
     
     function setClear(){
        $("#id").val("0");
     	$("#pics").val("");
	    $("#contents").val("请填写");
	    $("#titles").val("");
     }
     
    </script>  
	<body class="easyui-layout">
		<table id="GoodsDg" title="商品列表" class="easyui-datagrid" style="width:1750px;height:865px"
			url="getGoods.action"
			toolbar="#toolbar" pagination="true" rownumbers="true"  singleSelect="false">
			<thead>
				<tr>
				    <th field="ck" checkbox="true"></th>
					<!-- <th field="id" width="50">id</th> -->
					<th field="category" width="150" >商品分类</th>
					<th field="name" width="150">商品名称</th>					
					<!-- <th field="pics" width="250">图片</th> -->
					<th field="img" width="110" formatter="showImg">图片</th>
					<th field="content" width="400">商品链接</th>
					<th field="cjsj" width="150">创建时间</th>
				    <th field="xgsj" width="150">修改时间</th>

				</tr>
			</thead>
		</table>
		<div id="toolbar" style="padding:3px">
			<div style="padding:3px">
				<a href="#" class="easyui-linkbutton" iconCls="icon-add"   onclick="addGoodsWin()">新增商品</a>
				<a href="#" class="easyui-linkbutton" iconCls="icon-edit"  onclick="editGoodsWin()">修改商品</a>
				<a href="#" class="easyui-linkbutton" iconCls="icon-remove"   onclick="deleteGoods()">删除商品</a>
		    </div>
		    <div style="padding:3px">
		        <form id="searchGoodsFm">  					
					<span>商品分类:</span>	
					<input id="categoryId1" name="categoryId1" class="easyui-combobox" data-options="    
					            valueField: 'id',    
						        textField: 'name',    
						        url: 'getGoodCategoryCol.action',
						        method:'get',						     
                                panelHeight:'auto'
                              "/> &nbsp;&nbsp;
                    <span>商品名称:</span>
					<input id="name1" name="name1" style="line-height:18px;border:1px solid #95b9e7">&nbsp;&nbsp;				
					<a href="#" class="easyui-linkbutton" iconCls="icon-search"  onclick="doGoodsSearch()">搜索</a>&nbsp;&nbsp;				
					<a href="#" class="easyui-linkbutton" iconCls="icon-reload"  onclick="reGoodsFlash()">刷新</a>
				 </form>
			</div>
		</div>
       <div id="addGoods" class="easyui-window" title="新增商品"  closed = "true" style="width:500px;height:400px;">
			 <form method="post" id="GoodsFm"  enctype="multipart/form-data" text-align:left>
                <table cellspacing="8px;"> 
                   <tr>                       
                        <td>
                        	<input type="hidden" id="id" name="id" value="0"/>
                        </td>
                    </tr> 
                    <tr> 
                        <td>商品分类：</td>
                        <td> 
                        <input id="categoryId" name="categoryId" class="easyui-combobox" data-options="    
					            valueField: 'id',    
						        textField: 'name',    
						        url: 'getGoodCategoryCol.action',
						        method:'get',
						        required:true,    
                                panelHeight:'auto'
                              "/> &nbsp;<span style="color: red">*</span>
                        </td>
                    </tr>               
                    <tr>
                        <td>商品名称：</td>
                        <td><input type="text" id="name" name="name"
                            class="easyui-validatebox" required="true" />&nbsp;<span
                            style="color: red">*</span>
                        </td>
                    </tr>
                    <tr>
                        <td>商品图片：</td>
                        <td>
                        	<input id="pics2" name="pics2" class="easyui-filebox"  style="width:200px;"/>&nbsp;<span
                            style="color: red">*</span>
                        </td>
                    </tr>                            
                    <tr>
                       <td>商品链接：</td>
                        <td><textarea class="easyui-textbox" required="true" id="content" name="content" data-options="multiline:true" value="请填写" style="width:300px;height:100px;white-space:pre-wrap"></textarea>
                        </td>
                    </tr>                
                        
                </table>
				<div style="padding:5px;text-align:center;">
					<a href="#" class="easyui-linkbutton" icon="icon-ok" onclick="saveGoods()">保存</a>&nbsp;&nbsp;&nbsp;&nbsp;
					<a href="#" class="easyui-linkbutton" icon="icon-cancel"  onclick="closeGoodsWin()">取消</a>
				</div>
			</form>
	  </div>
	  	  
	<style>
	  	#GoodsFm{
	  		margin:0 auto !important;
	  		width: 400px
	  	}
	  .datagrid-btable tr{height: 32px;}
	  	
	  </style>
  </body>

	
</html>
