package or.kr.connect.bookserver;

import javax.sql.DataSource;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import or.kr.connect.bookserver.persistence.BookDao;

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
		DataSource dataSource = context.getBean(DataSource.class);							// 해당 객체에서 빈을 통해 사용할 클래스를 받아옴
		
		BookDao dao = new BookDao(dataSource);												// BookDao 클래스를 통해 행의 갯수를
		int count = dao.countBooks();														// count 변수에 받아옴
		System.out.println(count);														// 저장된 갯수 출력
		
		context.close();
	}
}
