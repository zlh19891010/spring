/**
 * Created by yaojie on 2017/4/12.
 */
var uploader;
var num = 0;
$(document).ready(function () {
    uploadImage();
    // 初始化回显
    renderVrkc();
});

//------------------------------------------上传文件-------------------------------------------------

function uploadImage() {
    WebUploader.Uploader.register({
        "before-send-file": "beforeSendFile"
    }, {
        //时间点1：所有分块进行上传之前调用此函数
        beforeSendFile: function (file) {
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
        pick: '#picker',
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
        fileNumLimit: 5,
        fileSizeLimit: 200 * 1024 * 1024,    // 200 M
        fileSingleSizeLimit: 50 * 1024 * 1024,   // 50 M
        accept: {
            title: 'Images',
            extensions: 'gif,jpg,jpeg,bmp,png',
            mimeTypes: 'image/jpg,image/jpeg,image/png,image/gif,image/bmp'
        }
    });
    // 当文件被加入队列之前触发
    uploader.on('beforeFileQueued', function(file) {
        if (num == 5) {
            alert("最多上传5张图片！");
            return false;
        }
    });
    // 当有文件被添加进队列的时候
    uploader.on('fileQueued', function (file) {

    });
    // 文件上传过程中创建进度条实时显示。
    //上传中
    uploader.on('uploadProgress', function (file, percentage) {
    });
    //已上传
    uploader.on('uploadSuccess', function (file, respones) {
        $('.mhgl-banner-list').append('<li><input type="hidden" id="' + file.id + '" ljm="' + file.name + '"  mtlx="' + file.type + '" wjgs="' + file.ext + '" fjdx="' + file.size + '" fjdz="' + respones.object + '"><img src="' + Constants.manageFileAddress + respones.object + '" style="width:226px;height:110px;"> <input type="text" class="input-text" value="" maxlength="20"/> <p class="mhgl-icon-box"> <i class="mhgl-icon mhgl-qy" onclick="beforeImage(this)"></i><i class="mhgl-icon mhgl-hy" onclick="afterImage(this)"></i><i class="mhgl-icon mhgl-sc"  id="' + file.id + '" onclick="deleteImage(this)"></i></li>');
        num++;
    });

    uploader.on('uploadError', function (file) {
        alert("上传失败", null, false);
    });
    //上传完成
    uploader.on('uploadComplete', function (file, respones) {

    });
}
//删除图片
function deleteImage(obj) {
    try {
        uploader.removeFile(uploader.getFile($(obj).attr("id")));//不要遗漏
    } catch (e) {
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
    if (position == 0) {
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
    if (size == position) {
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
function renderVrkc() {
    $.ajax({
        url: Constants.ctrlAddress + "syxx/getVrkcContent",
        type: "get",
        dataType: "json",
        contentType: "application/json",
        success: function (data) {
            if (data) {
                num = data.length;
                for (var i = 0; i < data.length; i++) {
                    var id = data[i].id;
                    var bt = data[i].bt;
                    var ljm = data[i].ljm;
                    var mtlx = data[i].mtlx;
                    var wjgs = data[i].wjgs;
                    var fjdx = data[i].fjdx;
                    var fjdz = data[i].fjdz;
                    $('.mhgl-banner-list').append('<li><input type="hidden" id="' + id + '" ljm="' + ljm + '"  mtlx="' + mtlx + '" wjgs="' + wjgs + '" fjdx="' + fjdx + '" fjdz="' + fjdz + '"><img src="' + Constants.manageFileAddress + fjdz + '" style="width:226px;height:110px;"> <input type="text" class="input-text" value="' + bt + '" maxlength="20"/> <p class="mhgl-icon-box"> <i class="mhgl-icon mhgl-qy" onclick="beforeImage(this)"></i><i class="mhgl-icon mhgl-hy" onclick="afterImage(this)"></i><i class="mhgl-icon mhgl-sc"  id="' + id + '" onclick="deleteImage(this)"></i></li>');
                }

            }
        }
    });
}
//保存方法
function save(obj) {
    var size = $('.mhgl-banner-list').children('li').length;
    if (size == 0) {
        alert("请上传图片！");
        return;
    } else {
        //遍历获取附件列表
        var bannerList = new Array();
        $('.mhgl-banner-list').children('li').each(function () {
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
            bannerList.push(temp);
        });
        var flag = false;
        for (var i = 0; i < bannerList.length; i++) {
            var title = bannerList[i].title;
            if (title && title.length > 20) {
                flag = true;
                break;
            }
        }
        if (flag) {
            alert("banner标题字数不能超过20个字");
            return;
        }
        $.sdAjax({
            url: Constants.ctrlAddress + 'syxx/addVrkc',
            type: 'POST',
            data: {bannerList: bannerList},
            async: false, // 是否异步
            waitFlag: true, // 是否需要加载等待动画
            successCallback: function (data) {
                if (data && data.result) {
                    success("保存成功！");
                } else {
                    error("保存失败！");
                }
            }
        });
    }
}

