
public class Tiro extends Thread {
	private int id;
	private Alvos alvoatual;
	private Posicao posin;
	private Posicao posfin;
	private Posicao posatt;
	private int posattalvo;
	private int posantalvo;
	private long timestamp;
	private long freqatt;
	private boolean contato=false;
	private Posicao tirofin;
	
	public Tiro() {
		this.posin=new Posicao(300, 560);
		this.posatt=posin;
		this.timestamp=System.currentTimeMillis();
		this.freqatt=30;
		this.posattalvo=0;
	}
	
	
	public int getIdd() {
		return id;
	}

	public void setId(int id) {
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

	public Posicao getPosatt() {
		return posatt;
	}

	public void setPosatt(Posicao posatt) {
		this.posatt = posatt;
	}

	public long getFreqatt() {
		return freqatt;
	}

	public void setFreqatt(long freqatt) {
		this.freqatt = freqatt;
	}

	public boolean isContato() {
		return contato;
	}

	public void setContato(boolean contato) {
		this.contato = contato;
	}
	public long getTimestamp() {
		return timestamp;
	}


	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	


	public void trajeto(Posicao tirofin) {
		long catetox=150;
		long catetoy=tirofin.getY();
		double ang=Math.atan(catetoy/catetox);
		this.posantalvo=this.posattalvo;
		try {
			this.sleep(30);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.posattalvo=alvoatual.getPosatt().getY();
		long movex=(long) ((Math.cos(ang))*(this.posattalvo-this.posantalvo));
		long movey=(long) ((Math.sin(ang))*(this.posattalvo-this.posantalvo));
		System.out.println("X: " + movex + " Y: "+ movey);
		if (tirofin.getX()<300) {
			getPosatt().setX((int) (getPosatt().getX() - movex));
			getPosatt().setY((int) (getPosatt().getY() - movey));
		}
		else {
			getPosatt().setX((int) (getPosatt().getX() + movex));
			getPosatt().setY((int) (getPosatt().getY() - movey));
		}
	}
	
	public void run() {
		while(true) {
			trajeto(tirofin);
			if(isContato()) {
				break;
			}
			
		}
		
		
	}


	public Posicao getTirofin() {
		return tirofin;
	}


	public void setTirofin(Posicao tirofin, Alvos alvoatual) {
		this.tirofin = tirofin;
		this.alvoatual=alvoatual;
	}


	
}












