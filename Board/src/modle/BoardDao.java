package modle;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import util.DatabaseUtil;

public class BoardDAO {

	// 특정 영역 리스트 가져오기
	public ArrayList<BoardDTO> getSelectBoardList(int startRow, int endRow) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DatabaseUtil.getConnection();

			stmt = conn.prepareStatement("select * from "
					+ "("
					+ "select @rownum:=@rownum+1 as bdNum, bdTitle, bdAuthor, bdCreateDate, bdViews "
					+ "from board, (select @rownum:=0) tmp"
					+ ") list "
					+ "where bdNum between ? and ?");
			stmt.setInt(1, startRow);
			stmt.setInt(2, endRow);
			rs = stmt.executeQuery();

			ArrayList<BoardDTO> arrayList = new ArrayList<BoardDTO>();
			while (rs.next()) {
				BoardDTO bdDTO = new BoardDTO().setBdNum(rs.getInt("bdNum")).setBdTitle(rs.getString("bdTitle"))
						.setBdAuthor(rs.getString("bdAuthor")).setBdCreateDate(rs.getDate("bdCreateDate"))
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
			} catch (Exception e2) {
			}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e2) {
			}
		}
	}
}
