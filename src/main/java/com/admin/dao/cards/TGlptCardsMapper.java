package com.admin.dao.cards;

import java.util.List;

import com.admin.entity.cards.TGlptCards;
import com.admin.param.ParamCards;
import com.admin.view.ViewCards;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * <p>
 * 点卡发放记录表 Mapper 接口
 * </p>
 *
 * @author zlh
 * @since 2017-12-12
 */
public interface TGlptCardsMapper extends BaseMapper<TGlptCards> {

	List<ViewCards> selectCards(Page<ViewCards> page,ParamCards paramCards);
}