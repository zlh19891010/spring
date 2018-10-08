package com.admin.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>
 * 反馈信息
 * </p>
 *
 * @author LIUCHENG
 * @since 2017-03-17
 */
@TableName("t_glpt_xtgl_fkxx")
public class TGlptXtglFkxx extends BaseModel {

    /**
     * serialVersionUID:TODO(序列化).
     *
     * @since JDK 1.7
     */
    private static final long serialVersionUID = 1L;

    /**
     * 信息标题
     */
    private String xxbt;
    /**
     * 反馈类型（00-平台管理建议 01-设备使用反馈）
     */
    private String fklx;
    /**
     * 反馈内容
     */
    private String fknr;
    /**
     * 反馈状态(00-待回复 01- 已回复)
     */
    private String fkzt;
    /**
     * 反馈人账号
     */
    private String fkrzh;
    /**
     * 反馈人名称
     */
    private String fkrmc;
    /**
     * 反馈时间
     */
    private Date fksj;
    /**
     * 回复人账号
     */
    private String hfrzh;
    /**
     * 回复人名称
     */
    private String hfrmc;
    /**
     * 回复内容
     */
    private String hfnr;
    /**
     * 回复时间
     */
    private Date hfsj;

    public String getXxbt() {
        return xxbt;
    }

    public void setXxbt(String xxbt) {
        this.xxbt = xxbt == null ? "" : xxbt.trim();
    }

    public String getFklx() {
        return fklx;
    }

    public void setFklx(String fklx) {
        this.fklx = fklx;
    }

    public String getFknr() {
        return fknr;
    }

    public void setFknr(String fknr) {
        this.fknr = fknr;
    }

    public String getFkzt() {
        return fkzt;
    }

    public void setFkzt(String fkzt) {
        this.fkzt = fkzt;
    }

    public String getFkrzh() {
        return fkrzh;
    }

    public void setFkrzh(String fkrzh) {
        this.fkrzh = fkrzh;
    }

    public String getFkrmc() {
        return fkrmc;
    }

    public void setFkrmc(String fkrmc) {
        this.fkrmc = fkrmc;
    }

    public Date getFksj() {
        return fksj;
    }

    public void setFksj(Date fksj) {
        this.fksj = fksj;
    }

    public String getHfrzh() {
        return hfrzh;
    }

    public void setHfrzh(String hfrzh) {
        this.hfrzh = hfrzh;
    }

    public String getHfrmc() {
        return hfrmc;
    }

    public void setHfrmc(String hfrmc) {
        this.hfrmc = hfrmc;
    }

    public String getHfnr() {
        return hfnr;
    }

    public void setHfnr(String hfnr) {
        this.hfnr = hfnr;
    }

    public Date getHfsj() {
        return hfsj;
    }

    public void setHfsj(Date hfsj) {
        this.hfsj = hfsj;
    }

}
