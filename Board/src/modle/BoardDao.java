package modle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import util.DatabaseUtil;

public class BoardDao {

	// 데이터 가져오기
	public ArrayList<BoardDTO> getBoardList() {
		Connection conn = null; // 연결체
		Statement stmt = null; // 데이터를 가져올 때 쓰는 변수
		ResultSet rs = null; // 가져온 데이터 정보를 담는 그릇
		
		String SQL = "select * from board";
		ArrayList<BoardDTO> boardDTOList = new ArrayList<BoardDTO>();
		
		try {
			conn = DatabaseUtil.getConnection();
			stmt = conn.createStatement();
		    rs = stmt.executeQuery(SQL);
		    
		    while(rs.next()) {
		    	BoardDTO boardDTO = new BoardDTO()
		    			.setBoardNum(rs.getInt("boardNum"))
		    			.setBoardTitle(rs.getString("boardTitle"))
		    			.setBoardAuthor(rs.getString("boardAuthor"))
		    			.setBoardCreateDate(rs.getDate("boardCreateDate"));
		    	
		    	boardDTOList.add(boardDTO);
		    }
		    
		    return boardDTOList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// 데이터 작성
	public int write(BoardDTO boardDTO) {
		String SQL = "insert into board values(null, ?, ?, now(), null)";
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = DatabaseUtil.getConnection();
			stmt = conn.prepareStatement(SQL);
			stmt.setString(1, boardDTO.getBoardTitle());
			stmt.setString(2, boardDTO.getBoardAuthor());
			return stmt.executeUpdate(); // 1번 리턴시 성공!
		} catch (Exception e) {
			try {
				if(stmt != null) {
					stmt.close();
				}
			} catch (Exception e2) {}
			
			try {
				if(conn != null) {
					conn.close();
				}
			} catch (Exception e2) {}
		}
		return -1; // 데이터베이스 오류!
	}
	
}
