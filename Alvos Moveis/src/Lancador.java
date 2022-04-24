import java.util.concurrent.Semaphore;
import java.util.Stack;
public class Lancador extends Thread{
	private Tiro tiro;
	private int id;
	private Posicao pos;
	private Alvos AlvoAtual;
	private Semaphore semaforo = new Semaphore(1);
	private boolean mirado;
	private Posicao tirofin;
	private Stack<Municao> carregador = new Stack();
	private boolean carrvazio=false;
	private boolean preparado=false;
	
	
	public Lancador(Alvos alvo) {
		this.pos=new Posicao(300,600);
		this.AlvoAtual=alvo;
		this.tiro= new Tiro();
		this.carregador.add(new Municao());
        this.carregador.add(new Municao());
        this.carregador.add(new Municao());
        
		start();
		
	}
	
	public Tiro getTiro() {
		return tiro;
	}
	
	public void setTiro(Tiro tiro) {
		this.tiro = tiro;
	}
	
	public Posicao getPos() {
		return pos;
	}
	
	public void setPos(Posicao pos) {
		this.pos = pos;
	}
	public int getIdlan() {
		return id;
	}

	public void setIdlan(int id) {
		this.id = id;
	}
		
	public void Carregar() {	
		try {
			Thread.sleep(30);
			
			if(this.carregador.isEmpty()==false) {
				this.carrvazio=false;
		

				
			}
			else {

				this.carrvazio=true;
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		
		
	
	
	public void Preparar() {
		if(this.AlvoAtual!=null) {
			
		try {
			Thread.sleep(30);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
		this.preparado=true;
		 this.AtiraOnde(this.AlvoAtual.getTimestamp(), this.AlvoAtual.getPosin());
		 this.tiro.setTirofin(tirofin, AlvoAtual);

		
		}
		else {
			
		
			this.preparado=false;
		}
		
	}
	public void AtiraOnde(long tempoalvo, Posicao posalvoini) {
		int ytiro;
		long tempoatual=System.currentTimeMillis();
		long posatual= this.AlvoAtual.getPosatt().getY();
		ytiro= (int) (((-325600-(posatual^2)+(2*posatual*590))/((2*posatual)-(2*590))));
		if(posalvoini.getX()<300) {
		
		this.tirofin=new Posicao(150,ytiro);
		}
		else 

		this.tirofin=new Posicao(450,ytiro);
		
	}

	public void atirar() {
			if( this.preparado) {
				if(this.carrvazio==false) {
			this.carregador.remove(carregador.size()-1);
			 this.AlvoAtual.setMirado(true);
			 tiro.start();
			 this.tiro=new Tiro();
			 this.AlvoAtual=null;
			 this.setMirado(false);
				}
				
			}

		}

	public Alvos getAlvoAtual() {
		return AlvoAtual;
	}

	public void setAlvoAtual(Alvos alvoAtual) {
		this.AlvoAtual = alvoAtual;


	}
	public boolean isMirado() {
		return mirado;
	}

	public void setMirado(boolean mirado) {
		this.mirado = mirado;
	}
	public void addmunicao() {
		this.carregador.add(new Municao());
		
	


		
		
		
		
	}

	public void run() {
		
		while(true) {
			try {
				semaforo.acquire();
				try {
					
					    Preparar();
						Carregar();
						atirar();
					
					
			
					
				}
				catch(Exception e) {
					e.printStackTrace();
				}
			}
			catch(InterruptedException e) {
				e.printStackTrace();
			}
			
		finally {
			semaforo.release();

		}
		
	}

	
	
	}

	
}
