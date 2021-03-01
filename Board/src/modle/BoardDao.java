package modle;

import java.sql.CallableStatement;
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

			while (rs.next()) {
				BoardDTO boardDTO = new BoardDTO().setBdNum(rs.getInt("bdNum")).setBdTitle(rs.getString("bdTitle"))
						.setBdAuthor(rs.getString("bdAuthor")).setBdCreateDate(rs.getDate("bdCreateDate"));

				boardDTOList.add(boardDTO);
			}

			return boardDTOList;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
			try {
				if (stmt != null)
					stmt.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
			try {
				if (rs != null)
					rs.close();
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
				if (stmt != null) {
					stmt.close();
				}
			} catch (Exception e2) {
			}

			try {
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e2) {
			}
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
			try {
				if (stmt != null)
					stmt.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return -1; // 데이터베이스 오류!
	}

	// 특정 영역 리스트 가져오기
	public ArrayList<BoardDTO> getSelectBoardList(int startRow, int endRow) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DatabaseUtil.getConnection();
			stmt = conn.prepareStatement("select * from board where bdNum between ? and ?");
			stmt.setInt(1, startRow);
			stmt.setInt(2, endRow);
			rs = stmt.executeQuery();
			
			ArrayList<BoardDTO> arrayList = new ArrayList<BoardDTO>();
			while(rs.next()) {
				BoardDTO bdDTO = new BoardDTO()
						.setBdNum(rs.getInt("bdNum"))
						.setBdTitle(rs.getString("bdTitle"))
						.setBdAuthor(rs.getString("bdAuthor"))
						.setBdCreateDate(rs.getDate("bdCreateDate"))
						.setBdViews(rs.getInt("bdViews"));
				
				arrayList.add(bdDTO);
			}
			
			return arrayList; // 총 DB List 갯수

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (Exception e2) {
			}
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (Exception e2) {
			}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e2) {
			}
		}
		
		return null; // Error Code 
	}

	// 전체 리스트 크기 가져오기
	public int getListCount() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			conn = DatabaseUtil.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select count(*) from board");
			rs.next();
			
			int count = rs.getInt(1);
			
			return count; // 총 DB List 갯수

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (Exception e2) {
			}
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (Exception e2) {
			}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e2) {
			}
		}
		
		return -1; // Error Code 
	}

	// Procedure 함수 호출 - 루프
	public void loopInsert(int count) {
		Connection conn = null;
		CallableStatement stmt = null;
		
		try {
			System.out.println("Loop 실행");
			conn = DatabaseUtil.getConnection();
			stmt = conn.prepareCall("call loopInsert(?)");
			stmt.setInt(1, count);
			stmt.execute();
		} catch (Exception e) {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (Exception e2) {}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e2) {}
		}
	}
}
