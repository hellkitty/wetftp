package org.iptime.dinky.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.iptime.dinky.domain.Member;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LogInProcessInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		HttpSession session =  request.getSession();
		if(session.getAttribute("member")==null){
			response.sendRedirect("memberLogin");
			return false;
		} else if(((Member)session.getAttribute("member")).getMemberGrade()>2){
			response.sendRedirect("hasNoPermission");
			return false;
		}
		
		return true;
	}
	
}
