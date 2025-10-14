package com.hk.ansboard.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import com.hk.ansboard.daos.IAnsDao;

//@RunWith(SpringJUnit4ClassRunner.class)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(
	locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"}
		     )
@WebAppConfiguration
class AnsServiceTest {

	@Autowired
	private IAnsDao ansDao;
	
	@Test
	void testGetAllList() {
//		fail("Not yet implemented");
	}

	@Test
	void testGetPCount() {
		fail("Not yet implemented");
	}

	@Test
	void testInsertBoard() {
		fail("Not yet implemented");
	}

	@Test
	void testGetBoard() {
		fail("Not yet implemented");
	}

	@Test
	void testReadCount() {
		fail("Not yet implemented");
	}

	@Test
	void testBoardUpdate() {
		fail("Not yet implemented");
	}

	@Test
	void testDeleteBoard() {
		fail("Not yet implemented");
	}

	@Test
	void testMulDel() {
		fail("Not yet implemented");
	}

	@Test
	void testReplyBoard() {
		fail("Not yet implemented");
	}

}