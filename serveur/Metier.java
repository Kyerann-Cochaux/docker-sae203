/**
 * Metier
 * 
 * Classe métier pour le serveur
 * 
 * Implémentation de connexion, resynchronise et gagner. Implémentation de l'envoie des commandes
 * LC de 00 à 22
 * J1 : x J2 : o
 * srv_morpi_checketatmorpion et srv_systm_attentejoueur_1 non utilsé
 *
 * @author Ethan Bapaume
 * @version 0.3 du 26/04/2026
 */

public class Metier
{
	// Attributs
	private char[][] plateau;

	private Serveur srv;

	// Constructeurs
	public Metier(Serveur srv)
	{
		this.srv = srv;

		this.plateau = new char[][] { { '0', '0', '0'},
		                              { '0', '0', '0'},
		                              { '0', '0', '0'} };
	}
	
	/*------------------------------------------------------*/
	/* Méthodes pour traiter les commandes                  */
	/*------------------------------------------------------*/

	public void connexion(int numClient)
	{
		if (numClient == 1)
			this.srv.envoyerCmd("srv_systm_attentejoueur_2");
		
		if (numClient == 2)
		{
			this.srv.envoyerCmd("srv_morpi_initmorpion");
			//this.srv.envoyerCmd("srv_morpi_attactionj1");
		}
	}

	public void resynchronise()
	{
		String cmd = "srv_morpi_updatemorpion_";

		for (int lig = 0; lig < this.plateau.length; lig++)
		{
			for (int col = 0; col < this.plateau[lig].length; col++)
			{
				cmd += plateau[lig][col];
			}
		}

		this.srv.envoyerCmd(cmd);
	}

	public boolean etatMorpion(String etat)
	{
		for (int cpt = 0; cpt < etat.length(); cpt++)
		{
			if (etat.charAt(cpt) != '0' && etat.charAt(cpt) != 'x' &&  etat.charAt(cpt) != 'o')
				return false;

			if (cpt < 3)
				this.plateau[0][cpt]     = etat.charAt(cpt);

			if (cpt >= 3 && cpt < 6)
				this.plateau[1][cpt - 3] = etat.charAt(cpt);

			if (cpt >= 6)
				this.plateau[2][cpt - 6] = etat.charAt(cpt);
		}

		return true;
	}

	public boolean dessiner(int numClient, int lig, int col)
	{
		if(lig < 0 || col < 0 || lig >= this.plateau.length || col >= this.plateau[lig].length)
		{
			return false;
		}

		if (numClient == 1)
		{
			this.plateau[lig][col] = 'x';

			if(this.gagner('x'))
				this.srv.envoyerCmd("srv_morpi_j1gagner");
			else
				this.resynchronise();

			return true;
		}

		if (numClient == 2)
		{
			this.plateau[lig][col] = 'o';

			if(this.gagner('o'))
				this.srv.envoyerCmd("srv_morpi_j2gagner");
			else
				this.resynchronise();

			return true;
		}

		return false;
	}

	//Méthode qui va reconnaître les commandes
	public boolean traiterCommande(String cmd)
	{
		int numClient;
		
		try
		{
			numClient = Integer.parseInt("" + cmd.charAt(3));
		}
		catch (Exception e)
		{
			return false;
		}
		
		String ordre = "";
		int cptUnderscore = 0;
		
		for (int cpt = 5; cpt < cmd.length() && cptUnderscore < 2; cpt++)
		{
			if (cmd.charAt(cpt) == '_')
				cptUnderscore++;
			
			if (cptUnderscore < 2)
				ordre += "" + cmd.charAt(cpt);
		}
		
		switch (ordre)
		{
			case "systm_connexion":
				{
					this.connexion(numClient);
					//System.out.println(numClient + " " + ordre); //Print de test
					break;
				}
		
			case "morpi_resychronise":
				{
					this.resynchronise();
					//System.out.println(numClient + " " + ordre); //Print de test
					break;
				}
				
			case "morpi_etatmorpion":
				{
					String etat = cmd.substring(23, cmd.length());
					this.etatMorpion(etat);
					//System.out.println(numClient + " " + ordre + " " + etat); //Print de test
					break;
				}
			
			case "morpi_dessiner":
				{
					int lig;
					int col;
					
					try
					{
						lig = Integer.parseInt("" + cmd.charAt(20));
						col = Integer.parseInt("" + cmd.charAt(21));
					}
					catch ( Exception e )
					{
						return false;
					}
					
					this.dessiner(numClient, lig, col);
					//System.out.println(numClient + " " + ordre + " " + lig + " " + col); //Print de test
					break;
				}
			
			default : return false;
		}
		
		return true;
	}
	
	//méthode qui détermine si un joueur a gagné
	public boolean gagner(char symbole)
	{
		boolean valide;
		
		//vérifie chaque ligne
		for (int lig = 0; lig < this.plateau.length; lig++)
		{
			valide = true;
			
			for (int col = 0; col < this.plateau[lig].length; col++)
			{
				if (this.plateau[lig][col] != symbole)
					valide = false;
			}
			
			if (valide)
				return true;
		}
		
		//vérifie chaque colonne
		for (int col = 0; col < this.plateau[0].length; col++)
		{
			valide = true;
			
			for (int lig = 0; lig < this.plateau.length; lig++)
			{
				if (this.plateau[lig][col] != symbole)
					valide = false;
			}
			
			if (valide)
				return true;
		}
		
		//vérifie la première diagonale
		valide = true;
		for (int lig = 0, col = 0; lig < this.plateau.length && col < this.plateau[lig].length; lig++, col++)
		{
			if (this.plateau[lig][col] != symbole)
					valide = false;
		}
		
		if (valide)
			return true;
		
		//vérifie la deuxième diagonale
		valide = true;
		for (int lig = 0, col = this.plateau[lig].length - 1; lig < this.plateau.length && col >= 0; lig++, col--)
		{
			if (this.plateau[lig][col] != symbole)
					valide = false;
		}
		
		if (valide)
			return true;
		
		return false;
	}
	
	/* //Main de test
	public static void main(String[] args)
	{
		Metier metier = new Metier(new Serveur());
		System.out.println(metier.traiterCommande("clt1_morpi_etatmorpion_o000o0o0o"));
		for (int lig = 0; lig < metier.plateau.length; lig++)
		{
			for (int col = 0; col < metier.plateau[lig].length; col++)
			{
				System.out.print(metier.plateau[lig][col]);
			}
			System.out.println();
		}
		System.out.println(metier.gagner('o'));
	}
	*/
}
