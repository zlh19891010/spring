/**
 * Created by yaojie on 2017/4/12.
 */
var banner_uploader = "banner_uploader";
var logo_uploader = "logo_uploader";
var ewm_uploader = "ewm_uploader";
var num = 0;
$(document).ready(function() {
	//上传banner
	banner_uploader = uploadImage({
		id: "#banner_picker"
	}, banner_uploader, 5);
	//上传logo
	logo_uploader = uploadImage({
		id: "#logo_picker",
		multiple: false
	}, logo_uploader, 1);
	//上传二维码
	ewm_uploader = uploadImage({
		id: "#ewm_picker",
		multiple: false
	}, ewm_uploader, 1);
	// 回显数据
	renderVrsy();
});

//------------------------------------------上传文件-------------------------------------------------
function uploadImage(elementId, uploader, fileNumLimit) {
	WebUploader.Uploader.register({
		"before-send-file": "beforeSendFile"
	}, {
		//时间点1：所有分块进行上传之前调用此函数
		beforeSendFile: function(file) {
			$("#tj").attr("disabled", true);
		}
	});
	var fjzs = 0;
	//上传文件
	uploader = WebUploader.create({

		// swf文件路径
		swf: '../../components/webuploader-0.1.5/Uploader.swf',
		// 文件接收服务端。
		server: Constants.ctrlAddress + 'file/uploadImage',
		// 选择文件的按钮。可选。
		// 内部根据当前运行是创建，可能是input元素，也可能是flash.
		pick: elementId,
		//选择文件后直接上传
		auto: true,
		//threads: 1,
		formData: {
			guid: WebUploader.Base.guid()
		},
		//开启分片上传
		chunked: false,
		duplicate: false,
		chunkSize: 20 * 1024 * 1024,
		timeout: 0,
		//disableGlobalDnd: true,
		//最多上传文件数量
		fileNumLimit: fileNumLimit,
		fileSizeLimit: 200 * 1024 * 1024, // 200 M
		fileSingleSizeLimit: 50 * 1024 * 1024, // 50 M
		accept: {
			title: 'Images',
			extensions: 'gif,jpg,jpeg,bmp,png',
			mimeTypes: 'image/jpg,image/jpeg,image/png,image/gif,image/bmp'
		},
	});
	// 当文件被加入队列之前触发
	uploader.on('beforeFileQueued', function(file) {
		if(elementId.id == "#banner_picker") {
			if(num == 5) {
				alert("最多上传5张图片！");
				return false;
			}
		} else if(elementId.id == "#logo_picker") {
			var _logoSrc = $("#logoImage").attr("src");
			if(_logoSrc) {
				return false;
			}
		} else {
			var _rwmSrc = $("#ewmImage").attr("src");
			if(_rwmSrc) {
				return false;
			}
		}
	});
	// 当有文件被添加进队列的时候
	uploader.on('fileQueued', function(file) {
		//console.log('fileQueued');
		//$('.mhgl-banner-list').append('<li class="active" name="fjlb" status="0"  id="' + file.id + '" ljm="' + file.name + '"  mtlx="' + file.type + '" wjgs="' + file.ext + '" fjdx="' + file.size + '" fjdz=""><p>' + file.name + '</p><div class="hp-nrxz-pro"><span class="hp-nrxz-ysc" id="progress' + file.id + '"></span></div><i class="hp-nrxz-delsp" style="cursor: pointer" onclick="deleteFile(\'' + file.id + '\')"></i>');
	});
	// 文件上传过程中创建进度条实时显示。
	//上传中
	uploader.on('uploadProgress', function(file, percentage) {});
	//已上传
	uploader.on('uploadSuccess', function(file, respones) {
		if(elementId.id == "#banner_picker") {
			$('.mhgl-banner-list').append('<li class="li_banner"><input type="hidden" id="' + file.id + '" ljm="' + file.name + '"  mtlx="' + file.type + '" wjgs="' + file.ext + '" fjdx="' + file.size + '" fjdz="' + respones.object + '"><img src="' + Constants.manageFileAddress + respones.object + '" style="width:226px;height:110px;"><div class="mhgl-logo-close imagebj" onclick="szlj(this)"></div>' +
				' <input type="text" class="input-text" value="" maxlength="20"/> <p class="mhgl-icon-box"> <i class="mhgl-icon mhgl-qy" onclick="beforeImage(this)"></i><i class="mhgl-icon mhgl-hy" onclick="afterImage(this)"></i><i class="mhgl-icon mhgl-sc" id="' + file.id + '" onclick="deleteImage(this)"></i></li>');
			num++;
		} else if(elementId.id == "#logo_picker") {
			$('.mhgl-logo').removeClass("none");
			$('.mhgl-logo').html('<input type="hidden" id="' + file.id + '" ljm="' + file.name + '"  mtlx="' + file.type + '" wjgs="' + file.ext + '" fjdx="' + file.size + '" fjdz="' + respones.object + '"><img id="logoImage" src="' + Constants.manageFileAddress + respones.object + '" style="width:100%;height:100%;"><div class="mhgl-logo-close" id="' + file.id + '" onclick="deletelogo(this)"></div>');
		} else {
			$('.mhgl-content').removeClass("none");
			$('.mhgl-content').html('<input type="hidden" id="' + file.id + '" ljm="' + file.name + '"  mtlx="' + file.type + '" wjgs="' + file.ext + '" fjdx="' + file.size + '" fjdz="' + respones.object + '"><img id="ewmImage" src="' + Constants.manageFileAddress + respones.object + '" ><div class="mhgl-logo-close" id="' + file.id + '" onclick="deleteewm(this)"></div>');
		}
	});

	uploader.on('uploadError', function(file) {
		alert("上传失败", null, false);
	});
	//上传完成
	uploader.on('uploadComplete', function(file, respones) {
		//console.log(uploader.getFile(file.id));
	});
	return uploader;
}
//设置链接
function szlj(obj) {
	var tzdz = $(obj).attr("tzdz");
	if(typeof tzdz != 'undefined' && tzdz != '') {
		store.set("bjtzdz", tzdz);
	} else {
		store.set("bjtzdz", "");
	}

	var opts = {
		content: "wzgl/szlj.html",
		scroll: false,
		title: '设置跳转链接',
		type: 2,
		width: '500px',
		height: '200px',
		shadeClose: false,
		end: function() {
			//保存按钮,回调刷新列表
			var ljxx = store.get("szlj");
			var bcflag = store.get("bcflag");
			if(bcflag == "1") {
				if(typeof ljxx != 'undefined' && ljxx != '') {
					$(obj).attr("tzdz", ljxx);
				} else {
					$(obj).attr("tzdz", "");
				}
			}

		}
	}
	dialog(opts);
}

//删除二维码
function deleteewm(obj) {
	try {
		ewm_uploader.removeFile(ewm_uploader.getFile($(obj).attr("id"))); //不要遗漏
	} catch(e) {
		// TODO
	}
	var li_element = $(obj).parent();
	li_element.html("");
	$('.mhgl-content').addClass("none");

}
//删除logo
function deletelogo(obj) {
	try {
		logo_uploader.removeFile(logo_uploader.getFile($(obj).attr("id"))); //不要遗漏
	} catch(e) {
		// TODO
	}
	var li_element = $(obj).parent();
	li_element.html("");
	$('.mhgl-logo').addClass("none");
}
//删除banner
function deleteImage(obj) {
	try {
		banner_uploader.removeFile(banner_uploader.getFile($(obj).attr("id"))); //不要遗漏
	} catch(e) {
		// TODO
	}
	var li_element = $(obj).parent().parent();
	li_element.remove();
	num--;
}

//前移
function beforeImage(obj) {
	var li_element = $(obj).parent().parent();
	var position = li_element.index();
	var size = $('.mhgl-banner-list').children('li').length;
	if(position == 0) {
		//已经是最前位置了
		return;
	} else {
		//获取当前位置的html
		var currentHtml = $('.mhgl-banner-list').children('li').eq(position).html();
		//获取上一个位置的html;
		var lastHtml = $('.mhgl-banner-list').children('li').eq(position - 1).html();
		//交换
		$('.mhgl-banner-list').children('li').eq(position).html(lastHtml);
		$('.mhgl-banner-list').children('li').eq(position - 1).html(currentHtml);
	}
}
//后退
function afterImage(obj) {
	var li_element = $(obj).parent().parent();
	var position = li_element.index();
	var size = $('.mhgl-banner-list').children('li').length;
	if(size == position) {
		//已经是最后位置了
		return;
	} else {
		//获取当前位置的html
		var currentHtml = $('.mhgl-banner-list').children('li').eq(position).html();
		//获取下一个位置的html;
		var nextHtml = $('.mhgl-banner-list').children('li').eq(position + 1).html();
		//交换
		$('.mhgl-banner-list').children('li').eq(position).html(nextHtml);
		$('.mhgl-banner-list').children('li').eq(position + 1).html(currentHtml);
	}
}
// 回显数据
function renderVrsy() {
	$.ajax({
		url: Constants.ctrlAddress + "syxx/getSyContent",
		type: "get",
		dataType: "json",
		contentType: "application/json",
		success: function(data) {
			// LOGO
			var logoFjxx = data.logoFjxx;
			if(logoFjxx) {
				$('.mhgl-logo').removeClass("none");
				$('.mhgl-logo').html('<input type="hidden" id="' + logoFjxx.id + '" ljm="' + logoFjxx.ljm + '"  mtlx="' + logoFjxx.mtlx + '" wjgs="' + logoFjxx.wjgs + '" fjdx="' + logoFjxx.fjdx + '" fjdz="' + logoFjxx.fjdz + '"><img id="logoImage" src="' + Constants.manageFileAddress + logoFjxx.fjdz + '" style="width:100%;height:100%;"><div class="mhgl-logo-close" id="' + logoFjxx.id + '" onclick="deletelogo(this)"></div>');
			}
			// Banner
			var bannerList = data.bannerList;
			if(bannerList && bannerList.length > 0) {
				num = bannerList.length;
				for(var i = 0; i < bannerList.length; i++) {
					var id = bannerList[i].id;
					var bt = bannerList[i].bt;
					var ljm = bannerList[i].ljm;
					var mtlx = bannerList[i].mtlx;
					var wjgs = bannerList[i].wjgs;
					var fjdx = bannerList[i].fjdx;
					var fjdz = bannerList[i].fjdz;
					$('.mhgl-banner-list').append('<li class="li_banner"><input type="hidden" id="' + id + '" ljm="' + ljm + '"  mtlx="' + mtlx + '" wjgs="' + wjgs + '" fjdx="' + fjdx + '" fjdz="' + fjdz + '"><img src="' + Constants.manageFileAddress + fjdz + '" style="width:226px;height:110px;"><div class="mhgl-logo-close imagebj"  onclick="szlj(this)" tzdz="' +  (bannerList[i].tzdz==null?"":bannerList[i].tzdz)  + '"></div>' +
						' <input type="text" class="input-text" value="' + bt + '" maxlength="20"/> <p class="mhgl-icon-box"> <i class="mhgl-icon mhgl-qy" onclick="beforeImage(this)"></i><i class="mhgl-icon mhgl-hy" onclick="afterImage(this)"></i><i class="mhgl-icon mhgl-sc" id="' + id + '" onclick="deleteImage(this)"></i></li>');
				}
			}
			// 联系电话
			$("#lxdh").val(data.lxfs);
			// 二维码
			var ewmFjxx = data.ewmFjxx;
			if(ewmFjxx) {
				$('.mhgl-content').removeClass("none");
				$('.mhgl-content').html('<input type="hidden" id="' + ewmFjxx.id + '" ljm="' + ewmFjxx.ljm + '"  mtlx="' + ewmFjxx.mtlx + '" wjgs="' + ewmFjxx.wjgs + '" fjdx="' + ewmFjxx.fjdx + '" fjdz="' + ewmFjxx.fjdz + '"><img id="ewmImage" src="' + Constants.manageFileAddress + ewmFjxx.fjdz + '" ><div class="mhgl-logo-close" id="' + ewmFjxx.id + '" onclick="deleteewm(this)"></div>');
			}

			//显示三张图片
			var threeImages = data.threeList;
			if(typeof threeImages != 'undefined' && threeImages.length > 0) {
				$('.mhgl-three-list').children("li").each(function() {
					var index = $(this).index();
					$(this).find(".mhgl-logo-close").attr("tzdz", threeImages[index].tzdz==null?"":threeImages[index].tzdz);
				});
			}

		}
	});
}

//保存方法
function save(obj) {
	var imagelogo = $('.mhgl-logo').children().size();
	if(imagelogo == 0) {
		alert("请上传logo图片！");
		return;
	}
	if($("#lxdh").val().trim() == "") {
		alert("请输入联系电话！");
		return;
	}
	var ewmimag = $('.mhgl-content').children().size();
	if(ewmimag == 0) {
		alert("请上传二维码图片！");
		return;
	}

	var param = {};
	//遍历获取附件列表
	var bannerList = new Array();
	$('.mhgl-banner-list').children('li').each(function() {
		var temp = {};
		temp.xssx = $(this).index();
		var title = $(this).find("input[type='text']").val();
		temp.title = title;
		var fjxx = $(this).find("input[type='hidden']");
		temp.ljm = fjxx.attr("ljm");
		temp.mtlx = fjxx.attr("mtlx");
		temp.wjgs = fjxx.attr("wjgs");
		temp.fjdx = fjxx.attr("fjdx");
		temp.fjdz = fjxx.attr("fjdz");
		var tzdz = $(this).find(".mhgl-logo-close").attr("tzdz");
		if(typeof tzdz != 'undefined' && tzdz != '') {
			temp.tzdz = tzdz;
		} else {
			temp.tzdz = "";
		}

		bannerList.push(temp);
	});
	var flag = false;
	for(var i = 0; i < bannerList.length; i++) {
		var title = bannerList[i].title;
		if(title && title.length > 20) {
			flag = true;
			break;
		}
	}
	if(flag) {
		alert("banner标题字数不能超过20个字");
		return;
	}
	param.bannerList = bannerList;
	//获取logo附件信息
	var fjxxLogo = {};
	var logo_fjxx = $(".mhgl-logo").find("input[type='hidden']");
	fjxxLogo.ljm = logo_fjxx.attr("ljm");
	fjxxLogo.mtlx = logo_fjxx.attr("mtlx");
	fjxxLogo.wjgs = logo_fjxx.attr("wjgs");
	fjxxLogo.fjdx = logo_fjxx.attr("fjdx");
	fjxxLogo.fjdz = logo_fjxx.attr("fjdz");
	param.logoFjxx = fjxxLogo;
	//获取二维码附件信息
	var ewmLogo = {};
	var ewm_fjxx = $(".mhgl-content").find("input[type='hidden']");
	ewmLogo.ljm = ewm_fjxx.attr("ljm");
	ewmLogo.mtlx = ewm_fjxx.attr("mtlx");
	ewmLogo.wjgs = ewm_fjxx.attr("wjgs");
	ewmLogo.fjdx = ewm_fjxx.attr("fjdx");
	ewmLogo.fjdz = ewm_fjxx.attr("fjdz");
	param.ewmFjxx = ewmLogo;
	//获取联系电话
	param.lxdh = $("#lxdh").val();

	//获取三张图片信息
	var threeList = new Array();
	$('.mhgl-three-list').children("li").each(function() {
		var temp = {};
		temp.title = $(this).attr("data-name");
		temp.xssx = $(this).index();
		var tzdz = $(this).find(".mhgl-logo-close").attr("tzdz");
		if(typeof tzdz != 'undefined' && tzdz != '') {
			temp.tzdz = tzdz;
		} else {
			temp.tzdz = "";
		}
		threeList.push(temp);
	});
	param.threeList = threeList;
	$.sdAjax({
		url: Constants.ctrlAddress + 'syxx/addSyxx',
		type: 'POST',
		data: param,
		async: false, // 是否异步
		waitFlag: true, // 是否需要加载等待动画
		successCallback: function(data) {
			if(data && data.result) {
				success("保存成功！");
			} else {
				error("保存失败！");
			}

		}
	})

}