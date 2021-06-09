import java.awt.*;

public class tour extends piece{




  public tour(boolean c) {
    super(c);
  }





  public tour(tour t){
    super(t);
  }





  public String toString(){
    return "2";
  }





  public int typeMouvement(int xPiece, int yPiece, int xDestination, int yDestination){
    if (xDestination == xPiece){
      if (yDestination - yPiece < -1)
        //mouvement en ligne haut + check chemin
        return 9;
      else if (yDestination -yPiece > 1)
        //mouvement en ligne bas + check chemin
        return 10;
      else
        //mouvement qui ne requiert pas de check les pieces sur le chemin (mouvement d'un seule case)
        return 1;
    }

    else if (yDestination == yPiece){
      if (xDestination - xPiece < -1)
        //mouvement en ligne gauche + check chemin
        return 11;
      else if (xDestination - xPiece > 1)
        //mouvement en ligne droite + check chemin
        return 12;
      else
        //mouvement qui ne requiert pas de check les pieces sur le chemin (mouvement d'un seule case)
        return 1;
    }

    return 0;
  }





  public Image getImage(){
    if (this.couleur)
      return Toolkit.getDefaultToolkit().getImage("D:\\eclipse\\workspace\\echiquier\\src\\img\\TourBlanc.png");
    else
      return Toolkit.getDefaultToolkit().getImage("D:\\eclipse\\workspace\\echiquier\\src\\img\\TourNoir.png");
  }

  
}
