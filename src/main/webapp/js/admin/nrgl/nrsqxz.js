var sqdx_ractive = new Ractive({
	el: '#sqyhlb',
	template: '#sqdxrlb',
	oninit: function() {
		var param = {}
		this.loadSqData(param);
	},
	loadSqData: function(param) {
		var _this = this;
		$.sdAjax({
			url: Constants.ctrlAddress + 'nrsq/selectSqrList',
			type: 'POST',
			data: param,
			async: false, // 是否异步
			waitFlag: true, // 是否需要加载等待动画
			successCallback: function(data) {
				_this.set("sqdxList", data.data);
			}
		})
	},
	oncomplete: function() {

	}
})

var sqnr_ractive = new Ractive({
	el: '.sqdxnrlb',
	template: '#sqdxnrlb',
	oninit: function() {},
	loadSqnrData: function(param,init) {
		var _this = this;
		$.sdAjax({
			url: Constants.ctrlAddress + 'nrsq/selectNrxxList',
			type: 'POST',
			data: param,
			async: false, // 是否异步
			waitFlag: true, // 是否需要加载等待动画
			successCallback: function(data) {
				_this.set("sqnrList", data.data);
				if(init){
					loadSelect();
				}
			}
		})
	},
	oncomplete: function() {
		$.sdCheckAll({
			pName: 'nrlbxxall',
			cName: 'nrlbxx'
		})
		this.on({
			check: function() {
				// 监听checkbox下的每一项change事件
				//$('input[name=nrlbxx]:checkbox').change(function() {

					var selectAllCB = false;
					// 如果选中的子选项与子选项个数一致，则应选中全选
					if($('input[name=nrlbxx]:checkbox').size() == $('input[name=nrlbxx]:checkbox:checked').size()) {
						selectAllCB = true;
					}

					$('input[name=nrlbxxall]:checkbox').prop('checked', selectAllCB);

				//});
			}
		})

	}
})

function loadSelect() {
	var ractive = new Ractive({
		el: '#cx',
		template: '#first-select',
		oninit: function() {
			this.loadData();
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
			/*var zkztlist = new Array();
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
						ractive.set("zkztlist", zkztlist);
					}
				}
			});*/

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
	// 初始化select控件
	$.sdSelect({ ractive: ractive });
}

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
function sqrcxbtn() {
	var param = {
		zh: $("#cx_dxzh").val().trim(),
		yhmc: $("#cx_dxmc").val().trim()
	}
	sqdx_ractive.loadSqData(param);
}

//下一步
function xyb() {
	if($("input[type='radio']:checked").attr("id") == undefined) {
		alert("请选择授权人再进行下一步", null, false);
		return;
	}
	$("#sqdxym").hide();
	$("#sqnrym").show();
	var sqdxObj = {
		jgId:$("input[type='radio']:checked").attr("jgId"),
		jgmc:$("input[type='radio']:checked").attr("ssjgmc"),
		sqdxmc: $("input[type='radio']:checked").attr("sqdxmc"),
		sqdxzh: $("input[type='radio']:checked").attr("zh")
	}
	store.set("sqdxObj", sqdxObj);
	var param = {
		zh: $("input[type='radio']:checked").attr("zh")
	}
	sqnr_ractive.loadSqnrData(param,true);
}

//内容查询
function nrcxbtn() {

	var param = {
		zh: store.get("sqdxObj").sqdxzh,
		nrbt: $("#cx_nrbt").val().trim(),
		flId: $("#xlcx").val()
	}
	sqnr_ractive.loadSqnrData(param,false);
}

//确认授权
function qrsq() {
	var sqdxObj = store.get("sqdxObj");
	var param = {

	}
	var sqList = new Array();
	if($("input[name='nrlbxx']:checked").length == 0) {
		alert("请选择至少一条内容");
	} else {
		confirm("确定授权吗？", function() {
			$("input[name='nrlbxx']:checked").each(function(index, value) {
				var sqxx = {
					nrbh: value.id,
					nrbb: value.getAttribute("nrbb"),
					ywbh: value.getAttribute("ywbh"),
					sqdxzh: sqdxObj.sqdxzh,
					sqdxmc: sqdxObj.sqdxmc,
					jgId:sqdxObj.jgId,
					jgmc:sqdxObj.jgmc
				}
				sqList.push(sqxx);
			})
			param.sqList = sqList;
			//要授权用户的机构编号
			param.yhjgId=sqdxObj.jgId;
			$.sdAjax({
				url: Constants.ctrlAddress + 'nrsq/addNrsq',
				type: 'POST',
				data: param,
				async: false, // 是否异步
				waitFlag: true, // 是否需要加载等待动画
				successCallback: function(data) {
					if(data && data.result) {
						success("授权成功",function(){
							closeParent();
						},true);
					}
				}
			})
		})
	}
}