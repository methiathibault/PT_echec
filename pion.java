import java.awt.*;

public class pion extends piece{

  public pion(boolean c) {
    super(c);
  }

  public pion(pion p){
    super(p);
  }





  public String toString(){
    return "1";
  }




  public int typeMouvement(int xPiece, int yPiece, int xDestination, int yDestination){
    int avant = getAvant();
    if (xDestination == xPiece && yDestination - yPiece == avant)
      //mouvement en avant qui requiert qu'il n'y ai pas d'ennemi sur la case de destination
      return 2;
    else if ((xDestination == xPiece && yDestination - yPiece == 2*avant) && !this.aFaitDeplacement)
      //mouvement qui requiert que le pion n'ai pas deja effectu� un mouvement et qu'il n'y ai pas d'enemi sur le chemin
      return 3;
    else if ((xDestination - xPiece == -1 && yDestination - yPiece == avant) || (xDestination - xPiece == 1 && yDestination - yPiece == avant))
      //mouvement qui requiert qu'il y ai une piece ennemi sur la case d'arriv�e
      return 4;
    else
      return 0;

  }





  public Image getImage(){
    if (this.couleur)
      return Toolkit.getDefaultToolkit().getImage("D:\\eclipse\\workspace\\echiquier\\src\\img\\PionBlanc.png");
    else
      return Toolkit.getDefaultToolkit().getImage("D:\\eclipse\\workspace\\echiquier\\src\\img\\PionNoir.png");
  }


}
