package com.admin.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * 
 * ClassName: TGlptJsglJsyqxdygx <br/>
 * Function:角色与权限对应关系. <br/>
 * Date: 2017年3月1日 下午6:35:49 <br/>
 *
 * @author tangli
 * @version 1.0
 * @since JDK 1.7
 */
@TableName("t_glpt_jsgl_jsyqxdygx")
public class TGlptJsglJsyqxdygx extends BaseModel {

    /**
     * serialVersionUID:TODO(序列化).
     * 
     * @since JDK 1.7
     */
    private static final long serialVersionUID = 1L;
    /**
     * 角色编号
     */
    @TableField("js_id")
    private String jsId;
    /**
     * 权限代码
     */
    @TableField("qx_code")
    private String qxCode;
    /**
     * 角色归属(01管理平台，02运营中心)
     */
    private String jsgs;
    /**
     * 是否限制内容购买(1-是，0-否)
     */
    private String xzgm;
    /**
     * 内容编号
     */
    @TableField("nr_id")
    private String nrId;
    /**
     * 是否限制内容使用
     */
    private String xzsy;

    /**
     * 操作级别
     */
    private String czjb;

    /**
     * `czjb`char(10)操作级别
     *
     * @return the `czjb`char(10)操作级别
     * @since JDK 1.7
     */
    public String getCzjb() {
        return czjb;
    }

    /**
     * `czjb`char(10)操作级别
     *
     * @param czjb the `czjb`char(10)DEFAU to set
     * @since JDK 1.7
     */
    public void setCzjb(String czjb) {
        this.czjb = czjb;
    }

    /**
     * 角色编号.
     *
     * @return the jsId
     * @since JDK 1.7
     */
    public String getJsId() {
        return jsId;
    }

    /**
     * 角色编号.
     *
     * @param jsId the 角色编号 to set
     * @since JDK 1.7
     */
    public void setJsId(String jsId) {
        this.jsId = jsId;
    }

    /**
     * 权限代码.
     *
     * @return the qxCode
     * @since JDK 1.7
     */
    public String getQxCode() {
        return qxCode;
    }

    /**
     * 权限代码.
     *
     * @param qxCode the 权限代码 to set
     * @since JDK 1.7
     */
    public void setQxCode(String qxCode) {
        this.qxCode = qxCode;
    }

    /**
     * 角色归属(01管理平台，02运营中心).
     *
     * @return the jsgs
     * @since JDK 1.7
     */
    public String getJsgs() {
        return jsgs;
    }

    /**
     * 角色归属(01管理平台，02运营中心).
     *
     * @param jsgs the 角色归属(01管理平台，02运营中心) to set
     * @since JDK 1.7
     */
    public void setJsgs(String jsgs) {
        this.jsgs = jsgs;
    }

    /**
     * 是否限制内容购买(1-是，0-否).
     *
     * @return the xzgm
     * @since JDK 1.7
     */
    public String getXzgm() {
        return xzgm;
    }

    /**
     * 是否限制内容购买(1-是，0-否).
     *
     * @param xzgm the 是否限制内容购买(1-是，0-否) to set
     * @since JDK 1.7
     */
    public void setXzgm(String xzgm) {
        this.xzgm = xzgm;
    }

    /**
     * 内容编号.
     *
     * @return the nrId
     * @since JDK 1.7
     */
    public String getNrId() {
        return nrId;
    }

    /**
     * 内容编号.
     *
     * @param nrId the 内容编号 to set
     * @since JDK 1.7
     */
    public void setNrId(String nrId) {
        this.nrId = nrId;
    }

    /**
     * 是否限制内容使用.
     *
     * @return the xzsy
     * @since JDK 1.7
     */
    public String getXzsy() {
        return xzsy;
    }

    /**
     * 是否限制内容使用.
     *
     * @param xzsy the 是否限制内容使用 to set
     * @since JDK 1.7
     */
    public void setXzsy(String xzsy) {
        this.xzsy = xzsy;
    }

}
