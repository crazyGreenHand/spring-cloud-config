package com.uuzz.config.interceptor;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 
 * @ClassName: FormSubmissionInterceptor
 * @Description: 防止表单重复提交拦截器
 * @author zj
 * @date 2017年6月22日 下午4:03:29
 *
 */
public class FormSubmissionInterceptor extends
		HandlerInterceptorAdapter {

	private static final String TOKEN = "token";

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		HandlerMethod handlerMethod = (HandlerMethod) handler;
		Token token = handlerMethod.getMethodAnnotation(Token.class);

		if (token == null) {
			return true;
		}

		// 生成token
		if (token.saveToken()) {
			request.getSession(true).setAttribute(TOKEN, UUID.randomUUID().toString());
		}
		// 移除token
		if (token.removeToken()) {
			if(isRepeatSubmit(request))return false;
			request.getSession(true).removeAttribute(TOKEN);
		}
		return true;
	}

	/**
	 * 
	* @Title: isRepeatSubmit  
	* @Description: 判断是否重复提交表单 
	* @param @param request
	* @param @return
	* @return boolean
	* @throws
	 */
	private boolean isRepeatSubmit(HttpServletRequest request) {
		
		String serverToken = (String) request.getSession(false).getAttribute(TOKEN);
				
		if (serverToken == null) {
			return true;
		}
		
		String clinetToken = request.getParameter(TOKEN);
		
		if (clinetToken == null) {
			return true;
		}
		if (!serverToken.equals(clinetToken)) {
			return true;
		}
		return false;
	}	
}
