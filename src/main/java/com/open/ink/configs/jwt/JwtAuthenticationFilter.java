package com.open.ink.configs.jwt;

import java.io.IOException;

import com.open.ink.configs.UserDetailServiceImple;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private UserDetailServiceImple userDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String reqToken = request.getHeader("Authorization");

		String userName = null;
		String token = null;

		if (reqToken != null && reqToken.startsWith("Bearer ")) {
			token = reqToken.substring(7);

			try {
				userName = jwtUtil.extractUsername(token);
			} catch (IllegalArgumentException e) {
				log.error("Unable to get JWT token");
			} catch (ExpiredJwtException e) {


				response.setStatus(HttpStatus.FORBIDDEN.value());
				response.setContentType("application/json");
				String errorResponse = "{\"message\":\"Token has expired\"}";
				response.getWriter().write(errorResponse);
				response.getWriter().flush();
				return;

			} catch (MalformedJwtException e) {
				log.error("Invalid JWT (from Filter Class)");
			} catch (Exception e) {

			}

		}


		// VALIDATING THE TOKEN
		if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {

			UserDetails userDetails = userDetailsService.loadUserByUsername(userName);

			if (jwtUtil.validateToken(token, userDetails)) {

				UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			}
		}

		// ***
		filterChain.doFilter(request, response);
	}


}
