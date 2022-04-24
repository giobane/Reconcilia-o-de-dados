import java.util.ArrayList;

public class main {
	public static void main(String[] args) throws Exception {
		Interface jogo = new Interface();
		Thread j=new Thread(jogo);
        j.start();
		

}
}
