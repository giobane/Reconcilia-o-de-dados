import java.util.ArrayList;
public class Alvos extends Thread{
	private long id;
	private static long l=0;
	private Posicao posin;
	private Posicao posfin;
	private Posicao posatt;
	private int npontos;
	private int passo;
	private int tmax;
	private int tponto;
	private double min=1200;
	private double max=1600;
	private long timestamp;
	private int freqatt;
	private int k;
	private int kant;
	private int move;
	private int[] vecvel=new int[] {0, 0, 0, 0, 0};
	private double[] vectemp=new double[] {0, 0, 0, 0, 0, 7000};
	private double[] dp= new double[] {0.1, 0.1, 0.1, 0.1, 0.1, 0.01};
	private double[] v= new double[] { 0, 0, 0, 0, 0, 0};
	private double[] A= new double[] { 1, 1, 1, 1, 1, -1} ;
	private boolean chegou=false;
	private boolean atingido=false;
	private boolean mirado=false;
	
	
	public Alvos (Posicao posin, Posicao posfin) {
		this.id=Alvos.l;
		Alvos.l++;
		this.posin=posin;
		this.posfin=posfin;
		this.posatt=posin;
		this.freqatt=30;
		this.timestamp=System.currentTimeMillis();
		this.npontos=5;
		this.passo=600/npontos;
		this.tmax=7000;
		this.tponto=tmax/npontos;
		this.k=-1;
		this.kant=0;
		this.move=0;
		
		for(int i=0;i<5;i++) {
			this.vectemp[i]=Math.floor(Math.random()*(max-min+1)+min);
			this.v[i]=this.dp[i]*this.dp[i];
			System.out.println("Tempo: " + (i+1) + " "+ this.vectemp[i]);
		}
			System.out.println("Tempo ultimo " + this.vectemp[5]);
			this.v[5]=this.dp[5]*this.dp[5];
		
		start();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Posicao getPosin() {
		return posin;
	}

	public void setPosin(Posicao posin) {
		this.posin = posin;
	}

	public Posicao getPosfin() {
		return posfin;
	}

	public void setPosfin(Posicao posfin) {
		this.posfin = posfin;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public long getFreqatt() {
		return freqatt;
	}

	public void setFreqatt(int freqatt) {
		this.freqatt = freqatt;
	}

	public boolean isMirado() {
		return mirado;
	}

	public void setMirado(boolean mirado) {
		this.mirado = mirado;
	}

	public Posicao getPosatt() {
		return posatt;
	}

	public void setPosatt(Posicao posatt) {
		this.posatt = posatt;
	}

	public boolean isChegou() {
		return chegou;
	}

	public void setChegou(boolean chegou) {
		this.chegou = chegou;
	}

	public boolean isAtingido() {
		return atingido;
	}

	public void setAtingido(boolean atingido) {
		this.atingido = atingido;
	}
	
	public void moveralvo() {
		if (this.posatt.getY()>=600) {
			this.chegou=true;
			System.out.println(System.currentTimeMillis()-timestamp);
		}
		else {
			kant=k;
			k=(this.posatt.getY()/this.passo) ;
			Reconciliation rec=new Reconciliation(vectemp,v,A);
			rec.printMatrix(rec.getReconciledFlow());
			if(kant!=k) {
			this.move=(int)(30*this.passo/(rec.getReconciledFlow()[k]));

			}
			this.posatt.setY(this.posatt.getY() + move);
			
	}
	}
	public void run() {
		while(true) {
			
			try {
				this.sleep(freqatt);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			moveralvo();
			if(isAtingido() || isChegou()) {
				break;
			}
		}
		
		
	}
	
}
