package alternativevote;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class TableModel extends AbstractTableModel {

	private final static String PARTY_NAME_COLUMN = "Party Name";
	private final static int PARTY_NAME_COLUMN_INDEX = 0;

	private List<Party> partyList;

	public TableModel(List<Party> partyList) {
		this.partyList = partyList;

	}

	@Override
	public int getRowCount() {
		return partyList.size();
	}

	@Override
	public int getColumnCount() {
		return 5;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
	
		Party row = partyList.get(rowIndex);

		if (columnIndex == PARTY_NAME_COLUMN_INDEX)
			return row.getPartyName();
		else
			return row.getPrefVotes(columnIndex-1);

	}

	@Override
	public String getColumnName(int columnIndex) {
		
		if (columnIndex == 0)
			return PARTY_NAME_COLUMN;
		else
			return "Prefs " + columnIndex;
	}

}
