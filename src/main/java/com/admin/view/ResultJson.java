package com.admin.view;

import java.io.Serializable;
import java.util.List;

import com.alibaba.fastjson.JSONObject;

/**
 * 
 * ClassName: ResultJson <br/>
 * Function: ResultJson类. <br/>
 * Date: 2016年3月11日 上午11:31:58 <br/>
 *
 * @author qiaocf
 * @version 1.0
 * @since JDK 1.7
 */
public class ResultJson implements Serializable {
    /** 序列化id **/
    private static final long serialVersionUID = 1L;

    /** 结果 **/
    private boolean result;

    /** 代码 **/
    private String code;
    /** 消息 **/
    private String message;

    /** 错误集合 **/
    private List<ErrorBean> errorList;

    /** 数据集合 **/
    private List<?> data;

    /** 实体类 **/
    private Object object;

    /** 实体类数组 **/
    private Object[] objects;

    public ResultJson() {

    }

    public ResultJson(boolean result) {
        this.result = result;
    }

    /***
     * 
     * trueResult,(trueResult). <br/>
     * Author: qiaocf <br/>
     * Create Date: 2016年3月11日 <br/>
     * ===============================================================<br/>
     * Modifier: qiaocf <br/>
     * Modify Date: 2016年3月11日 <br/>
     * Modify Description: <br/>
     * ===============================================================<br/>
     * 
     * @return String
     * @since JDK 1.7
     */
    public static String trueResult() {
        return new ResultJson(true).toString();
    }

    /**
     * 
     * falseResult,(falseResult). <br/>
     * Author: qiaocf <br/>
     * Create Date: 2016年3月11日 <br/>
     * ===============================================================<br/>
     * Modifier: qiaocf <br/>
     * Modify Date: 2016年3月11日 <br/>
     * Modify Description: <br/>
     * ===============================================================<br/>
     * 
     * @return String
     * @since JDK 1.7
     */
    public static String falseResult() {
        return new ResultJson(false).toString();
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }

    public ResultJson(boolean result, String code) {
        this.result = result;
        this.code = code;
    }

    public ResultJson(boolean result, String code, String message) {
        this.result = result;
        this.message = message;
        this.code = code;
    }

    @Override
    public String toString() {
        JSONObject json = new JSONObject();
        json.put("result", result);
        json.put("code", code);
        json.put("errorList", errorList);
        json.put("message", message);
        json.put("data", data);
        json.put("object", object);
        return json.toString();
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public List<ErrorBean> getErrorList() {
        return errorList;
    }

    public void setErrorList(List<ErrorBean> errorList) {
        this.errorList = errorList;
    }

    public Object[] getObjects() {
        return objects;
    }

    public void setObjects(Object[] objects) {
        this.objects = objects;
    }
}
