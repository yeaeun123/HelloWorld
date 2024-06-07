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

public class EncodingFilter implements Filter {
	// 로거 선언
	private static Logger logger = Logger.getLogger(EncodingFilter.class.getSimpleName());
	private static String encoding = "UTF-8";


	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
		logger.info("[EncodingFilter] init");
		Filter.super.init(filterConfig);
		// init-param으로부터 encoding 정보를 받아와서 encoding 속성을 변경
		String encodingParam = filterConfig.getInitParameter("encoding");
		if (encodingParam != null) {
			encoding = encodingParam;
			logger.info("\tinit-param으로부터 받은 인코딩:" + encodingParam);
		}
		logger.info("\t인코딩이 " + encoding + "으로 설정되었습니다.");
	}
	
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
							FilterChain chain)
			throws IOException, ServletException {
		
		// 출력을 위한 객체 얻어오기
		PrintWriter out = resp.getWriter();
		out.println("<h6>Encoding Filter 작동 전 </h6>");
		
		// 실제 필터가 처리하는 내용
		logger.info("[EncodingFilter] doFilter");
		req.setCharacterEncoding(encoding);	// 요청정보의 인코딩 설정
		resp.setContentType("text/html");	// 응답정보의 타입
		resp.setCharacterEncoding(encoding); // 응답 정보의 인코딩

		chain.doFilter(req, resp);	// 요청처리 종료 후에 다음 필터로 전달
		
		out.println("<h6>Encoding Filter 작동 후</h6>");
		
	}
	
	@Override
	public void destroy() {
		//		Filter.super.destroy();
		logger.info("[EncodingFilter] destroy");
	}

}
