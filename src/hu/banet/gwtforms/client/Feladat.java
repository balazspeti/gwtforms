package hu.banet.gwtforms.client;


public class Feladat {
	
  
  private String azon;
	private String kulcs;
  private String tema;
  private String nev;
  private String leiras;
  private String statusz;
  private String prioritas;
  private String letrehozo;
  private String megoldo;
	
  
	public Feladat(String azon, 
                 String kulcs, 
                 String tema,
                 String nev,
                 String leiras,
                 String statusz,
                 String prioritas,
                 String letrehozo,
                 String megoldo) {
		this.azon      = azon;
    this.kulcs     = kulcs;
    this.tema      = tema;
    this.nev       = nev;
    this.leiras    = leiras;
    this.statusz   = statusz;
    this.prioritas = prioritas;
    this.letrehozo = letrehozo;
    this.megoldo   = megoldo;
  }
	

  public String getAzon() {
    return this.azon;
  }
 
  
  public String getKulcs() {
    return this.kulcs;
  }
 
  
  public String getTema() {
    return this.tema;
  }
 
  
  public String getNev() {
    return this.nev;
  }
 
  
  public String getLeiras() {
    return this.leiras;
  }
 
  
  public String getStatusz() {
    return this.statusz;
  }
 
  
  public String getPrioritas() {
    return this.prioritas;
  }
 
  
  public String getLetrehozo() {
    return this.letrehozo;
  }
 
  
  public String getMegoldo() {
    return this.megoldo;
  }
 
 
  public void setAzon(String azon) {
    this.azon = azon;
  }
  
  
  public void setKulcs(String kulcs) {
    this.kulcs = kulcs;
  }
  
  
  public void setTema(String tema) {
    this.tema = tema;
  }
  
  
  public void setNev(String nev) {
    this.nev = nev;
  }
  
  
  public void setLeiras(String leiras) {
    this.leiras = leiras;
  }
  
  
  public void setStatusz(String statusz) {
    this.statusz = statusz;
  }
  
  
  public void setPrioritas(String prioritas) {
    this.prioritas = prioritas;
  }
  
  
  public void setLetrehozo(String letrehozo) {
    this.letrehozo = letrehozo;
  }
  
  
  public void setMegoldo(String megoldo) {
    this.megoldo = megoldo;
  }
  
  
}
