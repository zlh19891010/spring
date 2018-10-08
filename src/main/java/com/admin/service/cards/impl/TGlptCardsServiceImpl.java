package com.admin.service.cards.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.admin.dao.cards.TGlptCardsMapper;
import com.admin.entity.cards.TGlptCards;
import com.admin.param.ParamCards;
import com.admin.service.CommonService;
import com.admin.service.cards.TGlptCardsService;
import com.admin.view.ViewCards;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

/**
 * <p>
 * 点卡发放记录表 服务实现类
 * </p>
 *
 * @author zlh
 * @since 2017-12-12
 */
@Service
public class TGlptCardsServiceImpl extends ServiceImpl<TGlptCardsMapper, TGlptCards> implements TGlptCardsService {

	@Autowired
	private TGlptCardsMapper tGlptCardsMapper;

	@Autowired
	private CommonService commonService;

	@Override
	public Page<ViewCards> selectCards(ParamCards paramCards) {

		logger.info("===开始发点卡记录分页===");
		Page<ViewCards> page = new Page<ViewCards>(paramCards.getCurrent(), paramCards.getSize());
		List<ViewCards> result = tGlptCardsMapper.selectCards(page,paramCards);
		for(ViewCards car:result){
			car.setStrAccounts(commonService.getUid(car.getStrAccounts()));
		}
		page.setRecords(result);
		logger.info("===结束发点卡记录分页===");
		return page;
	}

}
