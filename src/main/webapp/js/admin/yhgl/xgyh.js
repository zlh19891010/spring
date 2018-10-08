var id = getQueryMbWeb("id");
var ractive = new Ractive({
	el: '#yhxx-form',
	template: '#yhxx-template',
	oninit: function(options) {
		var _this = this;
		var data = {
			jsgs: Constants.jsgsyy
		};
		$.sdAjax({
			url: Constants.ctrlAddress + 'getJsxxAll',
			type: 'POST',
			async: false,
			data: data,
			contentType: 'application/json',
			successCallback: function(data) {
				_this.set("jsxxlist", data.object)
				var size = Math.floor((data.object.length - 1) / 4) + 1;
				var xhlist = [];
				for(var i = 1; i < size; i++) {
					xhlist.push({ ks: 4 * i, js: 4 * (i + 1) });
				}
				_this.set("xhlist", xhlist)
			}
		})
		_this.on("save", function(event) {
			$("#form-yhxx").submit();
		});
		_this.on("fanhui", function(event) {
			window.history.back();
		});
	},
	oncomplete: function() {
		var _this = this;
		$.sdAjax({
			url: Constants.ctrlAddress + "getYhxxbyId?id=" + id,
			dataType: 'json',
			type: 'get',
			contentType: 'application/json',
			async: true, // 是否异步
			waitFlag: true, // 是否需要加载等待动画
			successCallback: function(data) {
				_this.set("yhxx", data.object)
				if(data.object.yhtx != null && data.object.yhtx != '') {
					$("#sczp").text("修改照片")
					_this.set("yhtx", Constants.manageFileAddress + data.object.yhtx);
					_this.set("txpath", data.object.yhtx);
				} else {
					$("#sczp").text("上传照片")
					_this.set("yhtx", '');
					_this.set("txpath", '');
				}
				$("input[name='js']").each(function(id, value) {
					if(value.value == data.object.jsId) {
						$("#gly" + id).prop("checked", true)
					}
				});
			}
		})
		$("#form-yhxx").sdValidate({
			messageShow: { // 此属性用于设置提示信息的显示，及显示风格
				show: true,
				type: 4 // 1、正常显示、2、alert框方式显示 3、tip方式显示、4、吐司方式显示
			},
			focusout: true,
			action: function() {
				var datas = {
					id: id,
					yhmc: _this.get("yhxx.yhmc"),
					yx: _this.get("yhxx.yx"),
					sjh: _this.get("yhxx.sjh"),
					jsId: $("input[name='js']:checked").val(),
					yhtx: _this.get("txpath"),
				}
				if(datas.jsId == '' || datas.jsId == undefined) {
					message("请先创建角色")
					return;
				}
				$.sdAjax({
					url: Constants.ctrlAddress + 'modifyYhxx',
					type: 'POST',
					async: true,
					contentType: 'application/json',
					data: datas,
					successCallback: function(data) {
						success("保存成功", function() {
							window.history.back(-1);
						}, true)
					}
				});
			},
			rules: {
				xm: {
					required: true
				},
				yx: {
					email: true
				},
			},
			messages: { // 提示会有默认信息，如果你认为默认提示不好，或者不能满足业务提示需要，可以使用下面的属性覆盖
				xm: {
					required: '用户名称不能为空'
				},
				yx: {
					email: '邮箱格式不正确'
				},

			}
		});
	},
});

function sjCheck(event) {
	var pattern = new RegExp("[a-zA-Z\u4e00-\u9fa5]", "g")
	replaceAndSetPos(event, pattern, '');
}

function yhxm(event) {
	replaceAndSetPos(event, /^(^\s+)|(\s+$)/g, '');
	var pattern = new RegExp("[0-9·`~!+@%#$^&*\——()=|{}':;',\\[\\].<>/?~！@#￥……&*（）|{}《》【】｛｝’‘；：”“'。，、？\"\"_-]", "g")
	replaceAndSetPos(event, pattern, '');
}

function onk(event) {
	var pattern = new RegExp("[\u4e00-\u9fa5]", "g")
	replaceAndSetPos(event, pattern, '');
	replaceAndSetPos(event, /^(^\s+)|(\s+$)/g, '');
}
//上传头像
var allMaxSize = 2;
var uploader = WebUploader.create({
	// swf文件路径
	swf: '../../components/webuploader-0.1.5/Uploader.swf',
	// 文件接收服务端。
	server: Constants.ctrlAddress + 'file/upload',
	// 选择文件的按钮。可选。
	// 内部根据当前运行是创建，可能是input元素，也可能是flash.
	pick: '#picker',
	//选择文件后直接上传
	auto: true,
	threads: 1,
	formData: { guid: WebUploader.Base.guid() },
	//上传图片大小
	fileSingleSizeLimit: allMaxSize * 1024 * 1024,
	duplicate :true,
	// 只允许选择图片文件。
	accept: {
		title: 'Images',
		extensions: 'gif,jpg,jpeg,bmp,png',
		mimeTypes: Constants.imgTypes
	}
});
// 文件上传过程中创建进度条实时显示。
//上传中
uploader.on('uploadProgress', function(file, percentage) {});
//已上传
uploader.on('uploadSuccess', function(file, respones) {
	ractive.set("yhtx", Constants.manageFileAddress + respones.object)
	ractive.set("txpath", respones.object);
	$("#sczp").text("修改照片")
});

uploader.on('uploadError', function(file) {});
//上传完成
uploader.on('uploadComplete', function(file, respones) {

});
// 判断文件出错
uploader.onError = function(code) {
	var msg;
	switch(code) {
		case 'Q_EXCEED_SIZE_LIMIT':
			msg = '上传文件大小过大';
			break;
		case 'Q_TYPE_DENIED':
			msg = '文件类型上传错误';
			break;
	}
	alert(msg);
};

function stop(o) {
	alert(o + "  " + o.id);
	Queue.getFile(o.id);
}
$(".hp-glpt-ckqx").click(function() {
	var jsId = $("input[name='js']:checked").val();
	var opts = {
		content: 'yhgl/ckqx.html?jsId=' + jsId +'&jsgs=02',
		scroll: true,
		title: '查看权限',
		type: 2,
		width: '300px',
		height: '400px',
		shadeClose: false,
		end: function() {}
	}
	dialog(opts);

});