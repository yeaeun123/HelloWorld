package himedia.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

// 요청과 응답의 기본적 내용을 기록으로 남기는 필터
public class LoggingFilter implements Filter {
	private static Logger logger = Logger.getLogger(LoggingFilter.class.getSimpleName());

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		logger.info("[LoggingFilter] init");
		Filter.super.init(filterConfig);
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain)
			throws IOException, ServletException {
		PrintWriter out = resp.getWriter();
		out.println("<h6>LoggingFilter 작동 전</h6>");
		logger.info("[LoggingFilter] doFilter");
		
		// 요청 정보를 로그로 출력
		// ServletRequest -> HttpServletRequest 캐스팅 URI 정보
		HttpServletRequest httpRequest = (HttpServletRequest) req;
		logger.info("들어오는 요청의 URI: " + httpRequest.getRequestURI());
		
		// 필터 로직을 수행한 후 연결된 필터(혹은 서블릿)으로 처리중인 요청과 응답 객체를 전달
		chain.doFilter(req, resp);
		
		// 응답이 돌아온 이후 응답 상태를 출력하고 로깅
		out.println("<h6>LoggingFilter 동작 후</h6>");
		logger.info("출력되는 응답의 컨텐츠 타입 : " + resp.getContentType());

	}

	@Override
	public void destroy() {
		logger.info("[LoggingFilter] destroy");
		Filter.super.destroy();
	}


}
