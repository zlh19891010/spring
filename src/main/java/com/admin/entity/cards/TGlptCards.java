package com.admin.entity.cards;

import com.admin.entity.BaseModel;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>
 * 点卡发放记录表
 * </p>
 *
 * @author zlh
 * @since 2017-12-12
 */
@TableName("t_glpt_cards")
public class TGlptCards extends BaseModel {

	private static final long serialVersionUID = 1L;


	/**
	 * 发送的点卡数量
	 */
	private Long card;
	/**
	 * 被发送人ID
	 */
	private String strAccounts;

	/**
	 * 被发送人昵称
	 */
	private String strNickName;




	public Long getCard() {
		return card;
	}

	public void setCard(Long card) {
		this.card = card;
	}

	public String getStrAccounts() {
		return strAccounts;
	}

	public void setStrAccounts(String strAccounts) {
		this.strAccounts = strAccounts;
	}



	public String getStrNickName() {

		return strNickName;
	}


	public void setStrNickName(String strNickName) {

		this.strNickName = strNickName;
	}



}
