import java.awt.Color;
import java.io.Serializable ;
import java.util.concurrent.Semaphore;


public class Animal implements Serializable  {


	//l'int orientation d�signe l'orientation de l'animal dans l'espace
	//0 d�signe le nord, 1 le nord-est, 2 l'est... jusqu'� 7 le nord-ouest
	private String name;
	private int orientation;

	private int[] position;

	//5 caract�res � d�finir
	private Color maCouleur;
	private double pbaAvant;
	private double pbaAvantGauche;
	private double pbaAvantDroit;
	private double pbaArriereGauche;
	private double pbaArriereDroit;

	//estomac: compte la nourriture, si 0, l'animal s'arr�te de bouger
	private int estomac; 
	private int score;
	
	//mutex qui servira � controler le lancement ou la mise en pause du thread
		private Semaphore MutexControl;



	public Animal(double pbaAvanta,
			double pbaAvantGauchea, 
			double pbaAvantDroita, 
			double pbaArriereGauchea, 
			double pbaArriereDroita, 
			int estomaca, 
			int orientationa, 
			MondeVirtuel leMonde, 
			String namea,
			GlobalVars c) {
		super();

		this.name = namea;
		//l'animal apparait sur une case al�atoire
		this.position = new int[c.NB_COORDONNEES];
		position[c.X] = (int)(Math.random()*leMonde.getLargeur()) ;
		position[c.Y] =(int)(Math.random()*leMonde.getLongueur());
		leMonde.animalCree(position, this);

		/*
		this.deplacementAbsolu = new int[c.NB_DIRECTIONS][c.NB_COORDONNEES];
		this.deplacementAbsolu[c.NORD][c.X]      = -1 ;
		this.deplacementAbsolu[c.NORDEST][c.X]   = -1 ;
		this.deplacementAbsolu[c.EST][c.X]       = 0  ;
		this.deplacementAbsolu[c.SUDEST][c.X]    = 1  ;
		this.deplacementAbsolu[c.SUD][c.X]       = 1  ;
		this.deplacementAbsolu[c.SUDOUEST][c.X]  = 1  ;
		this.deplacementAbsolu[c.OUEST][c.X]     = 0  ;
		this.deplacementAbsolu[c.NORDOUEST][c.X] = -1 ;
		this.deplacementAbsolu[c.NORD][c.Y]      = 0  ;
		this.deplacementAbsolu[c.NORDEST][c.Y]   = 1  ; 
		this.deplacementAbsolu[c.EST][c.Y]       = 1  ;
		this.deplacementAbsolu[c.SUDEST][c.Y]    = 1  ; 
		this.deplacementAbsolu[c.SUD][c.Y]       = 0  ;
		this.deplacementAbsolu[c.SUDOUEST][c.Y]  = -1 ;
		this.deplacementAbsolu[c.OUEST][c.Y]     = -1 ;
		this.deplacementAbsolu[c.NORDOUEST][c.Y] = -1 ; */

		this.orientation = orientationa;
		this.pbaAvant = pbaAvanta;
		this.pbaAvantGauche = pbaAvantGauchea;
		this.pbaAvantDroit = pbaAvantDroita;
		this.pbaArriereGauche = pbaArriereGauchea;
		this.pbaArriereDroit = pbaArriereDroita;
		this.estomac = estomaca;
		this.score = 0;
		
		//lorsque l'on cr�e l'animal, on met le mutex � 1
		this.MutexControl = new Semaphore(0);
	}

	public Animal(int estomaca, MondeVirtuel leMonde, String namea, GlobalVars c) {
		super();

		this.name = namea;
		//l'animal apparait sur une case al�atoire
		this.position = new int[2];
		position[c.X] = (int)(Math.random()*leMonde.getLargeur()) ;
		position[c.Y] =(int)(Math.random()*leMonde.getLongueur());
		leMonde.animalCree(position,this);


		this.orientation = (int) (8*Math.random());


		//module permettant de cr�er des probabilit�s al�atoires dans les 5 directions
		this.pbaAvant = Math.random();
		this.pbaAvantGauche = Math.random();
		this.pbaAvantDroit = Math.random();
		this.pbaArriereGauche = Math.random();
		this.pbaArriereDroit = Math.random();
		double total = pbaAvant + pbaAvantGauche + pbaAvantDroit + pbaArriereDroit + pbaArriereGauche; 
		this.pbaAvant /= total; 
		this.pbaAvantGauche /= total;
		this.pbaAvantDroit /= total;
		this.pbaArriereGauche /= total;
		this.pbaArriereDroit /= total;
		this.estomac = estomaca;
		this.score = 0;
		
		//lorsque l'on cr�e l'animal, on met le mutex � 1
		this.MutexControl = new Semaphore(0);
	}

	public void bouger(MondeVirtuel leMonde, GlobalVars c){
		double choix = Math.random();
		int deplacement;
		estomac--;
		score++;
		// d�finit par rapport aux probabilit�s le d�placement relatif 
		//avant: 0
		//avant gauche: 1	avant droit:3
		//arri�re gauche: 5  arri�re droit: 7
		if (choix <= pbaAvant) {
			deplacement = c.AVANT;
		} else if (choix <=(pbaAvant + pbaAvantDroit)){
			deplacement = c.AVANTDROIT;
		} else if (choix <= (pbaAvant + pbaAvantDroit + pbaArriereDroit)) {
			deplacement = c.ARRIEREDROIT;
		} else if (choix <= (pbaAvant + pbaAvantDroit + pbaArriereDroit + pbaArriereGauche)) {
			deplacement = c.ARRIEREGAUCHE;
		} else {
			deplacement = c.AVANTGAUCHE;			
		}

		//mise � jour du d�placement: il d�signe maintenant le d�placement absolu
		deplacement  = (deplacement + orientation) % c.NB_DIRECTIONS;
		//Mise  � jour de l'orientation
		orientation = deplacement; 

		//on stocke l'ancienne position
		int[] anciennePosition = new int[2];
		anciennePosition[0]=position[0];
		anciennePosition[1]=position[1];

		//mise � jour des coordonn�es x et y

		//on g�re le cas du modulo n�gatif, en rajoutant la largeur si c'est n�gatif

		position[c.X] =( position[c.X] + c.deplacementAbsolu[deplacement][c.X]+ leMonde.getLargeur())%leMonde.getLargeur();

		position[c.Y] =( position[c.Y] + c.deplacementAbsolu[deplacement][c.Y]+leMonde.getLongueur())%leMonde.getLongueur();


		//attention, il faut encore signifier au monde que l'on a boug�: mise � jour des cases du monde
		leMonde.mouvementAnimal(this,anciennePosition, position);
	}

	public void mange(MondeVirtuel leMonde, GlobalVars c){
		//si il y a de la nourriture disponible, il mange
		if (leMonde.contenu(this.position[c.X],this.position[c.Y])[c.QTE_NOURRITURE]!=0){
			leMonde.nourritureMangee(this.position);
			estomac++;
			score+=10;
		}
		//il faut mettre � jour l'estomac et le score
		
	}

	public int getEstomac(){
		return estomac;
	}

	public int getScore(){
		return score;
	}

	public String getName(){
		return name;
	}

	public int[] getPosition(){
		return position;
	}

	public int getOrientation() {
		return orientation;
	}

	public double getPbaAvant() {
		return pbaAvant;
	}

	public double getPbaAvantGauche() {
		return pbaAvantGauche;
	}

	public double getPbaAvantDroit() {
		return pbaAvantDroit;
	}

	public double getPbaArriereGauche() {
		return pbaArriereGauche;
	}

	public double getPbaArriereDroit() {
		return pbaArriereDroit;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setOrientation(int orientation) {
		this.orientation = orientation;
	}

	public void setPbaAvant(double pbaAvant) {
		this.pbaAvant = pbaAvant;
	}

	public void setPbaAvantGauche(double pbaAvantGauche) {
		this.pbaAvantGauche = pbaAvantGauche;
	}

	public void setPbaAvantDroit(double pbaAvantDroit) {
		this.pbaAvantDroit = pbaAvantDroit;
	}

	public void setPbaArriereGauche(double pbaArriereGauche) {
		this.pbaArriereGauche = pbaArriereGauche;
	}

	public void setPbaArriereDroit(double pbaArriereDroit) {
		this.pbaArriereDroit = pbaArriereDroit;
	}

	public void setEstomac(int estomac) {
		this.estomac = estomac;
	}

	public Color getMaCouleur() {
		return maCouleur;
	}

	public void setMaCouleur(Color maCouleur) {
		this.maCouleur = maCouleur;
	}

	public Semaphore getMutexControl() {
		return MutexControl;
	}


}

