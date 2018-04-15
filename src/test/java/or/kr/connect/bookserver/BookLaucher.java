package or.kr.connect.bookserver;

import java.util.Collections;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * 
 * 	@author minchoba
 *	h2 DB에서 SQL문을 통해 넣었던 데이터를
 *	현 파일에서 가져와 저장된 데이터 행의 수를 출력
 */
public class BookLaucher {
	public static void main(String[] args) {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.h2.Driver");											// h2 DB 사용
		dataSource.setUrl("jdbc:h2:~/javaweb/db;AUTO_SERVER=TRUE;DB_CLOSE_ON_EXIT=FALSE;");		// h2 DB에 설정한 URL, name, password
		dataSource.setUsername("sa");
		dataSource.setPassword("sa");

		NamedParameterJdbcTemplate jdbc = new NamedParameterJdbcTemplate(dataSource);				// h2 DB의 값을 jdbc 변수에 받아옴
		String sql = "SELECT COUNT(*) FROM book";												// sql문 실행
		Map<String, Object> params = Collections.emptyMap();										// 테이블에 저장된 데이터를 해쉬맵을 통해 가져옴
		Integer count = jdbc.queryForObject(sql, params, Integer.class);							// 저장된 객체의 갯수를 셀 count 변수 선언
		System.out.println(count);																// 저장된 갯수 출력
	}
}
