//登录
function login(loginAddress) {
	if ($("#userName").val() == "" || $("#password").val() == "") {
		message("用户名密码不能为空！");
		return;
	}

	if (validateUserName($("#userName").val())) {
		$.sdAjax({
			url: Constants.ctrlAddress + loginAddress,
			dataType: 'json',
			type: 'post',
			contentType: 'application/json',
			data: {
				userName: $("#userName").val(),
				mm: $("#password").val()
			},
			async: true, // 是否异步
			waitFlag: true, // 是否需要加载等待动画
			successCallback: function(data) {
				if (data && data.result) {
					window.location.href = "page/main.html";
				} else {
					message(data.message);
				}

			}
		})
	} else {
		message("用户名错误");
	}

}
//效验用户
function validateUserName(str) {
	var regex = /^[\u4e00-\u9fa5a-zA-Z0-9]+$/;
	if (regex.test(str)) {
		return true;
	} else {
		return false;
	}
}

$(document).keydown(function(event) {
	if (event.keyCode == 13) {
		$("#loginButton").click();
	}
})