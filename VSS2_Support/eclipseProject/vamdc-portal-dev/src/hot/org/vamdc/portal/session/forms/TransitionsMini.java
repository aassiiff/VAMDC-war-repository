package org.vamdc.portal.session.forms;

import javax.faces.model.SelectItem;

import org.vamdc.portal.utility.UnitConversion;

public class TransitionsMini {

	private String radTransWavelengthFrom;
	private String radTransWavelengthTo;

	// Input Value which will be converted into
	// Rad Trans Wavelength
	private String inputValueFrom;
	private String inputValueTo;

	private String inputParameter = "Wavelength";
	private String selectedUnit = UnitConversion.radTransWavelengthStdUnit;

	private String stateEnergyFrom;
	private String stateEnergyTo;
	private String stateEnergySelectedUnit = "";

	private String radTransProbabilityAFrom;
	private String radTransProbabilityATo;

	private String formType = "";

	public TransitionsMini() {

	}

	public TransitionsMini(String formTypeValue) {
		this.formType = formTypeValue;
	}
	
	public void clearFields(){
		inputParameter = "Wavelength";
		selectedUnit = UnitConversion.radTransWavelengthStdUnit;
		inputValueFrom = "";
		inputValueTo = "";
		stateEnergyFrom = "";
		stateEnergyTo = "";
		radTransProbabilityAFrom = "";
		radTransProbabilityATo = "";
	}

	public String getQueryString(String tempPrefix) {

		// Without IF conditions it adds 0 as the value
		if (stateEnergyFrom != null && stateEnergyFrom.trim().length() > 0) {
			stateEnergyFrom = UnitConversion.unitConversion(stateEnergyFrom,
					stateEnergySelectedUnit, "ENERGY");
		}
		if (stateEnergyTo != null && stateEnergyTo.trim().length() > 0) {
			stateEnergyTo = UnitConversion.unitConversion(stateEnergyTo,
					stateEnergySelectedUnit, "ENERGY");
		}

		String xsamsQuery = "";
		boolean firstEntry = true;

		// QUery String only use radTransWavelengthFrom and radTransWavelengthTo
		// Which is already converted so no more modifications required
		if ((radTransWavelengthFrom != null && radTransWavelengthFrom.trim()
				.length() > 0)
				|| (radTransWavelengthTo != null && radTransWavelengthTo.trim()
						.length() > 0)) {
			if (firstEntry != true) {
				xsamsQuery = xsamsQuery + " AND ";
			} else {
				firstEntry = false;
			}

			xsamsQuery = xsamsQuery
					+ getRangeQuery(radTransWavelengthFrom,
							radTransWavelengthTo, tempPrefix
									+ "RadTransWavelength");
		}

		if ((stateEnergyFrom != null && stateEnergyFrom.trim().length() > 0)
				|| (stateEnergyTo != null && stateEnergyTo.trim().length() > 0)) {
			
			if (this.formType.equalsIgnoreCase("MOLECULE")) {
				if (firstEntry != true) {
					xsamsQuery = xsamsQuery + " AND ";
				} else {
					firstEntry = false;
				}
				xsamsQuery = xsamsQuery
						+ getRangeQuery(stateEnergyFrom, stateEnergyTo,
								tempPrefix + "MoleculeStateEnergy");
				
			}
			if (this.formType.equalsIgnoreCase("ATOM")) {
				if (firstEntry != true) {
					xsamsQuery = xsamsQuery + " AND ";
				} else {
					firstEntry = false;
				}
				xsamsQuery = xsamsQuery
						+ getRangeQuery(stateEnergyFrom, stateEnergyTo,
								tempPrefix + "AtomStateEnergy");
				
			}
		}

		if ((radTransProbabilityAFrom != null && radTransProbabilityAFrom
				.trim().length() > 0)
				|| (radTransProbabilityATo != null && radTransProbabilityATo
						.trim().length() > 0)) {
			if (firstEntry != true) {
				xsamsQuery = xsamsQuery + " AND ";
			} else {
				firstEntry = false;
			}
			xsamsQuery = xsamsQuery
					+ getRangeQuery(radTransProbabilityAFrom,
							radTransProbabilityATo, tempPrefix
									+ "RadTransProbabilityA");
		}

		return xsamsQuery;
	}

	private String getRangeQuery(String value1, String value2, String columnName) {

		if ((value1 != null && value1.trim().length() > 0)
				&& (value2 != null && value2.trim().length() > 0)) {
			// return columnName + " BETWEEN " + value1 + " AND " + value2;

			try {
				if (Double.parseDouble(value1) < Double.parseDouble(value2)) {
					return "(" + columnName + " >= " + value1 + " AND "
							+ columnName + " <= " + value2 + ")";
				} else {
					return "(" + columnName + " >= " + value2 + " AND "
							+ columnName + " <= " + value1 + ")";
				}
			} catch (Exception e) {

			}
			return "(" + columnName + " >= " + value1 + " AND " + columnName
					+ " <= " + value2 + ")";
		} else if (value1 != null && value1.trim().length() > 0) {
			return columnName + " >= " + value1;
		} else if (value2 != null && value2.trim().length() > 0) {
			return columnName + " <= " + value2;
		}
		return "";
	}

	private SelectItem[] unitsSelectItem = { new SelectItem("Å", "Å"),
			new SelectItem("nm", "nm"), new SelectItem("mm", "mm"),
			new SelectItem("m", "m") };

	private SelectItem[] wavelengthUnits = { new SelectItem("Å", "Å"),
			new SelectItem("nm", "nm"), new SelectItem("mm", "mm"),
			new SelectItem("m", "m") };

	private SelectItem[] wavenumberUnits = { new SelectItem("1/cm", "1/cm"),
			new SelectItem("1/m", "1/m") };

	private SelectItem[] frequencyUnits = { new SelectItem("Hz", "Hz"),
			new SelectItem("kHz", "kHz"), new SelectItem("MHz", "MHz"),
			new SelectItem("GHz", "GHz"), new SelectItem("THz", "THz") };

	private SelectItem[] energyUnits = { new SelectItem("eV", "eV"),
			new SelectItem("J", "J"), new SelectItem("erg", "erg") };

	public String getRadTransWavelengthFrom() {
		return radTransWavelengthFrom;
	}

	public void setRadTransWavelengthFrom(String radTransWavelengthFrom) {
		this.radTransWavelengthFrom = radTransWavelengthFrom;
	}

	public String getRadTransWavelengthTo() {
		return radTransWavelengthTo;
	}

	public void setRadTransWavelengthTo(String radTransWavelengthTo) {
		this.radTransWavelengthTo = radTransWavelengthTo;
	}

	public String getInputParameter() {
		return inputParameter;
	}

	public void setInputParameter(String inputParameter) {
		this.inputParameter = inputParameter;
	}

	public SelectItem[] getUnitsSelectItem() {
		return unitsSelectItem;
	}

	public void setUnitsSelectItem(SelectItem[] unitsSelectItem) {
		this.unitsSelectItem = unitsSelectItem;
	}

	public String getSelectedUnit() {
		return selectedUnit;
	}

	public void setSelectedUnit(String selectedUnit) {
		this.selectedUnit = selectedUnit;
	}

	public String getInputValueFrom() {
		return inputValueFrom;
	}

	public void setInputValueFrom(String inputValueFrom) {
		this.inputValueFrom = inputValueFrom;
	}

	public String getInputValueTo() {
		return inputValueTo;
	}

	public void setInputValueTo(String inputValueTo) {
		this.inputValueTo = inputValueTo;
	}

	public String getStateEnergyFrom() {
		return stateEnergyFrom;
	}

	public void setStateEnergyFrom(String stateEnergyFrom) {
		this.stateEnergyFrom = stateEnergyFrom;
	}

	public String getStateEnergyTo() {
		return stateEnergyTo;
	}

	public void setStateEnergyTo(String stateEnergyTo) {
		this.stateEnergyTo = stateEnergyTo;
	}

	public String getStateEnergySelectedUnit() {
		return stateEnergySelectedUnit;
	}

	public void setStateEnergySelectedUnit(String stateEnergySelectedUnit) {
		this.stateEnergySelectedUnit = stateEnergySelectedUnit;
	}

	public String getRadTransProbabilityAFrom() {
		return radTransProbabilityAFrom;
	}

	public void setRadTransProbabilityAFrom(String radTransProbabilityAFrom) {
		this.radTransProbabilityAFrom = radTransProbabilityAFrom;
	}

	public String getRadTransProbabilityATo() {
		return radTransProbabilityATo;
	}

	public void setRadTransProbabilityATo(String radTransProbabilityATo) {
		this.radTransProbabilityATo = radTransProbabilityATo;
	}

	public String getFormType() {
		return formType;
	}

	public void setFormType(String formType) {
		this.formType = formType;
	}

	public void inputParameterChange() {
		System.out.println("inputParameterChange: " + inputParameter);
		if (inputParameter.equalsIgnoreCase("Wavenumber")) {
			unitsSelectItem = this.wavenumberUnits;
			selectedUnit = UnitConversion.radTransWavenumberStdUnit;
		} else if (inputParameter.equalsIgnoreCase("Frequency")) {
			unitsSelectItem = this.frequencyUnits;
			selectedUnit = UnitConversion.radTransFrequencyStdUnit;
		} else if (inputParameter.equalsIgnoreCase("Energy")) {
			unitsSelectItem = this.energyUnits;
			selectedUnit = UnitConversion.radTransEnergyStdUnit;
		} else if (inputParameter.equalsIgnoreCase("Wavelength")) {
			unitsSelectItem = this.wavelengthUnits;
			selectedUnit = UnitConversion.radTransWavelengthStdUnit;
		}
		if (inputValueFrom != null && inputValueFrom.trim().length() > 0) {
			inputConverterFrom();
		}
		if (inputValueTo != null && inputValueTo.trim().length() > 0) {
			inputConverterTo();
		}
	}

	public void inputConverterFrom() {

		if (inputValueFrom != null && inputValueFrom.trim().length() > 0) {
			if (inputParameter.equalsIgnoreCase("Frequency")) {
				this.radTransWavelengthTo = UnitConversion
						.inputConverterToWavelength(inputParameter,
								selectedUnit, inputValueFrom);
			} else if (inputParameter.equalsIgnoreCase("Wavenumber")) {

				this.radTransWavelengthTo = UnitConversion
						.inputConverterToWavelength(inputParameter,
								selectedUnit, inputValueFrom);
			} else if (inputParameter.equalsIgnoreCase("Energy")) {

				this.radTransWavelengthTo = UnitConversion
						.inputConverterToWavelength(inputParameter,
								selectedUnit, inputValueFrom);
			} else if (inputParameter.equalsIgnoreCase("Wavelength")) {
				radTransWavelengthFrom = UnitConversion
						.inputConverterToWavelength(inputParameter,
								selectedUnit, inputValueFrom);
			}
		} else {
			radTransWavelengthFrom = "";
		}
		System.out.println(" radTransWavelengthFrom:  "
				+ radTransWavelengthFrom);
	}

	public void inputConverterTo() {
		if (inputValueTo != null && inputValueTo.trim().length() > 0) {
			if (inputParameter.equalsIgnoreCase("Frequency")) {
				this.radTransWavelengthFrom = UnitConversion
						.inputConverterToWavelength(inputParameter,
								selectedUnit, inputValueTo);
			} else if (inputParameter.equalsIgnoreCase("Wavenumber")) {
				this.radTransWavelengthFrom = UnitConversion
						.inputConverterToWavelength(inputParameter,
								selectedUnit, inputValueTo);
			} else if (inputParameter.equalsIgnoreCase("Energy")) {
				this.radTransWavelengthFrom = UnitConversion
						.inputConverterToWavelength(inputParameter,
								selectedUnit, inputValueTo);
			} else if (inputParameter.equalsIgnoreCase("Wavelength")) {
				radTransWavelengthTo = UnitConversion
						.inputConverterToWavelength(inputParameter,
								selectedUnit, inputValueTo);
			}
		} else {
			radTransWavelengthTo = "";
		}
		System.out.println("radTransWavelengthTo: " + radTransWavelengthTo);
	}

	public void unitChange() {
		System.out.println("unitChange: " + selectedUnit);
		inputConverterFrom();
		inputConverterTo();
	}

}
