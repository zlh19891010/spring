/**
 * Project Name:admin
 * File Name:CodeConstants.java
 * Package Name:com.sd.vr.admin.common
 * Date:2017年3月1日上午10:43:18
 *
 */

package com.admin.common;

/**
 * ClassName:CodeConstants <br/>
 * Function: CODE基础数据. <br/>
 * Date: 2017年3月1日 上午10:43:18 <br/>
 *
 * @author tangli
 * @version
 * @since JDK 1.7
 * @see
 */
public final class CodeConstants {

	private CodeConstants() {
	}

	/**
	 * 角色归属(01管理平台，02运营中心)
	 */
	public static final String JSGS_GLPT = "01";

	/**
	 * 02运营中心
	 */
	public static final String JSGS_YYZX = "02";

	/**
	 * 01代表管理平台
	 */
	public static final String MOUDLE_XTDM_GLPT = "01";

	/**
	 * 02代表运营中心
	 */
	public static final String MOUDLE_XTDM_YYZX = "02";

	/**
	 * 03代表普通用户
	 */
	public static final String MOUDLE_XTDM_PTYH = "03";
	/**
	 * 超级管理员编号
	 */
	public static final String CJGLY_BH = "1";

	/**
	 * 管理员状态 01未启用
	 */
	public static final String GLYZT_WQY = "01";

	/**
	 * 管理员状态 02已启用
	 */
	public static final String GLYZT_YQY = "02";

	/**
	 * 管理员状态 03已停用
	 */
	public static final String GLYZT_YTY = "03";

	/**
	 * 用户状态 01未启用
	 */
	public static final String YHZT_WQY = "01";

	/**
	 * 用户状态 02已启用
	 */
	public static final String YHZT_YQY = "02";

	/**
	 * 用户状态 03已停用
	 */
	public static final String YHZT_YTY = "03";


	/**
	 * 角色归属
	 */
	public static final String JSGS = "JSGS";
	/**
	 * 角色状态
	 */
	public static final String JSZT = "JSZT";
	/**
	 * 启用状态
	 */
	public static final String QYZT = "QYZT";
	/** QYZT 启用状态 未启用 */
	public static final String QYZT_WQY = "01";
	/** QYZT 启用状态 已启用 */
	public static final String QYZT_YQY = "02";
	/** QYZT 启用状态 已停用 */
	public static final String QYZT_YTY = "03";

	/**
	 * 状态(1-正常，0-停用)
	 */
	public static final String STATUS = "STATUS";
	/** 正常 */
	public static final String STATUS_ZC = "1";
	/** 停用 */
	public static final String STATUS_TY = "0";
	/**
	 * 操作标识，新增
	 */
	public static final String DELFLAG_A = "A";
	/**
	 * 操作标识，修改
	 */
	public static final String DELFLAG_U = "U";
	/**
	 * 操作标识，删除
	 */
	public static final String DELFLAG_D = "D";

	/**
	 * 新增校验重复返回值
	 */
	public static final String ADD_JYCF = "-1";

	/**
	 * 日志操作类型01-新增
	 */
	public static final String LOG_ADD = "01";

	/**
	 * 日志操作类型 02-删除
	 */
	public static final String LOG_DELETE = "02";

	/**
	 * 日志操作类型 03-更新
	 */
	public static final String LOG_UPDATE = "03";

	/**
	 * 日志操作类型 04-查看
	 */
	public static final String LOG_FIND = "04";

	/**
	 * 日志操作类型 05 登录
	 */
	public static final String LOG_LOGIN = "05";
	/**
	 * 日志操作类型 06 协议
	 */
	public static final String LOG_XY = "06";

	/**
	 * 管理员用户session
	 */
	public static final String USER_MOUDLE = "USER_MOUDLE";


	/**
	 * 大分类-03 门户
	 */
	public static final String DFL_MH = "03";

	/**
	 * 小分类-06 加入我们
	 */
	public static final String XFL_JRWM = "06";

	/**
	 * 小分类-07 关于我们
	 */
	public static final String XFL_GYWM = "07";
	/**
	 * 管理员级别
	 */
	public static final String GLY_JB = "0";


	public static final int Message=3;

	public static final int Notice=2;

	public static final int Kicking=4;

	public static final int PointCard=1;

	public static final int Lock=5;

	public static final int UnLock=6;

	public static final int AUTHCODE=7;

	public static final int Recharge=8;

	public static final int User_Info=9;

	public static final int Unbink=10;


	public static final String TYPE="type";

	public static final String APPID="111111111";

	public static final String SECRET="222222222";

	/**
	 * 正在执行中...
	 */
	public static final String STATUS_ING="1";
	/**
	 * 执行成功
	 */
	public static final String STATUS_SUCCESS="0";
	/**
	 * 执行失败
	 */
	public static final String STATUS_FAILED="2";

	/**
	 * admin
	 */
	public static final String ADMIN="admin";

	/**
	 * 点卡余额不足
	 */
	public static final String NOT_ENOUGH="10010";

	/**
	 * WEB数据库
	 */
	public static final String BASE_DATA_SOURCE="baseDataSource";
	/**
	 * 游戏数据库
	 */
	public static final String GAME_DATA_SOURCE="gameDataSource";
	/**
	 * 返回OK
	 */
	public static final String OK="ok";

	/**
	 * 返回UNKOWN
	 */
	public static final String UNKOWN="unkown";

	/**
	 * 支付成功
	 */
	public static final String ORDER_SUCCESS="1";
	/**
	 * 转换的规则
	 */
	public static final Float ORDER_RULE=1f;

	/**
	 * 密匙
	 */
	public static final String PRIVATE_KEY="FA555AB40F152D1F035A6BDCF0EA94D3";

	/**
	 * 表示代理点卡
	 */
	public static final Integer STYLE_PROX=0;
	/**
	 * 表示充值点卡
	 */
	public static final Integer STYLE_RECHAGE=1;

	/**
	 * 当日
	 */
	public static final String FLAG_D="1";
	/**
	 * 当月
	 */
	public static final String FLAG_M="2";
	/**
	 * 今年
	 */
	public static final String FLAG_Y="3";

	/**
	 * 验证码无效
	 */
	public static final Integer AUTH_CODE_INVALID=-1;
	/**
	 * 验证码有效
	 */
	public static final Integer AUTH_CODE_EFFECTIVE=0;

}
