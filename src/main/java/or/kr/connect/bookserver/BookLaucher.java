package or.kr.connect.bookserver;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import or.kr.connect.bookserver.persistence.BookDao;
import or.kr.connect.domain.Book;

/**
 * 
 * 	@author minchoba
 * 	-----------------------------------------------------------------
 *	h2 DB에서 SQL문을 통해 넣었던 데이터를
 *	현 파일에서 가져와 저장된 데이터 행의 수를 출력
 *	-----------------------------------------------------------------
 *	BasicDataSource: Connection pool을 제공하는 CommonDBCP 라이브러리를 사용
 *	Connection pool은 JPA의 Persistence Context와 같은 기능을 하는것으로 보임
 *	의의: DI(Dependency Injection) 기능 없이도 해당 기법을 사용
 *	-> 위의 내용을 DI 기능을 가져와 AppConfig 클래스에서 정의함
 *	-----------------------------------------------------------------
 */

public class BookLaucher {
	public static void main(String[] args) {
		// AppConfig 객체를 Application Context를 통해 BookLauncer class에 참조함
		ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		
		/**
		 *  Application context에서 직접 Book Dao를 받아옴 ->	
		 *  BookDao의 생성자에 DataSource 객체를 넘겨서 주입하는 과정 : DataSource dataSource = context.getBean(DataSource.class);
		 *  	BookDao를 @Bean 애너테이션을 이용해서 등록하는 과정 : BookDao dao = new BookDao(dataSource);
		 * 	
		 * 	두 과정이 자동으로 이루어짐
		 */
		BookDao dao = context.getBean(BookDao.class);										// BookDao 클래스를 통해
		int count = dao.countBooks();														// 행의 갯수를 count 변수에 받아옴					
		System.out.println(count);															// 저장된 갯수 출력
		
		Book book = dao.selectById(1);							// Book에서 생성한 쿼리 메소드를 받아와서
		System.out.println(book);								// 반환값 출력
		
		Book book2 = new Book("naver Java", "김강산", 512);		// book2에 2번째 테이블 저장
		Integer newId = dao.insert(book2);						// book2의 아이디를 newId에 저장
		System.out.println(newId);								// id 출력
		System.out.println(dao.selectById(newId));				// id를 통한 테이블 출력
		
		context.close();
	}
}
