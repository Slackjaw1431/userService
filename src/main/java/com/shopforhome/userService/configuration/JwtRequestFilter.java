package com.shopforhome.userService.configuration;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.shopforhome.service.JwtService;
import com.shopforhome.userService.util.JwtUtil;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private JwtService jwtService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		final String requestTokenHeader = request.getHeader("Authorization");

		String username = null;
		String jwtToken = null;

		if (requestTokenHeader != null) {
			jwtToken = requestTokenHeader.substring(7);
//			System.out.println("-------------");
//			System.out.println(jwtToken);

			try {
				username = jwtUtil.getUsernameFromToken(jwtToken);
			} catch (Exception e) {
//				System.out.println("No specific User");
				username = null;
				jwtToken = null;
			}

		}

		if (username != null) {

			UserDetails userDetails = jwtService.loadUserByUsername(username);

			if (jwtUtil.validateToken(jwtToken, userDetails)) {

				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		}
		filterChain.doFilter(request, response);

	}

}
