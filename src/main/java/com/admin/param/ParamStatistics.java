package com.admin.param;

import java.io.Serializable;


/**
 * 文件名称： com.admin.param.ParamStatistics.java</br>
 * 初始作者： ZhouLanHui</br>
 * 创建日期： 2017年12月19日</br>
 * 功能说明： 这里用一句话描述这个类的作用--此句话需删除 <br/>
 *
 * =================================================<br/>
 * 修改记录：<br/>
 * 修改作者        日期       修改内容<br/>
 *
 *
 * ================================================<br/>
 *  Copyright (c) 2010-2011 .All rights reserved.<br/>
 */
public class ParamStatistics implements Serializable {

	/**
	 * 字段描述: [字段功能描述]
	 */
	private static final long	serialVersionUID	= 1L;
	/**
	 * 开始时间
	 */
	private String czkssj;
	/**
	 * 结束时间
	 */
	private String czjssj;

	/**
	 * 标识(1:当天，2：当月，3:今年)
	 */
	private String flag;

	/**
	 * 今天开始时间
	 */
	private String sdate;

	/**
	 *今天结束时间
	 */
	private String edate;

	/**
	 * 本月开始日期
	 */
	private String smouth;
	/**
	 * 本月结束日期
	 */
	private String emouth;
	/**
	 * 今年开始日期
	 */
	private String syear;
	/**
	 * 今年结束日期
	 */
	private String eyear;

	/**
	 * 当前页
	 */
	private int current;
	/**
	 * 当前页显示条数
	 */
	private int size;


	private String sAuthCode;


	private String openid;




	public String getsAuthCode() {

		return sAuthCode;
	}


	public void setsAuthCode(String sAuthCode) {

		this.sAuthCode = sAuthCode;
	}


	public String getOpenid() {

		return openid;
	}


	public void setOpenid(String openid) {

		this.openid = openid;
	}

	public String getCzkssj() {

		return czkssj;
	}

	public void setCzkssj(String czkssj) {

		this.czkssj = czkssj;
	}

	public String getCzjssj() {

		return czjssj;
	}

	public void setCzjssj(String czjssj) {

		this.czjssj = czjssj;
	}




	public String getSdate() {
		return sdate;
	}


	public void setSdate(String sdate) {

		this.sdate = sdate;
	}


	public String getEdate() {
		return edate;
	}


	public void setEdate(String edate) {

		this.edate = edate;
	}

	public String getSmouth() {
		return smouth;
	}

	public void setSmouth(String smouth) {

		this.smouth = smouth;
	}

	public String getEmouth() {
		return emouth;
	}

	public void setEmouth(String emouth) {

		this.emouth = emouth;
	}

	public String getSyear() {
		return syear;
	}

	public void setSyear(String syear) {

		this.syear = syear;
	}

	public String getEyear() {
		return eyear;
	}

	public void setEyear(String eyear) {

		this.eyear = eyear;
	}


	public int getCurrent() {

		return current;
	}


	public void setCurrent(int current) {

		this.current = current;
	}


	public int getSize() {

		return size;
	}


	public void setSize(int size) {

		this.size = size;
	}


	public String getFlag() {

		return flag;
	}


	public void setFlag(String flag) {

		this.flag = flag;
	}



}
