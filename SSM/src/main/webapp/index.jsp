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
		<title>后台管理首页</title>
		
		<link rel="stylesheet" type="text/css" href="easyui/themes/default/easyui.css">
		<link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">
		<script type="text/javascript" src="js/jquery-1.8.0.min.js"></script>
		<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
	</head>
	<body class="easyui-layout">
		<!-- 北 -->
		<div data-options="region:'north',border:false" style="height: 36px; border: 0px; padding: 0px; overflow: hidden;">
			<div class="accordion" style="text-align: right; height: 100%; background-color: #438EB9;">
				<div style="margin: auto;">
					<img src="img/top.jpg" width="1920"  height="35" style="margin: auto;border: 0px;" border="0" />
				</div>
			</div>
			
		</div>
		<!-- 西 -->
		<div data-options="region:'west',split:true,title:' '"style="width: 155px;">
			<div class="easyui-layout" data-options="fit:true">
				<div id="pic" data-options="region:'north',split:false" style="height: 140px; padding: 4px;">
					<img alt="" src="img/head.jpg" width="130px;">
					<div align="center">欢迎光临</div>
					<div align="center" id="showDate"></div>
				</div>
				<div data-options="region:'center'">
						<div id="frame_accordion" class="easyui-accordion" data-options="fit:true">  					  
						    <div title="医生管理" data-options="" style="overflow:auto; style="padding:10px;">  
						        <ul id="tree_ys" class="easyui-tree" >  
								    <li  data-options="iconCls:'icon-client_account_template'">  
				                        <span>医生列表</span>  
				                    </li>  
								</ul>  
						    </div>  
						    <div title="病人管理" style="padding:10px;">  
						        <ul id="tree_br" class="easyui-tree" >  
								    <li  data-options="iconCls:'icon-client_account_template'">  
				                        <span>病人列表</span>  
				                    </li>  
								</ul>  
						    </div>  
						    <div title="设备管理" style="padding:10px;">  
						        <ul id="tree_sb" class="easyui-tree" >  
								    <li  data-options="iconCls:'icon-client_account_template'">  
				                        <span>设备列表</span>  
				                    </li>  
								</ul>  
						    </div>  
						    <div title="训练项目管理" style="padding:10px;">  
						        <ul id="tree_xlxm" class="easyui-tree" >  
								   <!--  <li  data-options="iconCls:'icon-client_account_template'">  
				                        <span>项目名称列表</span>  
				                    </li>  --> 
				                    <li  data-options="iconCls:'icon-client_account_template'">  
				                        <span>训练内容列表</span>  
				                    </li>  
								</ul>  
						    </div>  
						    <div title="新闻管理" style="padding:10px;">  
						        <ul id="tree_xw" class="easyui-tree" >  
								    <li  data-options="iconCls:'icon-client_account_template'">  
				                        <span>新闻列表</span>  
				                    </li>  
								</ul>  
						    </div>  
						    <div title="系统消息" style="padding:10px;">  
						        <ul id="tree_xx" class="easyui-tree" >  
								    <li  data-options="iconCls:'icon-client_account_template'">  
				                        <span>消息列表</span>  
				                    </li>  
								</ul>  
						    </div>  
						    <div title="查询统计" style="padding:10px;">  
						        <ul id="tree_cxtj" class="easyui-tree" >  
								    <li  data-options="iconCls:'icon-client_account_template'">  
				                        <span>在线医生分布</span>  
				                    </li>  
				                    <li data-options="iconCls:'icon-group_green_edit'">  
				                        <span>在线病人分布</span>  
				                    </li>  
				                    <li data-options="iconCls:'icon-my-account'">  
				                        <span>按地域统计医生</span>  
				                    </li>  
								    <li data-options="iconCls:'icon-business-contact'">  
								        <span>按地域统计病人</span>  
								    </li>
								     <li data-options="iconCls:'icon-brainstorming-contact'">  
								        <span>按单位统计病人</span>  
								    </li>  
								</ul>  
						    </div>
						     <div title="视频计费管理" style="padding:10px;">  
						        <ul id="tree_spjf" class="easyui-tree" >  
								    <li  data-options="iconCls:'icon-client_account_template'">  
				                        <span>视频计费设置</span>  
				                    </li>  
								</ul>  
						    </div>   
						     <div title="财务管理" style="padding:10px;">  
						        <ul id="tree_cw" class="easyui-tree" >  
								    <li  data-options="iconCls:'icon-client_account_template'">  
				                        <span>充值列表</span>  
				                    </li> 				                 
								</ul>  
						    </div>   
						     <div title="系统设置" style="padding:10px;">  
						        <ul id="tree_xtsz" class="easyui-tree" >  
								    <li  data-options="iconCls:'icon-client_account_template'">  
				                        <span>账号设置</span>  
				                    </li> 
				                    <li  data-options="iconCls:'icon-client_account_template'">  
				                        <span>密码修改</span>  
				                    </li> 
				                    <li  data-options="iconCls:'icon-client_account_template'">  
				                        <span>训练计划设置</span>  
				                    </li> 
				                    <li  data-options="iconCls:'icon-client_account_template'">  
				                        <span>常量设置</span>  
				                    </li> 
				                    <li  data-options="iconCls:'icon-client_account_template'">  
				                        <span>退出</span>  
				                    </li> 				                 
								</ul>  
						    </div>    
						</div> 
				</div>
			</div>
		</div>
		<!-- 南 -->
		<div data-options="region:'south',border:false" style="height: 20px; background-color: #f3f3f3;text-align: right;">
			@版权所有2017&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		</div>
		<!-- 中 -->
		<div data-options="region:'center'">
			<div class="easyui-tabs" id="frame_tabs" style="overflow: hidden;background-color: #f3f3f3;"
				data-options="fit:true,border:false,plain:true">
				<div title="首页" style="overflow: hidden;">
					<jsp:include page="/home.jsp"></jsp:include>
				</div>
			</div>
		</div>
	</body>
	<script type="text/javascript">

		$(function(){			
			setInterval("getTime();",1000);
		})
		//取得系统当前时间
		function getTime(){
			var myDate = new Date();
			var date = myDate.toLocaleDateString();
			var hours = myDate.getHours();
			var minutes = myDate.getMinutes();
			var seconds = myDate.getSeconds();
			$("#showDate").html(date+" "+hours+":"+minutes+":"+seconds);
		}
	
		$("#tree_ys").tree( {
			 onSelect : function(node) {  
                openMenuTow(node);  
            } 
		});
		$("#tree_sb").tree( {
			 onSelect : function(node) {  
                openMenuTow(node);  
            } 
		});
		$("#tree_br").tree( {
			 onSelect : function(node) {  
                openMenuTow(node);  
            } 
		});
		$("#tree_xtsz").tree( {
			 onSelect : function(node) {  
                openMenuTow(node);  
            } 
		});
		$("#tree_xw").tree( {
			 onSelect : function(node) {  
                openMenuTow(node);  
            } 
		});
		$("#tree_xlxm").tree( {
			 onSelect : function(node) {  
                openMenuTow(node);  
            } 
		});
		
		$("#tree_spjf").tree( {
			 onSelect : function(node) {  
               openMenuTow(node);  
           } 
		});
		
		$("#tree_cw").tree( {
			 onSelect : function(node) {  
              openMenuTow(node);  
          } 
		});
		$("#tree_xx").tree( {
			 onSelect : function(node) {  
             openMenuTow(node);  
         } 
		});
		
		
		function openMenuTow(node){

			 //树型菜单的名字   
            var noteText = $(".tree-title", node.target).text();  
            var exist_tab = $('#frame_tabs').tabs('getTab', noteText);  
            //判断是否已经打开该选项卡  
            if (exist_tab) {  
                $('#frame_tabs').tabs('select', noteText);  
                return;  
            } else {  
                var content = "";
                if(noteText=="医生列表"){
                	content = '<iframe scrolling="auto" frameborder="0"  src="doc.action" style="width:100%;height:100%;"></iframe>';
                }else if(noteText=="病人列表"){
                	content = '<iframe scrolling="auto" frameborder="0"  src="pat.action" style="width:100%;height:100%;"></iframe>';

                }else if(noteText=="设备列表"){
                	content = '<iframe scrolling="auto" frameborder="0"  src="dev.action" style="width:100%;height:100%;"></iframe>';

                }else if(noteText=="项目名称列表"){
                	content = '<iframe scrolling="auto" frameborder="0"  src="pat.action" style="width:100%;height:100%;"></iframe>';

                }else if(noteText=="训练内容列表"){
                	content = '<iframe scrolling="auto" frameborder="0"  src="xm.action" style="width:100%;height:100%;"></iframe>';

                }else if(noteText=="新闻列表"){
                	content = '<iframe scrolling="auto" frameborder="0"  src="news.action" style="width:100%;height:100%;"></iframe>';

                }else if(noteText=="消息列表"){
                	content = '<iframe scrolling="auto" frameborder="0"  src="pat.action" style="width:100%;height:100%;"></iframe>';

                }else if(noteText=="在线医生分布"){
                	content = '<iframe scrolling="auto" frameborder="0"  src="pat.action" style="width:100%;height:100%;"></iframe>';

                }else if(noteText=="在线病人分布"){
                	content = '<iframe scrolling="auto" frameborder="0"  src="pat.action" style="width:100%;height:100%;"></iframe>';

                }else if(noteText=="按地域统计医生"){
                	content = '<iframe scrolling="auto" frameborder="0"  src="pat.action" style="width:100%;height:100%;"></iframe>';

                }else if(noteText=="按地域统计病人"){
                	content = '<iframe scrolling="auto" frameborder="0"  src="pat.action" style="width:100%;height:100%;"></iframe>';

                }else if(noteText=="按单位统计病人"){
                	content = '<iframe scrolling="auto" frameborder="0"  src="pat.action" style="width:100%;height:100%;"></iframe>';

                }else if(noteText=="视频计费设置"){
                	content = '<iframe scrolling="auto" frameborder="0"  src="zfsz.action" style="width:100%;height:100%;"></iframe>';

                }else if(noteText=="充值列表"){
                	content = '<iframe scrolling="auto" frameborder="0"  src="cwzf.action" style="width:100%;height:100%;"></iframe>';

                }else if(noteText=="账号设置"){
                	content = '<iframe scrolling="auto" frameborder="0"  src="pat.action" style="width:100%;height:100%;"></iframe>';

                }else if(noteText=="密码修改"){
                	content = '<iframe scrolling="auto" frameborder="0"  src="rePwd.action" style="width:100%;height:100%;"></iframe>';

                }else if(noteText=="训练计划设置"){
                	content = '<iframe scrolling="auto" frameborder="0"  src="pat.action" style="width:100%;height:100%;"></iframe>';

                }else if(noteText=="常量设置"){
                	content = '<iframe scrolling="auto" frameborder="0"  src="cl.action" style="width:100%;height:100%;"></iframe>';

                }else if(noteText=="退出"){
                	content = '<iframe scrolling="auto" frameborder="0"  src="pat.action" style="width:100%;height:100%;"></iframe>';

                }else{
                	content = '<div style="font-size: 35px; text-align: center;margin-top: 100px;">系统维护升级中！</br>'+
                	'<div style="font-size:20px;">如果觉得无聊,我们可以去<a href="http://www.aqee.net/docs/the-quiet-place/" target="_blank">一个安静的地方</a></div></div>';
                }
                $('#frame_tabs').tabs('add', {  
                    'id' : 'tab',  
                    title : noteText,  
                    fit : true,  
                    content : content,  
                    closable : true  
                });  
                //获取最后一个tabs 在新加的选项卡后面添加"关闭全部"  
                var li = $(".tabs-wrap ul li:last-child");  
                $("#close").remove();  
                li.after("<li id='close'><a class='tabs-inner' href='javascript:void()' onClick='javascript:closeAll()'>关闭全部</a></li>");  
            }  
		}

		function closeAll() {  
            $(".tabs li").each(function(index, obj) {  
                  //获取所有可关闭的选项卡  
                  var tab = $(".tabs-closable", this).text();  
                  $(".easyui-tabs").tabs('close', tab);  
            });  
            $("#close").remove();//同时把此按钮关闭  
        }  
	</script>
	
</html>
