package review;

import java.sql.*;
import java.util.*;

import db.DBConnection;

public class ReviewDaoImpl implements ReviewDao {
	DBConnection DBConn = null;
	Connection conn = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;
	String SQL = "";
	ReviewDaoImpl() {
		DBConn = DBConnection.getInstance();
	}
	
	@Override
	public List<HashMap<String, Object>> getSelectAllReview(ReviewVo vo) {
		List<HashMap<String, Object>> li = new ArrayList<HashMap<String, Object>>();
		try {
			conn = DBConn.getConnection();
			if (vo.getCh1()==null || vo.getCh2().equals("")) {
				SQL = "select o.orderid as orderid, "
						+ "r.uid as uid, uname, rnote, "
						+ "p.pid as pid, brand, p.pname, pimg "
						+ "from ordert o join productt p "
						+ "on o.pid = p.pid join reviewt r "
						+ "on o.orderid = r.orderid and o.pid = r.pid "
						+ "order by ridx desc limit ?, ?";
				stmt = conn.prepareStatement(SQL);
				stmt.setInt(1, vo.getPidx());
				stmt.setInt(2, vo.getPaseSize());
			} else if (vo.getCh1().equals("pname")) {
				SQL = "select o.orderid as orderid, "
						+ "r.uid as uid, uname, rnote, "
						+ "p.pid as pid, brand, p.pname as pname, pimg "
						+ "from ordert o join productt p "
						+ "on o.pid = p.pid join reviewt r "
						+ "on o.orderid = r.orderid and o.pid = r.pid "
						+ "where p.pname like ? "
						+ "order by ridx desc limit ?, ?";
				stmt = conn.prepareStatement(SQL);
				stmt.setString(1, "%"+vo.getCh2()+"%");
				stmt.setInt(2, vo.getPidx());
				stmt.setInt(3, vo.getPaseSize());
			}
			rs = stmt.executeQuery();
			while (rs.next()) {
				HashMap<String, Object> m = new HashMap<String, Object>();
				m.put("uid", rs.getString("uid"));
				m.put("pname", rs.getString("pname"));
				m.put("rnote", rs.getString("rnote"));
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
	public HashMap<String, Object> getSelectOneReview(ReviewVo vo) {
		HashMap<String, Object> m = new HashMap<String, Object>();
		try {
			conn = DBConn.getConnection();
			SQL = "select o.orderid as orderid, "
					+ "u.uid as uid, u.uname as uname, "
					+ "p.pid as pid, brand, pname, pimg "
					+ "from ordert o join usert u "
					+ "on o.uid = u.uid join productt p "
					+ "on o.pid = p.pid "
					+ "where o.orderid=? and p.pid=?";
			stmt = conn.prepareStatement(SQL);
			stmt.setString(1, vo.getOrderid());
			stmt.setString(2, vo.getPid());
			rs = stmt.executeQuery();
			if (rs.next()) {
				m.put("orderid", rs.getString("orderid"));
				m.put("uid", rs.getString("uid"));
				m.put("uname", rs.getString("uname"));
				m.put("pid", rs.getString("pid"));
				m.put("pname", rs.getString("pname"));
				m.put("pimg", rs.getString("pimg"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConn.close(rs, stmt, conn);
		}
		return m;
	}


	@Override
	public void insertReview(ReviewVo vo) {
		try {
			conn = DBConn.getConnection();
			SQL = "insert into reviewt (orderid, uid, uname, pid, pname, rnote) "
					+ "values (?, ?, ?, ?, ?, ?)";
			stmt = conn.prepareStatement(SQL);
			stmt.setString(1, vo.getOrderid());
			stmt.setString(2, vo.getUid());
			stmt.setString(3, vo.getUname());
			stmt.setString(4, vo.getPid());
			stmt.setString(5, vo.getPname());
			stmt.setString(6, vo.getRnote());
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConn.close(stmt, conn);
		}
	}

	@Override
	public int totalRecord(ReviewVo vo) {
		int totalRecord = 0;
		try {
			conn = DBConn.getConnection();
			SQL = "select count(*) tr from reviewt";
			stmt = conn.prepareStatement(SQL);
			rs = stmt.executeQuery();
			if (rs.next()) {
				totalRecord = rs.getInt("tr");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConn.close(rs, stmt, conn);
		}
		return totalRecord;
	}

}
