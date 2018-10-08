var param = {
	current: 1,
	size: 10
}
var id = getQueryMbWeb("id");
var ractive = new Ractive({
	el: '#test',
	template: '#test-template',
	oninit: function(options) {
		var _this = this;
		
		
		
	},
	oncomplete:function(){
		var param={
				"account":id
		}
		accountList(param);
	}
});

$(function() {
	// 初始化select控件
	$.sdSelect({
		ractive: ractive
	});
});
//页面列表初始化
function accountList(param) {
	$('#customtable').sdGrid({
		url: Constants.ctrlAddress + "logOperation/getLogPagination",
		type: 'POST',
		data: param,
		async: true,
		waitFlag: true, // true：加载数据时显示等待动画， false：不显示加载动画。
		callback: function() {
			// 数据加载完成后，希望执行的操作
			$.sdCheckAll({
				//pName:'jhshall',
				//cName:'jhsh'
			})
		},
		colProperty: [{
				colLabel: '账号名',
				style: {
					textAlign: 'center',
				},
				colModel: {
					style: {
						textAlign: 'center',
					},
					innerHtml: function(data) {
						return '<span title="' + (data.account==null?"":data.account) +
							'">' + (data.account==null?"":data.account)+
							'</span>';
					}
				}
			}, {
				colLabel: '协议类型',
				style: {
					textAlign: 'center',
					width: '100px',
				},
				colModel: {
					style: {
						textAlign: 'center',
						ellipsis: true
					},
					innerHtml: function(data) {
						var str="";
						if(Constants.PointCard==data.type){
							str="发点卡";
						}else if(Constants.Notice==data.type){
							str="通知公告";
						}
                         else if(Constants.Message==data.type){
                        	 str="发消息";
						}
                         else if(Constants.Kicking==data.type){
                        	 str="踢人";
 						}
                         else if(Constants.Lock==data.type){
                        	 str="封号";
 						}
                         else if(Constants.UnLock==data.type){
                        	 str="解封";
 						}
                         else if(Constants.Unbink==data.type){
                        	 str="解绑玩家";
 						}
						return '<span title="' + str +
							'">' + str+
							'</span>';
					}
				}
			}, {
				colLabel: '状态',
				style: {
					textAlign: 'center'
				},
				colModel: {
					style: {
						textAlign: 'center',
						ellipsis: true
					},
					innerHtml: function(data) {
						var str="";
						if(Constants.status_ing==data.status){
							str="<span style='color:red'>正在处理中...</span>";
						}else if(Constants.status_success==data.status){
							str="<span style='color:green'>处理成功</span>";
						}else if(Constants.status_failed==data.status){
							str="<span style='color:gray'>处理失败</span>";
						}
						return '<span title="' + str +
							'">' + str+
							'</span>';
					}
				}
			}, 
			/*{
				colLabel: '发送内容',
				style: {
					textAlign: 'center',
					width: '100px',
				},
				colModel: {
					style: {
						textAlign: 'center',
						ellipsis: true
					},
					innerHtml: function(data) {
						return '<span title="' + data.sendContent +
							'">' + data.sendContent +
							'</span>';
					}
				}
			}, {
				colLabel: '返回内容',
				style: {
					textAlign: 'center',
					width: '100px'
				},
				colModel: {
					style: {
						textAlign: 'center',
						ellipsis: true
					},
					innerHtml: function(data) {
						return '<span title="' + (data.returnContent==null?"":data.returnContent) +
							'">' + (data.returnContent==null?"":data.returnContent)+
							'</span>';
					}
				}
			},*/
			{
				colLabel: '发送时间',
				style: {
					textAlign: 'center',
					width: '160px'
				},
				colModel: {
					style: {
						textAlign: 'center',
						ellipsis: true
					},
					innerHtml: function(data) {
						return '<span title="' + new Date(data.createTime).format("yyyy-MM-dd hh:mm:ss") +
							'">' +  new Date(data.createTime).format("yyyy-MM-dd hh:mm:ss")   +
							'</span>';
					}
				}
			},
			{
				colLabel: '操作人',
				style: {
					textAlign: 'center',
					width: '100px'
				},
				colModel: {
					style: {
						textAlign: 'center',
						ellipsis: true
					},
					innerHtml: function(data) {
						
						return '<span title="' + data.operaterCode +
							'">' + data.operaterCode +
							'</span>';
					}
				}
			}
		],
		pagination: { // 设置是否显示分页，默认显示分页部分。  show:true 显示分页， show:false 不显示分页
			show: true,
			pageSize: 10 // 可选，默认10条
		}
	});
}
if(sessionStorage.getItem(id) == Constants.czjb_kd){
	$("#addBtn").hide();
}

function ckjl(obj){
	var opts = {
			content: 'account/czjl.html',
			scroll: false,
			title: '',
			type: 2,
			width: '800px',
			height: '600px',
			shadeClose: false,
			end: function() {
				
			}
		}
		dialog(opts);
}


//启用按钮
function QyBotton(obj) {
	var data={
			account:obj.id,
			message:"",
			type:obj.dataset.type
	}
	confirm("确定解封该玩家吗？", function() {
		$.sdAjax({
			url: Constants.ctrlAddress + "qpaccountdb/message",
			dataType: 'json',
			type: 'post',
			contentType: 'application/json',
			data: data,
			async: true, // 是否异步
			waitFlag: true, // 是否需要加载等待动画
			successCallback: function(data) {
				if(data && data.result) {
					success("操作成功");
				} else {
					success("操作失败");
				}

			}
		})
	})
}

//踢人操作
function Tr(obj){
	var data={
			account:obj.id,
			message:"",
			type:obj.dataset.type
	}
		confirm("确定踢除此玩家吗？", function() {
			$.sdAjax({
				url: Constants.ctrlAddress + "qpaccountdb/message",
				dataType: 'json',
				type: 'post',
				contentType: 'application/json',
				data: data,
				async: true, // 是否异步
				waitFlag: true, // 是否需要加载等待动画
				successCallback: function(data) {
					if(data && data.result) {
						success("操作成功");
					} else {
						success("操作失败");
					}

				}
			})
		})
}

//发消息操作
function xx(obj){
	dailoy(obj.id,obj.dataset.type,obj.dataset.title)    
}

function dailoy(id,type,title){
	var tips="";
	switch (parseInt(type)) {
	case Constants.PointCard:
		tips="请输入点卡数量，必须是数字";
		break;
	case Constants.Notice:
		tips="请输入通知公告内容";	
		break;
	case Constants.Message:
		tips="请输入消息内容";
		break;
	case Constants.Kicking:
		break;
	case Constants.Lock:
		tips="请输入封号时长，必须数字，单位是秒";
		break;
	case Constants.UnLock:
		break;
	}
	layer.open({
	      type: 1,
	      title:title,
	      area: ['600px', '360px'],
	      shadeClose: true, //点击遮罩关闭
	      content: '\<\div style="padding:20px;"><textarea placeholder="'+tips+'" id="xiaoxi" style="width:560px;height:220px" rows="" cols=""></textarea><div style="margin-left: 219px;margin-top: 15px;"><span  id="'+id+'" class="sdui-btn sdui-btn-warm" onclick="send(this.id,'+type+')">发送</span></div>\<\/div>'
	    });
}

//发送消息
function send(id,type){
	var xiaoxi=$("#xiaoxi").val();
	switch (parseInt(type)) {
	case Constants.PointCard:
		if(isNaN(xiaoxi)){
			error("请输入数字",null,false);
			return;
		}
		break;
	case Constants.Notice:
		break;
	case Constants.Message:
		break;
	case Constants.Kicking:
		break;
	case Constants.Lock:
		if(isNaN(xiaoxi)){
			error("请输入数字",null,false);
			return;
		}
		break;
	case Constants.UnLock:
		break;
	}
	
	var data={
			account:id,
			message:xiaoxi,
			type:type
	}
	$.sdAjax({
		url: Constants.ctrlAddress + "qpaccountdb/message",
		dataType: 'json',
		type: 'post',
		contentType: 'application/json',
		data: data,
		async: true, // 是否异步
		waitFlag: true, // 是否需要加载等待动画
		successCallback: function(data) {
			if(data && data.result) {
				success("操作成功");
				layer.closeAll();
			} else {
				success("操作失败");
				layer.closeAll();
			}

		}
	})
}

//停用按钮
function TyBotton(obj) {
	var data={
			account:obj.id,
			message:"",
			type:obj.dataset.type
	}
	confirm("确定封停此玩家吗？", function() {
		$.sdAjax({
			url: Constants.ctrlAddress + "qpaccountdb/message",
			dataType: 'json',
			type: 'post',
			contentType: 'application/json',
			data: data,
			async: true, // 是否异步
			waitFlag: true, // 是否需要加载等待动画
			successCallback: function(data) {
				if(data && data.result) {
					success("操作成功");
				} else {
					success("操作失败");
				}

			}
		})
	})
}
//删除按钮 
function removeItem(id) {
	var data = {
		id: id,
	}
	confirm("删除将无法恢复，确认删除运营中心超级管理员吗？", function() {
		$.sdAjax({
			url: Constants.ctrlAddress + "removeYhxx",
			dataType: 'json',
			type: 'post',
			contentType: 'application/json',
			data: data,
			async: true, // 是否异步
			waitFlag: true, // 是否需要加载等待动画
			successCallback: function(data) {
				if(data && data.result) {
					success("删除成功");
					yhxxList(param);
				} else {
					if (data.message) {
						error(data.message);
					} else {
                        error("删除失败");
					}
				}
			}
		})
	})
}
//修改跳转
function edits(id) {
	window.location.href = "xgyh.html?id=" + id
}
//新增跳转
function addYh() {
	window.location.href = "xzyh.html";
}
//查看跳转
function ckyh(id) {
	window.location.href = "ckyh.html?id=" + id
}
//
function modifymm(id) {
	var opts = {
		content: 'yhgl/xgyhmm.html?id=' + id,
		scroll: false,
		title: '重置密码',
		type: 2,
		width: '500px',
		height: '400px',
		shadeClose: false,
		end: function() {}
	}
	dialog(opts);
}