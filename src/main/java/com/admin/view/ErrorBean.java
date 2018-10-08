package com.admin.view;

import java.io.Serializable;

/**
 * 
 * ClassName: ErrorBean <br/>
 * Function: 错误信息类. <br/>
 * Date: 2016年3月10日 下午5:41:34 <br/>
 *
 * @author qiaocf
 * @version 1.0
 * @since JDK 1.7
 */
public class ErrorBean implements Serializable {
    /** 序列化id **/
    private static final long serialVersionUID = 1L;

    /** 错误代码 **/
    private String errorCode;

    /** 错误信息 **/
    private Object errorData;

    public ErrorBean() {
    }

    public ErrorBean(String errorCode) {
        this.errorCode = errorCode;
    }

    public ErrorBean(String errorCode, Object errorData) {
        this.errorCode = errorCode;
        this.errorData = errorData;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public Object getErrorData() {
        return errorData;
    }

    public void setErrorData(Object errorData) {
        this.errorData = errorData;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj instanceof ErrorBean) {
            ErrorBean target = (ErrorBean) obj;
            return this.getErrorCode().equals(target.getErrorCode());
        }

        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

}
