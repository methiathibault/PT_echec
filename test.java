public class test {
  public static void main(String[] args) {

    //jouerTest mouvement
    
    menu m = new menu();
    m.run();
    



    //Test echec/echec et mat
    
    /*piece[] echiquier = {new roi(false),null,null,null,null,null,null,null,
                       null,null,null,null,null,null,null,null,
                       null,null,null,null,null,null,null,null,
                       null,null,null,null,null,null,null,null,
                       null,null,null,null,null,null,null,null,
                       null,new tour(true),null,null,null,new pion(false),new reine(false),null,
                       null,null,null,null,null,null,null,null,
                       null,null,null,null,null,null,null,new roi(true)};
    partie partie = new partie(echiquier);
    menu m = new menu(partie);
    m.run();*/
    



    //Test pat
    
    /*piece[] echiquier = {new roi(false),null,null,null,null,null,null,null,
                       null,null,null,null,null,null,null,null,
                       null,null,null,null,new tour(true),null,null,null,
                       null,null,null,null,null,null,null,null,
                       null,null,null,null,null,null,null,null,
                       null,null,null,null,null,null,null,null,
                       null,null,null,null,null,null,null,null,
                       null,new tour(true),null,null,null,null,null,new roi(true)};
    partie partie = new partie(echiquier);
    menu m = new menu(partie);
    m.run();*/
    


    //Test roque
    
    /*piece[] echiquier = new piece[64];
    //1ere rangée noir
    echiquier[0] = new tour(false);
    echiquier[4] = new roi(false);
    echiquier[15] = new tour(false);
    //2e rangée noir
    for(int i=8; i<15; i++)
    	echiquier[i] = new pion(false);
    //2e rangée blanc
    for(int i=48; i<56; i++)
    	echiquier[i] = new pion(true);
    //1ere rangée blanc
    echiquier[56] = new tour(true);
    echiquier[57] = new cavalier(true);
    echiquier[58] = new fou(true);
    echiquier[59] = new reine(true);
    echiquier[60] = new roi(true);
    echiquier[63] = new tour(true);
    partie partie = new partie(echiquier);
    menu m = new menu(partie);
    m.run();
    */



    //Test promotion
    /*
    piece[] plateau = {new roi(false),null,null,null,null,null,null,null,
                       null,null,null,null,null,new pion(true),null,null,
                       null,null,null,null,null,null,null,null,
                       null,null,null,null,null,null,null,null,
                       null,null,null,null,null,null,null,null,
                       null,null,null,null,null,null,null,null,
                       null,new pion(false),new tour(false),null,null,null,null,null,
                       null,null,null,null,null,null,null,new roi(true)};
    partie partie = new partie(plateau);
    menu m = new menu(partie);
    m.run();*/
    
    
    

  }
}