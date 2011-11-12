package org.vamdc.portal.session.controller;

import java.util.ArrayList;
import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.log.Log;
import org.vamdc.portal.session.forms.Collisions;
import org.vamdc.portal.session.forms.SpeciesAtoms;
import org.vamdc.portal.session.forms.SpeciesMolecules;
import org.vamdc.portal.session.forms.Transitions;
import org.vamdc.portal.session.query.XSAMSQueryGeneratorNew;

@Name("navigator")
@Scope(ScopeType.SESSION)
public class Navigator {

	@Logger
	private Log log;

	@In(create=true)
	private FacesMessages facesMessages;
	
	@In(create = true)
	private XSAMSQueryGeneratorNew xsamsQueryNew;

	@In(create = true)
	SelectedRestrictables selectedRestrictables;

	private int panelHolderCounter = 1;
	public List<PanelHolder> speciesAtomsPanelHolderList = new ArrayList<PanelHolder>();
	
	public List<PanelHolder> speciesMoleculesPanelHolderList = new ArrayList<PanelHolder>();
	
	public List<PanelHolder> porcessesTransitionsPanelHolderList = new ArrayList<PanelHolder>();
	public List<PanelHolder> porcessesCollisionsPanelHolderList = new ArrayList<PanelHolder>();

	private String mainOptionPanel = "/xsamsForm/XSAMSMainOption.xhtml";

	private String mainFormPanel = "/xsamsForm/includeXsamsForm.xhtml";
	private String freeFormPanel = "/xsamsForm/emptyWithWidth.xhtml";
	private String restrictableTablePanel = "/xsamsForm/restrictableTable.xhtml";
	private String formButtonsPanel = "/xsamsForm/empty.xhtml";

	private boolean stage1Display = false;
	private boolean stage2Display = false;

	private String vssVersion = "VSS1";

	private List<String> selectedNodes;

	public void subOptionSpeciesAtoms() {
		log.info("subOptionSpeciesAtoms()");

		PanelHolder panelHolder = new PanelHolder(
				"/xsamsForm/speciesAtoms.xhtml", new SpeciesAtoms(),
				panelHolderCounter++);

		speciesAtomsPanelHolderList.add(panelHolder);

		formButtonsPanel = "/xsamsForm/xsamsFormButtons.xhtml";
	}

	public String getAtomsForm(int index) {
		String atomsPanel = "/xsamsForm/emptyWithWidth.xhtml";
		if (speciesAtomsPanelHolderList.size() > index) {
			atomsPanel = speciesAtomsPanelHolderList.get(index).getPanel();
		}
		return atomsPanel;
	}

	public void removeSpeciesAtomsPanel(PanelHolder panel) {
		speciesAtomsPanelHolderList.remove(panel);
		lastPanelCheck();
	}

	public void subOptionSpeciesMolecules() {
		PanelHolder panelHolder = new PanelHolder(
				"/xsamsForm/speciesMolecules.xhtml", new SpeciesMolecules(),
				panelHolderCounter++);

		speciesMoleculesPanelHolderList.add(panelHolder);

		formButtonsPanel = "/xsamsForm/xsamsFormButtons.xhtml";
	}

	public String getMoleculesForm(int index) {
		String moleculesPanel = "/xsamsForm/emptyWithWidth.xhtml";
		if (speciesMoleculesPanelHolderList.size() > index) {
			moleculesPanel = speciesMoleculesPanelHolderList.get(index)
					.getPanel();
		}

		return moleculesPanel;
	}

	public void removeSpeciesMoleculesPanel(PanelHolder panel) {
		log.info("removeSpeciesMoleculesPanel");
		speciesMoleculesPanelHolderList.remove(panel);
		lastPanelCheck();
	}

	// There is only one Transition Panel
	public void subOptionTransitions() {
		log.info("subOptionTransitions");
		if (porcessesTransitionsPanelHolderList.size() == 0) {
			PanelHolder panelHolder = new PanelHolder(
					"/xsamsForm/transitions.xhtml", new Transitions(),
					panelHolderCounter++);
			porcessesTransitionsPanelHolderList.add(panelHolder);
		}
		formButtonsPanel = "/xsamsForm/xsamsFormButtons.xhtml";
	}

	public String getTransitionsForm(int index) {
		String transitionsPanel = "/xsamsForm/emptyWithWidth.xhtml";
		if (porcessesTransitionsPanelHolderList.size() > index) {
			transitionsPanel = porcessesTransitionsPanelHolderList.get(index)
					.getPanel();
		}

		return transitionsPanel;
	}

	public void removeTransitionsPanel(PanelHolder panel) {
		porcessesTransitionsPanelHolderList.remove(panel);
		lastPanelCheck();
	}

	// porcessesCollisionsPanelHolderList
	public void subOptionCollisions() {
		log.info("subOptionCollisions");
		if (porcessesCollisionsPanelHolderList.size() == 0) {
			PanelHolder panelHolder = new PanelHolder(
					"/xsamsForm/collisions.xhtml", new Collisions(),
					panelHolderCounter++);
			porcessesCollisionsPanelHolderList.add(panelHolder);
		}
		formButtonsPanel = "/xsamsForm/xsamsFormButtons.xhtml";
	}

	public String getCollisionsForm(int index) {
		String transitionsPanel = "/xsamsForm/emptyWithWidth.xhtml";
		if (porcessesCollisionsPanelHolderList.size() > index) {
			transitionsPanel = porcessesCollisionsPanelHolderList.get(index)
					.getPanel();
		}

		return transitionsPanel;
	}

	public void removeCollisionsPanel(PanelHolder panel) {
		porcessesCollisionsPanelHolderList.remove(panel);
		lastPanelCheck();
	}
	
	private void lastPanelCheck(){
		if(speciesAtomsPanelHolderList.size() == 0 && speciesMoleculesPanelHolderList.size() == 0 
				&& porcessesTransitionsPanelHolderList.size() == 0 && porcessesCollisionsPanelHolderList.size() == 0){
			formButtonsPanel = "/xsamsForm/empty.xhtml";
		}
			
	}

	public void subOptionFreeForm() {
		log.info("Free Form");

		cancelQueryBuilder();

		//this.
		freeFormPanel = "/xsamsForm/freeForm.xhtml";
		formButtonsPanel = "/xsamsForm/empty.xhtml";
		this.clearForm();
	}

	public String getMainOptionPanel() {
		return mainOptionPanel;
	}

	public void setMainOptionPanel(String mainOptionPanel) {
		this.mainOptionPanel = mainOptionPanel;
	}

	public String getFreeFormPanel() {
		return freeFormPanel;
	}

	public String getRestrictableTablePanel() {
		// log.info("Right Panel Called");
		return restrictableTablePanel;
	}

	public void setRestrictableTablePanel(String rightPanel) {
		this.restrictableTablePanel = rightPanel;
	}

	public void setFreeFormPanel(String formPanel4) {
		this.freeFormPanel = formPanel4;
	}

	public String getFormButtonsPanel() {
		return formButtonsPanel;
	}
	
	public String getMainFormPanel() {
		return mainFormPanel;
	}

	public void setMainFormPanel(String mainFormPanel) {
		this.mainFormPanel = mainFormPanel;
	}

	public void setFormButtonsPanel(String formButtonsPanel) {
		this.formButtonsPanel = formButtonsPanel;
	}

	public List<String> getSelectedNodes() {
		return selectedNodes;
	}

	public void setSelectedNodes(List<String> selectedNodes) {
		this.selectedNodes = selectedNodes;
	}

	public boolean isStage1Display() {
		return stage1Display;
	}

	public void setStage1Display(boolean stage1Display) {
		this.stage1Display = stage1Display;
	}

	public boolean isStage2Display() {
		return stage2Display;
	}

	public void setStage2Display(boolean stage2Display) {
		this.stage2Display = stage2Display;
	}

	public List<PanelHolder> getSpeciesAtomsPanelHolderList() {
		return speciesAtomsPanelHolderList;
	}

	public void setSpeciesAtomsPanelHolderList(
			List<PanelHolder> speciesAtomsPanelHolderList) {
		this.speciesAtomsPanelHolderList = speciesAtomsPanelHolderList;
	}

	public List<PanelHolder> getSpeciesMoleculesPanelHolderList() {
		return speciesMoleculesPanelHolderList;
	}

	public void setSpeciesMoleculesPanelHolderList(
			List<PanelHolder> speciesMoleculesPanelHolderList) {
		this.speciesMoleculesPanelHolderList = speciesMoleculesPanelHolderList;
	}

	public List<PanelHolder> getPorcessesTransitionsPanelHolderList() {
		return porcessesTransitionsPanelHolderList;
	}

	public void setPorcessesTransitionsPanelHolderList(
			List<PanelHolder> porcessesTransitionsPanelHolderList) {
		this.porcessesTransitionsPanelHolderList = porcessesTransitionsPanelHolderList;
	}

	public List<PanelHolder> getPorcessesCollisionsPanelHolderList() {
		return porcessesCollisionsPanelHolderList;
	}

	public void setPorcessesCollisionsPanelHolderList(
			List<PanelHolder> porcessesCollisionsPanelHolderList) {
		this.porcessesCollisionsPanelHolderList = porcessesCollisionsPanelHolderList;
	}

	public String getVssVersion() {
		return vssVersion;
	}

	public void setVssVersion(String vssVersion) {
		this.vssVersion = vssVersion;
	}

	public void executeQueryStage1() {
		
		boolean atomsQuery = false;
		boolean moleculesQuery = false;
		boolean transitionsQuery = false;
		
		String queryString = "SELECT ALL WHERE ";

		String queryOperator = " OR ";
		String atomsQueryString = "";
		String moleculesQueryString = "";
		String transtionsQueryString = "";
		String collisionsQueryString = "";

		if (vssVersion.equalsIgnoreCase("VSS2")) {
			queryOperator = " AND ";
		}

		String tempQueryString = "";
		for (int i = 0; i < speciesAtomsPanelHolderList.size(); i++) {

			if (vssVersion.equalsIgnoreCase("VSS2")) {
				tempQueryString = speciesAtomsPanelHolderList.get(i)
						.getBackingBean().getQueryString(true, speciesAtomsPanelHolderList.get(i).getId());
			} else {
				tempQueryString = speciesAtomsPanelHolderList.get(i)
						.getBackingBean().getQueryString(false, speciesAtomsPanelHolderList.get(i).getId());
			}
			if (tempQueryString != null) {
				atomsQueryString = atomsQueryString + tempQueryString;
				if (i < speciesAtomsPanelHolderList.size() - 1) {
					atomsQueryString = atomsQueryString + queryOperator;
				}
				atomsQuery = true;
			}
		}

		for (int j = 0; j < speciesMoleculesPanelHolderList.size(); j++) {
			if (j > 0) {
				queryString = queryString + queryOperator;
			}
			if (vssVersion.equalsIgnoreCase("VSS2")) {
				tempQueryString = speciesMoleculesPanelHolderList.get(j)
						.getBackingBean().getQueryString(true, speciesMoleculesPanelHolderList.get(j).getId());
			} else {
				tempQueryString = speciesMoleculesPanelHolderList.get(j)
						.getBackingBean().getQueryString(false, speciesMoleculesPanelHolderList.get(j).getId());
			}
			if (tempQueryString != null) {
				if(atomsQuery == true){
					moleculesQueryString = moleculesQueryString + queryOperator;
					atomsQuery = false;
				} 
				moleculesQueryString = moleculesQueryString + tempQueryString;
				if (j < speciesMoleculesPanelHolderList.size() - 1) {
					moleculesQueryString = moleculesQueryString + queryOperator;
				}
				moleculesQuery = true;
			}
		}
		
		// There should be only one Transitions Panel
		for (int j = 0; j < porcessesTransitionsPanelHolderList.size(); j++) {
			tempQueryString = porcessesTransitionsPanelHolderList.get(j).getBackingBean().getQueryString(false, j);
			if (tempQueryString != null) {
				if(atomsQuery == true || moleculesQuery == true){
					tempQueryString = " AND " + tempQueryString;
					atomsQuery = false;
					moleculesQuery = false;
				}
				transtionsQueryString = tempQueryString;
				transitionsQuery = true;
			}
		}
		
		for (int j = 0; j < porcessesCollisionsPanelHolderList.size(); j++) {
			tempQueryString = porcessesCollisionsPanelHolderList.get(j).getBackingBean().getQueryString(false, j);
			if (tempQueryString != null) {
				if(atomsQuery == true || moleculesQuery == true || transitionsQuery == true){
					tempQueryString = " AND " + tempQueryString;
					atomsQuery = false;
					moleculesQuery = false;
					transitionsQuery = true;
				}
				collisionsQueryString = tempQueryString;
			}
		}
		if((speciesAtomsPanelHolderList.size() + speciesMoleculesPanelHolderList.size()) > 1){
			queryString = queryString + "(" + atomsQueryString + moleculesQueryString + ")" + transtionsQueryString + collisionsQueryString;
		} else {
			queryString = queryString + atomsQueryString + moleculesQueryString + transtionsQueryString + collisionsQueryString;
		}
		
		//System.out.println(queryString);

		/**/
		if (xsamsQueryNew.executeQueryStage1(queryString)) {
			this.stage1Display = true;
			
			//Called from Preview not from Refine
			toggleEditable(false);
			formButtonsPanel = "/xsamsForm/refineButton.xhtml";
			restrictableTablePanel = "/xsamsForm/xsamsQueryResponseStage1.xhtml";
		} else {
			restrictableTablePanel = "/xsamsForm/queryValidationError.xhtml";
		}
		
	}

	public void queryValidationOK() {
		restrictableTablePanel = "/xsamsForm/restrictableTable.xhtml";
	}

	public void executeQueryStage1FreeForm() {
		this.stage1Display = true;
		mainOptionPanel = "/xsamsForm/XSAMSMainOption.xhtml";
		formButtonsPanel = "/xsamsForm/emptyWithWidth.xhtml";
		restrictableTablePanel = "/xsamsForm/xsamsQueryResponseStage1.xhtml";
	}

	public void executeQueryStage2() {
		xsamsQueryNew.executeQueryStage2();
		this.stage1Display = false;
		selectedRestrictables.resetSelectedRestrictablesList();
		mainOptionPanel = "/xsamsForm/empty.xhtml";
		cancelQueryBuilder();
		freeFormPanel = "/xsamsForm/emptyWithWidth.xhtml";
		restrictableTablePanel = "/xsamsForm/empty.xhtml";
		formButtonsPanel = "/xsamsForm/emptyWithWidth.xhtml";
		mainFormPanel = "/xsamsForm/submittedXSAMSQueries.xhtml";
		try {
			// Thread.sleep(100);
			new Thread() {
				public void run() {
					// resetQueryBuilder();
				}
			};
		} catch (Exception e) {

		}
	}

	
	public void cancelQueryBuilderWithMainOption(){
		this.stage1Display = false;
		mainOptionPanel = "/xsamsForm/XSAMSMainOption.xhtml";
		mainFormPanel = "/xsamsForm/includeXsamsForm.xhtml";
		cancelQueryBuilder();
		facesMessages.clear();
	}
	
	
	public void cancelQueryBuilder() {
		log.info("cancelQueryBuilder called");
		
		selectedRestrictables.resetSelectedRestrictablesList();
		speciesAtomsPanelHolderList = new ArrayList<PanelHolder>();
		
		speciesMoleculesPanelHolderList = new ArrayList<PanelHolder>();
		
		porcessesTransitionsPanelHolderList = new ArrayList<PanelHolder>();
		porcessesCollisionsPanelHolderList = new ArrayList<PanelHolder>();
		
		formButtonsPanel = "/xsamsForm/emptyWithWidth.xhtml";
		restrictableTablePanel = "/xsamsForm/restrictableTable.xhtml";
	}

	public void defaultQueryBuilder() {
		log.info("enableQueryBuilder called");
		this.stage1Display = false;
		mainOptionPanel = "/xsamsForm/XSAMSMainOption.xhtml";
		cancelQueryBuilder();
		freeFormPanel = "/xsamsForm/empty.xhtml";
		restrictableTablePanel = "/xsamsForm/restrictableTable.xhtml";
		formButtonsPanel = "/xsamsForm/emptyWithWidth.xhtml";
	}

	public void resetQueryBuilder() {
		log.info("enableQueryBuilder called");
		this.stage1Display = false;
		mainOptionPanel = "/xsamsForm/XSAMSMainOption.xhtml";
		formButtonsPanel = "/xsamsForm/xsamsFormButtons.xhtml";
	}

	// Called from Refine Button
	public void enableQueryBuilder() {
		log.info("enableQueryBuilder called");
		toggleEditable(true);
		this.stage1Display = false;
		mainOptionPanel = "/xsamsForm/XSAMSMainOption.xhtml";
		restrictableTablePanel = "/xsamsForm/restrictableTable.xhtml";
		formButtonsPanel = "/xsamsForm/xsamsFormButtons.xhtml";
	}

	public void clearForm() {
		selectedRestrictables.resetSelectedRestrictablesList();
		for (int i = 0; i < speciesAtomsPanelHolderList.size(); i++) {
			speciesAtomsPanelHolderList.get(i).getBackingBean().clearFields();
		}

		for (int i = 0; i < speciesMoleculesPanelHolderList.size(); i++) {
			speciesMoleculesPanelHolderList.get(i).getBackingBean().clearFields();
		}

		for (int i = 0; i < porcessesTransitionsPanelHolderList.size(); i++) {
			porcessesTransitionsPanelHolderList.get(i).getBackingBean().clearFields();
		}

		for (int i = 0; i < porcessesCollisionsPanelHolderList.size(); i++) {
			porcessesCollisionsPanelHolderList.get(i).getBackingBean().clearFields();
		}

	}
	
	public void toggleEditable(boolean refine){
		for (int i = 0; i < speciesAtomsPanelHolderList.size(); i++) {
			speciesAtomsPanelHolderList.get(i).getBackingBean().toggleEditable();
		}

		for (int i = 0; i < speciesMoleculesPanelHolderList.size(); i++) {
			speciesMoleculesPanelHolderList.get(i).getBackingBean().toggleEditable();
			if(refine){
			/*
			 * It is Hack only to clear the check Box after Refine Query is clicked.
			 */
				SpeciesMolecules tempMolecule = (SpeciesMolecules)(speciesMoleculesPanelHolderList.get(i).getBackingBean());
				tempMolecule.emptySelectedIsotopesFromCheckBox2();
			}
		}

		for (int i = 0; i < porcessesTransitionsPanelHolderList.size(); i++) {
			porcessesTransitionsPanelHolderList.get(i).getBackingBean().toggleEditable();
		}

		for (int i = 0; i < porcessesCollisionsPanelHolderList.size(); i++) {
			porcessesCollisionsPanelHolderList.get(i).getBackingBean().toggleEditable();
		}
	}

	public boolean displayButtonsForm() {
		return false;
	}

}
