package view;

import javafx.beans.property.SimpleStringProperty;

public class Row {
	private final SimpleStringProperty klasse;
    private final SimpleStringProperty stunde;
    private final SimpleStringProperty fach;
    private final SimpleStringProperty raum;
    private final SimpleStringProperty art;
    private final SimpleStringProperty text;
    
    public Row(String klasse, String std, String fach, String raum, String art, String text) {
        this.klasse = new SimpleStringProperty(klasse);
        this.stunde = new SimpleStringProperty(std);
        this.fach = new SimpleStringProperty(fach);
        this.raum = new SimpleStringProperty(raum);
        this.art = new SimpleStringProperty(art);
        this.text = new SimpleStringProperty(text);
    }

	public String getKlasse() {
		return klasse.get();
	}

	public String getStunde() {
		return stunde.get();
	}

	public String getFach() {
		return fach.get();
	}

	public String getRaum() {
		return raum.get();
	}

	public String getArt() {
		return art.get();
	}

	public String getText() {
		return text.get();
	}
	
	public void setKlasse(String val) {
		this.klasse.set(val);
	}

	public void setStunde(String val) {
		this.stunde.set(val);
	}

	public void setFach(String val) {
		this.fach.set(val);
	}

	public void setRaum(String val) {
		this.raum.set(val);
	}

	public void setArt(String val) {
		this.art.set(val);
	}

	public void setText(String val) {
		this.text.set(val);
	}
}
