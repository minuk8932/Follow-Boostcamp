package or.kr.connect.bookserver;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
public class AppConfig {
	@Bean
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("org.h2.Driver");										// h2 DB 사용
		dataSource.setUrl("jdbc:h2:~/javaweb/db;AUTO_SERVER=TRUE;DB_CLOSE_ON_EXIT=FALSE;");	// h2 DB에 설정한 URL, name, password
		dataSource.setUsername("sa");
		dataSource.setPassword("sa");
		return dataSource;
	}
}
