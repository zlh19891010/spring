/**
 * 存放常量
 */
var Constants = window.Constants || {}; //常量

//排除URL
Constants.excludeUrl = [ Constants.appAddress+ "page/login.html"];

Constants.url = location.href.substring(0, location.href.indexOf(".html") + 5);

//管理中心后台接口地址
Constants.ctrlAddress = "http://localhost:8080/am/";


//运营中心后台接口地址
Constants.opAddress = "http://localhost:8080/vr-operation/op/";


//管理中心前端地址
Constants.appAddress = "http://localhost:8080/";
//运营中心前端地址
Constants.appOpAddress = "http://localhost:8080/";
// Ueditor常量地址
Constants.ueditor = {};
Constants.ueditor.prefix = "http://localhost/vr-manage/components/ueditor/";
Constants.ueditor.configJs = Constants.ueditor.prefix + "ueditor.config.js";
Constants.ueditor.allJs = Constants.ueditor.prefix + "ueditor.all.min.js";
//管理平台的文件访问地址
Constants.manageFileAddress =Constants.ctrlAddress+"getFile/";
//运营中心的文件访问地址
Constants.opFileAddress=Constants.opAddress+"getFile/"

Constants.flxx = ["", "所属行业：", "所属专业：", "内容级别："];

//内容状态 正式
Constants.nrztZS = "02";
//内容状态 草稿
Constants.nrztCG = "01";
//只是为了区别新增页面中的上架按钮，其他没意义
Constants.nrztSJ = "03";
//在库状态 在库中
Constants.zkztIng = "01";
//在库状态 上架
Constants.zkztSj = "02";
//在库状态 下架
Constants.zkztXj = "03";
//下拉第一属性
Constants.flsx = "01";
//操作级别 01 可读 	02 可写
Constants.czjb_kd = "01"
Constants.czjb_kx = "02"

//用户状态 未启用
Constants.ztwqy = "01";
//用户状态 已启用
Constants.ztyqy = "02";
//用户状态 已停用
Constants.ztyty = "03";
//是否在线  在线
Constants.sfzx_zx = "1";
//是否在线  不在线
Constants.sfzx_bzx = "0";

//帐号状态 正常
Constants.zhzt_zc = "0";
//帐号状态 禁止
Constants.zhzt_jz = "1";

//性别 男
Constants.xb_m = "1";
//性别 女
Constants.xb_w = "2";

//角色归属  平台
Constants.jsgspt = "01";
//角色归属 运营
Constants.jsgsyy = "02";

//上传图片文件格式
Constants.imgTypes = "image/jpg,image/jpeg,image/png,image/gif,image/bmp";
//视频上传文件格式
Constants.videoTypes = "mp4,rmvb,mkv,avi";
Constants.videoMimeTypes = ".mp4,.rmvb,.mkv,.avi";

//操作类型 新增
Constants.czlxAdd = "01";
//操作类型 删除
Constants.czlxDel = "02";
//操作类型 更新
Constants.czlxUpdate = "03";
//操作类型 查看
Constants.czlxQuery = "04";
//操作类型 登录
Constants.czlxLogin = "05";
//操作类型 协议
Constants.czlxXy = "06";
//获取播放进度的消息类型
Constants.GET_PLAY_PROGRESS=12;
//发布状态-草稿
Constants.ztcg = "01";
//发布状态-已发布
Constants.ztyfb = "02";
//发布状态-取消发布
Constants.ztqxfb = "03";
// 是否置顶-是
Constants.zds = "1";
// 是否置顶-否
Constants.zdf = "0";


Constants.Message=3;

Constants.Notice=2;

Constants.Kicking=4;

Constants.PointCard=1;

Constants.Lock=5;

Constants.UnLock=6;

Constants.Unbink=10;

//状态：正在处理
Constants.status_ing=1;
//状态：成功
Constants.status_success=0;
//状态：失败
Constants.status_failed=2;

//支付成功
Constants.success=1;

Constants.ADMIN="admin";

/**
 * 点卡余额不足
 */
Constants.NOT_ENOUGH="10010";

// 正则表达式
Constants.dfsUrlRegex = /http:\/\/localhost\/vr-manage\/am\/getFile\//g;