import java.awt.*;

public class fou extends piece{






  public fou(boolean c) {
    super(c);
  }





  public fou(fou f){
    super(f);
  }





  public String toString(){
    return "4";
  }





  public int typeMouvement(int xPiece, int yPiece, int xDestination, int yDestination){
    if (xDestination - xPiece == -(yDestination - yPiece)){
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
        return 1;
    }

    return 0;
  }





  public Image getImage(){
    if (this.couleur)
      return Toolkit.getDefaultToolkit().getImage("D:\\eclipse\\workspace\\echiquier\\src\\img\\FouBlanc.png");
    else
      return Toolkit.getDefaultToolkit().getImage("D:\\eclipse\\workspace\\echiquier\\src\\img\\FouNoir.png");
  }


  
}