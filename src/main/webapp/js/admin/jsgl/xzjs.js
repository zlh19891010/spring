var param = {};
var jsgsParam;
var ractive;
$(function() {
		ractive = new Ractive({
			el: '#jsjbtem',
			template: '#menu-template',
			onrender: function() {},
			oncomplete: function() {
				$(".hp-glpt-xzgly").removeClass("none");
				var jsjbGlpt = new Array();
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
							//加载上级角色
							jsgsParam = $("input[name='glyy']:checked")[0].getAttribute("msgType");
							$.ajax({
								url: Constants.ctrlAddress + "getAllxjjsList?jsgs=" + jsgsParam,
								type: "get",
								dataType: "json",
								contentType: "application/json",
								success: function(obj) {
									if(typeof obj == "object") {
										for(var i = 0; i < obj.length; i++) {
											jsjbGlpt.push({
												"code": obj[i].id,
												"name": obj[i].jsmc,
												"jsjb": obj[i].jsjb
											});
										}
										ractive.set("jsjbList", jsjbGlpt);

									}

								}
							});
							//radio改变事件按钮绑定
							//角色归属radio
							$("input[name='glyy']").change(function() {

								var jsgs = $("input[name='glyy']:checked").val();
								jsgsParam = $("input[name='glyy']:checked")[0].getAttribute("msgType");
								var jsjbGlpt = new Array();
								$.ajax({
									url: Constants.ctrlAddress + "getAllxjjsList?jsgs=" + jsgsParam,
									type: "get",
									dataType: "json",
									contentType: "application/json",
									success: function(obj) {
										if(typeof obj == "object") {
											for(var i = 0; i < obj.length; i++) {
												jsjbGlpt.push({
													"code": obj[i].id,
													"name": obj[i].jsmc,
													"jsjb": obj[i].jsjb
												});
											}
											ractive.set("jsjbList", jsjbGlpt);

										}

									}
								});

							})
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
			var jsjblx = ractive.get("sjjs");
			if(jsjblx.trim() == "") {
				alert("请选择可分配的角色!");
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
				//jsgsParam = $(".glptcs:checked")[0].getAttribute("msgType");
				param.jsgs = jsgsParam;
				//校验角色名称重复
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
								return;
							} else {
								qxfp(param);
							}

						}

					}
				});

			},
			rules: { // 此属性用于设置校验规则，可参照上面的规则设置
				jsmc: {
					required: true
				},
				jsms: {
					required: true,
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
	//从主方法抽离
function qxfp(param) {
	if($.fn.zTree.getZTreeObj("righttree") == null || $.fn.zTree.getZTreeObj("righttree").getNodes().length == 0) {
		alert("请分配权限！");
		return;
	}
	var rightNodes = $.fn.zTree.getZTreeObj("righttree").getNodes();
	var ids = new Array();
	for(var i = 0; i < rightNodes.length; i++) {
		if(rightNodes[i].pId == null) {
			if(rightNodes[i].children == null || rightNodes[i].children == "undefined" || rightNodes[i].children.length == 0) {
				if(rightNodes[i].dxflag == false) {
					//设置可读权限
					rightNodes[i].czjb = "01";
				}
				var temp = {
					"qxCode": rightNodes[i].id,
					"czjb": rightNodes[i].czjb
				};
				ids.push(temp);
			} else {
				for(var j = 0; j < rightNodes[i].children.length; j++) {
					if(rightNodes[i].children[j].dxflag == false) {
						//设置可读权限
						rightNodes[i].children[j].czjb = "01";
					}
					var tempc = {
						"qxCode": rightNodes[i].children[j].id,
						"czjb": rightNodes[i].children[j].czjb
					};
					ids.push(tempc);
				}
			}
		} else {
			if(checkedNodes[i].dxflag == false) {
				//设置可读权限
				rightNodes[i].czjb = "01";
			}
			var tempt = {
				"qxCode": checkedNodes[i].id,
				"czjb": checkedNodes[i].czjb
			};
			ids.push(tempt);
		}

	}
	param.yfpqxList = ids;
	//可分配的角色，上级角色
	param.sjjsId = ractive.get("sjjs");
	var currjsjb = $("#"+param.sjjsId).attr("jsjb");
	param.jsjb = parseInt(currjsjb) + 1;
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