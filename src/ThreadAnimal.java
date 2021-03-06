import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.concurrent.Semaphore;

import javax.swing.JOptionPane;


public class ThreadAnimal extends Thread {

	private GlobalVars c;
	private Animal animal;
	private MondeVirtuel leMonde;
	private Semaphore semtest;




	ThreadAnimal(double pbaAvanta,
			double pbaAvantGauchea, 
			double pbaAvantDroita, 
			double pbaArriereGauchea, 
			double pbaArriereDroita, 
			int estomaca, 
			int orientationa, 
			MondeVirtuel unMonde, 
			String namea,
			GlobalVars ca){

		animal  = new Animal( pbaAvanta,
				pbaAvantGauchea,
				pbaAvantDroita,
				pbaArriereGauchea,
				pbaArriereDroita,
				estomaca,
				orientationa,
				unMonde,
				namea,
				unMonde.getConstante());
		leMonde = unMonde;
		this.c = ca;
		this.leMonde.updateVectThreadAnimal(this);

		// on paint l'animal au debut
		this.animal.setMaCouleur(this.leMonde.getLaFenetreBoutons().getCouleurCourante());
		this.leMonde.getFenetreDuMonde().paintAnimalInitial(animal, animal.getPosition());

	}

	ThreadAnimal(int estomaca, MondeVirtuel unMonde, String namea, GlobalVars ca){
		c = ca;
		animal = new Animal(estomaca, unMonde, namea, c);
		leMonde = unMonde;
		this.leMonde.updateVectThreadAnimal(this);

		//on paint l'animal au debut

		this.animal.setMaCouleur(this.leMonde.getLaFenetreBoutons().getCouleurCourante());
		this.leMonde.getFenetreDuMonde().paintAnimalInitial(animal, animal.getPosition());


	}

	ThreadAnimal(String fileName, MondeVirtuel unMonde, GlobalVars ca){
		leMonde = unMonde;
		c = ca;
		animal = loadAnimal(fileName);
		this.leMonde.updateVectThreadAnimal(this);

		//on paint l'animal au debut
		this.animal.setMaCouleur(this.leMonde.getLaFenetreBoutons().getCouleurCourante());
		this.leMonde.getFenetreDuMonde().paintAnimalInitial(animal, animal.getPosition());

	}

	//m�thode pour sauvegarder le profil d'un animal
	public void saveAnimal(String namefile){
		//on d�clare le flux sortant
		ObjectOutputStream output;
		try{
			//on l'initialise
			output = new ObjectOutputStream(
					//utilisation d'un tampon pour acc�l�rer les �critures en m�moire
					new BufferedOutputStream(
							new FileOutputStream(
									new File(namefile))));
			//on l'utilise pour stocker l'animal dans le fichier cr�� (ou indiqu�)
			output.writeObject(animal);
			//on oublie pas de fermer le flux
			output.close();
		} catch (IOException e){
			e.printStackTrace();
		}
	}

	//m�thode pour charger le profil d'un animal depuis la m�moire
	public Animal loadAnimal(String filename){
		//on d�lcare le flux entrant
		ObjectInputStream input;
		//on d�clare l'animal qui sera cr��
		Animal ani = null;
		input = null;
		try {

			//on initialise le flux
			input = new ObjectInputStream(
					//utilisation d'un tampon
					new BufferedInputStream(
							new FileInputStream(
									new File(filename))));


			//on cr�e l'instance de l'animal � partir du fichier
				ani = (Animal) input.readObject();
			
			//on ferme le flux
			input.close();
		}
		 catch (IOException e){
			System.out.println("on a lev� l'exception du file not found");
			JOptionPane.showMessageDialog(null, "Fichier introuvable !", "Attention ", JOptionPane.WARNING_MESSAGE);
			
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("on a lev� l'exception du file not found");
			System.out.println(e.getClass());
		} 

		return ani;
	}

	public Animal getAnimal(){
		return animal;
	}

	public void run(){

		semtest = new Semaphore(1);
		//tant qu'il a de la nourriture en stock (�nergie encore), il peut se d�placer
		while (animal.getEstomac() > 0) {

			//mutex de control � 0
			try {
				this.animal.getMutexControl().acquire();
			} catch (InterruptedException e2) {e2.printStackTrace();
			}

			animal.bouger(leMonde, c);
			animal.mange(leMonde, c); 
		//	System.out.println(animal.getName()+" : Je suis en " + animal.getPosition()[0]+ ", " + animal.getPosition()[1] );

			//mutex de control remis � 1, une fois mouvement termin�
			this.animal.getMutexControl().release();

			int duree = (int) (Math.random()*1000);
			//dort pendant un tant al�atoire

			try {
				sleep(50);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			/**/
			try {
				this.leMonde.getFenetreDuMonde().semtest.acquire();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			try {
				this.c.mutexUpdateScore.acquire();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.leMonde.updateScore(this);
			this.c.mutexUpdateScore.release();

		}
		// quand l'animal meurt (estomac n'est plus plein), on teste si il y a encore des animaux vivants
		// si c'est le dernier vivant qui meurt, la simulation se met en pause
		this.leMonde.getMaFenetreLecture().PauseIfAllDead();
	}




	public void setAnimal(Animal animal) {
		this.animal = animal;
	}




}