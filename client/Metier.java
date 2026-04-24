public class Metier
{
	private int NB_LIG;
	private int NB_COL;
	
	private char[][] morpion;
	
	private Controlleur ctrl;
	
	public Metier(Controlleur ctrl)
	{
		this.ctrl = ctrl;
		
		this.NB_LIG = 3;
		this.NB_COL = 3;
		
		this.morpion = new char[this.NB_LIG][];
		
		for (int numLig = 0; numLig < this.NB_LIG; numLig++)
			this.morpion[numLig] = new char[this.NB_COL];
		
		for (int numLig = 0; numLig < this.NB_LIG; numLig++)
			for (int numCol = 0; numCol < this.NB_COL; numCol++)
				this.morpion[numLig][numCol] = ' ';
		
		this.updateMorpion();
	}
	
	public int      getNbLig  () { return this.NB_LIG;  }
	public int      getNbCol  () { return this.NB_COL;  }
	
	
	private void updateMorpion() { this.morpion = this.getMorpion(); }
	private char[][] getMorpion()
	{
		String sMorpion = ctrl.getMorpion();
		
		char[][] retMorpion = new char[this.NB_LIG][];
		int numCara = 0;
		
		for (int numLig = 0; numLig < this.NB_LIG; numLig++)
			retMorpion[numLig] = new char[this.NB_COL];
		
		for (int numLig = 0; numLig < this.NB_LIG; numLig++)
			for (int numCol = 0; numCol < this.NB_COL; numCol++)
				retMorpion[numLig][numCol] = sMorpion.charAt(numCara++);
		
		return retMorpion;
	}
	
	public char getValeur(int lig, int col)
	{
		this.updateMorpion();
		
		return this.morpion[lig][col];
	}
	
	// majIHM() : void
	
}
