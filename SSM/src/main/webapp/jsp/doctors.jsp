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
		<title>医生管理首页</title>
		
		<link rel="stylesheet" type="text/css" href="easyui/themes/default/easyui.css">
		<link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">
		<link rel="stylesheet" type="text/css" href="css/fgn.css">
		<script type="text/javascript" src="js/jquery-1.8.0.min.js"></script>
		<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="js/common.js"></script>
	</head>
	 <script type="text/javascript"> 
	 function ff(){	 
		 if(window.localStorage)
		 {
			 var ss = 1024 * 1024 * 5 - unescape(encodeURIComponent(JSON.stringify(localStorage))).length;		
			 alert(ss);
		 } 
	 }
	  
	  $( function() {	
		//  ff();
		  setHideColumn();
	  } );
	  
	 function setHideColumn(){
		 $('#dg').datagrid('hideColumn','city');
		 $('#dg').datagrid('hideColumn','province');
		 $('#dg').datagrid('hideColumn','area');
		 $('#dg').datagrid('hideColumn','skills');
		 $('#dg').datagrid('hideColumn','abstracts');
	 } 
	 
	 function setShowColumn(){
		 $('#dg').datagrid('showColumn','city');
		 $('#dg').datagrid('showColumn','province');
		 $('#dg').datagrid('showColumn','area');
		 $('#dg').datagrid('showColumn','skills');
		 $('#dg').datagrid('showColumn','abstracts');
	 }  
	 
	 
 	 $('#dg').datagrid({
 	    	columns:[[
 	    		{field:'type',title:'权限', width:80,
 	    			formatter: function(value,row,index){
 	    				if(value==null){  
 	                        return "";  
 	                    }else{  
 	                      if(value=1){  
 	                          return "管理员";  
 	                      }else{  
 	                          return "否";  
 	                      }  
 	                    }  
 	    			}
 	    		}
 	    	]]
 	    });
 	 
 	 

 	
 	 
    
   //下拉框中的数据-页面加载的时候会访问url并将查询的数据绑定到valueField和textField  
        function comboboxData(){  
            $('#dw_id').combobox({   
                                valueField: 'bz_id',    
						        textField: 'bz_value',    
						        url: 'getClsCo.action?bztype=protype',
						        method:'get', //提交方式  
                                panelHeight:'auto',  //下拉框高度自适应      
						        onSelect: function(rec){    
						            var url = 'getClsCo.action';    
						            $('#cc2').combobox('reload', url);    
						        }
                });    
        }  
        
      function reFlash(){
         //reload:重新执行url，condition是url中的参数  
            $("#searchFm").form("clear");  
            //$('#dg').datagrid("reload");  
             $("#dg").datagrid({
	           url:"getDocs.action"      	     
	         });         
        }
      function doSearch(){
	      $("#dg").datagrid({
	           url:"getSearch.action",      
	           queryParams: {  
	              uname: $("#uname").val(),  
	              account: $("#account").val(),
	              type: $("#type1").val()    
	          }  
	      });  
	   } 
    function addDocWin(){
    	 $("#dg").datagrid("uncheckAll");         
         $( "#addDoc" ).window("open").window("setTitle", "新增");
        // $( "#addDoc" ).window("open"); 
      }
    function closeDocWin(){
         $( "#addDoc" ).window("close");
         $("#fm").form("clear"); 
      }
    function closePwdWin(){
         $( "#upPwd" ).window("close");
         $("#pwdFm").form("clear"); 
      }  

    function saveDoc(){
	     var grid = $("#dg"); 
	     var fm = $("#fm");	   
	     var addWin = $( "#addDoc" );  
      	 commonSaveOperate(fm,"${pageContext.request.contextPath}/addDoc.action",addWin,grid);      	     
     }
              
    function editDocWin(){
       var grid = $("#dg"); 
	   var fm = $("#fm");
	   var addWin = $( "#addDoc" );  
       openEditWin(grid,addWin,fm);
     }
     
    function deleteDoc() {
        var grid = $("#dg"); 
     	commonBatchOperate(grid, "${pageContext.request.contextPath}/delDoc.action","确认删除所选数据吗？");
     }
     
    function jyDoc() {
        var grid = $("#dg"); 
     	commonBatchOperate(grid, "${pageContext.request.contextPath}/jyDoc.action","确认禁用(启用)所选数据吗？");
     }
     
     function qxDoc() {
        var grid = $("#dg"); 
     	commonBatchOperate(grid, "${pageContext.request.contextPath}/qxDoc.action","确认设置所选数据吗？");
     }    
     
     function upPwd(){
        var Rows = $("#dg").datagrid("getSelections");
        if (Rows.length != 1) {
         $.messager.alert("系统提示", "请选择一条要编辑的数据！");
         return;
        }
	     var row = Rows[0];
	     $("#upPwd").window("open").window("setTitle", "密码重置");
	     $("#pwdFm").form("load", row);
     }
     
    function checkPwd(){
       var p1 =  $("#pwd").val(); 
       var p2 =  $("#pwd2").val(); 
       if(p1!=p2){
       //alert(p1+"-----"+p2);
       	 $.messager.alert("系统提示", "两次密码不一致！");
       	 return false;
       }else{
         return true;
       }
     }
     
    function savePwd(){
         if(checkPwd()){
	         var grid = $("#dg"); 
		     var fm = $("#pwdFm");	   
		     var addWin = $( "#upPwd" );  
	      	 commonSaveOperate(fm,"${pageContext.request.contextPath}/upPwd.action",addWin,grid); 
      	 }
    }

   


    
    
    </script>  
	<body class="easyui-layout">
		<table id="dg" title="医生信息列表" class="easyui-datagrid" style="width:1750px;height:865px"
			url="getDocs.action"
			toolbar="#toolbar" pagination="true" rownumbers="true"  singleSelect="false">
			<thead>
				<tr>
				    <th field="ck" checkbox="true"></th>
					<!-- <th field="id" width="50">id</th> -->
					<th field="account" width="120">账号</th>
					<th field="uname" width="50">姓名</th>
					<th field="sex" width="60" formatter="formatNumSex">性别</th>
					<th field="age" width="50">年龄</th>
					<th field="emails" width="150">邮箱</th>   
					<!-- <th field="dw_id" width="0">单位</th> -->
					<th field="dw_name" width="120">单位名称</th>
					<!-- <th field="ks_id" width="0">科室</th> -->
					<th field="ks_name" width="100">科室名称</th>
					<th field="job" width="100">职务</th>
					<!-- <th field="lables" width="150">标签id</th> -->
					<th field="lableNames" width="200">标签内容</th>
					<th field="skills" width="150">技能</th>
					<th field="abstracts" width="200">简介</th>
					<th field="province" width="100">省份</th>
					<th field="city" width="100">城市</th>
					<th field="area" width="100">区域</th>
					<!-- <th field="created_at" width="150">创建时间</th> -->
					<th field="cjsj" width="150">创建时间</th>
					<th field="xgsj" width="150">修改时间</th>
					<th field="type" width="100" formatter="formatNumShow">权限</th>
					<th field="status" width="100" formatter="formatJy">禁用</th>
				</tr>
			</thead>
		</table>
		<div id="toolbar" style="padding:3px">
			<div style="padding:3px">
				<a href="#" class="easyui-linkbutton" iconCls="icon-add"   onclick="addDocWin()">新增医生</a>
				<a href="#" class="easyui-linkbutton" iconCls="icon-edit"  onclick="editDocWin()">修改医生</a>
				<shiro:hasPermission name="doc:delete"> 
				   <a href="#" class="easyui-linkbutton" iconCls="icon-remove"   onclick="deleteDoc()">删除医生</a>
				</shiro:hasPermission>  
				<shiro:hasPermission name="doc:jy"> 
					<a href="#" class="easyui-linkbutton" iconCls="icon-cut"   onclick="jyDoc()">禁用/启用</a>
				</shiro:hasPermission>
				<shiro:hasPermission name="doc:qx"> 
					<a href="#" class="easyui-linkbutton" iconCls="icon-my-account"   onclick="qxDoc()">权限更改</a>
				</shiro:hasPermission>
				<shiro:hasPermission name="doc:upPwd"> 
					<a href="#" class="easyui-linkbutton" iconCls="icon-pass"   onclick="upPwd()">重置密码</a>
				</shiro:hasPermission>
				<a href="#" class="easyui-linkbutton" iconCls="icon-filter"   onclick="setShowColumn()">更多信息</a>
		    </div>
		    <div style="padding:3px">
		        <form id="searchFm">  
					<span>账号:</span>
					<input id="account" style="line-height:18px;border:1px solid #95b9e7">&nbsp;&nbsp;
					<span>姓名</span>
					<input id="uname" style="line-height:18px;border:1px solid #95b9e7">
					<span>是否管理员:</span>			
				    <select id="type1"   style="width:150px;height:24px;line-height:20px;border:1px solid #95b9e7">
				        <option value="0">普通用户</option>
				        <option value="1">管理员</option>			       			        
				    </select>&nbsp;&nbsp;
					<a href="#" class="easyui-linkbutton" iconCls="icon-search"  onclick="doSearch()">搜索</a>&nbsp;&nbsp;
					<!--  <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="reFlash()">reFlash</a>-->
					<a href="#" class="easyui-linkbutton" iconCls="icon-reload"  onclick="reFlash()">刷新</a>
				 </form>
			</div>
		</div>
       <div id="addDoc" class="easyui-window" title="新增"  closed = "true" style="width:600px;height:90%;"><!-- 800px -->
			 <form method="post" id="fm" text-align:center>
                <table cellspacing="8px;"> 
                   <tr>                       
                        <td>
                        	<input type="hidden" id="id" name="id" value="0"/>
                        </td>
                    </tr>              
                    <tr>
                        <td>姓名(真实)：</td>
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
                        <td>手机号：</td>
                        <td><input type="text" id="account" name="account"
                            class="easyui-validatebox" required="true" />&nbsp;<span
                            style="color: red">*</span>
                        </td>
                    </tr>                     
                    <tr> 
                        <td>邮箱：</td>
                        <td><input type="text" id="emails" name="emails"
                            validType="email" class="easyui-validatebox" />
                        </td>
                    </tr>                                    
                    <tr> 
                        <td>单位：</td>
                        <td>
						<input id="dw_id" name="dw_id" class="easyui-combobox" data-options="    
					            valueField: 'bz_id',    
						        textField: 'bz_value',    
						        url: 'getClsCo.action?bz_type='+'dw_type',
						        method:'get', //提交方式  
						        required:true,   
                                panelHeight:'150'     
						       "/> &nbsp;<span style="color: red">*</span>
                        </td>
                    </tr>
                    <tr> 
                        <td>科室：</td>
                        <td> 
                        <input id="ks_id" name="ks_id" class="easyui-combobox" data-options="    
					            valueField: 'bz_id',    
						        textField: 'bz_value',    
						        url: 'getClsCo.action?bz_type='+'ks_type',
						        method:'get',
						        required:true,    
                                panelHeight:'150'
                              "/> &nbsp;<span style="color: red">*</span>
                        </td>
                    </tr>  
                    <tr>
                        <td>职务：</td>
                        <td>
                        <input id="job" name="job" class="easyui-combobox" data-options="    
					            valueField: 'bz_id',    
						        textField: 'bz_value',    
						        url: 'getClsCo.action?bz_type='+'job_type',
						        method:'get',
						        required:true,    
                                panelHeight:'150'
                              "/> &nbsp;<span style="color: red">*</span>
                        </td>
                    </tr>   
                    <tr>
                        <td>标签(可多选)：</td>
                        <td>
                        <input id="lables" name="lables" class="easyui-combobox" data-options="    
					            valueField: 'bz_id',    
						        textField: 'bz_value',    
						        url: 'getClsCo.action?bz_type='+'lable_type',
						        method:'get', //提交方式  
						        multiple:true,
						        required:true,   
                                panelHeight:'150'     
						       "/> &nbsp;<span style="color: red">*</span>
                        </td>
                    </tr>
                    <tr>
                        <td>擅长：</td>
                        <td><textarea class="easyui-validatebox"  required="true" id="skills" name="skills" data-options="multiline:true" value="请填写" style="width:300px;height:100px"></textarea>
                        </td>
                    </tr> 
                    <tr>
                        <td>简介：</td>
                        <td><textarea class="easyui-validatebox"  required="true" id="abstracts" name="abstracts" data-options="multiline:true" value="请填写" style="width:300px;height:100px"></textarea>
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
                                    $('#city_id').combobox('clear');   
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
                                    $('#area_id').combobox('clear');  
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
                </table>
				<div style="padding:5px;text-align:center;">
					<a href="#" class="easyui-linkbutton" icon="icon-ok" onclick="saveDoc()">保存</a>&nbsp;&nbsp;&nbsp;&nbsp;
					<a href="#" class="easyui-linkbutton" icon="icon-cancel"  onclick="closeDocWin()">取消</a>
				</div>
			</form>
	  </div>
	  
	  <div id="upPwd" class="easyui-window" title="密码重置"  closed = "true" style="width:400px;height:300px;">
			 <form method="post" id="pwdFm" text-align:center>
                <table cellspacing="8px;"> 
                   <tr>                       
                        <td>
                        	<input type="hidden" id="id" name="id" />
                        </td>
                    </tr>               
                    <tr>                       
                        <td>新密码：</td>
                        <td><input type="password" id="pwd" name="pwd"
                            class="easyui-validatebox" required="true" />&nbsp;<span
                            style="color: red">*</span>
                        </td>
                    </tr>
                    <tr>                       
                        <td>密码确认：</td>
                        <td><input type="password" id="pwd2" name="pwd2"
                            class="easyui-validatebox" required="true" />&nbsp;<span
                            style="color: red">*</span>
                        </td>
                    </tr>
                        
                </table>
				<div style="padding:5px;text-align:center;">
					<a href="#" class="easyui-linkbutton" icon="icon-ok" onclick="savePwd()">重置</a>&nbsp;&nbsp;&nbsp;&nbsp;
					<a href="#" class="easyui-linkbutton" icon="icon-cancel"  onclick="closePwdWin()">取消</a>
				</div>
			</form>
	  </div>
	
	<style>
	  	#fm{
	  		margin:0 auto !important;
	  		width: 400px
	  	}
	  	#pwdFm{
	  		margin:0 auto !important;
	  		width: 300px
	  	}
	   .datagrid-btable tr{height: 32px;}
	  	
	  </style>
  </body>

	
</html>
