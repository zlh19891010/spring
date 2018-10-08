var jsId = '';
var ractive = new Ractive({
	el: '#gly-form',
	template: '#gly-template',
	oninit: function(options) {
		var _this = this;
		//保存按钮
		_this.on("bc", function(event) {
			$("#form-gly").submit();
		});
		//返回按钮
		_this.on("fh", function(event) {
			window.history.back();
		});
		//获取角色
		$.sdAjax({
			url: Constants.ctrlAddress + 'getJsxxAll',
			type: 'POST',
			async: false,
			data: {
				jsgs: Constants.jsgspt
			},
			contentType: 'application/json',
			successCallback: function(data) {
				_this.set("jsxxlist", data.object)
				var size = Math.floor((data.object.length - 1) / 4) + 1;
				var xhlist = [];
				for(var i = 1; i < size; i++) {
					xhlist.push({
						ks: 4 * i,
						js: 4 * (i + 1)
					});
				}
				_this.set("xhlist", xhlist)
			}
		});

	},
	oncomplete: function() {
		var _this = this;
		//页面初始化 回显
		$.sdAjax({
			url: Constants.ctrlAddress + "tGlptGlyglGlyxx/findById?id=" + getQueryMbWeb("id"),
			type: 'get',
			async: true,
			dataType: 'json',
			successCallback: function(data) {
				_this.set("gly", data.object)
				if(data.object.yhtx != null && data.object.yhtx != '') {
					_this.set("yhtx", Constants.manageFileAddress + data.object.yhtx);
					_this.set("txpath", data.object.yhtx);
					$("#sczp").text("修改照片")
				} else {
					_this.set("yhtx", '');
					_this.set("txpath", '');
				}
				$("input[name ='js']").each(function(id, value) {
					if(value.value == data.object.jsId) {
						$("#gly" + id).prop("checked", true)
					}
				})
				var jsxx = _this.get("jsxxlist")
				jsId = data.object.jsId;
				for(var i = 0;i < jsxx.length;i++){
					if(jsxx[i].id == data.object.jsId){
						_this.set("jsmcs",jsxx[i].jsmc)
					}
					
				}
			}
		});
		//验证框架
		$("#form-gly").sdValidate({
			messageShow: { // 此属性用于设置提示信息的显示，及显示风格
				show: true,
				type: 4 // 1、正常显示、2、alert框方式显示 3、tip方式显示、4、吐司方式显示
			},
			focusout: true,
			action: function() {
				var gly = {
					id: _this.get("gly.id"),
					yhmc: _this.get("gly.yhmc"),
					yx: _this.get("gly.yx"),
					sjh: _this.get("gly.sjh"),
					jsId: $("input[name='js']:checked").val(),
					yhtx: _this.get("txpath")
				}
				_this.bc(gly);
			},
			rules: {
				loginid: {
					required: true
				},
				dlmm: {
					required: true
				},
				qrmm: {
					required: true
				},
				zsxm: {
					required: true
				},
				yx: {
					email: true
				},
			},
			messages: { // 提示会有默认信息，如果你认为默认提示不好，或者不能满足业务提示需要，可以使用下面的属性覆盖
				loginid: {
					required: '登录名不能为空'
				},
				dlmm: {
					required: '密码不能为空'
				},
				qrmm: {
					required: '确认密码不能为空'
				},
				zsxm: {
					required: '管理员名称不能为空'
				},
				yx: {
					email: '邮箱格式不正确'
				},
			}
		});
	},
	//保存操作
	bc: function(data) {
		$.sdAjax({
			url: Constants.ctrlAddress + "tGlptGlyglGlyxx/modifyGly",
			type: 'post',
			data: data,
			dataType: 'json',
			successCallback: function(data) {
				if(data.result) {
					success("保存成功", function() {
						window.history.back(-1);
					}, true)

				}
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
	fileSingleSizeLimit: allMaxSize * 1024 * 1024,
	auto: true,
	threads: 1,
	duplicate :true,
	formData: {
		guid: WebUploader.Base.guid()
	},
	//开启分片上传
	chunked: false,
	chunkSize: 5 * 1024 * 1024,
	accept: {
		title: 'Images',
		extensions: 'jpg,jpeg,bmp,png',
		mimeTypes: Constants.imgTypes,
	},
});

// 当有文件被添加进队列的时候
uploader.on('fileQueued', function(file) {
	$('#thelist').append('<div id="' + file.id + '" class="item">' +
		'<a class="upbtn" id="btn" onclick="stop(' + file.id + ')">[取消上传]</a>' +
		'<p class="info">' + file.name + '</p>' +
		'<p class="state">等待上传...</p></div>'
	);
});
// 文件上传过程中创建进度条实时显示。
//上传中
uploader.on('uploadProgress', function(file, percentage) {});
//已上传
uploader.on('uploadSuccess', function(file, respones) {
	ractive.set("yhtx", Constants.manageFileAddress + respones.object)
	ractive.set("txpath", respones.object);
});

uploader.on('uploadError', function(file) {
	$('#' + file.id).find('p.state').text('上传出错');
});
//上传完成
uploader.on('uploadComplete', function(file, respones) {
	$("#sczp").text("修改照片")
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
	jsId = $("input[name='js']:checked").val();
	var opts = {
		content: 'yhgl/ckqx.html?jsId=' + jsId +'&jsgs=01',
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
	function returns(){
			window.history.back()
	}
$(".hp-ckqx").click(function() {
	var opts = {
		content: 'yhgl/ckqx.html?jsId=' + jsId +'&jsgs=01',
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