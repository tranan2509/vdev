package org.vdev.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.vdev.constants.Constants;
import org.vdev.utils.DataChecker;

@Component
public class InitializationRequestFilter extends OncePerRequestFilter {

	@Autowired
	private Environment env;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		
		String path = request.getRequestURI().substring(request.getContextPath().length());
		
		if ("/api/initialization".equals(path)) {
			String privateKey = request.getHeader(Constants.INITIALIZATION_KEY);
			String envPrivateKey = env.getProperty("spring.initialization.private-key"); 
			if (!DataChecker.isEmpty(privateKey) && envPrivateKey.equals(privateKey)) {
				chain.doFilter(request, response);
			}
		} else {
			chain.doFilter(request, response);
		}
	}

}
