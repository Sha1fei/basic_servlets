package Filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;

@WebFilter(value = "/",
        servletNames = { "LayoutServlet" },
        dispatcherTypes = { DispatcherType.REQUEST }
)
public class UserFilter implements Filter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain) throws IOException, ServletException {
        if(((HttpServletRequest) req).getCookies() != null){
            var absentUserIdCookie = Arrays.stream(((HttpServletRequest) req).getCookies())
                .filter(cookie -> cookie.getName().equals("userId"))
                .map(Cookie::getName)
                .toList()
                .isEmpty();
            if(absentUserIdCookie){
                ((HttpServletResponse) res).sendRedirect( "/login");
            } else {
                ((HttpServletRequest) req).getRequestDispatcher("/").forward(req, res);
            }
        } else {
            ((HttpServletResponse) res).sendRedirect( "/login");
        }
    }
}
