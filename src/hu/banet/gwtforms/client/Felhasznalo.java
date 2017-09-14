package hu.banet.gwtforms.client;


public class Felhasznalo {
	
  
  private String nev;
	private String tasz;
	
  
	public Felhasznalo(String nev, 
                     String tasz) {
		this.nev  = nev;
    this.tasz = tasz;
  }
 
  
  public String getNev() {
    return nev;
  }
 
  
  public String getTasz() {
    return tasz;
  }
  
  
  public void setNev(String nev) {
    this.nev = nev;
  }
  
  
  public void setTasz(String tasz) {
    this.tasz = tasz;
  }
  
  
}
