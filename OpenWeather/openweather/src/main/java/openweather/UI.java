package openweather;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 
 * Interfeccia grafica del nostro applicativo, generata automaticamente tramite il software "NetBeans", 
 * questo per facilitare il lavoro dal punto di vista grafico
 * 
 * @author DanieleCocciGrifoni, AdrianoCruciani
 */

@SpringBootApplication
public class UI extends javax.swing.JFrame {
	
    private OpenWeather ow;
    
	/**
  		Costruttore
	*/
	
    public UI() {
        ow = new OpenWeather();
        getContentPane().setBackground(new java.awt.Color(51, 51, 51));
        initComponents();
        setResizable(false);
    }
	
	/**
	 * 
	 * Funzione(AutoGenerata) per posizionare ed inizializzare gli elementi
	 * 
	*/
	
    @SuppressWarnings("unchecked")
    private void initComponents() {

        LName = new javax.swing.JLabel();
        LLat = new javax.swing.JLabel();
        FLat = new javax.swing.JTextField();
        FLon = new javax.swing.JTextField();
        LLon = new javax.swing.JLabel();
        LId = new javax.swing.JLabel();
        BSearch = new javax.swing.JButton();
        sBar = new javax.swing.JTextField();
        oPanel = new openweather.OutPanel(this);
        CBPeriod = new javax.swing.JComboBox<>();
        LSPeriod = new javax.swing.JLabel();
        PPeriod = new javax.swing.JPanel();
        LStart = new javax.swing.JLabel();
        LEnd = new javax.swing.JLabel();
        FSD = new javax.swing.JTextField();
        LSlash1 = new javax.swing.JLabel();
        FSM = new javax.swing.JTextField();
        FSY = new javax.swing.JTextField();
        LSlash2 = new javax.swing.JLabel();
        FED = new javax.swing.JTextField();
        LSlash3 = new javax.swing.JLabel();
        FEM = new javax.swing.JTextField();
        LSlash4 = new javax.swing.JLabel();
        FEY = new javax.swing.JTextField();
        CBSearch = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(51, 51, 51));

        LName.setForeground(new java.awt.Color(255, 255, 255));
        LName.setText("name");
        LName.setVisible(false);

        LLat.setBackground(new java.awt.Color(0, 0, 0));
        LLat.setForeground(new java.awt.Color(255, 255, 255));
        LLat.setText("Latitude");

        FLat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {	//Setta la funzione che viene lanciata dal bottone quando cliccato
                FLatActionPerformed(evt);
            }
        });

        LLon.setBackground(new java.awt.Color(0, 0, 0));
        LLon.setForeground(new java.awt.Color(255, 255, 255));
        LLon.setText("Longitude");

        LId.setBackground(new java.awt.Color(255, 255, 255));
        LId.setForeground(new java.awt.Color(255, 255, 255));
        LId.setText("id");
        LId.setVisible(false);

        BSearch.setForeground(new java.awt.Color(255, 255, 255));
        BSearch.setText("Search");
        BSearch.setContentAreaFilled(false);
        BSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {	//Setta la funzione che viene lanciata dal bottone quando cliccato
                BSearchActionPerformed(evt);
            }
        });

        sBar.setCaretColor(new java.awt.Color(255, 255, 255));
        sBar.setVisible(false);
        
        sBar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {	//Setta la funzione che viene lanciata dal bottone quando cliccato
                sBarActionPerformed(evt);
            }
        });

        oPanel.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout oPanelLayout = new javax.swing.GroupLayout(oPanel);
        oPanel.setLayout(oPanelLayout);
        oPanelLayout.setHorizontalGroup(
            oPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        oPanelLayout.setVerticalGroup(
            oPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 188, Short.MAX_VALUE)
        );

        CBPeriod.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Now", "Today", "This Week", "This Month", "Custom" }));
        CBPeriod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {	//Setta la funzione che viene lanciata dal bottone quando cliccato
                CBPeriodActionPerformed(evt);
            }
        });

        LSPeriod.setForeground(new java.awt.Color(255, 255, 255));
        LSPeriod.setText("Searching period: ");

        PPeriod.setBackground(new java.awt.Color(255, 255, 255));

        LStart.setText("Start: ");

        LEnd.setText("End: ");

        FSD.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        FSD.setText("dd");

        LSlash1.setText("/");

        FSM.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        FSM.setText("mm");

        FSY.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        FSY.setText("yyyy");

        LSlash2.setText("/");

        FED.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        FED.setText("dd");

        LSlash3.setText("/");

        FEM.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        FEM.setText("mm");

        LSlash4.setText("/");

        FEY.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        FEY.setText("yyyy");

        javax.swing.GroupLayout PPeriodLayout = new javax.swing.GroupLayout(PPeriod);
        PPeriod.setLayout(PPeriodLayout);
        PPeriodLayout.setHorizontalGroup(
            PPeriodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PPeriodLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(PPeriodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PPeriodLayout.createSequentialGroup()
                        .addComponent(LStart)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(FSD, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(LSlash1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(FSM, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(LSlash2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(FSY, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PPeriodLayout.createSequentialGroup()
                        .addComponent(LEnd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(FED, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(LSlash3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(FEM, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(LSlash4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(FEY, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PPeriodLayout.setVerticalGroup(
            PPeriodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PPeriodLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PPeriodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PPeriodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(LSlash2)
                        .addComponent(FSY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PPeriodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(LStart)
                        .addComponent(FSD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(LSlash1)
                        .addComponent(FSM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(PPeriodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PPeriodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(FED, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(LSlash3)
                        .addComponent(FEM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(LSlash4)
                        .addComponent(FEY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(LEnd))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PPeriod.setVisible(false);

        CBSearch.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nome", "Id" }));
        CBSearch.setVisible(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(oPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LLat, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(FLat, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(FLon)
                            .addComponent(LLon, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(LSPeriod)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CBPeriod, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(CBSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sBar))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LId)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(74, 74, 74)
                                .addComponent(BSearch))
                            .addComponent(LName)
                            .addComponent(PPeriod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LLat)
                    .addComponent(LLon))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(FLat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(FLon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(BSearch)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CBPeriod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LSPeriod))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PPeriod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LId)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CBSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(oPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>                        

    private void FLatActionPerformed(java.awt.event.ActionEvent evt) {                                     
    }                           

	/**
	 * 
	 * Metodo per la ricerca di ID, nome, latitudine e longitudine
	 *
	 * @param RubData rd, dati della rubrica richiesti
	*/

    public void PutParams(RubData rd){
        LName.setText("name: " + rd.GetName().toString());
        LId.setText("Id: " + rd.GetId().toString());
        FLat.setText(rd.GetLat().toString());
        FLon.setText(rd.GetLon().toString());
    }
    
	/**
	 * 
	 * Metodo che ritorna l'oggetto OpenWeather
	 * 
	 * @return ow
	*/
	
    public OpenWeather GetOpenWeather(){return ow;}
    
	/**
	 * 
	 * (AutoGenerata) Evento che sull'invio effettua la richiesta al server
	 * 
	 * @param java.awt.event.ActionEvent evt, evento
	*/
	
    private void BSearchActionPerformed(java.awt.event.ActionEvent evt) {                                        
    	LName.setVisible(true);
        if(!ow.Query(FLat.getText(), FLon.getText())){
            //errore
            LName.setText("Failed to get data!");
            LId.setVisible(false);
            CBSearch.setVisible(true);
            sBar.setVisible(true);
            oPanel.Fail();
        }else{
            //successo
            ow.SaveRubData();
            ow.SaveWeatherData();
            PutParams(ow.GetRubData());
            LocalDate start = LocalDate.now();
            LocalDate end = LocalDate.now();
            if(CBPeriod.getSelectedIndex() == 0)    //Now
                oPanel.Success(ow.GetWeatherData());
            else{
                switch(CBPeriod.getSelectedIndex()){
                    case 1:     //Today
                        break;
                    case 2:     //This Week
                        start = start.minusWeeks(7); //today - 1week
                        break;
                    case 3:     //This Month
                        start = start.minusMonths(1); //today - 1month
                        break;
                    case 4:     //Custom
                        start = LocalDate.parse(FSY.getText()+'-'+FSM.getText()+'-'+FSD.getText(), DateTimeFormatter.ISO_LOCAL_DATE);//yyyy-mm-dd
                        end = LocalDate.parse(FEY.getText()+'-'+FEM.getText()+'-'+FED.getText(), DateTimeFormatter.ISO_LOCAL_DATE);//yyyy-mm-dd
                        break;
                }
                if(!ow.CalculateAverage(start, end)){
                    LName.setText("Failed to calculate average!");
                    LId.setVisible(false);
                    CBSearch.setVisible(true);
                    sBar.setVisible(true);
                    oPanel.Fail();
                }else
                    oPanel.Success(ow.GetStatisticData());
            }
            CBSearch.setVisible(false);
            sBar.setVisible(false);
            LId.setVisible(true);
        }
    }        
	
	/**
	 * 
	 * (AutoGenerata)Evento che sull'invio inizia la ricerca sul dizionario
	 * 
	 * @param java.awt.event.ActionEvent evt, evento
	*/


    private void sBarActionPerformed(java.awt.event.ActionEvent evt) {                                     
        if(CBSearch.getSelectedIndex() == 0)
            oPanel.Search("name", sBar.getText());
        else
            oPanel.Search("id", sBar.getText());
    }      
	
	/**
	 * 
	 * (AutoGenerata) Evento che mostra il pannello di configurazione del perdiodo di ricerca in base all'opzione selezionata dal menù a tendina
	 * 
	 * @param java.awt.event.ActionEvent evt, evento
	*/

    private void CBPeriodActionPerformed(java.awt.event.ActionEvent evt) {                                         
        if(CBPeriod.getSelectedIndex() == 4){ //Custom
            setSize(getWidth(), getHeight()+PPeriod.getHeight());
            if(PPeriod.isVisible() == false){
                PPeriod.setVisible(true);
                if(sBar.isVisible()){
                    oPanel.setVisible(false);
                    pack();
                    oPanel.setVisible(true);
                }else
                    pack();
                
                setSize(getWidth(), getHeight()+PPeriod.getHeight()+sBar.getHeight());
            }
        }else{
            if(PPeriod.isVisible()){
                PPeriod.setVisible(false);
                setSize(getWidth(), getHeight()-PPeriod.getHeight()+sBar.getHeight());
            }
        }
    }                                        

    public static void main(String args[]) {	//main
    	SpringApplication.run(UI.class, args);
    	java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton BSearch;
    private javax.swing.JComboBox<String> CBPeriod;
    private javax.swing.JComboBox<String> CBSearch;
    private javax.swing.JTextField FED;
    private javax.swing.JTextField FEM;
    private javax.swing.JTextField FEY;
    private javax.swing.JTextField FLat;
    private javax.swing.JTextField FLon;
    private javax.swing.JTextField FSD;
    private javax.swing.JTextField FSM;
    private javax.swing.JTextField FSY;
    private javax.swing.JLabel LEnd;
    private javax.swing.JLabel LId;
    private javax.swing.JLabel LLat;
    private javax.swing.JLabel LLon;
    private javax.swing.JLabel LName;
    private javax.swing.JLabel LSPeriod;
    private javax.swing.JLabel LSlash1;
    private javax.swing.JLabel LSlash2;
    private javax.swing.JLabel LSlash3;
    private javax.swing.JLabel LSlash4;
    private javax.swing.JLabel LStart;
    private javax.swing.JPanel PPeriod;
    private openweather.OutPanel oPanel;
    private javax.swing.JTextField sBar;
    // End of variables declaration                   
}
