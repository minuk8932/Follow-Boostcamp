package or.kr.connect;

import org.junit.Before;
import org.junit.Test;

public class CountTest {
	private int count = 0;
	@Before
	public void setUp() {
		System.out.println(count++);
	}
	
	@Test
	public void testPlus() {
		System.out.println(count++);
	}
	
	@Test
	public void increase() {
		System.out.println(count++);
	}
}
