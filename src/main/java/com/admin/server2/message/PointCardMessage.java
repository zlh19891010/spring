package com.admin.server2.message;

public class PointCardMessage extends BaseMessage {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 角色ID
	 */
	private String roleId;
	/**
	 * 点卡数量
	 */
	private Double  amount;

	/**
	 * 0位代理商发的，1表示充值
	 */
	private Integer style;





	public PointCardMessage(Integer type,String roleId, Double amount,Integer style) {
		this.roleId = roleId;
		this.amount = amount;
		this.type=type;
		this.style=style;
	}
	public PointCardMessage() {
	}


	public String getRoleId() {

		return roleId;
	}

	public void setRoleId(String roleId) {

		this.roleId = roleId;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Integer getStyle() {

		return style;
	}

	public void setStyle(Integer style) {

		this.style = style;
	}


}
