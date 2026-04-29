public class Controleur
{
	private FrameGrille ihm;
	private Metier      metier;
	private Client      client;
	
	private String testStringMorpionCetteVariableVaEtreSupprime;
	
	public Controleur(Client clt)
	{
		this.testStringMorpionCetteVariableVaEtreSupprime = "000000000";
		
		this.client = clt;
		
		this.metier = new Metier(this);
		this.ihm    = new FrameGrille ( this );
	}
	
	public int       getNbLigne  ()                 { return this.metier.getNbLig   ();        }
	public int       getNbColonne()                 { return this.metier.getNbCol   ();        }
	public char[][]  getMorpion  ()                 { return this.metier.getMorpion ();        } //renvoie un tab 2 dimension de char
	public char      getValeur   (int lig, int col) { return this.metier.getValeur (lig, col); }
	
	public String getRawMorpion()
	{
		return this.testStringMorpionCetteVariableVaEtreSupprime; // appeler la méthode pour du client
	}
	
	public void majIHM ()
	{
		this.ihm.majIHM();
	}
	
	public void traiterCmd(String cmd)
	{
		System.out.println(cmd);
		
		if ( this.client.getNumJoueur() == -1 )
		{
			switch (cmd)
			{
				case "srv_systm_attentejoueur_1":
					this.client.setNumJoueur(1);
					this.client.envoyerCmd("clt1_systm_connexion" );
					break;
				
				
				case "srv_systm_attentejoueur_2":
					this.client.setNumJoueur(1);
					this.client.envoyerCmd("clt2_systm_connexion" );
					break;
				
				
			}
		}
		
		// TODO "srv_morpi_updatemorpion_XXXXXXXXX"
		
		if ( this.client.getNumJoueur() == -1 ) return;
		
		
		switch (cmd)
		{
			
			case "srv_morpi_initmorpion":
				this.testStringMorpionCetteVariableVaEtreSupprime = "000000000";
				this.client.envoyerCmd("clt" + this.client.getNumJoueur() + "_morpi_initialise");
				break;
			
			
			case "srv_morpi_checketatmorpion":
				this.client.envoyerCmd("clt" + this.client.getNumJoueur() + "_morpi_etatmorpion_" + this.metier.toString() );
				break;
			
			
			case "srv_morpi_attactionj1":
				
				break;
			
			
			case "srv_morpi_attactionj2":
				
				break;
			
			
			case "srv_morpi_j1gagner":
				// je ne sait pas
				if ( this.client.getNumJoueur() == 1 ) System.out.println("T'as gagné :)" ); //on gagne
				else                                   System.out.println("T'as perdu :(" ); //on perd
				break;
			
			
			case "srv_morpi_j2gagner":
				// je ne sait pas
				if ( this.client.getNumJoueur() == 2 ) System.out.println("T'as gagné :)" ); //on gagne
				else                                   System.out.println("T'as perdu :(" ); //on perd
				break;
			
			default:
				break;
		}
	}
	
	public void dessiner(int lig, int col)
	{
		// méthode dans Client.java
		this.client.envoyerCmd("clt" + client.getNumJoueur() + "_morpi_dessiner_" + lig + "" + col);
		
		this.metier.updateMorpion();
		
		this.majIHM();
	}
}
