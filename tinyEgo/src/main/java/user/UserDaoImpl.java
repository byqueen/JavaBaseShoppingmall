package user;

import java.sql.*;
import java.util.*;

import db.DBConnection;

public class UserDaoImpl implements UserDao {
	DBConnection DBConn = null;
	Connection conn = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;
	String SQL = "";
	public UserDaoImpl() {
		DBConn = DBConnection.getInstance();
	}

	@Override
	public List<HashMap<String, Object>> getSelectAllUser(UserVo vo) {
		List<HashMap<String, Object>> li = new ArrayList<HashMap<String, Object>>();
		try {
			conn = DBConn.getConnection();
			if (vo.getCh1() == null || vo.getCh2().equals("")) {
				SQL = "select * from userT order by joindate desc limit ?, ?";
				stmt = conn.prepareStatement(SQL);
				stmt.setInt(1, vo.getPidx());
				stmt.setInt(2, vo.getPaseSize());
			} else if (vo.getCh1().equals("uname")) {
				SQL = "select * from userT where uname like ? order by joindate desc limit ?, ?";
				stmt = conn.prepareStatement(SQL);
				stmt.setString(1, "%"+vo.getCh2()+"%");
				stmt.setInt(2, vo.getPidx());
				stmt.setInt(3, vo.getPaseSize());
			} else if (vo.getCh1().equals("uphone")) {
				SQL = "select * from userT where uphone like ? order by joindate desc limit ?, ?";
				stmt = conn.prepareStatement(SQL);
				stmt.setString(1, "%"+vo.getCh2()+"%");
				stmt.setInt(2, vo.getPidx());
				stmt.setInt(3, vo.getPaseSize());
			} else if (vo.getCh1().equals("ugrade")) {
				SQL = "select * from userT where ugrade like ? order by joindate desc limit ?, ?";
				stmt = conn.prepareStatement(SQL);
				stmt.setString(1, "%"+vo.getCh2()+"%");
				stmt.setInt(2, vo.getPidx());
				stmt.setInt(3, vo.getPaseSize());
			}
			rs = stmt.executeQuery();
			while (rs.next()) {
				HashMap<String, Object> m = new HashMap<>();
				m.put("uid", rs.getString("uid"));
				m.put("upwd", rs.getString("upwd"));
				m.put("uname", rs.getString("uname"));
				m.put("ubirth", rs.getString("ubirth"));
				m.put("ugen", rs.getString("ugen"));
				m.put("uphone", rs.getString("uphone"));
				m.put("uzip", rs.getString("uzip"));
				m.put("uaddr1", rs.getString("uaddr1"));
				m.put("uaddr2", rs.getString("uaddr2"));
				m.put("ugrade", rs.getString("ugrade"));
				m.put("unote", rs.getString("unote"));
				m.put("joindate", rs.getString("joindate"));
				li.add(m);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConn.close(rs, stmt, conn);
		}
		return li;
	}

	@Override
	public HashMap<String, Object> getSelectOneUser(UserVo vo) {
		HashMap<String, Object> m = new HashMap<>();
		try {
			conn = DBConn.getConnection();
			SQL = "select * from userT where uid=?";
			stmt = conn.prepareStatement(SQL);
			stmt.setString(1, vo.getUid());
			rs = stmt.executeQuery();
			if (rs.next()) {
				m.put("uid", rs.getString("uid"));
				m.put("upwd", rs.getString("upwd"));
				m.put("uname", rs.getString("uname"));
				m.put("ubirth", rs.getString("ubirth"));
				m.put("ugen", rs.getString("ugen"));
				m.put("uphone", rs.getString("uphone"));
				m.put("uzip", rs.getString("uzip"));
				m.put("uaddr1", rs.getString("uaddr1"));
				m.put("uaddr2", rs.getString("uaddr2"));
				m.put("ugrade", rs.getString("ugrade"));
				m.put("unote", rs.getString("unote"));
				m.put("joindate", rs.getString("joindate"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConn.close(rs, stmt, conn);
		}
		return m;
	}

	@Override
	public void insertUser(UserVo vo) {
		try {
			conn = DBConn.getConnection();
			SQL = "insert into userT (uid, upwd, uname, ubirth, ugen, uphone, uzip, uaddr1, uaddr2) "
					+ "values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			stmt = conn.prepareStatement(SQL);
			stmt.setString(1, vo.getUid());
			stmt.setString(2, vo.getUpwd());
			stmt.setString(3, vo.getUname());
			stmt.setString(4, vo.getUbirth());
			stmt.setString(5, vo.getUgen());
			stmt.setString(6, vo.getUphone());
			stmt.setString(7, vo.getUzip());
			stmt.setString(8, vo.getUaddr1());
			stmt.setString(9, vo.getUaddr2());
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConn.close(stmt, conn);
		}
	}

	@Override
	public void updateUser(UserVo vo) {
		try {
			conn = DBConn.getConnection();
			SQL = "update userT set upwd=?, uname=?, ubirth=?, ugen=?, uphone=?, "
					+ "uzip=?, uaddr1=?, uaddr2=?, ugrade=?, unote=? "
					+ "where uid=?";
			stmt = conn.prepareStatement(SQL);
			stmt.setString(1, vo.getUpwd());
			stmt.setString(2, vo.getUname());
			stmt.setString(3, vo.getUbirth());
			stmt.setString(4, vo.getUgen());
			stmt.setString(5, vo.getUphone());
			stmt.setString(6, vo.getUzip());
			stmt.setString(7, vo.getUaddr1());
			stmt.setString(8, vo.getUaddr2());
			stmt.setString(9, vo.getUgrade());
			stmt.setString(10, vo.getUnote());
			stmt.setString(11, vo.getUid());
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConn.close(stmt, conn);
		}
	}

	@Override
	public void updateUser_pwdX(UserVo vo) {
		try {
			conn = DBConn.getConnection();
			SQL = "update userT set uname=?, ubirth=?, ugen=?, uphone=?, "
					+ "uzip=?, uaddr1=?, uaddr2=?, ugrade=?, unote=? "
					+ "where uid=?";
			stmt = conn.prepareStatement(SQL);
			stmt.setString(1, vo.getUname());
			stmt.setString(2, vo.getUbirth());
			stmt.setString(3, vo.getUgen());
			stmt.setString(4, vo.getUphone());
			stmt.setString(5, vo.getUzip());
			stmt.setString(6, vo.getUaddr1());
			stmt.setString(7, vo.getUaddr2());
			stmt.setString(8, vo.getUgrade());
			stmt.setString(9, vo.getUnote());
			stmt.setString(10, vo.getUid());
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConn.close(stmt, conn);
		}
	}

	@Override
	public void deleteUser(UserVo vo) {
		try {
			conn = DBConn.getConnection();
			SQL = "delete from userT where uid=?";
			stmt = conn.prepareStatement(SQL);
			stmt.setString(1, vo.getUid());
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConn.close(stmt, conn);
		}
	}

	@Override
	public int totalRecord(UserVo vo) {
		int totalRecord = 0;
		// System.out.println("impl ch1 :"+vo.getCh1());
		try {
			conn = DBConn.getConnection();
			if (vo.getCh1() == null || vo.getCh2().equals("")) {
				SQL = "select count(*) as totalRecord from userT";
				stmt = conn.prepareStatement(SQL);
			} else if (vo.getCh1().equals("uname")) {
				SQL = "select count(*) as totalRecord from userT where uname like ?";
				stmt = conn.prepareStatement(SQL);
				stmt.setString(1, "%"+vo.getCh2()+"%");
			} else if (vo.getCh1().equals("ugrade")) {
				SQL = "select count(*) as totalRecord from userT where ugrade like ?";
				stmt = conn.prepareStatement(SQL);
				stmt.setString(1, "%"+vo.getCh2()+"%");
			}
			rs = stmt.executeQuery();
			if (rs.next()) {
				totalRecord = rs.getInt("totalRecord");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConn.close(rs, stmt, conn);
		}
		return totalRecord;
	}

	@Override
	public HashMap<String, Object> userLogin(UserVo vo) {
		HashMap<String, Object> m = new HashMap<>();
		try {
			conn = DBConn.getConnection();
			SQL = "select uid, upwd from userT where uid=?";
			stmt = conn.prepareStatement(SQL);
			stmt.setString(1, vo.getUid());
			rs = stmt.executeQuery();
			if (rs.next()) {
				if (BCrypt.checkpw(vo.getUpwd(), rs.getString("upwd"))) { // BCrypt암호 체크
					m.put("uid", rs.getString("uid"));
				} else {
					m.put("uid", "");
				}
			}
			System.out.println("BCrypt.checkpw : "+BCrypt.checkpw(vo.getUpwd(), rs.getString("upwd")));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConn.close(stmt, conn);
		}
		return m;
	}

	@Override
	public String userIdCk(UserVo vo) {
		String idcheck = "";
		try {
			conn = DBConn.getConnection();
			SQL = "select uid from userT where uid=?";
			stmt = conn.prepareStatement(SQL);
			stmt.setString(1, vo.getUid());
			rs = stmt.executeQuery();
			if (rs.next()) {
				idcheck = "T";
			} else {
				idcheck = "F";
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConn.close(rs, stmt, conn);
		}
		return idcheck;
	}

}
