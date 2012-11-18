package core.jdbc;

import java.sql.*;

import core.jpa.User;

public class JDBCUserAuthentication {
	static Connection con = null;
	static ResultSet rs = null;

	public static User login(String username,String password) {
		User user = new User();
		Statement stmt = null;
		boolean isValid = false;
		String name = "";
		String lastname = "";

		String searchQuery = "SELECT * FROM users WHERE username = ? AND password = ?;";
		// vulnerable to sql injection
		// String searchQuery = "SELECT * FROM users WHERE username='"
		// +username+ "' AND password='"+password+ "'";
		// System.out.println("Try to login with Username : " + username +
		// " Password : " + password);

		try {
			con = ConnectionHandler.getConnection();
			stmt = con.createStatement();
			//
			PreparedStatement pstmt = con.prepareStatement(searchQuery);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			//
			// rs = stmt.executeQuery(searchQuery);
			boolean present = rs.next();
			if (!present)
				isValid = false;
			else if (present) {
				user.setName(rs.getString("name"));
				user.setLastname(rs.getString("lastname"));
				user.setUsername(username);
				isValid = true;
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
				}
				rs = null;
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (Exception e) {
				}
				stmt = null;
			}

			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
				}

				con = null;
			}
		}

		if (isValid) {
			return user;
		} else {
			return null;
		}
	}
}