/**
 * Project Name:admin
 * File Name:CommonConstants.java
 * Package Name:com.sd.vr.admin.common
 * Date:2017年3月1日上午10:44:19
 *
 */

package com.admin.content;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.admin.common.CodeConstants;

/**
 * ClassName:CommonConstants <br/>
 * Function: 公共常量定义. <br/>
 * Date: 2017年3月1日 上午10:44:19 <br/>
 *
 * @author tangli
 * @version
 * @since JDK 1.7
 * @see
 */
public final class CommonConstants {

	private CommonConstants() {
	}

	/** 日志记录 */
	private static Logger logger = LoggerFactory.getLogger(CommonConstants.class);

	/** 动态加载排除过滤请求地址集合 */
	static {
		InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream("conf/excludeSessionFilter.properties");
		Properties prop = new Properties();
		try {
			prop.load(input);
			EXCLUDE_SESSION_URL = prop.keySet();
			input.close();
		} catch (IOException e) {
			logger.error("Loading exclude session filter url failed!!");
			e.printStackTrace();
		}
	}

	/** 问卷调查 用户会话丢失编码 */
	public static final String SESSION_MISS_CODE_DC = "4001";

	/** 存放被排除过滤的请求地址 */
	public static Set<Object> EXCLUDE_SESSION_URL = new HashSet<Object>();

	/** 新增 */
	public static final String DELFLAG_ADD = "A";

	/** 更新 */
	public static final String DELFLAG_UPDATE = "U";

	/** 删除 */
	public static final String DELFLAG_DELETE = "D";

	/**
	 *
	 * parameterIsNull,(如果有一个参数为空，返回parameterIsNull=true). <br/>
	 * Author: 刘成 <br/>
	 * Create Date: 2017年2月27日 <br/>
	 * ===============================================================<br/>
	 * Modifier: 刘成 <br/>
	 * Modify Date: 2017年2月27日 <br/>
	 * Modify Description: <br/>
	 * ===============================================================<br/>
	 *
	 * @param args 字符串参数
	 * @return 是否有空字符串
	 * @since JDK 1.7
	 */
	public static boolean parameterIsNull(String... args) {

		for (String arg : args) {
			if (StringUtils.isBlank(arg) || StringUtils.isEmpty(arg)) {
				return true;
			}
		}
		return false;
	}

	/**
	 *
	 * getCzlx,(获取操作类型). <br/>
	 * Author: 刘成 <br/>
	 * Create Date: 2017年3月9日 <br/>
	 * ===============================================================<br/>
	 * Modifier: 刘成 <br/>
	 * Modify Date: 2017年3月9日 <br/>
	 * Modify Description: <br/>
	 * ===============================================================<br/>
	 *
	 * @param str 字符串
	 * @return 01-新增，02-删除，03-更新，04-查看 ，05 登录
	 * @since JDK 1.7
	 */
	public static String getCzlx(String str) {
		if (str.toLowerCase().indexOf("add") != -1 || str.toLowerCase().indexOf("insert") != -1 || str.toLowerCase().indexOf("save") != -1) {
			str = CodeConstants.LOG_ADD;
		} else if (str.toLowerCase().indexOf("update") != -1 || str.toLowerCase().indexOf("modify") != -1 || str.toLowerCase().indexOf("edit") != -1) {
			str = CodeConstants.LOG_UPDATE;
		} else if (str.toLowerCase().indexOf("delete") != -1 || str.toLowerCase().indexOf("remove") != -1) {
			str = CodeConstants.LOG_DELETE;
		} else if (str.toLowerCase().indexOf("get") != -1 || str.toLowerCase().indexOf("find") != -1 || str.toLowerCase().indexOf("load") != -1 || str.toLowerCase().indexOf("search") != -1
				|| str.toLowerCase().indexOf("select") != -1) {
			str = CodeConstants.LOG_FIND;
		} else if (str.toLowerCase().indexOf("login") != -1 || str.toLowerCase().indexOf("logout") != -1) {
			str = CodeConstants.LOG_LOGIN;
		} else if (str.toLowerCase().indexOf("message") != -1) {
			str = CodeConstants.LOG_XY;
		}else {
			str = "";
		}
		return str;
	}
}
