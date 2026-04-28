import java.net.* ;
import java.io.* ;

/**
 * Client
 * 
 * client TCP d'un jeu de Morpion.
 * Se connecte à un serveur
 * puis envoie et reçois des données pour jouer une partie de Morpion
 *
 * @author Maël Couasnon
 * @version 0.1 du 15/04/2026
 */

public class Client
{
	// Attributs
	private Controleur ctrl ;
	private int        numJoueur ;
	
	// Constructeurs
	public Client()
	{
		this.ctrl      = new Controleur(this) ;
		this.numJoueur = -1 ;
	}
	
	// Accesseur
	public int getNumJoueur() { return this.numJoueur ; }
	
	// Modifieur
	public void setNumJoueur( int nvNum ) { this.numJoueur = nvNum ; }
	
	// Autres Méthodes
	public String envoyerCmd( String cmd )
	{
		
		
		return null ;
	}
	
	public void recevoirCmd( String cmd )
	{
		this.ctrl.traiterCmd(cmd);
	}
	
	// Méthode Principale
	public static void main(String[] arg)
	{
		try
		{
			// Vérifications du Nom et Port de l'Hôte attendu en Arguments
			String nomHote  = "" ;
			String portHote = "" ;
			
			if ( arg.length == 2 )
			{
				nomHote  = arg[0];
				portHote = arg[1];
			}
			else
			{
				System.out.println("Utilisation : ClientSimple2 [NomHôte] [PortHôte]");
				System.exit(1);
			}
			// Connexion
			Socket cli = new Socket( nomHote, Integer.parseInt(portHote) );
			// Création de l'obj Client
			Client clt = new Client();
			
			// Ouverture du de l'input
			BufferedReader in = new BufferedReader( new InputStreamReader(cli.getInputStream()) );
			
			// Ouverture de l'Envoie de Texte
			PrintWriter out = new PrintWriter( cli.getOutputStream(), true );
			
			
			
		}
		catch ( IOException e )
		{
			e.printStackTrace();
		}
	}
}
