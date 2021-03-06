
import java.awt.Color;
import java.awt.Graphics;
import java.util.concurrent.Semaphore;

import javax.swing.JFrame;

public class FenetreMonde extends JFrame {
	private GlobalVars c;
	private PanneauMonde MonPan; 
	MondeVirtuel MonMondeVirtuel;
	public Semaphore semtest;

	public FenetreMonde(MondeVirtuel unMonde, GlobalVars c){
		this.c = c;
		this.MonMondeVirtuel = unMonde;
		this.MonPan = new PanneauMonde(this, unMonde);
		this.setTitle(c.titreMonde);
		this.setSize(2*this.MonMondeVirtuel.getLargeur()+40, 2*this.MonMondeVirtuel.getLongueur()+60);
		this.setLocation(500,350); 
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.semtest = new Semaphore(1);

		/*On pr�vient notre JFrame que notre JPanel
    sera son content pane  c'est dans celui-ci que nous placerons nos composant */
		this.setContentPane(MonPan);
		this.setVisible(true);

		//on lance le mouvement
		System.out.println("ggo");
		this.MonPan.revalidate();
	}


	public MondeVirtuel getMonMondeVirtuel() {
		return MonMondeVirtuel;
	}       

	public void go(){ 

		//On redessine notre Panneau
		//MonPan.paint(MonPan.getGraphics());
	//	System.out.println("go");
		MonPan.repaint();
	}
	
	public void paintAnimal(Animal unAnimal, int[] anciennePosition, int[] nouvellePosition){
		MonPan.paintAnimal(unAnimal, anciennePosition, nouvellePosition);
	}
	
	public void paintAnimalInitial(Animal unAnimal, int[] Position){
		MonPan.paintAnimalInitial(unAnimal, Position);
	}
	
	
	
	public void paintNourriture(int[] position){
		MonPan.paintNourriture(position);
	}
	public void unPaintNourriture(int[] position){
		MonPan.unPaintNourriture(position);
	}
	
}    


