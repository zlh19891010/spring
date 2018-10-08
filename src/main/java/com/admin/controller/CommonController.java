/**
 * Project Name:vr-ctrl
 * File Name:CommonController.java
 * Package Name:com.sd.vr.ctrl.controller
 * Date:2017年3月4日上午11:40:45
 *
 */

package com.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.admin.common.BaseController;
import com.admin.common.CodeConstants;
import com.admin.dataSource.DynamicDataSource;
import com.admin.entity.TGlptCode;
import com.admin.service.CommonService;

/**
 * ClassName:CommonController <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2017年3月4日 上午11:40:45 <br/>
 *
 * @author tangli
 * @version
 * @since JDK 1.7
 * @see
 */
@RestController
public class CommonController extends BaseController {

	/** CommonService */
	@Autowired
	private CommonService commonService;

	/**
	 * 下载服务
	 */
	/*@Autowired
	private DownloadService downloadService;*/

	/**
	 *
	 * getCodeListByCodeclass,(根据codeclass获取TYyxtCode列表). <br/>
	 * Author: nifang <br/>
	 * Create Date: 2017年2月21日 <br/>
	 * ===============================================================<br/>
	 * Modifier: nifang <br/>
	 * Modify Date: 2017年2月21日 <br/>
	 * Modify Description: <br/>
	 * ===============================================================<br/>
	 *
	 * @param codeclass code类别
	 * @return TGlptCode列表
	 * @since JDK 1.7
	 */

	@RequestMapping("getCodeListByCodeclass")
	public List<TGlptCode> getCodeListByCodeclass(@RequestParam String codeclass) {
		DynamicDataSource.setDbType(CodeConstants.BASE_DATA_SOURCE);//设置后 就OK
		return commonService.getCodeListByCodeclass(codeclass);
	}

	/**
	 *
	 * getCodeByCodeclassAndCode,(根据codeclass和code获取TYyxtCode). <br/>
	 * Author: nifang <br/>
	 * Create Date: 2017年2月21日 <br/>
	 * ===============================================================<br/>
	 * Modifier: nifang <br/>
	 * Modify Date: 2017年2月21日 <br/>
	 * Modify Description: <br/>
	 * ===============================================================<br/>
	 *
	 * @param codeclass code类别
	 * @param code code值
	 * @return TGlptCode
	 * @since JDK 1.7
	 */
	@RequestMapping("getCodeByCodeclassAndCode")
	public TGlptCode getCodeByCodeclassAndCode(@RequestParam String codeclass, @RequestParam String code) {
		return commonService.getCodeByCodeclassAndCode(codeclass, code);
	}

	/**
	 *
	 * getFile,(直接查看文件). <br/>
	 * Author: ZhouLanHui <br/>
	 * Create Date: 2017年3月18日 <br/>
	 * ===============================================================<br/>
	 * Modifier: ZhouLanHui <br/>
	 * Modify Date: 2017年3月18日 <br/>
	 * Modify Description: <br/>
	 * ===============================================================<br/>
	 *
	 * @param url
	 * @return
	 * @since JDK 1.7
	 */
	/*@RequestMapping("getFile/**")
	public void getFile() {
		ServletOutputStream out = null;
		InputStream in = null;
		try {
			response.setDateHeader("Expires", System.currentTimeMillis() + 60 * 60 * 1000);
			out = response.getOutputStream();
			in = downloadService.getFile(extractPathFromPattern(request));
			byte[] b = new byte[1024];
			int len;
			while ((len = in.read(b)) > 0) {
				out.write(b, 0, len);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}*/

	/**
	 *
	 * getImage,(直接查看图片). <br/>
	 * Author: yaojie <br/>
	 * Create Date: 2017年3月18日 <br/>
	 * ===============================================================<br/>
	 * Modifier: yaojie <br/>
	 * Modify Date: 2017年3月18日 <br/>
	 * Modify Description: <br/>
	 * ===============================================================<br/>
	 *
	 * @param url
	 * @return
	 * @since JDK 1.7
	 */
	/*@Login(action = Action.Skip)
	@RequestMapping("getImage/**")
	public void getImage() {
		ServletOutputStream out = null;
		InputStream in = null;
		try {
			response.setDateHeader("Expires", System.currentTimeMillis() + 60 * 60 * 1000);
			out = response.getOutputStream();
			long start = System.currentTimeMillis();
			in = downloadService.getFile(extractPathFromPattern(request));
			System.err.println("耗时：" + (System.currentTimeMillis() - start));
			byte[] b = new byte[1024];
			int len;
			while ((len = in.read(b)) > 0) {
				out.write(b, 0, len);
			}
			System.err.println("耗时All：" + (System.currentTimeMillis() - start));
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}*/
}
