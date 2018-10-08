package com.admin.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;

/**
 * ClassName: BaseModel <br/>
 * Function: 基础实体，用来继承. <br/>
 * Date: 2017年2月24日 下午1:23:55 <br/>
 *
 * @author weiming.chen
 * @version 1.0
 * @since JDK 1.7
 */
@SuppressWarnings("serial")
public class BaseModel implements Serializable {

    /** 主键id */
    @TableId
    protected String id;

    /** 创建者 */
    @TableField(value = "creater_code")
    private String createrCode;

    /** 创建时间 */
    @TableField(value = "create_time")
    private Date createTime;
    /** 操作标志 */
    @TableField(value = "delflag")
    private String delflag;
    /** 操作人 */
    @TableField(value = "operater_code")
    private String operaterCode;
    /** 操作时间 */
    @TableField(value = "operate_time")
    private Date operateTime;

    /**
     * 主键id.
     *
     * @return the 主键id
     * @since JDK 1.7
     */
    public String getId() {
        return id;
    }

    /**
     * 主键id.
     *
     * @param id the 主键id to set
     * @since JDK 1.7
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 创建者.
     *
     * @return the createrCode
     * @since JDK 1.7
     */
    public String getCreaterCode() {
        return createrCode;
    }

    /**
     * 创建者.
     *
     * @param createrCode the 创建者 to set
     * @since JDK 1.7
     */
    public void setCreaterCode(String createrCode) {
        this.createrCode = createrCode;
    }

    /**
     * 创建时间.
     *
     * @return the createTime
     * @since JDK 1.7
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间.
     *
     * @param createTime the 创建时间 to set
     * @since JDK 1.7
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 操作标志.
     *
     * @return the delflag
     * @since JDK 1.7
     */
    public String getDelflag() {
        return delflag;
    }

    /**
     * 操作标志.
     *
     * @param delflag the 操作标志 to set
     * @since JDK 1.7
     */
    public void setDelflag(String delflag) {
        this.delflag = delflag;
    }

    /**
     * 操作人.
     *
     * @return the operaterCode
     * @since JDK 1.7
     */
    public String getOperaterCode() {
        return operaterCode;
    }

    /**
     * 操作人.
     *
     * @param operaterCode the 操作人 to set
     * @since JDK 1.7
     */
    public void setOperaterCode(String operaterCode) {
        this.operaterCode = operaterCode  == null ? "" : operaterCode.trim();
    }

    /**
     * 操作时间.
     *
     * @return the operateTime
     * @since JDK 1.7
     */
    public Date getOperateTime() {
        return operateTime;
    }

    /**
     * 操作时间.
     *
     * @param operateTime the 操作时间 to set
     * @since JDK 1.7
     */
    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }

}
