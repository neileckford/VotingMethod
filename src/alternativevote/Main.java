package alternativevote;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class Main{

	private JPanel mainPanel;

	private JTable table;
	private TableModel model;
	private JLabel textField = new JLabel();

	private List<Party> partyList = new ArrayList<>();
	private List<BallotPaper> ballotPapers = new ArrayList<>();

	GraphCanvas canvasPanel;

	int countingRound = 1;

	public Main() {

		addParties();
		initPreferences();
		addTestPapers();

		MainWindow window = new MainWindow(partyList, countingRound);
	}

	private void initPreferences() {

		for (Party p : partyList) {
			p.initPreferences(partyList.size());
		}
	}

	private void addParties() {

		partyList.add(new Party("SNP", Color.YELLOW));
		partyList.add(new Party("Labour", Color.RED));
		partyList.add(new Party("Conservative", Color.BLUE));
		partyList.add(new Party("Lib Dem", Color.ORANGE));
	}

	private void setTable() {

		List<Party> tableList = new ArrayList<>(partyList);

		model = new TableModel(tableList);
		table = new JTable(model);

		table.setFillsViewportHeight(true);

		JTextField textField = new JTextField();

		table.setDefaultEditor(String.class, new DefaultCellEditor(textField));
	}

	private void addTestPapers() {

		singlePaper(new int[] { 4, 3, 1, 2 });
		singlePaper(new int[] { 4, 3, 2, 1 });
		singlePaper(new int[] { 4, 3, 1, 2 });
		singlePaper(new int[] { 4, 3, 2, 1 });
		singlePaper(new int[] { 4, 3, 1, 2 });
		singlePaper(new int[] { 1, 2, 3, 4 });
		singlePaper(new int[] { 1, 2, 3, 3 });
		singlePaper(new int[] { 1, 2, 3, 4 });
		singlePaper(new int[] { 2, 1, 3, 4 });
	}

	private void singlePaper(int[] choices) {

		BallotPaper paper = new BallotPaper(partyList);
		paper.addTestPaper(choices);
		ballotPapers.add(paper);
	}

	public static void main(String[] args) {
		Main m = new Main();
	}
}