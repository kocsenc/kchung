package TeamTwoHTMLEditor.GUI.inputDialogs;

import TeamTwoHTMLEditor.GUI.EditorFrame;

import javax.swing.*;
import java.net.URL;

/**
 * Created with IntelliJ IDEA. User: Kocsen Date: 3/26/13 Time: 1:22 AM JDialog
 * that appears in order to know how many elements inside the list
 */
public class SizeOfListDialog extends JDialog{

	private int sizeOfList;
	private javax.swing.JButton AcceptButton;
	private javax.swing.JButton CancelButton;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JSpinner numElementsSpinner;

	/**
	 * Creates new form SizeOfListDialog
	 */
	public SizeOfListDialog(EditorFrame parent, boolean modal){
		super(parent, modal);
		sizeOfList = 1;
		initComponents();

	}


	private void initComponents(){

		jPanel1 = new javax.swing.JPanel();
		jLabel1 = new javax.swing.JLabel();
		numElementsSpinner = new javax.swing.JSpinner();
		jLabel3 = new javax.swing.JLabel();
		jPanel2 = new javax.swing.JPanel();
		CancelButton = new javax.swing.JButton();
		AcceptButton = new javax.swing.JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

		URL url1 = getClass().getResource("/stock_record_number.png");
		URL url2 = getClass().getResource("/stock_list_bullet.png");

		jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
		jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		if(url1 != null){
			jLabel1.setIcon(new javax.swing.ImageIcon(url1)); // NOI18N
		}
		jLabel1.setText("Number of Elements");

		numElementsSpinner.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
		numElementsSpinner.setModel(new javax.swing.SpinnerNumberModel(1, 1, 9999, 1));

		jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
		if(url2 != null){
			jLabel3.setIcon(new javax.swing.ImageIcon(url2)); // NOI18N
		}
		jLabel3.setText("HTML List Creator");

		javax.swing.GroupLayout jPanel1Layout =
				new javax.swing.GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(
				jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
							 .addGroup(jPanel1Layout.createSequentialGroup()
													.addContainerGap()
													.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
																		   .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
																		   .addGroup(jPanel1Layout.createSequentialGroup()
																								  .addGap(162, 162, 162)
																								  .addComponent(numElementsSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
																													   .addComponent(numElementsSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
													.addComponent(AcceptButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
													.addContainerGap())
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
									  .addContainerGap()
									  .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
													  .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
																												  .addGap(0, 0, Short.MAX_VALUE)
																												  .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
													  .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
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
	}// </editor-fold>

	private void AcceptButtonActionPerformed(){
		sizeOfList = (Integer) numElementsSpinner.getValue();
		this.setVisible(false);
		this.dispose();
	}

	private void CancelButtonActionPerformed(){
		sizeOfList = 0;
		this.setVisible(false);
		this.dispose();
	}

	/**
	 * @return size of the input on the spinner
	 */
	public int getSizeOfList(){
		return sizeOfList;
	}
}
