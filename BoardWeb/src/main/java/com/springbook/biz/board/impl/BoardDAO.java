package com.springbook.biz.board.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.springbook.biz.board.BoardVO;
import com.springbook.biz.common.JDBCUtil;

//DAO(Data Access Object)
@Repository("boardDAO")
public class BoardDAO  {
	//JDBC���� ����
	private Connection c= null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	//SQL��ɾ�
	private final String BOARD_INSERT = " insert into board(seq, title, writer, content) "
										+ " values((select nvl(max(seq),0)+1 from board),?,?,?  ) ";
	private final String BOARD_UPDATE = " update board set title = ?, content = ? , where seq = ? ";
	private final String BOARD_DELETE = " delete board where seq = ? ";
	private final String BOARD_GET = " select * from board where seq = ? ";
	private final String BOARD_LIST = " select * from board order by seq desc ";
	
	
	//CRUD ����� �޼ҵ� ����
	//�۵��
	public void insertBoard(BoardVO vo) {
		System.out.println("===>JDBC�� insertBoard()��� ó��");
		try {
			c = JDBCUtil.getConnection();
			ps =  c.prepareStatement(BOARD_INSERT);
			ps.setString(1, vo.getTitle());
			ps.setString(2, vo.getWriter());
			ps.setString(3, vo.getContent());
			ps.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(null, ps, c);
		}
	}
	
	
	// �� ����
	public void updateBoard(BoardVO vo) {
		System.out.println("===>JDBC�� updateBoard()��� ó��");
		try {
			c = JDBCUtil.getConnection();
			ps = c.prepareStatement(BOARD_UPDATE);
			ps.setString(1, vo.getTitle());
			ps.setString(2, vo.getContent());
			ps.setInt(3, vo.getSeq());
			ps.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(rs, ps, c);
		}
	}
	
	// �� ����
	
	public void deleteBoard(BoardVO vo) {
		System.out.println("===>JDBC�� deleteBoard()��� ó��");
		try {
			c = JDBCUtil.getConnection();
			ps = c.prepareStatement(BOARD_DELETE);
			ps.setInt(1, vo.getSeq());
			ps.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(rs, ps, c);
		}
	}
	
	// �� �� ��ȸ
	public BoardVO getBoard(BoardVO vo) {
		BoardVO board = null;
		try {
			c = JDBCUtil.getConnection();
			ps = c.prepareStatement(BOARD_GET);
			ps.setInt(1, vo.getSeq());
			rs = ps.executeQuery();
			while(rs.next()) {
				board = new BoardVO();
				board.setSeq(rs.getInt("seq"));
				board.setCnt(rs.getInt("cnt"));
				board.setContent(rs.getString("content"));
				board.setRegdate(rs.getDate("regdate"));
				board.setTitle(rs.getString("title"));
				board.setWriter(rs.getString("writer"));
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			JDBCUtil.close(rs, ps, c);
		}
		return board;
	}
	
	//�� ��� ��ȸ
	public List<BoardVO> getBoardList(BoardVO vo) {
		System.out.println("===> JDBC�� getBoardList()��� ó��");
		List<BoardVO> list = new ArrayList<BoardVO>();
		try {
			c = JDBCUtil.getConnection();
			ps = c.prepareStatement(BOARD_LIST);
			rs = ps.executeQuery();
			while(rs.next()) {
				
				BoardVO board = new BoardVO();
				board.setSeq(rs.getInt("seq"));
				board.setCnt(rs.getInt("cnt"));
				board.setContent(rs.getString("content"));
				board.setRegdate(rs.getDate("regdate"));
				board.setTitle(rs.getString("title"));
				board.setWriter(rs.getString("writer"));
				list.add(board);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, ps, c);
		}
		return list;
	}
}
