var param = {
	current: 1,
	size: 10,
	zdgly:1
}
var id = getQueryMbWeb("id");
var ractive = new Ractive({
	el: '#test',
	template: '#test-template',
	oninit: function(options) {
		var _this = this;
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
					ractive.set("yhztlist", yhztlist);
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
			},
			async: true, // 是否异步
			waitFlag: true, // 是否需要加载等待动画
			successCallback: function(data) {
				sessionStorage.setItem(id, data.object.czjb);
				yhxxList(param);
			}
		})
		_this.on("query", function(event) {
			param.loginId = ractive.get("yhmc"),
				param.yhzt = ractive.get("yhzt")
			yhxxList(param)
		})
	}
});

$(function() {
	// 初始化select控件
	$.sdSelect({
		ractive: ractive
	});
});
//页面列表初始化
function yhxxList(param) {
	$('#customtable').sdGrid({
		url: Constants.ctrlAddress + "getYhxxPagination",
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
				colLabel: '编号',
				style: {
					textAlign: 'center',
				},
				colModel: {
					style: {
						textAlign: 'center',
					},
					innerHtml: function(data) {
						return '<span title="' + data.id +
							'">' + data.id +
							'</span>';
					}
				}
			}, {
				colLabel: '用户名称',
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
					width: '100px'
				},
				colModel: {
					style: {
						textAlign: 'center',
						ellipsis: true
					},
					innerHtml: function(data) {
						var jsmc = data.jsmc == undefined ? "" : data.jsmc;
						return '<span title="' + jsmc +
							'">' + jsmc +
							'</span>';
					}
				}
			}, {
				colLabel: '用户状态',
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
				colLabel: '操作',
				style: {
					textAlign: 'center',
				},
				colModel: {
					style: {
						textAlign: 'left',
						ellipsis: true
					},
					innerHtml: function(data) {
						var str = "";
						if(data.czjb == Constants.czjb_kx || data.czjb == null) {
							if(data.yhzt == "01" || data.yhzt == "03") {
								str = '<span class="look-icon" id="' + data.id + '" onclick="ckyh(this.id)">查看</span><span class="run-icon" id="' + data.id + '" onclick="QyBotton(this.id)">启用</span> ' +
									'<span class="edit-icon" id="' + data.id + '" onclick="edits(this.id)">编辑</span>  <span class="delete-icon" id="' + data.id + '" onclick="removeItem(this.id)" >删除</span>' +
									'<span class="xgmm-icon" onclick = "modifymm(\'' + data.id + '\');">重置密码</span>';
							}
							if(data.yhzt == "02") {
								str = '<span class="look-icon" id="' + data.id + '" onclick="ckyh(this.id)">查看</span><span class="stop-icon" id="' + data.id + '" onclick="TyBotton(this.id)">停用</span><span class="xgmm-icon" onclick = "modifymm(\'' + data.id + '\');">重置密码</span>';
							}
						}
						if(sessionStorage.getItem(id) == Constants.czjb_kd) {
							str = '<span class="look-icon" id="' + data.id + '" onclick="ckyh(this.id)">查看</span>';
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
//启用按钮
function QyBotton(id) {
	var data = {
		id: id,
		yhzt: Constants.ztyqy
	}
	confirm("确定启用吗？", function() {
		$.sdAjax({
			url: Constants.ctrlAddress + "modifyYhxx",
			dataType: 'json',
			type: 'post',
			contentType: 'application/json',
			data: data,
			async: true, // 是否异步
			waitFlag: true, // 是否需要加载等待动画
			successCallback: function(data) {
				if(data && data.result) {
					success("启用成功");
					yhxxList(param);
				} else {
					message("启用失败");
				}

			}
		})
	})
}
//停用按钮
function TyBotton(id) {
	var data = {
		id: id,
		yhzt: Constants.ztyty
	}
	confirm("确定停用吗？", function() {
		$.sdAjax({
			url: Constants.ctrlAddress + "modifyYhxx",
			dataType: 'json',
			type: 'post',
			contentType: 'application/json',
			data: data,
			async: true, // 是否异步
			waitFlag: true, // 是否需要加载等待动画
			successCallback: function(data) {
				if(data && data.result) {
					success("停用成功");
					yhxxList(param);
				} else {
					success("停用失败");
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