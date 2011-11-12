package org.vamdc.portal.session.forms;


import java.util.ArrayList;
import java.util.List;

import org.jboss.seam.ScopeType;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

import uk.ac.cam.ast.vamdc.portal.entity.Isotopologues;
import uk.ac.cam.ast.vamdc.portal.entity.MoleculeNames;
import uk.ac.cam.ast.vamdc.portal.entity.Molecules;
import uk.ac.cam.ast.vamdc.portal.session.IsotopologuesHome;
import uk.ac.cam.ast.vamdc.portal.session.MoleculeNamesHome;
import uk.ac.cam.ast.vamdc.portal.session.MoleculesHome;

@Name("speciesMolecules")
@Scope(ScopeType.SESSION)
public class SpeciesMolecules extends AbstractForm{

	/*
	 * @In(create = true) private RegistryBrowser registryBrowser;
	 */

	private String moleculeChemicalName = null;
	private String moleculeInchiKey = null;
	private String moleculeInchi = null;
	private String moleculeStoichiometricFormula = null;

	private boolean moleculeChemicalNameBoolean = true;
	private boolean moleculeInchiKeyBoolean = true;
	private boolean moleculeInchiBoolean = true;
	private boolean moleculeOrdinaryStructuralFormulaBoolean = true;

	// private boolean chemicalNameImage = false;

	// Molecule Ion Charge
	private String moleculeIonChargeFrom = null;
	private String moleculeIonChargeTo = null;

	// Molecule Protonation
	private String moleculeProtonationFrom = null;
	private String moleculeProtonationTo = null;

	private List<Isotopologues> isotopologuesList = new ArrayList<Isotopologues>();

	private List<String> selectedIsotopesFromCheckBox = new ArrayList<String>();
	private List<String> selectedIsotopesFromCheckBox2 = new ArrayList<String>();

	public SpeciesMolecules(){
		transitions = new TransitionsMini("MOLECULE");
	}
	
	public void clearFields() {
		moleculeChemicalName = "";
		moleculeInchiKey = "";
		moleculeInchi = "";
		moleculeStoichiometricFormula = "";
		moleculeIonChargeFrom = "";
		moleculeIonChargeTo = "";
		moleculeProtonationFrom = "";
		moleculeProtonationTo = "";

		moleculeChemicalNameBoolean = true;
		moleculeInchiBoolean = true;
		moleculeInchiKeyBoolean = true;
		moleculeOrdinaryStructuralFormulaBoolean = true;

		//imageFromChemicalName = fixedURlForImage + "false.jpeg";

		isotopologuesList = new ArrayList<Isotopologues>();
		selectedIsotopesFromCheckBox = new ArrayList<String>();
		selectedIsotopesFromCheckBox2 = new ArrayList<String>();
		
		transitions.clearFields();
	}

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	public String getMoleculeChemicalName() {
		return moleculeChemicalName;
	}

	public void setMoleculeChemicalName(String moleculeChemicalName) {
		this.moleculeChemicalName = moleculeChemicalName;
	}

	public String getMoleculeInchiKey() {
		return moleculeInchiKey;
	}

	public void setMoleculeInchiKey(String moleculeInchiKey) {
		this.moleculeInchiKey = moleculeInchiKey;
	}

	public String getMoleculeInchi() {
		return moleculeInchi;
	}

	public void setMoleculeInchi(String moleculeInchi) {
		this.moleculeInchi = moleculeInchi;
	}

	public String getMoleculeStoichiometricFormula() {
		return moleculeStoichiometricFormula;
	}

	public void setMoleculeStoichiometricFormula(
			String moleculeStoichiometricFormula) {
		this.moleculeStoichiometricFormula = moleculeStoichiometricFormula;
	}

	public String getMoleculeIonChargeFrom() {
		return moleculeIonChargeFrom;
	}

	public void setMoleculeIonChargeFrom(String moleculeIonChargeFrom) {
		this.moleculeIonChargeFrom = moleculeIonChargeFrom;
	}

	public String getMoleculeIonChargeTo() {
		return moleculeIonChargeTo;
	}

	public void setMoleculeIonChargeTo(String moleculeIonChargeTo) {
		this.moleculeIonChargeTo = moleculeIonChargeTo;
	}

	public String getMoleculeProtonationFrom() {
		return moleculeProtonationFrom;
	}

	public void setMoleculeProtonationFrom(String moleculeProtonationFrom) {
		this.moleculeProtonationFrom = moleculeProtonationFrom;
	}

	public String getMoleculeProtonationTo() {
		return moleculeProtonationTo;
	}

	public void setMoleculeProtonationTo(String moleculeProtonationTo) {
		this.moleculeProtonationTo = moleculeProtonationTo;
	}

	public List<Isotopologues> getIsotopologuesList() {
		return isotopologuesList;
	}

	public void setIsotopologuesList(List<Isotopologues> isotopologuesList) {
		this.isotopologuesList = isotopologuesList;
	}

	public List<String> getSelectedIsotopesFromCheckBox() {
		return selectedIsotopesFromCheckBox;
	}

	public boolean isMoleculeChemicalNameBoolean() {
		return (moleculeChemicalNameBoolean && this.editable);
	}

	public void setMoleculeChemicalNameBoolean(
			boolean moleculeChemicalNameBoolean) {
		this.moleculeChemicalNameBoolean = moleculeChemicalNameBoolean;
	}

	public boolean isMoleculeInchiKeyBoolean() {
		return (moleculeInchiKeyBoolean && this.editable);
	}

	public void setMoleculeInchiKeyBoolean(boolean moleculeInchiKeyBoolean) {
		this.moleculeInchiKeyBoolean = moleculeInchiKeyBoolean;
	}

	public boolean isMoleculeInchiBoolean() {
		return (moleculeInchiBoolean && this.editable);
	}

	public void setMoleculeInchiBoolean(boolean moleculeInchiBoolean) {
		this.moleculeInchiBoolean = moleculeInchiBoolean;
	}

	public boolean isMoleculeOrdinaryStructuralFormulaBoolean() {
		System.out.println("isMoleculeOrdinaryStructuralFormulaBoolean: "
				+ (moleculeOrdinaryStructuralFormulaBoolean && this.editable));
		return (moleculeOrdinaryStructuralFormulaBoolean && this.editable);
	}

	public void setMoleculeOrdinaryStructuralFormulaBoolean(
			boolean moleculeOrdinaryStructuralFormulaBoolean) {
		this.moleculeOrdinaryStructuralFormulaBoolean = moleculeOrdinaryStructuralFormulaBoolean;
	}
	
	public TransitionsMini getTransitions() {
		return transitions;
	}

	public void setTransitions(TransitionsMini transitions) {
		this.transitions = transitions;
	}

	public void setSelectedIsotopesFromCheckBox(
			List<String> selectedIsotopesFromCheckBox) {
		System.out.println("selectedIsotopesFromCheckBox: "
				+ selectedIsotopesFromCheckBox.size()
				+ "   selectedIsotopesFromCheckBox2: "
				+ selectedIsotopesFromCheckBox2.size());

		for (int i = 0; i < selectedIsotopesFromCheckBox.size(); i++) {
			System.out.println("selectedIsotopesFromCheckBox: "
					+ selectedIsotopesFromCheckBox.get(i) + ": "
					+ selectedIsotopesFromCheckBox.size());
			// Due to table each selected check box is sent separately
			// The last check box overwrite previous values
			// Created another check box to overcome this issue.
			/**/
			if (!this.selectedIsotopesFromCheckBox2
					.contains(selectedIsotopesFromCheckBox.get(0))) {
				this.selectedIsotopesFromCheckBox2
						.add(selectedIsotopesFromCheckBox.get(0));
			}
		}

		this.selectedIsotopesFromCheckBox = selectedIsotopesFromCheckBox2;
	}
	
	//

	public String getQueryString(boolean vss2, int index) {
		//emptySelectedIsotopesFromCheckBox2();
		System.out.println("Species Molecules get Query String: "
				+ selectedIsotopesFromCheckBox.size() + " "
				+ selectedIsotopesFromCheckBox2.size());
		
		String tempPrefix = "";
		if(vss2 && !vss2Prefix.equalsIgnoreCase("prefix")){
			tempPrefix = " " + vss2Prefix + index + ".";
		} else {
			tempPrefix = " ";
		}
		String xsamsQuery = "(";

		boolean firstEntry = true;

		if (moleculeChemicalName != null
				&& moleculeChemicalName.trim().length() > 0) {
			if (isotopologuesList.size() == 0) {
				if (firstEntry != true) {
					xsamsQuery = xsamsQuery + " AND ";
				} else {
					firstEntry = false;
				}
				xsamsQuery = xsamsQuery + tempPrefix + "MoleculeChemicalName = '"
						+ moleculeChemicalName + "'";

			} else {
				firstEntry = false;
				xsamsQuery = xsamsQuery + "(";
				for (int i = 0; i < selectedIsotopesFromCheckBox2.size(); i++) {
					
					if (i > 0)
						xsamsQuery = xsamsQuery + " OR ";
					xsamsQuery = xsamsQuery + tempPrefix + "MoleculeInchiKey='"
							+ selectedIsotopesFromCheckBox2.get(i) + "'";
				}
				xsamsQuery = xsamsQuery + ")";
			}

		}

		if (moleculeStoichiometricFormula != null
				&& moleculeStoichiometricFormula.trim().length() > 0) {
			if (isotopologuesList.size() == 0) {
				if (firstEntry != true) {
					xsamsQuery = xsamsQuery + " AND ";
				} else {
					firstEntry = false;
				}
				xsamsQuery = xsamsQuery + tempPrefix + "MoleculeStoichiometricFormula = '"
						+ moleculeStoichiometricFormula + "'";
			} else {
				for (int i = 0; i < selectedIsotopesFromCheckBox2.size(); i++) {
					if (i > 0)
						xsamsQuery = xsamsQuery + " OR ";
					xsamsQuery = xsamsQuery + tempPrefix + "MoleculeInchiKey='"
							+ selectedIsotopesFromCheckBox2.get(i) + "'";
				}
			}
		}

		if (moleculeInchiKey != null && moleculeInchiKey.trim().length() > 0) {
			if (firstEntry != true) {
				xsamsQuery = xsamsQuery + " AND ";
			} else {
				firstEntry = false;
			}
			// xsamsQuery = xsamsQuery + " MoleculeInchiKey LIKE '%" +
			// moleculeInchiKey + "%'";
			xsamsQuery = xsamsQuery + tempPrefix + "MoleculeInchiKey='" + moleculeInchiKey
					+ "'";
		} else {
			/*
			 * for (int i = 0; i < selectedIsotopesFromCheckBox2.size(); i++) {
			 * xsamsQuery = xsamsQuery + " OR "; xsamsQuery = xsamsQuery +
			 * " MoleculeInchiKey='" + selectedIsotopesFromCheckBox2.get(i) +
			 * "'"; }
			 */
		}

		if (moleculeInchi != null && moleculeInchi.trim().length() > 0) {
			if (firstEntry != true) {
				xsamsQuery = xsamsQuery + " AND ";
			} else {
				firstEntry = false;
			}
			// xsamsQuery = xsamsQuery + " MoleculeInchi LIKE '%" +
			// moleculeInchi + "%'";
			xsamsQuery = xsamsQuery + tempPrefix + "MoleculeInchi='" + moleculeInchi + "'";
		}

		selectedIsotopesFromCheckBox = selectedIsotopesFromCheckBox2;
		selectedIsotopesFromCheckBox2 = new ArrayList<String>();
		// System.out.println(xsamsQuery);
		// selectedIsotopesFromCheckBox.add("AfterPreview");
		
		if ((moleculeIonChargeFrom != null && moleculeIonChargeFrom.trim().length() > 0)
				|| (moleculeIonChargeTo != null && moleculeIonChargeTo.trim().length() > 0)) {
			if (firstEntry != true) {
				xsamsQuery = xsamsQuery + " AND ";
			} else {
				firstEntry = false;
			}
			xsamsQuery = xsamsQuery
					+ getRangeQuery(moleculeIonChargeFrom, moleculeIonChargeTo,
							tempPrefix + "MoleculeIonCharge");
		}
		
		String transitionsQuery = this.transitions.getQueryString(tempPrefix);
		//System.out.println(transitionsQuery);
		if (transitionsQuery != null && transitionsQuery.trim()
				.length() > 0){
			xsamsQuery = xsamsQuery + " AND (" + transitionsQuery + ")";
		}
		
		xsamsQuery = xsamsQuery + ")";
		
		if(xsamsQuery.trim().contains("=") || xsamsQuery.trim().contains(">=") || 
				xsamsQuery.trim().contains("<=") || xsamsQuery.trim().contains("IN")){
			
		} else {
			xsamsQuery = null;
		}
		
		//System.out.println(xsamsQuery);
		
		return xsamsQuery;
	}

	/*
	 * It is Hack only to clear the check Box after Refine Query is clicked.
	 */
	public void emptySelectedIsotopesFromCheckBox2() {
		selectedIsotopesFromCheckBox2 = new ArrayList<String>();
	}
	
	public List<MoleculeNames> getMoleculeNameQueryWildcard(Object suggest) {
		String pref = (String) suggest;
		List<MoleculeNames> moleculeNamesList = new MoleculeNamesHome()
				.findByMolecNameWildcard(pref);
		return moleculeNamesList;

	}
	
	public List<Molecules> getStoichiometricFormulaQueryWildcard(Object suggest) {		
		String pref = (String) suggest;
		//System.out.println("getStoichiometricFormulaQueryWildcard: " + pref);
		List<Molecules> moleculeNamesList = new MoleculesHome().findByStoichiometricFormulaWildcard(pref);
		return moleculeNamesList;
	}

	public void getMoleculeNameQuery() {
		System.out.println("getMoleculeNameQuery");
		if (moleculeChemicalName != null
				&& moleculeChemicalName.trim().length() > 0) {
			// moleculeOrdinaryStructuralFormulaBoolean = false;
			selectedIsotopesFromCheckBox = new ArrayList<String>();
			selectedIsotopesFromCheckBox2 = new ArrayList<String>();
			isotopologuesList = new ArrayList<Isotopologues>();
			List<MoleculeNames> moleculeNamesList = new MoleculeNamesHome()
					.findByMolecName(moleculeChemicalName);
			if (moleculeNamesList.size() > 0) {
				System.out.println(moleculeNamesList.get(0).getMolecName()
						+ "  " + moleculeNamesList.get(0).getMolecId());
				isotopologuesList = new IsotopologuesHome()
						.findByMolecName(moleculeNamesList.get(0).getMolecId());
				if (isotopologuesList.size() > 0) {
					for (int i = 0; i < isotopologuesList.size(); i++) {
						//selectedIsotopesFromCheckBox.add(isotopologuesList.get(i).getInChIkey());
					}
					System.out.println(isotopologuesList.size() + "  "
							+ isotopologuesList.get(0).getInChIkey());
				}
			}
		} else {
			// moleculeOrdinaryStructuralFormulaBoolean = true;
		}
	}

	public void getStoichiometricFormulaQuery() {
		System.out.println("getStoichiometricFormulaQuery");
		selectedIsotopesFromCheckBox = new ArrayList<String>();
		selectedIsotopesFromCheckBox2 = new ArrayList<String>();
		isotopologuesList = new ArrayList<Isotopologues>();

		List<Molecules> moleculesList = new MoleculesHome()
				.findByStoichiometricFormula(moleculeStoichiometricFormula);
		if (moleculesList.size() > 0) {
			System.out.println(moleculesList.get(0).getMolecName() + "  "
					+ moleculesList.get(0).getId());
			isotopologuesList = new IsotopologuesHome()
					.findByMolecName(moleculesList.get(0).getId());
			if (isotopologuesList.size() > 0) {
				for (int i = 0; i < isotopologuesList.size(); i++) {
					// selectedIsotopesFromCheckBox.add(isotopologuesList.get(i).getInChIkey());
				}
				System.out.println(isotopologuesList.size() + "  "
						+ isotopologuesList.get(0).getInChIkey());
			}
		}
	}

	public void disableIndividualFieldsOnFocus(String fieldName) {
		System.out.println("disableIndividualFieldsOnFocus");
		if (fieldName.equalsIgnoreCase("moleculeChemicalName")) {

			moleculeOrdinaryStructuralFormulaBoolean = false;
			// moleculeOrdinaryStructuralFormula = "disabled";

			moleculeInchiKeyBoolean = false;
			// moleculeInchiKey = "disabled";

			moleculeInchiBoolean = false;
			// moleculeInchi = "disabled";

		} else if (fieldName.equalsIgnoreCase("moleculeStoichiometricFormula")) {
			moleculeChemicalNameBoolean = false;
			moleculeInchiKeyBoolean = false;
			moleculeInchiBoolean = false;

		} else if (fieldName.equalsIgnoreCase("moleculeInchi")) {
			moleculeOrdinaryStructuralFormulaBoolean = false;
			moleculeChemicalNameBoolean = false;
			moleculeInchiKeyBoolean = false;
		} else if (fieldName.equalsIgnoreCase("moleculeInchiKey")) {
			moleculeOrdinaryStructuralFormulaBoolean = false;
			moleculeChemicalNameBoolean = false;
			moleculeInchiBoolean = false;
		}
	}

	public void disableIndividualFieldsOnBlur(String fieldName) {
		System.out.println("disableIndividualFieldsOnBlur");
		if (fieldName.equalsIgnoreCase("moleculeChemicalName")) {
			if (moleculeChemicalName != null
					&& moleculeChemicalName.trim().length() > 0) {
				moleculeOrdinaryStructuralFormulaBoolean = false;
				moleculeInchiKeyBoolean = false;
				moleculeInchiBoolean = false;
				getMoleculeNameQuery();
			} else {
				this.moleculeStoichiometricFormula = "";
				moleculeOrdinaryStructuralFormulaBoolean = true;

				moleculeInchiKey = "";
				moleculeInchiKeyBoolean = true;

				moleculeInchi = "";
				moleculeInchiBoolean = true;

				selectedIsotopesFromCheckBox = new ArrayList<String>();
				selectedIsotopesFromCheckBox2 = new ArrayList<String>();
				isotopologuesList = new ArrayList<Isotopologues>();
			}
		} else if (fieldName.equalsIgnoreCase("moleculeStoichiometricFormula")) {
			if (moleculeStoichiometricFormula != null
					&& moleculeStoichiometricFormula.trim().length() > 0) {
				getStoichiometricFormulaQuery();
			} else {
				moleculeChemicalNameBoolean = true;
				moleculeInchiKeyBoolean = true;
				moleculeInchiBoolean = true;

				selectedIsotopesFromCheckBox = new ArrayList<String>();
				selectedIsotopesFromCheckBox2 = new ArrayList<String>();
				isotopologuesList = new ArrayList<Isotopologues>();
			}

		} else if (fieldName.equalsIgnoreCase("moleculeInchi")) {
			if (moleculeInchi != null && moleculeInchi.trim().length() > 0) {

			} else {
				moleculeChemicalNameBoolean = true;
				moleculeInchiKeyBoolean = true;
				moleculeOrdinaryStructuralFormulaBoolean = true;
			}

		} else if (fieldName.equalsIgnoreCase("moleculeInchiKey")) {
			if (moleculeInchiKey != null
					&& moleculeInchiKey.trim().length() > 0) {

			} else {
				moleculeChemicalNameBoolean = true;
				moleculeInchiBoolean = true;
				moleculeOrdinaryStructuralFormulaBoolean = true;
			}
		}
	}
	
	private boolean selectAllBoolean = true;
	
	
	public boolean isSelectAllBoolean() {
		return selectAllBoolean;
	}

	public void setSelectAllBoolean(boolean selectAllBoolean) {
		this.selectAllBoolean = selectAllBoolean;
	}

	public void selectAllAction(){
		selectedIsotopesFromCheckBox = new ArrayList<String>();
		selectedIsotopesFromCheckBox2 = new ArrayList<String>();
		for (int i = 0; i < isotopologuesList.size(); i++) {
			selectedIsotopesFromCheckBox.add(isotopologuesList.get(i).getInChIkey());
		}
		selectAllBoolean = false;
	}
	
	public void deSelectAllAction(){
		selectedIsotopesFromCheckBox = new ArrayList<String>();
		selectedIsotopesFromCheckBox2 = new ArrayList<String>();		
		selectAllBoolean = true;
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
}
