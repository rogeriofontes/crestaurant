package br.com.ft.crestaurant.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@EnableJpaAuditing
@Configuration
public class AuditingConfig {
	
	@Bean
	public AuditorAware<String> createAuditorProvider() {
		return new SecurityAuditor();
	}

	@Bean
	public AuditingEntityListener createAuditingListener() {
		return new AuditingEntityListener();
	}

	public static class SecurityAuditor implements AuditorAware<String> {
		@Override
		public String getCurrentAuditor() {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			String username;
			if (auth.getName() != null){
				username = auth.getName();
			} else {
				username = "sistema@crestaurant.com.br";
			}
			return username;
		}
	}
}
