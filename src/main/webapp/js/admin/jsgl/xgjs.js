var param = {};
var jsgsParam;
var ractive;
//右侧树初始化数据
var jyids = [];
var initNodeCnt;
var qjjsmc;
// 从url中获取参数
function findParamFromUrl(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); // 构造一个含有目标参数的正则表达式对象
	var r = window.location.search.substr(1).match(reg); // 匹配目标参数
	if(r != null)
		return unescape(r[2]);
	return null; // 返回参数值
}

$(function() {
		ractive = new Ractive({
			el: '#jsjbtem',
			template: '#menu-template',
			onrender: function() {},
			oncomplete: function() {
				$(".hp-glpt-xzgly").removeClass("none");
				var id = findParamFromUrl("id");
				//加载角色归属
				$.sdAjax({
					url: Constants.ctrlAddress + "getCodeListByCodeclass?codeclass=jsgs",
					type: "get",
					dataType: "json",
					contentType: "application/json",
					waitFlag: true, // 是否需要加载等待动画
					successCallback: function(obj) {
						if(typeof obj == "object") {
							ractive.set("jsgsList", obj);
							//,加载后，加载基本信息
							//下拉框的处理
							var jsjbGlpt = new Array();
							$.sdAjax({
								url: Constants.ctrlAddress + "getJsqlJsxxById?id=" + id,
								type: "POST",
								dataType: "json",
								contentType: "application/json",
								data: param,
								waitFlag: true, // 是否需要加载等待动画
								successCallback: function(obj) {
									if(typeof obj == "object") {
										qjjsmc = obj.jsmc;
										$("#jsmc").val(obj.jsmc);
										$("#jsms").val(obj.jsms);
										$(".glptcs").each(function() {
												if(this.getAttribute("msgType") == obj.jsgs) {
													this.checked = true;
												} else {
													this.checked = false;
												}
											})
											//radio,禁用
										$("input[type='radio']").attr("disabled", "disabled");
										//设置上级可选角色，禁用
										jsgsParam = obj.jsgs;
										//设置上级角色
										ractive.set("jsjbList",{"code": obj.sjjsId,"name": obj.sjjsmc});

										ractive.set("kfpjsid", obj.sjjsId);
										ractive.set("jsmc", obj.sjjsmc);
										$(".sdui-select").prop("disabled", true);


									}
								}
							});

						}

					}
				});

			}
		});

		//左上角返回按钮
		$(".return").click(function() {
				window.location.href = "jsgl.html";
			})
			//新增页面取消按钮
		$(".hp-glpt-qxcz > .hp-glpt-cancel").click(function() {
			window.location.href = "jsgl.html";
		});

		//分配权限样式
		$(".hp-glpt-qxfp").click(function() {
			var jsjblx = ractive.get("kfpjsid");
			if(typeof(jsjblx) == "undefined" || jsjblx == "") {
				alert("请选择上级角色!");
				return;
			}
			$(".hp-black").removeClass("hide");
			$(".hp-show").removeClass("hide");
		});
		$(".hp-show-close").click(function() {
			$(".hp-black").addClass("hide");
			$(".hp-show").addClass("hide");
		});

		//校验框架
		$("#myform").sdValidate({
			messageShow: { // 此属性用于设置提示信息的显示，及显示风格
				show: true,
				type: 4
					// 1、正常显示、2、alert框方式显示 3、tip方式显示、4、吐司方式显示
			},
			focusout: true, // true或者不设置，则失去光标时，默认校验元素合法性；false：失去光标时，不校验合法性
			action: function() {
				param.jsmc = $("#jsmc").val().trim();
				param.jsms = $("#jsms").val().trim();
				param.jsgs = jsgsParam;
				//校验角色名称重复
				//名称没有变
				if(qjjsmc != param.jsmc) {
					$.sdAjax({
						url: Constants.ctrlAddress + "getJsxxByJsmc",
						type: "POST",
						dataType: "json",
						contentType: "application/json",
						data: {
							"jsmc": param.jsmc,
							"jsgs": param.jsgs
						},
						waitFlag: true, // 是否需要加载等待动画
						successCallback: function(obj) {
							if(typeof obj == "object") {
								if(obj.result == false) {
									alert("角色名称不可重复！");
								} else {
									fpqxgd(param);
								}
							}
						}
					});
				} else {
					fpqxgd(param);
				}
			},
			rules: { // 此属性用于设置校验规则，可参照上面的规则设置
				jsmc: {
					required: true
				},
				jsms: {
					required: true
				}

			},
			messages: { // 提示会有默认信息，如果你认为默认提示不好，或者不能满足业务提示需要，可以使用下面的属性覆盖
				jsmc: {
					required: '角色名称不能为空!'
				},
				jsms: {
					required: '角色描述不能为空!'
				}
			}
		});

		//点击保存按钮
		$(".hp-glpt-qxcz > .hp-glpt-save").click(function() {
			$("#myform").submit();
		});

	})
	//从主方法中抽离
function fpqxgd(param) {

	//分配权限，有改动
	var ids = new Array();
	if($.fn.zTree.getZTreeObj("righttree") != null) {
		var idsString = "";
		for(var j = 0; j < jyids.length; j++) {
			idsString += jyids[j] + ",";
		}
		var rightNodes = $.fn.zTree.getZTreeObj("righttree").getNodes();

		for(var i = 0; i < rightNodes.length; i++) {
			if(rightNodes[i].pId == null) {
				if(rightNodes[i].children == null || rightNodes[i].children == "undefined" || rightNodes[i].children.length == 0) {
					if(rightNodes[i].dxflag == false) {
						//设置可读权限
						rightNodes[i].czjb = "01";
					}
					if(idsString.indexOf(rightNodes[i].id) == -1) {
						var temp = {
							"qxCode": rightNodes[i].id,
							"czjb": rightNodes[i].czjb
						};
						ids.push(temp);
					}

				} else {
					for(var j = 0; j < rightNodes[i].children.length; j++) {
						//没有悬浮节点，or,没有点击节点
						if(rightNodes[i].children[j].dxflag == false) {
							//设置可读权限
							rightNodes[i].children[j].czjb = "01";
						}
						if(idsString.indexOf(rightNodes[i].children[j].id) == -1) {
							var tempc = {
								"qxCode": rightNodes[i].children[j].id,
								"czjb": rightNodes[i].children[j].czjb
							};
							ids.push(tempc);
						}

					}
				}
			} else {
				if(checkedNodes[i].dxflag == false) {
					//设置可读权限
					rightNodes[i].czjb = "01";
				}
				if(idsString.indexOf(checkedNodes[i].id) == -1) {
					var tempt = {
						"qxCode": checkedNodes[i].id,
						"czjb": checkedNodes[i].czjb
					};
					ids.push(tempt);
				}
			}
		}
	}
	param.yfpqxList = ids;
	param.sjjsId = ractive.get("kfpjsid");
	param.id = findParamFromUrl("id");
	//请求
	$.sdAjax({
		url: Constants.ctrlAddress + "insertOrUpdateJsxx",
		type: "POST",
		dataType: "json",
		contentType: "application/json",
		data: param,
		waitFlag: true, // 是否需要加载等待动画
		successCallback: function(obj) {
			if(typeof obj == "object") {
				if(obj.result) {
					success("保存成功!");
					window.location.href = "jsgl.html";
				} else {
					error("保存失败!");
				}

			}

		}
	});

}