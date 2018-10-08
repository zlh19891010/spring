//菜单切换
$(document).delegate(".nav-main>ul>li", 'click', function(e) {
	$('.nav-main>ul .cur').removeClass('cur');
	var curTarget = e.target || e.srcElement;
	if($(curTarget).siblings("ul").length < 1 && $(curTarget).children("ul").length < 1) {
		$(curTarget).addClass('cur');
		return;
	}
	$(this).addClass('cur');
	var ulList = $(this).siblings('li');
	for(var i = 0; i < ulList.length; i++) {
		if(!$(ulList[i]).find('ul').hasClass('hide')) {
			$(ulList[i]).find('ul').slideUp();
			$(ulList[i]).find('ul').addClass('hide');
		}
	}
	if($(this).has('em').length > 0) {
		var childNode = $(this).children('ul');
		if(childNode.hasClass('hide')) {
			childNode.slideDown();
			childNode.removeClass('hide');
			$(this).children('a').addClass('up');
		} else {
			childNode.slideUp();
			childNode.addClass('hide');
			$(this).children('a').removeClass('up');
		}
	}
})
//獲取用戶信息
var ractive = new Ractive({
	el: '#memu-content',
	template: '#memu-template',
	oninit: function() {
		$.sdAjax({
			url: Constants.ctrlAddress + 'getUserInfo',
			type: 'get',
			async: true, // 是否异步
			waitFlag: true, // 是否需要加载等待动画
			successCallback: function(data) {
				if(data && data.result) {
					var menu = data.object.listMoudle
					ractive.set("menuAddress", Constants.appAddress);
					ractive.set("menuList", menu);
					ractive.set("yhtx", Constants.manageFileAddress + data.object.tx);
					ractive.set("sAuthCode",data.object.sAuthCode);
					$.sdAjax({
						url: Constants.ctrlAddress + "tGlptGlyglGlyxx/selectCardByUserId",
						dataType: 'json',
						type: 'post',
						contentType: 'application/json',
						async: true, // 是否异步
						waitFlag: true, // 是否需要加载等待动画
						successCallback: function(data) {
							if(data && data.result) {
								ractive.set("cards",data.object);
							} else {
								success("操作失败");
							}

						}
					})
					ractive.set("isAdmin",data.object.loginId==Constants.ADMIN?true:false);
					resizeHtml('.sideView', 'contentFrame', 200);

					if(menu.length > 0) {
						if(menu[0].viewListMoudle) {
							ractive.set("initPage", Constants.appAddress + menu[0].viewListMoudle[0].treePath + "?id=" + menuList[0].viewListMoudle[0].id + "&new=" + Math.random());
						} else {
							ractive.set("initPage", Constants.appAddress + menu[0].treePath + "?id=" + menu[0].id + "&new=" + new Date().getTime());
						}
					}
				}
			}
		})
	}
})
//子頁面跳轉
function getUrl(treePath) {
	$("#contentFrame").src(Constants.appAddress + treePath + "?time=" + new Date().getTime());
}
//退出
function logOut() {
	$.sdAjax({
		url: Constants.ctrlAddress + 'logout',
		type: 'get',
		async: true, // 是否异步
		waitFlag: true, // 是否需要加载等待动画
		successCallback: function(data) {
			if(data && data.result) {
				window.location.href = "/login.html";
			}

		}
	})
}


//重置密码
function modifymm() {

	var opts = {
		content: 'glpt/glyczmm.html?flag=main',

		scroll: false,
		title: '重置密码',
		type: 2,
		width: '500px',
		height: '400px',
		shadeClose: false,
		end: function() {}
	}

	dialog(opts);
};