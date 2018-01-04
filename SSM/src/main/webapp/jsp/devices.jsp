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
		<title>设备管理首页</title>
		
		<link rel="stylesheet" type="text/css" href="easyui/themes/default/easyui.css">
		<link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">
		<link rel="stylesheet" type="text/css" href="css/fgn.css">
		<script type="text/javascript" src="js/jquery-1.8.0.min.js"></script>
		<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="js/common.js"></script>
	</head>
	 <script type="text/javascript"> 
	  
	  $( function() {	
	     
	  } );
    
      function reDevFlash(){
         //reload:重新执行url，condition是url中的参数  
            $("#searchDevFm").form("clear");  
            $("#devDg").datagrid({
 	           url:"getDev.action"      	     
 	         }); 
            //$("#devDg").datagrid("reload");         
        }
      function doDevSearch(){
	      $("#devDg").datagrid({
	           url:"getDevBy.action",      
	           queryParams: {  
	              macId: $("#macId1").val(), 
	              patID: $("#patID1").val(), 
	              name: $("#name1").val()
	          }  
	      });  
	   } 
    function addDevWin(){
    	  $("#devDg").datagrid("uncheckAll");         
         $( "#addDev" ).window("open").window("setTitle", "新增");
        // $( "#addDev" ).window("open"); 
      }
    function closeDevWin(){
         $( "#addDev" ).window("close");
         $("#macId").val("");   
      }

    function saveDev(){
	     var grid = $("#devDg"); 
	     var fm = $("#devFm");	   
	     var addWin = $( "#addDev" ); 
	     var myMac ="";
	     myMac =  $("#macId").val();  
	    // isValidate = checkMacId(myMac);
	     //if(isValidate){
      		 commonSaveOperate(fm,"${pageContext.request.contextPath}/addDev.action",addWin,grid); 
      	 //}
      	 //setClear();     	     
     }
    
    function deleteDev() {
        var grid = $("#devDg"); 
     	commonBatchOperate(grid, "${pageContext.request.contextPath}/delDev.action","确认删除所选数据吗？");
     }
    
    function editDevWin(){
     var selectedRows = $("#devDg").datagrid("getSelections");
     if (selectedRows.length != 1) {
         $.messager.alert("系统提示", "请选择一条要编辑的数据！");
         return;
     }
     var row = selectedRows[0];
     var stype = row.type;
     if(stype =='1'){
     	 $.messager.alert("系统提示", "设备已绑定，不能修改！");
         return;
     }
     $( "#addDev" ).window("open").window("setTitle", "编辑用户信息");
     $("#devFm").form("load", row);
     }
    
    
    function gzDev() {
     	 var selectedRowsD = $("#devDg").datagrid("getSelections");
          if (selectedRowsD.length != 1) {
              $.messager.alert("系统提示", "请选择一条数据！");
              return;
          }
          var row = selectedRowsD[0];
          var stype = row.type;
          if(stype !='0'){
          	 $.messager.alert("系统提示", "设备尚未解绑！");
              return;
          }
   	    var grid = $("#devDg"); 
     	commonBatchOperate2(grid, "${pageContext.request.contextPath}/updateDevZt.action?type=2","确认更改所选数据状态吗？");
   }
     
    
    </script>  
	<body class="easyui-layout">
		<table id="devDg" title="设备列表" class="easyui-datagrid" style="width:1750px;height:865px"
			url="getDev.action"
			toolbar="#toolbar" pagination="true" rownumbers="true"  singleSelect="false">
			<thead>
				<tr>
				    <th field="ck" checkbox="true"></th>
					<!-- <th field="id" width="50">id</th> -->
					<th field="macId" width="300">设备号</th>
					<!-- <th field="createdAt" width="200">设备保存时间</th>
					<th field="lastModifyTime" width="200">设备修改时间</th> -->					
					<th field="patId" width="100">绑定病人ID</th>
					<th field="age" width="80">年龄</th>
					<th field="sex" width="80" formatter="formatNumSex" >性别</th>
					<th field="uname" width="100">姓名</th>
					<!-- <th field="bdTime" width="200">绑定时间</th> -->
					<th field="bdsj" width="200">绑定时间</th>
					<th field="type" width="90" formatter="formatIsYes">是否已经绑定</th>
					<th field="cjsj" width="150">创建时间</th>
					<!-- <th field="xgsj" width="150">修改时间</th> -->
					<th field="dlIp" width="150">登录IP</th>
					<th field="dlAddr" width="200">登录地址</th>
				</tr>
			</thead>
		</table>
		<div id="toolbar" style="padding:3px">
			<div style="padding:3px">
				<a href="#" class="easyui-linkbutton" iconCls="icon-add"   onclick="addDevWin()">新增设备</a>
				<a href="#" class="easyui-linkbutton" iconCls="icon-edit"  onclick="editDevWin()">修改设备</a>
				<a href="#" class="easyui-linkbutton" iconCls="icon-remove"   onclick="deleteDev()">删除设备</a>
				<a href="#" class="easyui-linkbutton" iconCls="icon-remove"   onclick="gzDev()">设备故障</a>
		    </div>
		    <div style="padding:3px">
		        <form id="searchDevFm">  
					<span>设备号:</span>
					<input id="macId1" name="macId1" style="line-height:18px;border:1px solid #95b9e7">&nbsp;&nbsp;
					<span>病人ID:</span>
					<input id="patID1" name="patID1" style="line-height:18px;border:1px solid #95b9e7">&nbsp;&nbsp;
					<span>姓名:</span>
					<input id="name1" name="name1" style="line-height:18px;border:1px solid #95b9e7">&nbsp;&nbsp;
					<a href="#" class="easyui-linkbutton" iconCls="icon-search"  onclick="doDevSearch()">搜索</a>&nbsp;&nbsp;				
					<a href="#" class="easyui-linkbutton" iconCls="icon-reload"  onclick="reDevFlash()">刷新</a>
				 </form>
			</div>
		</div>
       <div id="addDev" class="easyui-window" title="设备新增"  closed = "true" style="width:400px;height:200px;">
			 <form method="post" id="devFm" text-align:center>
                <table cellspacing="8px;"> 
                   <tr>                       
                        <td>
                        	<input type="hidden" id="id" name="id" value="0"/>
                        </td>
                    </tr>              
                    <tr>
                        <td>设备号：</td>
                        <td><input type="text" id="macId" name="macId"
                            class="easyui-validatebox" required="true" />&nbsp;<span
                            style="color: red">*</span>
                        </td>
                    </tr>                           
                        
                </table>
				<div style="padding:5px;text-align:center;">
					<a href="#" class="easyui-linkbutton" icon="icon-ok" onclick="saveDev()">保存</a>&nbsp;&nbsp;&nbsp;&nbsp;
					<a href="#" class="easyui-linkbutton" icon="icon-cancel"  onclick="closeDevWin()">取消</a>
				</div>
			</form>
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
