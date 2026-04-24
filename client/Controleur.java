public class Controleur
{
	private FrameGrille ihm;
	private Metier      metier;

	public Controleur()
	{
		this.metier = new Metier(this);
		this.ihm    = new FrameGrille ( this );
	}


	public int       getNbLigne  ()                 { return this.metier.getNbLig   ();        }
	public int       getNbColonne()                 { return this.metier.getNbCol   ();        }
	public char[][]  getMorpion  ()                 { return this.metier.getMorpion ();        } //renvoie un tab 2 dimension de char
	public char      getValeur   (int lig, int col) { return this.metier.getValeur (lig, col); }
	
	public String getRawMorpion()
	{
		return "xxxooo0x0";
	}

	public void majIHM ()
	{
		this.ihm.majIHM();
	}

	public static void main(String[] a)
	{
		new Controleur();
	}
}
