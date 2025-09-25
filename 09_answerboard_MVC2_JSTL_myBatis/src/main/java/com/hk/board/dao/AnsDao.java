package com.hk.board.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.hk.board.config.SqlMapConfig;
import com.hk.board.dto.AnsDto;

public class AnsDao extends SqlMapConfig {
	
	private String namespace="com.hk.board.dao.";
	
	//1.글목록 조회
	public List<AnsDto> getAlList(){
		List<AnsDto> list = new ArrayList<AnsDto>();
		SqlSession sqlSession=null;
		
		try {
			//sqlSession객체를 구하려면 openSession()를 통해 얻어온다
			//openSession(true/false): t(autocommit)
			sqlSession=getSessionFactory().openSession(true);
			//selectList(쿼리ID) 해당 쿼리를 실행시킨다.
			list=sqlSession.selectList(namespace+"boardlist");
		} catch (Exception e) {
			e.printStackTrace();//오류 캐치
		}finally {
			sqlSession.close();
		}
		
		return list;
	}
	//2. 글 추가하기
	public boolean insertBoard(AnsDto dto){
		int count=0;
		SqlSession sqlSession=null;
		
		try {
			sqlSession=getSessionFactory().openSession(true);
			//파라미터 타입: dto, Array, Map, String, integer
			count=sqlSession.insert(namespace+"insertboard",dto);
			
		} catch (Exception e) {
			e.printStackTrace();//오류 캐치
		}finally {
			sqlSession.close();
		}
		
		return count>0?true:false;
	}
}
