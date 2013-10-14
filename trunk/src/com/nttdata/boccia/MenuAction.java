package com.nttdata.boccia;

import java.util.Date;

import javax.persistence.EntityManager;
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
	
	private static Log log = LogFactory.getLog(MenuAction.class);
	
	EntityManager em = EMF.get().createEntityManager();

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ActionForward forward = mapping.findForward("success");
		
		Query q = em.createQuery("select from Menu m where m.date = :date");
		q.setParameter("date", new Date());

		Menu menu = (Menu) q.getSingleResult();
		
		request.setAttribute("menu", menu);
		if (log.isDebugEnabled()) log.debug(menu); 
		
		return forward;
	}

}
