package com.hk.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hk.datasource.Database;
import com.hk.dtos.UserDto;

public class AdminDao extends Database{

	private static AdminDao adminDao;
	
	private AdminDao() {}
	
	public static AdminDao getAdminDao() {
		if(adminDao==null) {
			adminDao=new AdminDao();
		}
		return adminDao;
	}
	
	//1.회원목록 전체 조회[사용회원,탈퇴회원 모두]
	// getAllUserList: select문
	public List<UserDto> getAllUserList() {
		List<UserDto> list= new ArrayList<UserDto>();
		
		Connection conn=null;
		PreparedStatement psmt=null;
		ResultSet rs=null;
		
		String sql="SELECT seq,id,name,address,email,role,enabled,regdate"
				 + " FROM userinfo ";
		
		try {
			conn=getConnection();
			psmt=conn.prepareStatement(sql);
	
			rs=psmt.executeQuery();
			while(rs.next()) {
				UserDto dto=new UserDto();
				dto.setSeq(rs.getInt(1));
				dto.setId(rs.getString(2));
				dto.setName(rs.getString(3));
				dto.setAddress(rs.getString(4));
				dto.setEmail(rs.getString(5));
				dto.setRole(rs.getString(6));
				dto.setEnabled(rs.getString(7));
				dto.setRegDate(rs.getDate(8));
				list.add(dto);
			}
			System.out.println();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rs, psmt, conn);
		}

		return list;
	}

	//2.회원목록 전체 조회[사용중]
	// getUserList: select문
	
	//3.회원상세조회(1명에 대한)
	// getUserRole: select문
	
	//4.회원등급수정
	// getUpdateRole: update문
}
