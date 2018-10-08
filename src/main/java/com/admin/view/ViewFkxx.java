/**
 * Project Name:vr-admin-api
 * File Name:ViewNrsq.java
 * Package Name:com.sd.vr.admin.api.view
 * Date:2017年3月13日下午1:39:36
 *
 */

package com.admin.view;

import com.admin.entity.TGlptXtglFkxx;

/**
 * ClassName: ViewFkxx <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Date: 2017年3月17日 下午2:30:15 <br/>
 *
 * @author wangzheng
 * @version 1.0
 * @since JDK 1.7
 */
public class ViewFkxx extends TGlptXtglFkxx {

    /**
     * serialVersionUID:TODO(序列化).
     *
     * @since JDK 1.7
     */
    private static final long serialVersionUID = 1L;

    /**
     * 反馈类型描述
     */
    private String fklxDesc;

    /**
     * 回复状态描述
     */
    private String hfztDesc;

    public String getFklxDesc() {
        return fklxDesc;
    }

    public void setFklxDesc(String fklxDesc) {
        this.fklxDesc = fklxDesc;
    }

    public String getHfztDesc() {
        return hfztDesc;
    }

    public void setHfztDesc(String hfztDesc) {
        this.hfztDesc = hfztDesc;
    }

}
