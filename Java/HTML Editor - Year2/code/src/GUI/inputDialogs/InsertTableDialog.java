package TeamTwoHTMLEditor.GUI.inputDialogs;

import TeamTwoHTMLEditor.GUI.EditorFrame;

import javax.swing.*;
import java.net.URL;

/**
 * Created with IntelliJ IDEA. User: Kocsen Date: 3/21/13 Time: 9:13 PM JDialog
 * displayed to insert a Table
 */
public class InsertTableDialog extends JDialog{
	private int row;
	private int col;
	// ** GUI BUILDER VAR DECLARATIONS
	private javax.swing.JButton AcceptButton;
	private javax.swing.JButton CancelButton;
	private javax.swing.JSpinner colSpinner;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JSpinner rowSpinner;

	/**
	 * Creates new form InsertTableDialog
	 */
	public InsertTableDialog(EditorFrame parent){
		super(parent, true);
		initComponents();
		row = 1;
		col = 1;
	}


	public int getRow(){
		return row;
	}

	public int getCol(){
		return col;
	}


	/**
	 * This method is called from within the constructor to initialize the
	 * form.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents(){

		jPanel1 = new javax.swing.JPanel();
		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		rowSpinner = new javax.swing.JSpinner();
		colSpinner = new javax.swing.JSpinner();
		jLabel3 = new javax.swing.JLabel();
		jPanel2 = new javax.swing.JPanel();
		AcceptButton = new javax.swing.JButton();
		CancelButton = new javax.swing.JButton();

		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("Insert HTML Table");
		setAlwaysOnTop(true);

		URL rowURL = getClass().getResource("/show_table_row.png");
		URL colURL = getClass().getResource("/table_column_add.png");
		URL otherURL = getClass().getResource("/table.png");

		jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
		jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		if(rowURL != null){
			jLabel1.setIcon(new javax.swing.ImageIcon(rowURL)); // NOI18N
		}
		jLabel1.setText("Number of Rows");

		jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
		if(colURL != null){
			jLabel2.setIcon(new javax.swing.ImageIcon(colURL)); // NOI18N
		}
		jLabel2.setText("Number of Columns");

		rowSpinner.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
		rowSpinner.setModel(new javax.swing.SpinnerNumberModel(1, 1, 9999, 1));

		colSpinner.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
		colSpinner.setModel(new javax.swing.SpinnerNumberModel(1, 1, 9999, 1));

		jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
		if(otherURL != null){
			jLabel3.setIcon(new javax.swing.ImageIcon(otherURL)); // NOI18N
		}
		jLabel3.setText("HTML Table Creator");

		javax.swing.GroupLayout jPanel1Layout =
				new javax.swing.GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(
				jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
							 .addGroup(jPanel1Layout.createSequentialGroup()
													.addContainerGap()
													.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
																		   .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
																		   .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
																								  .addGroup(jPanel1Layout.createSequentialGroup()
																														 .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
																														 .addGap(34, 34, 34)
																														 .addComponent(colSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
																								  .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
																																									.addGap(162, 162, 162)
																																									.addComponent(rowSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))))
													.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							 .addGroup(jPanel1Layout.createSequentialGroup()
													.addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
													.addGap(0, 0, Short.MAX_VALUE))
										);
		jPanel1Layout.setVerticalGroup(
				jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
							 .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
																								.addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
																								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																								.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
																													   .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																													   .addComponent(rowSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
																								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																								.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
																													   .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																													   .addComponent(colSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
																								.addContainerGap())
									  );

		CancelButton.setText("Cancel");
		CancelButton.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt){
				CancelButtonActionPerformed();
			}
		});

		AcceptButton.setText("Accept");
		AcceptButton.addActionListener(new java.awt.event.ActionListener(){
			public void actionPerformed(java.awt.event.ActionEvent evt){
				AcceptButtonActionPerformed();
			}
		});

		javax.swing.GroupLayout jPanel2Layout =
				new javax.swing.GroupLayout(jPanel2);
		jPanel2.setLayout(jPanel2Layout);
		jPanel2Layout.setHorizontalGroup(
				jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
							 .addGroup(jPanel2Layout.createSequentialGroup()
													.addComponent(CancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
													.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
													.addComponent(AcceptButton, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
													.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
										);
		jPanel2Layout.setVerticalGroup(
				jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
							 .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
													.addComponent(CancelButton)
													.addComponent(AcceptButton, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
									  );

		javax.swing.GroupLayout layout =
				new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
					  .addGroup(layout.createSequentialGroup()
									  .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									  .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
													  .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
													  .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
								 );
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
					  .addGroup(layout.createSequentialGroup()
									  .addContainerGap()
									  .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
									  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
									  .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
									  .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							   );

		pack();
	}

	private void CancelButtonActionPerformed(){
		row = 0;
		col = 0;
		this.setVisible(false);
		this.dispose();
		System.out.println("You not ACCEPT");
	}

	private void AcceptButtonActionPerformed(){
		row = (Integer) rowSpinner.getValue();
		col = (Integer) colSpinner.getValue();
		this.setVisible(false);
		this.dispose();
	}


}
