package alternativevote;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Party {

	private String name;
	private List<Integer> preferences;
	private Color partyColor;

	public Party(String name, Color partyColor) {

		this.name = name;
		this.preferences = new ArrayList<>();
		this.partyColor = partyColor;
	}

	public void initPreferences(int totalParties) {

		for (int i = 0; i < totalParties; i++) {
			preferences.add(0);
		}
	}

	public void showParty() {

		System.out.println("Name: " + name);

		for (int i = 0; i < preferences.size(); i++) {
			System.out.println("Prefs " + (i + 1) + " - " + preferences.get(i));
		}
	}

	public int getTotalVotes(int pref) {

		int votes = 0;
		
		for (int i = 0; i < pref; i++) {
			votes += preferences.get(i);
		}
		
		return votes;
	}
	
	public int getPrefVotes(int pref) {
		return preferences.get(pref);
	}

	public void setVotes(int pref) {
		preferences.set(pref, preferences.get(pref) + 1);
	}

	public String getPartyName() {
		return name;
	}

	public Color getPartyColor() {
		return partyColor;
	}
}
