public class Metier
{
	private char[][] plateau;

	private Serveur srv;

	public metier(Serveur srv)
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

	public void etatMorpion(int numClient, String etat)
	{

	}

	public void dessiner(int numClient, int lig, int col)
	{

	}

	//Méthode qui va reconnaître les commandes
	public boolean traiterCommande(String cmd)
	{
		int numClient = Integer.parseInt("" + cmd.charAt(3));

		String ordre = "";

		for(int cpt = 5; cpt < cmd.length(); cpt++)
			ordre += "" + cmd.charAt(cpt);

		switch (ordre)
		{
			case ".":
				{
					
					break;
				}
		
			case "..":
				{
					
					break;
				}
				
			case "morpi_etatmorpion":
				{
					String etat = cmd.substring(23, cmd.length());
					break;
				}
		}
		return true;
	}
}