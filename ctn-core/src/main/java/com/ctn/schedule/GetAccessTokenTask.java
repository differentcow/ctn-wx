package com.ctn.schedule;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ctn.cache.AccessTokenCache;
import com.ctn.constant.Constant;
import com.ctn.entity.AccessToken;
import com.ctn.util.HttpRequestUtil;
import com.ctn.util.JsonMapper;

/**
 * 微信Access Token 获取定时任务
 * 
 * @author Barry.LJ.Huang
 *
 */
@Component
public class GetAccessTokenTask{
	private static Logger logger = Logger.getLogger(GetAccessTokenTask.class.getName());
	@Value("${wechat.token.url}")
	private String url;
	@Autowired
	private AccessTokenCache accessTokenCache;
	@Scheduled(fixedDelay=Constant.REFRESH_ACCESS_TOKEN)
	public void getAccessToken(){

		/*AccessToken at = new AccessToken();
		at.setAccess_token("Sij-kjsgEoG9CtE9Rm8Zi4FsEu-eMhRC5q1UtLePBj4GKSz17Bh65-OdHTWuU7jWahaZ8Wy-B53AldiIsVtajT_WNw1hxOU6ZnQT7eWiQkSgkZbt6oVpZ68W_IqhhWXsM0plDyvosTZJDgv2AwkPHg");
		at.setExpires_in("7200");
		at.setErrcode("0");
		at.setErrmsg("success");
		new AccessTokenCache().setAccessTokenCache(Constant.CACHE_ACCESS_TOKEN_KEY, at);
		System.out.println("Test");
		System.out.println(new AccessTokenCache().getAccessToken(Constant.CACHE_ACCESS_TOKEN_KEY));*/
		logger.info("access token url"+url);
//		AccessToken at = requestToken();
		
	
		if (accessTokenCache.getCacheManager() == null) {
			return;
		}
		
		accessTokenCache.setAccessTokenCache(Constant.CACHE_ACCESS_TOKEN_KEY, new AccessToken()/*at*/);
	}
	/**
	 * @return
	 */
	public AccessToken requestToken() {
		String returnCode = HttpRequestUtil.getRequest(url);
		AccessToken at = JsonMapper.getJsonMapperInstance().readValue(returnCode, AccessToken.class);
		return at;
	}

	/**
	 * 测试
	 * 
	 * @param args
	 */
	/**
	public static void main(String[] args) {
        
		String returnMsg = WebHttpConnection.getRequest("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx6eb8840d92a4bf47&secret=0ff4b305e7ce03fe945f5909ddcaccca");
		System.out.println(returnMsg);
		JSONObject jsonObj = JSONObject.fromObject(returnMsg);
		AccessToken at = (AccessToken)jsonObj.toBean(jsonObj, AccessToken.class);
		System.out.println(at.getAccess_token());
		System.out.println(at.getErrcode()+":"+at.getErrmsg());
		
		String param1 = "";
		String param2 = "";
		String param3 = "";
		String param4 = "";
		String param5 = "";
		String param6 = "";
		try {
			param1 = URLEncoder.encode("单据提交", "UTF-8");
			param2 = URLEncoder.encode("进度查询", "UTF-8");
			param3 = URLEncoder.encode("中联助手", "UTF-8");
			param4 = URLEncoder.encode("下载客户端", "UTF-8"); 
			param5 = URLEncoder.encode("使用帮助", "UTF-8");
			param6 = URLEncoder.encode("客服热线", "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			param1 = "btn1";
			param2 = "btn2";
			param3 = "btn3";
			param4 = "btn4";
			param5 = "btn5";
			param6 = "btn6";
		}
		
		String btn = "{\"button\":[{\"type\":\"view\",\"name\":\""+param1+"\",\"url\":\"http://5.wechatmei.sinaapp.com/index/demo/submitForm\"},"
				         +"{\"type\":\"view\",\"name\":\""+param2+"\",\"url\":\"http://5.wechatmei.sinaapp.com/index/demo/queryProcess\"},"
				         +"{\"name\":\""+param3+"\",\"sub_button\":[{\"type\":\"view\",\"name\":\""+param4+"\",\"url\":\"http://5.wechatmei.sinaapp.com/index/demo/downloadClient\"},"
				         +"{\"type\":\"click\",\"name\":\""+param5+"\",\"key\":\"click_help\"},{\"type\":\"click\",\"name\":\""+param6+"\",\"key\":\"click_line\"}]}]}";
		String url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=tOdZ3HqbJMBTGy53C0sngUFQxXx0TULfsmc_QhkpjG-Pb66L_nrGPSTJ4_vovegYih1DiQ-7eMiIS40pj7mJj2Jnp-Wi6jfFZ14rnGwyVDVoCl--o5JrJRHXsoraY4RlcwVhcIsMKulSeDhXW-Hszg";
		String retrunMsg = WebHttpConnection.postRequest(url, btn);
		System.out.println(retrunMsg);
	}*/
}
