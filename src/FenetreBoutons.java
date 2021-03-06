import java.awt.BorderLayout;
import java.awt.Color; 
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.util.Random;
import java.awt.event.KeyListener;

public class FenetreBoutons extends JFrame implements ActionListener {

	//on lie la classe GlobalVars qui sert � r�gler les diff�rents param�tres
	private GlobalVars c;

	private Color CouleurCourante;
	private PanneauBouton pan; 
	MondeVirtuel MonMondeVirtuel;
	private double pbaAvant;
	private double pbaAvantGauche;
	private double pbaAvantDroit;
	private double pbaArriereGauche;
	private double pbaArriereDroit;
	private int estomac;
	private String name;
	private int orientation;
	private int quantite;

	private int estomacbis;
	private String namebis;
	//private int orientationbis;
	private int quantitebis;

	private String fileName;

	JButton button1;
	JButton button2;
	JButton button3;
	JButton button4;
	JButton button5;

	JLabel label1;
	JLabel label2;
	JLabel label3;
	JLabel label4;
	JLabel label5;
	JLabel label6;
	JLabel label7;
	JLabel label8;
	JLabel label9;
	JLabel labelok;

	JLabel label6bis;
	JLabel label7bis;
	JLabel label8bis;
	JLabel label9bis;
	JLabel labelokbis;

	JLabel label10;
	JLabel labelcharger;

	JLabel label11;
	JLabel labelajouter;

	JTextField TpbaAvant; 	
	JTextField TpbaAvantGauche;
	JTextField TpbaAvantDroit;
	JTextField TpbaArriereGauche;
	JTextField TpbaArriereDroit;
	JTextField estomacInitial;
	JTextField nomAnimal;
	JTextField Torientation;
	JTextField Tquantit�;



	JTextField estomacInitialbis;
	JTextField nomAnimalbis;
	JTextField Tquantit�bis;

	JTextField TfileName;

	JTextField TqteNourriture;

	public FenetreBoutons(MondeVirtuel unMonde, GlobalVars ca){
		this.c = ca;
		this.MonMondeVirtuel = unMonde;
		this.pan = new PanneauBouton(this, unMonde);
		this.setTitle("Interface boutons");
		this.setSize(500,520);
		this.setLocation(10,150);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		/*On pr�vient notre JFrame que notre JPanel
    sera son content pane  c'est dans celui-ci que nous placerons nos composant */
		this.setContentPane(pan);
		this.setVisible(true);

		//Container contentPane = this.getContentPane();
		//contentPane.setLayout(new GridLayout(8,1)); //disposition grille

		//definition nb lignes et colonnes et espace entre lignes et colonnes
		//nb lignes, colonnes, pixels entre chaque ligne, pixel entre chaque colonne
		GridLayout gl = new GridLayout(	18, 2, 5, 5);
		this.setLayout(gl);

		//pour le premier constructeur personnalis�
		label1= new JLabel("Pba avant %");
		label2= new JLabel("Pba avant gauche %");
		label3= new JLabel("Pba avant droit %");
		label4= new JLabel("Pba arri�re gauche %");
		label5= new JLabel("Pba arri�re droite %");
		label6= new JLabel("Quantit� de nourriture initiale dans l'estomac");
		label7= new JLabel("Nom de l'animal");
		label8= new JLabel("Orientation de l'animal initiale");
		label9= new JLabel("Quantit� � cr�er");
		labelok= new JLabel("CLIQUER POUR CREER");

		TpbaAvant= new JTextField("20");
		TpbaAvantGauche= new JTextField("20");
		TpbaAvantDroit= new JTextField("20");
		TpbaArriereGauche= new JTextField("20");
		TpbaArriereDroit= new JTextField("20");
		estomacInitial = new JTextField("100");
		nomAnimal =new JTextField("Titi");
		Torientation =new JTextField("1");
		Tquantit� =new JTextField("10");
		button1 = new JButton ("OK");



		//pour le 2e constructeur al�atoire
		label6bis= new JLabel("Quantit� de nourriture initiale dans l'estomac");
		label7bis= new JLabel("Nom de l'animal");
		label8bis= new JLabel("Orientation de l'animal initiale");
		label9bis= new JLabel("Quantit� � cr�er");
		labelokbis= new JLabel("CLIQUER POUR CREER");

		estomacInitialbis = new JTextField("100");
		nomAnimalbis =new JTextField("TOTO");
		//Torientationbis =new JTextField("1");
		Tquantit�bis =new JTextField("10");

		button2 = new JButton ("OK");

		//3e constructeur load
		label10= new JLabel("Nom du fichier � charger");
		labelcharger= new JLabel("CLIQUER POUR CHARGER");
		TfileName =new JTextField("fichier");

		button3 = new JButton ("charger un profil");


		//ajout nourriture
		label11= new JLabel("Quantit� de nourriture � cr�er");
		labelajouter= new JLabel("CLIQUER POUR AJOUTER LA NOURRITURE");
		TqteNourriture =new JTextField("10000");
		button4 = new JButton ("ajouter");

		button1.addActionListener(this);
		button2.addActionListener(this);
		button3.addActionListener(this);
		button4.addActionListener(this);


		//on ajoute les diff�rents �l�ments au panneau, dans l'ordre
		//1er constructeur
		//on attribue la couleur bleue
		label1.setForeground(new Color(0,102,204));
		label2.setForeground(new Color(0,102,204));
		label3.setForeground(new Color(0,102,204));
		label4.setForeground(new Color(0,102,204));
		label5.setForeground(new Color(0,102,204));
		label6.setForeground(new Color(0,102,204));
		label7.setForeground(new Color(0,102,204));
		label8.setForeground(new Color(0,102,204));
		label9.setForeground(new Color(0,102,204));
		//label1.setText("<html><body><font color='blue'>Exemple JLabel texte color�:</body></html>" );
		pan.add(label1);pan.add(TpbaAvant);
		pan.add(label2);pan.add(TpbaAvantGauche);
		pan.add(label3);pan.add(TpbaAvantDroit);
		pan.add(label4);pan.add(TpbaArriereGauche);
		pan.add(label5);pan.add(TpbaArriereDroit);
		pan.add(label6);pan.add(estomacInitial);
		pan.add(label7);pan.add(nomAnimal);
		pan.add(label8);pan.add(Torientation);
		pan.add(label9);pan.add(Tquantit�);

		pan.add(labelok);pan.add(button1);



		//2e constructeur
		label6bis.setForeground(new Color(204,0,102));
		label7bis.setForeground(new Color(204,0,102));
		label8bis.setForeground(new Color(204,0,102));
		label9bis.setForeground(new Color(204,0,102));
		pan.add(label6bis);pan.add(estomacInitialbis);
		pan.add(label7bis);pan.add(nomAnimalbis);

		pan.add(label9bis);pan.add(Tquantit�bis);

		pan.add(labelokbis);pan.add(button2);

		//3e constructeur
		label10.setForeground(new Color(204,102,0));
		pan.add(label10);pan.add(TfileName);
		pan.add(labelcharger);pan.add(button3);

		//ajout nourriture
		label11.setForeground(new Color(102,0,204));
		pan.add(label11);pan.add(TqteNourriture);
		pan.add(labelajouter);pan.add(button4);

		pan.revalidate();
	}

	public MondeVirtuel getMonMondeVirtuel() {
		return MonMondeVirtuel;
	}


	public void actionPerformed(ActionEvent e){  
		// on met en place les boutons



		//bouton1 : "ok" on cr�� l'animal personnalis�
		if (e.getSource().equals(button1)){ 

			//on v�rifie que les champs sont remplis avec des d�cimaux
			try {

				double unDouble1 = Double.valueOf(TpbaAvant.getText());
				double unDouble2 = Double.valueOf(TpbaAvantGauche.getText());
				double unDouble3 = Double.valueOf(TpbaAvantDroit.getText());
				double unDouble4 = Double.valueOf(TpbaArriereGauche.getText());
				double unDouble5 = Double.valueOf(TpbaArriereDroit.getText());

				int unEntier6 = Integer.valueOf(Torientation.getText());
				int unEntier7 = Integer.valueOf(Tquantit�.getText());
				int unEntier8 = Integer.valueOf(this.estomacInitial.getText());


				if  (unDouble1 <= 0 || unDouble2 <= 0 
						|| unDouble1 <= 3 || unDouble1 <= 4 
						|| unDouble1 <= 5 || unEntier6 <= 0 
						|| unEntier7 <= 0 || unEntier8 <= 0 ) {
					Torientation.setText(null); 
				}

				//on v�rifie que la simulation n'est pas en lecture
				if (this.MonMondeVirtuel.getMaFenetreLecture().isLectureAppuye() == false){

					//on r�cup�re les donn�es entr�es		
					//on convertit le texte string en float, on divise par 100 pour obtenir la pba <1
					pbaAvant= Float.parseFloat(TpbaAvant.getText())/100;
					pbaAvantGauche = Float.parseFloat(TpbaAvantGauche.getText())/100;
					pbaAvantDroit = Float.parseFloat(TpbaAvantDroit.getText())/100;
					pbaArriereGauche = Float.parseFloat(TpbaArriereGauche.getText())/100;
					pbaArriereDroit = Float.parseFloat(TpbaArriereDroit.getText())/100;
					estomac = Integer.parseInt(estomacInitial.getText());
					orientation =Integer.parseInt(Torientation.getText());
					name = (nomAnimal.getText());
					quantite = Integer.parseInt(Tquantit�.getText());

					MonMondeVirtuel.getFenetreDuMonde().go();

					//on attribue une couleur al�atoire
					Random rand = new Random();
					// Java 'Color' class takes 3 floats, from 0 to 1.
					float r, g, b;
					do {
						r = rand.nextFloat();
						g = rand.nextFloat();
						b = (float) 0.5 * rand.nextFloat();
					} while ( r < b*0.1 && g < b*0.1);


					CouleurCourante= new Color(r, g, b);


					//on cr�e quantit� - 1	 fois l'animal
					for (int i=0;i<quantite-1;i++){
						ThreadAnimal threadtest = new ThreadAnimal(pbaAvant,
								pbaAvantGauche,
								pbaAvantDroit,
								pbaArriereGauche,
								pbaArriereDroit,
								estomac,
								orientation, 
								this.getMonMondeVirtuel(), 
								name,
								c);

						MonMondeVirtuel.getFenetreDuMonde().go();
						threadtest.saveAnimal("titi.txt");
						System.out.println(threadtest.getAnimal().getName());
						threadtest.start();
						MonMondeVirtuel.getFenetreDuMonde().go();}


					//on cr�e une derni�re fois l'animal et on ne sauvegarde qu'une fois
					ThreadAnimal threadtest = new ThreadAnimal(pbaAvant,
							pbaAvantGauche,
							pbaAvantDroit,
							pbaArriereGauche,
							pbaArriereDroit,
							estomac,
							orientation, 
							this.getMonMondeVirtuel(), 
							name,
							c);

					MonMondeVirtuel.getFenetreDuMonde().go();
					System.out.println(threadtest.getAnimal().getName());
					threadtest.start();
					MonMondeVirtuel.getFenetreDuMonde().go();

					//pan.AfficheAnimalCree(getGraphics());
				}
			} catch (NumberFormatException nfe) {

				System.out.println("Une des valeurs n'est pas un nombre" );
				JOptionPane.showMessageDialog(this,
						"Une des valeurs n'est pas un nombre",
						"warning",
						JOptionPane.WARNING_MESSAGE);
				TpbaAvant.setText("20");
				TpbaAvantGauche.setText("20");
				TpbaAvantDroit.setText("20");
				TpbaArriereGauche.setText("20");
				TpbaArriereDroit.setText("20");
				Torientation.setText("1");
				Tquantit�.setText("100");
				estomacInitial.setText("10");
			}



		}




		//bouton2 : "ok" on cr�� l'animal al�atoire
		if (e.getSource().equals(button2)){ 

			try {
				//v�rification des champs
				int unEntier7 = Integer.valueOf(Tquantit�bis.getText());
				int unEntier8 = Integer.valueOf(estomacInitialbis.getText());

				//on v�rifie que la simulation n'est pas en lecture
				if (this.MonMondeVirtuel.getMaFenetreLecture().isLectureAppuye() == false){

					//on r�cup�re les donn�es entr�es		
					//on convertit le texte string en float, on divise par 100 pour obtenir la pba <1

					estomacbis = Integer.parseInt(estomacInitialbis.getText());
					//orientationbis =Integer.parseInt(Torientationbis.getText());
					namebis = (nomAnimalbis.getText());
					quantitebis = Integer.parseInt(Tquantit�bis.getText());


					if  ( unEntier7 <= 0 || unEntier8 <= 0 ) {
						Tquantit�bis.setText(null);
						quantitebis = Integer.parseInt(Tquantit�bis.getText());
					}


					//on cr�e quantit� - 1	 fois l'animal
					for (int i=0;i<quantitebis-1;i++){
						//on attribue une couleur al�atoire
						Random rand = new Random();
						// Java 'Color' class takes 3 floats, from 0 to 1.
						float r, g, b;
						do {
							r = rand.nextFloat();
							g = rand.nextFloat();
							b = (float) 0.5 * rand.nextFloat();
						} while (r < b*0.1 && g < b*0.1);


						CouleurCourante= new Color(r, g, b);

						MonMondeVirtuel.getFenetreDuMonde().go();
						ThreadAnimal threadtest = new ThreadAnimal(
								estomacbis,
								this.getMonMondeVirtuel(), 
								namebis,
								this.getMonMondeVirtuel().getConstante());

						MonMondeVirtuel.getFenetreDuMonde().go();
						System.out.println(threadtest.getAnimal().getName());
						threadtest.start();
						MonMondeVirtuel.getFenetreDuMonde().go();
					}

					//on cr�e une derni�re fois l'animal et on ne sauvegarde qu'une fois
					ThreadAnimal threadtest = new ThreadAnimal(
							estomac,
							this.getMonMondeVirtuel(), 
							name,
							c);

					MonMondeVirtuel.getFenetreDuMonde().go();
					threadtest.saveAnimal(name + ".txt");
					System.out.println(threadtest.getAnimal().getName());
					threadtest.start();
					MonMondeVirtuel.getFenetreDuMonde().go();

				}
			}
			catch (NumberFormatException nfe) {

				System.out.println("Une des valeurs n'est pas un nombre" );
				JOptionPane.showMessageDialog(this,
						"Une des valeurs n'est pas un nombre",
						"warning",
						JOptionPane.WARNING_MESSAGE);
				Tquantit�bis.setText("10");
				estomacInitialbis.setText("100");
			}
		}



		//bouton3: charger un profil
		if (e.getSource().equals(button3)){ 

			//on v�rifie que la simulation n'est pas en lecture
			if (this.MonMondeVirtuel.getMaFenetreLecture().isLectureAppuye() == false){
				fileName = TfileName.getText();
				//on attribue une couleur al�atoire
				Random rand = new Random();
				// Java 'Color' class takes 3 floats, from 0 to 1.
				float r, g, b;
				do {
					r = rand.nextFloat();
					g = rand.nextFloat();
					b = (float) 0.5 * rand.nextFloat();
				} while (r < b*0.1 && g < b*0.1);


				CouleurCourante= new Color(r, g, b);
				try {
					ThreadAnimal threadtest = new ThreadAnimal(fileName, this.getMonMondeVirtuel(),  c);
				} catch (NullPointerException ee){
					JOptionPane.showMessageDialog(this,
							"Fichier introuvable",
							"warning",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		}

		//bouton4: ajout nourriture
		if (e.getSource().equals(button4)){ 

			try {
				//v�rification des champs
				int unEntier = Integer.valueOf(TqteNourriture.getText());


				//on lance la cr�ation plusieurs fois
				for(int i=0;i<Integer.parseInt(TqteNourriture.getText())-2;i++){
					int[] p={(int)(Math.random()*MonMondeVirtuel.getLargeur()),(int)(Math.random()*MonMondeVirtuel.getLongueur())};
					new Nourriture(p, MonMondeVirtuel);

				}

				int[] p={(int)(Math.random()*MonMondeVirtuel.getLargeur()),(int)(Math.random()*MonMondeVirtuel.getLongueur())};
				new Nourriture(p, MonMondeVirtuel);
				this.MonMondeVirtuel.getFenetreDuMonde().go();
			}

			catch (NumberFormatException nfe) {

				System.out.println("La quantit� de nourriture doit �tre enti�re" );
				JOptionPane.showMessageDialog(this,
						"La quantit� de nourriture doit �tre enti�re",
						"warning",
						JOptionPane.WARNING_MESSAGE);
			}
		}
	}

	public void setMonMondeVirtuel(MondeVirtuel monMondeVirtuel) {
		MonMondeVirtuel = monMondeVirtuel;
	}

	public Color getCouleurCourante() {
		return CouleurCourante;
	}

	public void setCouleurCourante(Color Couleur) {
		this.CouleurCourante = Couleur;
	}

}


