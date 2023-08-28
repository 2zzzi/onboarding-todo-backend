package onboarding.todo.config;


import lombok.RequiredArgsConstructor;
import onboarding.todo.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

//Security같은 경우에는 PermitAll함.
//SecruityFilterChain을 사용하지 않음.

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService userService;
    @Value("${jwt.token.secret}")
    private String secretKey;


    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.httpBasic().disable()
                .csrf().disable()
                .cors().and()
                .formLogin().disable()
                .authorizeRequests()
                .antMatchers("/auth/**", "/v3/api-docs/**", "/swagger*/**").permitAll()
                .antMatchers(HttpMethod.POST, "/todos").authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(new JwtFilter(userService, secretKey), UsernamePasswordAuthenticationFilter.class)
//                .build()
                ;
    }

}
