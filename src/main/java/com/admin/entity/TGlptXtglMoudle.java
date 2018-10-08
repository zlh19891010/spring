package com.admin.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * 
 * ClassName: TGlptXtglMoudle <br/>
 * Function: 模块菜单Entity. <br/>
 * Date: 2017年3月1日 下午4:23:47 <br/>
 *
 * @author tangli
 * @version 1.0
 * @since JDK 1.7
 */
@TableName("t_glpt_xtgl_moudle")
public class TGlptXtglMoudle extends BaseModel {

    /**
     * serialVersionUID:TODO(序列化).
     * 
     * @since JDK 1.7
     */
    private static final long serialVersionUID = 1L;

    /**
     * 父模块编号
     */
    @TableField("parent_id")
    private String parentId;
    /**
     * 模块名称
     */
    @TableField("module_name")
    private String moduleName;
    /**
     * 模块代码
     */
    @TableField("module_code")
    private String moduleCode;
    /**
     * 模块等级
     */
    @TableField("module_level")
    private Integer moduleLevel;
    /**
     * 菜单编号
     */
    @TableField("menu_id")
    private String menuId;
    /**
     * 菜单编号
     */
    @TableField("css_path")
    private String cssPath;
    /**
     * 系统代码（GLPT代表管理平台,YYZX代表运营中心）
     */
    @TableField("system_code")
    private String systemCode;
    /**
     * 模块路径
     */
    @TableField("tree_path")
    private String treePath;
    /**
     * 状态(1-正常，0-停用)
     */
    private String status;
    /**
     * 显示顺序
     */
    @TableField("display_order")
    private String displayOrder;

    /**
     * 父模块编号.
     *
     * @return the parentId
     * @since JDK 1.7
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * 父模块编号.
     *
     * @param parentId the 父模块编号 to set
     * @since JDK 1.7
     */
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    /**
     * 模块名称.
     *
     * @return the moduleName
     * @since JDK 1.7
     */
    public String getModuleName() {
        return moduleName;
    }

    /**
     * 模块名称.
     *
     * @param moduleName the 模块名称 to set
     * @since JDK 1.7
     */
    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    /**
     * 模块代码.
     *
     * @return the moduleCode
     * @since JDK 1.7
     */
    public String getModuleCode() {
        return moduleCode;
    }

    /**
     * 模块代码.
     *
     * @param moduleCode the 模块代码 to set
     * @since JDK 1.7
     */
    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    /**
     * 模块等级.
     *
     * @return the moduleLevel
     * @since JDK 1.7
     */
    public Integer getModuleLevel() {
        return moduleLevel;
    }

    /**
     * 模块等级.
     *
     * @param moduleLevel the 模块等级 to set
     * @since JDK 1.7
     */
    public void setModuleLevel(Integer moduleLevel) {
        this.moduleLevel = moduleLevel;
    }

    /**
     * 菜单编号.
     *
     * @return the menuId
     * @since JDK 1.7
     */
    public String getMenuId() {
        return menuId;
    }

    /**
     * 菜单编号.
     *
     * @param menuId the 菜单编号 to set
     * @since JDK 1.7
     */
    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    /**
     * 系统代码（GLPT代表管理平台YYZX代表运营中心）.
     *
     * @return the systemCode
     * @since JDK 1.7
     */
    public String getSystemCode() {
        return systemCode;
    }

    /**
     * 系统代码（GLPT代表管理平台YYZX代表运营中心）.
     *
     * @param systemCode the 系统代码（GLPT代表管理平台YYZX代表运营中心） to set
     * @since JDK 1.7
     */
    public void setSystemCode(String systemCode) {
        this.systemCode = systemCode;
    }

    /**
     * 模块路径.
     *
     * @return the treePath
     * @since JDK 1.7
     */
    public String getTreePath() {
        return treePath;
    }

    /**
     * 模块路径.
     *
     * @param treePath the 模块路径 to set
     * @since JDK 1.7
     */
    public void setTreePath(String treePath) {
        this.treePath = treePath;
    }

    /**
     * 状态(1-正常，0-停用).
     *
     * @return the status
     * @since JDK 1.7
     */
    public String getStatus() {
        return status;
    }

    /**
     * 状态(1-正常，0-停用).
     *
     * @param status the 状态(1-正常，0-停用) to set
     * @since JDK 1.7
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 显示顺序.
     *
     * @return the displayOrder
     * @since JDK 1.7
     */
    public String getDisplayOrder() {
        return displayOrder;
    }

    /**
     * 显示顺序.
     *
     * @param displayOrder the 显示顺序 to set
     * @since JDK 1.7
     */
    public void setDisplayOrder(String displayOrder) {
        this.displayOrder = displayOrder;
    }

    public String getCssPath() {
        return cssPath;
    }

    public void setCssPath(String cssPath) {
        this.cssPath = cssPath;
    }

	public int compareTo(TGlptXtglMoudle o) {
		// TODO Auto-generated method stub
		return 0;
	}

    
}
