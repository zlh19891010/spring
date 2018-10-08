/**
 * Project Name:vr-admin-api
 * File Name:ViewJsqxxx.java
 * Package Name:com.sd.vr.admin.api.view
 * Date:2017年3月1日下午7:12:45
 *
 */

package com.admin.view;

import java.io.Serializable;
import java.util.List;

import com.admin.entity.TGlptJsglJsyqxdygx;
import com.admin.entity.TGlptXtglMoudle;

/**
 * ClassName:ViewJsqxxx <br/>
 * Function: 角色权限信息View. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2017年3月1日 下午7:12:45 <br/>
 * 
 * @author tangli
 * @version
 * @since JDK 1.7
 * @see
 */
public class ViewJsqxxx implements Serializable {
    /**
     * serialVersionUID:TODO(序列化).
     * 
     * @since JDK 1.7
     */
    private static final long serialVersionUID = 1L;
    /** 可用权限 */
    List<TGlptXtglMoudle> moduleList;
    /** 已分配权限 */
    List<TGlptJsglJsyqxdygx> yfpqxList;

    /**
     * 可用权限.
     *
     * @return the moduleList
     * @since JDK 1.7
     */
    public List<TGlptXtglMoudle> getModuleList() {
        return moduleList;
    }

    /**
     * 可用权限.
     *
     * @param moduleList the 可用权限 to set
     * @since JDK 1.7
     */
    public void setModuleList(List<TGlptXtglMoudle> moduleList) {
        this.moduleList = moduleList;
    }

    /**
     * 已分配权限.
     *
     * @return the yfpqxList
     * @since JDK 1.7
     */
    public List<TGlptJsglJsyqxdygx> getYfpqxList() {
        return yfpqxList;
    }

    /**
     * 已分配权限.
     *
     * @param yfpqxList the 已分配权限 to set
     * @since JDK 1.7
     */
    public void setYfpqxList(List<TGlptJsglJsyqxdygx> yfpqxList) {
        this.yfpqxList = yfpqxList;
    }

}
