/**
 * Project Name:admin
 * File Name:User.java
 * Package Name:com.admin.param.glyxx
 * Date:2017年3月1日下午2:35:28
 *
*/

package com.admin.param;

import java.io.Serializable;

/**
 * ClassName:LoginParam <br/> 
 * Function: 登录请求参数. <br/> 
 * Reason:	 TODO ADD REASON. <br/> 
 * Date:     2017年3月1日 下午2:35:28 <br/> 
 * @author   刘成
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
public class LoginParam implements Serializable{
    private static final long serialVersionUID = 1L;
    /**
     * 用户名
     */
    private String userName;
    
    /**
     * 密码
     */
    private String mm;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMm() {
        return mm;
    }

    public void setMm(String mm) {
        this.mm = mm;
    }


    
    
}

