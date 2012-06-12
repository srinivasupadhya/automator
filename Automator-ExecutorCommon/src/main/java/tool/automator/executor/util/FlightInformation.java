package tool.automator.executor.util;

import java.util.ArrayList;
import java.util.List;

public class FlightInformation {
	private int noOfSegments;
	private List<String> flightCodesList;
	private List<String> aircraftCodesList;
	private String[] amounts;

	public FlightInformation(String flightCode, String aircraftCode, String[] amounts) {
		this.noOfSegments = 1;
		this.flightCodesList = new ArrayList<String>();
		flightCodesList.add(flightCode);
		this.aircraftCodesList = new ArrayList<String>();
		aircraftCodesList.add(aircraftCode);
		this.amounts = amounts;
	}

	public void addFlight(String flight, String aircraft) {
		noOfSegments++;
		flightCodesList.add(flight);
		aircraftCodesList.add(aircraft);
	}

	public int getNoOfSegments() {
		return noOfSegments;
	}

	public void setNoOfSegments(int noOfSegments) {
		this.noOfSegments = noOfSegments;
	}

	public List<String> getFlightCodesList() {
		return flightCodesList;
	}

	public void setFlightCodesList(List<String> flightCodesList) {
		this.flightCodesList = flightCodesList;
	}

	public List<String> getAircraftCodesList() {
		return aircraftCodesList;
	}

	public void setAircraftCodesList(List<String> aircraftCodesList) {
		this.aircraftCodesList = aircraftCodesList;
	}

	public String[] getAmounts() {
		return amounts;
	}

	public void setAmounts(String[] amounts) {
		this.amounts = amounts;
	}

	public String getAmountAt(int index) {
		return amounts[index];
	}

	public String toString() {
		String text = "[";
		for (int i = 0; i < amounts.length; i++) {
			if (i == 0)
				text = text + amounts[i];
			else
				text = text + "," + amounts[i];
		}
		text = text + "]";
		return noOfSegments + " - " + flightCodesList.toString() + " - " + aircraftCodesList.toString() + " - " + text;
	}
}
