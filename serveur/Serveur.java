import java.net.* ;
import java.io.* ;

/**
 * Serveur
 * 
 * serveur TCP du jeu de Morpion,
 * 2 clients peuvent se connecter au Serveur,
 * pour échanger des commandes 
 * pour le déroulement d'une partie de morpion
 * 
 * Commencement d'Implémentation de Thread
 *
 * @author Maël Couasnon
 * @version 0.1 du 15/04/2026
 */

public class Serveur
{
	// Attributs
	private Metier metier ;
	
	private ServeurSocket srv ;
	
	private PrintWriter    sortie ;
	private BufferedReader entrer ;
	
	// Constructeurs
	public Serveur ( Metier metier )
	{
		this.metier = metier ;
	}
	
	// Autres Méthodes
	private void demarerServeur( int port )
	{
		try
		{
			while ( ! srv.isClosed() )
			{
				this.srv    = new ServerSocket( port ) ;
				Socket cltX = serverSocket.accept();
				
				GererClient gestionClt = new GererClient( cltX );
				
				Thread thread = new Thread( gestionClt );
				thread.start();
			}
		}
		catch ( IOException e )
		{
			
		}
	}
	
	private void fermerServeur()
	{
		
	}
	
	private void envoyerCmd( String cmd )
	{
		
	}
	
	private void recevoirCmd( String cmd )
	{
		this.metier.traiterCommande( cmd ) ;
	}

	// Modifieurs
	public void setClt1 ( Socket clt1 ) { this.clt1 = clt1 ; }
	
	public void setClt2 ( Socket clt2 ) { this.clt2 = clt2 ; }
	
	// Méthode Principale
	public static void main(String[] args)
	{
		
	}
}