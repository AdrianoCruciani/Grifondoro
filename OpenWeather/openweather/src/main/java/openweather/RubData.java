package openweather;

/**
 * 
 * (Dizionario), sono i dati in rubrica
 * 
 * @author DanieleCocciGrifoni, AdrianoCruciani
 */

public class RubData {    

	//parametri
	
    private String name = new String();
    private String id = new String();
    private String lat = new String();
    private String lon = new String();
 
    
	/**
	 * 
	 * Costruttore per i parametri
	 * 
	 */
	
    public RubData(String name,
                   String id,
                   String lat,
                   String lon){
        this.name = name;
        this.id = id;
        this.lat = lat;
        this.lon = lon;
    }
    
    public RubData(){
        name = "";
        id = "";
        lat = "";
        lon = "";
	}
    
	//Getters and Setters
	
    public String GetName(){return name;}
    public String GetId(){return id;}
    public String GetLat(){return lat;}
    public String GetLon(){return lon;}
    
    public void SetName(String name){this.name = name;}
    public void SetId(String id){this.id = id;}
    public void SetLat(String lat){this.lat = lat;}
    public void SetLon(String lon){this.lon = lon;}
}
