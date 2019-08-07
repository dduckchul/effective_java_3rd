package ssg.ad.cm.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import ssg.ad.cm.annotation.BoUserOnly;
import ssg.framework.domain.UserInfo;
import ssg.framework.support.spring.authentication.SsgLoginService;
import ssg.framework.support.spring.exception.BizMsgException;
import ssg.framework.support.spring.interceptor.SsgLoginAdatper;


public class UserAuthCheckInterceptor extends HandlerInterceptorAdapter {
	
	@Autowired
	private SsgLoginAdatper ssgLoginAdatper;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {
		
		System.out.println(obj.getClass());
		if(!(obj instanceof HandlerMethod))	{
			return true;
		}
		
		HandlerMethod hadler = (HandlerMethod) obj;
		
		SsgLoginService ssgLoginService = ssgLoginAdatper.getLoginService();
		UserInfo userInfo = ssgLoginService.getUser(request, response);
		
		// boUser인지 권한 확인
		if( hadler.getMethodAnnotation(BoUserOnly.class) != null)	{
			if (!"10".equals(userInfo.getOccuUserDivCd()))	{
				throw new BizMsgException("permission deny");
			}
		}
		
		return true;
	}

}
