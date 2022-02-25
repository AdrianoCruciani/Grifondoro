package openweather;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.BufferedReader;
import java.io.FileReader;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.Iterator;
import java.util.Scanner;

import javax.swing.JPanel;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 
 * Classe controller per il programma
 * 
 * @author DanieleCocciGrifoni, AdrianoCruciani
 */

@Controller
public class RequestController {
	
	/**
	 * Servizio su cui si basa il programma
	*/
	
	/**
	 * Creo un oggetto di tipo 'OpenWeather' per usare le sue funzionalità
	*/
	@Autowired
	private OpenWeather ow = new OpenWeather();
	
	/**
	 * Rotta che mostra le informazioni meteo relative a umidità, temperatura effettiva e
	 * temperatura percepita della città inserita da utente tramite coordinate
	 * @param  String lat
	 * @param  String lon
	 * @return ResponseEntity<String> contenente la stringa Json con i della città e le info meteo se trovate, 
	 * altrimenti un avviso che la ricerca della città non è andata a buon fine
	**/
	@RequestMapping(value = "/getWeatherByCoordinates")
	public ResponseEntity<String> getWeatherByCoordinates(@RequestParam(name = "lat") String lat, @RequestParam(name = "lon") String lon){
		if(!ow.Query(lat,lon))
			return new ResponseEntity<>("No city with lat="+lat+", lon="+lon+" found", HttpStatus.BAD_REQUEST);
		
		JSONObject ret = new JSONObject();
		JSONObject city = new JSONObject();
		JSONObject weather = new JSONObject();
		
		city.put("name", ow.GetRubData().GetName());
		city.put("id", ow.GetRubData().GetId());
		city.put("lat", ow.GetRubData().GetLat());
		city.put("lon", ow.GetRubData().GetLon());
		
		weather.put("humidity", Float.toString(ow.GetWeatherData().GetHumidity()));
		weather.put("temp", Float.toString(ow.GetWeatherData().GetTemp()));
		weather.put("feels_like", Float.toString(ow.GetWeatherData().GetFeels_like()));
		
		ret.put("city", city);
		ret.put("weather", weather);
		
		return new ResponseEntity<>(ret.toJSONString(), HttpStatus.OK);
	}
	
	/**
	 * 
	 * Rotta per generare delle statistiche richieste dall'utente sui dati raccolti dal servizio filtrate per un certo periodo
	 * 
	 * @param  String lat
	 * @param  String lon
	 * @param  String start
	 * @param  String end
	 * @return ResponseEntity<String> contenente la stringa Json con i dati della città, il weather avarage, la weather variance, i massimi e i minimi di interesse; 
	 * se no che non esiste la città con le coordinate inserite o che la data filtrata non è corretta
	*/
	
	@RequestMapping(value = "/getPeriodWeatherByCoordinates")
	public ResponseEntity<String> getPeriodWeatherByCoordinates(@RequestParam(name = "lat") String lat, @RequestParam(name = "lon") String lon, @RequestParam(name = "start") String start, @RequestParam(name = "end") String end){
		if(!ow.Query(lat,lon))
			return new ResponseEntity<>("No city with lat="+lat+", lon="+lon+" found", HttpStatus.BAD_REQUEST);

		LocalDate startd = LocalDate.parse(start, DateTimeFormatter.ISO_LOCAL_DATE);//yyyy-mm-dd
		LocalDate endd = LocalDate.parse(end, DateTimeFormatter.ISO_LOCAL_DATE);//yyyy-mm-dd
		if(!ow.CalculateAverage(startd, endd)) 
			return new ResponseEntity<>("Date format wrong!", HttpStatus.BAD_REQUEST);
		else {
			JSONObject ret = new JSONObject();
			JSONObject city = new JSONObject();
			JSONObject aweather = new JSONObject();
			JSONObject weatherv = new JSONObject();
			JSONObject max = new JSONObject();
			JSONObject min = new JSONObject();
			
			city.put("name", ow.GetRubData().GetName());
			city.put("id", ow.GetRubData().GetId());
			city.put("lat", ow.GetRubData().GetLat());
			city.put("lon", ow.GetRubData().GetLon());
			
			aweather.put("average_humidity", Float.toString(ow.GetStatisticData().GetAverageWeatherData().GetHumidity()));
			aweather.put("average_temp", Float.toString(ow.GetStatisticData().GetAverageWeatherData().GetTemp()));
			aweather.put("average_feels_like", Float.toString(ow.GetStatisticData().GetAverageWeatherData().GetFeels_like()));
			
			weatherv.put("humidity_variance", Float.toString(ow.GetStatisticData().GetVarianceWeatherData().GetHumidity()));
			weatherv.put("temp_variance", Float.toString(ow.GetStatisticData().GetVarianceWeatherData().GetTemp()));
			weatherv.put("feels_like_variance", Float.toString(ow.GetStatisticData().GetVarianceWeatherData().GetFeels_like()));
			
			max.put("humidity_variance", Float.toString(ow.GetStatisticData().GetMaxWeatherData().GetHumidity()));
			max.put("temp_variance", Float.toString(ow.GetStatisticData().GetMaxWeatherData().GetTemp()));
			max.put("feels_like_variance", Float.toString(ow.GetStatisticData().GetMaxWeatherData().GetFeels_like()));
			
			min.put("humidity_variance", Float.toString(ow.GetStatisticData().GetMinWeatherData().GetHumidity()));
			min.put("temp_variance", Float.toString(ow.GetStatisticData().GetMinWeatherData().GetTemp()));
			min.put("feels_like_variance", Float.toString(ow.GetStatisticData().GetMinWeatherData().GetFeels_like()));
			
			ret.put("city", city);
			ret.put("average_weather", aweather);
			ret.put("weather_variance", weatherv);
			ret.put("max", max);
			ret.put("min", min);
		
			return new ResponseEntity<>(ret.toJSONString(), HttpStatus.OK);
		}
	}
	
	/**
	 * Rotta aggiuntiva che mostra le informazioni meteo relative a umidità, temperatura effettiva e
	 * temperatura percepita della città inserita da utente tramite nome
	 *
	 * @param name String che rappresenta il nome della città di cui si richiedono le previsioni
	 * @return ResponseEntity<String> contenente la stringa Json con i dati relativi alla città ricercata e le previsioni meteo se trovata o un avviso che il nome della città non esiste
	 * */
	
	@RequestMapping(value = "/getWeatherByName")
	public ResponseEntity<String> getWeatherByName(@RequestParam(name = "name") String name){
		if(!Search("name", name))
			return new ResponseEntity<>("No city called "+name+" found!", HttpStatus.BAD_REQUEST);

		JSONObject ret = new JSONObject();
		JSONObject city = new JSONObject();
		JSONObject weather = new JSONObject();
		
		city.put("name", ow.GetRubData().GetName());
		city.put("id", ow.GetRubData().GetId());
		city.put("lat", ow.GetRubData().GetLat());
		city.put("lon", ow.GetRubData().GetLon());
		
		weather.put("humidity", Float.toString(ow.GetWeatherData().GetHumidity()));
		weather.put("temp", Float.toString(ow.GetWeatherData().GetTemp()));
		weather.put("feels_like", Float.toString(ow.GetWeatherData().GetFeels_like()));
		
		ret.put("city", city);
		ret.put("weather", weather);
		
		return new ResponseEntity<>(ret.toJSONString(), HttpStatus.OK);
	}
		
	/**
	 * 
	 * Rotta per generare delle statistiche richieste dall'utente sui dati raccolti dal servizio filtrate per un certo periodo riferite alla città ricercata per nome
	 * 
	 * @param  String name
	 * @param  String start
	 * @param  String end
	 * @return ResponseEntity<String> contenente la stringa Json con i dati della città, il weather avarage, la weather variance, i massimi e i minimi di interesse; 
	 * se no che non esiste la città con il nome richiesto o che la data filtrata non è corretta
	*/
	
	@RequestMapping(value = "/getPeriodWeatherByName")
	public ResponseEntity<String> getPeriodWeatherByName(@RequestParam(name = "name") String name, @RequestParam(name = "start") String start, @RequestParam(name = "end") String end){
		if(!Search("name", name))
			return new ResponseEntity<>("No city called "+name+" found!", HttpStatus.BAD_REQUEST);

		LocalDate startd = LocalDate.parse(start, DateTimeFormatter.ISO_LOCAL_DATE);//yyyy-mm-dd
		LocalDate endd = LocalDate.parse(end, DateTimeFormatter.ISO_LOCAL_DATE);//yyyy-mm-dd
		if(!ow.CalculateAverage(startd, endd)) 
			return new ResponseEntity<>("Date format wrong!", HttpStatus.BAD_REQUEST);
		else {
			JSONObject ret = new JSONObject();
			JSONObject city = new JSONObject();
			JSONObject aweather = new JSONObject();
			JSONObject weatherv = new JSONObject();
			JSONObject max = new JSONObject();
			JSONObject min = new JSONObject();
			
			city.put("name", ow.GetRubData().GetName());
			city.put("id", ow.GetRubData().GetId());
			city.put("lat", ow.GetRubData().GetLat());
			city.put("lon", ow.GetRubData().GetLon());
			
			aweather.put("average_humidity", Float.toString(ow.GetStatisticData().GetAverageWeatherData().GetHumidity()));
			aweather.put("average_temp", Float.toString(ow.GetStatisticData().GetAverageWeatherData().GetTemp()));
			aweather.put("average_feels_like", Float.toString(ow.GetStatisticData().GetAverageWeatherData().GetFeels_like()));
			
			weatherv.put("humidity_variance", Float.toString(ow.GetStatisticData().GetVarianceWeatherData().GetHumidity()));
			weatherv.put("temp_variance", Float.toString(ow.GetStatisticData().GetVarianceWeatherData().GetTemp()));
			weatherv.put("feels_like_variance", Float.toString(ow.GetStatisticData().GetVarianceWeatherData().GetFeels_like()));
			
			max.put("humidity_variance", Float.toString(ow.GetStatisticData().GetMaxWeatherData().GetHumidity()));
			max.put("temp_variance", Float.toString(ow.GetStatisticData().GetMaxWeatherData().GetTemp()));
			max.put("feels_like_variance", Float.toString(ow.GetStatisticData().GetMaxWeatherData().GetFeels_like()));
			
			min.put("humidity_variance", Float.toString(ow.GetStatisticData().GetMinWeatherData().GetHumidity()));
			min.put("temp_variance", Float.toString(ow.GetStatisticData().GetMinWeatherData().GetTemp()));
			min.put("feels_like_variance", Float.toString(ow.GetStatisticData().GetMinWeatherData().GetFeels_like()));
			
			ret.put("city", city);
			ret.put("average_weather", aweather);
			ret.put("weather_variance", weatherv);
			ret.put("max", max);
			ret.put("min", min);
		
			return new ResponseEntity<>(ret.toJSONString(), HttpStatus.OK);
		}
	}
	
	/**
	 * Rotta aggiuntiva che mostra le informazioni meteo relative a umidità, temperatura effettiva e
	 * temperatura percepita della città inserita da utente tramite ID
	 *
	 * @param id String, che rappresenta l'ID della città di cui si richiedono le previsioni
	 * @return ResponseEntity<String> contenente la stringa Json con i dati relativi alla città ricercata e le previsioni meteo se trovata o un avviso che l'ID della città non esiste
	* */
	
	@RequestMapping(value = "/getWeatherById")
	public ResponseEntity<String> getWeatherById(@RequestParam(name = "id") String id){
		if(!Search("id", id))
			return new ResponseEntity<>("No city with id:"+id+" found!", HttpStatus.BAD_REQUEST);
		
		JSONObject ret = new JSONObject();
		JSONObject city = new JSONObject();
		JSONObject weather = new JSONObject();
		
		city.put("name", ow.GetRubData().GetName());
		city.put("id", ow.GetRubData().GetId());
		city.put("lat", ow.GetRubData().GetLat());
		city.put("lon", ow.GetRubData().GetLon());
		
		weather.put("humidity", Float.toString(ow.GetWeatherData().GetHumidity()));
		weather.put("temp", Float.toString(ow.GetWeatherData().GetTemp()));
		weather.put("feels_like", Float.toString(ow.GetWeatherData().GetFeels_like()));
		
		ret.put("city", city);
		ret.put("weather", weather);
		
		return new ResponseEntity<>(ret.toJSONString(), HttpStatus.OK);
	}
	
	/**
	 * 
	 * Rotta per generare delle statistiche richieste dall'utente sui dati raccolti dal servizio filtrate per un certo periodo riferite alla città ricercata per ID
	 * 
	 * @param  String id
	 * @param  String start
	 * @param  String end
	 * @return ResponseEntity<String> contenente la stringa Json con i dati della città, il weather avarage, la weather variance, i massimi e i minimi di interesse; 
	 * se no che non esiste la città con l'ID richiesto o che la data filtrata non è corretta
	*/
	
	@RequestMapping(value = "/getPeriodWeatherById")
	public ResponseEntity<String> getPeriodWeatherById(@RequestParam(name = "id") String id, @RequestParam(name = "start") String start, @RequestParam(name = "end") String end){
		if(!Search("id", id))
			return new ResponseEntity<>("No city with id:"+id+" found!", HttpStatus.BAD_REQUEST);

		LocalDate startd = LocalDate.parse(start, DateTimeFormatter.ISO_LOCAL_DATE);//yyyy-mm-dd
		LocalDate endd = LocalDate.parse(end, DateTimeFormatter.ISO_LOCAL_DATE);//yyyy-mm-dd
		if(!ow.CalculateAverage(startd, endd)) 
			return new ResponseEntity<>("Date format wrong!", HttpStatus.BAD_REQUEST);
		else {
			JSONObject ret = new JSONObject();
			JSONObject city = new JSONObject();
			JSONObject aweather = new JSONObject();
			JSONObject weatherv = new JSONObject();
			JSONObject max = new JSONObject();
			JSONObject min = new JSONObject();
			
			city.put("name", ow.GetRubData().GetName());
			city.put("id", ow.GetRubData().GetId());
			city.put("lat", ow.GetRubData().GetLat());
			city.put("lon", ow.GetRubData().GetLon());
			
			aweather.put("average_humidity", Float.toString(ow.GetStatisticData().GetAverageWeatherData().GetHumidity()));
			aweather.put("average_temp", Float.toString(ow.GetStatisticData().GetAverageWeatherData().GetTemp()));
			aweather.put("average_feels_like", Float.toString(ow.GetStatisticData().GetAverageWeatherData().GetFeels_like()));
			
			weatherv.put("humidity_variance", Float.toString(ow.GetStatisticData().GetVarianceWeatherData().GetHumidity()));
			weatherv.put("temp_variance", Float.toString(ow.GetStatisticData().GetVarianceWeatherData().GetTemp()));
			weatherv.put("feels_like_variance", Float.toString(ow.GetStatisticData().GetVarianceWeatherData().GetFeels_like()));
			
			max.put("humidity_variance", Float.toString(ow.GetStatisticData().GetMaxWeatherData().GetHumidity()));
			max.put("temp_variance", Float.toString(ow.GetStatisticData().GetMaxWeatherData().GetTemp()));
			max.put("feels_like_variance", Float.toString(ow.GetStatisticData().GetMaxWeatherData().GetFeels_like()));
			
			min.put("humidity_variance", Float.toString(ow.GetStatisticData().GetMinWeatherData().GetHumidity()));
			min.put("temp_variance", Float.toString(ow.GetStatisticData().GetMinWeatherData().GetTemp()));
			min.put("feels_like_variance", Float.toString(ow.GetStatisticData().GetMinWeatherData().GetFeels_like()));
			
			ret.put("city", city);
			ret.put("average_weather", aweather);
			ret.put("weather_variance", weatherv);
			ret.put("max", max);
			ret.put("min", min);
		
			return new ResponseEntity<>(ret.toJSONString(), HttpStatus.OK);
		}
	}
	
	/**
	 * Rotta per avviare una ricerca: controlla se la request(String), contentente coordinate, nome o id, coincida con toFind(String)
	 * 
	 * @param String request, String toFind
	 * @return ritornerà un tipo bool per indicare se è stato trovato o meno il parametro richiesto
	*/
	
	private boolean Search(String request, String toFind){
		JSONObject file = new JSONObject();
		JSONArray list = new JSONArray();
		JSONParser parser = new JSONParser();
    	
		try{
			Scanner in = new Scanner(new BufferedReader(new FileReader("src/config.json")));
	        String input = in.nextLine();
	        in.close();
	        file = (JSONObject) parser.parse(input);
	        
	        String dBPath = new String(file.get("path_rubrica").toString());
	        
	        boolean found = false;
        
            in = new Scanner(new BufferedReader(new FileReader(dBPath)));
            input = in.nextLine();
            in.close();
            file = (JSONObject) parser.parse(input);
            
            JSONObject objt = new JSONObject();
            JSONObject coord = new JSONObject();
            
            list = (JSONArray) file.get("lista");
            Iterator<JSONObject> iterator = list.iterator();
            while (iterator.hasNext()) {
                objt = iterator.next();
                if(toFind.equals(objt.get(request).toString())){
                    found = true;
                    coord = (JSONObject) objt.get("coord");
                    return ow.Query(coord.get("lat").toString(),coord.get("lon").toString());
                }
            }
        }catch(Exception e){}
        
        return false;
    }
}
