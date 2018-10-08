package com.admin.entity.log.operation;

import com.baomidou.mybatisplus.annotations.TableField;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableName;
import com.admin.entity.BaseModel;

/**
 * <p>
 * 
 * </p>
 *
 * @author zlh
 * @since 2017-12-05
 */
@TableName("log_operation")
public class LogOperation extends BaseModel {

    private static final long serialVersionUID = 1L;

    /**
     * 协议类型
     */
	private String type;
    /**
     * 状态（1:正在执行   0：执行成功   2：执行失败  ）
     */
	private String status;
    
    /**
     * 玩家ID  （为空就是操作全局的）
     */
	private String account;
    /**
     * 发送内容
     */
	@TableField("send_content")
	private String sendContent;
    /**
     * 返回结果
     */
	@TableField("return_content")
	private String returnContent;
	



	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getSendContent() {
		return sendContent;
	}

	public void setSendContent(String sendContent) {
		this.sendContent = sendContent;
	}

	public String getReturnContent() {
		return returnContent;
	}

	public void setReturnContent(String returnContent) {
		this.returnContent = returnContent;
	}


}
