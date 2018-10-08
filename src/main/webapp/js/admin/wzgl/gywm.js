/**
 * Created by yaojie on 2017/4/12.
 */
Array.prototype.remove = function(dx) {
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
/**
 * 唯一性id
 * @returns {string}
 */
function getUuid() {
    var d = new Date().getTime();
    var uuid = 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
        var r = (d + Math.random()*16)%16 | 0;
        d = Math.floor(d/16);
        return (c=='x' ? r : (r&0x3|0x8)).toString(16);
    });
    return uuid;
}
var uuid = getUuid();
var banner_uploader = "banner_uploader";
var khxx_uploader = "khxx_uploader";
var ue;
$.sdAjax({
    url: Constants.ctrlAddress + "gywm/getGywmxx",
    successCallback: function (data) {
        var index = 0;
        if (data.khxxList.length > 0) {
            index = data.khxxList.length - 1;
        }
        // 初始化客户列表(当客户列表没有数据时)
        var _initList = [{
            khxx: {
                uuid: uuid,
                index: index,
                tx: "",
                mc: "",
                khjj: ""
            },
            fjxx: {
                fjdz: "",
                fjdx: "",
                wjgs: "",
                ljm: "",
                mtlx: ""
            }
        }];
        // 如果已保存过客户列表，则重新封装回显
        if (data.khxxList.length > 0) {
            for (var i = 0; i < data.khxxList.length; i++) {
                var _obj = data.khxxList[i];
                var _khxx = {
                    //id: _obj.khId,
                    tx: _obj.tx,
                    mc: _obj.mc,
                    khjj: _obj.khjj,
                    index: i,
                    uuid: getUuid()
                };
                var _fjxx = {
                    //id: _obj.fjId,
                    fjdz: _obj.fjdz,
                    fjdx: _obj.fjdx,
                    wjgs: _obj.wjgs,
                    ljm: _obj.ljm,
                    mtlx: _obj.mtlx
                }
                data.khxxList[i].khxx = _khxx;
                data.khxxList[i].fjxx = _fjxx;
            }
            _initList = data.khxxList;
        }
        // 关于我们-模板对象初始化
        var ractive = new Ractive({
            el: ".gywm-content",
            template: "#gywm-template",
            data: {
                fileAddress: Constants.manageFileAddress,
                gywm: {
                    khxxList: _initList
                }
            },
            oninit: function () {
                var _this = this;
                // 页面回显
                // 1、附件信息
                if (data.fjxx) {
                    //_this.set("gywm.fjxx.id", data.fjxx.id);
                    _this.set("gywm.fjxx.fjdz", data.fjxx.fjdz);
                    _this.set("gywm.fjxx.fjdx", data.fjxx.fjdx);
                    _this.set("gywm.fjxx.wjgs", data.fjxx.wjgs);
                    _this.set("gywm.fjxx.ljm", data.fjxx.ljm);
                    _this.set("gywm.fjxx.mtlx", data.fjxx.mtlx);
                }
                // 2、公司介绍
                if (data.gywm) {
                    //_this.set("gywm.gywm.id", data.gywm.id);
                    _this.set("gywm.gywm.banner", data.gywm.banner);
                    _this.set("gywm.gywm.jsxx", data.gywm.jsxx);
                }
                // 初始化绑定事件
                _this.on({
                    addKhxx: function() {
                        var _this = this;
                        var khxxList = _this.get("gywm.khxxList");
                        index++;
                        uuid = getUuid();
                        var khxxModel = {
                            khxx: {
                                tx: "",
                                mc: "",
                                khjj: "",
                                index: index,
                                uuid: uuid
                            },
                            fjxx: {
                                fjdz: "",
                                fjdx: "",
                                wjgs: "",
                                ljm: "",
                                mtlx: ""
                            }
                        }
                        khxxList.push(khxxModel);
                        _this.set("gywm.khxxList", khxxList);
                        // 初始化头像图片上传
                        khxx_uploader = initUpload("#picker", uuid, khxx_uploader, function(file, respones, num) {
                            var _khxxList = _this.get("gywm.khxxList");
                            for (var i = 0; i < _khxxList.length; i++) {
                                if (_khxxList[i].khxx.uuid == num) {
                                    _this.set("gywm.khxxList[" + i + "].khxx.tx", respones.object);
                                    _this.set("gywm.khxxList[" + i + "].fjxx.fjdz", respones.object);
                                    _this.set("gywm.khxxList[" + i + "].fjxx.fjdx", file.size);
                                    _this.set("gywm.khxxList[" + i + "].fjxx.wjgs", file.ext);
                                    _this.set("gywm.khxxList[" + i + "].fjxx.ljm", file.name);
                                    _this.set("gywm.khxxList[" + i + "].fjxx.mtlx", file.type);
                                    break;
                                }
                            }
                            $("#" + num).show();
                            $("#picker" + num).removeClass("mhgl-uploader-box").addClass("mhgl-khtx-change");
                            $("#picker" + num + " .webuploader-pick").text("点击更换图片");
                        });
                    },
                    delKhxx: function(event) {
                        var _this = this;
                        if (index > 0) {
                            var khxxList = event.get("gywm.khxxList");
                            var _index = $(event.node).attr("index");
                            khxxList.remove(_index);
                            _this.set("gywm.khxxList", khxxList);
                            for (var o = 0; o < khxxList.length; o++) {
                                var _uuid = khxxList[o].khxx.uuid;
                                // 初始化头像图片上传
                                khxx_uploader = initUpload("#picker", _uuid, khxx_uploader, function(file, respones, num) {
                                    var _kList = _this.get("gywm.khxxList");
                                    for (var i = 0; i < _kList.length; i++) {
                                        if (_kList[i].khxx.uuid == num) {
                                            _this.set("gywm.khxxList[" + i + "].khxx.tx", respones.object);
                                            _this.set("gywm.khxxList[" + i + "].fjxx.fjdz", respones.object);
                                            _this.set("gywm.khxxList[" + i + "].fjxx.fjdx", file.size);
                                            _this.set("gywm.khxxList[" + i + "].fjxx.wjgs", file.ext);
                                            _this.set("gywm.khxxList[" + i + "].fjxx.ljm", file.name);
                                            _this.set("gywm.khxxList[" + i + "].fjxx.mtlx", file.type);
                                            break;
                                        }
                                    }
                                    $("#" + num).show();
                                    $("#picker" + num).removeClass("mhgl-uploader-box").addClass("mhgl-khtx-change");
                                    $("#picker" + num + " .webuploader-pick").text("点击更换图片");
                                });
                                if (khxxList[o].khxx.tx != '' && khxxList[o].khxx.tx != null) {
                                    $("#" + _uuid).show();
                                    $("#picker" + _uuid).removeClass("mhgl-uploader-box").addClass("mhgl-khtx-change");
                                    $("#picker" + _uuid + " .webuploader-pick").text("点击更换图片");
                                } else {
                                    $("#" + _uuid).hide();
                                    $("#picker" + _uuid).removeClass("mhgl-khtx-change").addClass("mhgl-uploader-box");
                                    $("#picker" + _uuid + " .webuploader-pick").text("点击上传图片");
                                }
                            }
                            index--;
                        }
                    },
                    save: function () {
                        var _this = this;
                        var temp = _this.get("gywm");
                        // 关于我们基本信息
                        var _gywm = {
                            id: temp.gywm.id,
                            banner: temp.gywm.banner,
                            jsxx: ue.getContent()
                        }
                        // 附件信息
                        var _fjxx = {
                            //id: temp.fjxx.id,
                            fjdz: temp.fjxx.fjdz,
                            fjdx: temp.fjxx.fjdx,
                            wjgs: temp.fjxx.wjgs,
                            ljm: temp.fjxx.ljm,
                            mtlx: temp.fjxx.mtlx
                        }
                        var _khList = [];
                        var flag = false;
                        var khmcFlag = false;
                        for (var i = 0; i < temp.khxxList.length; i++) {
                            temp.khxxList[i].fjxx.px = (i + 1);
                            var _khxxModel = {
                                khxx: {
                                    //id: temp.khxxList[i].khxx.id,
                                    tx: temp.khxxList[i].khxx.tx,
                                    mc: temp.khxxList[i].khxx.mc,
                                    khjj: temp.khxxList[i].khxx.khjj
                                },
                                fjxx: temp.khxxList[i].fjxx
                            }
                            if (_khxxModel.khxx.mc && _khxxModel.khxx.mc.length > 16) {
                                khmcFlag = true;
                                break;
                            }
                            if (_khxxModel.khxx.khjj && _khxxModel.khxx.khjj.length > 100) {
                                flag = true;
                                break;
                            }
                            _khList.push(_khxxModel);
                        }
                        // 保存前校验
                        // 1、Banner主图非空校验
                        if (!_gywm.banner) {
                            alert("Banner主图不能为空");
                            return;
                        }
                        // 2、公司信息非空校验
                        if (!_gywm.jsxx) {
                            alert("公司信息不能为空");
                            return;
                        } else {
                            if (_gywm.jsxx.length > 100000) {
                                alert("公司信息字数超出最大长度");
                                return;
                            }
                        }
                        // 3、客户信息列表完整性校验
                        if (_khList.length > 0) {
                            for (var j = 0; j < _khList.length; j++ ) {
                                if ((_khList[j].khxx.tx == '' || _khList[j].khxx.tx == null)
                                    || (_khList[j].khxx.mc == '' || _khList[j].khxx.mc == null)) {
                                    alert("请将客户信息填写完整");
                                    return;
                                }
                            }
                        } else {
                            alert("请将客户信息填写完整");
                            return;
                        }
                        // 4、客户信息列表介绍信息字数校验
                        if (khmcFlag) {
                            alert("客户信息中的客户名称字数不能超过16个字");
                            return;
                        }
                        if (flag) {
                            alert("客户信息中的客户简介字数不能超过100个字");
                            return;
                        }
                        _gywm.jsxx = _gywm.jsxx.replace(Constants.dfsUrlRegex, "");
                        var param = {
                            gywm: _gywm,
                            fjxx: _fjxx,
                            khxxList: _khList
                        }
                        $.sdAjax({
                            url: Constants.ctrlAddress + "gywm/addGywmxx",
                            type: "post",
                            data: param,
                            successCallback: function (data) {
                                if (typeof data == "object" && data.result == true) {
                                    success("保存成功");
                                }
                            }
                        });
                    }
                });
            },
            onrender: function () {
                var _this = this;
                // 初始化是否隐藏banner图片区域
                if (data.gywm && data.fjxx) {
                    $("#banner").show();
                } else {
                    $("#banner").hide();
                }
                // 初始化图片上传
                initUpload("#picker", "", banner_uploader, function (file, respones) {
                    _this.set("gywm.gywm.banner", respones.object);
                    _this.set("gywm.fjxx.fjdz", respones.object);
                    _this.set("gywm.fjxx.fjdx", file.size);
                    _this.set("gywm.fjxx.wjgs", file.ext);
                    _this.set("gywm.fjxx.ljm", file.name);
                    _this.set("gywm.fjxx.mtlx", file.type);
                    $("#banner").show();
                });
                // 处理富文本编辑器中的图片地址
                if (data.gywm && data.gywm.jsxx) {
                    data.gywm.jsxx = convertImgUrlByUEditor(data.gywm.jsxx);
                }
                // 初始化回显富文本编辑器
                _this.set("ueditorDom", "<script id='gywm' name='gywm' type='text/plain'></script>");
                var toolbars = [['bold', 'italic', 'underline', 'fontborder', 'strikethrough', '|', 'forecolor', 'backcolor', '|', 'fontfamily', 'fontsize', '|',
                    'justifyleft', 'justifycenter', 'justifyright', 'justifyjustify', 'simpleupload']];
                dynamicLoadScript(function() {
                    ue = UE.getEditor('gywm', {
                        initialContent: (data.gywm && data.gywm.jsxx) ? data.gywm.jsxx : "",
                        toolbars: toolbars,
                        initialFrameHeight: 500,
                        initialFrameWidth: 1080,
                        autoHeightEnabled: false,
                        autoFloatEnabled: false
                    });
                });
                // 渲染回显的客户信息列表
                for (var a = 0; a < _initList.length; a++) {
                    var _uuid = _initList[a].khxx.uuid;
                    // 循环初始化每一个回显客户的上传图片控件
                    khxx_uploader = initUpload("#picker", _uuid, khxx_uploader, function(file, respones, num) {
                        var _khList = _this.get("gywm.khxxList");
                        for (var i = 0; i < _khList.length; i++) {
                            if (_khList[i].khxx.uuid == num) {
                                _this.set("gywm.khxxList[" + i + "].khxx.tx", respones.object);
                                _this.set("gywm.khxxList[" + i + "].fjxx.fjdz", respones.object);
                                _this.set("gywm.khxxList[" + i + "].fjxx.fjdx", file.size);
                                _this.set("gywm.khxxList[" + i + "].fjxx.wjgs", file.ext);
                                _this.set("gywm.khxxList[" + i + "].fjxx.ljm", file.name);
                                _this.set("gywm.khxxList[" + i + "].fjxx.mtlx", file.type);
                                break;
                            }
                        }
                        $("#" + num).show();
                        $("#picker" + num).removeClass("mhgl-uploader-box").addClass("mhgl-khtx-change");
                        $("#picker" + num + " .webuploader-pick").text("点击更换图片");
                    });
                    if (data.khxxList.length > 0) {
                        $("#" + _uuid).show();
                        $("#picker" + _uuid).removeClass("mhgl-uploader-box").addClass("mhgl-khtx-change");
                        $("#picker" + _uuid + " .webuploader-pick").text("点击更换图片");
                    }
                }
            }
        });
    }
});

function initUpload(elementId, uuid, uploader, fn) {
    WebUploader.Uploader.register({
        "before-send-file": "beforeSendFile"
    }, {
        //时间点1：所有分块进行上传之前调用此函数
        beforeSendFile: function(file) {
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
        pick: elementId + uuid,
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
            fn.call(this, file, respones, uuid);
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