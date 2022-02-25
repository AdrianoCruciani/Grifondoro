package openweather;

/**
 * 
 * Classe che contiene i dati prelevati dal server "OpenWeather" che poi verranno visualizzati e successivamente eleborati
 * 
 * @author DanieleCocciGrifoni, AdrianoCruciani
*/

public class WeatherData { 

	//Data from server
	
    private float humidity;
    private float temp;
    private float feels_like;
    
	/**
	 * 
	 * Costruttore per i dati prelevati dal server
	 * 
	*/
	
    public WeatherData(float humidity,
                   float temp,
                   float feels_like){
        this.humidity = humidity;
        this.temp = temp;
        this.feels_like = feels_like;
    }
    
    public WeatherData(){
        humidity = 0;
        temp = 0;
        feels_like = 0;
    }
    
	//Getters and Setters
	
    public float GetHumidity(){return humidity;}
    public float GetTemp(){return temp;}
    public float GetFeels_like(){return feels_like;}
    
    public void SetHumidity(float humidity){this.humidity = humidity;}
    public void SetTemp(float temp){this.temp = temp;}
    public void SetFeels_like(float feels_like){this.feels_like = feels_like;}
}
