/**
 * Project Name:admin
 * File Name:JsxxParam.java
 * Package Name:com.admin.param
 * Date:2017年3月1日下午5:30:27
 *
 */

package com.admin.param;

import java.util.List;

import com.admin.entity.TGlptJsglJsyqxdygx;
import com.admin.entity.jsgl.TGlptJsglJsxx;

/**
 * ClassName:JsxxParam <br/>
 * Function: 角色信息参数 <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2017年3月1日 下午5:30:27 <br/>
 * 
 * @author tangli
 * @version
 * @since JDK 1.7
 * @see
 */
public class JsxxParam extends TGlptJsglJsxx {

    /**
     * serialVersionUID:TODO(序列化).
     * 
     * @since JDK 1.7
     */
    private static final long serialVersionUID = 1L;
    /** 当前页 */
    private Integer current;
    /** 每页大小 */
    private Integer size;
    /** pages */
    private Integer pages;
    /** 已分配权限list */
    private List<TGlptJsglJsyqxdygx> yfpqxList;

    /**
     * 已分配权限list.
     *
     * @return the yfpqxList
     * @since JDK 1.7
     */
    public List<TGlptJsglJsyqxdygx> getYfpqxList() {
        return yfpqxList;
    }

    /**
     * 已分配权限list.
     *
     * @param yfpqxList the 已分配权限list to set
     * @since JDK 1.7
     */
    public void setYfpqxList(List<TGlptJsglJsyqxdygx> yfpqxList) {
        this.yfpqxList = yfpqxList;
    }

    /**
     * 每页大小.
     *
     * @return the size
     * @since JDK 1.7
     */
    public Integer getSize() {
        return size;
    }

    /**
     * 每页大小.
     *
     * @param size the 每页大小 to set
     * @since JDK 1.7
     */
    public void setSize(Integer size) {
        this.size = size;
    }

    /**
     * pages.
     *
     * @return the pages
     * @since JDK 1.7
     */
    public Integer getPages() {
        return pages;
    }

    /**
     * pages.
     *
     * @param pages the pages to set
     * @since JDK 1.7
     */
    public void setPages(Integer pages) {
        this.pages = pages;
    }

    /**
     * 当前页.
     *
     * @return the current
     * @since JDK 1.7
     */
    public Integer getCurrent() {
        return current;
    }

    /**
     * 当前页.
     *
     * @param current the 当前页 to set
     * @since JDK 1.7
     */
    public void setCurrent(Integer current) {
        this.current = current;
    }

}
