package sakila.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sakila.query.SalesQuery;


public class SalesDao {

	// 매장 통계 - 매장별
	public List<Map<String, Object>> selectSalesByStore(Connection conn, String manager)throws Exception {
		List<Map<String, Object>> list = new ArrayList<>();
		Map<String, Object> map = null;

		PreparedStatement stmt = conn.prepareStatement(SalesQuery.SELECT_SALES_BY_STORE);
		stmt.setString(1, manager);
		System.out.println(stmt + "<--selectSalesByStore stmt");
		
		ResultSet rs = stmt.executeQuery();

		while(rs.next()) {
			map = new HashMap<String, Object>();
			map.put("store", rs.getString("store"));
			map.put("manager", rs.getString("manager"));
			map.put("total_sales", rs.getString("total_sales"));
			
			list.add(map);
		}
		
		return list;
	}
	// 매장 통계 - 카테고리별
	public List<Map<String, Object>> selectSalesByCategory(Connection conn)throws Exception {
		List<Map<String, Object>> list = new ArrayList<>();
		Map<String, Object> map = null;

		PreparedStatement stmt = conn.prepareStatement(SalesQuery.SELECT_SALES_BY_CATEGORY);
		System.out.println(stmt + "<--selectSalesByCategory stmt");
		
		ResultSet rs = stmt.executeQuery();

		while(rs.next()) {
			map = new HashMap<String, Object>();
			map.put("category", rs.getString("category"));
			map.put("total_sales", rs.getString("total_sales"));
			
			list.add(map);
		}
		
		return list;
	}
}
