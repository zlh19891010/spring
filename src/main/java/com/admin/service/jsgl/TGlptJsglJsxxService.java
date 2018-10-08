package com.admin.service.jsgl;

import java.util.List;

import com.admin.entity.TGlptJsglJsyqxdygx;
import com.admin.entity.jsgl.TGlptJsglJsxx;
import com.admin.param.JsxxParam;
import com.admin.view.ResultJson;
import com.admin.view.ViewJsqxxx;
import com.admin.view.ViewTGlptJsglJsxx;
import com.admin.view.ViewXzjs;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

/**
 *
 * ClassName: TGlptJsglJsxxService <br/>
 * Function: 角色信息 服务类. <br/>
 * Date: 2017年3月1日 上午10:16:10 <br/>
 *
 * @author tangli
 * @version 1.0
 * @since JDK 1.7
 */
public interface TGlptJsglJsxxService extends IService<TGlptJsglJsxx> {
    /**
     *
     * getPagination,(角色信息分页). <br/>
     * Author: tangli <br/>
     * Create Date: 2017年3月1日 <br/>
     * ===============================================================<br/>
     * Modifier: tangli <br/>
     * Modify Date: 2017年3月1日 <br/>
     * Modify Description: <br/>
     * ===============================================================<br/>
     *
     * @param param 请求参数
     * @return 分页结果
     * @since JDK 1.7
     */
    //@MonitorLog(text = "获取角色信息分页service", type = MonitorLogInfo.METHOD_TYPE_SERVICE)
    Page<ViewTGlptJsglJsxx> getJsxxPagination(JsxxParam param);

    /**
     *
     * updateJsxx,(更新角色信息). <br/>
     * Author: tangli <br/>
     * Create Date: 2017年3月1日 <br/>
     * ===============================================================<br/>
     * Modifier: tangli <br/>
     * Modify Date: 2017年3月1日 <br/>
     * Modify Description: <br/>
     * ===============================================================<br/>
     *
     * @param zh 账号
     * @param param 请求参数
     * @return 结果
     * @since JDK 1.7
     */
    //@MonitorLog(text = "更新角色信息service", type = MonitorLogInfo.METHOD_TYPE_SERVICE)
    ResultJson updateJsxx(TGlptJsglJsxx param, String zh);

    /**
     *
     * getXtglModuleJsqxByParam,(根据系统代码和角色编号获取模块菜单). <br/>
     * Author: tangli <br/>
     * Create Date: 2017年3月1日 <br/>
     * ===============================================================<br/>
     * Modifier: tangli <br/>
     * Modify Date: 2017年3月1日 <br/>
     * Modify Description: <br/>
     * ===============================================================<br/>
     *
     * @param param 系统代码和角色编号
     * @return 结果
     * @since JDK 1.7
     */
    //@MonitorLog(text = "根据系统代码和角色编号获取模块菜单service", type = MonitorLogInfo.METHOD_TYPE_SERVICE)
    ViewJsqxxx getXtglModuleJsqxByParam(JsxxParam param);

    /**
     *
     * insertOrUpdateJsxx,(新增角色信息). <br/>
     * Author: tangli <br/>
     * Create Date: 2017年3月1日 <br/>
     * ===============================================================<br/>
     * Modifier: tangli <br/>
     * Modify Date: 2017年3月1日 <br/>
     * Modify Description: <br/>
     * ===============================================================<br/>
     *
     * @param zh 账号
     * @param param 请求参数
     * @return 结果
     * @since JDK 1.7
     */
    //@MonitorLog(text = "新增角色信息service", type = MonitorLogInfo.METHOD_TYPE_SERVICE)
    ResultJson insertOrUpdateJsxx(JsxxParam param, String zh);

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
    //@MonitorLog(text = "根据id,获取角色信息service", type = MonitorLogInfo.METHOD_TYPE_SERVICE)
    ViewXzjs getJsqlJsxxById(String id);

    /**
     *
     * getJsxx,(获取角色信息). <br/>
     * Author: 刘成 <br/>
     * Create Date: 2017年3月1日 <br/>
     * ===============================================================<br/>
     * Modifier: 刘成 <br/>
     * Modify Date: 2017年3月1日 <br/>
     * Modify Description: <br/>
     * ===============================================================<br/>
     *
     * @param param
     * @return
     * @since JDK 1.7
     */
    //@MonitorLog(text = "获取角色信息service", type = MonitorLogInfo.METHOD_TYPE_SERVICE)
    TGlptJsglJsxx getJsxx(TGlptJsglJsxx param);

    /**
     * selectJsxx,(获取所有角色信息). <br/>
     * Author: shaopei<br/>
     * Create Date: 2017年3月6日 <br/>
     * ===============================================================<br/>
     * Modifier: shaopei <br/>
     * Modify Date: 2017年3月6日 <br/>
     * Modify Description: <br/>
     * ===============================================================<br/>
     *
     * @param tGlptJsglJsxx 参数
     * @return 结果
     * @since JDK 1.7
     */
    //@MonitorLog(text = "获取所有角色信息service", type = MonitorLogInfo.METHOD_TYPE_SERVICE)
    List<TGlptJsglJsxx> selectJsxx(TGlptJsglJsxx tGlptJsglJsxx);

    /**
     *
     * getDygxListByParam,(根据角色id,和角色归属查询结果). <br/>
     * Author: tangli <br/>
     * Create Date: 2017年3月6日 <br/>
     * ===============================================================<br/>
     * Modifier: tangli <br/>
     * Modify Date: 2017年3月6日 <br/>
     * Modify Description: <br/>
     * ===============================================================<br/>
     *
     * @param jsgs 角色归属
     * @param jsid 角色id
     * @return 结果
     * @since JDK 1.7
     */
    //@MonitorLog(text = "根据角色id,和角色归属查询结果service", type = MonitorLogInfo.METHOD_TYPE_SERVICE)
    List<TGlptJsglJsyqxdygx> getDygxListByParam(String jsgs, String jsid);

    /**
     * 
     * getJsxxListByJsgs,(根据角色归属（平台or运营)获取). <br/>
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
    //@MonitorLog(text = "根据角色归属（平台or运营)获取service", type = MonitorLogInfo.METHOD_TYPE_SERVICE)
    List<TGlptJsglJsxx> getJsxxListByJsgs(String jsgs);

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
     * @param param 角色归属&角色名称
     * @return 结果
     * @since JDK 1.7
     */
    ResultJson getJsxxByJsmc(TGlptJsglJsxx param);

    /**
     * selectYhCzjb,((查询用户操作级别). <br/>
     * Author: shaopei<br/>
     * Create Date: 2017年3月10日 <br/>
     * ===============================================================<br/>
     * Modifier: shaopei <br/>
     * Modify Date: 2017年3月10日 <br/>
     * Modify Description: <br/>
     * ===============================================================<br/>
     * 
     * @param param 参数
     * @param jsId 参数
     * @return 结果
     * @since JDK 1.7
     */
    TGlptJsglJsyqxdygx getJsxxZcjb(TGlptJsglJsyqxdygx param, String jsId);

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
    TGlptJsglJsxx getKfpJsByJsgs(String jsgs);

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
     * @param jsid
     * @return
     * @since JDK 1.7
     */
    List<TGlptJsglJsxx> getAllxjjsList(String jsgs, String jsid);
}
