package logika;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Tocka {
	
	public int x;
	
	public int y;
	
	public int xx;
	
	public	 int yy;
	

	protected String ime;
	
	public String zasedenost; // Kdo je zasedel mesto
	
	public Map<String,String> sosedi; // Mesta zasedena okoli točke

	
	public Tocka (String ime , int x, int y) {
		this.ime = ime;
		this.x = x;
		this.y = y;
		this.zasedenost = null;
		this.xx =0;
		this.yy=0;
		sosedi = new HashMap<String,String>();
		sosedi.put("Levo", "Prosto");
		sosedi.put("Desno", "Prosto");
		sosedi.put("Gor", "Prosto");
		sosedi.put("Dol", "Prosto");
	}

	@Override
	public String toString() {
		return ime;
	}
}
