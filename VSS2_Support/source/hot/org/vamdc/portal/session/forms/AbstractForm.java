package org.vamdc.portal.session.forms;

public abstract class AbstractForm {
	
	protected TransitionsMini transitions;
	
	protected String vss2Prefix;
	
	protected boolean editable = true;
	
	public abstract String getQueryString(boolean vss2, int index);
	
	public abstract void clearFields();

	public String getVss2Prefix() {
		return vss2Prefix;
	}

	public void setVss2Prefix(String vss2Prefix) {
		System.out.println(vss2Prefix);
		this.vss2Prefix = vss2Prefix;
	}	 
	
	public void toggleEditable() {
		editable = !editable;
	}

}
