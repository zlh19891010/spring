var param;
var flag = false;
$(function() {

	var ractive = new Ractive({
		el: '.top-area',
		template: '#main-template',
		onrender: function() {},
		oncomplete: function() {
			//01未启用，02已启用，03已停用
			var jsztlist = new Array();
			$.sdAjax({
				url: Constants.ctrlAddress + "getCodeListByCodeclass?codeclass=jszt",
				type: "get",
				dataType: "json",
				contentType: "application/json",
				waitFlag: true, // 是否需要加载等待动画
				successCallback: function(obj) {
					if(typeof obj == "object") {
						for(var i = 0; i < obj.length; i++) {
							jsztlist.push({
								"code": obj[i].code,
								"name": obj[i].codedesc
							});
						}
						ractive.set("jsztList", jsztlist);
					}

				}
			});

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
			// 初始化select控件
			$.sdSelect({
				ractive: ractive
			});

			//设置权限
			$.sdAjax({
				url: Constants.ctrlAddress + "getViewAllMenu?jsgs=01&jsId=",
				type: "get",
				dataType: "json",
				contentType: "application/json",
				waitFlag: true, // 是否需要加载等待动画
				successCallback: function(obj) {
					for(var i=0;i<obj.length;i++){
						if(obj[i].name=="角色权限管理"&&obj[i].czjb=="02"){
							flag=true;
							break;
						}
						if(obj[i].name=="角色权限管理"&&obj[i].czjb=="01"){
							flag=false;
							break;
						}
						
					}
					if(flag==false){
						$(".xzbtn").addClass("none");
					}else{
						$(".xzbtn").removeClass("none");
					}
//					if(str.indexOf("角色权限管理") != -1&&) {
//						flag=false;
//						//禁用新增按钮
//						$(".xzbtn").attr("href", "javascript:void(0);");
//					} else {
//						flag=true;
//					}

					//加载数据
					param = {
						current: 1,
						size: 10
					}
					loadTable(param, flag);
				}
			});

		}
	});
	ractive.on({
		search: function() {
			var jsmc = ractive.get("jsmc").trim();
			var jszt = ractive.get("jszt");
			var jsgs = ractive.get("jsgs");
			var data = {
				jsmc: jsmc,
				jszt: jszt,
				jsgs: jsgs
			};
			var obj = $.extend(param, data);
			loadTable(obj, flag);

		}

	})

});

//加载数据
function loadTable(param, flag) {
	$('#customtable').sdGrid({
		url: Constants.ctrlAddress + "getJsxxPagination",
		type: 'POST',
		data: param,
		async: true,
		waitFlag: true, // true：加载数据时显示等待动画， false：不显示加载动画。
		//						headBtns : { // headBtns非必须，如果列表头部没有按钮，可以不设置此项
		//							// 用户通过下面的调整，自定义列表头部按钮样式。
		//							innerHtml : function() {
		//								return '<a class="sdui-btn sdui-btn-tongguo" href="javascript:void(0);" onclick="passAll();">批量通过</a>';
		//							}
		//						},
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
						width: '230px',
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
				colLabel: '角色名称',
				style: {
					textAlign: 'center',
					width: '320px',
				},
				colModel: {
					style: {
						width: '320px',
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
				colLabel: '角色描述',
				style: {
					textAlign: 'center',
					width: '230px',
				},
				colModel: {
					style: {
						width: '230px',
						textAlign: 'center',
						ellipsis: true
					},
					innerHtml: function(data) {
						return '<span title="' + data.jsms +
							'">' + data.jsms +
							'</span>';
					}
				}
			}, /**
			{
				colLabel: '角色状态',
				style: {
					textAlign: 'center',
				},
				colModel: {
					style: {
						width: '180px',
						textAlign: 'center',
						ellipsis: true
					},
					innerHtml: function(data) {
						//01未启用，02已启用，03已停用
						if(data.jsztDesc) {
							return data.jsztDesc;
						}
					}
				}
			}, **/{
				colLabel: '角色归属',
				style: {
					textAlign: 'center',
				},
				colModel: {
					style: {
						textAlign: 'center',
						ellipsis: true
					},
					innerHtml: function(data) {
						return data.jsgsDesc;
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
						var str = "";
						//通用按钮
						if(data.bjBtn) {
							if(flag){
								str += '<span class="edit-icon" data-id="' + data.id + '"  onclick="edit(this)">编辑</span>';
							}else{
								str += '';
							}
							
						}
						if(data.scBtn) {
							if(flag){
								str += '<span class="delete-icon"  data-id="' + data.id + '" onclick="sc(this)">删除</span>';
							}else{
								str += '';
							}
							
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

//启用、停用、删除方法
function sc(obj) {
	var id = $(obj).attr("data-id");

	confirm("确认删除吗?", function() {
		$.sdAjax({
			url: Constants.ctrlAddress + "updateJsxx",
			type: "POST",
			dataType: "json",
			contentType: "application/json",
			data: {
				"id": id
			},
			waitFlag: true, // 是否需要加载等待动画
			successCallback: function(obj) {
				if(typeof obj == "object") {
					if(obj.result) {
						success("删除成功!");
						//重新加载列表
						loadTable(param, flag);

					} else {
						error("删除失败!");

					}
				}

			}
		});
	});

}

//修改方法
function edit(obj) {
	var id = $(obj)[0].getAttribute("data-id");
	window.location.href = "xgjs.html?id=" + id;

}