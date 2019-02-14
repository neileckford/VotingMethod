package alternativevote;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BallotPaper {

	private List<Party> partyList = new ArrayList<>();
	private List<Integer> ballotChoice = new ArrayList<>();

	public BallotPaper(List<Party> partyList) {

		this.partyList = partyList;
	}

	public void scanPartyPrefs() {
		for (Party p : partyList) {

			System.out.println(p.getPartyName());

			Scanner scanner = new Scanner(System.in);
			int choice = Integer.parseInt(scanner.nextLine()) - 1;

			ballotChoice.add(choice);
			p.setVotes(choice);
		}
	}
	
	public void addTestPaper(int[] choices) {
		
		for (int i=0;i<partyList.size();i++) {
			ballotChoice.add(choices[i]);
			partyList.get(i).setVotes(choices[i]-1);
		}
		
	}
	
	public void showPaper() {
		
		for (Integer i : ballotChoice) {
			System.out.println(i);
		}
	}
}
