/**
 * Project Name:vr-admin
 * File Name:UploadUtil.java
 * Package Name:com.sd.vr.admin.utils
 * Date:2017年3月6日下午4:08:48
 *
 */

package com.admin.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;



/**
 * ClassName:UploadUtil <br/>
 * Function: 上传工具类. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2017年3月6日 下午4:08:48 <br/>
 *
 * @author ZhouLanHui
 * @version
 * @since JDK 1.7
 * @see
 */
public class UploadUtil {

    /**
     *
     * upload,(上传通用方法). <br/>
     * Author: ZhouLanHui <br/>
     * Create Date: 2017年3月6日 <br/>
     * ===============================================================<br/>
     * Modifier: ZhouLanHui <br/>
     * Modify Date: 2017年3月6日 <br/>
     * Modify Description: <br/>
     * ===============================================================<br/>
     *
     * @param uploadUrl 上传地址
     * @param in 文件流
     * @param fileName 文件名称
     * @param fileSize 文件大小
     * @param fileId 文件ID
     * @param fileInfo 文件信息
     * @return 返回上传的结果
     * @since JDK 1.7
     */
    public static String upload(String uploadUrl, InputStream in, FileInfoParam fileInfo, String fileName, String fileSize, Object fileId) {
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        CloseableHttpClient httpClient = httpClientBuilder.build();
        HttpPost httppost = new HttpPost(uploadUrl);
        try {
            MultipartEntityBuilder entityBuilder = MultipartEntityBuilder.create();
            entityBuilder.addBinaryBody("files", in);
            entityBuilder.addTextBody("fileName", fileName);
            entityBuilder.addTextBody("fileSize", fileSize);
            entityBuilder.addTextBody("totalSize", String.valueOf(fileInfo.getSize()));
            if (fileId == null) {
                fileId = "";
            }
            entityBuilder.addTextBody("fileId", String.valueOf(fileId));
            HttpEntity reqEntity = entityBuilder.build();
            httppost.setEntity(reqEntity);
            HttpResponse response = httpClient.execute(httppost);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == HttpStatus.SC_OK) {
                System.out.println("服务器正常响应.....");
                HttpEntity resEntity = response.getEntity();
                String res = EntityUtils.toString(resEntity);
                EntityUtils.consume(resEntity);
                return res;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
