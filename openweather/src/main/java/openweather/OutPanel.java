package openweather;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Iterator;
import java.util.Scanner;
import javax.swing.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * 
 * Pannello che mostra "PInfo" in caso di query andata a buon fine e invece una lista di "BRub" nel caso contrario
 * 
 * @author DanieleCocciGrifoni, AdrianoCruciani
 */

public class OutPanel extends JPanel {
	
	/**
  		Frame da cui si prende l'oggetto openweather e dove si inseriscono i dati relativi al pulsante cliccato
	*/
	
    private UI frame;
    
	/**
  		Costruttore vuoto
	*/
	
    public OutPanel() {
        frame = null;
    }
    
	/**
  		Costruttore per frame
	*/
	
    public OutPanel(UI frame) {
        this.frame = frame;
    }
    
	/**
	 * 
	 * Metodo che ridefinisce il layout del pannello
	 * 
	 * @param JPanel mainLis 
	*/
	
    private void Init(JPanel mainList){
        removeAll();
        setLayout(new BorderLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.NORTH;
        mainList.add(new JPanel(), gbc);
        
        JScrollPane scrollPane = new JScrollPane(mainList);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        
        add(scrollPane);
    }
	
	/**
	 * 
	 * Metodo che mostra un "PInfo" con i dati statistici 
	 * 
	 * @param StatisticData sd, dati statistici
	*/
    
    public void Success(StatisticData sd){
        //mostra i dati
        JPanel mainList = new JPanel(new GridBagLayout());
        Init(mainList);
        
        PInfo info = new PInfo(sd);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainList.add(info, gbc, 0);

        validate(); //ricostruzione pannello (spostamento, ridimensionamento oggetti)
        repaint();  //aggiorna disegno pannello
    }
    
	/**
	 * 
	 * Metodo che mostra un "PInfo" con i dati meteo attuali
	 * 
	 * @param WeatherData wd, dati meteorologici attuali
	*/
	
    public void Success(WeatherData wd){
        //mostra i dati
        JPanel mainList = new JPanel(new GridBagLayout());
        Init(mainList);
        
        PInfo info = new PInfo(wd);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainList.add(info, gbc, 0);

        validate(); //ricostruzione pannello (spostamento, ridimensionamento oggetti)
        repaint();  //aggiorna disegno pannello
    }
    
	/**
	 * 
	 * mostra un "BRub" per ogni città presente in rubrica
	 * 
	*/
	
    public void Fail(){
        //mostra la rubrica
        JSONParser parser = new JSONParser();
        JSONObject f = new JSONObject();
        JSONArray list = new JSONArray();
        
        String dBPath = frame.GetOpenWeather().GetDBPath();
        
        JPanel mainList = new JPanel(new GridBagLayout());
        
        Init(mainList);
        
        try{
            Scanner in = new Scanner(new BufferedReader(new FileReader(dBPath)));
            String input = in.nextLine();
            f = (JSONObject) parser.parse(input);
            
            JSONObject objt = new JSONObject();
            JSONObject coord = new JSONObject();
            
            list = (JSONArray) f.get("lista");
            Iterator<JSONObject> iterator = list.iterator();
            while (iterator.hasNext()) {
                objt = iterator.next();
                coord = (JSONObject) objt.get("coord");;
                GridBagConstraints gbc = new GridBagConstraints();
                RubData rd = new RubData(objt.get("name").toString(),
                                         objt.get("id").toString(),
                                         coord.get("lat").toString(),
                                         coord.get("lon").toString());
                BRub rub = new BRub(rd);
                rub.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e){	//Setta la funzione che viene lanciata dal bottone quando cliccato
                        frame.PutParams(rub.GetRubData());
                    }
                });
                gbc.gridwidth = GridBagConstraints.REMAINDER;
                gbc.weightx = 1;
                gbc.fill = GridBagConstraints.HORIZONTAL;
                mainList.add(rub, gbc, 0);
                
                validate();
                repaint();
            }
            in.close();
        }catch(Exception e){}
    }
	
	/**
	 * 
	 * Metodo che mostra un "BRub" con i parametri della città che abbia "request" uguale a "ToFind"
	 * 
	 * @param String request, String toFind
	*/
    
    public void Search(String request, String toFind){
        //mostra la rubrica
        JSONParser parser = new JSONParser();
        JSONObject f = new JSONObject();
        JSONArray list = new JSONArray();
        
        boolean found = false;
        
        String dBPath = frame.GetOpenWeather().GetDBPath();
        
        JPanel mainList = new JPanel(new GridBagLayout());
        
        Init(mainList);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        try{
            Scanner in = new Scanner(new BufferedReader(new FileReader(dBPath)));
            String input = in.nextLine();
            f = (JSONObject) parser.parse(input);
            
            JSONObject objt = new JSONObject();
            JSONObject coord = new JSONObject();
            
            list = (JSONArray) f.get("lista");
            Iterator<JSONObject> iterator = list.iterator();
            while (iterator.hasNext()) {
                objt = iterator.next();
                if(toFind.equals(objt.get(request).toString())){
                    found = true;
                    coord = (JSONObject) objt.get("coord");
                    RubData rd = new RubData(objt.get("name").toString(),
                                             objt.get("id").toString(),
                                             coord.get("lat").toString(),
                                             coord.get("lon").toString());
                    BRub rub = new BRub(rd);
                    rub.addActionListener(new ActionListener(){
                        @Override
                        public void actionPerformed(ActionEvent e){	//Setta la funzione che viene lanciata dal bottone quando cliccato
                            frame.PutParams(rub.GetRubData());
                        }
                    });
                    
                    mainList.add(rub, gbc, 0);
                }
            }
            
            in.close();
        }catch(Exception e){}
        
        if(!found) mainList.add(new BRub("nome inserito non trovato"), gbc, 0);

        validate();
        repaint();
    }
}