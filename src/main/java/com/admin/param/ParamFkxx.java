/**
 * Project Name:vr-admin-api
 * File Name:ParamFjxx.java
 * Package Name:com.sd.vr.admin.api.param
 * Date:2017年3月10日下午3:51:32
 *
 */

package com.admin.param;

import com.admin.entity.TGlptXtglFkxx;

/**
 * ClassName: ParamFkxx <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Date: 2017年3月17日 下午1:59:14 <br/>
 *
 * @author wangzheng
 * @version 1.0
 * @since JDK 1.7
 */
public class ParamFkxx extends TGlptXtglFkxx {

    /**
     * serialVersionUID:TODO(用一句话描述这个变量表示什么).
     *
     * @since JDK 1.7
     */
    private static final long serialVersionUID = 1L;

    /**
     * 反馈开始时间
     */
    private String fkkssj;
    /**
     * 反馈结束时间
     */
    private String fkjssj;
    /**
     * 当前页
     */
    private int current;
    /**
     * 当前页显示条数
     */
    private int size;

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

    public String getFkkssj() {
        return fkkssj;
    }

    public void setFkkssj(String fkkssj) {
        this.fkkssj = fkkssj;
    }

    public String getFkjssj() {
        return fkjssj;
    }

    public void setFkjssj(String fkjssj) {
        this.fkjssj = fkjssj;
    }

}
