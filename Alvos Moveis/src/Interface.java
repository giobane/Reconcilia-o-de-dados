import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Interface extends JFrame implements Runnable  {
	private GeraAlvo gerador=new GeraAlvo();
	private BufferedImage Buffer;
    private ImageIcon atirador = new ImageIcon("./interface/atirador.png");
    private ImageIcon tiro = new ImageIcon("./interface/tiro.png");
    private ImageIcon alvo = new ImageIcon("./interface/alvo.png");
    private int largura = 600;
    private int altura = 600;
    private static int p=2;
    
    
    private void atualizar() {
    	
    }
    private boolean colisao(Posicao tiro, Posicao alvo) {
    	if(tiro.getX()<=alvo.getX()+20 && tiro.getX()>=alvo.getX()-20 && tiro.getY()>=alvo.getY()-20 && tiro.getY()<=alvo.getY()+20) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }

    private void tela() {
    	Graphics graph= getGraphics();
    	Graphics bg=Buffer.getGraphics();
    	bg.setColor(Color.white);
    	bg.fillRect(0, 0, 600, 600);
    	bg.drawImage(atirador.getImage(), 290,540,60,60, this);

    	for(int i=0; i<gerador.getAlvos1().size(); i++) {
    		bg.drawImage(alvo.getImage(), gerador.getAlvos1().get(i).getPosatt().getX(),gerador.getAlvos1().get(i).getPosatt().getY(), 20, 20, this);
    	
    	
    	}
    	
    	
    	
    	
    	for(int i=0; i<gerador.getAlvos2().size(); i++) {
    		bg.drawImage(alvo.getImage(), gerador.getAlvos2().get(i).getPosatt().getX(),gerador.getAlvos2().get(i).getPosatt().getY(), 20, 20, this);
    		
    	
    	     
    	
    	}
    	
    
    	for(int l=0; l<gerador.getTiros().size(); l++) {

    		bg.drawImage(tiro.getImage(), gerador.getTiros().get(l).getPosatt().getX(), gerador.getTiros().get(l).getPosatt().getY(), 20, 20, this);
    		for (int u=0; u<gerador.getAlvos1().size();u++) {
    			if(colisao(gerador.getTiros().get(l).getPosatt(),gerador.getAlvos1().get(u).getPosatt())){
    				gerador.getLancadores().get(gerador.getTiros().get(l).getIdd()).addmunicao();
    				gerador.getAlvos1().get(u).setAtingido(true);
    				gerador.getAlvos1().remove(u);
    				gerador.getTiros().get(l).setContato(true);
    				gerador.getTiros().remove(l);
    			
    			}
    		}
    		for (int u=0; u<gerador.getAlvos2().size();u++) {
    			if(colisao(gerador.getTiros().get(l).getPosatt(),gerador.getAlvos2().get(u).getPosatt())){
    				gerador.getLancadores().get(gerador.getTiros().get(l).getIdd()).addmunicao();
    				gerador.getAlvos2().get(u).setAtingido(true);
    				gerador.getAlvos2().remove(u);
    				gerador.getTiros().get(l).setContato(true);
    				gerador.getTiros().remove(l);
    			
    			}
    		}
    	}
    	graph.drawImage(Buffer,0,0,this);

}
    public void begin() {
    	gerador.start();
    	setTitle("Lançadores");
    	setSize(largura, altura);
    	setResizable(false);
    	setDefaultCloseOperation(EXIT_ON_CLOSE);
    	setLayout(null);
    	setVisible(true);
    	Buffer = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_RGB);
    	
    }
    @Override
    public void run() {
    	begin();
    	while(true) {
    		atualizar();
    		tela();
    		try {
				Thread.sleep(1000/30);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
    		
    		}
    	}
    }

