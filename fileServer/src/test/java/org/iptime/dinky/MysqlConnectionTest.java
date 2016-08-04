package org.iptime.dinky;

import java.sql.Connection;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class MysqlConnectionTest {

	private Logger logger = LoggerFactory.getLogger(MysqlConnectionTest.class);
	
	@Inject
	private DataSource ds;
	
	@Test
	public void getConnection() throws Exception{
		try (Connection c = ds.getConnection()){
			logger.info("connection : "+c);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Inject
	private SqlSessionFactory sqlSessionFactory;
	
	@Test
	public void getSqlSessionFactory(){
		try (SqlSession sqlSession = sqlSessionFactory.openSession()){
			logger.info("sqlSession : "+sqlSession);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
