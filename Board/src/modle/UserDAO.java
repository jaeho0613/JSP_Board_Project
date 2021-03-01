package modle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import util.DatabaseUtil;

public class UserDAO {

	// 로그인 체크
	public UserDTO exist(String userID, String userPasswordHash) throws Exception {
		/* 있으면 Member 객체 리턴, 없으면 null 리턴 */
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			// conn = connPool.getConnection();
			conn = DatabaseUtil.getConnection();
			stmt = conn.prepareStatement(
					"select userName, userID, userPasswordHash from user where userID = ? and userPasswordHash = ?");
			stmt.setString(1, userID);
			stmt.setString(2, userPasswordHash);
			rs = stmt.executeQuery();

			if (rs.next()) {
				return new UserDTO()
						.setUserID(rs.getString("userID"))
						.setUserPasswordHash(rs.getString("userPasswordHash"))
						.setUserName(rs.getString("userName"));
								
								
			} else {
				return null;
			}

		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (Exception e2) {
			}
			try {
				if (rs != null)
					rs.close();
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

	// 등록하기
	public int insert(UserDTO user) throws Exception {
		/* 회원등록 */
		PreparedStatement stmt = null;
		Connection conn = null;
		try {
			conn = DatabaseUtil.getConnection();
			stmt = conn.prepareStatement(
					"insert into user(userID, userName, userPassword, userPasswordHash) values(?, ?, ?, ?)");

			stmt.setString(1, user.userID);
			stmt.setString(2, user.userName);
			stmt.setString(3, user.userPassword);
			stmt.setString(4, user.userPasswordHash);
			
			return stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		} finally {
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
