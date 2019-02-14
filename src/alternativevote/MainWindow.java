package alternativevote;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.BoxLayout;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class MainWindow extends JFrame {

	private JPanel mainPanel;
	private GraphCanvas canvasPanel;
	private JButton countButton;

	private JTable table;
	private TableModel model;
	private JLabel captionLabel = new JLabel();

	private List<Party> partyList = new ArrayList<>();
	private int countingRound;

	public MainWindow(List<Party> partyList, int countingRound) {

		super("The Alternative Voting Method");
		
		this.partyList = partyList;
		this.countingRound = countingRound;

		sortParties(countingRound);
		buildGui();
	}

	private void buildGui() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		canvasPanel = new GraphCanvas(partyList, countingRound);

		setTable();

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(scrollPane.getWidth(), 1));

		captionLabel.setText("Under First Past The Post - " + partyList.get(0).getPartyName()
				+ " would win by first preferences alone.");
		canvasPanel.updateCanvas(countingRound);

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.add(scrollPane);
		mainPanel.add(canvasPanel);

		countButton = new JButton(new AbstractAction("Next Count") {

			@Override
			public void actionPerformed(ActionEvent e) {

				progressCount(++countingRound);
				canvasPanel.repaint();
			}

		});

		JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));

		footerPanel.add(captionLabel);
		footerPanel.add(countButton);

		add(mainPanel, BorderLayout.CENTER);
		add(footerPanel, BorderLayout.SOUTH);

		setSize(800, 600);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private void setTable() {

		List<Party> tableList = new ArrayList<>(partyList);

		model = new TableModel(tableList);
		table = new JTable(model);

		table.setFillsViewportHeight(true);

		JTextField textField = new JTextField();

		table.setDefaultEditor(String.class, new DefaultCellEditor(textField));
	}

	private void progressCount(int countingRound) {

		sortParties(countingRound);
		canvasPanel.updateCanvas(countingRound);
		canvasPanel.repaint();

		captionLabel.setText(partyList.get(partyList.size() - 1).getPartyName() + " eliminated.");

		partyList.remove(partyList.size() - 1);

		if (partyList.size() < 3) {
			
			captionLabel.setText(captionLabel.getText() + " " + partyList.get(0).getPartyName() + " win.");
			countButton.setEnabled(false);
		}

	}

	private void sortParties(int countingRound) {

		Collections.sort(partyList, new Comparator<Party>() {

			@Override
			public int compare(Party o1, Party o2) {

				return o2.getTotalVotes(countingRound) - o1.getTotalVotes(countingRound);
			}

		});
	}

}
