/**
 * Metier
 * 
 * Classe métier pour le serveur
 * 
 * Implémentation de traiterCommande, etatMorpion et dessiner
 * LC de 00 à 22
 * J1 : x J2 : o
 *
 * @author Ethan Bapaume
 * @version 0.2 du 17/04/2026
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
		
	}

	public void resynchronise(int numClient)
	{

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
			return true;
		}

		if (numClient == 2)
		{
			this.plateau[lig][col] = 'o';
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
					this.resynchronise(numClient);
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

	/* //Main de test
	public static void main(String[] args)
	{
		Metier metier = new Metier(new Serveur());
		System.out.println(metier.traiterCommande("clt1_morpi_etatmorpion_o00o00o00"));
		for (int lig = 0; lig < metier.plateau.length; lig++)
		{
			for (int col = 0; col < metier.plateau[lig].length; col++)
			{
				System.out.print(metier.plateau[lig][col]);
			}
			System.out.println();
		}
	}
	*/
}