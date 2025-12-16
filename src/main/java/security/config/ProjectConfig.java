package security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter {
	@Bean
	public UserDetailsService userDetailsService() {
		var userDetailService = new InMemoryUserDetailsManager();

		var user = User.withUsername("john").password("12345").authorities("read").build();
		userDetailService.createUser(user);

		return userDetailService;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		// NoOpPasswordEncoder 인스턴스는 암호에 암호화나 해시를 적용하지 않고 일반 텍스트처럼 처리
		return NoOpPasswordEncoder.getInstance();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic();
		// 인증 없이 모든 요청 가능
		http.authorizeRequests().anyRequest().permitAll();
	}
}
