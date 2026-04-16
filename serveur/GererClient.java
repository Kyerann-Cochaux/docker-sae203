import java.net.* ;
import java.io.* ;
import java.util.ArrayList ;

/**
 * Gérer Client
 * 
 * Gère la Fonctionnalité de client multiple du serveur
 * 
 * @author Maël Couasnon
 * @version 0.1 du 16/04/2026
 */

public class GererClient implements Runnable
{
	// statique pour que tout les clients profite du comportement de GererClient
	public static ArrayList<GererClient> gestionClient ;
	
	private Serveur srv ;
	
	private Socket cltX ;
	private BufferedReader entrer ;
	private BufferedWriter sortie ;
	
	private int numeroClient ;
	
	public GererClient ( Socket cltX, Serveur srv )
	{
		try
		{
			this.srv    = srv ;
			this.cltX   = cltX ;
			this.sortie = new BufferedWriter ( new OutputStreamWriter (cltX.getOutputStream()) );
			this.entrer = new BufferedReader ( new InputStreamReader  (cltX.getInputStream() ) );
			
			gestionClient.add(this);
			
			this.numeroClient = gestionClient.size();
		}
		catch ( IOException e )
		{
			this.fermetureGestionClient(cltX, entrer, sortie);
		} 
	}
	
	// Méthode de Thread
	@Override
	public void run()
	{
		String cmd ;
		
		while ( this.cltX.isConnected() )
		{
			try
			{
				cmd = entrer.readLine();         // On attend qu'on client envoie une commande
				this.srv.traiterCommande( cmd ); // Puis on l'envoie au Serveur pour être traité
			}
			catch ( IOException e )
			{
				this.fermetureGestionClient(cltX, entrer, sortie);
				break;
			}
		}
	}
	
	/**
	 * Envoyer Commande du Serveur
	 * 
	 * Une fois la commande traité,
	 * le serveur enverras une commande approprier avec cette méthode
	 * 
	 * @param cmd - la commande à envoyer
	 */
	public void envoyerCmdDuServeur( String cmd )
	{
		for ( GererClient clients : GererClient.gestionClient )
		{
			clients.sortie.write( cmd );
			clients.sortie.flush();
		}
		catch ( IOException e )
		{
			this.fermetureGestionClient(cltX, entrer, sortie);
		}
		
	}
	
	public void departClient()
	{
		gestionClient.remove(this) ;
	}
	
	// Méthode appeller si Erreur d'IO
	public void fermetureGestionClient( Socket cltX, BufferedReader entrer, BufferedWriter sortie )
	{
		departClient();
		
		try
		{
			if ( entrer != null ) { entrer.close(); }
			if ( sortie != null ) { sortie.close(); }
			if ( cltX   != null ) { cltX.close();   }
		}
		catch ( IOException e )
		{
			e.printStackTrace();
		}
	}
}