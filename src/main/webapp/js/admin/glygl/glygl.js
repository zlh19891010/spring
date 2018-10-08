var id = getQueryMbWeb("id");
var ractive = new Ractive({
	el: '#test',
	template: '#test-template',
	oninit: function() {
		var _this = this;
		_this.on("cx", function() {
			_this.ck();
		});
		var yhztlist = new Array();
		$.ajax({
			url: Constants.ctrlAddress + "getCodeListByCodeclass?codeclass=yhzt",
			type: "get",
			dataType: "json",
			contentType: "application/json",
			success: function(obj) {
				if(typeof obj == "object") {
					for(var i = 0; i < obj.length; i++) {
						yhztlist.push({
							"code": obj[i].code,
							"name": obj[i].codedesc
						});
					}
					_this.set("yhztlist", yhztlist);
				}
			}
		});
		$.sdAjax({
			url: Constants.ctrlAddress + "searchQxczjb",
			dataType: 'json',
			type: 'post',
			contentType: 'application/json',
			data: {
				qxCode: id,
				jsgs: Constants.jsgspt
			},
			async: true, // 是否异步
			waitFlag: true, // 是否需要加载等待动画
			successCallback: function(data) {
				sessionStorage.setItem(id, data.object.czjb);
				cx({});
			}
		})
		if(sessionStorage.getItem("czjb") == Constants.czjb_kd){
			$("#addBtn").hide();
		}
	},
	ck: function() {
		var _this = this;
		cx(_this.get("param"));
	},

});

// 初始化select控件
$.sdSelect({
	ractive: ractive
});
//新增管理员
function addGly() {
	window.location.href = "xzgly.html"
};
//启用
function qy(id) {
	confirm("确认启用吗", function() {
		xgqq({
			id: id,
			glyZt: Constants.ztyqy
		});
		success("启用成功")
		ractive.ck();
	});
};
//停用
function ty(id) {
	confirm("确认停用吗", function() {
		xgqq({
			id: id,
			glyZt: Constants.ztyty
		});
		success("停用成功")
		ractive.ck();
	});
};
//修改管理员
function modifyGly(id) {
	window.location.href = "xggly.html?id=" + id;
};
//查看管理员
function ck(id) {
	window.location.href = "ckgly.html?id=" + id;
}

function updateCards(id,yhmc){
	layer.open({
	      type: 1,
	      title:"分配点卡",
	      area: ['600px', '360px'],
	      shadeClose: true, //点击遮罩关闭
	      content: '\<\div style="padding:20px;"><textarea placeholder="请输入点卡数量，必须是数字" id="cards" style="width:560px;height:220px" rows="" cols=""></textarea><div style="margin-left: 219px;margin-top: 15px;"><span  id="'+id+'" yhmc='+yhmc+' class="sdui-btn sdui-btn-warm" onclick="send(this)">分配</span></div>\<\/div>'
	    });
}

function isPInt(str) {
    var g = /^[1-9]*[1-9][0-9]*$/;
    return g.test(str);
}
//发送消息
function send(obj){
	var cards=$("#cards").val();
	if(!isPInt(cards)){
		error("请输入正整数",null,false);
		return;
	}
	$.sdAjax({
		url: Constants.ctrlAddress + "tGlptGlyglGlyxx/updateCards?id="+obj.id+"&cards="+cards+"&yhmc="+$(obj).attr("yhmc"),
		dataType: 'json',
		type: 'get',
		contentType: 'application/json',
		async: true, // 是否异步
		waitFlag: true, // 是否需要加载等待动画
		successCallback: function(data) {
			if(data && data.result) {
				ractive.ck();
				success("操作成功",null,true);
				layer.closeAll();
			} else {
				success("操作失败");
				layer.closeAll();
			}

		}
	})
}




//重置密码
function modifymm(id) {

	var opts = {
		content: 'glpt/glyczmm.html?id=' + id,

		scroll: false,
		title: '重置密码',
		type: 2,
		width: '500px',
		height: '400px',
		shadeClose: false,
		end: function() {}
	}

	dialog(opts);
};
//删除
function deleteGly(id) {
	confirm("确认删除吗", function() {
		$.sdAjax({
			url: Constants.ctrlAddress + "tGlptGlyglGlyxx/modifyDelflag?id=" + id,
			type: 'get',
			dataType: 'json',
			successCallback: function(data) {
				if(data.result) {
					success("删除成功")
					ractive.ck();
				}
			}
		});
	});
};
//修改调用后台请求
function xgqq(data) {
	$.sdAjax({
		url: Constants.ctrlAddress + "tGlptGlyglGlyxx/modifyGly",
		type: 'post',
		data: data,
		dataType: 'json',
		successCallback: function(data) {

		}
	});

};
//授权
function authCode(id){
	$.sdAjax({
		url: Constants.ctrlAddress + "tGlptGlyglGlyxx/authCode?id="+id,
		type: 'get',
		dataType: 'json',
		successCallback: function(data) {
			if(data&&data.result){
				success("授权成功",null,true);
				ractive.ck();
			}else{
				alert("授权失败",null,true);
			}
		}
	});
}


//列表查询
function cx(data) {
	$('#customtable').sdGrid({
		url: Constants.ctrlAddress + "tGlptGlyglGlyxx/getGlyxxList",
		type: 'POST',
		data: data,
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
			colLabel: '编号',
			style: {
				textAlign: 'center',
			},
			colModel: {
				style: {
					textAlign: 'center',
					ellipsis: true
				},
				innerHtml: function(data) {
					return '<span title="' + data.id +
						'">' + data.id +
						'</span>';
				}
			}
		}, {
			colLabel: '名称',
			style: {
				textAlign: 'center',
				width: '130px',
			},
			colModel: {
				style: {
					textAlign: 'center',
					ellipsis: true
				},
				innerHtml: function(data) {
					return '<span title="' + data.loginId +
						'">' + data.loginId +
						'</span>';
				}
			}
		}, {
			colLabel: '账号',
			style: {
				textAlign: 'center'
			},
			colModel: {
				style: {
					textAlign: 'center',
					ellipsis: true
				},
				innerHtml: function(data) {
					return '<span title="' + data.zh +
						'">' + data.zh +
						'</span>';
				}
			}
		}, {
			colLabel: '姓名',
			style: {
				textAlign: 'center',
				width: '120px',
			},
			colModel: {
				style: {
					textAlign: 'center',
					ellipsis: true
				},
				innerHtml: function(data) {
					return '<span title="' + data.yhmc +
						'">' + data.yhmc +
						'</span>';
				}
			}
		}, {
			colLabel: '角色名称',
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
					return '<span title="' + data.jsmc +
						'">' + data.jsmc +
						'</span>';
				}
			}
		}, {
			colLabel: '状态',
			style: {
				textAlign: 'center',
			},
			colModel: {
				style: {
					textAlign: 'center',
					ellipsis: true
				},
				innerHtml: function(data) {
					var yhzt = "";
					if(data.yhzt == Constants.ztwqy) {
						yhzt = "未启用";
					}
					if(data.yhzt == Constants.ztyqy) {
						yhzt = "已启用";
					}
					if(data.yhzt == Constants.ztyty) {
						yhzt = "已停用";
					}
					return '<span title="' + yhzt +
						'">' + yhzt +
						'</span>';
				}
			}
		},
		{
			colLabel: '点卡余额',
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
					return '<span title="' + data.cards +
						'">' + data.cards +
						'</span>';
				}
			}
		}
		,
		{
			colLabel: '授权码',
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
					return '<span title="' + (data.sAuthCode==null?"":data.sAuthCode) +
						'">' + (data.sAuthCode==null?"":data.sAuthCode) +
						'</span>';
				}
			}
		}
		, {
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
					var str = '';
					if(data.yhzt == Constants.ztyqy) {
						str = '<span class="look-icon" onclick = "ck(\'' + data.id + '\');">查看</span><span class="stop-icon" onclick = "ty(\'' + data.id + '\');">停用</span><span class="xgmm-icon" onclick = "modifymm(\'' + data.id + '\');">重置密码</span>';
						str += '<span class="tjfx_icon_cur" onclick = "updateCards(\'' + data.id + '\',\''+data.yhmc+'\');">分配点卡</span>';
					    if(data.sAuthCode==null){
					    	str += '<span class="ckqx" onclick = "authCode(\'' + data.id + '\');">授权</span>';
					    }
					} else {
						str = '<span class="look-icon" onclick = "ck(\'' + data.id + '\');">查看</span><span class="edit-icon" onclick = "modifyGly(\'' + data.id + '\');">编辑</span><span class="run-icon" onclick = "qy(\'' + data.id + '\');">启用</span><span class="delete-icon" onclick = "deleteGly(\'' + data.id + '\');">删除</span><span class="xgmm-icon" onclick = "modifymm(\'' + data.id + '\');">重置密码</span>'
					}
					if(sessionStorage.getItem(id) == Constants.czjb_kd) {
						str = '<span class="look-icon" onclick = "ck(\'' + data.id + '\');">查看</span>';
					}
					return str;
				}
			}
		}],
		pagination: { // 设置是否显示分页，默认显示分页部分。  show:true 显示分页， show:false 不显示分页
			show: true,
			pageSize: 10 // 可选，默认10条
		}
	});
}



