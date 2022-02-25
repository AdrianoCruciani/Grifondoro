package openweather;

/**
 * 
 * Classe che contiene i dati elaborati dalla nostra classe OpenWeather
 * 
 * @author DanieleCocciGrifoni, AdrianoCruciani
 */

public class StatisticData {
	
	//parametri statistici
	
    private WeatherData maxd;
    private WeatherData mind;
    private WeatherData ad;
    private WeatherData vd;
    
	/**
	 * 
	 * Costruttore per i dati statistici
	 * 
	*/
	
    public StatisticData(){
        maxd = new WeatherData();
        mind = new WeatherData();
        ad = new WeatherData();
        vd = new WeatherData();
    }
    
	//Getters and Setters
	
    public WeatherData GetMaxWeatherData(){return maxd;}
    public WeatherData GetMinWeatherData(){return mind;}
    public WeatherData GetAverageWeatherData(){return ad;}
    public WeatherData GetVarianceWeatherData(){return vd;}
    
    public void SetMaxWeatherData(WeatherData maxd){this.maxd = maxd;}
    public void SetMinWeatherData(WeatherData mind){this.mind = mind;}
    public void SetAverageWeatherData(WeatherData ad){this.ad = ad;}
    public void SetVarianceWeatherData(WeatherData vd){this.vd = vd;}
}
