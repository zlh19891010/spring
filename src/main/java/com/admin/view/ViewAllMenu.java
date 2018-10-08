/**
 * Project Name:vr-admin-api
 * File Name:ViewAllMenu.java
 * Package Name:com.sd.vr.admin.api.view
 * Date:2017年3月7日上午10:53:02
 *
 */

package com.admin.view;

import com.admin.entity.TGlptXtglMoudle;

/**
 * ClassName:ViewAllMenu <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2017年3月7日 上午10:53:02 <br/>
 * 
 * @author tangli
 * @version
 * @since JDK 1.7
 * @see
 */
public class ViewAllMenu extends TGlptXtglMoudle {

    public ViewAllMenu() {

    }

    public ViewAllMenu(String id, String pId, String name, String czjb, boolean dxflag) {
        this.id = id;
        this.pId = pId;
        this.name = name;
        this.czjb = czjb;
        this.dxflag = dxflag;
    }

    public ViewAllMenu(String id, String pId, String name) {
        this.id = id;
        this.pId = pId;
        this.name = name;
    }

    /**
     * serialVersionUID:TODO(序列化).
     * 
     * @since JDK 1.7
     */
    private static final long serialVersionUID = 1L;
    /*父id*/
    private String pId;
    /*菜单名*/
    private String name;
    /*操作级别*/
    private String czjb;
    /* 默认读写区分*/
    private boolean dxflag;

    /**
     * dxflag.
     *
     * @return the dxflag
     * @since JDK 1.7
     */
    public boolean isDxflag() {
        return dxflag;
    }

    /**
     * dxflag.
     *
     * @param dxflag the dxflag to set
     * @since JDK 1.7
     */
    public void setDxflag(boolean dxflag) {
        this.dxflag = dxflag;
    }

    /**
     * czjb.
     *
     * @return the czjb
     * @since JDK 1.7
     */
    public String getCzjb() {
        return czjb;
    }

    /**
     * czjb.
     *
     * @param czjb the czjb to set
     * @since JDK 1.7
     */
    public void setCzjb(String czjb) {
        this.czjb = czjb;
    }

    /**
     * pId.
     *
     * @return the pId
     * @since JDK 1.7
     */
    public String getpId() {
        return pId;
    }

    /**
     * pId.
     *
     * @param pId the pId to set
     * @since JDK 1.7
     */
    public void setpId(String pId) {
        this.pId = pId;
    }

    /**
     * name.
     *
     * @return the name
     * @since JDK 1.7
     */
    public String getName() {
        return name;
    }

    /**
     * name.
     *
     * @param name the name to set
     * @since JDK 1.7
     */
    public void setName(String name) {
        this.name = name;
    }

}
