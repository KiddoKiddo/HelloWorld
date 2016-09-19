import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.Serializable;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class GameGUI extends JFrame implements Serializable {

	private static final long serialVersionUID = -2915642945247071751L;

	int N, K;

	JTable table;
	JTable maze;
	
	private int CELL_SIZE = 20;

	GameGUI(int N, int K, String playerId) {
		System.out.println("Init Board...");
		
		JPanel panel = new JPanel(new BorderLayout(1, 2));

		/**
		 * Score board
		 */
		String[] headers = new String[] { "Player ID", "Score" };

		table = new JTable() {
			private static final long serialVersionUID = 3739426267786323567L;
			public boolean isCellEditable(int nRow, int nCol) {
				return false;
			}
		};
		table.setPreferredScrollableViewportSize(new Dimension(150, (N-1)*CELL_SIZE));
		table.setFillsViewportHeight(true);
		
		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		tableModel.setColumnIdentifiers(headers);

		JScrollPane tableBoard = new JScrollPane(table);
		panel.add(tableBoard, BorderLayout.WEST);

		/**
		 *  Maze board
		 */
		maze = new JTable(N, N) {
			private static final long serialVersionUID = 3739426267786323567L;

			public boolean isCellEditable(int nRow, int nCol) {
				return false;
			}
		};
		maze.setPreferredScrollableViewportSize(new Dimension(N*CELL_SIZE, N*CELL_SIZE));
		maze.setFillsViewportHeight(true);
		maze.setTableHeader(null);
		setTableCellSize(maze, CELL_SIZE);

		JScrollPane mazeBoard = new JScrollPane(maze);
		panel.add(mazeBoard);

		/**
		 *  JFrame
		 */
		setTitle(playerId);
		add(panel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationByPlatform(true);
		pack();
		setMinimumSize(this.getSize());
		setVisible(true);
	}

	private void setTableCellSize(JTable table, int size) {
		// Row
		table.setRowHeight(size);
		
		// Set align
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		
		// Column
		for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
			TableColumn column = table.getColumnModel().getColumn(i);
			column.setPreferredWidth(size);
			column.setCellRenderer( centerRenderer );
		}
	}
	
	public void updateBoard(Map<String, Player> players, String[][] board){
		/**
		 * Score board
		 */
		
		
		/**
		 *  Maze board
		 */
		DefaultTableModel tableModel = (DefaultTableModel) maze.getModel();
		tableModel.setRowCount(0); // Clear
		for	(int i = 0; i < board.length; i++) {
	        tableModel.addRow(board[i]);
	    }
	    tableModel.fireTableDataChanged();
	}
}
