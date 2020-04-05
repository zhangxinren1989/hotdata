
package disappear.programmer.service.hotdata.configuration.crossorigin;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.Order;


@WebFilter(urlPatterns = "/*", filterName = "corsFilter")
@Order(1)//指定过滤器的执行顺序,值越大越靠后执行
public class CorsFilter implements Filter{
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException 
	{
		HttpServletResponse response = (HttpServletResponse) res;  
        response.setHeader("Access-Control-Allow-Origin", "*");  
        response.setHeader("Access-Control-Allow-Methods", "PUT, POST, GET, OPTIONS, DELETE");  
        response.setHeader("Access-Control-Max-Age", "3600");  
        response.setHeader("Access-Control-Allow-Headers", "*");  
        response.setHeader("Access-Control-Allow-Credentials", "true");
        chain.doFilter(req, response);   
	}
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException
	{
	}
	
	@Override
	public void destroy()
	{
	}
}

