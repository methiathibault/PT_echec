import java.awt.*;
import javax.swing.*;


public class echiquier extends JFrame {

  private static final long serialVersionUID = 1890713488426328269L;

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
    this.getContentPane().setPreferredSize(new Dimension(720, 720));
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

    private static final long serialVersionUID = 5900033470581859604L;





    public DrawPanel() {
      this.setOpaque(true);

      this.setBackground(Color.GRAY);
    }





    public void paintComponent(Graphics g){
      super.paintComponent(g);
      this.drawPlateau(g);
      this.drawPiece(g);
    }





    public void drawPlateau(Graphics g){
      g.setColor(Color.WHITE);
      g.setFont(new Font(null, Font.BOLD, 20));
      for (int i=0; i<8; i++){
        if (joueur){
          g.drawString(lettre[i], 72+i*80, 28);
          g.drawString(lettre[i], 72+i*80, 680+28);
          g.drawString(chiffre[7-i], 12, 87+i*80);
          g.drawString(chiffre[7-i], 680+12, 87+i*80);
        }
        else {
          g.drawString(lettre[7-i], 72+i*80, 28);
          g.drawString(lettre[7-i], 72+i*80, 680+28);
          g.drawString(chiffre[i], 12, 87+i*80);
          g.drawString(chiffre[i], 680+12, 87+i*80);
        }
      }

      g.setColor(Color.LIGHT_GRAY);
      for (int i=0; i<8; i++){
        if (i%2 == 0){
          g.fillRect(120, 40+i*80, 80, 80);
          g.fillRect(280, 40+i*80, 80, 80);
          g.fillRect(440, 40+i*80, 80, 80);
          g.fillRect(600, 40+i*80, 80, 80);
        }

        else {
          g.fillRect(40, 40+i*80, 80, 80);
          g.fillRect(200, 40+i*80, 80, 80);
          g.fillRect(360, 40+i*80, 80, 80);
          g.fillRect(520, 40+i*80, 80, 80);
        }
      }

      g.setColor(Color.WHITE);
      for (int i=0; i<8; i++){
        if (i%2 == 1){
          g.fillRect(120, 40+i*80, 80, 80);
          g.fillRect(280, 40+i*80, 80, 80);
          g.fillRect(440, 40+i*80, 80, 80);
          g.fillRect(600, 40+i*80, 80, 80);
        }

        else {
          g.fillRect(40, 40+i*80, 80, 80);
          g.fillRect(200, 40+i*80, 80, 80);
          g.fillRect(360, 40+i*80, 80, 80);
          g.fillRect(520, 40+i*80, 80, 80);
        }
      }
    }



    

    public void drawPiece(Graphics g){
      for (int i=0; i<8; i++){
        for (int j=0; j<8; j++){
          if (plateau[i*8+j] != null){
            if (joueur)
              ((Graphics2D)g).drawImage(plateau[i*8+j].getImage(), 40+j*80, 40+i*80, this);
            else
              ((Graphics2D)g).drawImage(plateau[i*8+j].getImage(), 600-j*80, 600-i*80, this);
          }
        }
      }
    }

    


  }
}
