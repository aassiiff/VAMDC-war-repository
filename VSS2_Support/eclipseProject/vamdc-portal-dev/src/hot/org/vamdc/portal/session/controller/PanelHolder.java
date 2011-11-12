package org.vamdc.portal.session.controller;

import org.vamdc.portal.session.forms.AbstractForm;

public class PanelHolder {

	private String panel;
	private AbstractForm backingBean;
	private int id;
	
	public PanelHolder(String panelValue, AbstractForm backingBeanValue, int idValue){
		
		this.panel = panelValue;
		this.backingBean = backingBeanValue;
		this.id = idValue;		
	}

	public String getPanel() {
		return panel;
	}

	public void setPanel(String panel) {
		this.panel = panel;
	}

	public AbstractForm getBackingBean() {
		return backingBean;
	}

	public void setBackingBean(AbstractForm backingBean) {
		this.backingBean = backingBean;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
}
