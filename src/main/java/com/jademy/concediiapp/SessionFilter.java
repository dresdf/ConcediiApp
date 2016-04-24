package com.jademy.concediiapp;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Dragos Secara
 */
public class SessionFilter implements Filter {

    private ArrayList<String> urlList;
    private static final boolean debug = true;

    private FilterConfig filterConfig = null;

    public SessionFilter() {
    }

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        String url = request.getServletPath();
        boolean allowedRequest = false;

        if (urlList.contains(url)) {
            allowedRequest = true;
        }

        if (!allowedRequest) {
            HttpSession session = request.getSession(true);
            if (session == null) {
                response.sendRedirect("/WEB-INF/jsp/index.jsp");
                return;
            }
        }
        chain.doFilter(req, res);

    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {
//        String urls = filterConfig.getInitParameter("avoid-urls");
//        StringTokenizer token = new StringTokenizer(urls, ",");
//
//        urlList = new ArrayList<String>();
//
//        while (token.hasMoreTokens()) {
//            urlList.add(token.nextToken());
//
//        }
    }
}
