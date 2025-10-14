package com.hk.ansboard.daos;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.hk.ansboard.dtos.AnsDto;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor //생성자를 통해 주입하는 방법
public class AnsDaoImp implements IAnsDao{
	
	//생성자 주입
	private final SqlSessionTemplate sqlSession;
	
//	public AnsDaoImp() {
//		// TODO Auto-generated constructor stub
//	}
	
	//lombok을 이용하면 코드 생략 가능
	public AnsDaoImp(SqlSessionTemplate sqlSession) {
		this.sqlSession=sqlSession;
	}
	
//	@Autowired //타입으로 찾아서 주입하는 기능
//	@Qualifier("sqlSessionTemplate") //보조역할: Autowired와 같이 사용(이름으로 구별)
//	@Resource(name = "sqlSessionTemplate") //이름으로 매칭, 이름 없을 경우 타입으로 매칭(별도 라이브러리추가해야함)
//	private SqlSessionTemplate sqlSession;
	
	private String namespace="com.hk.ansboard.dao.";
	
	@Override
	public List<AnsDto> getAllList(String pnum) {
		Map<String, String>map=new HashMap<>();
		map.put("pnum", pnum);
		return sqlSession.selectList(namespace+"boardlist",map);
	}

	@Override
	public int getPCount() {
		return sqlSession.selectOne(namespace+"getpcount");
	}

	@Override
	public boolean insertBoard(AnsDto dto) {
		int count=sqlSession.insert(namespace+"insertboard", dto);
		return count>0?true:false;
	}

	@Override
	public AnsDto getBoard(int seq) {
		Map<String,Integer> map=new HashMap<>();
		map.put("seq", seq);
		//boardlist 쿼리를 동적쿼리로 작성해서 상세보기도 가능함
		return sqlSession.selectOne(namespace+"boardlist", map);
	}

	@Override
	public boolean readCount(int seq) {
		int count=sqlSession.update(namespace+"readcount",seq);
		return count>0?true:false;
	}

	@Override
	public boolean boardUpdate(String title, String content, int seq) {
		Map<String, Object>map=new HashMap<String, Object>();
		map.put("title", title);
		map.put("content", content);
		map.put("seq", seq);
		int count = sqlSession.update(namespace+"boardupdate", map);
		return count>0?true:false;
	}

	@Override
	public boolean deleteBoard(int seq) {
		int count=sqlSession.update(namespace+"boarddelete", seq);
		return count>0?true:false;
	}

	@Override
	public boolean mulDel(String[] seqs) {
		Map<String, String[]>map=new HashMap<>();
		map.put("seqs", seqs);
		int count=sqlSession.update(namespace+"muldel", map);
		return count>0?true:false;
	}
	
	@Override
	public int replyUpdate(AnsDto dto) {
		return sqlSession.update(namespace+"replyupdate", dto);
	}
	
	@Override
	public int replyInsert(AnsDto dto) {
		return sqlSession.update(namespace+"replyinsert", dto);
	}

}