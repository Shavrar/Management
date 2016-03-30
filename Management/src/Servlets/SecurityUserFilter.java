package Servlets;

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

import Entities.User;

public class SecurityUserFilter implements Filter {
    @Override
    public void init(FilterConfig config) throws ServletException {}

    @Override
    public void doFilter(ServletRequest req,
                         ServletResponse resp,
                         FilterChain chain)
                               throws IOException, ServletException {
        HttpSession session = ((HttpServletRequest)req)
                                                  .getSession(false);
        if(session != null && session.getAttribute("user") != null ) {
        	User user=(User)session.getAttribute("user");
        	if(user.getRole().equalsIgnoreCase("admin")){
            chain.doFilter(req, resp);
        	}
        	else {
                ((HttpServletResponse)resp).sendRedirect(
                    ((HttpServletRequest)req).getContextPath()
                    + "/login-form.jsp"
                );
            }
        } else {
            ((HttpServletResponse)resp).sendRedirect(
                ((HttpServletRequest)req).getContextPath()
                + "/login-form.jsp"
            );
        }
    }

    @Override
    public void destroy() {}
}
