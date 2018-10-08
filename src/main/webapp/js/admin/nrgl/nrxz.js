var ractive = new Ractive({
	el: '#nrform',
	template: '#first-select',
	oninit: function() {
		//this.loadData();
	},
	loadBjData: function() {
		var _this = this;
		$.sdAjax({
			url: Constants.ctrlAddress + 'nrgl/selectNrxxById?id=' + store.get("nrxxId"),
			type: 'GET',
			async: false, // 是否异步
			waitFlag: true, // 是否需要加载等待动画
			successCallback: function(data) {
				if(data && data.result && data.object) {
					var nrxx = data.object;
					//回显值的时候把最低级的分类ID赋值给隐藏域
					$("#xlcx").val(nrxx.flId);
					_this.set("nrxx", nrxx);
					var flMap = nrxx.flxxMap;
					var list = new Array();
					for(var key in flMap) {
						var json = eval('(' + key + ')');
						if(Constants.flsx == json.flsx) {
							_this.set("first", json.id);
							continue;
						}
						var str = '<dl id="' + json.fljb + '">';
						str += '	<dt><span class="red">*</span>' + Constants.flxx[parseInt(json.fljb)] + '</dt>';
						str += '	<dd>';
						str += '		<select class="sdui-select" value="" onchange="flChange(this)">';
						str += '			<option value=""></option>';
						for(var i = 0; i < flMap[key].length; i++) {
							str += '		<option value="' + flMap[key][i].id + '"';
							if(json.id == flMap[key][i].id) {
								str += " selected ";
							}
							str += '>' + flMap[key][i].flmc + '</option>';
						}
						str += '		</select>';
						str += '	</dd>';
						str += '</dl>';
						$("#selectQy").append(str);
					}
					//动态拼接附件列表
					for(var i = 0; i < nrxx.fjList.length; i++) {
						var fjxx = nrxx.fjList[i];
						$('#thelist').append('<li class="active" name="fjlb" lsid="' + fjxx.id + '" status="1" id="' + fjxx.id + '" ljm="' + fjxx.ljm + '"  mtlx="' + fjxx.mtlx + '" wjgs="' + fjxx.wjgs + '" fjdx="' + fjxx.fjdx + '" fjdz="' + fjxx.fjdz + '"><p>' + fjxx.ljm + '</p><div class="hp-nrxz-pro"><span class="hp-nrxz-ysc" id="progress' + fjxx.id + '" style="width:100%;"></span></div><i class="hp-nrxz-delsp" style="cursor: pointer" onclick="deleteFile(\'' + fjxx.id + '\')"></i>');
					}

					// _this.set("flxxSelect",list);
				}
			}
		})
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
	//上架按钮
	sj: function(param) {
		if(store.get("btn_dis")=="0"){
			alert(" 附件上传中，请稍候！");
			return;
		}
		param.nrzt = Constants.nrztZS;
		param.zkzt = Constants.zkztSj;
		var bjgn = store.get("bjgn");
		var url;
		if(bjgn == Constants.nrztZS || bjgn == Constants.nrztCG) {
			url = Constants.ctrlAddress + "nrgl/updateNrxx";
		} else {
			url = Constants.ctrlAddress + "nrgl/addContent";
		}
		$.sdAjax({
			url: url,
			type: 'post',
			data: param,
			dataType: 'json',
			successCallback: function(data) {
				if(data.result) {
					success("保存成功", function() {
						location.href = Constants.appAddress + "/page/nrgl/nrgl.html?yqbs=ytj";
					}, true)
				}
			},
			errorCallback: function(data) {
				alert("保存失败", null, false);
			}
		});
	},
	//提交按钮
	tj: function(param) {
		if(store.get("btn_dis")=="0"){
			alert(" 附件上传中，请稍候！");
			return;
		}
		param.nrzt = Constants.nrztZS;
		//编辑按钮操作回显数据的时候URL 应变成修改的接口
		var bjgn = store.get("bjgn");
		var url;
		if(bjgn == Constants.nrztZS || bjgn == Constants.nrztCG) {
			url = Constants.ctrlAddress + "nrgl/updateNrxx";
		} else {
			url = Constants.ctrlAddress + "nrgl/addContent";
		}

		$.sdAjax({
			url: url,
			type: 'post',
			data: param,
			dataType: 'json',
			successCallback: function(data) {
				if(data.result) {
					success("保存成功", function() {
						location.href = Constants.appAddress + "/page/nrgl/nrgl.html?yqbs=ytj";
					}, true)
				}
			},
			errorCallback: function(data) {
				alert("保存失败", null, false);
			}
		});
	},
	//保存草稿按钮
	cg: function(param) {
		if(store.get("btn_dis")=="0"){
			alert(" 附件上传中，请稍候！");
			return;
		}
		var bjgn = store.get("bjgn");
		var url;
		if(bjgn == Constants.nrztCG) {
			url = Constants.ctrlAddress + "nrgl/updateNrxxCg";
		} else {
			url = Constants.ctrlAddress + "nrgl/addContent";
		}
		param.nrzt = Constants.nrztCG;
		$.sdAjax({
			url: url,
			type: 'post',
			data: param,
			dataType: 'json',
			successCallback: function(data) {
				if(data.result) {
					success("保存成功", function() {
						fh();
					}, true)
				}
			},
			errorCallback: function(data) {
				alert("保存失败", null, false);
			}
		});
	},
	oncomplete: function() {

		var _this = this;
		//编辑回显数据调用
		var bjgn = store.get("bjgn");
		if(bjgn == Constants.nrztZS) {
			$("#bccgBtn").hide();
			$("#bt").text("编辑内容");
			_this.loadBjData();
		} else if(bjgn == Constants.nrztCG) {
			$("#bt").text("编辑内容");
			_this.loadBjData();
		} else {
			_this.set("nrxx.fjzs", 0);
		}
		$("#form-nrxx").sdValidate({
			messageShow: { // 此属性用于设置提示信息的显示，及显示风格
				show: true,
				type: 4 // 1、正常显示、2、alert框方式显示 3、tip方式显示、4、吐司方式显示
			},
			focusout: true,
			action: function() {
				var flId = $("#xlcx").val();
				if(flId == "" || flId == null) {
					alert("内容级别必须选择", null, false);
					return;
				}
				var fjList = new Array();
				$("li[name='fjlb']").each(function(index, value) {
					var fjxx = {
						ljm: value.getAttribute("ljm"),
						mtlx: value.getAttribute("mtlx"),
						wjgs: value.getAttribute("wjgs"),
						fjdx: value.getAttribute("fjdx"),
						fjdz: value.getAttribute("fjdz")
					}
					fjList.push(fjxx);
				});
				var param = _this.get("nrxx");
				param.flId = $("#xlcx").val();
				param.fjList = fjList;
				if(store.get("bcfs") == Constants.nrztZS) {
					_this.tj(param);
				}
				if(store.get("bcfs") == Constants.nrztCG) {
					_this.cg(param);
				}
				if(store.get("bcfs") == Constants.nrztSJ) {
					_this.sj(param);
				}
			},
			rules: {
				nrbt: {
					required: true,
					maxlength: 100
				},
				nrsm: {
					required: true,
					maxlength: 500
				},
			},
			messages: { // 提示会有默认信息，如果你认为默认提示不好，或者不能满足业务提示需要，可以使用下面的属性覆盖
				nrbt: {
					required: '内容标题不能为空',
					maxlength: '内容标题不能超过100个字符'
				},
				nrsm: {
					required: '内容说明不能为空',
					maxlength: '内容说明不能超过500个字符'
				},
			}
		});
		this.on({
			firstChange: function(event) {
				$("#xlcx").val('');
				$("#first").nextAll().remove();
				if(event.context.first == "" || event.context.first == null) {
					return;
				}
				$.sdAjax({
					url: Constants.ctrlAddress + 'flgl/getFlxxList?parentId=' + event.context.first,
					type: 'GET',
					async: false, // 是否异步
					waitFlag: true, // 是否需要加载等待动画
					successCallback: function(data) {
						if(data && data.result && data.data) {
							var str = '<dl id="' + data.data[0].fljb + '">';
							str += '	<dt><span class="red">*</span>' + Constants.flxx[parseInt(data.data[0].fljb)] + '</dt>';
							str += '	<dd>';
							str += '		<select class="sdui-select" value="{{first}}" onchange="flChange(this)">';
							str += '			<option value=""></option>';
							for(var i = 0; i < data.data.length; i++) {
								str += '		<option value="' + data.data[i].id + '">' + data.data[i].flmc + '</option>';
							}
							str += '		</select>';
							str += '	</dd>';
							str += '</dl>';
							$("#selectQy").append(str);
						}
					}
				})
			},
			//上架按钮
			sj: function() {
				store.set("bcfs", Constants.nrztSJ);
				$("#form-nrxx").submit();
			},
			//正式提交
			tj: function() {
				store.set("bcfs", Constants.nrztZS);
				$("#form-nrxx").submit();
			},
			//保存草稿
			cg: function() {
				store.set("bcfs", Constants.nrztCG);
				$("#form-nrxx").submit();
			}
		})
		// 初始化select控件
		$.sdSelect({ ractive: ractive });
	}
})

function flChange(obj) {
	$("#xlcx").val('');
	$("#" + $(obj).parent().parent().attr("id")).nextAll().remove();
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
				var str = '<dl id="' + data.data[0].fljb + '">';
				str += '	<dt><span class="red">*</span>' + Constants.flxx[parseInt(data.data[0].fljb)] + '</dt>';
				str += '	<dd>';
				str += '		<select class="sdui-select" value="{{first}}" onchange="flChange(this)">';
				str += '			<option value=""></option>';
				for(var i = 0; i < data.data.length; i++) {
					str += '		<option value="' + data.data[i].id + '">' + data.data[i].flmc + '</option>';
				}
				str += '		</select>';
				str += '	</dd>';
				str += '</dl>';
				$("#selectQy").append(str);
			} else {
				$("#xlcx").val(obj.value);
			}
		}
	})
}

//------------------------------------------上传文件-------------------------------------------------

WebUploader.Uploader.register({
	"before-send-file": "beforeSendFile"
}, {
	//时间点1：所有分块进行上传之前调用此函数
	beforeSendFile: function(file) {
//		$("#bccgBtn").attr("disabled", true);
//		$("#tjBtn").attr("disabled", true);
//		$("#sjBtn").attr("disabled", true);
		store.set("btn_dis","0");
	}
});

var fjzs = 0;
//上传文件
var uploader = WebUploader.create({
	// swf文件路径
	swf: '../../components/webuploader-0.1.5/Uploader.swf',
	// 文件接收服务端。
	server: Constants.test + 'file/upload',
	// 选择文件的按钮。可选。
	// 内部根据当前运行是创建，可能是input元素，也可能是flash.
	pick: '#picker',
	//选择文件后直接上传
	auto: true,
	threads: 2,
	formData: { guid: WebUploader.Base.guid() },
	//开启分片上传
	chunked: true,
	duplicate: true,
	chunkSize: 5* 1024,
	timeout: 0,
	accept: {
		title: 'videos',
		extensions: Constants.videoTypes,
		mimeTypes: Constants.videoMimeTypes
	},
});

// 当有文件被添加进队列的时候
uploader.on('fileQueued', function(file) {
	$('#thelist').append('<li class="active" name="fjlb" status="0"  id="' + file.id + '" ljm="' + file.name + '"  mtlx="' + file.type + '" wjgs="' + file.ext + '" fjdx="' + file.size + '" fjdz=""><p>' + file.name + '</p><div class="hp-nrxz-pro"><span class="hp-nrxz-ysc" id="progress' + file.id + '"></span></div><span id="percent' + file.id + '">等待上传</span><i class="hp-nrxz-delsp" style="cursor: pointer" onclick="deleteFile(\'' + file.id + '\')"></i>');
});
// 文件上传过程中创建进度条实时显示。
//上传中
uploader.on('uploadProgress', function(file, percentage) {
	if(percentage== 1){
		$('#percent' + file.id).html("99.99/100");	
	}else{
		$('#percent' + file.id).html((percentage*100).toFixed(2) + "/100");
	}
	
	$('#progress' + file.id).css('width', Math.round(percentage * 100) + '%');
});
//已上传
uploader.on('uploadSuccess', function(file, respones) {
	fjzs = $("#pink").text();
	var lbsl = $("li[name='fjlb']").length;
	if(lbsl > fjzs) {
		$("#"+file.id).attr("status","1");
		fjzs++;
	}
	$("#pink").text(fjzs);
	$("#" + file.id).attr("fjdz", respones.object);
});

uploader.on('uploadError', function(file) {
	deleteFile(file.id);
	alert("上传失败", null, false);
});
//上传完成
uploader.on('uploadComplete', function(file, respones) {
	$('#percent' + file.id).html("100/100");	
	var lbsl = $("li[name='fjlb']").length;
	var ycgsl = $("#pink").text();
	if(lbsl == ycgsl) {
		store.set("btn_dis","1");
//		$("#bccgBtn").attr("disabled", false);
//		$("#tjBtn").attr("disabled", false);
//		$("#sjBtn").attr("disabled", false);
	}
});
//删除附件
function deleteFile(data) {
	if(store.get("nrxxId")==null||store.get("nrxxId")==''){
		try {
            uploader.removeFile(uploader.getFile(data));//不要遗漏
		} catch (e) {
			// TODO 异常处理
		}
	}
	var lbsl = $("li[name='fjlb']").length;
	fjzs = $("#pink").text();
	/*先判断列表数量和已上传的数量
	 *如果上传列表的数量大于已上传的数量，那么就是上传中，被删除的
	 * 所以不用更改已上传的数量，直接删除这个列表行
	 */
	if(lbsl <= fjzs && fjzs > 0) {
		fjzs--;
	}
	else if($("#"+data).attr("status")==1){
		fjzs--;
	}
	$("#" + data).remove();
	$("#pink").text(fjzs);
	if(lbsl == fjzs) {
		store.set("btn_dis","1");
//		$("#bccgBtn").attr("disabled", false);
//		$("#tjBtn").attr("disabled", false);
//		$("#sjBtn").attr("disabled", false);
	}
}

//历史附件
function lsfj() {
	var lsfjIds = new Array();
	$("li[name='fjlb']").each(function(index, value) {
		if(value.getAttribute("lsid") == "" || value.getAttribute("lsid") == null) {
			return false;
		}
		lsfjIds.push(value.getAttribute("lsid"));
	});
	store.set("lsfjIds", lsfjIds);
	var opts = {
		content: 'nrgl/fjcx.html',
		scroll: false,
		title: '',
		type: 2,
		width: '1000px',
		height: '520px',
		shadeClose: false,
		end: function() {
			fjzs = $("#pink").text();
			var lsfjlb = store.get("lsfjlb");
			if(lsfjlb == "" || lsfjlb == null || lsfjlb.length <= 0) {
				return;
			}
			for(var i = 0; i < lsfjlb.length; i++) {
				$('#thelist').append('<li class="active" name="fjlb" status="1" lsid="' + lsfjlb[i].id + '"  id="' + lsfjlb[i].id + '" ljm="' + lsfjlb[i].ljm + '"  mtlx="' + lsfjlb[i].mtlx + '" wjgs="' + lsfjlb[i].wjgs + '" fjdx="' + lsfjlb[i].fjdx + '" fjdz="' + lsfjlb[i].fjdz + '"><p>' + lsfjlb[i].ljm + '</p><div class="hp-nrxz-pro"><span class="hp-nrxz-ysc" id="progress' + lsfjlb[i].id + '" style="width:100%"></span></div><i class="hp-nrxz-delsp" style="cursor: pointer" onclick="deleteFile(\'' + lsfjlb[i].id + '\')"></i>');
				fjzs++;
			}
			$("#pink").text(fjzs);
			store.set("lsfjlb", "");
		}
	}
	dialog(opts);
}

function dlmCheck(event) {
	replaceAndSetPos(event, /^(^\s+)|(\s+$)/g, '');
	var pattern = new RegExp("[·`~!+@%#$^&*\——()=|{}':;',\\[\\].<>/?~！@#￥……&*（）|{}《》【】｛｝’‘；：”“'。，、？\"\"_-]", "g")
	replaceAndSetPos(event, pattern, '');
}

function fh() {
	if(store.get("yqbs")) {
		location.href = Constants.appAddress + "/page/nrgl/nrgl.html?yqbs=ytj"
	} else {
		location.href = Constants.appAddress + "/page/nrgl/nrgl.html?yqbs=dtj"
	}
}