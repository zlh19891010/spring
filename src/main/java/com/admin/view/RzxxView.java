/**
 * Project Name:vr-admin-api
 * File Name:RzxxView.java
 * Package Name:com.sd.vr.admin.api.view
 * Date:2017年3月15日下午3:08:56
 *
 */

package com.admin.view;

import com.admin.entity.TGlptXtglLog;

/**
 * ClassName:RzxxView <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2017年3月15日 下午3:08:56 <br/>
 * @author   shaopei
 * @version
 * @since    JDK 1.7
 * @see
 */
public class RzxxView  extends TGlptXtglLog {

    /**
     * serialVersionUID:标识.
     * @since JDK 1.7
     */
    private static final long serialVersionUID = 1L;

    /**
     * 操作人姓名
     */
    private String yhmc;
    /**
     * 操作时间
     */
    private String zcsj;

    public String getYhmc() {
        return yhmc;
    }

    public void setYhmc(String yhmc) {
        this.yhmc = yhmc;
    }

    public String getZcsj() {
        return zcsj;
    }

    public void setZcsj(String zcsj) {
        this.zcsj = zcsj;
    }


}

