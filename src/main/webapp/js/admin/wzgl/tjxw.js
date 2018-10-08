/**
 * Created by yaojie on 2017/4/12.
 */
var ractive = new Ractive({
    el: ".tjxw-content",
    template: "#tjxw-template",
    oninit: function () {
        var _this = this;
        // 初始化富文本编辑器
        var ue;
        _this.set("ueditorDom", "<script id='xwxq' name='xwxq' type='text/plain'></script>");
        var toolbars = [['bold', 'italic', 'underline', 'fontborder', 'strikethrough', '|', 'forecolor', '|', 'fontfamily', 'fontsize', '|',
            'justifyleft', 'justifycenter', 'justifyright', 'justifyjustify', 'simpleupload']];
        dynamicLoadScript(function() {
            ue = UE.getEditor('xwxq', {
                toolbars: toolbars,
                initialFrameHeight: 400,
                initialFrameWidth: 1080,
                autoHeightEnabled: false,
                autoFloatEnabled: false
            });
        });
        // 初始化绑定事件
        _this.on({
            backXwList: function () {
                window.location.href = "xwzx.html";
            },
            save: function(event) {
                var xwxx = _this.get("xwxx");
                xwxx.xwxq = ue.getContent();
                xwxx.isFb = $(event.node).attr("sign");
                if (xwxx.zd.length > 0) {
                    xwxx.zd = xwxx.zd[0];
                } else {
                    xwxx.zd = "";
                }
                if (xwxx.bt == null || xwxx.bt == "") {
                    alert("标题不能为空");
                    return;
                } else {
                    if (xwxx.bt.length > 25) {
                        alert("标题字数不能超过25个字");
                        return;
                    }
                }
                if (xwxx.zy == null || xwxx.zy == "") {
                    alert("摘要不能为空");
                    return;
                } else {
                    if (xwxx.zy.length > 100) {
                        alert("摘要字数不能超过100个字");
                        return;
                    }
                }
                if (xwxx.fjdz == null || xwxx.fjdz == "") {
                    alert("新闻主图不能为空");
                    return;
                }
                if (xwxx.xwxq == null || xwxx.xwxq == "") {
                    alert("新闻详情不能为空");
                    return;
                } else {
                    if (xwxx.xwxq.length > 100000) {
                        alert("新闻详情字数超出最大长度");
                        return;
                    }
                }
                xwxx.xwxq = xwxx.xwxq.replace(Constants.dfsUrlRegex, "");
                $.sdAjax({
                    url: Constants.ctrlAddress + "xwxx/insertXwxx",
                    type: "post",
                    data: xwxx,
                    successCallback: function (data) {
                        if (typeof data == "object" && data.result == true) {
                            window.location.href = "xwzx.html";
                        }
                    }
                });
            },
            cancel: function() {
                window.location.href = "xwzx.html";
            }
        });
    },
    onrender: function() {
        var _this = this;
        // 初始化隐藏图片区域
        $("#xwzt").hide();
        // 初始化图片上传
        initUpload(function(file, respones) {
            _this.set("fileAddress", Constants.manageFileAddress);
            _this.set("xwxx.fjdz", respones.object);
            _this.set("xwxx.fjdx", file.size);
            _this.set("xwxx.wjgs", file.ext);
            _this.set("xwxx.ljm", file.name);
            _this.set("xwxx.mtlx", file.type);
            $("#xwzt").show();
        });
    }
});

function initUpload(fn) {
    var allMaxSize = 2;
    var uploader = WebUploader.create({
        // swf文件路径
        swf: '../../components/webuploader-0.1.5/Uploader.swf',
        // 文件接收服务端。
        server: Constants.ctrlAddress + 'file/uploadImage',
        // 选择文件的按钮。可选。
        // 内部根据当前运行是创建，可能是input元素，也可能是flash.
        pick: '#picker',
        //选择文件后直接上传
        fileSingleSizeLimit: allMaxSize * 1024 * 1024,
        auto: true,
        threads: 1,
        duplicate: false,
        formData: {
            guid: WebUploader.Base.guid()
        },
        //开启分片上传
        chunked: false,
        chunkSize: 5 * 1024 * 1024,
        timeout: 0,
        accept: {
            title: 'Images',
            extensions: 'jpg,jpeg,bmp,png',
            mimeTypes: Constants.imgTypes,
        },
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
        if (typeof fn == "function") {
            fn.call(this, file, respones);
        }
    });

    uploader.on('uploadError', function (file) {
        $('#' + file.id).find('p.state').text('上传出错');
    });
    //上传完成
    uploader.on('uploadComplete', function (file, respones) {
    });
    // 判断文件出错
    uploader.onError = function (code) {
        var msg;
        switch (code) {
            case 'Q_EXCEED_SIZE_LIMIT':
                msg = '上传文件大小过大';
                break;
            case 'Q_TYPE_DENIED':
                msg = '文件类型上传错误';
                break;
            case 'F_DUPLICATE':
                msg = '';
                break;
        }
        if (msg) {
            alert(msg);
        }
    };
}
