package com.haley.board;

import java.sql.Connection;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/*.xml" })
public class BoardTest {
	@Inject // = @Autowired와 같은 설정
	private DataSource ds;

	@Test // 테스트 코드임을 선언 
	public void testConection() throws Exception {
		Connection con = null;
		try {
			con = ds.getConnection();
			System.out.println(con);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}
}