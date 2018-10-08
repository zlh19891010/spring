/**
 * Project Name:vr-admin
 * File Name:DownLoadUtil.java
 * Package Name:com.sd.vr.admin.utils
 * Date:2017年2月27日下午4:46:44
 *
 */

package com.admin.utils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * ClassName:DownLoadUtil <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2017年2月27日 下午4:46:44 <br/>
 *
 * @author ZhouLanHui
 * @version
 * @since JDK 1.7
 * @see
 */
public final class DownLoadUtil {

    private DownLoadUtil() {

    }

    /**
     *
     * getNetFileSize,(获取网络文件的大小). <br/>
     * Author: ZhouLanHui <br/>
     * Create Date: 2017年2月27日 <br/>
     * ===============================================================<br/>
     * Modifier: ZhouLanHui <br/>
     * Modify Date: 2017年2月27日 <br/>
     * Modify Description: <br/>
     * ===============================================================<br/>
     *
     * @param url 文件路径
     * @return 文件大小
     * @since JDK 1.7
     */
    public static long getNetFileSize(String url) {
        long count = 0;
        try {
            URL fileUrl = new URL(url);
            HttpURLConnection httpconn = (HttpURLConnection) fileUrl.openConnection();
            count = httpconn.getContentLengthLong();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return count;
    }

}
