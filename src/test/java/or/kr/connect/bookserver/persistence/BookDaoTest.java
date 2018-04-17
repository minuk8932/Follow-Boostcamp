package or.kr.connect.bookserver.persistence;

import static org.junit.Assert.assertThat;

import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import or.kr.connect.bookserver.AppConfig;
import or.kr.connect.domain.Book;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = AppConfig.class)
@Transactional
public class BookDaoTest {
	@Autowired
	private BookDao dao;
	
	@Test
	public void shouldCount() {
		int count = dao.countBooks();
		System.out.println(count);
	}
	
	@Test
	public void shouldInsertAndSelect() {
		// given
		Book book = new Book("Java 웹개발", "네이버", 342);

		// when
		Integer id = dao.insert(book);

		// then
		Book selected = dao.selectById(id);
		System.out.println(selected);
		assertThat(selected.getTitle(), CoreMatchers.is("Java 웹개발"));
	}
	
	@Test
	public void shouldDelete() {
		// given
		Book book = new Book("네이버 자바", "네이버", 142);
		Integer id = dao.insert(book);

		// when
		int affected = dao.deleteById(id);

		// Then
		assertThat(affected, CoreMatchers.is(1));
	}
	
	@Test
	public void shouldUpdate() {
		// Given
		Book book = new Book("네이버 자바", "네이버", 142);
		Integer id = dao.insert(book);

		// When
		book.setId(id);
		book.setTitle("네이버 자바2");
		int affected = dao.update(book);

		// Then
		assertThat(affected, CoreMatchers.is(1));
		Book updated = dao.selectById(id);
		assertThat(updated.getTitle(), CoreMatchers.is("네이버 자바2"));
	}
}
