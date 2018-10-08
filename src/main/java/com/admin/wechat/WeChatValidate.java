package com.admin.wechat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import com.admin.common.CodeConstants;



public class WeChatValidate {

	private static String accessToken;
	private static String openID;
	private static String refreshToken;
	private static Long expires_in;



	/**
	 * 获取凭证
	 * @param code
	 * @return
	 */
	public static  String getAccessToken(String code) {

		String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+CodeConstants.APPID+"&secret="+CodeConstants.SECRET+"&code="+code+"&grant_type=authorization_code";
		URI uri = URI.create(url);
		HttpClient client = new DefaultHttpClient();
		HttpGet get = new HttpGet(uri);

		HttpResponse response;
		try {
			response = client.execute(get);
			if (response.getStatusLine().getStatusCode() == 200) {
				HttpEntity entity = response.getEntity();

				BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent(), "UTF-8"));
				StringBuilder sb = new StringBuilder();

				for (String temp = reader.readLine(); temp != null; temp = reader.readLine()) {
					sb.append(temp);
				}


				JSONObject object = new JSONObject(sb.toString().trim());
				accessToken = object.getString("access_token");
				openID = object.getString("openid");
				refreshToken = object.getString("refresh_token");
				expires_in = object.getLong("expires_in");
				return accessToken;
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 获取用户信息
	 */
	private void getUserInfo() {
		if (isAccessTokenIsInvalid() && System.currentTimeMillis() < expires_in) {
			String uri = "https://api.weixin.qq.com/sns/userinfo?access_token=" + accessToken + "&openid=" + openID;
			HttpClient client = new DefaultHttpClient();
			HttpGet get = new HttpGet(URI.create(uri));
			try {
				HttpResponse response = client.execute(get);
				if (response.getStatusLine().getStatusCode() == 200) {
					BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
					StringBuilder builder = new StringBuilder();
					for (String temp = reader.readLine(); temp != null; temp = reader.readLine()) {
						builder.append(temp);
					}
					JSONObject object = new JSONObject(builder.toString().trim());
					String nikeName = object.getString("nickname");
				}
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}



	/**
	 * 验证凭证
	 * @return
	 */
	private boolean isAccessTokenIsInvalid() {
		String url = "https://api.weixin.qq.com/sns/auth?access_token=" + accessToken + "&openid=" + openID;
		URI uri = URI.create(url);
		HttpClient client = new DefaultHttpClient();
		HttpGet get = new HttpGet(uri);
		HttpResponse response;
		try {
			response = client.execute(get);
			if (response.getStatusLine().getStatusCode() == 200) {
				HttpEntity entity = response.getEntity();

				BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent(), "UTF-8"));
				StringBuilder sb = new StringBuilder();

				for (String temp = reader.readLine(); temp != null; temp = reader.readLine()) {
					sb.append(temp);
				}
				JSONObject object = new JSONObject(sb.toString().trim());
				int errorCode = object.getInt("errcode");
				if (errorCode == 0) {
					return true;
				}
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}



	/**
	 * 刷新凭证
	 */
	private void refreshAccessToken() {
		String uri = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=APPID&grant_type=refresh_token&refresh_token="
				+ refreshToken;
		HttpClient client = new DefaultHttpClient();
		HttpGet get = new HttpGet(URI.create(uri));
		try {
			HttpResponse response = client.execute(get);
			if (response.getStatusLine().getStatusCode() == 200) {
				BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
				StringBuilder builder = new StringBuilder();
				for (String temp = reader.readLine(); temp != null; temp = reader.readLine()) {
					builder.append(temp);
				}
				JSONObject object = new JSONObject(builder.toString().trim());
				accessToken = object.getString("access_token");
				refreshToken = object.getString("refresh_token");
				openID = object.getString("openid");
				expires_in = object.getLong("expires_in");
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
