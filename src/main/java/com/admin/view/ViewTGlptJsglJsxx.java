/**
 * Project Name:vr-admin-api
 * File Name:ViewTGlptJsglJsxx.java
 * Package Name:com.sd.vr.admin.api.view
 * Date:2017年3月4日上午11:51:12
 *
 */

package com.admin.view;

import com.admin.entity.jsgl.TGlptJsglJsxx;

/**
 * ClassName:ViewTGlptJsglJsxx <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2017年3月4日 上午11:51:12 <br/>
 * 
 * @author tangli
 * @version
 * @since JDK 1.7
 * @see
 */
public class ViewTGlptJsglJsxx extends TGlptJsglJsxx {
    /**
     * serialVersionUID:TODO(序列化).
     * 
     * @since JDK 1.7
     */
    private static final long serialVersionUID = 1L;
    /** 角色归属描述 */
    private String jsgsDesc;
    /** 角色状态描述 */
    private String jsztDesc;
    /** 编辑btn */
    private boolean bjBtn;
    /** 删除btn */
    private boolean scBtn;

    /**
     * 编辑btn.
     *
     * @return the bjBtn
     * @since JDK 1.7
     */
    public boolean isBjBtn() {
        return bjBtn;
    }

    /**
     * 编辑btn.
     *
     * @param bjBtn the 编辑btn to set
     * @since JDK 1.7
     */
    public void setBjBtn(boolean bjBtn) {
        this.bjBtn = bjBtn;
    }

    /**
     * 删除btn.
     *
     * @return the scBtn
     * @since JDK 1.7
     */
    public boolean isScBtn() {
        return scBtn;
    }

    /**
     * 删除btn.
     *
     * @param scBtn the 删除btn to set
     * @since JDK 1.7
     */
    public void setScBtn(boolean scBtn) {
        this.scBtn = scBtn;
    }

    /**
     * 角色状态描述.
     *
     * @return the jsztDesc
     * @since JDK 1.7
     */
    public String getJsztDesc() {
        return jsztDesc;
    }

    /**
     * 角色状态描述.
     *
     * @param jsztDesc the 角色状态描述 to set
     * @since JDK 1.7
     */
    public void setJsztDesc(String jsztDesc) {
        this.jsztDesc = jsztDesc;
    }

    /**
     * 角色归属描述.
     *
     * @return the jsgsDesc
     * @since JDK 1.7
     */
    public String getJsgsDesc() {
        return jsgsDesc;
    }

    /**
     * 角色归属描述.
     *
     * @param jsgsDesc the 角色归属描述 to set
     * @since JDK 1.7
     */
    public void setJsgsDesc(String jsgsDesc) {
        this.jsgsDesc = jsgsDesc;
    }

}
