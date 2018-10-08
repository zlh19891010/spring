package com.admin.entity.yhgl;

import com.admin.entity.BaseModel;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;


/**
 *
 * ClassName: TGlptYhglYhxx <br/>
 * Function: 用户管理用户. <br/>
 * Date: 2017年3月6日 上午10:39:15 <br/>
 *
 * @author 刘成
 * @version 1.0
 * @since JDK 1.7
 */
@TableName("t_glpt_yhgl_yhxx")
public class TGlptYhglYhxx extends BaseModel {

    /**
     * 标识
     */
    private static final long serialVersionUID = 1L;
    /**
     * 登录名
     */
    @TableField("login_id")
    private String loginId;
    /**
     * 账号
     */
    private String zh;
    /**
     * 姓名
     */
    private String yhmc;
    /**
     * 用户头像
     */
    private String yhtx;
    /**
     * 密码
     */
    private String mm;
    /**
     * 邮箱
     */
    private String yx;
    /**
     * 手机号
     */
    private String sjh;
    /**
     * 用户状态（01未启用，02已启用，03已停用）
     */
    private String yhzt;
    /**
     * 角色编号
     */
    @TableField("js_id")
    private String jsId;
    /**
     * 指定管理员
     */
    private String zdgly;
    /**
     * 次及管理员
     */
    private String cjgly;
    /**
     * 机构id
     */
    @TableField("jg_id")
    private String jgid;
    /**
     * 机构名称
     */
    private String ssjgmc;
    /**
     * 上级管理员id
     */
    @TableField("sjgly_id")
    private String sjglyid;
    /**
     * 管理员级别
     */
    private String glyjb;


    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId == null ? "" : loginId.trim();
    }

    public String getZh() {
        return zh;
    }

    public void setZh(String zh) {
        this.zh = zh;
    }

    public String getYhmc() {
        return yhmc;
    }

    public void setYhmc(String yhmc) {
        this.yhmc = yhmc;
    }

    public String getYhtx() {
        return yhtx;
    }

    public void setYhtx(String yhtx) {
        this.yhtx = yhtx;
    }

    public String getMm() {
        return mm;
    }

    public void setMm(String mm) {
        this.mm = mm;
    }

    public String getYx() {
        return yx;
    }

    public void setYx(String yx) {
        this.yx = yx;
    }

    public String getSjh() {
        return sjh;
    }

    public void setSjh(String sjh) {
        this.sjh = sjh;
    }

    public String getYhzt() {
        return yhzt;
    }

    public void setYhzt(String yhzt) {
        this.yhzt = yhzt;
    }

    public String getJsId() {
        return jsId;
    }

    public void setJsId(String jsId) {
        this.jsId = jsId;
    }

    public String getZdgly() {
        return zdgly;
    }

    public void setZdgly(String zdgly) {
        this.zdgly = zdgly;
    }

    public String getJgid() {
        return jgid;
    }

    public void setJgid(String jgid) {
        this.jgid = jgid;
    }

    public String getSsjgmc() {
        return ssjgmc;
    }

    public void setSsjgmc(String ssjgmc) {
        this.ssjgmc = ssjgmc;
    }

    public String getCjgly() {
        return cjgly;
    }

    public void setCjgly(String cjgly) {
        this.cjgly = cjgly;
    }

    public String getSjglyid() {
        return sjglyid;
    }

    public void setSjglyid(String sjglyid) {
        this.sjglyid = sjglyid;
    }

    public String getGlyjb() {
        return glyjb;
    }

    public void setGlyjb(String glyjb) {
        this.glyjb = glyjb;
    }


}
