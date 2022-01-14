package com.Blog.nambhamBlogs.filter;

import com.Blog.nambhamBlogs.model.User;
import com.Blog.nambhamBlogs.service.UserService;
import com.Blog.nambhamBlogs.util.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    UserService userService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        final String requestTokenHeader = request.getHeader("Authorization");
        String userEmail = null;
        String jwtToken = null;
        logger.warn(request.getRequestURI());
        logger.warn(request.getRemoteHost());
        logger.warn(request.getRemoteAddr());

        // JWT Token is in the form "Bearer token". Remove Bearer word and get
        // only the Token
        if(request.getRequestURI().contains("swagger") || request.getRequestURI().contains("api") || request.getRequestURI().contains("login") ){
            chain.doFilter(request, response);
            return;
        }
        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            jwtToken = requestTokenHeader.substring(7);
            try {
                userEmail = jwtUtil.getUserEmailFromToken(jwtToken);
            } catch (IllegalArgumentException e) {
                response.sendError(HttpStatus.FORBIDDEN.value(),"no token provided");
                logger.warn("Unable to get JWT Token");
                return ;

            } catch (ExpiredJwtException e) {
                response.sendError(HttpStatus.FORBIDDEN.value()," token expired");
                logger.warn("Unable to get JWT Token");
                return ;
            }
            catch (Exception e){
                response.sendError(HttpStatus.FORBIDDEN.value()," not valid token");
                logger.warn("Unable to get JWT Token");
                return ;
            }
        } else {
            logger.warn("JWT Token does not begin with Bearer String");
            response.sendError(HttpStatus.FORBIDDEN.value(),"provide token");
            return ;
        }		// Once we get the token validate it.
        if (userEmail != null ) {

            User userDetails = (User) (userService.getUserByEmailId(userEmail).getResponseCodeJson().getCode()==200?userService.getUserByEmailId(userEmail).getObject():null);			// if token is valid configure Spring Security to manually set
            logger.warn(userDetails.toString());
            // authentication
            if (userEmail!=null && jwtUtil.validateToken(jwtToken, userDetails)) {
                logger.warn("Valid user");
                request.setAttribute("userDetails",userDetails);
            }else{
                response.sendError(HttpStatus.FORBIDDEN.value()," not valid user");
                logger.warn("not Valid user");
                return ;

            }
        }
        chain.doFilter(request, response);
    }
}
