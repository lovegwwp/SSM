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
		<title>系统消息首页</title>
		
		<link rel="stylesheet" type="text/css" href="easyui/themes/default/easyui.css">
		<link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">
		<script type="text/javascript" src="js/jquery-1.8.0.min.js"></script>
		<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="js/common.js"></script>
		<script type="text/javascript" src="js/newsDic.js"></script>
	</head>
	 <script type="text/javascript"> 
	  
	  $( function() {	
	     
	  } );
    
      function reKaijiXtNewsFlash(){
         //reload:重新执行url，condition是url中的参数  
            $("#searchKaijiXtNewsFm").form("clear"); 
             $("#KaijiXtNewsDg").datagrid({
	           url:"getKaijiXtNews.action"      	     
	         }); 

        
        }
      function doKaijiXtNewsSearch(){
	      $("#KaijiXtNewsDg").datagrid({
	           url:"getKaijiXtNewsBy.action",      
	           queryParams: {  
	              title1: $("#title1").val(),  	            
	          }  
	      });  
	   } 
    function addKaijiXtNewsWin(){
         setXtClear();
         $("#KaijiXtNewsDg").datagrid("uncheckAll");         
         $( "#addKaijiXtNews" ).window("open").window("setTitle", "新增");
        // $( "#addKaijiXtNews" ).window("open"); 
      }
    function closeKaijiXtNewsWin(){
         $( "#addKaijiXtNews" ).window("close");
         $("#KaijiXtNewsFm").form("clear"); 
      }

    function saveKaijiXtNews(){
	     var grid = $("#KaijiXtNewsDg"); 
	     var fm = $("#KaijiXtNewsFm");	   
	     var addWin = $( "#addKaijiXtNews" );
	     var fValue = $("#myFile").filebox('getValue');
	    // var fValue = $("#myFile").val();  
 	   /*    fm.form("submit", {
	    	 type: "post",
	    	 url: "${pageContext.request.contextPath}/addNews.action",
	    	 success: function(data) {
	    		 
	    	 }
	     });   */
	    if(checkFileExt(fValue)){
	    	 commonSaveUpload(fm,"${pageContext.request.contextPath}/addKaijiXtNews.action",addWin,grid);  
      	 }else{
      	 	$.messager.alert("系统提示", "上传文件类型不合法！");
      	 }        
     }
     
    
    function deleteKaijiXtNews() {
        var grid = $("#KaijiXtNewsDg"); 
     	commonBatchOperate(grid, "${pageContext.request.contextPath}/delXtNews.action","确认删除所选数据吗？");
     }
    
    function editKaijiXtNewsWin(){
       var grid = $("#KaijiXtNewsDg"); 
	   var fm = $("#KaijiXtNewsFm");
	   var addWin = $( "#addKaijiXtNews" );  
       openEditWin(grid,addWin,fm);
     }
     
     function setXtClear(){
        $("#id").val("0");
     	/* $("#pic").val("");
	    $("#contents").val("请填写"); */
	    $("#title").val("");
     }
 
    </script>  
	<body class="easyui-layout">
		<table id="KaijiXtNewsDg" title="开机页列表" class="easyui-datagrid" style="width:1750px;height:865px"
			url="getKaijiXtNews.action"
			toolbar="#toolbar" pagination="true" rownumbers="true"  singleSelect="false">
			<thead>
				<tr>
				    <th field="ck" checkbox="true"></th>
					<!-- <th field="id" width="50">id</th> -->
					<!-- <th field="type" width="80" formatter="formatNewsType">发送类型</th> -->
					<th field="title" width="150">说明</th>
					<th field="pic" width="200">图片</th>
				<!-- 	<th field="byteSize" width="100">图片大小</th> --> 
					 <th field="contents" width="650">文字描述</th> 
					<!-- <th field="ps1" width="200">说明1</th>
					<th field="ps2" width="200">说明2</th> -->
					<th field="cjsj" width="150">创建时间</th>
				<!-- 	<th field="xgsj" width="150">修改时间</th> -->

				</tr>
			</thead>
		</table>
		<div id="toolbar" style="padding:3px">
			<div style="padding:3px">
				<a href="#" class="easyui-linkbutton" iconCls="icon-add"   onclick="addKaijiXtNewsWin()">新增消息</a>
				<a href="#" class="easyui-linkbutton" iconCls="icon-edit"  onclick="editKaijiXtNewsWin()">修改消息</a>
				<a href="#" class="easyui-linkbutton" iconCls="icon-remove"   onclick="deleteKaijiXtNews()">删除消息</a>
		    </div>
		    <div style="padding:3px">
		        <form id="searchKaijiXtNewsFm">  
					<span>标题:</span>
					<input id="title1" name="title1" style="line-height:18px;border:1px solid #95b9e7">&nbsp;&nbsp;					
					<a href="#" class="easyui-linkbutton" iconCls="icon-search"  onclick="doKaijiXtNewsSearch()">搜索</a>&nbsp;&nbsp;				
					<a href="#" class="easyui-linkbutton" iconCls="icon-reload"  onclick="reKaijiXtNewsFlash()">刷新</a>
				 </form>
			</div>
		</div>
       <div id="addKaijiXtNews" class="easyui-window" title="新增消息"  closed = "true" style="width:500px;height:350px;">
			 <form method="post" id="KaijiXtNewsFm"  enctype="multipart/form-data" text-align:left>
                <table cellspacing="8px;"> 
                   <tr>                       
                        <td>
                        	<input type="hidden" id="id" name="id" value="0"/>
                        </td>
                    </tr> 
                   <!--  <tr> 
                        <td>发送类型：</td>
                        <td> 
                        <input id="type" name="type" class="easyui-combobox" data-options="    
					            valueField: 'bz_id',    
						        textField: 'bz_value',    
						        url: 'getClsCo.action?bz_type='+'send_type',
						        method:'get',
						        required:true,    
                                panelHeight:'auto'
                              "/> &nbsp;<span style="color: red">*</span>
                        </td>
                    </tr>            -->    
                    <tr>
                        <td>说明：</td> <!-- required="true"  -->
                        <td><input type="text" id="title" name="title"
                            class="easyui-validatebox"/>&nbsp;<span
                            style="color: red">*</span>
                        </td>
                    </tr>
                   <tr>
                        <td>图片：</td>
                        <td>
                        	<input id="myFile" name="myFile" class="easyui-filebox"  style="width:200px;"/>&nbsp;<span
                            style="color: red">*</span>
                        </td>
                   </tr>                   
                    <tr>
                       <td>内容：</td> <!-- required="true" -->
                        <td><textarea class="easyui-textbox" id="contents" name="contents" data-options="multiline:true" value="请填写" style="width:300px;height:100px;white-space:pre-wrap"></textarea>
                        </td>
                    </tr>                                
                        
                </table>
				<div style="padding:5px;text-align:center;">
					<a href="#" class="easyui-linkbutton" icon="icon-ok" onclick="saveKaijiXtNews()">保存</a>&nbsp;&nbsp;&nbsp;&nbsp;
					<a href="#" class="easyui-linkbutton" icon="icon-cancel"  onclick="closeKaijiXtNewsWin()">取消</a>
				</div>
			</form>
	  </div>
	  	  
	<style>
	  	#KaijiXtNewsFm{
	  		margin:0 auto !important;
	  		width: 400px
	  	}
	   .datagrid-btable tr{height: 32px;}
	  	
	  </style>
  </body>

	
</html>
