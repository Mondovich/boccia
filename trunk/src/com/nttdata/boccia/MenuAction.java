package com.nttdata.boccia;

import java.util.ArrayList;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.nttdata.boccia.bean.Menu;
import com.nttdata.boccia.em.EMF;


public class MenuAction extends Action {
	
	private static Log log = LogFactory.getLog(Menu.class);
//	private static Logger logger = Logger.getLogger(MenuAction.class.getName());
	
	EntityManager em = EMF.get().createEntityManager();

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ActionForward forward = mapping.findForward("success");
		
		Query q = em.createQuery("select from Menu m where m.date = :date");
		q.setParameter("date", new Date());

		log.info("MENU!");
		
		Menu menu = null;
		try {
			menu = (Menu) q.getSingleResult();
			
			log.info(menu.toString());
		} catch (NoResultException e) {
			log.info("No menu found.");
			
			menu = new Menu();
			menu.setDate(new Date());
			
			ArrayList<String> primi = new ArrayList<String>();
			primi.add("Pasta");
			menu.setPrimi(primi);
			
			em.persist(menu);
		} 
		
		request.setAttribute("ciao", "Ciao");
		request.setAttribute("menu", menu);
		
		return forward;
	}

}
