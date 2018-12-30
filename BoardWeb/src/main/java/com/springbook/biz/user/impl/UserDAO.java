package com.springbook.biz.user.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.stereotype.Repository;

import com.springbook.biz.common.JDBCUtil;
import com.springbook.biz.user.UserVO;

//DAO(Data Access Object)
@Repository("userDAO")
public class UserDAO   {
	//JDBC���� ����
	private Connection c = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	//SQL��ɾ�
	private final String USER_GET = " select * from users where id = ? and password = ? ";
	
	//CRUD����� �޼ҵ� ����
	//ȸ�� ���
	public UserVO getUser(UserVO vo) {
		UserVO user = null;
		try {
			System.out.println("===> JDBC�� getUser()��� ó��");
			c = JDBCUtil.getConnection();
			ps = c.prepareStatement(USER_GET);
			ps.setString(1, vo.getId());
			ps.setString(2, vo.getPassword());
			rs = ps.executeQuery();
			while(rs.next()) {
				user = new UserVO();
				user.setId(rs.getString("id"));
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
				user.setRole(rs.getString("role"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(rs, ps, c);
		}
		return user;
	}
}
