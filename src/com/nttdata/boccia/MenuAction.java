package com.nttdata.boccia;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.nttdata.boccia.em.EMF;
import com.nttdata.boccia.model.dao.MenuDao;
import com.nttdata.boccia.model.dao.MenuDaoImpl;
import com.nttdata.boccia.model.entity.Menu;


public class MenuAction extends Action {
	
	private static Log log = LogFactory.getLog(Menu.class);
//	private static Logger logger = Logger.getLogger(MenuAction.class.getName());
	
	EntityManager em = EMF.get().createEntityManager();

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ActionForward forward = mapping.findForward("success");
		
		Calendar today = new GregorianCalendar();
		today.set(Calendar.HOUR_OF_DAY, 0);
		today.set(Calendar.MINUTE, 0);
		today.set(Calendar.SECOND, 0);
		today.set(Calendar.MILLISECOND, 0);
		
		log.info(today);
		
		MenuDao dao = new MenuDaoImpl();

		log.info("MENU!");
		
		List<Menu> menus = null;
		try {
			menus = dao.findAll();
			
			if (menus.size() == 0) {
				log.info("No menu found.");
				
				Menu menu = createMenu(today, dao);
				
				menus = new ArrayList<Menu>();
				menus.add(menu);
			}
			
		} catch (NoResultException e) {
			log.info("NoResultException");
			
			Menu menu = createMenu(today, dao);
			
			menus = new ArrayList<Menu>();
			menus.add(menu);
			
		} 
		
		request.setAttribute("ciao", "Ciao");
		request.setAttribute("menu", menus.get(0));
		
		return forward;
	}

	private Menu createMenu(Calendar today, MenuDao dao) {
		Menu menu = new Menu();
		menu.setDate(today.getTime());
		
		ArrayList<String> primi = new ArrayList<String>();
		primi.add("Pasta");
		menu.setPrimi(primi);
		
		dao.save(menu);
		return menu;
	}

}
