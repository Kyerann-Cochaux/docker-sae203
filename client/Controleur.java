// in faut ajouter le client

public class Controleur
{
	private FrameGrille ihm;
	private Metier      metier;
	private Client      client;
	
	private String testStringMorpionCetteVariableVaEtreSupprime;
	
	public Controleur()
	{
		this.testStringMorpionCetteVariableVaEtreSupprime = "000000000";
		
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
	
	public static void main(String[] a)
	{
		new Controleur();
	}

	public void dessiner(int lig, int col)
	{
		this.client.dessiner(lig, col);
		
		// Pour test : 
		// int let = col + lig * this.metier.getNbCol();
		// this.testStringMorpionCetteVariableVaEtreSupprime = this.testStringMorpionCetteVariableVaEtreSupprime.substring(0,let) + 'x'+ this.testStringMorpionCetteVariableVaEtreSupprime.substring(let+1);
		
		this.metier.updateMorpion();
		
		this.majIHM();
	}
}
