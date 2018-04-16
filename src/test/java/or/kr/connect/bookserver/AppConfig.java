package or.kr.connect.bookserver;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 
 * 	@author minchoba
 * 	-----------------------------------------------
 * 	DataSource 생성 역할을 넘겨받음
 * 	-----------------------------------------------
 *	annotation - Configuration, Bean
 *	스프링의 Application context에서 관리할 객체임을 나타냄
 *	
 */

@Configuration
@ComponentScan
@PropertySource("application.properties")		// ApplicationContext가 properties 파일을 인지해 읽어올 수 있도록 위치를 지정한다.
public class AppConfig {
	@Value("${spring.datasource.url}")
	private String url;
	
	@Value("${spring.datasource.driver-class-name}")
	private String driverClassName;
	
	@Value("${spring.datasource.username}")
	private String username;
	
	@Value("${spring.datasource.password}")
	private String password;
	
	
	@Bean
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(driverClassName);										// h2 DB 사용
		dataSource.setUrl(url);	// h2 DB에 설정한 URL, name, password
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		return dataSource;
	}
}
