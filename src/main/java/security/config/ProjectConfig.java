package security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import security.service.InMemoryUserDetailsService;
import security.model.User;

import javax.sql.DataSource;
import java.util.List;

@Configuration
public class ProjectConfig {
	@Bean
	public UserDetailsService userDetailsService(DataSource datasource) {
		String userByUsernameQuery = "SELECT USERNAME, PASSWORD, ENABLED FROM BPLTE.USERS WHERE USERNAME = ?";
		String authsByUserQuery = "SELECT USERNAME, AUTHORITY FROM BPLTE.AUTHORITIES WHERE USERNAME = ?";
		
		var userDetailsManager = new JdbcUserDetailsManager(datasource);
		userDetailsManager.setUsersByUsernameQuery(userByUsernameQuery);
		userDetailsManager.setAuthoritiesByUsernameQuery(authsByUserQuery);
		return userDetailsManager;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
}
