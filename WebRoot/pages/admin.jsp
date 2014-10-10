<%@ page language="java" import="java.util.*,cn.edu.xjtu.manage.business.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>

		<base href="<%=basePath%>">
	  	<title>Malbox管理系统</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">    
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="Malbox管理">
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
		
		<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.css">
		<link rel="stylesheet" type="text/css" href="bootstrap/css/carousel.css">
		<link rel="stylesheet" href="css/docs-5892f62386325d472ddefdaec9d29018.css"> 
		<link rel="stylesheet" type="text/css" media="screen" href="css/tabs.css" />
		<link rel="stylesheet" href="css/docs-5892f62386325d472ddefdaec9d29018.css"> 
		<link href="Flat-UI-master/css/flat-ui.css" rel="stylesheet">
    	<link rel="Flat-UI-master/shortcut icon" href="images/favicon.ico">
		<link rel="stylesheet" type="text/css" media="screen" href="js/jqueryUI/css/jquery-ui-1.10.3.custom.css" />
	
		<link rel="stylesheet" type="text/css" media="screen" href="js/jqGrid/css/ui.jqgrid.css" />
		<link rel="stylesheet" type="text/css" media="screen" href="js/jqueryUI/themes/redmond/jquery.ui.theme.css" />
		 
		<script src="js/jquery/jquery-1.10.2.min.js" type="text/javascript"></script>
		<script src="js/jquery/jquery-migrate-1.2.1.js"></script>
		<script type="text/javascript" src="js/jquery-validation-1.11.1/dist/jquery.validate.js"></script>
		
		<script src="bootstrap/js/bootstrap.js"></script>
		<script src="bootstrap/js/holder.min.js"></script>
		<script src="js/jqGrid/js/i18n/grid.locale-cn.js" type="text/javascript"></script>
		<script src="js/jqGrid/js/jquery.jqGrid.min.js" type="text/javascript"></script>
	
		<script src="js/easytabs/jquery.hashchange.min.js" type="text/javascript"></script>
		<script src="js/easytabs/jquery.ba-hashchange.js" type="text/javascript"></script>
		<script src="js/easytabs/jquery.easing.1.3.js" type="text/javascript"></script>
		<script src="js/easytabs/jquery.easytabs.js" type="text/javascript"></script>
		<script src="js/easytabs/jquery.raptorize.1.0.js" type="text/javascript"></script>
		
	    <script src="Flat-UI-master/js/jquery-ui-1.10.3.custom.min.js"></script>
	    <script src="Flat-UI-master/js/jquery.ui.touch-punch.min.js"></script>
	    <script src="Flat-UI-master/js/bootstrap-select.js"></script>
	    <script src="Flat-UI-master/js/bootstrap-switch.js"></script>
	    <script src="Flat-UI-master/js/flatui-checkbox.js"></script>
	    <script src="Flat-UI-master/js/flatui-radio.js"></script>
	    <script src="Flat-UI-master/js/jquery.tagsinput.js"></script>
	    <script src="Flat-UI-master/js/jquery.placeholder.js"></script>
	    
	    <link rel="stylesheet" href="js/uploadify/uploadify.css">
	 	<script type="text/javascript" src="js/uploadify/jquery.uploadify.min.js"></script>
	 	
	    <script src="js/layer-v1.8.5/layer/layer.min.js"></script>
	    <script type="text/javascript" src="js/admin.js"></script>
	    
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
 
  
 
	<style>
	 body{
	 font-family:'微软雅黑'
	 } 
	 label{
	 font-size:14px;
	 }
	 h2{
		font-size:20px;
		font-family:'微软雅黑'
	 }
	</style>

</head>

  <body style="background-image:url(images/background.jpg);background-repeat:repeat-y ">
    <nav class="navbar" role="navigation">
	  <div class="container" style="background-image:url(images/background.jpg);">
	    <div class="row">	  
	      <div class="collapse navbar-collapse">
	      	<div>
	      	<ul style="margin-top:10px;margin-bottom:-80px;float:left">
	      		<img style="width:40%;margin-left:-100px;" alt="BotWall 管理系统" src="images/eagleeye-dark.png">
	      	 	<h4 style="color:#eee">Malbox管理系统</h4>
	      	</ul>
	        <ul class="nav navbar-nav navbar-right" style="margin-top:10px;margin-right:15px;float:left">
	          <li class="active" style="color:#eee;font-size:15px;">	 
	          	产品名称：Malbox管理设备</br>
	          	产品型号：MB-0001</br>
	          	版本号：Version 1.0	
	          </li>
	        </ul>
	        </div>
	      </div>
	    </div>
	</div>
	</nav>

    <div class="container content" style="margin-top:-30px;">
      <div class="row">
		  <div style="margin-left:20px">
		    <div style="float:left;margin-bottom:20px;">		    	
		      <h3 class="list-group-heading" style="color:white;font-style:bold;font-size:20px;font-family:微软雅黑">提交样本</h3>
		      <div>
			      <div style="border:2px solid;padding:10px;width:150px;">
			        <%--用来作为文件队列区域--%>
			        <div id="fileQueue">
			        </div>
			        <input type="file" name="uploadify" id="uploadify" />
			        <div >
			            <a href="javascript:$('#uploadify').uploadify('upload')">上传</a>| 
			            <a href="javascript:$('#uploadify').uploadify('cancel')">取消上传</a>
					</div>
			     </div>		
			 </div>
		    </div>
		    <div style="float:left;width:600px;color:white;margin-top:50px;margin-left:50px;">
				 	点击选择文件，将恶意文件上传。Malbox将对其进行分析，一段时间之后当下面列表中“是否分析完毕”字段变为“是”，则可查看恶意样本的各种行为的报告。
			</div>	
				 
		  </div>
	
		</div>
		
		<div>							
			<!-- 表格 -->
      		<table id="TaskList" class="table table-striped table-bordered table-hover datatable " ></table>
      		<div id="TaskPager" ></div>      		
		</div>
								
    </div>
    <footer style="margin-top:200px;color: #999999;padding: 40px;text-align: center;color:white;background-color:rgba(149, 187, 224, 0.33)">
  		<span id="copyright">&copy; 2014 </span> 
	</footer>
		
  </body>
 
	
	<script >

	</script>
	
</html>

	