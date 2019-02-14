package alternativevote;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class GraphCanvas extends JPanel {

	private List<Party> partyList;
	private int countingRound;

	public GraphCanvas(List<Party> partyList, int countingRound) {
		this.partyList = partyList;
		this.countingRound = countingRound;
	}

	public void updateCanvas(int countingRound) {
		this.countingRound = countingRound;
	}

	public void paintComponent(Graphics graphics) {

		final int chartWidth = (int) (this.getWidth());
		final int chartHeight = (int) (this.getHeight());

		final float totalVotes = getTotalVotes(countingRound);

		for (int pref = 0; pref < countingRound; pref++) {

			graphics.setColor(Color.WHITE);
			graphics.fillRect(0, 0, chartWidth, chartHeight);

			for (int i = 0; i < partyList.size(); i++) {
				
				int columnSpacing = chartWidth / (partyList.size() + 1); // even spacing depending on number of columns
				int columnWidth = columnSpacing / 2;

				int xPos = (i + 1) * columnSpacing - columnWidth / 2;
				int yTop = chartHeight;
				
				yTop -= 25;

				// find current height of column
				for (int j = 0; j <= pref; j++) {

					float fractionOfTotalVotes = partyList.get(i).getPrefVotes(j) / totalVotes;
					int thisPrefHeight = (int) (chartHeight * fractionOfTotalVotes);
					yTop -= thisPrefHeight;

					graphics.setColor(transparentColor(partyList.get(i).getPartyColor(), 255 - j * 70));
					graphics.fillRect(xPos, yTop, columnWidth, thisPrefHeight);

					graphics.setColor(Color.BLACK);
					graphics.drawLine(xPos, yTop, xPos + columnWidth, yTop);
				}

				graphics.setFont(new Font("TimesRoman", Font.BOLD, 18)); 
				graphics.drawString("" + partyList.get(i).getTotalVotes(pref+1), xPos + columnWidth / 2, yTop - 10);
			
				String nameText = partyList.get(i).getPartyName();
				int centerText = xPos + columnWidth/2 - (graphics.getFontMetrics().stringWidth(nameText)/2);
				graphics.drawString(nameText, centerText, chartHeight);
			}
		}
	}

	private int getTotalVotes(int countingRound) {

		int total = 0;

		for (Party p : partyList) {

			for (int i = 0; i < countingRound; i++) {
				total += p.getPrefVotes(i);
			}
		}

		return total;
	}

	private Color transparentColor(Color col, int opacity) {
		return new Color(col.getRed(), col.getGreen(), col.getBlue(), opacity);
	}
}
