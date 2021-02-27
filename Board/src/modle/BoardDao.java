package modle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import util.DatabaseUtil;

public class BoardDAO {

	// 데이터 가져오기
	public ArrayList<BoardDTO> getBoardList() throws Exception {
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
		    			.setBdNum(rs.getInt("bdNum"))
		    			.setBdTitle(rs.getString("bdTitle"))
		    			.setBdAuthor(rs.getString("bdAuthor"))
		    			.setBdCreateDate(rs.getDate("bdCreateDate"));
		    	
		    	boardDTOList.add(boardDTO);
		    }
		    
		    return boardDTOList;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) conn.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
			try {
				if (stmt != null) stmt.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
			try {
				if (rs != null) rs.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return null;
	}
	
	// 데이터 작성
	public int write(BoardDTO boardDTO) throws Exception {
		String SQL = "insert into board values(null, ?, ?, now(), null)";
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = DatabaseUtil.getConnection();
			stmt = conn.prepareStatement(SQL);
			stmt.setString(1, boardDTO.getBdTitle());
			stmt.setString(2, boardDTO.getBdAuthor());
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
		} finally {
			try {
				if (conn != null) conn.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
			try {
				if (stmt != null) stmt.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return -1; // 데이터베이스 오류!
	}
	
}
