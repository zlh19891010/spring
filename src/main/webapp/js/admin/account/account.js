var param = {
	current: 1,
	size: 10
}
var id = getQueryMbWeb("id");
if(id==6){
	$("#isShow").css("display","none");
	$("#title").text("我的玩家");
}
var ractive = new Ractive({
	el: '#test',
	template: '#test-template',
	oninit: function(options) {
		var _this = this;
		var sfzxlist = new Array();
		var zhztlist = new Array();
		$.ajax({
			url: Constants.ctrlAddress + "getCodeListByCodeclass?codeclass=SFZX",
			type: "get",
			dataType: "json",
			contentType: "application/json",
			success: function(obj) {
				if(typeof obj == "object") {
					for(var i = 0; i < obj.length; i++) {
						sfzxlist.push({
							"code": obj[i].code,
							"name": obj[i].codedesc
						});
					}
					ractive.set("sfzxlist", sfzxlist);
				}
			}
		});
		$.ajax({
			url: Constants.ctrlAddress + "getCodeListByCodeclass?codeclass=ZHZT",
			type: "get",
			dataType: "json",
			contentType: "application/json",
			success: function(obj) {
				if(typeof obj == "object") {
					for(var i = 0; i < obj.length; i++) {
						zhztlist.push({
							"code": obj[i].code,
							"name": obj[i].codedesc
						});
					}
					ractive.set("zhztlist", zhztlist);
				}
			}
		});
		_this.on("cx", function(event) {
			param.strAccounts = ractive.get("param.strAccounts");
			param.strNickName = ractive.get("param.strNickName");
			param.bOnline = ractive.get("param.bOnline");
			param.nState = ractive.get("param.nState");
			accountList(param)
		})
	},
	oncomplete:function(){
		$.ajax({
			url: Constants.ctrlAddress + "qpaccountdb/selectCount",
			type: "get",
			dataType: "json",
			contentType: "application/json",
			success: function(obj) {
				$("#onCount").text(obj.object);
			}
		});
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
		url: Constants.ctrlAddress + "qpaccountdb/getAccountList",
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
			colLabel: '用户ID',
			style: {
				textAlign: 'center',
			},
			colModel: {
				style: {
					textAlign: 'center',
				},
				innerHtml: function(data) {
					return '<span title="' + (data.suid==null?"":data.suid) +
						'">' + (data.suid==null?"":data.suid) +
						'</span>';
				}
			}
		}
		,
		/*{
				colLabel: '账号',
				style: {
					textAlign: 'center',
				},
				colModel: {
					style: {
						textAlign: 'center',
					},
					innerHtml: function(data) {
						return '<span title="' + (data.strOpenID==null?"":data.strOpenID) +
							'">' + (data.strOpenID==null?"":data.strOpenID) +
							'</span>';
					}
				}
			}
			, 
			, 
			{
				colLabel: '机器识别码',
				style: {
					textAlign: 'center'
				},
				colModel: {
					style: {
						textAlign: 'center',
						ellipsis: true
					},
					innerHtml: function(data) {
						return '<span title="' + (data.strMachineID==null?"":data.strMachineID) +
							'">' + (data.strMachineID==null?"":data.strMachineID) +
							'</span>';
					}
				}
			}
			,*/
			/*{
				colLabel: '头像路径',
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
						return '<span title="' + (data.strIcon==null?"":data.strIcon) +
							'">' + (data.strIcon==null?"":data.strIcon) +
							'</span>';
					}
				}
			},*/
			/*{
				colLabel: '第三方登入唯一ID',
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
						return '<span title="' + data.strOpenID +
							'">' + data.strOpenID +
							'</span>';
					}
				}
			},
			{
				colLabel: '第三方登入token',
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
						return '<span title="' + data.strToken +
							'">' + data.strToken +
							'</span>';
					}
				}
			},*/
			
			
			 {
				colLabel: '昵称',
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
						return '<span title="' + (data.strNickName==null?"":data.strNickName) +
							'">' + (data.strNickName==null?"":data.strNickName) +
							'</span>';
					}
				}
			},{
				colLabel: '是否在线',
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
						var sfzx = "";
						if(data.bOnline == Constants.sfzx_zx) {
							sfzx = "是";
						}
						if(data.bOnline == Constants.sfzx_bzx) {
							sfzx = "否";
						}
						return '<span title="' + sfzx +
							'">' + sfzx +
							'</span>';
					}
				}
			},
			 /*{
				colLabel: '账户状态',
				style: {
					textAlign: 'center',
					width: '80px'
				},
				colModel: {
					style: {
						textAlign: 'center',
						ellipsis: true
					},
					innerHtml: function(data) {
						var zhzt = "";
						if(data.nState == Constants.zhzt_zc) {
							zhzt = "正常";
						}
						if(data.nState == Constants.zhzt_jz) {
							zhzt = "禁止";
						}
						return '<span title="' + zhzt +
							'">' + zhzt +
							'</span>';
					}
				}
			},*/
			{
				colLabel: '性别',
				style: {
					textAlign: 'center',
					width: '80px'
				},
				colModel: {
					style: {
						textAlign: 'center',
						ellipsis: true
					},
					innerHtml: function(data) {
						var xb = "";
						if(data.nSex == Constants.xb_m) {
							xb = "男";
						}
						if(data.nSex == Constants.xb_w) {
							xb = "女";
						}
						return '<span title="' + xb +
							'">' + xb +
							'</span>';
					}
				}
			},
			{
				colLabel: '房卡数量',
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
						return '<span title="' + (data.nCards==null?0:data.nCards) +
							'">' + (data.nCards==null?0:data.nCards) +
							'</span>';
					}
				}
			}
			,
			{
				colLabel: '最后登入时间',
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
						return '<span title="' +(data.tmLastTime==null?"": new Date(data.tmLastTime).format("yyyy-MM-dd hh:mm:ss")) +
							'">' +  (data.tmLastTime==null?"": new Date(data.tmLastTime).format("yyyy-MM-dd hh:mm:ss"))   +
							'</span>';
					}
				}
			},
			{
				colLabel: '登入IP地址',
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
						return '<span title="' + (data.strClientIP==null?"":data.strClientIP) +
							'">' + (data.strClientIP==null?"":data.strClientIP) +
							'</span>';
					}
				}
			},
			{
				colLabel: '代理ID',
				style: {
					textAlign: 'center',
					width: '200px',
				},
				colModel: {
					style: {
						textAlign: 'center',
						ellipsis: true
					},
					innerHtml: function(data) {
						
						var str = "";
						if(data.sAuthCode&&data.sAuthCode.trim()!=""){
							str+='<span class="run-icon" style="color:blue" id="' + data.strOpenID + '" data-type=10 onclick="unbink(this)">'+data.sAuthCode+' 解绑</span> ';
						}else{
							str += "未绑定";
						}
						return '<span title="' + (data.sAuthCode==null?"":data.sAuthCode) +
							'">' + str +
							'</span>';
					}
				}
			}
             ,
			{
				colLabel: '操作',
				style: {
					textAlign: 'center',
				},
				colModel: {
					style: {
						textAlign: 'center',
						ellipsis: true
					},
					innerHtml: function(data) {
						var str = "";
						/*if(data.sAuthCode){
							str+='<span class="run-icon" id="' + data.strOpenID + '" data-type=10 onclick="unbink(this)">解绑玩家</span> ';
						}*/
						if(id==6){
							str+='<span class="mhwzgl_icon_cur" id="' + data.strOpenID + '" data-strNickName="'+data.strNickName+'" data-type=1 data-title="发点卡" onclick="xx(this)">发点卡</span>';
						}else{
							if(data.nState == "1" ) {
								/*str += '<span class="run-icon" id="' + data.strOpenID + '" data-type=6 onclick="QyBotton(this)">解封</span> ' +*/
									/*'<span class="delete-icon" id="' + data.id + '" onclick="removeItem(this.id)" >删除</span>' +*/
									'';
							}
							if(data.nState == "0") {
								/*str += '<span class="stop-icon" id="' + data.strOpenID + '" data-type=5 data-title="封号" onclick="xx(this)">封号</span>';
							   if(data.bOnline=="1"){
								str += '<span class="fb_icon" id="' + data.strOpenID + '" data-type=4 onclick="Tr(this)">踢人</span>';  
							   }*/
							}
							/*str+='<span class="hf_icon" id="' + data.strOpenID + '" data-type=3 data-title="消息" onclick="xx(this)">发消息</span>';*/
							str+='<span class="mhwzgl_icon_cur" id="' + data.strOpenID + '" data-strNickName="'+data.strNickName+'" data-type=1 data-title="发点卡" onclick="xx(this)">发点卡</span>';
							str+='<span class="look-icon" id="' + data.strOpenID + '"  onclick="ckjl(this)">查看操作记录</span>';
						}
						return str;
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
			content: 'account/czjl.html?id='+obj.id,
			scroll: false,
			title: '',
			type: 2,
			width: '78%',
			height: '63%',
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

function unbink(obj){
	var data={
			account:obj.id,
			message:"",
			type:obj.dataset.type
	}
	confirm("确定解绑该玩家吗？", function() {
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
	dailoy(obj.id,obj.dataset.type,obj.dataset.title,obj.dataset.strnickname)    
}

function dailoy(id,type,title,strnickname){
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
	      content: '\<\div style="padding:20px;"><textarea placeholder="'+tips+'" id="xiaoxi" style="width:560px;height:220px" rows="" cols=""></textarea><div style="margin-left: 219px;margin-top: 15px;"><span  id="'+id+'" data-strNickName="'+strnickname+'" data-type="'+type+'" class="sdui-btn sdui-btn-warm" onclick="send(this)">发送</span></div>\<\/div>'
	    });
}
function isPInt(str) {
    var g = /^[1-9]*[1-9][0-9]*$/;
    return g.test(str);
}
//发送消息
function send(obj){
	var xiaoxi=$("#xiaoxi").val();
	switch (parseInt(obj.dataset.type)) {
	case Constants.PointCard:
		if(!isPInt(xiaoxi)){
			error("请输入正整数",null,false);
			return;
		}
		var count=0;
		$.sdAjax({
			url: Constants.ctrlAddress + "tGlptGlyglGlyxx/selectCardByUserId",
			dataType: 'json',
			type: 'post',
			contentType: 'application/json',
			async: false, // 是否异步
			waitFlag: true, // 是否需要加载等待动画
			successCallback: function(data) {
				if(data && data.result) {
					count=data.object;
				} else {
					success("操作失败");
				}

			}
		})
		if(count!=Constants.ADMIN&&xiaoxi>count){
			error("您的点卡余额不足",null,false);
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
		if(!isPInt(xiaoxi)){
			error("请输入正整数",null,false);
			return;
		}
		break;
	case Constants.UnLock:
		break;
	}
	
	var data={
			account:obj.id,
			message:xiaoxi,
			type:obj.dataset.type,
			strnickname:obj.dataset.strnickname
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
				$.sdAjax({
					url: Constants.ctrlAddress + "tGlptGlyglGlyxx/selectCardByUserId",
					dataType: 'json',
					type: 'post',
					contentType: 'application/json',
					async: true, // 是否异步
					waitFlag: true, // 是否需要加载等待动画
					successCallback: function(data) {
						if(data && data.result) {
							window.parent.document.getElementById("card_").innerText=data.object;
						} else {
							success("操作失败");
						}

					}
				})
				success("操作成功",null,true);
				layer.closeAll();
			} else {
				if(Constants.NOT_ENOUGH==data.code){
				alert("点卡余额不足",null,false);	
				return
				}
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