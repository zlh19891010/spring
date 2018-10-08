package com.admin.service.cards;

import com.admin.entity.cards.TGlptCards;
import com.admin.param.ParamCards;
import com.admin.view.ViewCards;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 点卡发放记录表 服务类
 * </p>
 *
 * @author zlh
 * @since 2017-12-12
 */
public interface TGlptCardsService extends IService<TGlptCards> {


	Page<ViewCards> selectCards(ParamCards paramCards);
}
