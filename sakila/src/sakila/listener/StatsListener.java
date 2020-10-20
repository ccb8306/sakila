package sakila.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import sakila.dao.StatsDao;
import sakila.service.StatsService;

@WebListener
public class StatsListener implements HttpSessionListener {
	private StatsService statsService;
    public StatsListener() {
    }
    // 새로운 세션이 감지될 경우
    public void sessionCreated(HttpSessionEvent se)  { 
    	// 완전 새로운 세션일 경우
    	if(se.getSession().isNew()) {
    		System.out.println("새로운 세션 생성");
    		// 방문자 카운트
    		statsService = new StatsService();
    		statsService.countStats();
    	}
    }

    public void sessionDestroyed(HttpSessionEvent se)  { 
    }
	
}
