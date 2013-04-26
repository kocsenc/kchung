package TeamTwoHTMLEditor.GUI.inputDialogs;

import javax.swing.*;
import java.net.URL;

/**
 * Created with IntelliJ IDEA. User: Kocsen Date: 4/7/13 Time: 2:28 PM To change
 * this template use File | Settings | File Templates.
 */
public class URLDialog extends JDialog{

	String theURL = "";
	// Variables declaration from GUI Builder
	private javax.swing.JButton AcceptButton;
	private javax.swing.JButton CancelButton;
	private javax.swing.JTextField URLField;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel2;


	public URLDialog(java.awt.Frame parent, boolean modal){
		super(parent, modal);
		initComponents();

	}


	private void initComponents(){

		jPanel1 = new javax.swing.JPanel();
		jLabel1 = new javax.swing.JLabel();
		jLabel3 = new javax.swing.JLabel();
		URLField = new javax.swing.JTextField();
		jPanel2 = new javax.swing.JPanel();
		CancelButton = new javax.swing.JButton();
		AcceptButton = new javax.swing.JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

		URL titleURL = getClass().getResource("/insert_link.png");
		URL promptURL = getClass().getResource("/stock_connect_to_url.png");


		jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
		jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		if(titleURL != null){
			jLabel1.setIcon(new javax.swing.ImageIcon(titleURL)); // NOI18N
		}
		jLabel1.setText("URL");

		jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
		if(promptURL != null){
			jLabel3.setIcon(new ImageIcon(promptURL)); // NOI18N
		}
		jLabel3.setText("Insert Tag With Link");

		URLField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        URLField.setText("http://");
		URLField.setToolTipText("Insert URL here");
		URLField.addActionListener(new java.awt.event.ActionListener()

		{
			public void actionPerformed(java.awt.event.ActionEvent evt){
				URLFieldActionPerformed(evt);
			}
		}

								  );

		javax.swing.GroupLayout jPanel1Layout =
				new javax.swing.GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addComponent(URLField).addContainerGap()).addGroup(jPanel1Layout.createSequentialGroup().addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(0, 0, Short.MAX_VALUE)));
		jPanel1Layout.setVerticalGroup(
				jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
							 .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
																								.addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
																								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																								.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
																													   .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																													   .addComponent(URLField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
																										 ).addContainerGap())
									  );

		CancelButton.setText("Cancel");
		CancelButton.addActionListener(new java.awt.event.ActionListener()

		{
			public void actionPerformed(java.awt.event.ActionEvent evt){
				CancelButtonActionPerformed(evt);
			}
		}
									  );

		AcceptButton.setText("Accept");
		AcceptButton.addActionListener(new java.awt.event.ActionListener()

		{
			public void actionPerformed(java.awt.event.ActionEvent evt){
				AcceptButtonActionPerformed(evt);
			}
		}
									  );

		javax.swing.GroupLayout jPanel2Layout =
				new javax.swing.GroupLayout(jPanel2);
		jPanel2.setLayout(jPanel2Layout);
		jPanel2Layout.setHorizontalGroup(
				jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
							 .addGroup(jPanel2Layout.createSequentialGroup()
													.addComponent(CancelButton, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
													.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
													.addComponent(AcceptButton, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
													.addContainerGap()
									  )
										);
		jPanel2Layout.setVerticalGroup(
				jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
							 .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
													.addComponent(CancelButton)
													.addComponent(AcceptButton, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
									  )
									  );

		javax.swing.GroupLayout layout =
				new javax.swing.GroupLayout(getContentPane());

		getContentPane()
				.setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
					  .addGroup(layout.createSequentialGroup()
									  .addContainerGap().addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
																		.addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))));
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
					  .addGroup(layout.createSequentialGroup().addContainerGap().addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		pack();

	}// </editor-fold>

	private void CancelButtonActionPerformed(java.awt.event.ActionEvent evt){
		theURL = "";
		this.setVisible(false);
		this.dispose();
	}

	private void AcceptButtonActionPerformed(java.awt.event.ActionEvent evt){
		theURL = URLField.getText();
		this.setVisible(false);
		this.dispose();
	}

	private void URLFieldActionPerformed(java.awt.event.ActionEvent evt){
	}

	public String getURL(){
		return theURL;
	}
}
