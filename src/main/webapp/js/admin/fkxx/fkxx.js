var ractive = new Ractive({
	el: '#test',
	template: '#test-template',
	oninit: function(options) {
		var _this = this;
		//角色归属
		var fkztList = new Array();
		$.sdAjax({
			url: Constants.ctrlAddress + "getCodeListByCodeclass?codeclass=fkzt",
			type: "get",
			dataType: "json",
			contentType: "application/json",
			waitFlag: true, // 是否需要加载等待动画
			successCallback: function(obj) {
				if(typeof obj == "object") {
					for(var i = 0; i < obj.length; i++) {
						fkztList.push({
							"code": obj[i].code,
							"name": obj[i].codedesc
						});
					}
					ractive.set("fkztList", fkztList);
				}

			}
		});
		//操作类型
		var fklxList = new Array();
		$.sdAjax({
			url: Constants.ctrlAddress + "getCodeListByCodeclass?codeclass=fklx",
			type: "get",
			dataType: "json",
			contentType: "application/json",
			waitFlag: true, // 是否需要加载等待动画
			successCallback: function(obj) {
				if(typeof obj == "object") {
					for(var i = 0; i < obj.length; i++) {
						fklxList.push({
							"code": obj[i].code,
							"name": obj[i].codedesc
						});
					}
					ractive.set("fklxList", fklxList);
				}
			}
		});
		_this.on("query", function(event) {
			rzxxxList(_this.get("fkxx"))
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
		url: Constants.ctrlAddress + "getFkxxPagination",
		type: 'POST',
		data: param,
		dataType: 'json',
		contentType: "application/json",
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
				colLabel: '内容标题',
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
						return '<span title="' + data.xxbt +
							'">' + data.xxbt +
							'</span>';
					}
				}
			}, {
				colLabel: '反馈人',
				style: {
					textAlign: 'center',
				},
				colModel: {
					style: {
						textAlign: 'center',
					},
					innerHtml: function(data) {
						return '<span title="' + data.fkrmc +
							'">' + data.fkrmc +
							'</span>';
					}
				}
			}, {
				colLabel: '反馈时间',
				style: {
					textAlign: 'center',
				},
				colModel: {
					style: {
						textAlign: 'center',
					},
					innerHtml: function(data) {
						var fksj = new Date(data.createTime).format("yyyy-MM-dd");
						return '<span title="' + fksj +
							'">' + fksj +
							'</span>';
					}
				}
			}, {
				colLabel: '反馈类型',
				style: {
					textAlign: 'center',
				},
				colModel: {
					style: {
						textAlign: 'center',
					},
					innerHtml: function(data) {
						var fklxDesc = data.fklxDesc == undefined ? "" : data.fklxDesc;
						return '<span title="' + fklxDesc +
							'">' + fklxDesc +
							'</span>';
					}
				}
			}, {
				colLabel: '反馈状态',
				style: {
					textAlign: 'center',
				},
				colModel: {
					style: {
						textAlign: 'center',
					},
					innerHtml: function(data) {
						return '<span title="' + data.hfztDesc +
							'">' + data.hfztDesc +
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
						textAlign: 'center',
						ellipsis: true
					},
					innerHtml: function(data) {
						if(data.fkzt == "00"){
							return '<span class="hf_icon" id="' + data.id + '" onclick="reply(this.id)">回复</span>';
						}else{
							return '<span class="look-icon" id="' + data.id + '" onclick="ckxx(this.id)">查看</span>';
						}
						
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
//回复跳转
function reply(id){
	window.location.href="xxfk-hf.html?id="+id;
}
//查看跳转
function ckxx(id){
	window.location.href="ckfkxx.html?id="+id;
}
