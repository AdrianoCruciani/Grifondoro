package openweather;

import java.text.DecimalFormat;

/**
 * 
 * Pannello che mostra i dati in OutPut
 *
 * 
 * @author DanieleCocciGrifoni, AdrianoCruciani
 */

public class PInfo extends javax.swing.JPanel {

    public PInfo() {
        initComponents();
    }
	
	/**
  		Costruttore per StatisticData sd, output dei dati statistici
	*/
    
    public PInfo(StatisticData sd) {
        initComponents();
        
        DecimalFormat df = new DecimalFormat("#.##");
        
        LAHum.setText("humidity: " + df.format(sd.GetAverageWeatherData().GetHumidity()));
        LATemp.setText("temp: " + df.format(sd.GetAverageWeatherData().GetTemp()));
        LAFL.setText("feels_like: " + df.format(sd.GetAverageWeatherData().GetFeels_like()));
        
        LVHum.setText("humidity: " + df.format(sd.GetVarianceWeatherData().GetHumidity()));
        LVTemp.setText("temp: " + df.format(sd.GetVarianceWeatherData().GetTemp()));
        LVFL.setText("feels_like: " + df.format(sd.GetVarianceWeatherData().GetFeels_like()));
        
        LMaxHum.setText("humidity: " + df.format(sd.GetMaxWeatherData().GetHumidity()));
        LMaxTemp.setText("temp: " + df.format(sd.GetMaxWeatherData().GetTemp()));
        LMaxFL.setText("feels_like: " + df.format(sd.GetMaxWeatherData().GetFeels_like()));
        
        LMinHum.setText("humidity: " + df.format(sd.GetMinWeatherData().GetHumidity()));
        LMinTemp.setText("temp: " + df.format(sd.GetMinWeatherData().GetHumidity()));
        LMinFL.setText("feels_like: " + df.format(sd.GetMinWeatherData().GetFeels_like()));
    }
    
	/**
  		Costruttore per WeatherData wd, output dei dati meteorologici
	*/
	
    public PInfo(WeatherData wd) {
        initComponents();
        
        DecimalFormat df = new DecimalFormat("#.##");
        
        LAHum.setText("humidity: " + df.format(wd.GetHumidity()));
        LATemp.setText("temp: " + df.format(wd.GetTemp()));
        LAFL.setText("feels_like: " + df.format(wd.GetFeels_like()));

        LA.setVisible(false);
        
        LV.setVisible(false);
        LVHum.setVisible(false);
        LVTemp.setVisible(false);
        LVFL.setVisible(false);
        
        LMax.setVisible(false);
        LMaxHum.setVisible(false);
        LMaxTemp.setVisible(false);
        LMaxFL.setVisible(false);
        
        LMin.setVisible(false);
        LMinHum.setVisible(false);
        LMinTemp.setVisible(false);
        LMinFL.setVisible(false);
    }
    
	/**
	 * 
	 * (AutoGenerata)Funzione per posizionare ed inizializzare gli elementi
	 * 
	 * @param java.awt.event.ActionEvent evt, evento
	*/
	
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        LAHum = new javax.swing.JLabel();
        LATemp = new javax.swing.JLabel();
        LAFL = new javax.swing.JLabel();
        LMax = new javax.swing.JLabel();
        LMin = new javax.swing.JLabel();
        LVHum = new javax.swing.JLabel();
        LVTemp = new javax.swing.JLabel();
        LVFL = new javax.swing.JLabel();
        LA = new javax.swing.JLabel();
        LV = new javax.swing.JLabel();
        LMinHum = new javax.swing.JLabel();
        LMinTemp = new javax.swing.JLabel();
        LMinFL = new javax.swing.JLabel();
        LMaxHum = new javax.swing.JLabel();
        LMaxTemp = new javax.swing.JLabel();
        LMaxFL = new javax.swing.JLabel();

        LAHum.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LAHum.setText("humidity");
        LAHum.setToolTipText("");

        LATemp.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LATemp.setText("temp");

        LAFL.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LAFL.setText("feels_like");

        LMax.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LMax.setText("Max:");
        LMax.setToolTipText("");

        LMin.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LMin.setText("Min:");

        LVHum.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LVHum.setText("humidity");
        LVHum.setToolTipText("");

        LVTemp.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LVTemp.setText("temp");

        LVFL.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LVFL.setText("feels_like");

        LA.setText("Average:");

        LV.setText("Variance:");

        LMinHum.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LMinHum.setText("humidity");
        LMinHum.setToolTipText("");

        LMinTemp.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LMinTemp.setText("temp");

        LMinFL.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LMinFL.setText("feels_like");

        LMaxHum.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LMaxHum.setText("humidity");
        LMaxHum.setToolTipText("");

        LMaxTemp.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LMaxTemp.setText("temp");

        LMaxFL.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LMaxFL.setText("feels_like");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(LMin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(LA)
                            .addComponent(LATemp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(LAFL, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                            .addComponent(LAHum, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LV)
                            .addComponent(LVFL, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LVTemp, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LVHum, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LMax, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(LMinTemp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(LMinFL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(LMinHum, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(LMaxTemp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(LMaxFL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(LMaxHum, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LA)
                    .addComponent(LV))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(LAHum)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(LATemp)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(LAFL))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(LVHum)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(LVTemp)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(LVFL)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LMax)
                    .addComponent(LMin))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(LMinHum)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(LMinTemp)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(LMinFL))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(LMaxHum)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(LMaxTemp)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(LMaxFL)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>                        
    
    // Variables declaration - do not modify                     
    private javax.swing.JLabel LA;
    private javax.swing.JLabel LAFL;
    private javax.swing.JLabel LAHum;
    private javax.swing.JLabel LATemp;
    private javax.swing.JLabel LMax;
    private javax.swing.JLabel LMaxFL;
    private javax.swing.JLabel LMaxHum;
    private javax.swing.JLabel LMaxTemp;
    private javax.swing.JLabel LMin;
    private javax.swing.JLabel LMinFL;
    private javax.swing.JLabel LMinHum;
    private javax.swing.JLabel LMinTemp;
    private javax.swing.JLabel LV;
    private javax.swing.JLabel LVFL;
    private javax.swing.JLabel LVHum;
    private javax.swing.JLabel LVTemp;
    // End of variables declaration                   
}
