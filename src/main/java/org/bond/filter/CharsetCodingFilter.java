package org.bond.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CharsetCodingFilter implements Filter {
	private String charset;

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		if (this.charset == null || this.charset.length() == 0) {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
		} else {
			request.setCharacterEncoding(this.charset);
			response.setCharacterEncoding(this.charset);
		}
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		this.charset = fConfig.getInitParameter("charset");
	}

}
