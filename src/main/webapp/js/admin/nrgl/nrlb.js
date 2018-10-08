var zkzt_ractive = new Ractive({
	el: '#cxzkzt',
	template: '#zkzt-select',
	oninit: function() {
		this.loadZkztData();
	},
	loadZkztData: function() {
		var _this = this;
		var zkztlist = new Array();
		$.sdAjax({
			url: Constants.ctrlAddress + "getCodeListByCodeclass?codeclass=zkzt",
			type: 'GET',
			async: false, // 是否异步
			waitFlag: true, // 是否需要加载等待动画
			successCallback: function(obj) {
				if(typeof obj == "object") {
					for(var i = 0; i < obj.length; i++) {
						zkztlist.push({
							"code": obj[i].code,
							"name": obj[i].codedesc
						});
					}
					_this.set("zkztlist", zkztlist);
				}
			}
		});
	},
	oncomplete: function() {}
})

var ractive = new Ractive({
	el: '#cx',
	template: '#first-select',
	oninit: function() {
		this.loadData();
		var param = {}
		this.loadYtjData(param);
		this.loadDtjData(param);
	},
	loadData: function() {
		var _this = this;
		$.sdAjax({
			url: Constants.ctrlAddress + 'flgl/getFlxxList',
			type: 'GET',
			async: false, // 是否异步
			waitFlag: true, // 是否需要加载等待动画
			successCallback: function(data) {
				if(data && data.result) {
					_this.set("list", data.data);
					_this.set("mc", Constants.flxx[1]);
				}
			}
		});

	},
	loadDtjData: function(param) {
		/*var param={
			nrzt:Constants.nrztCG
		}*/
		param.nrzt = Constants.nrztCG;
		$('.dtjlb').sdGrid({
			url: Constants.ctrlAddress + 'nrgl/selectNrxxList',
			type: 'POST',
			async: true,
			waitFlag: true, // true：加载数据时显示等待动画， false：不显示加载动画。  默认显示加载动画
			data: param,
			headBtns: { // headBtns非必须，如果列表头部没有按钮，可以不设置此项
				//用户通过下面的调整，自定义列表头部按钮样式。
				innerHtml: function() {
					return '';
				}
			},
			callback: function() {
				// 数据加载完成后，希望执行的操作

				$('tr').hover(function() {
					$(this).find('.upAndDown').show();
				}, function() {
					$(this).find('.upAndDown').hide();
				});
			},
			colProperty: [{
					colLabel: '内容编号',
					style: {
						textAlign: 'center',
						width: '200px' // 指定列宽，不指定时，采用默认情况
					},
					colModel: {
						style: {
							textAlign: 'center',
							width: '200px',
							ellipsis: true
						},
						innerHtml: function(data) {
							return '<span title="' + data.id + '">' + data.id + '</span>';
						}
					}
				},
				{
					colLabel: '内容标题',
					style: {
						textAlign: 'center',
						width: '200px' // 指定列宽，不指定时，采用默认情况
					},
					colModel: {
						style: {
							textAlign: 'center',
							width: '200px',// 指定列宽，不指定时，采用默认情况
							ellipsis: true
						},
						innerHtml: function(data) {
							return '<span title="' + data.nrbt + '">' + data.nrbt + '</span>';
						}
					}
				},
				{
					colLabel: '内容级别',
					style: {
						textAlign: 'center', // 指定文本对齐方式
						width: '200px' // 指定列宽，不指定时，采用默认情况
					},
					colModel: {
						style: {
							textAlign: 'center',
							width: '200px',
							ellipsis: true
						},
						innerHtml: function(data) {
							return '<span title="' + data.flmc + '">' + data.flmc + '</span>';
						}
					}
				},
				{
					colLabel: '附件总数',
					style: {
						textAlign: 'center' // 指定文本对齐方式
					},
					colModel: {
						style: {
							textAlign: 'center',
							ellipsis: true
						},
						innerHtml: function(data) {
							return '<span title="' + data.fjzs + '">' + data.fjzs + '</span>';
						}
					}
				},{
					colLabel: '创建人',
					style: {
						textAlign: 'center', // 指定文本对齐方式
						width: '200px' // 指定列宽，不指定时，采用默认情况
					},
					colModel: {
						style: {
							textAlign: 'center',
							width: '200px',
							ellipsis: true
						},
						innerHtml: function(data) {
							return '<span title="' + data.createrCode + '">' + data.createrCode + '</span>';
						}
					}
				},				
				{
					colLabel: '创建时间',
					style: {
						textAlign: 'center', // 指定文本对齐方式
						width: '130px' // 指定列宽，不指定时，采用默认情况
					},
					colModel: {
						style: {
							textAlign: 'center', // 指定文本对齐方式
							ellipsis: true
						},
						innerHtml: function(data) {
							return '<span title="' + new Date(data.createTime).format("yyyy-MM-dd hh:mm:ss") + '">' + new Date(data.createTime).format("yyyy-MM-dd hh:mm:ss") + '</span>';
						}
					}
				},
				{
					colLabel: '操作',
					style: {
						textAlign: 'center', // 指定文本对齐方式
					},
					colModel: {
						style: {
							textAlign: 'center'
						},
						innerHtml: function(data) {
							var str = '<a href="javascript:void(0)"  onclick="bjan(\'' + data.id + '\')" class="edit-icon">编辑</a>';
							str += '<a href="javascript:void(0)"  onclick="scan(\'' + data.id + '\')" class="delete-icon">删除</a>';
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
	},
	loadYtjData: function(param) {
		/*var param={
			nrzt:Constants.nrztZS
		}*/
		param.nrzt = Constants.nrztZS;
		$('.ytjlb').sdGrid({
			url: Constants.ctrlAddress + 'nrgl/selectNrxxList',
			type: 'POST',
			async: true,
			waitFlag: true, // true：加载数据时显示等待动画， false：不显示加载动画。  默认显示加载动画
			data: param,
			headBtns: { // headBtns非必须，如果列表头部没有按钮，可以不设置此项
				//用户通过下面的调整，自定义列表头部按钮样式。
				innerHtml: function() {
					return '';
				}
			},
			callback: function() {
				// 数据加载完成后，希望执行的操作
				$.sdCheckAll({
					pName: 'bklball',
					cName: 'bklb'
				})
				$('tr').hover(function() {
					$(this).find('.upAndDown').show();
				}, function() {
					$(this).find('.upAndDown').hide();
				});
			},
			colProperty: [{
					colLabel: '<input type="checkbox" class="sdui-checkbox" name="bklball" id="selectall"><label for="selectall"></label>',
					style: {
						textAlign: 'center',
						width:'60px'
					},
					colModel: {
						style: {
							textAlign: 'center'
						},
						innerHtml: function(data) {
							return '<input type="checkbox" class="sdui-checkbox" name="bklb" id=' + data.id + '><label for=' + data.id + '></label>'
							//return '<input type="checkbox" class="sdui-checkbox ischeckAll" name="jfsz" id="ck-' + data.id + '" data-id="' + data.id + '" ' + (data.isChecked == true ? 'checked' : '') + ' ><label  for="ck-' + data.id + '">';
						}
					}
				},
				{
					colLabel: '内容编号',
					style: {
						textAlign: 'center',
						width: '160px' // 指定列宽，不指定时，采用默认情况
					},
					colModel: {
						style: {
							textAlign: 'center',
							ellipsis: true
						},
						innerHtml: function(data) {
							return '<span title="' + data.id + '">' + data.id + '</span>';
						}
					}
				},

				{
					colLabel: '内容标题',
					style: {
						textAlign: 'center',
						width: '210px' // 指定列宽，不指定时，采用默认情况
					},
					colModel: {
						style: {
							textAlign: 'center',
							ellipsis: true
						},
						innerHtml: function(data) {
							return '<span title="' + data.nrbt + '">' + data.nrbt + '</span>';
						}
					}
				},
				{
					colLabel: '内容级别',
					style: {
						textAlign: 'center', // 指定文本对齐方式
						width: '150px' // 指定列宽，不指定时，采用默认情况
					},
					colModel: {
						style: {
							textAlign: 'center',
							ellipsis: true
						},
						innerHtml: function(data) {
							return '<span title="' + data.flmc + '">' + data.flmc + '</span>';
						}
					}
				},
				{
					colLabel: '在库状态',
					style: {
						textAlign: 'center', // 指定文本对齐方式
						width:'80px'
					},
					colModel: {
						style: {
							textAlign: 'center',
							ellipsis: true
						},
						innerHtml: function(data) {
							return '<span title="' + data.zkztmc + '">' + data.zkztmc + '</span>';
						}
					}
				},
				{
					colLabel: '附件总数',
					style: {
						textAlign: 'center', // 指定文本对齐方式
						width: '80px'
					},
					colModel: {
						style: {
							textAlign: 'center',
							ellipsis: true
						},
						innerHtml: function(data) {
							return '<span title="' + data.fjzs + '">' + data.fjzs + '</span>';
						}
					}
				},
				{
					colLabel: '内容版本',
					style: {
						textAlign: 'center', // 指定文本对齐方式
						width: '80px'
					},
					colModel: {
						style: {
							textAlign: 'center',
							ellipsis: true
						},
						innerHtml: function(data) {
							return '<span title="' + data.nrbb + '">' + data.nrbb + '</span>';
						}
					}
				}, {
					colLabel: '创建人',
					style: {
						textAlign: 'center', // 指定文本对齐方式
						width: '200px' // 指定列宽，不指定时，采用默认情况
					},
					colModel: {
						style: {
							textAlign: 'center',
							ellipsis: true
						},
						innerHtml: function(data) {
							return '<span title="' + data.createrCode + '">' + data.createrCode + '</span>';
						}
					}
				},
				{
					colLabel: '创建时间',
					style: {
						textAlign: 'center', // 指定文本对齐方式
						width: '180px', // 指定列宽，不指定时，采用默认情况
					},
					colModel: {
						style: {
							textAlign: 'center', // 指定文本对齐方式
						},
						innerHtml: function(data) {
							return '<span title="' + new Date(data.createTime).format("yyyy-MM-dd hh:mm:ss") + '">' + new Date(data.createTime).format("yyyy-MM-dd hh:mm:ss") + '</span>';
						}
					}
				},
				{
					colLabel: '操作',
					style: {
						textAlign: 'center', // 指定文本对齐方式
						width: '250px'
					},
					colModel: {
						style: {
							textAlign: 'center'
						},
						innerHtml: function(data) {
							var str = '<a href="javascript:void(0)"  onclick="cxan(\'' + data.id + '\')" class="look-icon">查看</a>';
							if(data.zkzt != Constants.zkztSj) {
								str += '<a href="javascript:void(0)"  onclick="bjan(\'' + data.id + '\',' + data.zkzt + ')" class="edit-icon">编辑</a>';
							}

							if(data.zkzt == Constants.zkztIng || data.zkzt == Constants.zkztXj) {
								str += '<a href="javascript:void(0)"  onclick="sjan(\'' + data.id + '\')" class="sj-icon">上架</a>';
							}
							if(data.zkzt == Constants.zkztSj) {
								str += '<a href="javascript:void(0)"  onclick="xjan(\'' + data.id + '\')" class="xj-icon">下架</a>';
							}
							if(data.zkzt != Constants.zkztSj) {
								str += '<a href="javascript:void(0)"  onclick="ytjscan(\'' + data.id + '\',' + data.zkzt + ')" class="delete-icon">删除</a>';
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
	},
	oncomplete: function() {

		this.on({
			firstChange: function(event) {
				$("#xlcx").val('');
				$("#first").nextAll().remove();
				if(ractive.get("first") == "" || ractive.get("first") == null) {
					return;
				}
				$.sdAjax({
					url: Constants.ctrlAddress + 'flgl/getFlxxList?parentId=' + ractive.get("first"),
					type: 'GET',
					async: false, // 是否异步
					waitFlag: true, // 是否需要加载等待动画
					successCallback: function(data) {
						if(data && data.result && data.data) {
							var str = '<div id="' + data.data[0].fljb + '" class="hp-nrgl-select">';
							str += '<label class="sdui-label">' + Constants.flxx[parseInt(data.data[0].fljb)] + '</label>';
							str += '<select class="sdui-select" value=""  onchange="flChange(this)">';
							str += '<option value=""></option>';
							for(var i = 0; i < data.data.length; i++) {
								str += '<option value="' + data.data[i].id + '">' + data.data[i].flmc + '</option>';
							}
							str += '</select></div>';
							$("#cx").append(str);
						}
					}
				})
			}
		})
	}
});
$(function() {
	// 初始化select控件
	$.sdSelect({ ractive: ractive });

    if($.getUrlParam("yqbs")=="dtj"){
    	dtj();
    }else{
    	ytj();
    }
	$('.sdui-btn').click(function() {
	});
});

function flChange(obj) {
	$("#xlcx").val('');
	$("#" + $(obj).parent().attr("id")).nextAll().remove();
	if(obj.value == "" || obj.value == null) {
		return;
	}
	$.sdAjax({
		url: Constants.ctrlAddress + 'flgl/getFlxxList?parentId=' + obj.value,
		type: 'GET',
		async: false, // 是否异步
		waitFlag: true, // 是否需要加载等待动画
		successCallback: function(data) {
			if(data && data.result && data.data.length > 0) {
				var str = '<div id="' + data.data[0].fljb + '" class="hp-nrgl-select">';
				str += '<label class="sdui-label">' + Constants.flxx[parseInt(data.data[0].fljb)] + '</label>';
				str += '<select class="sdui-select" value=""  onchange="flChange(this)">';
				str += '<option value=""></option>';
				for(var i = 0; i < data.data.length; i++) {
					str += '<option value="' + data.data[i].id + '">' + data.data[i].flmc + '</option>';
				}
				str += '</select></div>';
				$("#cx").append(str);
			} else {
				$("#xlcx").val(obj.value);
			}
		}
	})
}

//查询条件按钮
function cxbtn() {
	/*if($("#xlcx").val()==""||$("#xlcx").val()==null){
		alert("必须选择");
		return;
	}*/
	var param = {
		nrbt: $("#cx_nrbt").val().trim(),
		flId: $("#xlcx").val(),
	}
	if($(".ytjlb").is(":hidden")) {
		ractive.loadDtjData(param);
	}
	if($(".dtjlb").is(":hidden")) {
		param.zkzt = $("#zkztValue").val();
		ractive.loadYtjData(param);
	}
}

//页签的事件
function ytj() {
	$("#ytj").addClass("active");
	$("#dtj").removeClass("active");
	$(".ytjlb").show();
	$("#cxzkzt").show();
	$("#sjan").show();
	$("#xjan").show();
	$(".dtjlb").hide();
	//页面标识  true 已提交页签
	store.set("yqbs", true);
}

function dtj() {
	$("#ytj").removeClass("active");
	$("#dtj").addClass("active");
	$(".ytjlb").hide();
	$("#cxzkzt").hide();
	$("#sjan").hide();
	$("#xjan").hide();
	$(".dtjlb").show();
	//页面标识  true 待提交页签
	store.set("yqbs", false);
}

//上架 
function sjan(id) {
	var ids = new Array();
	ids.push(id);
	confirm("确定上架吗？", function() {
		$.sdAjax({
			url: Constants.ctrlAddress + 'nrgl/updateBatchSjNrxx',
			type: 'POST',
			data: ids,
			async: false, // 是否异步
			waitFlag: true, // 是否需要加载等待动画
			successCallback: function(data) {
				if(data && data.result) {
					var param = {}
					ractive.loadYtjData(param);
				}
			}
		})
	})
}
//下架
function xjan(id) {
	var ids = new Array();
	ids.push(id);
	confirm("确定下架吗？", function() {
		$.sdAjax({
			url: Constants.ctrlAddress + 'nrgl/updateBatchXjNrxx',
			type: 'POST',
			data: ids,
			async: false, // 是否异步
			waitFlag: true, // 是否需要加载等待动画
			successCallback: function(data) {
				if(data && data.result) {
					var param = {}
					ractive.loadYtjData(param);
				}
			}
		})
	})
}

//批量上架
function plsj() {
	var ids = new Array();
	if($("input[name='bklb']:checked").length == 0) {
		alert("请选择至少一条记录");
	} else {
		confirm("确定上架吗？", function() {
			$("input[name='bklb']:checked").each(function(index, value) {
				ids.push(value.id);
			})
			$.sdAjax({
				url: Constants.ctrlAddress + 'nrgl/updateBatchSjNrxx',
				type: 'POST',
				data: ids,
				async: false, // 是否异步
				waitFlag: true, // 是否需要加载等待动画
				successCallback: function(data) {
					if(data && data.result) {
						var param = {}
						ractive.loadYtjData(param);
					}
				}
			})
		})
	}
}
//批量下架 
function plxj() {
	var ids = new Array();
	if($("input[name='bklb']:checked").length == 0) {
		alert("请选择至少一条记录");
	} else {
		confirm("确定下架吗？", function() {
			$("input[name='bklb']:checked").each(function(index, value) {
				ids.push(value.id);
			})
			$.sdAjax({
				url: Constants.ctrlAddress + 'nrgl/updateBatchXjNrxx',
				type: 'POST',
				data: ids,
				async: false, // 是否异步
				waitFlag: true, // 是否需要加载等待动画
				successCallback: function(data) {
					if(data && data.result) {
						var param = {}
						ractive.loadYtjData(param);
					}
				}
			})
		})
	}
}

//删除按钮
function ytjscan(id, zkzt) {
	if(Constants.zkztSj == zkzt) {
		alert("上架状态中，不允许删除", null, false);
		return;
	}
	var ids = new Array();
	ids.push(id);
	confirm("确定删除吗？", function() {
		$.sdAjax({
			url: Constants.ctrlAddress + 'nrgl/deleteBatchNrxx',
			type: 'POST',
			data: ids,
			async: false, // 是否异步
			waitFlag: true, // 是否需要加载等待动画
			successCallback: function(data) {
				if(data && data.result) {
					success("删除成功",
						function() {
							var param = {}
							ractive.loadYtjData(param);
						}, true);
				}
			}
		})
	})
}

//已提交的删除按钮
function scan(id) {
	var ids = new Array();
	ids.push(id);
	confirm("确定删除吗？", function() {
		$.sdAjax({
			url: Constants.ctrlAddress + 'nrgl/deleteBatchNrxx',
			type: 'POST',
			data: ids,
			async: false, // 是否异步
			waitFlag: true, // 是否需要加载等待动画
			successCallback: function(data) {
				if(data && data.result) {
					success("删除成功",
						function() {
							var param = {}
							ractive.loadDtjData(param);
						}, true);
				}
			}
		})
	})
}

//创建内容
function addContent() {
	store.set("bjgn", '');
	location.href = "nrgl-xz.html";
}
//查看内容按钮
function cxan(id) {
	store.set("nrxxId", id);
	location.href = "nrgl-ck.html";
}

//编辑按钮
function bjan(id, zkzt) {
	if(Constants.zkztSj == zkzt) {
		alert("上架状态中，不允许编辑", null, false);
		return;
	}
	if(store.get("yqbs")){
		store.set("bjgn", Constants.nrztZS);
	}else{
		store.set("bjgn", Constants.nrztCG);
	}
	store.set("nrxxId", id);
	location.href = "nrgl-xz.html";
}