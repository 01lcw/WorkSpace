package com.hk.board.config;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

//SqlSessionFactory객체를 구현
public class SqlMapConfig {
	
	//작업을 진행할 객체(SqlSession) 만들어줄 객체
	private SqlSessionFactory sqlSessionFactory;
	
	//SqlSessionFactory를 생성해줄 메서드
	public SqlSessionFactory getSessionFactory() {
		String resource="sql/Configuration.xml";
		
		try {
			Reader reader=Resources.getResourceAsReader(resource);
			sqlSessionFactory=new SqlSessionFactoryBuilder().build(reader);
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return sqlSessionFactory;
	}
}