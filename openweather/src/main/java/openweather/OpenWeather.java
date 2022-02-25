package openweather;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.junit.*;
import org.springframework.stereotype.Component;

/**
 * 
 * Classe principale che gestisce le query, il salvataggio e l'analisi dei dati statistici 
 * 
 * @author DanieleCocciGrifoni, AdrianoCruciani
 */

@Component
public class OpenWeather {
	
    private RubData rd = new RubData();
    private WeatherData wd = new WeatherData();
    private StatisticData sd = new StatisticData();
    private String dBPath = new String();	
    private String SDPath = new String(); 	
    private String appid = new String();	
    
    //Costruttore
	
    public OpenWeather() {
    	JSONObject file;
    	JSONParser parser = new JSONParser();
    	
    	try{
			Scanner in = new Scanner(new BufferedReader(new FileReader("src/config.json")));
            String input = in.nextLine();
            in.close();
            file = (JSONObject) parser.parse(input);
            
            dBPath = file.get("path_rubrica").toString();		//percorso del DataBase, ossia la rubrica
            SDPath = file.get("path_dati_salvati").toString();	//percorso dati salvati
            appid = file.get("appid_OpenWeather").toString();	//API_KEY;
        }catch(Exception e){e.printStackTrace();}
    }
	
	/**
	 * Query che chiede dal server "OpenWeather" i dati meteorologici della città con latitudine iLat e longitudine iLon
	 * 
	 * @param String iLat, String iLon
	 * @return true se coincidono, false nel caso opposto
	*/
    
    public boolean Query(String iLat, String iLon){        
        String sURL = "https://api.openweathermap.org/data/2.5/weather?lat="+
                iLat + "&lon=" +
                iLon + "&appid=" +
                appid;
        
        // Connect to the URL using java's native library
        URL url;
        JSONObject jsonObject = null;
        
        try {
            JSONParser parser = new JSONParser();
            url = new URL(sURL);
            URLConnection request = url.openConnection();
            request.connect();

            jsonObject = (JSONObject) parser.parse(new InputStreamReader((InputStream) request.getContent()));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        
        JSONObject main = (JSONObject) jsonObject.get("main");
        JSONObject coord = (JSONObject) jsonObject.get("coord");
        
        wd = new WeatherData(Float.parseFloat(main.get("humidity").toString()),
                         Float.parseFloat(main.get("temp").toString()),
                         Float.parseFloat(main.get("feels_like").toString()));
        rd = new RubData(jsonObject.get("name").toString(),
                         jsonObject.get("id").toString(),
                         coord.get("lat").toString(),
                         coord.get("lon").toString());
          
        return true;
    }
    
	/**
	 * Metodo che salva in rubrica la città restituita dalla query se non è già presente
	 * 
	*/
	
    @Test
    public void SaveRubData(){
        boolean save;
        JSONParser parser = new JSONParser();
        JSONObject obj = new JSONObject();
        obj.put("name", rd.GetName());
        obj.put("id", rd.GetId());
        JSONObject coords = new JSONObject();
        coords.put("lat", rd.GetLat());
        coords.put("lon", rd.GetLon());
        obj.put("coord",coords);
        
        JSONObject fileRoot = new JSONObject();
        JSONArray list = new JSONArray();
        Object idt;
        
        save = true;
        
        //Search if already in the db
        try{
            Scanner in = new Scanner(new BufferedReader(new FileReader(dBPath)));
            String input = in.nextLine();
            in.close();
            fileRoot = (JSONObject) parser.parse(input);
            
            JSONObject objt = new JSONObject();

            list = (JSONArray) fileRoot.get("lista");
            Iterator<JSONObject> iterator = list.iterator();
            while(iterator.hasNext()){
                objt = iterator.next();
                idt = objt.get("id").toString();
                if(idt.equals(rd.GetId())){
                    save = false;
                    break;
                }
            }
        }catch(Exception e){}
        
        if(save){
            list.add(obj);
            try (FileWriter file = new FileWriter(dBPath)) {    
                fileRoot.put("lista",list);
                file.write(fileRoot.toJSONString());
                file.flush();
                file.close();
            } catch (IOException e) {}
        }
    }
    
	/**
	 * Metodo che salva i dati meteorologici della città
	 * 
	*/
	
    @Test
    public void SaveWeatherData(){
    	JSONObject fileRoot = new JSONObject();
    	JSONParser parser = new JSONParser();
        try{
            Scanner in = new Scanner(new BufferedReader(new FileReader(SDPath)));
            String input = in.nextLine();
            in.close();
            
            fileRoot = (JSONObject) parser.parse(input);
        }catch(Exception e){e.printStackTrace();}
        
        try{
            JSONObject toAdd = new JSONObject();
            toAdd.put("humidity", wd.GetHumidity());
            toAdd.put("temp", wd.GetTemp());
            toAdd.put("feels_like", wd.GetFeels_like());
            toAdd.put("date", LocalDate.now().toString());
            
            FileWriter file = new FileWriter(SDPath);
            JSONArray data;
            
            if(fileRoot.containsKey(rd.GetId())){
                data = (JSONArray) fileRoot.get(rd.GetId());
                data.add(toAdd);
            }else{
                data = new JSONArray();
                data.add(toAdd);
                fileRoot.put(rd.GetId(), data);
            }
            
            file.write(fileRoot.toJSONString());
            file.flush();
            file.close();
        }catch(Exception e){e.printStackTrace();}
    }
	
	/**
	 * Metodo per il calcolo statistico dei dati meteorologici delle città con periodo compreso tra "start" e "end"
	 * 
	 * @param LocalDate start, LocalDate end, data di inizio e di fine
	 * @return true or false
	*/

    public boolean CalculateAverage(LocalDate start, LocalDate end){
        JSONParser parser = new JSONParser();
        
        JSONObject fileRoot = new JSONObject();
        JSONArray rec = new JSONArray();
        
        WeatherData maxd = new WeatherData();
        WeatherData mind = new WeatherData();
        
        float nTmp;
        
        float nTemp = 0;
        float nHumidity = 0;
        float nFeels_like = 0;
        
        LocalDate tmp;
        
        boolean set = false; //controlla se max e min sono già stati inizializzati al primo elemento idoneo nel file
        try{
            Scanner in = new Scanner(new BufferedReader(new FileReader(SDPath)));
            String input = in.nextLine();
            fileRoot = (JSONObject) parser.parse(input);
            in.close();
            
            JSONObject objt = new JSONObject();
            
            rec = (JSONArray) fileRoot.get(rd.GetId().toString());
            
            List<WeatherData> outList = new ArrayList();
            Iterator<JSONObject> iterator = rec.iterator();
            while (iterator.hasNext()) {
                objt = iterator.next();
                tmp = LocalDate.parse(objt.get("date").toString(), DateTimeFormatter.ISO_LOCAL_DATE) ;//yyyy-mm-dd
                
                if(tmp.getYear() > end.getYear()) continue;
                if(tmp.getYear() < start.getYear()) continue;
                if(tmp.getMonthValue() > end.getMonthValue()) continue;
                if(tmp.getMonthValue() < start.getMonthValue()) continue;
                if(tmp.getDayOfMonth() > end.getDayOfMonth()) continue;
                if(tmp.getDayOfMonth() < start.getDayOfMonth()) continue;
                
                wd = new WeatherData(Float.parseFloat(objt.get("humidity").toString()),
                                     Float.parseFloat(objt.get("temp").toString()),
                                     Float.parseFloat(objt.get("feels_like").toString()));
                
                if(!set){
                    maxd.SetHumidity(wd.GetHumidity());
                    maxd.SetTemp(wd.GetTemp());
                    maxd.SetFeels_like(wd.GetFeels_like());

                    mind.SetHumidity(wd.GetHumidity());
                    mind.SetTemp(wd.GetTemp());
                    mind.SetFeels_like(wd.GetFeels_like());
                    
                    set = true;
                }else{
                    if(wd.GetHumidity() > maxd.GetHumidity()) maxd.SetHumidity(wd.GetHumidity());
                    if(wd.GetHumidity() < mind.GetHumidity()) mind.SetHumidity(wd.GetHumidity());
                    
                    if(wd.GetTemp() > maxd.GetTemp()) maxd.SetTemp(wd.GetTemp());
                    if(wd.GetTemp() < mind.GetTemp()) mind.SetTemp(wd.GetTemp());
                    
                    if(wd.GetFeels_like() > maxd.GetFeels_like()) maxd.SetFeels_like(wd.GetFeels_like());
                    if(wd.GetFeels_like() < mind.GetFeels_like()) mind.SetFeels_like(wd.GetFeels_like());
                }
                
                
                nTemp += wd.GetTemp();
                nHumidity += wd.GetHumidity();
                nFeels_like += wd.GetFeels_like();
                
                outList.add(wd);
            }
            
            if(outList.isEmpty()) //no elementi nel range
                return false;
            
            //salvataggio massimi
            sd.SetMaxWeatherData(maxd);
            
            //salvataggio minimi
            sd.SetMinWeatherData(mind);
            
            //salvataggio media
            wd.SetTemp(nTemp/outList.size());
            wd.SetHumidity(nHumidity/outList.size());
            wd.SetFeels_like(nFeels_like/outList.size());
            
            sd.SetAverageWeatherData(wd);
            
            nTemp = 0;
            nHumidity = 0;
            nFeels_like = 0;
            
            //variance = for(i=1 -> n){ sum((x[i] - xmed)^2))}/n
            for(int i=0; i < outList.size(); i++){
                nTmp = outList.get(i).GetTemp()-wd.GetTemp();
                nTemp += nTmp*nTmp;
                nTmp = outList.get(i).GetHumidity()-wd.GetHumidity();
                nHumidity += nTmp*nTmp;
                nTmp = outList.get(i).GetFeels_like()-wd.GetFeels_like();
                nFeels_like += nTmp*nTmp;
            }
            
            //salvataggio varianza
            sd.SetVarianceWeatherData(new WeatherData(nTemp/outList.size(),
                                                      nHumidity/outList.size(),
                                                      nFeels_like/outList.size()));
            
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    public WeatherData GetWeatherData(){return wd;}
    public StatisticData GetStatisticData(){return sd;}
    public RubData GetRubData(){return rd;}
    public String GetDBPath(){return dBPath;}
}