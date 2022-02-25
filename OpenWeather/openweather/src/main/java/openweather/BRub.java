package openweather;

import javax.swing.SwingConstants;

/**
 * 
 * Pulsante che mostra il dato relativo ad una determinatà città in rubrica 
 * e una volta premuto ne inserisce ID,nome,lat,lon nei campi di ricerca
 * 
 * @author DanieleCocciGrifoni, AdrianoCruciani
 */

public class BRub extends javax.swing.JButton{
	
    RubData rd;	//dato rubrica
    
	/**
	 * 
	 * Costruttore per dato rubrica rd
	 * 
	 */
	
    public BRub(RubData rd) {
        this.rd = rd;
        this.setHorizontalAlignment(SwingConstants.LEFT);
        this.setOpaque(false);
        this.setContentAreaFilled(false);
        this.setText("<html>Name: " + rd.GetName() +
                     "<br />Id: " + rd.GetId() +
                     "<br />Lat: " + rd.GetLat() +
                     "<br />Lon: " + rd.GetLon() + "</html>");
    }
    
	/**
	 * 
	 * Costruttore per stringa di testo
	 * 
	 */
	
    public BRub(String text) {
        this.rd = null;
        this.setHorizontalAlignment(SwingConstants.LEFT);
        this.setOpaque(false);
        this.setContentAreaFilled(false);
        this.setText(text);
    }
    
    public RubData GetRubData(){return rd;}
}
