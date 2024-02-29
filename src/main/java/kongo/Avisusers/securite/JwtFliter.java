package kongo.Avisusers.securite;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kongo.Avisusers.entites.Utilisateur;
import kongo.Avisusers.services.UtilisateurService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Enumeration;


@Service
public class JwtFliter extends OncePerRequestFilter {

  private UtilisateurService utilisateurService;
  private JwtService jwtService;


    public JwtFliter(UtilisateurService utilisateurService, JwtService jwtService) {
        this.utilisateurService = utilisateurService;
        this.jwtService = jwtService;

    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = null;
        String username = null;
        boolean isTokenExpired = true;

       final String authorization = String.valueOf(request.getHeaders("Authorization"));
       if (authorization != null && authorization.startsWith("Bearer")){
           token = authorization.substring(7);
          isTokenExpired =  jwtService.isTokenExpired(token);
           jwtService.extractUsername(token);


       }
       if (!isTokenExpired && username != null && SecurityContextHolder.getContext().getAuthentication() == null){
       UserDetails userDetailsService = utilisateurService.loadUserByUsername(username);
       UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetailsService, null, userDetailsService.getAuthorities());
       SecurityContextHolder.getContext().getAuthentication();

       }
       filterChain.doFilter(request, response);


    }
}
