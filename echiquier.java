import java.awt.*;
import javax.swing.*;


public class echiquier extends JFrame {

  public static String[] lettre = { "A", "B", "C", "D", "E", "F", "G", "H" };
  public static String[] chiffre = { "1", "2", "3", "4", "5", "6", "7", "8" };

  public DrawPanel drawPanel;
  public Piece[] echiquier;
  public boolean joueur;





  public echiquier(Piece[] echiquier, boolean joueur){
    super();
    this.joueur = joueur;
    this.echiquier = echiquier;
    this.setTitle("echiquier");
    this.getContentPane().setPreferredSize(new Dimension(1000, 1000));
    this.pack();
    this.setLocationRelativeTo(null);
    this.setResizable(false);
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.setAlwaysOnTop(true);
    this.drawPanel = new DrawPanel();
    this.add(drawPanel, BorderLayout.CENTER);
  }





  public void refresh(Piece[] echiquier, boolean joueur){
    this.joueur = joueur;
    this.echiquier = echiquier;
    drawPanel.repaint();
    this.setVisible(true);
  }





  class DrawPanel extends JPanel {

    public DrawPanel() {
      this.setOpaque(true);

      this.setBackground(Color.GRAY);
    }





    public void paintComponent(Graphics graph){
      super.paintComponent(graph);
      this.drawEchiquier(graph);
      this.drawPiece(graph);
    }





    public void drawEchiquier(Graphics graph){
      graph.setColor(Color.WHITE);
      graph.setFont(new Font(null, Font.BOLD, 20));
      for (int i=0; i<8; i++){
        if (joueur){
          graph.drawString(lettre[i], 72+i*80, 28);
          graph.drawString(lettre[i], 72+i*80, 680+28);
          graph.drawString(chiffre[7-i], 12, 87+i*80);
          graph.drawString(chiffre[7-i], 680+12, 87+i*80);
        }
        else {
          graph.drawString(lettre[7-i], 72+i*80, 28);
          graph.drawString(lettre[7-i], 72+i*80, 680+28);
          graph.drawString(chiffre[i], 12, 87+i*80);
          graph.drawString(chiffre[i], 680+12, 87+i*80);
        }
      }

      graph.setColor(Color.LIGHT_GRAY);
      for (int i=0; i<8; i++){
        if (i%2 == 0){
          graph.fillRect(120, 40+i*80, 80, 80);
          graph.fillRect(280, 40+i*80, 80, 80);
          graph.fillRect(440, 40+i*80, 80, 80);
          graph.fillRect(600, 40+i*80, 80, 80);
        }

        else {
          graph.fillRect(40, 40+i*80, 80, 80);
          graph.fillRect(200, 40+i*80, 80, 80);
          graph.fillRect(360, 40+i*80, 80, 80);
          graph.fillRect(520, 40+i*80, 80, 80);
        }
      }

      graph.setColor(Color.WHITE);
      for (int i=0; i<8; i++){
        if (i%2 == 1){
          graph.fillRect(120, 40+i*80, 80, 80);
          graph.fillRect(280, 40+i*80, 80, 80);
          graph.fillRect(440, 40+i*80, 80, 80);
          graph.fillRect(600, 40+i*80, 80, 80);
        }

        else {
          graph.fillRect(40, 40+i*80, 80, 80);
          graph.fillRect(200, 40+i*80, 80, 80);
          graph.fillRect(360, 40+i*80, 80, 80);
          graph.fillRect(520, 40+i*80, 80, 80);
        }
      }
    }



    

    public void drawPiece(Graphics graph){
      for (int i=0; i<8; i++){
        for (int j=0; j<8; j++){
          if (echiquier[i*8+j] != null){
            if (joueur)
              ((Graphics2D)graph).drawImage(echiquier[i*8+j].getImage(), 40+j*80, 40+i*80, this);
            else
              ((Graphics2D)graph).drawImage(echiquier[i*8+j].getImage(), 600-j*80, 600-i*80, this);
          }
        }
      }
    }

    


  }
}
