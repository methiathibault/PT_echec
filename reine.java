import java.awt.*;

public class reine extends piece{

  public reine(boolean c) {
    super(c);
  }





  public reine(reine r){
    super(r);
  }





  public String toString(){
    return "5";
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

    else if (xDestination - xPiece == -(yDestination - yPiece)){
      if (xPiece < xDestination - 1)
        //mouvement en diagonale haut droite + check chemin
        return 6;
      else if (xPiece > xDestination + 1)
        //mouvement en diagonale bas gauche + check chemin
        return 7;
      else
        return 1;
    }

    else if (xDestination - xPiece == yDestination - yPiece){
      if (xPiece < xDestination - 1)
        //mouvement en diagonale bas droite + check chemin
        return 8;
      else if (xPiece > xDestination + 1)
        //mouvement en diagonale haut gauche + check chemin
        return 5;
      else
        // mouvement qui ne requiert pas de check les pieces sur le chemin (mouvement d'un seule case)
        return 1;
    }

    return 0;
  }






  public Image getImage(){
    if (this.couleur)
      return Toolkit.getDefaultToolkit().getImage("D:\\eclipse\\workspace\\echiquier\\src\\img\\ReineBlanc.png");
    else
      return Toolkit.getDefaultToolkit().getImage("D:\\eclipse\\workspace\\echiquier\\src\\img\\ReineNoir.png");
  }

  
}