var ractive = new Ractive({
	el: '#test',
	template: '#test-template',
	oninit: function(options) {
		var _this = this;
		//角色归属
		var jsgsList = new Array();
		$.sdAjax({
			url: Constants.ctrlAddress + "getCodeListByCodeclass?codeclass=jsgs",
			type: "get",
			dataType: "json",
			contentType: "application/json",
			waitFlag: true, // 是否需要加载等待动画
			successCallback: function(obj) {
				if(typeof obj == "object") {
					for(var i = 0; i < obj.length; i++) {
						jsgsList.push({
							"code": obj[i].code,
							"name": obj[i].codedesc
						});
					}
					ractive.set("jsgsList", jsgsList);
				}

			}
		});
		//操作类型
		var czlxList = new Array();
		$.sdAjax({
			url: Constants.ctrlAddress + "getCodeListByCodeclass?codeclass=czlx",
			type: "get",
			dataType: "json",
			contentType: "application/json",
			waitFlag: true, // 是否需要加载等待动画
			successCallback: function(obj) {
				if(typeof obj == "object") {
					for(var i = 0; i < obj.length; i++) {
						czlxList.push({
							"code": obj[i].code,
							"name": obj[i].codedesc
						});
					}
					ractive.set("czlxList", czlxList);
				}

			}
		});
		_this.on("query", function(event) {
			rzxxxList(_this.get("log"))
		})
	},
	oncomplete: function() {
		var param = {};
		rzxxxList(param);
	}
});
$(function() {
	// 初始化select控件
	$.sdSelect({
		ractive: ractive
	});
});
//页面列表初始化
function rzxxxList(param) {
	$('#customtable').sdGrid({
		url: Constants.ctrlAddress + "getRzxxlist",
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
				colLabel: '序号',
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
				colLabel: '日志归属',
				style: {
					textAlign: 'center'
				},
				colModel: {
					style: {
						textAlign: 'center',
						ellipsis: true
					},
					innerHtml: function(data) {
						var ywbh = "";
						if(data.ywbh == Constants.jsgspt) {
							ywbh = "管理平台"
						}
						if(data.ywbh == Constants.jsgsyy) {
							ywbh = "运营中心"
						}
						return '<span title="' + ywbh +
							'">' + ywbh +
							'</span>';
					}
				}
			}, {
				colLabel: '业务名称',
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
						return '<span title="' + data.ywmc +
							'">' + data.ywmc +
							'</span>';
					}
				}
			}, {
				colLabel: '操作类型',
				style: {
					textAlign: 'center'
				},
				colModel: {
					style: {
						textAlign: 'center',
					},
					innerHtml: function(data) {
						var ywb = "";
						if(data.ywb == Constants.czlxAdd) {
							ywb = "新增"
						}
						if(data.ywb == Constants.czlxDel) {
							ywb = "删除"
						}
						if(data.ywb == Constants.czlxUpdate) {
							ywb = "更新"
						}
						if(data.ywb == Constants.czlxQuery) {
							ywb = "查看"
						}
						if(data.ywb == Constants.czlxLogin) {
							ywb = "登录"
						}
						if(data.ywb == Constants.czlxXy) {
							ywb = "协议"
						}
						return '<span title="' + ywb +
							'">' + ywb +
							'</span>';
					}
				}
			}, {
				colLabel: '业务描述',
				style: {
					textAlign: 'center',
					width: '150px',
				},
				colModel: {
					style: {
						textAlign: 'center',
						ellipsis: true
					},
					innerHtml: function(data) {
						var rzms = data.rzms == undefined ? "" : data.rzms;
						return '<span title="' + rzms +
							'">' + rzms +
							'</span>';
					}
				}
			}, {
				colLabel: '操作人账号',
				style: {
					textAlign: 'center',
				},
				colModel: {
					style: {
						textAlign: 'center',
					},
					innerHtml: function(data) {
						return '<span title="' + data.operaterCode +
							'">' + data.operaterCode +
							'</span>';
					}
				}
			},
			{
				colLabel: '操作人名称',
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
						var yhmc = data.yhmc == undefined ? "" : data.yhmc;
						return '<span title="' + yhmc +
							'">' + yhmc +
							'</span>';
					}
				}
			},
			{
				colLabel: '操作时间',
				style: {
					textAlign: 'center',
				},
				colModel: {
					style: {
						textAlign: 'center',
					},
					innerHtml: function(data) {
						/*return '<span title="' + data.zcsj.substring(0, 10) +
							'">' + data.zcsj.substring(0, 10) +
							'</span>';*/
						return '<span title="' + data.zcsj +
						'">' + data.zcsj +
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