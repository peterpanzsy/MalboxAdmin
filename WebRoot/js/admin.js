$(function () {
	$.ajax({
		type : "POST",
		url : "getCurrentSysNum.action",
		dataType:"json",
		success : function(data) {
			$("#cursysNum").text(data.cursysNum);			
		},
		error:function(msg){			
		}
	});
	
	$("#sysNum").change(function(){
				var sysNum = $("#sysNum").children('option:selected').val();
				if(check_Integer(sysNum)){
					$.ajax({
						type : "POST",
						url : "systemTransferAction.action",
						data : {
							sysNum:sysNum
						},
						dataType:"json",
						success : function(data) {
							if(data.result=="success"){
								alert("更新成功！");
								$("#cursysNum").text(data.sysNum);
							}else{
								alert("更新失败！");
							}							
						},
						error:function(msg){
							alert("通讯错误，更新失败！！");						
						}
					});
				}
		});
		
		function check_Integer(obj){
		    //定义正则表达式部分
			 var Expression= /[0-9]/;  
		    var reg=new RegExp(Expression);
		    if(!reg.test(obj)){ 
			    return false;
		    }else{
				return true;  
			}
		}
	
	
        $("#uploadify").uploadify({
            //指定swf文件
            'swf': 'js/uploadify/uploadify.swf',
            'cancelImg'   : 'js/uploadify/uploadify-cancel.png',
            //后台处理的页面
            'uploader': 'uploadSample.action',
            //按钮显示的文字
            'buttonText': '选择文件',
            //显示的高度和宽度，默认 height 30；width 120
            //'height': 15,
            //'width': 80,
            //上传文件的类型  默认为所有文件    'All Files'  ;  '*.*'
            //在浏览窗口底部的文件类型下拉菜单中显示的文本
            'fileTypeDesc': 'All Files',
            //允许上传的文件后缀
            'fileTypeExts': '*.*',
            //发送给后台的其他参数通过formData指定
            //'formData': { 'someKey': 'someValue', 'someOtherKey': 1 },
            //上传文件页面中，你想要用来作为文件队列的元素的id, 默认为false  自动生成,  不带#
            //'queueID': 'fileQueue',
            //选择文件后自动上传
            'auto': false,
            //设置为true将允许多文件上传
            'multi': true,
            'fileSizeLimit':'0',
            'fileObjName' : 'samplefile',
            'onUploadSuccess' : function(file, data, response){
            		alert("上传成功！");      
            		$("#TaskList").trigger("reloadGrid");
            },
    	    'onUploadStart': function (file) { 
    	    	var name=file.name;
    	    	var type=file.type;
    	    	$("#uploadify").uploadify("settings", "formData", {'name':name,'type':type });  
    	    }
        });
        
    	var datagrid = jQuery("#TaskList")
		.jqGrid(
				{
					url : "listTask.action",// 后端的数据交互程序，改为你的
					datatype : "json",// 前后交互的格式是json数据
					mtype : 'POST',// 交互的方式是发送httpget请求						
					colNames : ['ID', '最近发现时间','最早发现时间', '名称', 'MD5', '大小', '是否分析完毕','查看分析报告'],// 表格的列名
					colModel : [
					        {
					        	name:'ID',
					        	index:'ID',
					        	hidden:true
					        },    
							{
								name : 'lasttime',
								index : 'lasttime',
								width : 100,
								align : "center",
								sortable:true,
								formatter:function(value,options,rows){
									return rows.lasttime.replace("T"," ");
								}
							},// 每一列的具体信息，index是索引名，当需要排序时，会传这个参数给后端
							{
								name : 'firsttime',
								index : 'firsttime',
								width : 100,
								align : "center",
								sortable:true,
								formatter:function(value,options,rows){
									return rows.firsttime.replace("T"," ");
								}
							},
							{
								name : 'name',
								index : 'name',
								width : 100,
								align : "center",
								sortable:true
							},
							{
								name : 'md5',
								index : 'md5',
								width : 150,
								align : "center",
								sortable:true
							},
							{
								name:'filesize',
								index:'filesize',
								width: 50,
								align:'center',
								sortable:true
							},
							{
								name:'analysis',
								index:'analysis',
								width: 110,
								align:'center',
								sortable:true,
								formatter:"select",
								editoptions:{value:"0:否;1:是"}
							},
//							{
//								name:'analysis',
//								index:'analysis',
//								width: 100,
//								align:'center',
//								sortable:true,
//								formatter:function(cellvalue,options,rows){
//									//cellvalue - 当前cell的值  
//									//options - 该cell的options设置，包括{rowId, colModel,pos,gid}  
//									//rowObject - 当前cell所在row的值，如{ id=1, name="name1", price=123.1, ...}  
//									return "<select onchange=viewReport(this.value,'"+rows.md5+"') style='margin:2px 10px 2px 0px;padding:1px;font-size:14px;font-family:'Microsoft YaHei''>" +
//											"<option value='none' selected>请选择规则</option><option value='net'>网络行为</option><option value='register'>注册表行为</option><option value='file'>文件行为</option>" +
//											"<option value='process'>进程行为</option><option value='all'>所有行为</option>" +
//											"</select>";
//								}
//							},
							{
								name:'analysisCheck',
								index:'analysisCheck',
								width: 420,
								align:'center',
								sortable:true,
								formatter:function(cellvalue,options,rows){
									//cellvalue - 当前cell的值  
									//options - 该cell的options设置，包括{rowId, colModel,pos,gid}  
									//rowObject - 当前cell所在row的值，如{ id=1, name="name1", price=123.1, ...}  
									return "<div style='margin:2px 10px 2px 0px;padding:1px;font-size:14px;font-family:'Microsoft YaHei''>" +
											"<input type='checkbox' name='checkbox' value='net'>网络行为" +
											"<input type='checkbox' name='checkbox' value='register'>注册表行为" +
											"<input type='checkbox' name='checkbox' value='file'>文件行为" +
											"<input type='checkbox' name='checkbox' value='process'>进程行为" +
											"<button style='margin-left:5px' value='查看' onclick=viewReport('"+rows.md5+"')>查看</button></div>";
								}
							}
						
		
							],
//					autowidth:true,
					rowNum:20,//每一页的行数
					height:'auto',
					width:1070,
					rowList:[10,20,30],
					pager: '#TaskPager',
					sortname: 'id',
					viewrecords: true,
					sortorder: "desc",
					multiselect: true,  //可多选，出现多选框 
				    multiselectWidth: 35, //设置多选列宽度 
					jsonReader: {//读取后端json数据的格式
					root: "dataList",//保存详细记录的名称
					total: "total",//总共有页
					page: "page",//当前是哪一页
					records: "records",//总共记录数
					repeatitems: false
					},
					caption: "恶意代码列表",//表格名称,
					jsonReader : {// 读取后端json数据的格式
						root : "dataList",// 保存详细记录的名称
						// total: "total",//总共有页
						// page: "page",//当前是哪一页
						records : "records",// 总共记录数
						repeatitems : false
					}
				}	
		);
   		
    	datagrid.jqGrid('navGrid','#TaskPager',{
    			edit : false,
    			add : false,
//    			search:true,
    			del : false}).jqGrid('navButtonAdd',"#TaskPager",{
    					title:'删除',
    					caption:"删除",	
    					id:"delete_TaskList",
    					onClickButton:deleteTask,
    					position:"first"
    				});   
    	
    	setTimeout("refreshTaskGrid()",100000);
});
function refreshTaskGrid(){
	   $("#TaskList").trigger("reloadGrid");
}
function viewReport(md5){	
	var val="";
    $("input[name='checkbox']:checkbox").each(function(){ 
       if($(this).attr("checked")){
           val += $(this).val()+",";
       }
    })
       
	var title="";
	var flag=1;
/*	switch(val){
		case "none":flag=0;break;
		case "net":title="网络行为报告";break;
		case "file":title="文件行为报告";break;
		case "process":title="进程行为报告";break;
		case "register":title="注册表行为报告";break;
		case "all":title="所有行为报告";break;
		default:title="恶意行为报告";break;
	}*/
	if(flag==1){
		$.ajax({
			url:"viewReport.action",
			method:"post",
			data:{
				fileMD5:md5,
				regulation:val
			},
			dataType:"json",
			success:function(data){
				   $.layer({
				        type: 1,
				        title: title, //不显示默认标题栏
				        shade: [0], //不显示遮罩
				        area: ['700px', 'auto'],
				        page: {html: '<div><textarea cols="94" rows="20" readonly="readonly" style="font-size:13px;">'+data.report+'</textarea></div>'}
				    });
			}
		});
	}
};

function deleteTask() {
        var sels = $("#TaskList").jqGrid('getGridParam','selarrrow'); 
        if(sels==""){ 
           alert("请选择要删除的项!");
        }else{ 
        	var selectedIDs={};
        	$.each(sels,function(i,n){ 
              if(sels[i]!=""){ 
            	  var rowData = $("#TaskList").jqGrid("getRowData", sels[i]);
            	  selectedIDs["ids[" + i + "]"]=rowData.ID;
              } 
        	}); 

           if(confirm("您是否确认删除？")){ 
            $.ajax({ 
              type: "POST", 
              url: "delTask.action", 
              data: selectedIDs, 
              success: function(msg){ 
            	alert("删除成功！");
				$("#TaskList").trigger("reloadGrid");
              } 
            }); 
           } 
        } 
		

}