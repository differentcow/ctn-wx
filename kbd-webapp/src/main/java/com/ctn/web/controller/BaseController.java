package com.ctn.web.controller;

import com.ctn.cache.AccessTokenCache;
import com.ctn.constant.Constant;
import com.ctn.constant.WebHttpConnection;
import com.ctn.core.bean.HandlerBean;
import com.ctn.core.bean.HandlerMapping;
import com.ctn.core.bean.manager.HandlerBeanManager;
import com.ctn.entity.*;
import com.ctn.schedule.GetAccessTokenTask;
import com.ctn.service.MessageService;
import com.ctn.util.EncoderHandler;
import com.ctn.util.JsonMapper;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.TreeSet;
import java.util.logging.Logger;

@RestController
@RequestMapping("/core")
public class BaseController {
	private static Logger logger = Logger.getLogger(BaseController.class.getName());
	
	@Autowired
	private AccessTokenCache accessTokenCache;
	@Autowired
	private GetAccessTokenTask getAccessTokenTask;
	@Value("${wechat.token}")
	private String token;
	@Autowired
	private MessageService messageService;
    @Autowired
    private HandlerBeanManager handlerBeanManager;
    @Value("${wechat.developer.account}")
    private String developer;
    @Value("${server.address}")
    private String serverUrl;


	@RequestMapping(value = "/chat", method = RequestMethod.GET)
	public Object checkToken(HttpServletRequest req,
                            @RequestParam(value = "signature",required = false) String signature,
                            @RequestParam(value = "timestamp",required = false) String timestamp,
                            @RequestParam(value = "nonce",required = false) String nonce,
                            @RequestParam(value = "echostr",required = false) String echostr) {
		signature = StringUtils.isEmpty(signature) ? "0" : signature;
		token = StringUtils.isEmpty(token) ? "1" : token;
		nonce = StringUtils.isEmpty(nonce) ? "1" : nonce;
		timestamp = StringUtils.isEmpty(timestamp) ? "3" : timestamp;

        boolean flag = verifyMsg(timestamp, nonce, signature);
		/**
		 * 加密后的密文验证
		 */
		if (flag)
			return echostr;
		/** Success,返回微信提供的返回信号 */
		else
			return false;
		/** Failure, 什么都不做 */
	}

	private boolean verifyMsg(String timestamp, String nonce, String signature) {
		
		logger.info("timestamp:"+timestamp+"\nnonce:"+nonce+"\nsignature:"+signature);
		/**
		 * 对微信传回来的验证值进行加密处理
		 */
		TreeSet<String> access = new TreeSet<String>();
		access.add(token);
		access.add(nonce);
		access.add(timestamp);
		String signatureOrgin = access.toString()
				.substring(1, access.toString().length() - 1)
				.replaceAll("\\, ", "");
		String signatureSha1 = EncoderHandler.encode(
				EncoderHandler.ALGORITHM_SHA1, signatureOrgin);
		return signature.equals(signatureSha1);
	}



	@RequestMapping(value = "/chat", method = RequestMethod.POST)
	public Object msg(HttpServletRequest request,HttpServletResponse response,
                    @RequestParam(value="signature",required=false) String signature,
                    @RequestParam(value="timestamp",required=false) String timestamp,
                    @RequestParam(value="nonce",required=false) String nonce,
                    @RequestParam(value="echostr",required=false) String echostr,
                    @RequestBody Message msg) {

		logger.info("timestamp:"+timestamp+"\nnonce:"+nonce+"\nsignature:"+signature);
        boolean flag = verifyMsg(timestamp, nonce, signature);
//        boolean flag = true;
        /**
         * 通过验证后
         */
        if (flag){
            try {
                request.setAttribute("developer",developer);
                request.setAttribute("serverUrl",serverUrl);
                HandlerMapping mapping = handlerBeanManager.getHandlerMapping();
                HandlerBean bean = mapping.getHandlerBean(msg.getEvent(),msg.getMsgType());
                Method method = bean.getClazz().getMethod(bean.getMethodName(), HttpServletRequest.class,
                        HttpServletResponse.class, Message.class);
                return method.invoke(bean.getValue(),request,response,msg);

            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

		return null;
	}

	@RequestMapping(value = "/btn", method = RequestMethod.POST)
	public Object createBtn(HttpServletRequest req, @RequestBody Menu menu) {
		RestResult result = new RestResult();
		if (menu.getButton() != null && menu.getButton().size() > 3) {
			result.setCode(HttpStatus.SC_BAD_REQUEST);
			result.setMsg("最多只能有3个一级菜单");
			return result;
		}
		AccessToken at = getAccessToken();
		if (at != null && !StringUtils.isEmpty(at.getAccess_token())) {
			// String btn =
			// "{\"button\":[{\"type\":\"view\",\"name\":\""+param1+"\",\"url\":\"http://5.wechatmei.sinaapp.com/index/demo/submitForm\"},"
			// +"{\"type\":\"view\",\"name\":\""+param2+"\",\"url\":\"http://5.wechatmei.sinaapp.com/index/demo/queryProcess\"},"
			// +"{\"name\":\""+param3+"\",\"sub_button\":[{\"type\":\"view\",\"name\":\""+param4+"\",\"url\":\"http://5.wechatmei.sinaapp.com/index/demo/downloadClient\"},"
			// +"{\"type\":\"click\",\"name\":\""+param5+"\",\"key\":\"click_help\"},{\"type\":\"click\",\"name\":\""+param6+"\",\"key\":\"click_line\"}]}]}";
			String btn = JsonMapper.getJsonMapperInstance().toJson(menu);
			String url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token="
					+ at.getAccess_token();
			String retrunMsg = WebHttpConnection.postRequest(url, btn);
			WxResult response = JsonMapper.getJsonMapperInstance().readValue(
					retrunMsg, WxResult.class);
			if (response.getErrorCode() == 0) {
				result.setCode(HttpStatus.SC_OK);
				result.setMsg("创建成功");
			} else {
				result.setCode(response.getErrorCode());
				result.setMsg(response.getErrmsg());
			}
		} else {
			result.setCode(HttpStatus.SC_SERVICE_UNAVAILABLE);
			result.setMsg("创建失败");
		}
		return result;
	}

	/**
	 * @return
	 */
	private AccessToken getAccessToken() {
		if (accessTokenCache.getCacheManager() == null) {
			AccessToken at = getAccessTokenTask.requestToken();
			return at;
		} else {
			return accessTokenCache.getAccessToken(Constant.CACHE_ACCESS_TOKEN_KEY);
		}
	}
}
