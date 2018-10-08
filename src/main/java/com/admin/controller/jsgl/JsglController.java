package com.admin.controller.jsgl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.admin.common.BaseController;
import com.admin.common.logger.MonitorLog;
import com.admin.common.logger.MonitorLogInfo;
import com.admin.entity.TGlptJsglJsyqxdygx;
import com.admin.entity.jsgl.TGlptJsglJsxx;
import com.admin.param.JsxxParam;
import com.admin.service.jsgl.TGlptJsglJsxxService;
import com.admin.view.ResultJson;
import com.admin.view.ViewTGlptJsglJsxx;
import com.admin.view.ViewUserModel;
import com.admin.view.ViewXzjs;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * 
 * ClassName: JsglController <br/>
 * Function: 角色信息 前端控制器. <br/>
 * Date: 2017年3月1日 上午10:48:14 <br/>
 *
 * @author tangli
 * @version 1.0
 * @since JDK 1.7
 */
@RestController
public class JsglController extends BaseController {
    /** 角色信息管理service */
    @Autowired
    private TGlptJsglJsxxService tGlptJsglJsxxService;

    /**
     * 
     * getJsxxPagination,(角色信息分页). <br/>
     * Author: tangli <br/>
     * Create Date: 2017年3月1日 <br/>
     * ===============================================================<br/>
     * Modifier: tangli <br/>
     * Modify Date: 2017年3月1日 <br/>
     * Modify Description: <br/>
     * ===============================================================<br/>
     * 
     * @return 分页结果
     * @since JDK 1.7
     */
    @RequestMapping("getJsxxPagination")
    Page<ViewTGlptJsglJsxx> getJsxxPagination(@RequestBody JsxxParam param) {
        ViewUserModel userModel = getUserModel();
        if (userModel != null) {
            param.setId(userModel.getJsId());
            return tGlptJsglJsxxService.getJsxxPagination(param);
        }
        return null;

    }

    /**
     * 
     * updateJsxx,(更新角色信息根据id). <br/>
     * Author: tangli <br/>
     * Create Date: 2017年3月1日 <br/>
     * ===============================================================<br/>
     * Modifier: tangli <br/>
     * Modify Date: 2017年3月1日 <br/>
     * Modify Description: <br/>
     * ===============================================================<br/>
     * 
     * @param param 请求参数
     * @return 结果
     * @since JDK 1.7
     */
    @RequestMapping("updateJsxx")
    ResultJson updateJsxx(@RequestBody TGlptJsglJsxx param) {
        ViewUserModel userModel = getUserModel();
        if (userModel != null && StringUtils.isNotBlank(userModel.getZh())) {
            return tGlptJsglJsxxService.updateJsxx(param, userModel.getZh());
        }
        return null;

    }

    /**
     * 
     * getJsqlJsxxById,(根据id,获取角色信息). <br/>
     * Author: tangli <br/>
     * Create Date: 2017年3月2日 <br/>
     * ===============================================================<br/>
     * Modifier: tangli <br/>
     * Modify Date: 2017年3月2日 <br/>
     * Modify Description: <br/>
     * ===============================================================<br/>
     * 
     * @param id 角色id
     * @return 结果
     * @since JDK 1.7
     */
    @RequestMapping("getJsqlJsxxById")
    public ViewXzjs getJsqlJsxxById(@RequestParam String id) {
        return tGlptJsglJsxxService.getJsqlJsxxById(id);
    }

    /**
     * 
     * insertOrUpdateJsxx,(编辑角色). <br/>
     * Author: tangli <br/>
     * Create Date: 2017年3月2日 <br/>
     * ===============================================================<br/>
     * Modifier: tangli <br/>
     * Modify Date: 2017年3月2日 <br/>
     * Modify Description: <br/>
     * ===============================================================<br/>
     * 
     * @param param 请求参数
     * @return 结果返回
     * @since JDK 1.7
     */
    @RequestMapping("insertOrUpdateJsxx")
    public ResultJson insertOrUpdateJsxx(@RequestBody JsxxParam param) {
        ViewUserModel userModel = getUserModel();
        if (userModel != null && StringUtils.isNotBlank(userModel.getZh())) {
            return tGlptJsglJsxxService.insertOrUpdateJsxx(param, userModel.getZh());
        }
        return null;

    }

    /**
     * 
     * getJsxxListByJsgs,(根据角色归属获取对应角色列表). <br/>
     * Author: tangli <br/>
     * Create Date: 2017年3月9日 <br/>
     * ===============================================================<br/>
     * Modifier: tangli <br/>
     * Modify Date: 2017年3月9日 <br/>
     * Modify Description: <br/>
     * ===============================================================<br/>
     * 
     * @param jsgs 角色归属
     * @return 结果
     * @since JDK 1.7
     */
    @RequestMapping("getJsxxListByJsgs")
    public List<TGlptJsglJsxx> getJsxxListByJsgs(@RequestParam String jsgs) {
        return tGlptJsglJsxxService.getJsxxListByJsgs(jsgs);
    }

    /**
     * 
     * getJsxxByJsmc,(查询角色名称是否存在). <br/>
     * Author: tangli <br/>
     * Create Date: 2017年3月9日 <br/>
     * ===============================================================<br/>
     * Modifier: tangli <br/>
     * Modify Date: 2017年3月9日 <br/>
     * Modify Description: <br/>
     * ===============================================================<br/>
     * 
     * @param param 角色名称&角色归属
     * @return 结果
     * @since JDK 1.7
     */
    @RequestMapping("getJsxxByJsmc")
    public ResultJson getJsxxByJsmc(@RequestBody TGlptJsglJsxx param) {
        return tGlptJsglJsxxService.getJsxxByJsmc(param);

    }

    /**
     * getJsqxCzjb,(查询权限操作级别). <br/>
     * Author: shaopei<br/>
     * Create Date: 2017年3月10日 <br/>
     * ===============================================================<br/>
     * Modifier: shaopei <br/>
     * Modify Date: 2017年3月10日 <br/>
     * Modify Description: <br/>
     * ===============================================================<br/>
     * 
     * @param param 参数
     * @return 结果
     * @since JDK 1.7
     */
    @RequestMapping("searchQxczjb")
    public ResultJson getJsqxCzjb(@RequestBody TGlptJsglJsyqxdygx param) {
        ResultJson resultJson = new ResultJson();
        resultJson.setObject(tGlptJsglJsxxService.getJsxxZcjb(param, getUserModel().getJsId()));
        return resultJson;
    }

    /**
     * 
     * getKfpJsByJsgs,(根据角色归属查询当前下的最小级别的记录). <br/>
     * Author: tangli <br/>
     * Create Date: 2017年3月10日 <br/>
     * ===============================================================<br/>
     * Modifier: tangli <br/>
     * Modify Date: 2017年3月10日 <br/>
     * Modify Description: <br/>
     * ===============================================================<br/>
     * 
     * @param jsgs 角色归属
     * @return 结果
     * @since JDK 1.7
     */
    @RequestMapping("getKfpJsByJsgs")
    public TGlptJsglJsxx getKfpJsByJsgs(@RequestParam String jsgs) {
        return tGlptJsglJsxxService.getKfpJsByJsgs(jsgs);
    }

    /**
     * 
     * getAllxjjsList,(获取所有的下级角色). <br/>
     * Author: tangli <br/>
     * Create Date: 2017年4月14日 <br/>
     * ===============================================================<br/>
     * Modifier: tangli <br/>
     * Modify Date: 2017年4月14日 <br/>
     * Modify Description: <br/>
     * ===============================================================<br/>
     * 
     * @param jsgs
     * @return
     * @since JDK 1.7
     */
    @RequestMapping("getAllxjjsList")
    public List<TGlptJsglJsxx> getAllxjjsList(@RequestParam String jsgs) {
        ViewUserModel userModel = getUserModel();
        if (userModel != null) {
            return tGlptJsglJsxxService.getAllxjjsList(jsgs, userModel.getJsId());
        }
        return null;

    }

}
