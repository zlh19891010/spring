/**
 * Project Name:vr-admin-api
 * File Name:YhxxParam.java
 * Package Name:com.sd.vr.admin.api.param
 * Date:2017年3月1日下午1:43:47
 *
 */

package com.admin.param;

import com.admin.entity.yhgl.TGlptYhglYhxx;


/**
 * ClassName:YhxxParam <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2017年3月1日 下午1:43:47 <br/>
 * 
 * @author shaopei
 * @version
 * @since JDK 1.7
 * @see
 */
public class YhxxParam extends TGlptYhglYhxx {

	/**
	 * serialVersionUID:TODO(序列化标识).
	 * 
	 * @since JDK 1.7
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 角色名称
	 */
	private String jsmc;

	/**
	 * 当前页
	 */
	private int current;
	/**
	 * 当前页显示条数
	 */
	private int size;

	/**
	 * 角色名称.
	 * 
	 * @return the jsmc
	 * @since JDK 1.7
	 */
	public String getJsmc() {
		return jsmc;
	}

	/**
	 * 角色名称.
	 * 
	 * @param jsmc
	 *            the 角色名称 to set
	 * @since JDK 1.7
	 */
	public void setJsmc(String jsmc) {
		this.jsmc = jsmc;
	}

	/**
	 * 当前页.
	 * 
	 * @return the current
	 * @since JDK 1.7
	 */
	public int getCurrent() {
		return current;
	}

	/**
	 * 当前页.
	 * 
	 * @param current
	 *            the 当前页 to set
	 * @since JDK 1.7
	 */
	public void setCurrent(int current) {
		this.current = current;
	}

	/**
	 * 当前页显示条数.
	 * 
	 * @return the size
	 * @since JDK 1.7
	 */
	public int getSize() {
		return size;
	}

	/**
	 * 当前页显示条数.
	 * 
	 * @param size
	 *            the 当前页显示条数 to set
	 * @since JDK 1.7
	 */
	public void setSize(int size) {
		this.size = size;
	}

}
