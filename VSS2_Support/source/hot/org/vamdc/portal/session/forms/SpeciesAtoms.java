package org.vamdc.portal.session.forms;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

@Name("speciesAtoms")
@Scope(ScopeType.SESSION)
public class SpeciesAtoms extends AbstractForm {

	// Atom Name
	private String atomSymbol = null;

	private String atomInchiKey = null;

	private String atomInchi = null;

	// Atomic Mass
	private String atomMassNumberFrom = null;
	private String atomMassNumberTo = null;

	// Atomic Ion Charge
	private String atomIonChargeFrom = null;
	private String atomIonChargeTo = null;

	// Atomic Nuclear Charge
	private String atomNuclearChargeFrom = null;
	private String atomNuclearChargeTo = null;

	
	
	//public TransitionsMini transitions = new TransitionsMini();
	
	public SpeciesAtoms(){
		transitions = new TransitionsMini("ATOM");
	}

	public void clearFields() {
		atomSymbol = "";
		atomInchiKey = "";
		atomInchi = "";
		atomMassNumberFrom = "";
		atomMassNumberTo = "";
		atomIonChargeFrom = "";
		atomIonChargeTo = "";
		atomNuclearChargeFrom = "";
		atomNuclearChargeTo = "";
		transitions.clearFields();
	}

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	public String getAtomSymbol() {
		return atomSymbol;
	}

	public void setAtomSymbol(String atomSymbol) {
		this.atomSymbol = atomSymbol;
		System.out.println("this.atomSymbol: " + this.atomSymbol);
	}

	public String getAtomMassNumberFrom() {
		return atomMassNumberFrom;
	}

	public void setAtomMassNumberFrom(String atomMassNumberFrom) {
		this.atomMassNumberFrom = atomMassNumberFrom;
	}

	public String getAtomMassNumberTo() {
		return atomMassNumberTo;
	}

	public void setAtomMassNumberTo(String atomMassNumberTo) {
		this.atomMassNumberTo = atomMassNumberTo;
	}

	public String getAtomIonChargeFrom() {
		return atomIonChargeFrom;
	}

	public void setAtomIonChargeFrom(String atomIonChargeFrom) {
		this.atomIonChargeFrom = atomIonChargeFrom;
	}

	public String getAtomIonChargeTo() {
		return atomIonChargeTo;
	}

	public void setAtomIonChargeTo(String atomIonChargeTo) {
		this.atomIonChargeTo = atomIonChargeTo;
	}

	public String getAtomInchiKey() {
		return atomInchiKey;
	}

	public void setAtomInchiKey(String atomInchiKey) {
		this.atomInchiKey = atomInchiKey;
	}

	public String getAtomInchi() {
		return atomInchi;
	}

	public void setAtomInchi(String atomInchi) {
		this.atomInchi = atomInchi;
	}

	public String getAtomNuclearChargeFrom() {
		return atomNuclearChargeFrom;
	}

	public void setAtomNuclearChargeFrom(String atomNuclearChargeFrom) {
		this.atomNuclearChargeFrom = atomNuclearChargeFrom;
	}

	public String getAtomNuclearChargeTo() {
		return atomNuclearChargeTo;
	}

	public void setAtomNuclearChargeTo(String atomNuclearChargeTo) {
		this.atomNuclearChargeTo = atomNuclearChargeTo;
	}

	public TransitionsMini getTransitions() {
		return transitions;
	}

	public void setTransitions(TransitionsMini transitions) {
		this.transitions = transitions;
	}

	public String getQueryString(boolean vss2, int index) {
		String tempPrefix = "";
		
		if(vss2 && !vss2Prefix.equalsIgnoreCase("prefix")){
			tempPrefix = " " + vss2Prefix + index + ".";
		} else {
			tempPrefix = " ";
		}
		
		String xsamsQuery = "(";

		boolean firstEntry = true;

		if (atomSymbol != null && atomSymbol.trim().length() > 0) {
			xsamsQuery = xsamsQuery + tempPrefix + "AtomSymbol " + parseAtomSymbol()
					+ "";
			firstEntry = false;
		}

		if (atomInchi != null && atomInchi.trim().length() > 0) {

			if (firstEntry != true) {
				xsamsQuery = xsamsQuery + " AND ";
			} else {
				firstEntry = false;
			}

			xsamsQuery = xsamsQuery + tempPrefix + "AtomInchi LIKE '%" + atomInchi + "%'";

		}

		if (atomInchiKey != null && atomInchiKey.trim().length() > 0) {

			if (firstEntry != true) {
				xsamsQuery = xsamsQuery + " AND ";
			} else {
				firstEntry = false;
			}
			xsamsQuery = xsamsQuery + tempPrefix + "AtomInchiKey LIKE '%" + atomInchiKey
					+ "%'";

		}

		if ((atomMassNumberFrom != null && atomMassNumberFrom.trim().length() > 0)
				|| (atomMassNumberTo != null && atomMassNumberTo.trim()
						.length() > 0)) {
			if (firstEntry != true) {
				xsamsQuery = xsamsQuery + " AND ";
			} else {
				firstEntry = false;
			}
			xsamsQuery = xsamsQuery
					+ getRangeQuery(atomMassNumberFrom, atomMassNumberTo,
							tempPrefix + "AtomMassNumber");
		}

		if ((atomIonChargeFrom != null && atomIonChargeFrom.trim().length() > 0)
				|| (atomIonChargeTo != null && atomIonChargeTo.trim().length() > 0)) {
			if (firstEntry != true) {
				xsamsQuery = xsamsQuery + " AND ";
			} else {
				firstEntry = false;
			}
			xsamsQuery = xsamsQuery
					+ getRangeQuery(atomIonChargeFrom, atomIonChargeTo,
							tempPrefix + "AtomIonCharge");
		}

		if ((atomNuclearChargeFrom != null && atomNuclearChargeFrom.trim()
				.length() > 0)
				|| (atomNuclearChargeTo != null && atomNuclearChargeTo.trim()
						.length() > 0)) {
			if (firstEntry != true) {
				xsamsQuery = xsamsQuery + " AND ";
			} else {
				firstEntry = false;
			}
			xsamsQuery = xsamsQuery
					+ getRangeQuery(atomNuclearChargeFrom, atomNuclearChargeTo,
							tempPrefix + "AtomNuclearCharge");
		}
		
		String transitionsQuery = this.transitions.getQueryString(tempPrefix);
		//System.out.println(transitionsQuery);
		if (transitionsQuery != null && transitionsQuery.trim()
				.length() > 0){
			xsamsQuery = xsamsQuery + " AND (" + transitionsQuery + ")";
		}
		
		xsamsQuery = xsamsQuery + " )";
		if(xsamsQuery.trim().contains("=") || xsamsQuery.trim().contains(">=") || 
				xsamsQuery.trim().contains("<=") || xsamsQuery.trim().contains("IN")){
			
		} else {
			xsamsQuery = null;
		}
				
		return xsamsQuery;
	}

	private String getRangeQuery(String value1, String value2, String columnName) {
		if ((value1 != null && value1.trim().length() > 0)
				&& (value2 != null && value2.trim().length() > 0)) {
			// return columnName + " BETWEEN " + value1 + " AND " + value2;
			try {
				if (Double.parseDouble(value1) < Double.parseDouble(value2)) {
					return "(" + columnName + " >= " + value1 + " AND " + columnName
							+ " <= " + value2 + ")";
				} else {
					return "(" + columnName + " >= " + value2 + " AND " + columnName
							+ " <= " + value1 + ")";
				}
			} catch (Exception e) {

			}

			return "(" + columnName + " >= " + value1 + " AND " + columnName + " <= "
					+ value2 + ")";
		} else if (value1 != null && value1.trim().length() > 0) {
			return columnName + " >= " + value1;
		} else if (value2 != null && value2.trim().length() > 0) {
			return columnName + " <= " + value2;
		}
		return "";
	}

	private String parseAtomSymbol() {
		String collectiveString = "";

		String[] temp;

		/* delimiter */
		String delimiter = " ";
		/* given string will be split by the argument delimiter provided. */ 
		
		temp = atomSymbol.trim().split(delimiter);

		if (temp.length == 1) {
			collectiveString = "= '" + temp[0] + "' ";
		} else {
			collectiveString = "IN (";
			for (int i = 0; i < temp.length; i++) {
				if (i == (temp.length - 1)) {
					collectiveString = collectiveString + "'" + temp[i] + "'";
				} else {
					collectiveString = collectiveString + "'" + temp[i] + "',";
				}
			}
			collectiveString = collectiveString + ")";
		}
		return collectiveString;
	}

}
