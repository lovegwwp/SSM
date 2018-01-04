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
		<title>康复者地域统计首页</title>
		
		<link rel="stylesheet" type="text/css" href="easyui/themes/default/easyui.css">
		<link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">
		<script type="text/javascript" src="js/jquery-1.8.0.min.js"></script>
		<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="js/common.js"></script>
	</head>
	 <script type="text/javascript"> 
	  
	  $( function() {	
		  //onLoadSuccess();
		 // alert(compute("num"));

	     
	  } );
	  
      function rePatAddrFlash(){
         //reload:重新执行url，condition是url中的参数  
            $("#searchPatAddrFm").form("clear");  
            //$('#dg').datagrid("reload");  
             $("#patAddrDg").datagrid({
	           url:"patAddrTjCx.action"      	     
	         });         
        }
      function doPatAddrSearch(){
    	  var  sprovince =$("#province_id").combobox("getValue");
    	  if (sprovince == null || sprovince == undefined || sprovince == '') { 
    		  sprovince = 'AA'; 
    	  } 
    	  var  scity = $("#city_id").combobox("getValue");
    	  if (scity == null || scity == undefined || scity == '') { 
    		  scity = 'AA'; 
    	  } 
    	 // alert($("#city_id").combobox("getValue"));
	      $("#patAddrDg").datagrid({
	           url:"patAddrTjBy.action",      
	           queryParams: {  
	        	   province: sprovince,
	        	   city: scity
	          }  
	      });  
	   } 
      
      function onLoadSuccess() {
          //添加“合计”列
         $("#patAddrDg").datagrid("appendRow", {
              province: '<span class="subtotal">合计</span>',          
              city : '<span class="subtotal">' + ' ' + '</span>',
              num: '<span class="subtotal">' + compute("num")  + '</span>'
          });
      }
     //指定列求和
     function compute(colName) {
          var rows = $("#patAddrDg").datagrid("getRows");
          var total = 0;
          for (var i = 0; i < rows.length; i++) {
              total += parseFloat(rows[i][colName]);
          }
          return total;
      }

    </script>  
	<body class="easyui-layout">
		<table id="patAddrDg" title="康复者区域统计" class="easyui-datagrid" style="width:1750px;height:865px"
			url="patAddrTjCx.action"
			 toolbar="#toolbar"  pagination="true" rownumbers="true"   showFooter="true" singleSelect="false">
			<thead>
				<tr>
				    <th field="ck" checkbox="true"></th>						
					<th field="province" width="150">省份</th>
					<th field="city" width="150">城市</th>
					<th field="num" width="100">人数</th>									
		 		</tr>
			</thead>
		</table>
		<div id="toolbar" style="padding:3px">
			<!-- <div style="padding:3px">
				<a href="#" class="easyui-linkbutton" iconCls="icon-add"   onclick="addPatWin()">新增康复者</a>
				<a href="#" class="easyui-linkbutton" iconCls="icon-edit"  onclick="editPatWin()">修改康复者</a>
				<a href="#" class="easyui-linkbutton" iconCls="icon-remove"   onclick="deletePat()">删除康复者</a>
				<a href="#" class="easyui-linkbutton" iconCls="icon-filter"   onclick="setShowColumn()">更多信息</a>
		    </div> -->
		    <div style="padding:5px">
		        <form id="searchPatAddrFm">  
					<span>省份:</span>&nbsp;
					 <input id="province_id" name="province_id" class="easyui-combobox" data-options="    
					            valueField: 'bz_value',    
						        textField: 'bz_value',    
						        url: 'getClsCo.action?bz_type='+'pro_type',
						        method:'get', 
                                panelHeight:'150',
                                onSelect: function(rec){    
						            var url = 'getClsCo.action?bz_type='+'city_type'+'&pid='+rec.bz_id;
						            $('#city_id').combobox('reload', url);    
						        }  "/>&nbsp;&nbsp;
					<span>城市:</span>&nbsp;
					 <input id="city_id" name="city_id" class="easyui-combobox" data-options="    
					            valueField: 'bz_value',    
						        textField: 'bz_value',    
						        url: 'getClsCo.action?bz_type='+'city_type',
						        method:'get', 
                                panelHeight:'150',
                                onSelect: function(rec){    						            
						        }  "/>&nbsp;
					<a href="#" class="easyui-linkbutton" iconCls="icon-search"  onclick="doPatAddrSearch()">搜索</a>&nbsp;&nbsp;					
					<a href="#" class="easyui-linkbutton" iconCls="icon-reload"  onclick="rePatAddrFlash()">刷新</a>
				 </form>
			</div>
		</div>
     
	  <style>
	  	#patFm{
	  		margin:0 auto !important;
	  		width: 300px
	  	}
	   .datagrid-btable tr{height: 32px;}
	   .subtotal { font-weight: bold; }/*合计单元格样式*/
	  	
	  </style>
	  
  </body>

	
</html>
