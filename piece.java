import java.awt.*;

public abstract class piece{


  public boolean couleur;
  public boolean aFaitDeplacement;





  public piece(boolean c){
    this.couleur = c;
    this.aFaitDeplacement = false;
  }





  public piece(piece p){
    this.couleur = p.getCouleur();
    this.aFaitDeplacement = p.aFaitDeplacement;
  }





  public boolean getAFaitDeplacement() {
    return this.aFaitDeplacement;
  }





  public void effectueDeplacement() {
    this.aFaitDeplacement = true;
  }





  public boolean getCouleur(){
    return this.couleur;
  }





  public void setCouleur(boolean c){
    this.couleur = c;
  }





  public int getAvant(){
    if (this.couleur)
      return -1;
    else
      return 1;
  }





  public abstract Image getImage();





  
  public abstract int typeMouvement(int xPiece, int yPiece, int xDestination, int yDestination);

}
