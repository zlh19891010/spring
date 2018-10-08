package com.admin.server2.message;

import java.io.Serializable;
import java.util.Date;

public class BaseMessage implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 消息ID
	 */
	private Integer id;
	/**
	 * 类型
	 */
	public  Integer type;

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}





	public Integer getId() {
		id=getSecondTimestamp(new Date());

		return id;
	}


	public void setId(Integer id) {

		this.id = id;
	}

	/**
	 * 获取精确到秒的时间戳
	 * @return
	 */
	private  Integer getSecondTimestamp(Date date){
		if (null == date) {
			return 0;
		}
		String timestamp = String.valueOf(date.getTime());
		int length = timestamp.length();
		if (length > 3) {
			return Integer.valueOf(timestamp.substring(0,length-3));
		} else {
			return 0;
		}
	}

}
