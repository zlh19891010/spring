package com.admin.entity;

import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>
 * 
 * </p>
 *
 * @author weiming.chen
 * @since 2017-03-04
 */
@TableName("t_glpt_code")
public class TGlptCode extends BaseModel {
    /**
     * 序列化
     */
    private static final long serialVersionUID = 1L;
    /** 父类id */
    private String parentid;

    /** code代码 */
    private String codeclass;

    /** code中文解释 */
    private String codeclassdesc;

    /** code编码 */
    private String code;

    /** code讲中文解析 */
    private String codedesc;

    /** code 拼音 */
    private String spellcode;

    /** code 等级 */
    private String codelevel;

    /** 显示顺序 */
    private String displayorder;

    /** 选择表 */
    private String selectable;

    /** 树路径 */
    private String treepath;

    /** 状态 */
    private String status;

    /** 版本 */
    private String versions;

    /**
     * 父类id.
     *
     * @return the parentid
     * @since JDK 1.7
     */
    public String getParentid() {
        return parentid;
    }

    /**
     * 父类id.
     *
     * @param parentid the 父类id to set
     * @since JDK 1.7
     */
    public void setParentid(String parentid) {
        this.parentid = parentid;
    }

    /**
     * code代码.
     *
     * @return the codeclass
     * @since JDK 1.7
     */
    public String getCodeclass() {
        return codeclass;
    }

    /**
     * code代码.
     *
     * @param codeclass the code代码 to set
     * @since JDK 1.7
     */
    public void setCodeclass(String codeclass) {
        this.codeclass = codeclass;
    }

    /**
     * code中文解释.
     *
     * @return the codeclassdesc
     * @since JDK 1.7
     */
    public String getCodeclassdesc() {
        return codeclassdesc;
    }

    /**
     * code中文解释.
     *
     * @param codeclassdesc the code中文解释 to set
     * @since JDK 1.7
     */
    public void setCodeclassdesc(String codeclassdesc) {
        this.codeclassdesc = codeclassdesc;
    }

    /**
     * code编码.
     *
     * @return the code编码
     * @since JDK 1.7
     */
    public String getCode() {
        return code;
    }

    /**
     * code编码.
     *
     * @param code the code编码 to set
     * @since JDK 1.7
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * code讲中文解析.
     *
     * @return the codedesc
     * @since JDK 1.7
     */
    public String getCodedesc() {
        return codedesc;
    }

    /**
     * code讲中文解析.
     *
     * @param codedesc the code讲中文解析 to set
     * @since JDK 1.7
     */
    public void setCodedesc(String codedesc) {
        this.codedesc = codedesc;
    }

    /**
     * code拼音.
     *
     * @return the spellcode
     * @since JDK 1.7
     */
    public String getSpellcode() {
        return spellcode;
    }

    /**
     * code拼音.
     *
     * @param spellcode the code拼音 to set
     * @since JDK 1.7
     */
    public void setSpellcode(String spellcode) {
        this.spellcode = spellcode;
    }

    /**
     * code等级.
     *
     * @return the codelevel
     * @since JDK 1.7
     */
    public String getCodelevel() {
        return codelevel;
    }

    /**
     * code等级.
     *
     * @param codelevel the code等级 to set
     * @since JDK 1.7
     */
    public void setCodelevel(String codelevel) {
        this.codelevel = codelevel;
    }

    /**
     * 显示顺序.
     *
     * @return the displayorder
     * @since JDK 1.7
     */
    public String getDisplayorder() {
        return displayorder;
    }

    /**
     * 显示顺序.
     *
     * @param displayorder the 显示顺序 to set
     * @since JDK 1.7
     */
    public void setDisplayorder(String displayorder) {
        this.displayorder = displayorder;
    }

    /**
     * 选择表.
     *
     * @return the selectable
     * @since JDK 1.7
     */
    public String getSelectable() {
        return selectable;
    }

    /**
     * 选择表.
     *
     * @param selectable the 选择表 to set
     * @since JDK 1.7
     */
    public void setSelectable(String selectable) {
        this.selectable = selectable;
    }

    /**
     * 树路径.
     *
     * @return the treepath
     * @since JDK 1.7
     */
    public String getTreepath() {
        return treepath;
    }

    /**
     * 树路径.
     *
     * @param treepath the 树路径 to set
     * @since JDK 1.7
     */
    public void setTreepath(String treepath) {
        this.treepath = treepath;
    }

    /**
     * 状态.
     *
     * @return the status
     * @since JDK 1.7
     */
    public String getStatus() {
        return status;
    }

    /**
     * 状态.
     *
     * @param status the 状态 to set
     * @since JDK 1.7
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 版本.
     *
     * @return the versions
     * @since JDK 1.7
     */
    public String getVersions() {
        return versions;
    }

    /**
     * 版本.
     *
     * @param versions the 版本 to set
     * @since JDK 1.7
     */
    public void setVersions(String versions) {
        this.versions = versions;
    }

}
