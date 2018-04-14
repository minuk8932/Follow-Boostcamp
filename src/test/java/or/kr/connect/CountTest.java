package or.kr.connect;

import org.junit.Before;
import org.junit.Test;

public class CountTest {
	private int count = 0;
	@Before								// @BeforeClass, @AfterClass : 테스트 클래스마다 한 번 씩. static 메소드 앞에만 가능
	public void setUp() {				// @Before 매번 테스트 전, @After 매번 테스트 후
		System.out.println(count++);
	}
	
	@Test								// 실행 할 테스트 메소드
	public void testPlus() {				// count 멤버 변수는 매 테스트 마다 새로 생성된다. (출력결과 참고)
		System.out.println(count++);
	}
	
	@Test
	public void increase() {
		System.out.println(count++);
	}
}
