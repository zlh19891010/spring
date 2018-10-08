package com.admin.entity.glygl;

import com.admin.entity.BaseModel;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 *
 * ClassName: TGlptGlyglGlyxx <br/>
 * Function: 管理员信息. <br/>
 * Date: 2017年3月6日 上午10:47:17 <br/>
 *
 * @author 刘成
 * @version 1.0
 * @since JDK 1.7
 */
@TableName("t_glpt_glygl_glyxx")
public class TGlptGlyglGlyxx extends BaseModel {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 登录名
	 */
	@TableField("login_id")
	private String loginId;
	/**
	 * 密码
	 */
	private String mm;
	/**
	 * 账号
	 */
	private String zh;
	/**
	 * 姓名
	 */
	private String yhmc;

	/**
	 * 用户头像
	 */
	private String yhtx;

	/**
	 * 邮箱
	 */
	private String yx;
	/**
	 * 手机号
	 */
	private String sjh;
	/**
	 * 角色编号
	 */
	@TableField("js_id")
	private String jsId;
	/**
	 * 是否次级管理员(1-是，0-否)
	 */
	private String sfcjgly;
	/**
	 * 上级管理员编号
	 */
	@TableField("sjgly_id")
	private Long sjglyId;
	/**
	 * 管理员状态（01未启用，02已启用，03已停用）
	 */
	@TableField("gly_zt")
	private String glyZt;

	/**
	 * 点卡数量
	 */
	@TableField("cards")
	private Long cards;

	/**
	 * 授权码
	 */
	private String sAuthCode;

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getMm() {
		return mm;
	}

	public void setMm(String mm) {
		this.mm = mm;
	}

	public String getZh() {
		return zh;
	}

	public void setZh(String zh) {
		this.zh = zh;
	}

	public String getYhmc() {
		return yhmc;
	}

	public void setYhmc(String yhmc) {
		this.yhmc = yhmc;
	}

	public String getYx() {
		return yx;
	}

	public void setYx(String yx) {
		this.yx = yx;
	}

	public String getSjh() {
		return sjh;
	}

	public void setSjh(String sjh) {
		this.sjh = sjh;
	}

	public String getJsId() {
		return jsId;
	}

	public void setJsId(String jsId) {
		this.jsId = jsId;
	}

	public String getSfcjgly() {
		return sfcjgly;
	}

	public void setSfcjgly(String sfcjgly) {
		this.sfcjgly = sfcjgly;
	}

	public Long getSjglyId() {
		return sjglyId;
	}

	public void setSjglyId(Long sjglyId) {
		this.sjglyId = sjglyId;
	}

	public String getGlyZt() {
		return glyZt;
	}

	public void setGlyZt(String glyZt) {
		this.glyZt = glyZt;
	}

	public String getYhtx() {
		return yhtx;
	}

	public void setYhtx(String yhtx) {
		this.yhtx = yhtx;
	}


	public Long getCards() {

		return cards;
	}


	public void setCards(Long cards) {

		this.cards = cards;
	}


	public String getsAuthCode() {

		return sAuthCode;
	}


	public void setsAuthCode(String sAuthCode) {

		this.sAuthCode = sAuthCode;
	}



}
