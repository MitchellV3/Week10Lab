/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filters;

import dataaccess.UserDB;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 *
 * @author Mitchell
 */
public class AdminFilter implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
            // code that is exectuted before the servlet
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            HttpSession session = httpRequest.getSession();
            String email = (String) session.getAttribute("email");

            UserDB userDB = new UserDB();
            String role = userDB.get(email).getRole().getRoleName();

            if (role.equals("admin")) {
                // user is not logged in, so redirect to login page
                String url = "/User";
                request.getRequestDispatcher(url).forward(request, response);
            }

                // user is logged in, so just continue on to the servlet
                // (the request will be passed to the servlet

            if (email == null) {
                // user is not logged in, so redirect to login page
                String url = "/User";
                request.getRequestDispatcher(url).forward(request, response);
            }

                // user is logged in, so just continue on to the servlet
                // (the request will be passed to the servlet

            chain.doFilter(request, response); // execute the servlet
            // code that is exectuted after the servlet
    
    }

    public void destroy() {        
    }
    
    public void init(FilterConfig filterConfig) {        
    }

    
}
