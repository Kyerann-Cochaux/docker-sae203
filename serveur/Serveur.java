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
	
	private ServerSocket srv ;
	
	private GererClient gestionClt ;
	
	// Constructeurs
	public Serveur( int port )
	{
		this.metier = new Metier(this) ;
		this.demarerServeur( port );
	}
	
	// Autres Méthodes
	private void demarerServeur( int port )
	{
		try
		{
			this.srv = new ServerSocket( port ) ;
			
			while ( true )
			{
				Socket cltX = this.srv.accept();
				
				this.gestionClt = new GererClient( cltX, this );
				
				Thread thread = new Thread( this.gestionClt );
				thread.start();
			}
		}
		catch ( IOException e )
		{
			e.printStackTrace();
		}
	}
	
	private void fermerServeur()
	{
		try
		{
			if ( this.srv != null ) { srv.close(); }
		}
		catch ( IOException e )
		{
			e.printStackTrace();
		}
	}
	
	public void envoyerCmd( String cmd )
	{
		this.gestionClt.envoyerCmdDuServeur( cmd );
	}
	
	public void recevoirCmd( String cmd )
	{
		this.metier.traiterCommande( cmd );
	}
	
	// Méthode Principale
	public static void main(String[] args)
	{
		if ( args.length == 1 )
		{
			Serveur serveurMorpion = new Serveur(Integer.parseInt(args[0]));
		}
		else
		{
			System.out.println("Utilisation : Serveur [port]")
			System.exit(1);
		}
	}
}
