var jsjbqj;
var jsjb;
$(function() {
	//新增可分配权限按钮触发绑定
	$(".hp-glpt-qxfp").click(function() {
		//jsjb = ractive.get("kfpjsid");
		jsjb = ractive.get("sjjs");
		//		if(jsjb.trim() == "") {
		//			alert("请选择角色级别!");
		//			return;
		//		}
		if(jsjbqj == jsjb) {
			return;
		}
		//如果上级和上次点击的一样，则无需重新加载
		jsjbqj = jsjb;
		$.sdAjax({
			url: Constants.ctrlAddress + "getViewAllMenu?jsgs=" + jsgsParam + "&jsId=" + jsjb,
			type: "get",
			dataType: "json",
			contentType: "application/json",
			waitFlag: true, // 是否需要加载等待动画
			successCallback: function(obj) {
				if(typeof obj == "object") {
					showTree("#lefttree", setting, obj);
					showTree("#righttree", settingRight, []);
					bind();
				}

			}
		});
	});

	//保存按钮
	$(".hp-show-btns >.hp-glpt-save").click(function() {
			$(".hp-black").addClass("hide");
			$(".hp-show").addClass("hide");
		})
		//取消按钮
	$(".hp-show-btns >.hp-glpt-cancel").click(function() {
		//$.fn.zTree.getZTreeObj("righttree").destroy();
		$(".hp-black").addClass("hide");
		$(".hp-show").addClass("hide");
	})

});

function showTree(id, setting, zNodes) {
	$.fn.zTree.init($(id), setting, zNodes);
}

var setting = {
	check: {
		enable: true,
		nocheckInherit: true,
	},
	view: {
		showLine: false
	},
	data: {
		simpleData: {
			enable: true
		}
	},

};

var settingRight = {
	check: {
		enable: true,
		nocheckInherit: true,
	},
	view: {
		addDiyDom: addDiyDom,
		showLine: false
	},
	data: {
		simpleData: {
			enable: true
		}
	},

};

function addDiyDom(treeId, treeNode) {
	if(treeNode&&typeof(treeNode.children)!="undefined" && treeNode.children.length>0) return;
	var aObj = $("#" + treeNode.tId + '_a');
	if($("#diyBtn_" + treeNode.id).length > 0) return;
	if(treeNode.czjb == "02") {
		//分配到右侧后，默认是可读
		//treeNode.czjb = "01";

		var editStr = "<input type='button'  style='width:50px;' value='设置可写' class='kxbtn' id='diyBtn_" + treeNode.id + "' title='" + treeNode.name + "' onfocus='this.blur();'/>";
		aObj.after(editStr);
		var btn = $("#diyBtn_" + treeNode.id);
		if(btn) btn.bind("click", function() {
			//有没有操作过就是点击过
			treeNode.dxflag = true;
			//alert("diy Button for " + treeNode.name);
			if(btn.hasClass("kxbtn-blue")) {
				btn.removeClass("kxbtn-blue");
				treeNode.czjb = "01";
				//alert(treeNode.czjb);
			} else {
				btn.addClass("kxbtn-blue");
				treeNode.czjb = "02";
				//alert(treeNode.czjb);
			}

		});

	}
}

//父节点
function filter(nodes) {
	return(nodes.pId == null);
}

function isContain(list, obj) {
	var flag = false;
	for(var i = 0; i < list.length; i++) {
		if($.trim(list[i].id) == $.trim(obj)) {
			flag = true;
		}
	}
	return flag;
}

//查找选中的树的节点pid为id的
function getSelectedNode(nodes, id) {
	var list = [];
	if(nodes != null) {
		for(var i = 0; i < nodes.length; i++) {
			if($.trim(nodes[i].pId) == $.trim(id)) {
				list.push(nodes[i]);
			}
		}
	}
	return list;
}

//将arr0中的数组元素中arr1的删除
function removeItem(arr0, arr1) {
	var list = [];
	for(var i = 0; i < arr0.length; i++) {
		if(!isContain(arr1, arr0[i].id)) {
			list.push(arr0[i]);
		}
	}
	return list;
}

//将arr0中的数组元素中arr1的删除,排除父
	function removeChildItem(arr0, arr1) {
		var list = [];
		for (var i = 0; i < arr0.length; i++) {
			if (!isContain(arr1, arr0[i].id) || arr0[i].pId == null) {
				list.push(arr0[i]);
			}
		}
		return list;
	}

function bind() {
	//授予
	$('.hp-qxfp-xz').click(function() {
		//左边树
		var lefttreeObj = $.fn.zTree.getZTreeObj("lefttree");
		//右边树
		var righttreeObj = $.fn.zTree.getZTreeObj("righttree");
		//获取左边树中选中的
		var nodes = lefttreeObj.getCheckedNodes(true);
		var r = righttreeObj.transformToArray(righttreeObj.getNodes());
		nodes=removeChildItem(nodes,r);
		//左边树的父节点
		var parentNodes = lefttreeObj.getNodesByFilter(filter);
		//右边树的父节点
		var parentRightNodes = righttreeObj.getNodesByFilter(filter);
		if(nodes.length == 0) {
			return;
			//alert(zzhgl_app.jsgjhRactive.get("i18n_zzhgljsqxfp_choseLeft"));
		} else {
			var parents = [];
			for(var i = 0; i < nodes.length; i++) {
				//寻找选中的父节点
				if(isContain(parentNodes, nodes[i].id)) {
					var pNode = nodes[i]; //父节点
					//如果右边的树没有该父节点，则先生成该父节点

					//查询左边树的当前父id的所有子
					var cNodes = pNode.children == null ? [] : pNode.children;
					//选中的树为当前父的子
					var selectedNode = getSelectedNode(nodes, pNode.id);
					//先清空左边的父节点。。。。。。左边的树，不改动
					lefttreeObj.removeChildNodes(pNode);
					//如果当前父节点不包含右边的父节点，右边增加父节点,左边数，不修改
					if(!isContain(parentRightNodes, pNode.id)) {
						//生成父
						righttreeObj.addNodes(null, pNode);
					}
					//如果该父节点的下都选中
					if(selectedNode.length == cNodes.length) {
						var rParentZNode = righttreeObj.getNodeByParam("id", pNode.id, null);
						righttreeObj.addNodes(rParentZNode, selectedNode);
						//lefttreeObj.removeNode(pNode);
					} else {
						//左边当前父id未选中的
						var leftNode = removeItem(cNodes, selectedNode);
						//获取父节点
						var rParentZNode = righttreeObj.getNodeByParam("id", pNode.id, null);
						righttreeObj.addNodes(rParentZNode, selectedNode);
						//获取父节点
						//var lParentZNode = lefttreeObj.getNodeByParam("id", pNode.id, null);
						//lefttreeObj.addNodes(lParentZNode, leftNode);
					}

				    lefttreeObj.addNodes(pNode);
					var lParentZNode = lefttreeObj.getNodeByParam("id", pNode.id, null);
					lefttreeObj.addNodes(lParentZNode, cNodes);

				}
			}
		}

	});

	$('.hp-qxfp-sc').click(function() {
		//右边树
		var righttreeObj = $.fn.zTree.getZTreeObj("righttree");
		//左边树
		var lefttreeObj = $.fn.zTree.getZTreeObj("lefttree");
		//lefttreeObj.checkAllNodes(false);
		//获取右边树中选中的
		var nodes = righttreeObj.getCheckedNodes(true);
		//右边树的父节点
		var parentNodes = righttreeObj.getNodesByFilter(filter);
		var parentIds=[];
		//左边树的父节点
		//var parentLeftNodes = lefttreeObj.getNodesByFilter(filter);
		for(var i = 0; i < nodes.length; i++) {
			nodes[i].checked = false;
		}
		if(nodes.length == 0) {
			return;
			//alert(zzhgl_app.jsgjhRactive.get("i18n_zzhgljsqxfp_choseRight"));
		} else {
			var parents = [];
			for(var i = 0; i < nodes.length; i++) {
				//寻找选中的父节点
				if(isContain(parentNodes, nodes[i].id) &&  !isContain(parentIds, nodes[i].pId==null?nodes[i].id:nodes[i].pId)) {
					var pNode = nodes[i]; //父节点
					
					var obj={"id":pNode.id};
					parentIds.push(obj);
					//如果右边的树没有该父节点，则先生成该父节点
					//查询左边树的当前父id的所有子
					var cNodes = pNode.children == null ? [] : pNode.children;
					//选中的树为当前父的子
					var selectedNode = getSelectedNode(nodes, pNode.id);
					//先清空右边的父节点
					//righttreeObj.removeChildNodes(pNode);
					//如果当前父节点不包含右边的父节点，右边增加父节点，、、、、、左边树不改动
					//if(!isContain(parentLeftNodes, pNode.id)) {
					//生成父
					//lefttreeObj.addNodes(null, pNode);
					//}
					//如果该父节点的下都选中
					if(selectedNode.length == cNodes.length) {
						//var rParentZNode = lefttreeObj.getNodeByParam("id", pNode.id, null);
						//lefttreeObj.addNodes(rParentZNode, selectedNode);
						righttreeObj.removeNode(pNode);
					} else {
						pNode.checked = false;
						/*//左边当前父id未选中的
						var rightNode = removeItem(cNodes, selectedNode);
						//获取父节点
						//var rParentZNode = lefttreeObj.getNodeByParam("id", pNode.id, null);
						//lefttreeObj.addNodes(rParentZNode, selectedNode);
						//获取父节点
						var lParentZNode = righttreeObj.getNodeByParam("id", pNode.id, null);
						righttreeObj.addNodes(lParentZNode, rightNode);*/
						for(var i=0;i<selectedNode.length;i++){
							righttreeObj.removeNode(selectedNode[i]);
						}
						
					}

				}
			}
		}
	});

}