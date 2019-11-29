package com.yydh.www.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.yydh.www.common.JDBCUtill;

@Repository
public class BoardDAO {
	private Connection conn = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;
	
	private final String BOARD_INSERT = "insert into board(seq,title,writer,content,regDate,cnt) "
			+"values((select nvl(max(seq),0)+1 from board),?,?,?,sysdate,0)";
	private final String BOARD_UPDATE = "update board set title=?, content=? where seq=?";
	private final String BOARD_DELETE = "delete board where seq=?";
	private final String BOARD_GET = "select * from board where seq=?";
	private final String BOARD_LIST = "select * from board order by seq desc";
	private final String BOARD_CNT_UPDATE = "update board set cnt=cnt+1 where seq=?";
	
	public void insertBoard(BoardDTO dto) {
		try {
			conn = JDBCUtill.getConnection();
			stmt = conn.prepareStatement(BOARD_INSERT);
			stmt.setString(1, dto.getTitle());
			stmt.setString(2, dto.getWriter());
			stmt.setString(3, dto.getContent());
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtill.close(stmt, conn);
		}
	}

	public void updateBoard(BoardDTO dto) {
		try {
			conn = JDBCUtill.getConnection();
			stmt = conn.prepareStatement(BOARD_UPDATE);
			stmt.setString(1, dto.getTitle());
			stmt.setString(2, dto.getContent());
			stmt.setInt(3, dto.getSeq());
			stmt.executeUpdate();			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtill.close(stmt, conn);
		}
	}
	
	public void deleteBoard(BoardDTO dto) {
		try {
			conn = JDBCUtill.getConnection();
			stmt = conn.prepareStatement(BOARD_DELETE);
			stmt.setInt(1, dto.getSeq());
			stmt.executeUpdate();			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtill.close(stmt, conn);
		}		
	}
	
	public BoardDTO getBoard(int seq) {
		BoardDTO board = null;
		try {
			conn = JDBCUtill.getConnection();
			stmt = conn.prepareStatement(BOARD_GET);
			stmt.setInt(1, seq);
			rs = stmt.executeQuery();
			if(rs.next()) {
				board = new BoardDTO();
				board.setSeq(rs.getInt("seq"));
				board.setTitle(rs.getString("title"));
				board.setWriter(rs.getString("writer"));
				board.setContent(rs.getString("content"));
				board.setRegDate(rs.getDate("regDate"));
				board.setCnt(rs.getInt("cnt"));
			}
			// update cnt
			stmt = conn.prepareStatement(BOARD_CNT_UPDATE);
			stmt.setInt(1, seq);
			rs = stmt.executeQuery();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtill.close(rs, stmt, conn);
		}	
		return board;
	}
	
	
	public List<BoardDTO> getBoardList() {
		List<BoardDTO> boardList = new ArrayList<BoardDTO>();
		try {
			conn = JDBCUtill.getConnection();
			stmt = conn.prepareStatement(BOARD_LIST);
			rs = stmt.executeQuery();
			while(rs.next()) {
				BoardDTO board = new BoardDTO();
				board.setSeq(rs.getInt("seq"));
				board.setTitle(rs.getString("title"));
				board.setWriter(rs.getString("writer"));
				board.setContent(rs.getString("content"));
				board.setRegDate(rs.getDate("regDate"));
				board.setCnt(rs.getInt("cnt"));
				boardList.add(board);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtill.close(rs, stmt, conn);;
		}
		return boardList;
	}

}
