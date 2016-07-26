package com.nttdata.boccia;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.nttdata.boccia.model.dao.MenuDao;
import com.nttdata.boccia.model.dao.MenuDaoImpl;
import com.nttdata.boccia.model.entity.Menu;


public class HomeAction extends Action {
	
	private static Log log = LogFactory.getLog(Menu.class);
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		log.info("MenuAction.execute ");
		
		ActionForward forward = mapping.findForward("success");
		MenuDao dao = new MenuDaoImpl();

		List<Menu> menus = new ArrayList<Menu>(dao.findAll());
		
		log.info(String.format("Found %d menus", menus.size()));
			
		request.setAttribute("ciao", "Ciao");
		request.setAttribute("menu", menus.get(0));
		
		return forward;
	}

	private Calendar getToday() {
		Calendar today = new GregorianCalendar();
		today.set(Calendar.HOUR_OF_DAY, 0);
		today.set(Calendar.MINUTE, 0);
		today.set(Calendar.SECOND, 0);
		today.set(Calendar.MILLISECOND, 0);
		return today;
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
