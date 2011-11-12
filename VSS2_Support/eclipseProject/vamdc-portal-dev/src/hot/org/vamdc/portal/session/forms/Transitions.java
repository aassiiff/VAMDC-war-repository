package org.vamdc.portal.session.forms;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import javax.faces.model.SelectItem;
import javax.measure.Measure;
import javax.measure.converter.UnitConverter;
import static javax.measure.unit.NonSI.*;
import static javax.measure.unit.SI.*;

@Name("transitions")
@Scope(ScopeType.SESSION)
public class Transitions extends AbstractForm {

	
	
	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	public Transitions(){
		transitions = new TransitionsMini("");
	}
	
	public TransitionsMini getTransitions() {
		return transitions;
	}

	public void setTransitions(TransitionsMini transitions) {
		this.transitions = transitions;
	}
	
	public String getQueryString(boolean vss2, int index){
		String transitionsQuery = transitions.getQueryString("");
		//System.out.println("Transitions QUery String Mini " + transitionsQuery);
		if (transitionsQuery != null && transitionsQuery.trim()
				.length() > 0){;
		} else {
			transitionsQuery = null;
		}
		return transitionsQuery;
	}
	
	
	public void clearFields() {
		transitions.clearFields();
	}
	
		
}