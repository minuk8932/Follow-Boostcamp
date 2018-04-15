package or.kr.connect.bookserver;

import org.apache.commons.dbcp2.BasicDataSource;

import or.kr.connect.bookserver.persistence.BookDao;

/**
 * 
 * 	@author minchoba
 *	h2 DB에서 SQL문을 통해 넣었던 데이터를
 *	현 파일에서 가져와 저장된 데이터 행의 수를 출력
 *	
 *	BasicDataSource: Connection pool을 제공하는 CommonDBCP 라이브러리를 사용
 *	Connection pool은 JPA의 Persistence Context와 같은 기능을 하는것으로 보임
 *	의의: DI(Dependency Injection) 기능 없이도 해당 기법을 사용
 */
public class BookLaucher {
	public static void main(String[] args) {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("org.h2.Driver");										// h2 DB 사용
		dataSource.setUrl("jdbc:h2:~/javaweb/db;AUTO_SERVER=TRUE;DB_CLOSE_ON_EXIT=FALSE;");	// h2 DB에 설정한 URL, name, password
		dataSource.setUsername("sa");
		dataSource.setPassword("sa");

		BookDao dao = new BookDao(dataSource);												// BookDao 클래스를 통해 행의 갯수를
		int count = dao.countBooks();														// count 변수에 받아옴
		System.out.println(count);															// 저장된 갯수 출력
	}
}
