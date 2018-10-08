package com.admin.utils;

public enum MessType {
	//点卡
	PointCard(1),
	//通知公告
	Notice(2),
	//消息
	Message(3),
	//踢人
	Kicking(4),
	//封号
	Lock(5),
	//解封
	UnLock(6),
	//授权码验证
	AuthCode(7),
	//解绑
	Unbink(10),
	//充值
	Recharge(8);

	private Integer value;

	private MessType(Integer value){
		this.value=value;
	}

	public Integer getValue() {
		return value;
	}

}
