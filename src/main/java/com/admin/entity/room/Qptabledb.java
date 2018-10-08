package com.admin.entity.room;

import com.baomidou.mybatisplus.annotations.TableId;
import java.util.Date;
import com.admin.entity.BaseModel;

/**
 * <p>
 * 
 * </p>
 *
 * @author zlh
 * @since 2017-12-15
 */
public class Qptabledb  {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一ID
     */
	@TableId("GUID")
	private String guid;
    /**
     * 存储游戏相关
     */
	private byte[] Data;
    /**
     * 存储时间
     */
	private Date Time;


	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public byte[] getData() {
		return Data;
	}

	public void setData(byte[] Data) {
		this.Data = Data;
	}

	public Date getTime() {
		return Time;
	}

	public void setTime(Date Time) {
		this.Time = Time;
	}

}
