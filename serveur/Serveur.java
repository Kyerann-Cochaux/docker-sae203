

/**
 * Serveur
 * 
 * serveur TCP du jeu de Morpion,
 * 2 clients peuvent se connecter au Serveur,
 * pour échanger des commandes 
 * pour le déroulement d'une partie de morpion
 *
 * @author Maël Couasnon
 * @version 0.1 du 15/04/2026
 */

public class Serveur
{
	// Attributs
	private Metier metier ;

	private Socket clt1 ;
	private Socket clt2 ;
	
	// Constructeurs
	public Serveur ( Metier metier )
	{
		this.metier = metier ;
	}

	// Modifieurs
	public void setClt1 ( Socket clt1 ) { this.clt1 = clt1 ; }
	
	public void setClt2 ( Socket clt2 ) { this.clt2 = clt2 ; }
	
	// Méthode Principale
	public static void main(String[] args)
	{
		
	}
}