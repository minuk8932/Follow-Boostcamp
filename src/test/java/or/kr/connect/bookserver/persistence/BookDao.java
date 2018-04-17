package or.kr.connect.bookserver.persistence;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import or.kr.connect.domain.Book;

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
	private static final String SELECT_BY_ID = "SELECT id, title, author, pages FROM book where id = :id";	// book 테이블을 id로 조회
	
	private NamedParameterJdbcTemplate jdbc;
	private SimpleJdbcInsert insertAction;
	
	public BookDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);	// NamedParameterJdbcTemplate에 파라미터로 받아온 dataSource를 넘겨줌
		
		this.insertAction = new SimpleJdbcInsert(dataSource)		// jdbcInsert 객체 생성 후 메서드를 통해 dataSource를 가져옴
				.withTableName("book")							// table 이름이 book
				.usingGeneratedKeyColumns("id");					// 프라이머리키가 id
	}
	
	public int countBooks() {								// 책 갯수를 세는 메소드
		Map<String, Object> params = Collections.emptyMap();	// BookLauncher 클래스의 코드를 그대로 가져옴, SQL 구문은 상수 처리
		return jdbc.queryForObject(COUNT_BOOK, params, Integer.class);	// 메소드 실행을 통해, 받아온 행 데이터를 객체로 받아와 그 수를 정수로 반환
	}
	
	/**
	 * 
	 * 		RowMapper<Book> rowMapper = new RowMapper<Book>() {
	 *			@Override
	 *			public Book mapRow(ResultSet rs, int i) throws SQLException {
	 *				Book book = new Book();
	 *				book.setId(rs.getInt("id"));
	 *				book.setTitle(rs.getString("title"));
	 *				book.setAuthor(rs.getString("author"));
	 *				book.setPages((Integer) rs.getObject("pages"));
	 *				return book;
	 *			}
	 *		};
	 * 				->  아래의 람다 표현식을 익명클래스로 표현
	 */
	
	RowMapper<Book> rowMapper = BeanPropertyRowMapper.newInstance(Book.class);	// 람다 표현식을 간단히해주는 BeanPropertyRowMapper로 선언
																// newInstance로 생성된 객체는 멀티스레드에서 접근해도 안전해, 멤버변수로 선언 가능
	public Book selectById(Integer id) {		
		Map<String, Object> params = new HashMap<>();
		params.put("id", id);
		
		return jdbc.queryForObject(SELECT_BY_ID, params, rowMapper);	// id값으로 조회하는 쿼리, rowMapper를 통해 객체 형변환 
	}
	
	public Integer insert(Book book) {
		// BeanPropertySqlParameterSource을 이용해서 Book 클래스에서 SqlParameterSource로 변환
		SqlParameterSource params = new BeanPropertySqlParameterSource(book);	
		return insertAction.executeAndReturnKey(params).intValue();			// 생성된 key 값을 정수형으로 반환
	}
}
