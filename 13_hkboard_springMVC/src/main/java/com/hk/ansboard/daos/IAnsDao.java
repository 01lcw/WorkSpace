package com.hk.ansboard.daos;

import java.util.List;
import com.hk.ansboard.dtos.AnsDto;

public interface IAnsDao {
	//1.글목록 조회
		public List<AnsDto> getAllList(String pnum);
		
		//1-2.페이지수 구하기
		public int getPCount();
		
		//2. 글추가하기
		public boolean insertBoard(AnsDto dto);
		
		//3.글상세 조회
		public AnsDto getBoard(int seq);
		
		//4. 조회수 올리기
		public boolean readCount(int seq);
		
		//5.수정하기
		public boolean boardUpdate(String title, String content, int seq);
		//6.삭제하기
		public boolean deleteBoard(int seq);
		//7.여러글 삭제하기
		public boolean mulDel(String[] seqs);
		
		//8. 답글추가하기: update, insert
		public int replyUpdate(AnsDto dto);
		public int replyInsert(AnsDto dto);
}
