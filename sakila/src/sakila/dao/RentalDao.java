package sakila.dao;

import java.sql.*;
import java.util.*;

import sakila.query.RentalQuery;
import sakila.vo.*;

public class RentalDao {
	// 대여중인 전체 대여 리스트 출력
	public List<RentalAndFilm> selectRentalList(Connection conn, int storeId, int currentPage, int rowPage) throws Exception{
		List<RentalAndFilm> list = new ArrayList<RentalAndFilm>();
		RentalAndFilm raf = null;
		Rental rental = null;
		Film film = null;
		
		PreparedStatement stmt = conn.prepareStatement(RentalQuery.SELECT_RENTAL_LIST);
		stmt.setInt(1, storeId);  // 상점아이디
		stmt.setInt(2,(int)((currentPage -1) * rowPage));  // 페이징
		stmt.setInt(3, rowPage); // 페이징	
		System.out.println(stmt + "<--select rentalList stmt");
		
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			rental = new Rental();
			film = new Film();
			
			rental.setRentalId(rs.getInt("r.rental_id"));
			film.setTitle(rs.getString("f.title"));
			rental.setRentalDate(rs.getString("rental_date"));
			film.setRentalDuration(rs.getInt("f.rental_duration"));
			rental.setReturnDueDate(rs.getString("return_due_date"));
			
			raf = new RentalAndFilm();
			raf.setFilm(film);
			raf.setRental(rental);
			
			list.add(raf);
		}
		
		return list;
	}
	// 대여중인 전체 대여 리스트 최대 페이지
	public int selectRentalListEndPage(Connection conn, int storeId, int rowPage)throws Exception {
		int endPage = 1;
		
		PreparedStatement stmt = conn.prepareStatement(RentalQuery.SELECT_RENTAL_LIST_COUNT);
		stmt.setInt(1, storeId);
		ResultSet rs = stmt.executeQuery();
		
		if(rs.next()) {
			endPage = rs.getInt("COUNT(*)");
			if(endPage%rowPage == 0)			
				endPage = (int)(endPage/rowPage);
			else
				endPage = (int)(endPage/rowPage) + 1;
			
		}	
		return endPage;
	}
	
}
