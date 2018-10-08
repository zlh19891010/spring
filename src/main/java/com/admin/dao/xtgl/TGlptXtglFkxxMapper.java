package com.admin.dao.xtgl;

import java.util.List;

import com.admin.entity.TGlptXtglFkxx;
import com.admin.param.ParamFkxx;
import com.admin.view.ViewFkxx;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * ClassName: TGlptXtglFkxxMapper <br/>
 * Function: 反馈信息mapper. <br/>
 * Date: 2017年3月17日 上午11:55:15 <br/>
 *
 * @author wangzheng
 * @version 1.0
 * @since JDK 1.7
 */
public interface TGlptXtglFkxxMapper extends BaseMapper<TGlptXtglFkxx> {

    /**
     * selectFkxxList,(分页查询反馈信息列表). <br/>
     * Author: wangzheng <br/>
     * Create Date: 2017年3月17日 <br/>
     * ===============================================================<br/>
     * Modifier: wangzheng <br/>
     * Modify Date: 2017年3月17日 <br/>
     * Modify Description: <br/>
     * ===============================================================<br/>
     *
     * @param page 分页参数
     * @param paramFkxx 查询条件
     * @return 结果
     * @since JDK 1.7
     */
    List<TGlptXtglFkxx> selectFkxxList(Page<ViewFkxx> page, ParamFkxx paramFkxx);
}