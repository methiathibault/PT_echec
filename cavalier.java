import java.awt.*;

public class cavalier extends piece{


  public cavalier(boolean c) {
    super(c);
  }





  public cavalier(cavalier c){
    super(c);
  }





  public String toString(){
    return "3";
  }

  public int typeMouvement(int xPiece, int yPiece, int xDestination, int yDestination){
    if ((xDestination == xPiece-1 && (yDestination == yPiece-2 || yDestination == yPiece+2))
     || (xDestination == xPiece+1 && (yDestination == yPiece-2 || yDestination == yPiece+2))
     || (xDestination == xPiece-2 && (yDestination == yPiece-1 || yDestination == yPiece+1))
     || (xDestination == xPiece+2 && (yDestination == yPiece-1 || yDestination == yPiece+1)))
      // mouvement qui ne requiert pas de check les pieces sur le chemin
      return 1;
    return 0;
  }






  public Image getImage(){
    if (this.couleur)
      return Toolkit.getDefaultToolkit().getImage("img/CavalierBlanc.png");
    else
      return Toolkit.getDefaultToolkit().getImage("img/CavalierNoir.png");
  }



}
