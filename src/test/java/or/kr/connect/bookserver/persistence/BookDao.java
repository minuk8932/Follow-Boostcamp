package or.kr.connect.bookserver.persistence;

import java.util.Collections;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * 
 * 	@author minchoba
 *	DataSource에 의존하는 DAO(Data Access Object) 추출
 *	DB에 접속해 쿼리를 호출하는 역할을 해당 클래스에 선언해줌 
 *	즉, 행의 수를 세는 기능을 본 클래스로 옮기고 메인 클래스에선 결과값만 받아 볼 수 있게 설정
 */

@Repository
public class BookDao {
	private static final String COUNT_BOOK = "SELECT COUNT(*) FROM book";
	
	private NamedParameterJdbcTemplate jdbc;
	
	public BookDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);	// NamedParameterJdbcTemplate에 파라미터로 받아온 dataSource를 넘겨줌
	}
	
	public int countBooks() {								// 책 갯수를 세는 메소드
		Map<String, Object> params = Collections.emptyMap();	// BookLauncher 클래스의 코드를 그대로 가져옴, SQL 구문은 상수 처리
		return jdbc.queryForObject(COUNT_BOOK, params, Integer.class);	// 메소드 실행을 통해, 받아온 행 데이터를 객체로 받아와 그 수를 정수로 반환
	}
}
