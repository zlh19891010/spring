/**
 * Created by yaojie on 2017/4/12.
 */
// 工具栏
var toolbars = [['bold', 'italic', 'underline', 'fontborder', 'strikethrough', '|', 'forecolor', 'backcolor', '|', 'fontfamily', 'fontsize', '|',
    'justifyleft', 'justifycenter', 'justifyright', 'justifyjustify']];
$(document).ready(function () {
    /**
     * 加入我们-模板对象初始化
     */
    ractive = new Ractive({
        el: ".jrwm-content",
        template: "#jrwm-template",
        data: {
            fileAddress: Constants.manageFileAddress
        },
        oninit: function () {
            var _this = this;
            // 初始化招聘信息
            $.sdAjax({
                url: Constants.ctrlAddress + "zpxx/getZpxx",
                successCallback: function (data) {
                    if (data.gsxx) {
                        _this.set("jrwm.gsxx", data.gsxx);
                        if (data.gsxx.banner) {
                            $("#banner").show();
                        }
                    }
                    // 初始化图片上传
                    initUpload(function (file, respones) {
                        _this.set("jrwm.fjdz", respones.object);
                        _this.set("jrwm.fjdx", file.size);
                        _this.set("jrwm.wjgs", file.ext);
                        _this.set("jrwm.ljm", file.name);
                        _this.set("jrwm.mtlx", file.type);
                        _this.set("jrwm.gsxx.banner", respones.object);
                        $("#banner").show();
                    });
                    dynamicLoadScript(function () {
                        // 回显的数据
                        if (data.zpxxModuleList && data.zpxxModuleList.length > 0) {
                            for (var i = 0; i < data.zpxxModuleList.length; i++) {
                                var _uuid = getUuid();
                                var _html = renderZpxx(_uuid);
                                $("ul").append(_html);
                                $("#zwmc" + _uuid).val(data.zpxxModuleList[i].zwmc);
                                UE.getEditor('zwyq' + _uuid, {
                                    initialContent: data.zpxxModuleList[i].zwxx ? data.zpxxModuleList[i].zwxx : "",
                                    toolbars: toolbars,
                                    initialFrameHeight: 400,
                                    initialFrameWidth: 360,
                                    autoHeightEnabled: false,
                                    autoFloatEnabled: false
                                });
                            }
                        } else {
                            // 初始化招聘信息对象
                            var uuid = getUuid();
                            var html = renderZpxx(uuid);
                            $("ul").append(html);
                            UE.getEditor('zwyq' + uuid, {
                                toolbars: toolbars,
                                initialFrameHeight: 400,
                                initialFrameWidth: 360,
                                autoHeightEnabled: false,
                                autoFloatEnabled: false
                            });
                        }
                    });
                }
            });
        }
    });
});

/**
 * 唯一性id
 * @returns {string}
 */
function getUuid() {
    var d = new Date().getTime();
    var uuid = 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function (c) {
        var r = (d + Math.random() * 16) % 16 | 0;
        d = Math.floor(d / 16);
        return (c == 'x' ? r : (r & 0x3 | 0x8)).toString(16);
    });
    return uuid;
}
/**
 * 渲染招聘信息
 * @param uuid
 * @returns {string}
 */
function renderZpxx(uuid) {
    var _html = '<li id="' + uuid + '">';
    _html += '<div class="mhgl-khxx-box">';
    _html += '<dl class="form-dl">';
    _html += '<dt>职位名称</dt>';
    _html += '<dd><input id="zwmc' + uuid + '" type="text" class="input-text" /></dd>';
    _html += '<dt>职位要求</dt>';
    _html += '<dd><script id="zwyq' + uuid + '" type="text/plain"></script></dd>';
    _html += '</dl>';
    _html += '<a href="javascript:void(0)" class="mhgl-icon mhgl-sc3" onclick="delZpxx(\'' + uuid + '\')"></a>';
    _html += '</div>';
    _html += '</li>';
    return _html;
}

/**
 * 添加招聘信息
 */
function addZpxx() {
    var _uuid = getUuid();
    var _html = renderZpxx(_uuid);
    $("ul").append(_html);
    // 加载职位描述编辑器
    UE.getEditor('zwyq' + _uuid, {
        toolbars: toolbars,
        initialFrameHeight: 400,
        initialFrameWidth: 360,
        autoHeightEnabled: false,
        autoFloatEnabled: false
    });
    $("li").find("a").show();
}

/**
 * 删除招聘信息
 * @param uuid
 */
function delZpxx(uuid) {
    var len = $("li").length;
    if (len > 1) {
        $("#" + uuid).remove();
        if ((len - 1) == 1) {
            $("li").find("a").hide();
        }
    }
}

/**
 * 保存招聘信息
 */
function save() {
    var jrwm = ractive.get("jrwm");
    var zpxxList = [];
    var ids = $("li").each(function (index) {
        var _id = $(this).attr("id");
        var _zwmc = $("#zwmc" + _id).val();
        var _zwxx = UE.getEditor('zwyq' + _id).getContent();
        var _zpxx = {
            zwmc: _zwmc,
            zwxx: _zwxx,
            xssx: index + 1
        }
        zpxxList.push(_zpxx);
    });
    jrwm.zpxxModuleList = zpxxList;
    // 保存前校验
    var flag = false;
    var lenFlag = false;
    for (var i = 0; i < jrwm.zpxxModuleList.length; i++) {
        if (!jrwm.zpxxModuleList[i].zwmc) {
            flag = true;
            break;
        } else {
            if (jrwm.zpxxModuleList[i].zwmc.length > 40) {
                lenFlag = true;
                break;
            }
        }
        if (!jrwm.zpxxModuleList[i].zwxx) {
            flag = true;
            break;
        }
    }
    if (!jrwm.gsxx.banner) {
        alert("请上传banner图片");
        return;
    }
    if (jrwm.gsxx.mccn && jrwm.gsxx.mccn.length > 40) {
        alert("公司名称（中文）字数不能超过40个字");
        return;
    }
    if (jrwm.gsxx.dzcn && jrwm.gsxx.dzcn.length > 100) {
        alert("公司地址（中文）字数不能超过100个字");
        return;
    }
    if (jrwm.gsxx.zylj && jrwm.gsxx.zylj.length > 80) {
        alert("主页链接不能超过80个字符");
        return;
    }
    var pattern = /[\u4e00-\u9fa5]+/g;
    if (jrwm.gsxx.mcen) {
        var result = jrwm.gsxx.mcen.match(pattern);
        if (result && result.length > 0) {
            alert("公司名称（英文）不能输入中文");
            return;
        }
        if (jrwm.gsxx.mcen.length > 80) {
            alert("公司名称（英文）不能超过80个字符");
            return;
        }
    }
    if (jrwm.gsxx.dzen) {
        var result = jrwm.gsxx.dzen.match(pattern);
        if (result && result.length > 0) {
            alert("公司地址（英文）不能输入中文");
            return;
        }
        if (jrwm.gsxx.dzen.length > 100) {
            alert("公司地址（英文）不能超过100个字符");
            return;
        }
    }
    if (flag) {
        alert("请将招聘信息填写完整");
        return;
    }
    if (lenFlag) {
        alert("招聘信息中职位名称字数不能超过40个字");
        return;
    }
    $.sdAjax({
        url: Constants.ctrlAddress + "zpxx/addZpxx",
        type: "post",
        data: jrwm,
        successCallback: function (data) {
            if (data && data.result) {
                success("保存成功");
            } else {
                if (data.message) {
                    error(data.message);
                } else {
                    error("保存失败");
                }
            }
        }
    });
}

/**
 * 初始化上传图片控件
 * @param fn
 */
function initUpload(fn) {
    WebUploader.Uploader.register({
        "before-send-file": "beforeSendFile"
    }, {
        //时间点1：所有分块进行上传之前调用此函数
        beforeSendFile: function (file) {
            //$("#tj").attr("disabled", true);
        }
    });
    var allMaxSize = 2;
    uploader = WebUploader.create({
        // swf文件路径
        swf: '../../components/webuploader-0.1.5/Uploader.swf',
        // 文件接收服务端。
        server: Constants.ctrlAddress + 'file/uploadImage',
        // 选择文件的按钮。可选。
        // 内部根据当前运行是创建，可能是input元素，也可能是flash.
        pick: "#picker",
        //选择文件后直接上传
        fileSingleSizeLimit: allMaxSize * 1024 * 1024,
        auto: true,
        threads: 1,
        duplicate: true,
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
/**
 * 数组原型-删除方法
 * @param dx
 * @returns {boolean}
 */
Array.prototype.remove = function (dx) {
    if (isNaN(dx) || dx > this.length) {
        return false;
    }
    for (var i = 0, n = 0; i < this.length; i++) {
        if (this[i] != this[dx]) {
            this[n++] = this[i];
        }
    }
    this.length -= 1;
}