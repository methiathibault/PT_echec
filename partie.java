import java.util.ArrayList;
import java.io.Serializable;

public class partie implements Serializable{

  private static final long serialVersionUID = -6671243848797034601L;

  private piece[] echiquier;
  private boolean joueur;
  private ArrayList<String> historique;
  private int nbrTour;
  private echiquier affichage = new echiquier(this.echiquier, this.joueur);





  public partie(){
    this.echiquier = new piece[64];
    this.initPlateau();
    this.joueur = true;
    this.historique = new ArrayList<String>();
    this.nbrTour = 1;
  }





  public partie(partie p){
    this.echiquier = new piece[64];
    piece[] pEchiquier = p.getEchiquier();
    for (int i=0; i<64; i++){
      piece pPiece = pEchiquier[i];
      if (pPiece == null)
        this.echiquier[i] = null;
      else if (pPiece.getClass() == pion.class)
        this.echiquier[i] = new pion((pion) pPiece);
      else if (pPiece.getClass() == tour.class)
        this.echiquier[i] = new tour((tour) pPiece);
      else if (pPiece.getClass() == cavalier.class)
        this.echiquier[i] = new cavalier((cavalier) pPiece);
      else if (pPiece.getClass() == fou.class)
        this.echiquier[i] = new fou((fou) pPiece);
      else if (pPiece.getClass() == reine.class)
        this.echiquier[i] = new reine((reine) pPiece);
      else
        this.echiquier[i] = new roi((roi) pPiece);
    }
    this.joueur = p.getJoueur();
    this.historique = p.getHistorique();
    this.nbrTour = p.getnbrTour();
  }





  public partie(piece[] echiquier){
    this.echiquier = echiquier;
    this.joueur = true;
    this.historique = new ArrayList<String>();
    this.nbrTour = 1;
  }





  private void initPlateau(){
    //1ere rangée noir
    this.echiquier[0] = new tour(false);
    this.echiquier[1] = new cavalier(false);
    this.echiquier[2] = new fou(false);
    this.echiquier[3] = new reine(false);
    this.echiquier[4] = new roi(false);
    this.echiquier[5] = new fou(false);
    this.echiquier[6] = new cavalier(false);
    this.echiquier[7] = new tour(false);

    //2e rangée noir
    for(int i=8; i<16; i++)
      this.echiquier[i] = new pion(false);

    //2e rangée blanc
    for(int i=48; i<56; i++)
      this.echiquier[i] = new pion(true);

    //1ere rangée blanc
    this.echiquier[56] = new tour(true);
    this.echiquier[57] = new cavalier(true);
    this.echiquier[58] = new fou(true);
    this.echiquier[59] = new reine(true);
    this.echiquier[60] = new roi(true);
    this.echiquier[61] = new fou(true);
    this.echiquier[62] = new cavalier(true);
    this.echiquier[63] = new tour(true);
  }





  public piece[] getEchiquier(){
    return this.echiquier;
  }





  public boolean getJoueur(){
    return this.joueur;
  }





  public ArrayList<String> getHistorique(){
    return this.historique;
  }





  public void setJoueur(boolean j){
    this.joueur = j;
  }





  public void nextJoueur(){
    this.joueur = !this.joueur;
  }





  public int getnbrTour(){
    return this.nbrTour;
  }





  public boolean deplacementPossible(int xPiece, int yPiece, int xDestination, int yDestination, boolean joueur){
    //verifie qu'il y a bel et bien un déplacement
    if (xPiece == xDestination && yPiece == yDestination){
      //System.out.println("aucun deplacement effectue");
      return false;
    }

    //verifie que la case contenant la piece existe
    if (!(xPiece <= 7 && xPiece >= 0 ) || !(yPiece <= 7 && yPiece >= 0 )){
      //System.out.println("la case indiquée n'existe pas ");
      return false;
    }

    //verifie que la case de destination existe
    if (!(xDestination <= 7 && xDestination >= 0 ) || !(yDestination <= 7 && yDestination >= 0 )){
      //System.out.println("la case indiquée n'existe pas ");
      return false;
    }

    piece pieceDepart = this.echiquier[xPiece + 8*yPiece];
    piece pieceArrivee = this.echiquier[xDestination + 8*yDestination];

    //verifie qu'il y a bien une piece alliée sur la case de depart
    if (pieceDepart == null || pieceDepart.getCouleur() != joueur){
      //System.out.println("il n'y a aucune piece sur cette case");
      return false;
    }

    //verifie qu'il n'y a pas de piece allié sur la case de destination
    if (pieceArrivee != null && pieceArrivee.getCouleur() == joueur){
      //System.out.println("la case d'arivée contient un pion allié");
      return false;
    }

    /*
    Regarde quel type de mouvement est effectué:
    0 -> mouvement impossible
    1 -> mouvement qui ne requiert pas de check les pieces sur le chemin
    2 -> mouvement en avant qui requiert qu'il n'y ai pas d'ennemi sur la case de destination
    3 -> mouvement qui requiert que le pion n'ai pas deja effectué un mouvement et qu'il n'y ai pas d'enemi sur le chemin
    4 -> mouvement qui requiert qu'il y ai une piece ennemi sur la case d'arrivee
    5 -> mouvement en diagonale haut gauche + check chemin
    6 -> mouvement en diagonale haut droite + check chemin
    7 -> mouvement en diagonale bas gauche + check chemin
    8 -> mouvement en diagonale bas droite + check chemin
    9 -> mouvement en ligne haut + check chemin
    10 -> mouvement en ligne bas + check chemin
    11 -> mouvement en ligne gauche + check chemin
    12 -> mouvement en ligne droite + check chemin
    13 -> grand roque
    14 -> petit roque
    */

    int typeMouvement = pieceDepart.typeMouvement(xPiece, yPiece, xDestination, yDestination);
    int avant = pieceDepart.getAvant();


    //mouvement impossible si typeMouvement = 0
    if (typeMouvement == 0){
      //System.out.println("le mouvement indiquée est impossible");
      return false;
    }

    else if(typeMouvement == 1){
      return true;
    }

    //On applique differents tests en fonction des requierement specifiques du move (ex: qu'il n'ai pas de piece entre la case de depart et la case d'arrivee)
    else if (typeMouvement == 2){
      if (pieceArrivee != null && pieceArrivee.getCouleur() != joueur)
        return false;
    }

    else if (typeMouvement == 3){
      if (pieceDepart.getAFaitDeplacement() || pieceArrivee != null || this.echiquier[xPiece + 8*(yPiece+avant)] != null)
        return false;
    }

    else if (typeMouvement == 4){
      if (pieceArrivee == null || pieceArrivee.getCouleur() == joueur)
        return false;
    }

    else if (typeMouvement == 5 || typeMouvement == 6 || typeMouvement == 7 || typeMouvement == 8){
      int xDirection;
      int yDirection;
      if (typeMouvement == 5){
        xDirection = -1;
        yDirection = -1;
      }
      else if (typeMouvement == 6){
        xDirection = 1;
        yDirection = -1;
      }
      else if (typeMouvement == 7){

        xDirection = -1;
        yDirection = 1;
      }
      else{
        xDirection = 1;
        yDirection = 1;
      }

      for(int i=1; i<Math.abs(xDestination - xPiece); i++){
        if (this.echiquier[(xPiece+xDirection*i) + 8*(yPiece+yDirection*i)] != null){
          return false;
        }
      }
    }

    else if(typeMouvement == 9 || typeMouvement == 10 || typeMouvement == 11 || typeMouvement == 12){
      if (typeMouvement == 9){
        for(int i = 1; i < yPiece-yDestination; i++){
          if (this.echiquier[xPiece + 8*(yPiece-i)] != null)
            return false;
        }
      }
      else if (typeMouvement == 10){
        for(int i = 1; i < yDestination-yPiece; i++){
          if (this.echiquier[xPiece + 8*(yPiece+i)] != null)
            return false;
        }
      }
      else if (typeMouvement == 11){
        for(int i = 1; i < xPiece-xDestination; i++){
          if (this.echiquier[xPiece-i + 8*yPiece] != null)
            return false;
        }
      }
      else{
        for(int i = 1; i < xDestination-xPiece; i++){
          if (this.echiquier[xPiece+i + 8*yPiece] != null)
            return false;
        }
      }
    }

    else if (typeMouvement == 13){
      if (!grandRoquePossible())
        return false;
    }

    else{
      if (!petitRoquePossible())
        return false;
    }

    return true;
  }





  public void ajouterHistorique(int xPiece, int yPiece, int xDestination, int yDestination){
    if (joueur)
      this.historique.add(nbrTour + ". " + (xPiece + 1) + (8 - yPiece) + (xDestination + 1) + (8 - yDestination));
    else{
      String s = this.historique.get(this.historique.size() - 1);
      this.historique.set(this.historique.size()-1, s + " " + (xPiece + 1) + (8 - yPiece) + (xDestination + 1) + (8 - yDestination));
    }

  }





  public void afficherHistorique(){
    for (String s : this.historique)
      System.out.println(s);
    if (!this.historique.isEmpty())
      System.out.println();
  }





  public boolean grandRoquePossible(){
    int xRoi = 4;
    int yRoi;
    int xTour = 0;
    int yTour;


    if (this.joueur){
      yRoi = 7;
      yTour = 7;
    }
    else{
      yRoi = 0;
      yTour = 0;
    }

    piece caseRoi = this.echiquier[xRoi + yRoi*8];
    piece caseTour = this.echiquier[xTour + yTour*8];

    if (caseRoi == null || caseRoi.getClass() != roi.class || caseRoi.getCouleur() != this.getJoueur() || caseRoi.getAFaitDeplacement())
      return false;

    else if (caseTour == null || caseTour.getClass() != tour.class || caseTour.getCouleur() != this.getJoueur() || caseTour.getAFaitDeplacement())
      return false;

    else if (this.echiquier[xRoi-1 + yRoi*8] != null || this.echiquier[xRoi-2 + yRoi*8] != null || this.echiquier[xRoi-3 + yRoi*8] != null)
      return false;

    else if (enEchec() || enEchecApresMouvemement(xRoi, yRoi, xRoi-1, yRoi) || enEchecApresMouvemement(xRoi, yRoi, xRoi-2, yRoi))
      return false;

    else
      return true;
  }





  public boolean petitRoquePossible() {
    int xRoi = 4;
    int yRoi;
    int xTour = 7;
    int yTour;

    if (this.joueur) {
      yRoi = 7;
      yTour = 7;
    }
    else{
      yRoi = 0;
      yTour = 0;
    }

    piece caseRoi = this.echiquier[xRoi + yRoi*8];
    piece caseTour = this.echiquier[xTour + yTour*8];

    if (caseRoi == null || caseRoi.getClass() != roi.class || caseRoi.getCouleur() != this.getJoueur() || caseRoi.getAFaitDeplacement()){
      //System.out.println("1");
      return false;
    }

    else if (caseTour == null || caseTour.getClass() != tour.class || caseTour.getCouleur() != this.getJoueur() || caseTour.getAFaitDeplacement()){
      //System.out.println("2");
      return false;
    }

    else if (this.echiquier[xRoi+1 + yRoi*8] != null || this.echiquier[xRoi+2 + yRoi*8] != null){
      //System.out.println("3");
      return false;
    }

    else if (enEchec() || enEchecApresMouvemement(xRoi, yRoi, xRoi+1, yRoi) || enEchecApresMouvemement(xRoi, yRoi, xRoi+2, yRoi)){
      //System.out.println("4");
      return false;
    }

    else
      return true;
  }





  public void grandRoque(){
    int y;
    if (this.joueur) {
      y = 7;
    }
    else{
      y = 0;
    }

    piece roi = this.echiquier[4+y*8];
    piece tour = this.echiquier[0+y*8];
    this.echiquier[4+y*8] = null;
    this.echiquier[0+y*8] = null;
    this.echiquier[2+y*8] = roi;
    this.echiquier[3+y*8] = tour;
  }





  public void petitRoque(){
    int y;
    if (this.joueur) {
      y = 7;
    }
    else{
      y = 0;
    }

    piece roi = this.echiquier[4+y*8];
    piece tour = this.echiquier[0+y*8];
    this.echiquier[4+y*8] = null;
    this.echiquier[7+y*8] = null;
    this.echiquier[6+y*8] = roi;
    this.echiquier[5+y*8] = tour;
  }





  public void deplacerPiece(int xPiece, int yPiece, int xDestination, int yDestination){

    piece pieceDepart = this.echiquier[xPiece + 8*yPiece];

    //on prend en compte le mouvement
    pieceDepart.effectueDeplacement();

    //enregistrement du coup dans l'historique
    this.ajouterHistorique(xPiece, yPiece, xDestination, yDestination);

    //le mouvement est effectué
    if (pieceDepart.typeMouvement(xPiece, yPiece, xDestination, yDestination) == 13)
      this.grandRoque();

    else if (pieceDepart.typeMouvement(xPiece, yPiece, xDestination, yDestination) == 14)
      this.petitRoque();

    else{
      this.echiquier[xPiece + 8*yPiece] = null;
      this.echiquier[xDestination + 8*yDestination] = pieceDepart;
    }
  }





  public int[] getPosRoi(){
    int xRoi = -1;
    int yRoi = -1;
    for(int i = 0; i<64 && xRoi == -1; i++){
      if(this.echiquier[i] != null && this.echiquier[i].getClass() == roi.class && this.echiquier[i].getCouleur() == this.joueur){
        xRoi = i%8;
        yRoi = i/8;
      }
    }
    return new int[] {xRoi, yRoi};
  }



  public boolean enEchec(){
    int[] posRoi = this.getPosRoi();
    int xRoi = posRoi[0];
    int yRoi = posRoi[1];
    boolean ennemi = !this.joueur;

    for(int i = 0; i<64; i++){
      if (this.echiquier[i] != null && this.echiquier[i].getCouleur() == ennemi && this.deplacementPossible(i%8, i/8, xRoi, yRoi, ennemi))
        return true;
    }
    return false;
  }





  public boolean enEchecApresMouvemement(int xPiece, int yPiece, int xDestination, int yDestination){
    //on cree une copie de la partie
    partie copiePartie = new partie(this);
    //on simule le mouvement
    piece pieceDepart = copiePartie.echiquier[xPiece + 8*yPiece];
    copiePartie.echiquier[xPiece + 8*yPiece] = null;
    copiePartie.echiquier[xDestination + 8*yDestination] = pieceDepart;
    //on regarde si le move met le joueur en echec
    return copiePartie.enEchec();
  }





  public boolean echecEtMat(){
    if (enEchec()){
      for (int i = 0; i < 64; i++) {
        if (this.echiquier[i] != null && this.echiquier[i].getCouleur() == this.joueur) {
          for (int j = 0; j < 64; j++) {
            if (this.deplacementPossible(i % 8, i / 8, j % 8, j / 8, this.joueur)
                && !this.enEchecApresMouvemement(i % 8, i / 8, j % 8, j / 8))
              return false;
          }
        }
      }
      return true;
    }
    return false;
  }





  public boolean pat(){
    if (!enEchec()){
      for (int i = 0; i < 64; i++) {
        if (this.echiquier[i] != null && this.echiquier[i].getCouleur() == this.joueur) {
          for (int j = 0; j < 64; j++) {
            if (this.deplacementPossible(i % 8, i / 8, j % 8, j / 8, this.joueur)
                && !this.enEchecApresMouvemement(i % 8, i / 8, j % 8, j / 8))
                return false;
          }
        }
      }
      return true;
    }
    return false;
  }





  public int promotionPossible(){
    if (this.joueur){
      for (int i=0; i<8; i++){
        if (this.echiquier[i] != null && this.echiquier[i].getCouleur() && this.echiquier[i].getClass() == pion.class)
          return i;
      }
    }

    else{
      for (int i=56; i<63; i++){
        if (this.echiquier[i] != null && !this.echiquier[i].getCouleur() && this.echiquier[i].getClass() == pion.class)
          return i;
      }
    }

    return -1;
  }





  public void promotion(int nJoueur, int position){
    this.clearTerminal();
    this.affichage.refresh(this.echiquier, this.joueur);
    this.afficherHistorique();
    System.out.println("Au tour du joueur " + nJoueur + ".\n");
    System.out.println("En quelle pièce voulez vous promouvoir votre pion : (Entrez \"reine\", \"fou\", \"tour\" ou \"cavalier\")");
    String piece = menu.scan.nextLine().toLowerCase();

    while (!(piece.equals("reine") || piece.equals("r") || piece.equals("fou") || piece.equals("f") 
        || piece.equals("tour") || piece.equals("t") || piece.equals("cavalier") || piece.equals("c"))){
      this.clearTerminal();
      this.afficherHistorique();
      System.out.println("Au tour du joueur " + nJoueur + ".\n");
      System.out.println("Saisie incorrecte, veuillez réesayer : (Entrez \"reine\", \"fou\", \"tour\" ou \"cavalier\")");
      piece = menu.scan.nextLine().toLowerCase();
    }

    if (piece.equals("reine") || piece.equals("r"))
      this.echiquier[position] = new reine(this.joueur);

    else if (piece.equals("fou") || piece.equals("f"))
      this.echiquier[position] = new fou(this.joueur);

    else if (piece.equals("tour") || piece.equals("t"))
      this.echiquier[position] = new tour(this.joueur);

    else if (piece.equals("cavalier") || piece.equals("c"))
      this.echiquier[position] = new cavalier(this.joueur);

    this.echiquier[position].effectueDeplacement();
  }





/*
  public void afficherPlateau(){
    for(int i=0; i<8; i++){
      String row = "";
      for(int j=0; j<8; j++){
        if (this.plateau[8*i+j] == null)
          row += "0";
        else
          row += this.plateau[8*i+j];
      }
      System.out.println(row);
    }
  }
*/





  public int typeAction(String action){
    if (action.equals("help") || action.equals("h"))
      return 1;

    else if (action.equals("menu") || action.equals("m"))
      return 2;

    else if (action.equals("abandonner") || action.equals("a"))
      return 3;

    else if (action.length() == 5
    && action.charAt(0) >= 'a' && action.charAt(0) <= 'h'
    && action.charAt(1) >= '1' && action.charAt(1) <= '8'
    && action.charAt(2) == ' '
    && action.charAt(3) >= 'a' && action.charAt(3) <= 'h'
    && action.charAt(4) >= '1' && action.charAt(4) <= '8'
    && this.deplacementPossible(action.charAt(0) - 97, 7-(action.charAt(1)-49), action.charAt(3)-97, 7-(action.charAt(4)-49), this.joueur)
    && !this.enEchecApresMouvemement(action.charAt(0)-97, 7-(action.charAt(1)-49), action.charAt(3)-97, 7-(action.charAt(4)-49)))
        return 4;

    else if ((action.equals("grand roque") || action.equals("gr")) && grandRoquePossible())
      return 5;

    else if ((action.equals("petit roque") || action.equals("pr")) && petitRoquePossible())
      return 6;

    else
      return 0;
  }





  public void clearTerminal(){
    String os = System.getProperty("os.name").toLowerCase();
    if (os.contains("win")){
      try {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
      } catch (Exception e) {
        System.out.println(e);
      }
    }

    else{
      System.out.print("\033[H\033[2J");
      System.out.flush();
    }
  }





  public void closeAffichage(){
    this.affichage.setVisible(false);
    this.affichage.dispose();
  }





  public void closeScanner(){
    menu.scan.close();
  }




  public void help(){
    System.out.println("Liste des commandes:\n");
    System.out.println("\"help\" pour obtenir la liste des commandes,");
    System.out.println("\"menu\" pour accéder au menu du jeu,");
    System.out.println("\"abandonner\" pour abandonner la partie,");
    System.out.println("Un mouvement peut être effectué sous la forme \"[départ] [arrivée]\" (ex: \"b1 c3\"),");
    System.out.println("\"grand roque\" pour effectuer le grand roque,");
    System.out.println("\"petit roque\" pour effectuer le petit roque.");
    System.out.println("Toutes les commandes peuvent être écrites de manière raccourcie (ex: \"m\" au lieu de \"menu\").");
    System.out.println("\nEntrez \"quitter\" pour revenir quitter cette page et revenir au jeu:");
    String action = menu.scan.nextLine().toLowerCase();
    while (!(action.equals("quitter") || action.equals("q"))){
      System.out.println("Liste des commandes:\n");
      System.out.println("\"help\" pour obtenir la liste des commandes,");
      System.out.println("\"menu\" pour accéder au menu du jeu,");
      System.out.println("\"abandonner\" pour abandonner la partie,");
      System.out.println("Un mouvement peut être effectué sous la forme \"[départ] [arrivée]\" (ex: \"b1 c3\"),");
      System.out.println("\"grand roque\" pour effectuer le grand roque,");
      System.out.println("\"petit roque\" pour effectuer le petit roque.");
      System.out.println("Toutes les commandes peuvent être écrites de manière raccourcie (ex: \"m\" au lieu de \"menu\").");
      System.out.println("\nEntrez \"quitter\" pour revenir quitter cette page et revenir au jeu:");
      action = menu.scan.nextLine().toLowerCase();
    }
  }





  public boolean jouer(){
    while (!echecEtMat() && !pat()){

      this.clearTerminal();
      this.affichage.refresh(this.echiquier, this.joueur);
      this.afficherHistorique();


      int nJoueur;
      if (this.joueur)
        nJoueur = 1;
      else
        nJoueur = 2;

      System.out.println("Au tour du joueur " + nJoueur + ".\n");
      System.out.println("Veuillez effectuer une action:");

      String action = menu.scan.nextLine().toLowerCase();
      int typeAction = typeAction(action);

      while (typeAction == 0){
        this.clearTerminal();
        this.afficherHistorique();
        System.out.println("Au tour du joueur " + nJoueur + ".\n");
        System.out.println("Action ou mouvement invalide, veuillez réessayer: (\"help\" pour  afficher une liste commandes)");
        action = menu.scan.nextLine().toLowerCase();
        typeAction = typeAction(action);
      }

      if (typeAction == 1){
        this.clearTerminal();
        this.help();
      }

      else if (typeAction == 2){
        this.affichage.setVisible(false);
        return false;
      }

      else if (typeAction == 3){
        return true;
      }

      else if (typeAction == 4 || typeAction == 5 || typeAction == 6){
        if (typeAction == 4){
          int xPiece = action.charAt(0)-97;
          int yPiece = 7-(action.charAt(1)-49);
          int xDestination = action.charAt(3)-97;
          int yDestination = 7-(action.charAt(4)-49);
          this.deplacerPiece(xPiece, yPiece, xDestination, yDestination);
        }

        else if (typeAction == 5){
          this.grandRoque();
          if (joueur)
            ajouterHistorique(4, 1, 2, 1);
          else
            ajouterHistorique(4, 7, 2, 7);
        }

        else{
          this.petitRoque();
          if (joueur)
            ajouterHistorique(4, 1, 6, 1);
          else
            ajouterHistorique(4, 7, 6, 7);
        }

        int promotionPossible = this.promotionPossible();
        if (promotionPossible != -1){
          this.promotion(nJoueur, promotionPossible);
        }

        if (!joueur)
          this.nbrTour ++;
        this.nextJoueur();
      }
    }

    this.clearTerminal();
    this.affichage.refresh(this.echiquier, this.joueur);
    this.afficherHistorique();

    if (pat()){
      System.out.println("Pat ! Il y a égalité.\n\n");
    }

    else{
      int nJoueur;
      if (this.joueur)
        nJoueur = 2;
      else
        nJoueur = 1;
      System.out.println("Echec et Mat ! Le joueur "+nJoueur+" à gagné.\n\n");
    }

    System.out.println("Entrez \"menu\" pour revenir au menu");
    String action = menu.scan.nextLine().toLowerCase();

    while (!(action.equals("menu") || action.equals("m"))){
      this.clearTerminal();
      this.afficherHistorique();
      if (pat()) {
        System.out.println("Pat ! Il y a égalité.\n\n");
      }
      else {
        int nJoueur;
        if (this.joueur)
          nJoueur = 2;
        else
          nJoueur = 1;
        System.out.println("Echec et Mat ! Le joueur " + nJoueur + " à gagné.\n\n");
      }

      System.out.println("Entrez \"menu\" pour revenir au menu:");
      action = menu.scan.nextLine().toLowerCase();
    }

    return true;
  }



}