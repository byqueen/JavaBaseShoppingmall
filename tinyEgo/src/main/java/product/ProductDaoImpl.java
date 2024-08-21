package product;

import java.sql.*;
import java.util.*;

import db.DBConnection;

public class ProductDaoImpl implements ProductDao {
	DBConnection DBConn = null;
	Connection conn = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;
	String SQL = ""; 
	ProductDaoImpl() {
		DBConn = DBConnection.getInstance();
	}

	@Override
	public List<HashMap<String, Object>> getSelectAllProd(ProductVo vo) {
		List<HashMap<String, Object>> li = new ArrayList<HashMap<String, Object>>();
		try {
			conn = DBConn.getConnection();
			if (vo.getCh1()==null || vo.getCh2().equals("")) {
				SQL = "select * from productT order by pid desc limit 0, 8";
				stmt = conn.prepareStatement(SQL);
			} else if (vo.getCh1().equals("pname")) {
				SQL = "select * from productT where pname like ? order by pid desc limit 0, 8";
				stmt = conn.prepareStatement(SQL);
				stmt.setString(1, "%"+vo.getCh2()+"%");
			} else if (vo.getCh1().equals("brand")) {
				SQL = "select * from productT where brand like ? order by pid desc limit 0, 8";
				stmt = conn.prepareStatement(SQL);
				stmt.setString(1, "%"+vo.getCh2()+"%");
			}
			rs = stmt.executeQuery();
			while (rs.next()) {
				HashMap<String, Object> m = new HashMap<>();
				m.put("pid", rs.getString("pid"));
				m.put("brand", rs.getString("brand"));
				m.put("pname", rs.getString("pname"));
				m.put("supplyPrice", rs.getString("supplyPrice"));
				m.put("salePrice", rs.getString("salePrice"));
				m.put("delcost", rs.getString("delcost"));
				m.put("pimg", rs.getString("pimg"));
				m.put("pnote", rs.getString("pnote"));
				m.put("regdate", rs.getString("regdate"));
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
	public HashMap<String, Object> getSelectOneProd(ProductVo vo) {
		HashMap<String, Object> m = new HashMap<>();
		try {
			conn = DBConn.getConnection();
			SQL = "select * from productT where pid=?";
			stmt = conn.prepareStatement(SQL);
			stmt.setInt(1, vo.getPid());
			rs = stmt.executeQuery();
			if (rs.next()) {
				m.put("pid", rs.getString("pid"));
				m.put("brand", rs.getString("brand"));
				m.put("pname", rs.getString("pname"));
				m.put("supplyPrice", rs.getString("supplyPrice"));
				m.put("salePrice", rs.getString("salePrice"));
				m.put("delcost", rs.getString("delcost"));
				m.put("pimg", rs.getString("pimg"));
				m.put("pnote", rs.getString("pnote"));
				m.put("regdate", rs.getString("regdate"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConn.close(rs, stmt, conn);
		}	
		return m;
	}

	@Override
	public void insertProd(ProductVo vo) {
		try {
			conn = DBConn.getConnection();
			SQL = "insert into productT (pid, brand, pname, supplyPrice, salePrice, delcost, pimg, pnote) "
					+ "values(?, ?, ?, ?, ?, ?, ?, ?)";
			stmt = conn.prepareStatement(SQL);
			stmt.setInt(1, vo.getPid());
			stmt.setString(2, vo.getBrand());
			stmt.setString(3, vo.getPname());
			stmt.setString(4, vo.getSupplyPrice());
			stmt.setString(5, vo.getSalePrice());
			stmt.setString(6, vo.getDelcost());
			stmt.setString(7, vo.getPimg());
			stmt.setString(8, vo.getPnote());
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConn.close(stmt, conn);
		}
		
	}

	@Override
	public void updateProd(ProductVo vo) {
		try {
			conn = DBConn.getConnection();
			SQL = "update productT set brand=?, pname=?, "
					+ "supplyPrice=?, salePrice=?, delcost=?, "
					+ "pimg=?, pnote=? where pid=?";
			stmt = conn.prepareStatement(SQL);
			stmt.setString(1, vo.getBrand());
			stmt.setString(2, vo.getPname());
			stmt.setString(3, vo.getSupplyPrice());
			stmt.setString(4, vo.getSalePrice());
			stmt.setString(5, vo.getDelcost());
			stmt.setString(6, vo.getPimg());
			stmt.setString(7, vo.getPnote());
			stmt.setInt(8, vo.getPid());
			stmt.executeUpdate();
			System.out.println("impl update vo: "+vo);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConn.close(stmt, conn);
		}
	}
	

	@Override
	public void updateProd_fileX(ProductVo vo) {
		try {
			conn = DBConn.getConnection();
			SQL = "update productT set brand=?, pname=?, "
					+ "supplyPrice=?, salePrice=?, delcost=?, pnote=? where pid=?";
			stmt = conn.prepareStatement(SQL);
			stmt.setString(1, vo.getBrand());
			stmt.setString(2, vo.getPname());
			stmt.setString(3, vo.getSupplyPrice());
			stmt.setString(4, vo.getSalePrice());
			stmt.setString(5, vo.getDelcost());
			stmt.setString(6, vo.getPnote());
			stmt.setInt(7, vo.getPid());
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConn.close(stmt, conn);
		}
	}
	

	@Override
	public void deleteProd(ProductVo vo) {
		try {
			conn = DBConn.getConnection();
			SQL = "delete from productT where pid=?";
			stmt = conn.prepareStatement(SQL);
			stmt.setInt(1, vo.getPid());
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConn.close(stmt, conn);
		}
	}

	@Override
	public int maxPid(ProductVo vo) {
		int maxPid = 0;
		try {
			conn = DBConn.getConnection();
			SQL = "select max(pid)+1 as maxPid from productT";
			stmt = conn.prepareStatement(SQL);
			rs = stmt.executeQuery();
			if (rs.next()) {
				if (rs.getInt("maxPid")==0) { 
					maxPid = 1000;
				} else {
					maxPid = rs.getInt("maxPid");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConn.close(rs, stmt, conn);
		}
		return maxPid;
	}

}
