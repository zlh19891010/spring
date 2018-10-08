package com.admin.service.jsgl.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.admin.common.CodeConstants;
import com.admin.common.logger.ModuleInfo;
import com.admin.common.logger.MonitorLog;
import com.admin.common.logger.MonitorLogInfo;
import com.admin.content.CommonConstants;
import com.admin.dao.glygl.TGlptGlyglGlyxxMapper;
import com.admin.dao.jsgl.TGlptJsglJsxxMapper;
import com.admin.dao.jsgl.TGlptJsglJsyqxdygxMapper;
import com.admin.dao.xtgl.TGlptXtglMoudleMapper;
import com.admin.entity.TGlptCode;
import com.admin.entity.TGlptJsglJsyqxdygx;
import com.admin.entity.TGlptXtglMoudle;
import com.admin.entity.jsgl.TGlptJsglJsxx;
import com.admin.param.JsxxParam;
import com.admin.service.CommonService;
import com.admin.service.jsgl.TGlptJsglJsxxService;
import com.admin.view.ResultJson;
import com.admin.view.ViewJsqxxx;
import com.admin.view.ViewTGlptJsglJsxx;
import com.admin.view.ViewXzjs;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

/**
 *
 * ClassName: TGlptJsglJsxxServiceImpl <br/>
 * Function: 角色信息 服务实现类. <br/>
 * Date: 2017年3月1日 上午10:14:59 <br/>
 *
 * @author tangli
 * @version 1.0
 * @since JDK 1.7
 */
@Service
public class TGlptJsglJsxxServiceImpl extends ServiceImpl<TGlptJsglJsxxMapper, TGlptJsglJsxx> implements TGlptJsglJsxxService {
    /** 角色信息Mapper */
    @Autowired
    private TGlptJsglJsxxMapper tGlptJsglJsxxMapper;
    /** 模块菜单Mapper */
    @Autowired
    private TGlptXtglMoudleMapper tGlptXtglMoudleMapper;
    /** 角色权限对应关系Mapper */
    @Autowired
    private TGlptJsglJsyqxdygxMapper tGlptJsglJsyqxdygxMapper;
    /** 管理员权限对应关系Mapper */
    @Autowired
    private TGlptGlyglGlyxxMapper tGlptGlyglGlyxxMapper;
    /** CommonService */
    @Autowired
    private CommonService commonService;

    /**
     *
     * getPagination,(角色分页). <br/>
     * Author: tangli <br/>
     * Create Date: 2017年3月1日 <br/>
     * =============================================================== <br/>
     * Modifier: tangli <br/>
     * Modify Date: 2017年3月1日 <br/>
     * Modify Description: <br/>
     * =============================================================== <br/>
     *
     * @param page 分页参数,当前角色id&角色名称
     * @return 结果
     * @since JDK 1.7
     */
    @Override
  //  @MonitorLog(text = "获取角色分页service", type = MonitorLogInfo.METHOD_TYPE_SERVICE,containRequest=true,module=ModuleInfo.JSGL)
    public Page<ViewTGlptJsglJsxx> getJsxxPagination(JsxxParam param) {
        logger.info("===角色分页begin===");
        Page<ViewTGlptJsglJsxx> page = new Page<ViewTGlptJsglJsxx>(param.getCurrent(), param.getSize());
        List<TGlptJsglJsxx> result = tGlptJsglJsxxMapper.getJsxxList(page, param);
        List<ViewTGlptJsglJsxx> viewjsxx = new ArrayList<ViewTGlptJsglJsxx>();
        Map<String, TGlptCode> jsgsMap = commonService.getCodeMapByCodeclass(CodeConstants.JSGS);
        Map<String, TGlptCode> jsztMap = commonService.getCodeMapByCodeclass(CodeConstants.JSZT);
        for (TGlptJsglJsxx tGlptJsglJsxx : result) {
            ViewTGlptJsglJsxx temp = new ViewTGlptJsglJsxx();
            BeanUtils.copyProperties(tGlptJsglJsxx, temp);
            //设置角色归属
            String jsgsKey = CodeConstants.JSGS + temp.getJsgs();
            temp.setJsgsDesc(jsgsMap.get(jsgsKey).getCodedesc());
            //设置角色状态
            String jsztKey = CodeConstants.JSZT + temp.getJszt();
            temp.setJsztDesc(jsztMap.get(jsztKey).getCodedesc());
            temp.setBjBtn(true);
            temp.setScBtn(false);
            //查询是否是叶子节点，且，没有被引用
            TGlptJsglJsxx jsxx = new TGlptJsglJsxx();
            jsxx.setSjjsId(tGlptJsglJsxx.getId());
            TGlptJsglJsxx showBtn = tGlptJsglJsxxMapper.getJsxx(jsxx);
            if (showBtn == null || StringUtils.isBlank(showBtn.getId())) {
                List<String> items = tGlptGlyglGlyxxMapper.getYhxxByJsid(tGlptJsglJsxx.getId());
                if (CollectionUtils.isEmpty(items)) {
                    //最底层，不存在引用，则显示删除按钮
                    temp.setScBtn(true);
                }
            }
            viewjsxx.add(temp);
        }
        page.setRecords(viewjsxx);
        logger.info("===角色分页end===");
        return page;

    }

    /**
     *
     * updateJsxx,(更新角色信息-启用，停用，删除). <br/>
     * Author: tangli <br/>
     * Create Date: 2017年3月1日 <br/>
     * =============================================================== <br/>
     * Modifier: tangli <br/>
     * Modify Date: 2017年3月1日 <br/>
     * Modify Description: <br/>
     * =============================================================== <br/>
     *
     * @param zh 账号
     * @param param 请求参数
     * @return 结果
     * @since JDK 1.7
     */
    @Override
   // @MonitorLog(text = "获取更新角色信息service", type = MonitorLogInfo.METHOD_TYPE_SERVICE,containRequest=true,module=ModuleInfo.JSGL)
    public ResultJson updateJsxx(TGlptJsglJsxx param, String zh) {
        ResultJson result = new ResultJson(false);
        //判断参数
        if (StringUtils.isBlank(param.getId())) {
            return result;
        }
        logger.info("===更新角色信息begin===id:" + param.getId());
        TGlptJsglJsxx jsxx = new TGlptJsglJsxx();
        jsxx.setId(param.getId());

        if (StringUtils.isBlank(param.getJszt())) {
            jsxx.setDelflag(CommonConstants.DELFLAG_DELETE);
        } else {
            //角色状态（01未启用，02已启用，03已停用）'
            jsxx.setJszt(param.getJszt());
            jsxx.setDelflag(CommonConstants.DELFLAG_UPDATE);
        }
        jsxx.setOperaterCode(zh);
        jsxx.setOperateTime(new Date());
        Integer cnt = tGlptJsglJsxxMapper.updateById(jsxx);
        if (cnt > 0) {
            result.setResult(true);
        }
        logger.info("===更新角色信息end===id:" + param.getId());
        return result;
    }

    /**
     *
     * getXtglModuleBySystemCode,(根据系统代码和角色编号获取模块菜单). <br/>
     * Author: tangli <br/>
     * Create Date: 2017年3月1日 <br/>
     * =============================================================== <br/>
     * Modifier: tangli <br/>
     * Modify Date: 2017年3月1日 <br/>
     * Modify Description: <br/>
     * =============================================================== <br/>
     *
     * @param param 系统代码和角色编号
     * @return 结果
     * @since JDK 1.7
     */
    @Override
  //  @MonitorLog(text = "获取根据系统代码和角色编号获取模块菜单service", type = MonitorLogInfo.METHOD_TYPE_SERVICE,containRequest=true,module=ModuleInfo.JSGL)
    public ViewJsqxxx getXtglModuleJsqxByParam(JsxxParam param) {
        logger.info("===根据系统代码和角色编号获取模块菜单begin===");
        ViewJsqxxx view = new ViewJsqxxx();

        EntityWrapper<TGlptXtglMoudle> wrapper = new EntityWrapper<TGlptXtglMoudle>();
        wrapper.ne("delflag", CommonConstants.DELFLAG_DELETE);

        if (StringUtils.isNotBlank(param.getJsgs())) {
            wrapper.eq("system_code", param.getJsgs());
        }
        wrapper.eq("status", CodeConstants.STATUS_ZC);
        wrapper.orderBy("display_order", true);
        //获取可分配的权限
        List<TGlptXtglMoudle> result = tGlptXtglMoudleMapper.selectList(wrapper);
        view.setModuleList(result);
        //根据角色编号，获取已分配权限
        if (StringUtils.isBlank(param.getId())) {
            return view;
        }
        EntityWrapper<TGlptJsglJsyqxdygx> qxgx = new EntityWrapper<TGlptJsglJsyqxdygx>();
        qxgx.ne("delflag", CommonConstants.DELFLAG_DELETE);
        if (StringUtils.isNotBlank(param.getId())) {
            qxgx.eq("js_id", param.getId());
        }
        qxgx.eq("jsgs", param.getJsgs());
        qxgx.orderBy("create_time", false);
        List<TGlptJsglJsyqxdygx> yfpqxList = tGlptJsglJsyqxdygxMapper.selectList(qxgx);
        view.setYfpqxList(yfpqxList);
        logger.info("===根据系统代码和角色编号获取模块菜单end===");
        return view;
    }

    /**
     *
     * insertJsxx,(新增or更新角色信息). <br/>
     * Author: tangli <br/>
     * Create Date: 2017年3月1日 <br/>
     * =============================================================== <br/>
     * Modifier: tangli <br/>
     * Modify Date: 2017年3月1日 <br/>
     * Modify Description: <br/>
     * =============================================================== <br/>
     *
     * @param param 请求参数
     * @param zh 账号
     * @return 结果
     * @since JDK 1.7
     */
    @Override
    @MonitorLog(text = "新增or更新角色信息service", type = MonitorLogInfo.METHOD_TYPE_SERVICE,containRequest=true,module=ModuleInfo.JSGL)
    public ResultJson insertOrUpdateJsxx(JsxxParam param, String zh) {
        logger.info("===新增or更新角色信息begin===");
        ResultJson result = new ResultJson(false);
        TGlptJsglJsxx jsxx = null;
        //角色名称,角色描述，角色归属，不能为空
        if (StringUtils.isBlank(param.getJsmc()) || StringUtils.isBlank(param.getJsms()) || StringUtils.isBlank(param.getJsgs())) {
            return result;
        }

        if (StringUtils.isNotBlank(param.getId())) {
            jsxx = tGlptJsglJsxxMapper.selectById(param.getId());
        }
        //封装参数
        TGlptJsglJsxx temp = new TGlptJsglJsxx();
        if (StringUtils.isNotBlank(param.getJsmc())) {
            temp.setJsmc(param.getJsmc());
        }
        if (StringUtils.isBlank(param.getId())) {
            temp.setJsjb(param.getJsjb());
        }
        temp.setJszt(CodeConstants.QYZT_YQY);
        temp.setJsgs(param.getJsgs());
        temp.setJsms(param.getJsms());
        temp.setOperaterCode(zh);
        temp.setOperateTime(new Date());

        if (jsxx != null && StringUtils.isNotBlank(jsxx.getId())) {
            //存在更新
            temp.setId(param.getId());
            temp.setDelflag(CommonConstants.DELFLAG_UPDATE);
            temp.setOperaterCode(zh);
            temp.setOperateTime(new Date());
            tGlptJsglJsxxMapper.updateById(temp);

            //            TGlptJsglJsyqxdygx dygx = new TGlptJsglJsyqxdygx();
            //            //dygx.setJsId(param.getId());
            //            dygx.setOperaterCode(zh);
            //            dygx.setOperateTime(new Date());
            //            dygx.setDelflag(CommonConstants.DELFLAG_DELETE);
            //            EntityWrapper<TGlptJsglJsyqxdygx> wrapper = new EntityWrapper<TGlptJsglJsyqxdygx>();
            //            wrapper.ne("delflag", CommonConstants.DELFLAG_DELETE);
            //            wrapper.eq("js_id", param.getId());
            //            tGlptJsglJsyqxdygxMapper.update(dygx, wrapper);
            // tGlptJsglJsyqxdygxMapper.updateJsidDygx(dygx);
        } else {
            //新增
            if (StringUtils.isNotBlank(param.getSjjsId())) {
                temp.setSjjsId(param.getSjjsId());
            }
            //temp.setJszt(CodeConstants.QYZT_YQY);
            temp.setCreaterCode(zh);
            temp.setId(this.getKey());
            temp.setCreateTime(new Date());
            temp.setDelflag(CommonConstants.DELFLAG_ADD);
            tGlptJsglJsxxMapper.insert(temp);
        }
        //新增分配权限
        if (CollectionUtils.isNotEmpty(param.getYfpqxList())) {
            List<TGlptJsglJsyqxdygx> paramList = new ArrayList<TGlptJsglJsyqxdygx>();
            for (TGlptJsglJsyqxdygx item : param.getYfpqxList()) {
                TGlptJsglJsyqxdygx entity = new TGlptJsglJsyqxdygx();
                entity.setId(this.getKey());
                if (jsxx != null && StringUtils.isNotBlank(jsxx.getId())) {
                    entity.setJsId(param.getId());
                } else {
                    entity.setJsId(temp.getId());
                }
                //设置操作级别
                entity.setCzjb(item.getCzjb());
                entity.setQxCode(item.getQxCode());
                entity.setJsgs(param.getJsgs());
                entity.setDelflag(CommonConstants.DELFLAG_ADD);
                entity.setCreaterCode(zh);
                entity.setCreateTime(new Date());
                entity.setOperaterCode(zh);
                entity.setOperateTime(new Date());
                paramList.add(entity);
            }
            tGlptJsglJsyqxdygxMapper.insertPl(paramList);
        }
        logger.info("===新增or更新角色信息end===");
        return new ResultJson(true);
    }

    /**
     *
     * getJsqlJsxxById,(根据id,获取角色信息). <br/>
     * Author: tangli <br/>
     * Create Date: 2017年3月2日 <br/>
     * =============================================================== <br/>
     * Modifier: tangli <br/>
     * Modify Date: 2017年3月2日 <br/>
     * Modify Description: <br/>
     * =============================================================== <br/>
     *
     * @param id 角色id
     * @return 结果
     * @since JDK 1.7
     */
    @Override
 //   @MonitorLog(text = "根据id,获取角色信息service", type = MonitorLogInfo.METHOD_TYPE_SERVICE,containRequest=true,module=ModuleInfo.JSGL)
    public ViewXzjs getJsqlJsxxById(String id) {
        logger.info("===根据id,获取角色信息begin===id:" + id);
        if (StringUtils.isBlank(id)) {
            return null;
        }
        ViewXzjs result = tGlptJsglJsxxMapper.getJsxxById(id);
        logger.info("===根据id,获取角色信息end===id:" + id);
        return result;
    }

    /**
     *
     * getJsxx,(获取角色信息). <br/>
     * Author: 刘成 <br/>
     * Create Date: 2017年3月1日 <br/>
     * =============================================================== <br/>
     * Modifier: 刘成 <br/>
     * Modify Date: 2017年3月1日 <br/>
     * Modify Description: <br/>
     * =============================================================== <br/>
     *
     * @param param
     * @return
     * @since JDK 1.7
     */
    @Override
 //   @MonitorLog(text = "获取角色信息service", type = MonitorLogInfo.METHOD_TYPE_SERVICE,containRequest=true,module=ModuleInfo.JSGL)
    public TGlptJsglJsxx getJsxx(TGlptJsglJsxx param) {

        return tGlptJsglJsxxMapper.getJsxx(param);
    }

    /**
     * selectJsxx,(获取所有角色信息). <br/>
     * Author: shaopei <br/>
     * Create Date: 2017年3月6日 <br/>
     * =============================================================== <br/>
     * Modifier:shaopei<br/>
     * Modify Date: 2017年3月6日 <br/>
     * Modify Description: <br/>
     * =============================================================== <br/>
     *
     * @return
     * @since JDK 1.7
     */
    @Override
 //   @MonitorLog(text = "获取所有角色信息service", type = MonitorLogInfo.METHOD_TYPE_SERVICE,containRequest=true,module=ModuleInfo.JSGL)
    public List<TGlptJsglJsxx> selectJsxx(TGlptJsglJsxx tGlptJsglJsxx) {
        TGlptJsglJsxx tGlptJsglJsxxs = selectById(tGlptJsglJsxx.getId());
        tGlptJsglJsxx.setJsjb(tGlptJsglJsxxs.getJsjb());
        if ("01".equals(tGlptJsglJsxx.getJsgs())) {
            return tGlptJsglJsxxMapper.selectGlyJsxx(tGlptJsglJsxx);
        }
        return tGlptJsglJsxxMapper.selectYyzxJsxx(tGlptJsglJsxx);
    }

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
    @Override
  //  @MonitorLog(text = "根据角色id,和角色归属查询结果service", type = MonitorLogInfo.METHOD_TYPE_SERVICE,containRequest=true,module=ModuleInfo.JSGL)
    public List<TGlptJsglJsyqxdygx> getDygxListByParam(String jsgs, String jsid) {
        TGlptJsglJsyqxdygx param = new TGlptJsglJsyqxdygx();
        if (StringUtils.isNotBlank(jsgs)) {
            param.setJsgs(jsgs);
        }
        if (StringUtils.isNotBlank(jsid)) {
            param.setJsId(jsid);
        }
        return tGlptJsglJsyqxdygxMapper.getDygxListByParam(param);
    }

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
    @Override
 //   @MonitorLog(text = "获取所有角色信息service", type = MonitorLogInfo.METHOD_TYPE_SERVICE,containRequest=true,module=ModuleInfo.JSGL)
    public List<TGlptJsglJsxx> getJsxxListByJsgs(String jsgs) {
        TGlptJsglJsxx param = new TGlptJsglJsxx();
        if (StringUtils.isNotBlank(jsgs)) {
            param.setJsgs(jsgs);
        }
        return tGlptJsglJsxxMapper.selectJsxx(param);
    }

    /**
     *
     * getJsxxByJsmc,(查询角色名称是否存在). <br/>
     * Author: tangli <br/>
     * Create Date: 2017年3月9日 <br/>
     * =============================================================== <br/>
     * Modifier: tangli <br/>
     * Modify Date: 2017年3月9日 <br/>
     * Modify Description: <br/>
     * =============================================================== <br/>
     *
     * @param param 角色归属&角色名称
     * @return 结果
     * @since JDK 1.7
     */
    @Override
 //   @MonitorLog(text = "查询角色名称是否存在service", type = MonitorLogInfo.METHOD_TYPE_SERVICE,containRequest=true,module=ModuleInfo.JSGL)
    public ResultJson getJsxxByJsmc(TGlptJsglJsxx param) {
        ResultJson result = new ResultJson(false);
        if (StringUtils.isBlank(param.getJsmc())) {
            return result;
        }
        if (StringUtils.isBlank(param.getJsgs())) {
            return result;
        }
        TGlptJsglJsxx jsxx = tGlptJsglJsxxMapper.getJsxxByJsmc(param);
        if (jsxx != null && StringUtils.isNotBlank(jsxx.getId())) {
            result.setResult(false);
            return result;
        }
        return new ResultJson(true);
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
    @Override
 //   @MonitorLog(text = "根据角色归属查询当前下的最小级别的记录service", type = MonitorLogInfo.METHOD_TYPE_SERVICE,containRequest=true,module=ModuleInfo.JSGL)
    public TGlptJsglJsxx getKfpJsByJsgs(String jsgs) {
        if (StringUtils.isBlank(jsgs)) {
            return null;
        }
        return tGlptJsglJsxxMapper.getKfpJsByJsgs(jsgs);
    }

    /**
     * getJsxxZcjb,((查询用户操作级别). <br/>
     * Author: shaopei <br/>
     * Create Date: 2017年3月10日 <br/>
     * =============================================================== <br/>
     * Modifier:shaopei<br/>
     * Modify Date: 2017年3月10日 <br/>
     * Modify Description: <br/>
     * =============================================================== <br/>
     *
     * @param param
     * @return
     * @since JDK 1.7
     */
    @Override
  //  @MonitorLog(text = "查询用户操作级别service", type = MonitorLogInfo.METHOD_TYPE_SERVICE,containRequest=true,module=ModuleInfo.JSGL)
    public TGlptJsglJsyqxdygx getJsxxZcjb(TGlptJsglJsyqxdygx param, String jsId) {
        if (StringUtils.isBlank(jsId)) {
            return null;
        }
        param.setJsId(jsId);
        return tGlptJsglJsyqxdygxMapper.selectYhCzjb(param);

    }

    /**
     * 
     * getAllxjjsList,(获取所有的下级角色). <br/>
     * Author: tangli <br/>
     * Create Date: 2017年4月14日 <br/>
     * =============================================================== <br/>
     * Modifier: tangli <br/>
     * Modify Date: 2017年4月14日 <br/>
     * Modify Description: <br/>
     * =============================================================== <br/>
     * 
     * @param jsgs 角色归属
     * @param jsid 角色id
     * @return
     * @since JDK 1.7
     */
    @Override
    public List<TGlptJsglJsxx> getAllxjjsList(String jsgs, String jsid) {
        if (StringUtils.isBlank(jsgs)) {
            return null;
        }
        return tGlptJsglJsxxMapper.getAllxjjsList(jsgs, jsid);
    }

}
