var ractive = new Ractive({
	el: '#cx',
	template: '#first-select',
	oninit: function() {
		this.loadData();
		var param = {}
		this.loadSqData(param);
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
	loadSqData: function(param) {
		$('.sqlb').sdGrid({
			url: Constants.ctrlAddress + 'nrsq/selectNrsqList',
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
			
			colProperty: [
				{
					colLabel: '内容编号',
					style: {
						textAlign: 'center',
						width: '180px'
					},
					colModel: {
						style: {
							textAlign: 'center',
							ellipsis: true
						},
						innerHtml: function(data) {
							return '<span title="' + data.nrbh + '">' + data.nrbh + '</span>';
						}
					}
				},

				{
					colLabel: '内容标题',
					style: {
						textAlign: 'center',
						width: '200px'
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
						width: '150px'
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
					colLabel: '授权对象名称',
					style: {
						textAlign: 'center', // 指定文本对齐方式
						width: '150px'
					},
					colModel: {
						style: {
							textAlign: 'center',
							ellipsis: true
						},
						innerHtml: function(data) {
							return '<span title="' + data.sqdxmc + '">' + data.sqdxmc + '</span>';
						}
					}
				},
				{
					colLabel: '授权对象帐号',
					style: {
						textAlign: 'center', // 指定文本对齐方式
						width: '150px'
					},
					colModel: {
						style: {
							textAlign: 'center',
							ellipsis: true
						},
						innerHtml: function(data) {
							return '<span title="' + data.sqdxzh + '">' + data.sqdxzh + '</span>';
						}
					}
				}, {
					colLabel: '授权时间',
					style: {
						textAlign: 'center', // 指定文本对齐方式
						width: '140px'
					},
					colModel: {
						style: {
							textAlign: 'center' // 指定文本对齐方式
						},
						innerHtml: function(data) {
							return '<span title="' + new Date(data.sqsj).format("yyyy-MM-dd hh:mm:ss") + '">' + new Date(data.sqsj).format("yyyy-MM-dd hh:mm:ss") + '</span>';
						}
					}
				},
				{
					colLabel: '操作',
					style: {
						textAlign: 'center', // 指定文本对齐方式
						width: '300px'
					},
					colModel: {
						style: {
							textAlign: 'center'
						},
						innerHtml: function(data) {
							var str = '<a href="javascript:void(0)"  onclick="cxan(\'' + data.nrbh + '\')" class="look-icon">查看内容</a>';
							
							    str += '<a href="javascript:void(0)"  onclick="scan(\'' + data.id + '\')" class="delete-icon">取消授权</a>';
							

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

		$("#tab-click02").sdTab();
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
	var param = {
		nrbt: $("#cx_nrbt").val().trim(),
		flId: $("#xlcx").val(),
		sqdxzh:$("#cx_dxzh").val().trim(),
		sqdxmc:$("#cx_dxmc").val().trim()
	}
		ractive.loadSqData(param);
}

//删除按钮
function scan(id) {
	var ids = new Array();
	ids.push(id);
	confirm("确定取消授权吗？", function() {
		$.sdAjax({
			url: Constants.ctrlAddress + 'nrsq/deleteNrsq',
			type: 'POST',
			data: ids,
			async: false, // 是否异步
			waitFlag: true, // 是否需要加载等待动画
			successCallback: function(data) {
				if(data && data.result) {
					success("取消授权成功",
					function(){
						var param = {};
					ractive.loadSqData(param);
					},true);
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

function addNrsq(){
	var opts = {
		content: 'nrgl/nrsq-xz.html',
		scroll: false,
		title: '',
		type: 2,
		width: '800px',
		height: '600px',
		shadeClose: false,
		end: function() {
			var param = {};
			ractive.loadSqData(param);
		}
	}
	dialog(opts);
}
