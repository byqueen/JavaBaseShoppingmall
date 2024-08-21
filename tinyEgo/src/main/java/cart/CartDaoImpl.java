package cart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import db.DBConnection;

public class CartDaoImpl implements CartDao {
	DBConnection DBConn = null;
	Connection conn = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;
	String SQL = ""; 
	
	CartDaoImpl() {
		DBConn = DBConnection.getInstance();
	}
	
	@Override
	public List<HashMap<String, Object>> getSelectAllCart(CartVo vo) {
		List<HashMap<String, Object>> li = new ArrayList<HashMap<String, Object>>();
		try {
			conn = DBConn.getConnection();
			SQL = "select cartid, p.pid as pid, pimg, brand, pname, "
					+ "salePrice, cartAmount, delcost "
					+ "from productt p join cartt c "
					+ "on p.pid = c.pid "
					+ "where uid=? "
					+ "order by cartid desc";
			stmt = conn.prepareStatement(SQL);
			stmt.setString(1, vo.getUid());
			rs = stmt.executeQuery();
			while (rs.next()) {
				HashMap<String, Object> m = new HashMap<>();
				m.put("cartid", rs.getString("cartid"));
				m.put("pid", rs.getString("pid"));
				m.put("pimg", rs.getString("pimg"));
				m.put("brand", rs.getString("brand"));
				m.put("pname", rs.getString("pname"));
				m.put("salePrice", rs.getString("salePrice"));
				m.put("cartAmount", rs.getString("cartAmount"));
				m.put("delcost", rs.getString("delcost"));
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
	public void insertCart(CartVo vo) { // 장바구니에 담기지 않는 상품인 경우
		try {
			conn = DBConn.getConnection();
			SQL = "insert into cartT (uid, pid, cartAmount) values(?, ?, ?)";
			stmt = conn.prepareStatement(SQL);
			stmt.setString(1, vo.getUid());
			stmt.setString(2, vo.getPid());
			stmt.setString(3, vo.getCartAmount());
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConn.close(stmt, conn);
		}
	}

	@Override
	public void insertCartIn(CartVo vo) { // 이미 장바구니에 담긴 상품인 경우
		try {
			conn = DBConn.getConnection();
			SQL = "update cartT set cartAmount=cartAmount+? where cartid=? and uid=?";
			stmt = conn.prepareStatement(SQL);
			stmt.setString(1, vo.getCartAmount());
			stmt.setString(2, vo.getCartid());
			stmt.setString(3, vo.getUid());
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConn.close(stmt, conn);
		}
	}
	
	@Override
	public void updateCart(CartVo vo) {
		try {
			conn = DBConn.getConnection();
			SQL = "update cartT set cartAmount=? where cartid=?";
			stmt = conn.prepareStatement(SQL);
			stmt.setString(1, vo.getCartAmount());
			stmt.setString(2, vo.getCartid());
			stmt.executeUpdate();
			System.out.println("impl vo : "+vo);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConn.close(stmt, conn);
		}
		
	}

	@Override
	public void deleteCart(CartVo vo) {
		try {
			conn = DBConn.getConnection();
			SQL = "delete from cartT where pid=?";
			stmt = conn.prepareStatement(SQL);
			stmt.setString(1, vo.getPid());
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConn.close(stmt, conn);
		}
	}

	@Override
	public void deleteCartAll(CartVo vo) {
		try {
			conn = DBConn.getConnection();
			SQL = "delete from cartT";
			stmt = conn.prepareStatement(SQL);
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConn.close(stmt, conn);
		}
	}
	
	@Override
	public void insertOrder(CartVo vo) {
		try {
			conn = DBConn.getConnection();
			SQL = "insert into orderT (orderid, uid, pid, "
					+ "orderAmount, orderPrice, delcost, "
					+ "dname, dphone, daddr, dmemo) "
					+ "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			stmt = conn.prepareStatement(SQL);
			stmt.setString(1, vo.getOrderid());
			stmt.setString(2, vo.getUid());
			stmt.setString(3, vo.getPid());
			stmt.setString(4, vo.getOrderAmount());
			stmt.setString(5, vo.getOrderPrice());
			stmt.setString(6, vo.getDelcost());
			stmt.setString(7, vo.getDname());
			stmt.setString(8, vo.getDphone());
			stmt.setString(9, vo.getDaddr());
			stmt.setString(10, vo.getDmemo());
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConn.close(stmt, conn);
		}
	}
	
	@Override
	public List<HashMap<String, Object>> getSelectAllOrderAdm(CartVo vo) {
		List<HashMap<String, Object>> li = new ArrayList<HashMap<String, Object>>();
		try {
			conn = DBConn.getConnection();
			SQL = "select orderid, count(*) totalNumber, u.uid as uid, uname, pname, orderdate "
					+ "from ordert o join productt p "
					+ "on o.pid = p.pid join usert u "
					+ "on o.uid = u.uid "
					+ "group by orderid, orderdate "
					+ "order by orderdate desc";
			stmt = conn.prepareStatement(SQL);
			rs = stmt.executeQuery();
			while (rs.next()) {
				HashMap<String, Object> m = new HashMap<>();
				m.put("orderid", rs.getString("orderid"));
				m.put("uid", rs.getString("uid"));
				m.put("uname", rs.getString("uname"));
				m.put("pname", rs.getString("pname"));
				m.put("totalNumber", rs.getString("totalNumber"));
				m.put("orderdate", rs.getString("orderdate"));
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
	public List<HashMap<String, Object>> getSelectAllOrderCust(CartVo vo) {
		List<HashMap<String, Object>> li = new ArrayList<HashMap<String, Object>>();
		try {
			conn = DBConn.getConnection();
			SQL = "select orderid, count(*) totalNumber, pname, orderdate "
					+ "from ordert o join productt p "
					+ "on o.pid = p.pid "
					+ "where uid = ? "
					+ "group by orderid, orderdate "
					+ "order by orderdate desc";
			stmt = conn.prepareStatement(SQL);
			stmt.setString(1, vo.getUid());
			rs = stmt.executeQuery();
			while (rs.next()) {
				HashMap<String, Object> m = new HashMap<>();
				m.put("orderid", rs.getString("orderid"));
				m.put("pname", rs.getString("pname"));
				m.put("totalNumber", rs.getString("totalNumber"));
				m.put("orderdate", rs.getString("orderdate"));
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
	public List<HashMap<String, Object>> getSelectOneOrder(CartVo vo) {
		List<HashMap<String, Object>> li = new ArrayList<HashMap<String, Object>>();
		try {
			conn = DBConn.getConnection();
			SQL = "select orderid, "
					+ "p.pid as pid, pimg, brand, pname, "
					+ "uid, orderPrice, orderAmount, o.delcost as delcost, "
					+ "dname, dphone, daddr, dmemo "
					+ "from productt p join ordert o "
					+ "on p.pid = o.pid "
					+ "where uid = ? and orderid = ?";
			stmt = conn.prepareStatement(SQL);
			stmt.setString(1, vo.getUid());
			stmt.setString(2, vo.getOrderid());
			rs = stmt.executeQuery();
			while (rs.next()) {
				HashMap<String, Object> m = new HashMap<>();
				m.put("orderid", rs.getString("orderid"));
				m.put("pid", rs.getString("pid"));
				m.put("pimg", rs.getString("pimg"));
				m.put("brand", rs.getString("brand"));
				m.put("pname", rs.getString("pname"));
				m.put("orderPrice", rs.getString("orderPrice"));
				m.put("orderAmount", rs.getString("orderAmount"));
				m.put("delcost", rs.getString("delcost"));
				m.put("dname", rs.getString("dname"));
				m.put("dphone", rs.getString("dphone"));
				m.put("daddr", rs.getString("daddr"));
				m.put("dmemo", rs.getString("dmemo"));
				li.add(m);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConn.close(rs, stmt, conn);
		}
		return li;
	}

}
