package sakila.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import sakila.query.ActorQuery;
import sakila.query.CustomerQuery;
import sakila.vo.Actor;
import sakila.vo.Staff;


public class ActorDao {
	// 배우 목록
	public List<Actor> selectActorList(Connection conn, int currentPage, int rowPage) throws Exception{
		List<Actor> list = new ArrayList<Actor>();
		Actor a = null;
		
		PreparedStatement stmt = conn.prepareStatement(ActorQuery.SELECT_ACTOR_LIST);
		stmt.setInt(1, (int)(currentPage -1) * rowPage);
		stmt.setInt(2, rowPage);
		System.out.println(stmt + "<--selectActorList stmt");
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			a = new Actor();
			a.setActorId(rs.getInt("actor_id"));
			a.setFirstName(rs.getString("first_name"));
			a.setLastName(rs.getString("last_name"));
			
			list.add(a);
		}
		return list;
	}

	// 배우 리스트 마지막 페이지 구하기
	public int selectActorEndPage(Connection conn, int rowPage)throws Exception {
		int endPage = 0;
		
		PreparedStatement stmt = conn.prepareStatement(ActorQuery.SELECT_ACTOR_LIST_ENDPAGE);
		
		ResultSet rs = stmt.executeQuery();
		System.out.println(stmt + "<--selectActorEndPage stmt");
		
		if(rs.next()) {
			endPage = rs.getInt("COUNT(*)");
			if(endPage%rowPage == 0)			
				endPage = (int)(endPage/rowPage);
			else
				endPage = (int)(endPage/rowPage) + 1;
		}
		return endPage;
	}
	// 배우 상세보기
	public Actor selectActorOne(Connection conn, int actorId) throws Exception{
		Actor a = null;
		
		PreparedStatement stmt = conn.prepareStatement(ActorQuery.SELECT_ACTOR_ONE);
		stmt.setInt(1, actorId);
		System.out.println(stmt + "<--selectActorOne stmt");
		ResultSet rs = stmt.executeQuery();		
		
		while(rs.next()) {
			a = new Actor();
			a.setActorId(rs.getInt("actor_id"));
			a.setFirstName(rs.getString("first_name"));
			a.setLastName(rs.getString("last_name"));
			a.setFilmInfo(rs.getString("film_info").replace(";", "<hr>"));
		}
		return a;
	}
	
	// 한 영화의 배우들 이름 가져오기
	public List<Actor> selectFilmActorOne(Connection conn, int filmId) throws Exception{
		List<Actor> list = new ArrayList<Actor>();
		Actor a = null;
		
		PreparedStatement stmt = conn.prepareStatement(ActorQuery.SELECT_FILM_ACTOR_ONE);
		stmt.setInt(1, filmId);
		System.out.println(stmt + "<--selectFilmActorOne stmt");
		ResultSet rs = stmt.executeQuery();		
		
		while(rs.next()) {
			a = new Actor();
			a.setActorId(rs.getInt("actor_id"));
			a.setFirstName(rs.getString("first_name"));
			a.setLastName(rs.getString("last_name"));
			
			list.add(a);
		}
		return list;
	}

}