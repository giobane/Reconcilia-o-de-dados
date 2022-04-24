import java.util.ArrayList;

public class GeraAlvo extends Thread {
	private ArrayList<Alvos> alvos1= new ArrayList<Alvos>();
	private ArrayList<Alvos> alvos2=new ArrayList<Alvos>();
	private ArrayList<Lancador> lancadores=new ArrayList<Lancador>();
	private ArrayList<Tiro> tiros=new ArrayList<Tiro>();
	private int p=0;
	
	
	
	public void addalvo() {
		try {
			this.sleep(100);
		
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
    	alvos1.add(new Alvos(new Posicao(150,0), new Posicao(150,600)));

    	if(p<5) {
    		lancadores.add(new Lancador(alvos1.get(alvos1.size()-1)));
    		lancadores.get(p).setIdlan(p);
    		tiros.add(lancadores.get(lancadores.size()-1).getTiro());
    		tiros.get(tiros.size()-1).setId(lancadores.size()-1);
    		p++;
    	}
    	else {
    		for(int i=0; i<lancadores.size(); i++) {
    			if (lancadores.get(i).isMirado()==false) {
    				lancadores.get(i).setMirado(true);
    				lancadores.get(i).setAlvoAtual(alvos1.get(alvos1.size()-1));
    				tiros.add(lancadores.get(i).getTiro());
    				tiros.get(tiros.size()-1).setId(i);

    				break;
    			}
    		}
    	}
    	try {
			this.sleep(100);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
    	alvos2.add(new Alvos(new Posicao(450,0), new Posicao(450,600)) );
    	if(p<5) {
    		lancadores.add(new Lancador(alvos2.get(alvos2.size()-1)));
    		lancadores.get(p).setIdlan(p);
    		tiros.add(lancadores.get(lancadores.size()-1).getTiro());
    		tiros.get(tiros.size()-1).setId(lancadores.size()-1);
    		p++;
    	}
    	else {
    		for(int i=0; i<lancadores.size(); i++) {
    			if (lancadores.get(i).isMirado()==false) {
    				lancadores.get(i).setMirado(true);
    				lancadores.get(i).setAlvoAtual(alvos2.get(alvos2.size()-1));
    				tiros.add(lancadores.get(i).getTiro());
    				tiros.get(tiros.size()-1).setId(i);
    				break;
    			}
    		}
    	}
	}
	public ArrayList<Alvos> getAlvos1() {
		return alvos1;
	}

	public void setAlvos1(ArrayList<Alvos> alvos1) {
		this.alvos1 = alvos1;
	}

	public ArrayList<Alvos> getAlvos2() {
		return alvos2;
	}

	public void setAlvos2(ArrayList<Alvos> alvos2) {
		this.alvos2 = alvos2;
	}
	
	public void run() {
		//alvos1.add(new Alvos(new Posicao(150,0), new Posicao(150,600)));
		while (true){
			
			addalvo();
		}
	}
	public ArrayList<Lancador> getLancadores() {
		return lancadores;
	}
	public void setLancadores(ArrayList<Lancador> lancadores) {
		this.lancadores = lancadores;
	}
	public ArrayList<Tiro> getTiros() {
		return tiros;
	}
	public void setTiros(ArrayList<Tiro> tiros) {
		this.tiros = tiros;
	}
	public int getP() {
		return p;
	}
	public void setP(int p) {
		this.p = p;
	}

	

}
