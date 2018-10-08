/**
 * Project Name:vr-admin-api
 * File Name:ParamLogxx.java
 * Package Name:com.sd.vr.admin.api.param
 * Date:2017年3月15日下午1:17:08
 *
 */

package com.admin.param;

import com.admin.entity.TGlptXtglLog;

/**
 * ClassName:ParamLogxx <br/>
 * Function: 日志参数. <br/>
 * Reason:	 ADD REASON. <br/>
 * Date:     2017年3月15日 下午1:17:08 <br/>
 * @author   shaopei
 * @version
 * @since    JDK 1.7
 * @see
 */
public class ParamLogxx extends TGlptXtglLog {

    /**
     * serialVersionUID:(标识).
     * @since JDK 1.7
     */
    private static final long serialVersionUID = 1L;
    /**
     * 当前页
     */
    private int current;
    /**
     * 当前页显示条数
     */
    private int size;
    /**
     * 操作开始时间
     */
    private String czkssj;
    /**
     * 操作结束时间
     */
    private String czjssj;
    public int getCurrent() {
        return current;
    }
    public void setCurrent(int current) {
        this.current = current;
    }
    public int getSize() {
        return size;
    }
    public void setSize(int size) {
        this.size = size;
    }
    public String getCzkssj() {
        return czkssj;
    }
    public void setCzkssj(String czkssj) {
        this.czkssj = czkssj;
    }
    public String getCzjssj() {
        return czjssj;
    }
    public void setCzjssj(String czjssj) {
        this.czjssj = czjssj;
    }


}

