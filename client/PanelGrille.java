import java.awt.GridLayout;
import java.awt.Dimension;

import javax.swing.*;

import java.util.Arrays;

import java.awt.event.*;

public class PanelGrille extends JPanel 
{
	Controleur ctrl;

	JLabel[][] tabLblCase;


	public PanelGrille(Controleur ctrl)
	{


		this.ctrl = ctrl;
		this.setLayout ( new GridLayout ( this.ctrl.getNbLigne(), this.ctrl.getNbColonne(), 1, 1 ) );


		/*------------------------------*/
		/* Création des composants      */
		/*------------------------------*/

		// Création des Labels
		this.tabLblCase  = new JLabel [ this.ctrl.getNbLigne() ] [ this.ctrl.getNbColonne() ];


		for (int lig=0;lig<tabLblCase.length; lig++ )
			for (int col=0;col<tabLblCase[lig].length; col++ )
			{
				if (ctrl.getValeur(lig, col) == 'x')
					this.tabLblCase[lig][col] = new JLabel(new ImageIcon("./images/croix.png"));

				if (ctrl.getValeur(lig, col) == 'o')
					this.tabLblCase[lig][col] = new JLabel(new ImageIcon("./images/rond.jpg"));

				if (ctrl.getValeur(lig, col) == '0')
					this.tabLblCase[lig][col] = new JLabel(new ImageIcon("./images/vide.jpg"));

				this.tabLblCase[lig][col].setOpaque(true);
			}


		/*------------------------------*/
		/* Postionnement des composants */
		/*------------------------------*/
			
		for (int lig=0; lig < this.ctrl.getNbLigne(); lig++ )
		{
			for (int col=0; col < this.ctrl.getNbColonne(); col++ )
			{
				this.add(tabLblCase[lig][col]);
			}
		}

	}
		

	public void majIHM()
	{
		for ( int lig=0; lig< this.tabLblCase.length; lig++)
			for ( int col=0; col< this.tabLblCase[lig].length; col++)
			{
				if (ctrl.getValeur(lig, col) == 'x')
					this.tabLblCase[lig][col] = new JLabel(new ImageIcon("./images/croix.png"));

				if (ctrl.getValeur(lig, col) == 'o')
					this.tabLblCase[lig][col] = new JLabel(new ImageIcon("./images/rond.jpg"));

				if (ctrl.getValeur(lig, col) == '0')
					this.tabLblCase[lig][col] = new JLabel(new ImageIcon("./images/vide.jpg"));
				
				this.tabLblCase[lig][col].setOpaque(true);
			}

	}

}
