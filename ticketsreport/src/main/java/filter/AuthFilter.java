/**
 * 
 */
package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.annotation.WebFilter;

import org.apache.log4j.Logger;

/**
 * @author Damir Tuktamyshev
 */
@WebFilter(filterName = "AuthFilter", urlPatterns = {"*.xhtml"})
public class AuthFilter implements Filter {

	
	/**
	 * Logger.
	 */
	private static final Logger LOG = Logger.getLogger(AuthFilter.class);
	/**
	 * 
	 */
	public AuthFilter() {
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#destroy()
	 */
	@Override
	public void destroy() {

	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		   try {
			   
	            HttpServletRequest req = (HttpServletRequest) request;
	            HttpServletResponse res = (HttpServletResponse) response;
	            HttpSession ses = req.getSession(false);
	            String reqURI = req.getRequestURI();
	            if ( reqURI.indexOf("/login.xhtml") >= 0 || (ses != null && ses.getAttribute("token") != null)
	                                        || reqURI.contains("javax.faces.resource") )
	                   chain.doFilter(request, response);
	            else   
	                   res.sendRedirect(req.getContextPath() + "/login.xhtml");  
	      }
	     catch(Exception e) {
	         LOG.warn("Exception " + e);
	     }

	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
