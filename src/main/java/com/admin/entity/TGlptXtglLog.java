package com.admin.entity;

import com.baomidou.mybatisplus.annotations.TableName;

/**
 *
 * ClassName: TGlptXtglLog <br/>
 * Function: 日志信息. <br/>
 * Date: 2017年3月9日 下午5:19:55 <br/>
 *
 * @author 刘成
 * @version 1.0
 * @since JDK 1.7
 */
@TableName("t_glpt_xtgl_log")
public class TGlptXtglLog extends BaseModel {

    public  TGlptXtglLog() {

    };
    public  TGlptXtglLog(String zh, String ywmc, String ywbh, String ywb, String rzms, String czrmc) {
        this.czrmc = czrmc;
        this.ywb = ywb;
        this.ywbh = ywbh;
        this.ywmc = ywmc;
        this.rzms = rzms;
        super.setOperaterCode(zh);
        super.setCreaterCode(zh);

    }


    /**
     * 标识
     */
    private static final long serialVersionUID = 1L;

    /**
     * 业务名称
     */
    private String ywmc;
    /**
     * 日志归属（01-管理平台，02-运营中心）
     */
    private String ywbh;
    /**
     * 操作类型（01-新增，02-删除，03-更新，04-查看）
     */
    private String ywb;
    /**
     * 日志描述
     */
    private String rzms;
    /**
     * 操作人名称
     */
    private String czrmc;


    public String getYwmc() {
        return ywmc;
    }

    public void setYwmc(String ywmc) {
        this.ywmc = ywmc  == null ? "" : ywmc.trim();
    }

    public String getYwbh() {
        return ywbh;
    }

    public void setYwbh(String ywbh) {
        this.ywbh = ywbh;
    }

    public String getYwb() {
        return ywb;
    }

    public void setYwb(String ywb) {
        this.ywb = ywb;
    }

    public String getRzms() {
        return rzms;
    }

    public void setRzms(String rzms) {
        this.rzms = rzms;
    }

    public String getCzrmc() {
        return czrmc;
    }

    public void setCzrmc(String czrmc) {
        this.czrmc = czrmc;
    }

}
