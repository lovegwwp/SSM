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
		<title>康复者管理首页</title>
		
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
	  
	  //city_id  area_id
	  /* $("#province_id").combobox({  
		  onChange: function () {  
	           var newPtion = $("#addcourseTypName").combobox('getText')  
	           if (newPtion == "公共选修") {  
	               $("#city_id").css("display", "block");  
	           }  
	           else {  
	               $("#PublicChooseCourse").css("display", "none");  
	           }  
	       }  
	   })  */
	   
	    //设置
	function setPatFm(){
	  //  $("#account").combobox("reload", "getDevCl.action");    
	    $("#account").val(""); 
	    $("#uname").val(""); 
	    $("#age").val("");
	    $("#talkTime").val(""); 
	    $("#videoTime").val(""); 	
	    $("#zd").val("请填写"); 
	    $("#addr").val("请填写"); 
	       
	 }
      function rePatFlash(){
         //reload:重新执行url，condition是url中的参数  
            $("#searchPatFm").form("clear");  
            //$('#dg').datagrid("reload");  
             $("#patDg").datagrid({
	           url:"getPat.action"      	     
	         });         
        }
      function doPatSearch(){
    	  var p1 = $("#province1").combobox('getText');
    	  var c1 = $("#city1").combobox('getText');
    	  var a1 = $("#area1").combobox('getText');
	      $("#patDg").datagrid({
	           url:"getPatBy.action",      
	           queryParams: {  
	              uname1: $("#uname1").val(),  
	              account1: $("#account1").val(),
	              sex1: $("#sex1").val(),
	              province1:p1,
	              city1: c1,
	              area1: a1,
	                
	          }  
	      });  
	   } 
    function addPatWin(){
         setPatFm();
         $("#patDg").datagrid("uncheckAll");         
         $( "#addPat" ).window("open").window("setTitle", "新增"); 
      }
    function closePatWin(){
         $( "#addPat" ).window("close");
         $("#patFm").form("clear"); 
      }
    function closePwdWin(){
         $( "#upPwd" ).window("close");
         $("#pwdFm").form("clear"); 
      }  

    function savePat(){
	     var grid = $("#patDg"); 
	     var fm = $("#patFm");	   
	     var addWin = $( "#addPat" );  
	     var isValidate =false;
	     var myMac ="";
	     myMac =  $("#account").val();  
	     //isValidate = checkMacId(myMac);
	    // if(isValidate){
      		 commonSaveOperate(fm,"${pageContext.request.contextPath}/addPat.action",addWin,grid); 
      	// }     	     
     }
              
    function editPatWin(){
    	$("#ksId").combobox("disable"); 
    	$("#dwId").combobox("disable");    	
    	$("#zd").attr("readonly","readonly");
    	$("#bq").attr("readonly","readonly");
    	$("#jbqk").attr("readonly","readonly");
       var grid = $("#patDg"); 
	   var fm = $("#patFm");
	   var addWin = $( "#addPat" );  
       openEditWin(grid,addWin,fm);
     }
     
    function deletePat() {
        var grid = $("#patDg"); 
     	commonBatchOperate(grid, "${pageContext.request.contextPath}/delPat.action","确认删除所选数据吗？");
     }
  
    
    function setShowColumn(){
		 $('#patDg').datagrid('showColumn','ls');
		 $('#patDg').datagrid('showColumn','hc');
		 $('#patDg').datagrid('showColumn','cxbw');
		 $('#patDg').datagrid('showColumn','cxdx');
		 $('#patDg').datagrid('showColumn','ksName');
		 $('#patDg').datagrid('showColumn','bq');
		 $('#patDg').datagrid('showColumn','jbqk');
		 $('#patDg').datagrid('showColumn','zd');
	 }  
  

    </script>  
	<body class="easyui-layout">
		<table id="patDg" title="康复者信息列表" class="easyui-datagrid" style="width:1750px;height:865px"
			url="getPat.action"
			toolbar="#toolbar" pagination="true" rownumbers="true"  singleSelect="false">
			<thead>
				<tr>
				    <th field="ck" checkbox="true"></th>
					 <th field="id" width="50">id</th> 
					<th field="account" width="200">设备号</th>
					<th field="talkTime" width="100">通话时长</th>
					<th field="videoTime" width="100">训练时长</th>
					<th field="uname" width="50">姓名</th>
					<th field="sex" width="50" formatter="formatNumSex">性别</th>
					<th field="age" width="50">年龄</th>					
					<th field="province" width="100">省份</th>
					<th field="city" width="100">城市</th>
					<th field="area" width="100">区域</th>					
					<th field="addr" width="250">地址</th>
					<th field="cjsj" width="150">绑定时间</th>
				<!-- 	<th field="createdAt" width="150">绑定时间</th>
					<th field="xgsj" width="150">修改时间</th> -->
					<th field="status" width="50"  formatter="formatJy">禁用</th>					
					<!-- 病例情况 -->
					<th field="ls" width="80" hidden="true">利手</th>					
					<th field="hc" width="80" hidden="true">患侧</th>
					<th field="cxbw" width="80" hidden="true">出血部位</th>
					<th field="cxdx" width="80" hidden="true">出血大小</th>					
					<th field="ksName" width="80" hidden="true">住院科室</th>
					<th field="bq" width="80" hidden="true">病区</th>					
					<th field="jbqk" width="200" hidden="true">基本情况</th>
					<th field="zd" width="300" hidden="true">诊断</th>
		 		</tr>
			</thead>
		</table>
		<div id="toolbar" style="padding:3px">
			<div style="padding:3px">
				<a href="#" class="easyui-linkbutton" iconCls="icon-add"   onclick="addPatWin()">新增康复者</a>
				<a href="#" class="easyui-linkbutton" iconCls="icon-edit"  onclick="editPatWin()">修改康复者</a>
				<shiro:hasPermission name="pat:delete"> 
				<a href="#" class="easyui-linkbutton" iconCls="icon-remove"   onclick="deletePat()">删除康复者</a>
				</shiro:hasPermission>
				<a href="#" class="easyui-linkbutton" iconCls="icon-filter"   onclick="setShowColumn()">更多信息</a>
		    </div>
		    <div style="padding:3px">
		        <form id="searchPatFm">  
					<span>设备号:</span>
					<input id="account1" style="line-height:18px;border:1px solid #95b9e7">&nbsp;&nbsp;
					<span>姓名</span>
					<input id="uname1" style="line-height:18px;border:1px solid #95b9e7">
					<span>性别:</span>			
				    <select id="sex1"   style="width:150px;height:24px;line-height:20px;border:1px solid #95b9e7">
				        <option value="0">女</option>
				        <option value="1">男</option>			       			        
				    </select>&nbsp;&nbsp;
				    <span>省份:</span>
				    <input id="province1" name="province1" class="easyui-combobox" data-options="    
					            valueField: 'bz_id',    
						        textField: 'bz_value',    
						        url: 'getClsCo.action?bz_type='+'pro_type',
						        method:'get', 
                                panelHeight:'150',
                                onSelect: function(rec){ 
                                   $('#city1').combobox('clear');     
						            var url = 'getClsCo.action?bz_type='+'city_type'+'&pid='+rec.bz_id;
						            $('#city1').combobox('reload', url);    
						        }  "/> &nbsp; &nbsp;
                    <span>城市:</span>
                     <input id="city1" name="city1" class="easyui-combobox" data-options="    
					            valueField: 'bz_id',    
						        textField: 'bz_value',    
						        url: 'getClsCo.action?bz_type='+'city_type',
						        method:'get', 
                                panelHeight:'150',
                                onSelect: function(rec){  
                                    $('#area1').combobox('clear');   
						            var url = 'getClsCo.action?bz_type='+'area_type'+'&pid='+rec.bz_id;
						            $('#area1').combobox('reload', url);    
						        }  "/> &nbsp;
                    <span>区域:</span>
                    <input id="area1" name="area1" class="easyui-combobox" data-options="    
					            valueField: 'bz_id',    
						        textField: 'bz_value',    
						        url: 'getClsCo.action?bz_type='+'area_type',
						        method:'get', 
                                panelHeight:'150',
                                onSelect: function(rec){    
						            var url = 'getClsCo.action';    
						            $('#cc2').combobox('reload', url);    
						        }  "/>&nbsp;&nbsp;
					<a href="#" class="easyui-linkbutton" iconCls="icon-search"  onclick="doPatSearch()">搜索</a>&nbsp;&nbsp;					
					<a href="#" class="easyui-linkbutton" iconCls="icon-reload"  onclick="rePatFlash()">刷新</a>
				 </form>
			</div>
		</div>
       <div id="addPat" class="easyui-window" title="新增"  closed = "true" style="width:600px;height:500px;">
			 <form method="post" id="patFm" text-align:center>
                <table cellspacing="8px;"> 
                   <tr>                       
                        <td>
                        	<input type="hidden" id="id" name="id" value="0"/>
                        </td>
                    </tr>              
                    <tr>
                        <td>姓名：</td>
                        <td><input type="text" id="uname" name="uname"
                            class="easyui-validatebox" required="true" />&nbsp;<span
                            style="color: red">*</span>
                        </td> 
                    </tr>
                     <tr>
                        <td>年龄：</td>
                        <td><input type="text" id="age" name="age"
 							class="easyui-validatebox" required="true" />&nbsp;<span
                            style="color: red">*</span>
                        </td>
                    </tr>
                    <tr>
                        <td>性别：</td>
                         <td style="text-align:left">
				            <span class="radioSpan">
				                <input type="radio" name="sex" value="1" checked >男</input>&nbsp;&nbsp;&nbsp;&nbsp;
				                <input type="radio" name="sex" value="0">女</input>
				            </span>
				        </td>
                    </tr>
                    <tr>
                        <td>设备号：</td>
                        <td>
                       <!--  <input id="account" name="account" class="easyui-combobox" data-options="    
					            valueField: 'macId',    
						        textField: 'macId',    
						        url: 'getDevCl.action',
						        method:'get', 
                                panelHeight:'auto',
                                required:true
                                "/> &nbsp;<span style="color: red">*</span> -->
                                <input type="text" id="account" name="account"
 							class="easyui-validatebox" required="true" />&nbsp;<span
                            style="color: red">*</span>
                        </td>
                    </tr>                                                                                     
                    <tr> 
                        <td>通话时长：</td>
                        <td><input type="text" id="talkTime" name="talkTime"
 							class="easyui-validatebox" required="true" />&nbsp;<span
                            style="color: red">*</span>
                        </td>
                    </tr>    
                    <tr> 
                        <td>训练时长：</td>
                        <td><input type="text" id="videoTime" name="videoTime"
 							class="easyui-validatebox" required="true" />&nbsp;<span
                            style="color: red">*</span>
                        </td>
                    </tr>                                                                                     
                    <tr> 
                        <td>省份：</td>
                        <td> 
                        <input id="province_id" name="province_id" class="easyui-combobox" data-options="    
					            valueField: 'bz_id',    
						        textField: 'bz_value',    
						        url: 'getClsCo.action?bz_type='+'pro_type',
						        method:'get', 
                                panelHeight:'150',
                                onSelect: function(rec){    
						            var url = 'getClsCo.action?bz_type='+'city_type'+'&pid='+rec.bz_id;
						            $('#city_id').combobox('reload', url);    
						        }  "/> &nbsp;<span style="color: red">*</span>
                        </td>
                    </tr>    
                    <tr> 
                        <td>城市：</td>
                        <td> 
                        <input id="city_id" name="city_id" class="easyui-combobox" data-options="    
					            valueField: 'bz_id',    
						        textField: 'bz_value',    
						        url: 'getClsCo.action?bz_type='+'city_type',
						        method:'get', 
                                panelHeight:'150',
                                onSelect: function(rec){    
						            var url = 'getClsCo.action?bz_type='+'area_type'+'&pid='+rec.bz_id;
						            $('#area_id').combobox('reload', url);    
						        }  "/> &nbsp;<span style="color: red">*</span>
                        </td>
                    </tr>    
                    <tr> 
                        <td>区域：</td>
                        <td> 
                        <input id="area_id" name="area_id" class="easyui-combobox" data-options="    
					            valueField: 'bz_id',    
						        textField: 'bz_value',    
						        url: 'getClsCo.action?bz_type='+'area_type',
						        method:'get', 
                                panelHeight:'150',
                                onSelect: function(rec){    
						            var url = 'getClsCo.action';    
						            $('#cc2').combobox('reload', url);    
						        }  "/> &nbsp;<span style="color: red">*</span>
                        </td>
                    </tr> 
                    <tr> 
                        <td>地址：</td>
                        <td>
                        <textarea class="easyui-textbox" required="true" id="addr" name="addr" data-options="multiline:true" value="请填写" style="width:180px;height:80px;white-space:pre-wrap"></textarea>
                       <!--    <input type="text" id="addr" name="addr"
 							class="easyui-validatebox" required="true" /> -->&nbsp;<span
                            style="color: red">*</span>
                        </td>
                    </tr>  
                    
                </table>
				<div style="padding:5px;text-align:center;">
					<a href="#" class="easyui-linkbutton" icon="icon-ok" onclick="savePat()">保存</a>&nbsp;&nbsp;&nbsp;&nbsp;
					<a href="#" class="easyui-linkbutton" icon="icon-cancel"  onclick="closePatWin()">取消</a>
				</div>
			</form>
	  </div>
	  <style>
	  	#patFm{
	  		margin:0 auto !important;
	  		width: 300px
	  	}
	   .datagrid-btable tr{height: 32px;}
	  	
	  </style>
	   <!--    <tr> 
                        <td>患侧：</td>
                        <td><input type="text" id="hc" name="hc"
 							class="easyui-validatebox" required="true" />&nbsp;<span
                            style="color: red">*</span>
                        </td>
                    </tr>  
                     <tr> 
                        <td>利手：</td>
                        <td><input type="text" id="ls" name="ls"
 							class="easyui-validatebox" required="true" />&nbsp;<span
                            style="color: red">*</span>
                        </td>
                    </tr>    
                    <tr> 
                        <td>出血部位：</td>
                        <td><input type="text" id="cxbw" name="cxbw"
 							class="easyui-validatebox" required="true" />&nbsp;<span
                            style="color: red">*</span>
                        </td>
                    </tr>    
                    <tr> 
                        <td>出血大小：</td>
                        <td><input type="text" id="cxdx" name="cxdx"
 							class="easyui-validatebox" required="true" />&nbsp;<span
                            style="color: red">*</span>
                        </td>
                    </tr>
                    <tr> 
                        <td>所属医院：</td>
                        <td> 
                        <input id="dwId" name="dwId" class="easyui-combobox" data-options="    
					            valueField: 'bz_id',    
						        textField: 'bz_value',    
						        url: 'getClsCo.action?bz_type='+'dw_type',
						        method:'get', 
                                panelHeight:'auto',
                                onSelect: function(rec){    
						            var url = 'getClsCo.action';    
						            $('#cc2').combobox('reload', url);    
						        }  "/> &nbsp;<span style="color: red">*</span>
                        </td>
                    </tr>      
                    <tr> 
                        <td>住院科室：</td>
                        <td> 
                        <input id="ksId" name="ksId" class="easyui-combobox" data-options="    
					            valueField: 'bz_id',    
						        textField: 'bz_value',    
						        url: 'getClsCo.action?bz_type='+'ks_type',
						        method:'get', 
                                panelHeight:'auto',
                                onSelect: function(rec){    
						            var url = 'getClsCo.action';    
						            $('#cc2').combobox('reload', url);    
						        }  "/> &nbsp;<span style="color: red">*</span>
                        </td>
                    </tr> 
                    <tr> 
                        <td>病区：</td>
                        <td><input type="text" id="bq" name="bq"
 							class="easyui-validatebox" required="true" />&nbsp;<span
                            style="color: red">*</span>
                        </td>
                    </tr>  
                     <tr> 
                        <td>基本情况：</td>
                        <td><input type="text" id="jbqk" name="jbqk"
 							class="easyui-validatebox" required="true" />&nbsp;<span
                            style="color: red">*</span>
                        </td>
                    </tr>                     
                     <tr>
                        <td>医生诊断：</td>
                        <td><textarea class="easyui-textbox" id="zd" name="zd" data-options="multiline:true" value="请填写" style="width:300px;height:100px"></textarea>
                        </td>
                    </tr> -->  
  </body>

	
</html>
