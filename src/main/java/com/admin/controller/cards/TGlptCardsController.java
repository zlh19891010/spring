package com.admin.controller.cards;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.admin.common.BaseController;
import com.admin.common.CodeConstants;
import com.admin.param.ParamCards;
import com.admin.service.cards.TGlptCardsService;
import com.admin.view.ViewCards;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * <p>
 * 点卡发放记录表 前端控制器
 * </p>
 *
 * @author zlh
 * @since 2017-12-12
 */
@RestController
@RequestMapping("/tGlptCards")
public class TGlptCardsController extends BaseController{


	@Autowired
	private TGlptCardsService tGlptCardsService;

	/**
	 *
	 *
	 * 方法描述: [获取发点卡记录分页.]</br>
	 * 初始作者: ZhouLanHui<br/>
	 * 创建日期: 2017年12月12日-下午8:53:29<br/>
	 * 开始版本: 2.0.0<br/>
	 * =================================================<br/>
	 * 修改记录：<br/>
	 * 修改作者         日期         修改内容<br/>
	 * ================================================<br/>
	 * @param paramCards
	 * @return
	 * Page<ViewCards>
	 *
	 */
	@RequestMapping("selectCards")
	Page<ViewCards> selectCards(@RequestBody ParamCards paramCards){
		if(!CodeConstants.ADMIN.equals(getUserModel().getLoginId())){
			paramCards.setUserId(getUserModel().getId());
		}
		return tGlptCardsService.selectCards(paramCards);
	}



}
