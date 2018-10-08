/**
 * Project Name:vr-admin-api
 * File Name:ViewXzjs.java
 * Package Name:com.sd.vr.admin.api.view
 * Date:2017年3月9日下午5:56:43
 *
 */

package com.admin.view;

import com.admin.entity.jsgl.TGlptJsglJsxx;

/**
 * ClassName:ViewXzjs <br/>
 * Function: 角色view ,新增上级名称字段. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2017年3月9日 下午5:56:43 <br/>
 * 
 * @author tangli
 * @version
 * @since JDK 1.7
 * @see
 */
public class ViewXzjs extends TGlptJsglJsxx {
    /**
     * serialVersionUID:TODO(序列化).
     * 
     * @since JDK 1.7
     */
    private static final long serialVersionUID = 1L;
    /**
     * 上级角色名称
     */
    private String sjjsmc;

    /**
     * 上级角色名称.
     *
     * @return the sjjsmc
     * @since JDK 1.7
     */
    public String getSjjsmc() {
        return sjjsmc;
    }

    /**
     * 上级角色名称.
     *
     * @param sjjsmc the 上级角色名称 to set
     * @since JDK 1.7
     */
    public void setSjjsmc(String sjjsmc) {
        this.sjjsmc = sjjsmc;
    }

}
