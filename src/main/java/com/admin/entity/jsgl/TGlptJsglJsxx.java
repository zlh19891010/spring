package com.admin.entity.jsgl;

import com.admin.entity.BaseModel;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>
 * 角色信息
 * </p>
 *
 * @author weiming.chen
 * @since 2017-03-01
 */
@TableName("t_glpt_jsgl_jsxx")
public class TGlptJsglJsxx extends BaseModel {
    /**
     * 序列化
     */
    private static final long serialVersionUID = 1L;
    /**
     * 角色状态
     */
    private String jszt;

    /**
     * 角色名称
     */
    private String jsmc;
    /**
     * 角色描述
     */
    private String jsms;
    /**
     * 角色归属(01管理平台，02运营中心)
     */
    private String jsgs;
    /**
     * 角色级别
     */
    private String jsjb;
    /**
     * 上级角色编号
     */
    @TableField("sjjs_id")
    private String sjjsId;

    /**
     * 上级角色编号.
     *
     * @return the sjjsId
     * @since JDK 1.7
     */
    public String getSjjsId() {
        return sjjsId;
    }

    /**
     * 上级角色编号.
     *
     * @param sjjsId the 上级角色编号 to set
     * @since JDK 1.7
     */
    public void setSjjsId(String sjjsId) {
        this.sjjsId = sjjsId;
    }

    /**
     * 角色状态.
     *
     * @return the jszt
     * @since JDK 1.7
     */
    public String getJszt() {
        return jszt;
    }

    /**
     * 角色状态.
     *
     * @param jszt the 角色状态 to set
     * @since JDK 1.7
     */
    public void setJszt(String jszt) {
        this.jszt = jszt;
    }

    /**
     * 角色名称.
     *
     * @return the jsmc
     * @since JDK 1.7
     */
    public String getJsmc() {
        return jsmc;
    }

    /**
     * 角色名称.
     *
     * @param jsmc the 角色名称 to set
     * @since JDK 1.7
     */
    public void setJsmc(String jsmc) {
        this.jsmc = jsmc;
    }

    /**
     * 角色描述.
     *
     * @return the jsms
     * @since JDK 1.7
     */
    public String getJsms() {
        return jsms;
    }

    /**
     * 角色描述.
     *
     * @param jsms the 角色描述 to set
     * @since JDK 1.7
     */
    public void setJsms(String jsms) {
        this.jsms = jsms;
    }

    /**
     * 角色归属(01管理平台，02运营中心).
     *
     * @return the msgs
     * @since JDK 1.7
     */
    public String getJsgs() {
        return jsgs;
    }

    /**
     * 角色归属(01管理平台，02运营中心).
     *
     * @param msgs the 角色归属(01管理平台，02运营中心) to set
     * @since JDK 1.7
     */
    public void setJsgs(String msgs) {
        this.jsgs = msgs;
    }

    /**
     * 角色级别.
     *
     * @return the jsjb
     * @since JDK 1.7
     */
    public String getJsjb() {
        return jsjb;
    }

    /**
     * 角色级别.
     *
     * @param jsjb the 角色级别 to set
     * @since JDK 1.7
     */
    public void setJsjb(String jsjb) {
        this.jsjb = jsjb;
    }

}
