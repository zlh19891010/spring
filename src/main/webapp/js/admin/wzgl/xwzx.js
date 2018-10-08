/**
 * 新闻中心功能
 */
var ractive = new Ractive({
    el: '.xwzx-content',
    template: '#xwzx-template',
    oninit: function () {
        var _this = this;
        _this.on({
            tjxw: function () {
                window.location.href = "tjxw.html"
            }
        });
    },
    onrender: function() {
        searchXwList();
    }
});
/**
 * 编辑新闻
 * @param id
 */
function bjxw(id) {
    window.location.href = "bjxw.html?id=" + id;
}
/**
 * 发布新闻
 * @param id 新闻编号
 */
function fbxw(id) {
    confirm("确认发布吗？", function() {
        $.sdAjax({
            url: Constants.ctrlAddress + "xwxx/updateXwxxWithFbZt?id=" + id,
            successCallback: function (data) {
                if (typeof data == "object" && data.result == true) {
                    searchXwList();
                }
            }
        });
    });
}

/**
 * 取消发布新闻
 * @param id 新闻编号
 */
function qxfbxw(id) {
    confirm("确认取消发布吗？", function() {
        $.sdAjax({
            url: Constants.ctrlAddress + "xwxx/updateXwxxWithQxfbZt?id=" + id,
            successCallback: function (data) {
                if (typeof data == "object" && data.result == true) {
                    searchXwList();
                }
            }
        });
    });
}

/**
 * 置顶新闻
 * @param id 新闻编号
 */
function zdxw(id) {
    $.sdAjax({
        url: Constants.ctrlAddress + "xwxx/updateXwxxWithZd?id=" + id,
        successCallback: function (data) {
            if (typeof data == "object" && data.result == true) {
                searchXwList();
            }
        }
    });
}

/**
 * 删除新闻
 * @param id 新闻编号
 */
function scxw(id) {
    confirm("确认删除吗？", function() {
        $.sdAjax({
            url: Constants.ctrlAddress + "xwxx/deleteXwxx?id=" + id,
            successCallback: function (data) {
                if (typeof data == "object" && data.result == true) {
                    searchXwList();
                }
            }
        });
    });
}

/**
 * 获取新闻列表
 */
function searchXwList() {
    $('#customtable').sdGrid({
        url: Constants.ctrlAddress + "xwxx/getXwxxPagination",
        type: 'POST',
        data: {},
        async: true,
        waitFlag: true, // true：加载数据时显示等待动画， false：不显示加载动画。
        callback: function () {
            // TODO
        },
        colProperty: [{
            colLabel: '新闻标题',
            style: {
                textAlign: 'center',
                //width: '130px',
            },
            colModel: {
                style: {
                    textAlign: 'center',
                    ellipsis: true
                },
                innerHtml: function (data) {
                    return '<span title="' + data.bt + '">' + data.bt + '</span>';
                }
            }
        }, {
            colLabel: '发布时间',
            style: {
                textAlign: 'center'
            },
            colModel: {
                style: {
                    textAlign: 'center',
                    ellipsis: true
                },
                innerHtml: function (data) {
                    if (data.zt == Constants.ztyfb && data.fbsj) {
                        var convetFbsj = moment(data.fbsj).format("YYYY-MM-DD HH:mm:ss");
                        return '<span title="' + convetFbsj + '">' + convetFbsj + '</span>';
                    } else {
                        return "";
                    }
                }
            }
        }, {
            colLabel: '状态',
            style: {
                textAlign: 'center',
                width: '120px',
            },
            colModel: {
                style: {
                    textAlign: 'center',
                    ellipsis: true
                },
                innerHtml: function (data) {
                    return '<span title="' + data.ztDesc + '">' + data.ztDesc + '</span>';
                }
            }
        }, /*{// 临时注释===>勿删
            colLabel: '是否置顶',
            style: {
                textAlign: 'center',
                width: '100px',
            },
            colModel: {
                style: {
                    textAlign: 'center',
                    ellipsis: true
                },
                innerHtml: function (data) {
                    return data.zd ? '<span title="' + data.zdDesc + '">' + data.zdDesc + '</span>' : '';
                }
            }
        },*/ {
            colLabel: '操作',
            style: {
                textAlign: 'center',
            },
            colModel: {
                style: {
                    textAlign: 'left',
                    ellipsis: true
                },
                innerHtml: function (data) {
                    var str = '';
                    if (data.zt == Constants.ztcg || data.zt == Constants.ztqxfb) {
                        str += '<span class="edit-icon" onclick = "bjxw(\'' + data.id + '\');">编辑</span>';
                        str += '<span class="fb_icon" onclick = "fbxw(\'' + data.id + '\');">发布</span>';
                    } else {
                        str = '<span class="qx_icon" onclick = "qxfbxw(\'' + data.id + '\');">取消发布</span>'
                    }
                    // 临时注释===>勿删
                    /*if (data.zd != Constants.zds) {
                        str += '<span class="zd_icon" onclick = "zdxw(\'' + data.id + '\');">置顶</span>';
                    }*/
                    str += '<span class="delete-icon" onclick = "scxw(\'' + data.id + '\');">删除</span>';
                    return str;
                }
            }
        }],
        pagination: { // 设置是否显示分页，默认显示分页部分。  show:true 显示分页， show:false 不显示分页
            show: true,
            pageSize: 10 // 可选，默认10条
        }
    });
}