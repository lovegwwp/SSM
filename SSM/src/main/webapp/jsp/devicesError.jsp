<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
		<title>故障设备首页</title>
		
		<link rel="stylesheet" type="text/css" href="easyui/themes/default/easyui.css">
		<link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">
		<script type="text/javascript" src="js/jquery-1.8.0.min.js"></script>
		<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="js/common.js"></script>
	</head>
	 <script type="text/javascript"> 
	  
	  $( function() {	
	     
	  } );
    
      function reDevErrFlash(){
         //reload:重新执行url，condition是url中的参数  
            $("#searchDevErrFm").form("clear");  
            $("#devErrDg").datagrid({
 	           url:"getDevErr.action"      	     
 	         }); 
            //$("#devErrDg").datagrid("reload");         
        }
      function doDevErrSearch(){
	      $("#devErrDg").datagrid({
	           url:"getDevErrBy.action",      
	           queryParams: {  
	              macId: $("#macId2").val(),       
	          }  
	      });  
	   } 

    
    function DevWx() {
    	 var selectedRowsW = $("#devErrDg").datagrid("getSelections");
         if (selectedRowsW.length != 1) {
             $.messager.alert("系统提示", "请选择一条数据！");
             return;
         }
         var row = selectedRowsW[0];
         var stype = row.type;
         if(stype !='2'){
         	 $.messager.alert("系统提示", "设备未故障！");
             return;
         }
         //设备送维修
         upDevZt(3);
     }
    
    function DevHf() {
   	 var selectedRowsH = $("#devErrDg").datagrid("getSelections");
        if (selectedRowsH.length != 1) {
            $.messager.alert("系统提示", "请选择一条数据！");
            return;
        }
        var row = selectedRowsH[0];
        var stype = row.type;
        if(stype !='3'){
        	 $.messager.alert("系统提示", "设备尚未维修！");
            return;
        }
      //设备恢复使用
        upDevZt(0);
    }
    
    function DevBf() {
      	 var selectedRowsE = $("#devErrDg").datagrid("getSelections");
           if (selectedRowsE.length != 1) {
               $.messager.alert("系统提示", "请选择一条数据！");
               return;
           }
           var row = selectedRowsE[0];
           var stype = row.type;
           if(stype !='3'){
           	 $.messager.alert("系统提示", "设备尚未维修！");
               return;
           }
           //设备报废
           upDevZt(4);
       }
    
    
    
    function upDevZt(type){
    	 var grid = $("#devErrDg"); 
    	commonBatchOperate2(grid, "${pageContext.request.contextPath}/updateDevZt.action?type="+type,"确认更改所选数据状态吗？");
    }
    
   
     
    
    </script>  
	<body class="easyui-layout">
		<table id="devErrDg" title="设备列表" class="easyui-datagrid" style="width:1750px;height:865px"
			url="getDevErr.action"
			toolbar="#toolbar" pagination="true" rownumbers="true"  singleSelect="false">
			<thead>
				<tr>
				    <th field="ck" checkbox="true"></th>
					<!-- <th field="id" width="50">id</th> -->
					<th field="macId" width="300">设备号</th>
					<!-- <th field="createdAt" width="200">设备保存时间</th>
					<th field="lastModifyTime" width="200">设备修改时间</th> -->					
					<!-- <th field="patId" width="100">绑定病人ID</th>
					<th field="age" width="80">年龄</th>
					<th field="sex" width="80" formatter="formatNumSex" >性别</th>
					<th field="uname" width="100">姓名</th> -->
					<!-- <th field="bdTime" width="200">绑定时间</th> -->
					<!-- <th field="bdsj" width="200">绑定时间</th> -->
					<th field="type" width="100" formatter="formatIsYes">设备状态</th>
					<!-- <th field="cjsj" width="150">创建时间</th> -->
					<th field="xgsj" width="150">修改时间</th>
					<!-- <th field="dlIp" width="150">登录IP</th>
					<th field="dlAddr" width="200">登录地址</th> -->
				</tr>
			</thead>
		</table>
		<div id="toolbar" style="padding:3px">
			<div style="padding:3px">
				<a href="#" class="easyui-linkbutton" iconCls="icon-add"   onclick="DevWx()">故障维修</a>
				<a href="#" class="easyui-linkbutton" iconCls="icon-edit"  onclick="DevHf()">恢复使用</a>
				<a href="#" class="easyui-linkbutton" iconCls="icon-remove"   onclick="DevBf()">设备报废</a>
			<!-- 	<a href="#" class="easyui-linkbutton" iconCls="icon-remove"   onclick="deleteDev()">删除设备</a> -->
		    </div>
		    <div style="padding:3px">
		        <form id="searchDevErrFm">  
					<span>设备号:</span>
					<input id="macId2" name="macId2" style="line-height:18px;border:1px solid #95b9e7">&nbsp;&nbsp;
					<a href="#" class="easyui-linkbutton" iconCls="icon-search"  onclick="doDevErrSearch()">搜索</a>&nbsp;&nbsp;				
					<a href="#" class="easyui-linkbutton" iconCls="icon-reload"  onclick="reDevErrFlash()">刷新</a>
				 </form>
			</div>
		</div>
       
	 <style>
	  	#devFm{
	  		margin:0 auto !important;
	  		width: 300px
	  	}
	  	
	  	 .datagrid-btable tr{height: 32px;}
	  	  	
	  </style>
  </body>

	
</html>
