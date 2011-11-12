package org.vamdc.portal.utility;

import static javax.measure.unit.NonSI.ANGSTROM;
import static javax.measure.unit.NonSI.ELECTRON_VOLT;
import static javax.measure.unit.NonSI.ERG;
import static javax.measure.unit.SI.JOULE;
import static javax.measure.unit.SI.METER;
import static javax.measure.unit.SI.MILLIMETER;

import javax.measure.Measure;
import javax.measure.converter.UnitConverter;

public class UnitConversion {
	
	private static double speedOfLightInMeter = 299792458;
	private static double speedOfLightInCM = 29979245800d;
	private static double planksConstantIneVs = 4.135667E-15d;
	
	public static final String radTransWavelengthStdUnit = "�";
	public static final String radTransEnergyStdUnit = "eV";
	public static final String radTransWavenumberStdUnit = "1/cm";
	public static final String radTransFrequencyStdUnit = "Hz";
	public static final String atomStateEnergyStdUnit = "eV";
	
	public static String unitConversion(String value, 
			String selectedUnit, String elementType) {
		double valueDouble;

		try {
			valueDouble = Double.parseDouble(value);
			if (elementType.toUpperCase().equals("WAVELENGTH")) {
				if (radTransWavelengthStdUnit.equals(selectedUnit)) {
					return value;
				} else if (selectedUnit.equals("nm")) {
					return "" + valueDouble * 10;
				} else if (selectedUnit.equals("mum")) {
					return value;
				} else if (selectedUnit.equals("mm")) {
					UnitConverter toArmstrong2 = MILLIMETER
							.getConverterTo(ANGSTROM);
					double angstrom2 = toArmstrong2.convert(Measure.valueOf(
							valueDouble, MILLIMETER).doubleValue(MILLIMETER));
					return "" + angstrom2;
				} else if (selectedUnit.equals("m")) {
					UnitConverter toArmstrong = METER.getConverterTo(ANGSTROM);
					double angstrom = toArmstrong.convert(Measure.valueOf(
							valueDouble, METER).doubleValue(METER));
					return "" + angstrom;
				}

			} else if (elementType.toUpperCase().equals("FREQUENCY")) {
				if (radTransFrequencyStdUnit.equals(selectedUnit)) {
					// frequencyToWavelength(valueDouble);
					return value;
				} else if (selectedUnit.equals("kHz")) {
					// frequencyToWavelength(valueDouble * 1e3);
					return "" + valueDouble * 1e3;
				} else if (selectedUnit.equals("MHz")) {
					// frequencyToWavelength(valueDouble * 1e6);
					return "" + valueDouble * 1e6;
				} else if (selectedUnit.equals("GHz")) {
					// frequencyToWavelength(valueDouble * 1e9);
					return "" + valueDouble * 1e9;
				} else if (selectedUnit.equals("THz")) {
					// frequencyToWavelength(valueDouble * 1e12);
					return "" + valueDouble * 1e12;

				}

			} else if (elementType.toUpperCase().equals("WAVENUMBER")) {
				if (radTransWavenumberStdUnit.equals(selectedUnit)) {
					return value;
				} else if (selectedUnit.equals("1/m")) {
					return "" + valueDouble * 100;
				}

			} else if (elementType.toUpperCase().equals("ENERGY")) {
				if (radTransEnergyStdUnit.equals(selectedUnit)) {
					return value;
				} else if (selectedUnit.equals("J")) {
					UnitConverter toEV = JOULE.getConverterTo(ELECTRON_VOLT);
					double ev = toEV.convert(Measure
							.valueOf(valueDouble, JOULE).doubleValue(JOULE));
					return "" + ev;
				} else if (selectedUnit.equals("erg")) {
					UnitConverter toEV = ERG.getConverterTo(ELECTRON_VOLT);
					double ev = toEV.convert(Measure.valueOf(valueDouble, ERG)
							.doubleValue(ERG));
					return "" + ev;
				}
			}
		} catch (NumberFormatException exception) {
			return "0";
		}
		return value;
	}
	
	private static double frequencyToWavelength(double frequencyInHertz) {
		double wavelengthInMeter = speedOfLightInMeter / frequencyInHertz;

		UnitConverter toArmstrong = METER.getConverterTo(ANGSTROM);
		double wavelengthInAngstrom = toArmstrong.convert(Measure.valueOf(
				wavelengthInMeter, METER).doubleValue(METER));
		return wavelengthInAngstrom;
	}

	private static double energyToFrequency(double energyIneV) {
		double frequency = energyIneV / planksConstantIneVs;
		return frequency;
	}

	private static double wavenumberToFrequency(double wavenumberInCM) {
		double frequency = wavenumberInCM * speedOfLightInCM;
		return frequency;
	}

	private static double wavenumberToWavelength(double wavenumberInCM) {
		double frequency = wavenumberToFrequency(wavenumberInCM);
		double wavelength = frequencyToWavelength(frequency);
		return wavelength;
	}

	private static double energyToWavelength(double energyIneV) {
		double frequency = energyToFrequency(energyIneV);
		double wavelength = frequencyToWavelength(frequency);
		return wavelength;
	}
	
	public static String inputConverterToWavelength(String inputParameter, String selectedUnit, String inputValue) {
		String radTransWavelength = "";
		if (inputValue != null && inputValue.trim().length() > 0) {
			if (inputParameter.equalsIgnoreCase("Frequency")) {
				String frequencyStringInHertz = unitConversion(inputValue,
						selectedUnit, "FREQUENCY");
				double frequencyInHertz = Double
						.parseDouble(frequencyStringInHertz);

				String wavelength = ""
						+ frequencyToWavelength(frequencyInHertz);
				radTransWavelength = wavelength;
			} else if (inputParameter.equalsIgnoreCase("Wavenumber")) {
				String wavenumberStringInCM = unitConversion(inputValue, selectedUnit, "WAVENUMBER");

				double wavenumberInCM = Double
						.parseDouble(wavenumberStringInCM);

				String wavelength = "" + wavenumberToWavelength(wavenumberInCM);
				radTransWavelength = wavelength;
			} else if (inputParameter.equalsIgnoreCase("Energy")) {
				String energyStringIneV = unitConversion(inputValue, selectedUnit, "ENERGY");

				double energyIneV = Double.parseDouble(energyStringIneV);

				String wavelength = "" + energyToWavelength(energyIneV);
				radTransWavelength = wavelength;
			} else if (inputParameter.equalsIgnoreCase("Wavelength")) {
				radTransWavelength = unitConversion(inputValue, selectedUnit, "WAVELENGTH");				
			}
		} else {
			radTransWavelength = "";
		}
		
		return radTransWavelength;

	}

}
