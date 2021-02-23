/*
 * MIT License
 *
 * Copyright (c) 2021 TEZEA-Chantiers
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package fr.tezea.chantiers.security.configurer;

import fr.tezea.chantiers.security.filters.JwtRequestFilter;
import fr.tezea.chantiers.security.services.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfigurer extends WebSecurityConfigurerAdapter
{
    @Autowired
    private MyUserDetailsService userDetailsService;
    @Autowired
    private JwtRequestFilter filter;
    private static final String API_VERSION = "v1";

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.userDetailsService(userDetailsService);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http.csrf().disable().authorizeRequests()
        		
        		//https://stackoverflow.com/questions/30819337/multiple-antmatchers-in-spring-security
        		//Pour plus d'informations sur la formation des authorisations sur les differentes API
        
        		.antMatchers("/api/" + API_VERSION + "/authentification/authentifier")
                .permitAll().antMatchers("/api/" + API_VERSION + "/chantier/*")
                .hasAnyRole("Administration", "Telephone", "Direction", "Chef de site")
                
                .antMatchers("/api/" + API_VERSION + "/demandedechantier/**")
                .hasAnyRole("Administration", "Telephone", "Direction", "Chef de site")
                
                .antMatchers("/api/" + API_VERSION + "/media/**").hasAnyRole("Administration", "Telephone", "Direction")
                
                .antMatchers("/api/" + API_VERSION + "/probleme/**")
                .hasAnyRole("Administration", "Telephone", "Direction")
                
                .antMatchers("/api/" + API_VERSION + "/client/**")
                .hasAnyRole("Administration", "Direction", "Chef de site")
                
                .antMatchers("/api/" + API_VERSION + "/media/**")
                .hasAnyRole("Administration", "Telephone", "Direction", "Chef de site")
                
                .antMatchers("/api/" + API_VERSION + "/utilisateur/get/myself").hasAnyRole("Administration", "Direction", "Chef de site", "Telephone")
                
                .antMatchers("/api/" + API_VERSION + "/utilisateur/**").hasAnyRole("Administration", "Direction")
                
                .antMatchers("/api/" + API_VERSION + "/rapportchantierregulier/**")
                .hasAnyRole("Administration", "Direction")
                .anyRequest().authenticated().and().sessionManagement()
                
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception
    {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return NoOpPasswordEncoder.getInstance();
    }
}
