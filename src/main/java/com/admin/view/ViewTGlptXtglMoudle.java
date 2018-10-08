/**
 * Project Name:vr-admin-api
 * File Name:ViewTGlptXtglMoudle.java
 * Package Name:com.sd.vr.admin.api.view
 * Date:2017年3月3日上午10:13:33
 *
*/

package com.admin.view;

import java.io.Serializable;
import java.util.List;

import com.admin.entity.TGlptXtglMoudle;

/**
 * ClassName:ViewTGlptXtglMoudle <br/> 
 * Function: 系统菜单. <br/> 
 * Reason:	  递归菜单 <br/> 
 * Date:     2017年3月3日 上午10:13:33 <br/> 
 * @author   刘成
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
public class ViewTGlptXtglMoudle extends TGlptXtglMoudle implements Serializable,Comparable<TGlptXtglMoudle>{

  
    private static final long serialVersionUID = 1L;
    
    /**
     * 是否可读
     */
    private String czjb;
    
    
    
    public String getCzjb() {
        return czjb;
    }

    public void setCzjb(String czjb) {
        this.czjb = czjb;
    }

    /**
     * 菜单集合
     */
    private List<ViewTGlptXtglMoudle> viewListMoudle;

    public List<ViewTGlptXtglMoudle> getViewListMoudle() {
        return viewListMoudle;
    }

    public void setViewListMoudle(List<ViewTGlptXtglMoudle> viewListMoudle) {
        this.viewListMoudle = viewListMoudle;
    }

    public int compareTo(TGlptXtglMoudle o) {
        
        return  Integer.parseInt(this.getDisplayOrder()) - Integer.parseInt(o.getDisplayOrder());
    }

}

