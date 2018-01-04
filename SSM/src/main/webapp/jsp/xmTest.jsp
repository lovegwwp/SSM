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
		<title>第三方训练管理首页</title>
		
		<link rel="stylesheet" type="text/css" href="easyui/themes/default/easyui.css">
		<link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">
		<script type="text/javascript" src="js/jquery-1.8.0.min.js"></script>
		<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="js/common.js"></script>
		<script type="text/javascript" src="js/gridDic.js"></script>
	</head>
	 <script type="text/javascript"> 
	  
	  $( function() {	
		
	  } );
    
      function reTestFlash(){
         //reload:重新执行url，condition是url中的参数  
            $("#searchTestFm").form("clear");  
            //$("#TestDg").datagrid("reload");  
            $("#TestDg").datagrid({
 	           url:"getTest.action",      
 	           queryParams: {  
 	                     
 	          }  
 	      });  
        
        }
      function doTestSearch(){
	      $("#TestDg").datagrid({
	           url:"getTestBy.action",      
	           queryParams: {  
	              titles1: $("#titles1").val(),  	            
	          }  
	      });  
	   } 
    function addTestWin(){
         setClear();     
         $("#TestDg").datagrid("uncheckAll");         
         $( "#addTest" ).window("open").window("setTitle", "新增");
         //$( "#addTest" ).window("open"); 
      }
    function closeTestWin(){
         $( "#addTest" ).window("close");
         $("#TestFm").form("clear"); 
      }

    function saveTest(){
	     var grid = $("#TestDg"); 
	     var fm = $("#TestFm");	   
	     var addWin = $( "#addTest" );
	    // var fValue = $("#myFile").val();  
	   // if(checkFileExt2(fValue)){
	    	 commonSaveUpload(fm,"${pageContext.request.contextPath}/addTest.action",addWin,grid);  
      	// }else{
      	// 	$.messager.alert("系统提示", "上传文件类型不合法！");
      	// } 
	   }       
     
    
    function deleteTest() {
        var grid = $("#TestDg"); 
     	commonBatchOperate(grid, "${pageContext.request.contextPath}/delXmTest.action","确认删除所选数据吗？");
     }
    
    function editTestWin(){
       var grid = $("#TestDg"); 
	   var fm = $("#TestFm");
	   var addWin = $( "#addTest" );  
       openEditWin(grid,addWin,fm);
     }
     
     function setClear(){
        $("#id").val("0");
	    $("#titles").val("");
     }
       
 
    </script>  
	<body class="easyui-layout">
		<table id="TestDg" title="第三方训练内容列表" class="easyui-datagrid" style="width:1750px;height:865px"
			url="getTest.action"
			toolbar="#toolbar" pagination="true" rownumbers="true"  singleSelect="false">
			<thead>
				<tr>
				    <th field="ck" checkbox="true"></th>
					<th field="id" width="50">id</th>
					<!-- <th field="type" width="80" formatter="formatXlType" >训练类型</th> -->
					<!-- <th field="vedioType" width="80"  formatter="formatVedioType" >视频类型</th>
					<th field="itemid" width="80">类目</th> -->
					<th field="titles" width="200">标题</th>
					<!-- <th field="byteSize" width="100">视频大小</th> -->
					<th field="vedio" width="400">程序路径</th>
					<th field="cjsj" width="150">创建时间</th>
					<th field="xgsj" width="150">修改时间</th>

				</tr>
			</thead>
		</table>
		<div id="toolbar" style="padding:3px">
			<div style="padding:3px">
				<a href="#" class="easyui-linkbutton" iconCls="icon-add"   onclick="addTestWin()">新增训练程序</a>
				<a href="#" class="easyui-linkbutton" iconCls="icon-edit"  onclick="editTestWin()">修改训练程序</a>
				<a href="#" class="easyui-linkbutton" iconCls="icon-remove"   onclick="deleteTest()">删除训练程序</a>
		    </div>
		    <div style="padding:3px">
		        <form id="searchTestFm">  
					<span>标题:</span>
					<input id="titles1" name="titles1" style="line-height:18px;border:1px solid #95b9e7">&nbsp;&nbsp;					
					<a href="#" class="easyui-linkbutton" iconCls="icon-search"  onclick="doTestSearch()">搜索</a>&nbsp;&nbsp;				
					<a href="#" class="easyui-linkbutton" iconCls="icon-reload"  onclick="reTestFlash()">刷新</a>
				 </form>
			</div>
		</div>
       <div id="addTest" class="easyui-window" title="新增训练程序"  closed = "true" style="width:500px;height:300px;">
			 <form method="post" id="TestFm"  enctype="multipart/form-data" text-align:left>
                <table cellspacing="8px;"> 
                   <tr>                       
                        <td>
                        	<input type="hidden" id="id" name="id" value="0"/>
                        </td>
                    </tr> 
                   <!--  <tr> 
                        <td>训练类型：</td>
                        <td> 
                        <input id="type" name="type" class="easyui-combobox" data-options="    
					            valueField: 'bz_id',    
						        textField: 'bz_value',    
						        url: 'getClsCo.action?bz_type='+'xlTest_type',
						        method:'get',
						        required:true,    
                                panelHeight:'150'
                              "/> &nbsp;<span style="color: red">*</span>
                        </td>
                    </tr>  -->
                    <tr>
                        <td>标题：</td>
                        <td><input type="text" id="titles" name="titles"
                            class="easyui-validatebox" required="true" />&nbsp;<span
                            style="color: red">*</span>
                        </td>
                    </tr> 
                    <tr>
                        <td>程序路径：</td>
                        <td><input type="text" id="vedio" name="vedio"
                            class="easyui-validatebox" required="true" />&nbsp;<span
                            style="color: red">*</span>
                        </td>
                    </tr> 
                    <!--  <tr>
                        <td>类目ID：</td>
                        <td><input type="text" id="itemid" name="itemid"
                            class="easyui-validatebox" required="true" />&nbsp;<span
                            style="color: red">*</span>
                        </td>
                    </tr>  -->
                    
                  <!--   <tr> 
                        <td>视频类型：</td>
                        <td> 
                        <input id="vedioType" name="vedioType" class="easyui-combobox" data-options="    
					            valueField: 'bz_id',    
						        textField: 'bz_value',    
						        url: 'getClsCo.action?bz_type='+'vedio_type',
						        method:'get',
						        required:true,    
                                panelHeight:'150'
                              "/> &nbsp;<span style="color: red">*</span>
                        </td>
                    </tr>   -->                                          
                  <!--   <tr>
                        <td>视频：</td>
                        <td><input type="file" id="myFile" name="myFile"
                            class="easyui-validatebox" required="true" />&nbsp;<span
                            style="color: red">*</span>
                        </td>
                    </tr>   -->                                          
                        
                </table>
				<div style="padding:5px;text-align:center;">
					<a href="#" class="easyui-linkbutton" icon="icon-ok" onclick="saveTest()">保存</a>&nbsp;&nbsp;&nbsp;&nbsp;
					<a href="#" class="easyui-linkbutton" icon="icon-cancel"  onclick="closeTestWin()">取消</a>
				</div>
			</form>
	  </div>
	  	  
     <style>
	  	#TestFm{
	  		margin:0 auto !important;
	  		width: 400px
	  	}
	  	
	  	 .datagrid-btable tr{height: 32px;}
	  	  	
	  </style>
  </body>

	
</html>
