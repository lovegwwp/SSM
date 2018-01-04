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
		<title>评测管理首页</title>
		
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
    
      function reXmPcFlash(){
         //reload:重新执行url，condition是url中的参数  
            $("#searchXmPcFm").form("clear");  
            //$("#XmPcDg").datagrid("reload");  
            $("#XmPcDg").datagrid({
 	           url:"getXmPc.action",      
 	           queryParams: {  
 	                     
 	          }  
 	      });  
        
        }
      function doXmPcSearch(){
	      $("#XmPcDg").datagrid({
	           url:"getXmPcBy.action",      
	           queryParams: {  
	              titles1: $("#titles1").val(),  	            
	          }  
	      });  
	   } 
    function addXmPcWin(){
         setClear();     
         $("#XmPcDg").datagrid("uncheckAll");         
         $( "#addXmPc" ).window("open").window("setTitle", "新增");
         //$( "#addXmPc" ).window("open"); 
      }
    function closeXmPcWin(){
         $( "#addXmPc" ).window("close");
         $("#XmPcFm").form("clear"); 
      }

    function saveXmPc(){
	     var grid = $("#XmPcDg"); 
	     var fm = $("#XmPcFm");	   
	     var addWin = $( "#addXmPc" );
	    // var fValue = $("#myFile").val();  
	   // if(checkFileExt2(fValue)){
	    	 commonSaveUpload(fm,"${pageContext.request.contextPath}/addXmPc.action",addWin,grid);  
      	// }else{
      	// 	$.messager.alert("系统提示", "上传文件类型不合法！");
      	// }        
     }
     
    
    function deleteXmPc() {
        var grid = $("#XmPcDg"); 
     	commonBatchOperate(grid, "${pageContext.request.contextPath}/delXmPc.action","确认删除所选数据吗？");
     }
    
    function editXmPcWin(){
       var grid = $("#XmPcDg"); 
	   var fm = $("#XmPcFm");
	   var addWin = $( "#addXmPc" );  
       openEditWin(grid,addWin,fm);
     }
     
     function setClear(){
        $("#id").val("0");
	    $("#titles").val("");
     }
     
 
     
 
    </script>  
	<body class="easyui-layout">
		<table id="XmPcDg" title="评测内容列表" class="easyui-datagrid" style="width:1750px;height:865px"
			url="getXmPc.action"
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
				<a href="#" class="easyui-linkbutton" iconCls="icon-add"   onclick="addXmPcWin()">新增评测</a>
				<a href="#" class="easyui-linkbutton" iconCls="icon-edit"  onclick="editXmPcWin()">修改评测</a>
				<a href="#" class="easyui-linkbutton" iconCls="icon-remove"   onclick="deleteXmPc()">删除评测</a>
		    </div>
		    <div style="padding:3px">
		        <form id="searchXmPcFm">  
					<span>标题:</span>
					<input id="titles1" name="titles1" style="line-height:18px;border:1px solid #95b9e7">&nbsp;&nbsp;					
					<a href="#" class="easyui-linkbutton" iconCls="icon-search"  onclick="doXmPcSearch()">搜索</a>&nbsp;&nbsp;				
					<a href="#" class="easyui-linkbutton" iconCls="icon-reload"  onclick="reXmPcFlash()">刷新</a>
				 </form>
			</div>
		</div>
       <div id="addXmPc" class="easyui-window" title="新增评测"  closed = "true" style="width:500px;height:300px;">
			 <form method="post" id="XmPcFm"  enctype="multipart/form-data" text-align:left>
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
						        url: 'getClsCo.action?bz_type='+'xlXmPc_type',
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
					<a href="#" class="easyui-linkbutton" icon="icon-ok" onclick="saveXmPc()">保存</a>&nbsp;&nbsp;&nbsp;&nbsp;
					<a href="#" class="easyui-linkbutton" icon="icon-cancel"  onclick="closeXmPcWin()">取消</a>
				</div>
			</form>
	  </div>
	  	  
     <style>
	  	#XmPcFm{
	  		margin:0 auto !important;
	  		width: 400px
	  	}
	  	
	  	 .datagrid-btable tr{height: 32px;}
	  	  	
	  </style>
  </body>

	
</html>
