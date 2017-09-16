package hu.banet.gwtforms.client;


public class Prioritas {
	
  
  private String kod;
	private String nev;
	
  
	public Prioritas(String kod, 
                   String nev) {
		this.kod = kod;
    this.nev = nev;
  }
 
  
  public String getKod() {
    return kod;
  }
 
  
  public String getNev() {
    return nev;
  }
  
  
  public void setKod(String kod) {
    this.kod = kod;
  }
  
  
  public void setNev(String nev) {
    this.nev = nev;
  }
  
  
}
