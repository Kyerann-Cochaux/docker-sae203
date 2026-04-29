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
	private Controleur   ctrl ;
	private int          numJoueur ;
	private String       cmdActuelle ;
	
	// Constructeurs
	public Client( String[] arg )
	{
		this.ctrl      = new Controleur(this) ;
		this.numJoueur = -1 ;
		this.cmdActuelle = "";
		
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
			
			// Ouverture du de l'input
			BufferedReader in = new BufferedReader( new InputStreamReader(cli.getInputStream()) );
			
			// Ouverture de l'Envoie de Texte
			PrintWriter out = new PrintWriter( cli.getOutputStream(), true );
			
			// 1ère Lecture
			String cmd = in.readLine();
			
			System.out.println("Commande reçu !");
			
			// Boucle d'E/R de Texte
			while ( cmd != "" )
			{
				this.recevoirCmd( cmd );
				out.println( this.cmdActuelle );
				
				cmd = in.readLine();
			}
			
			in.close();
			cli.close();
		}
		catch ( IOException e )
		{
			e.printStackTrace();
		}
	}
	
	// Accesseur
	public int getNumJoueur() { return this.numJoueur ; }
	
	// Modifieur
	public void setNumJoueur( int nvNum ) { this.numJoueur = nvNum ; }
	
	public void envoyerCmd( String cmd ) { this.cmdActuelle = cmd ; }
	
	// Autres Méthodes
	public void recevoirCmd( String cmd )
	{
		this.ctrl.traiterCmd(cmd);
	}
	
	// Méthode Principale
	public static void main(String[] arg)
	{
		Client clientMorpion = new Client(arg);
	}
}
