# Progetto per l'esame di Programmazione ad Oggetti

![Project Image](https://github.com/AdrianoCruciani/Progetto_Grifondoro/blob/main/OpenWeather/immagini/download1.png)

## Index

Lo scopo di questo progetto è di offrire un servizio meteo all'utente con cui, scelte a suo piacimento le coordinate relative ad una specifica città, si visualizzino tutte le informazioni attuali relative alla temperatura, temperatura percepita e umidità e le statistiche riguardanti valori minimi, massimi, media e varianza, con filtraggio in base alla periodicità scelta. Una volta effettuata la richiesta, il programma cercherà i dati meteo riguardanti le città. Inoltre tramite un sistema sviluppato apposta per l'utente in caso di errore nell'inserimento dei parametri, sarà possibile sfruttare un dizionario statico che fornirà le coordinate relative alle città insieme al loro ID (identificativo) e il NOME. 
Infine l'utente potrà utilizzare una barra di ricerca nella quale scrivendo il NOME o l'ID della città di interesse otterrà le relative coordinate.

- [Progetto per l'esame di Programmazione ad Oggetti](#progetto-per-lesame-di-programmazione-ad-oggetti)
  - [Index](#index)
  - [Description](#description)
  - [UML Diagrams](#uml-diagrams)
    - [UML UsingCase Diagram](#uml-usingcase-diagram)
  - [Java Classes](#java-classes)
  - [Starting](#starting)
  - [Available Routes](#available-routes)
  - [Using Example](#using-examples)
  - [How To Use](#how-to-use)
  - [Errors Managment](#errors-managment)
  - [Test Units](#test-units)
  - [Technologies used](#technologies-used)
  - [Authors Info](#authors-info)

---

## Description

Tramite L'API OpenWeather il programma riceve, salva e processa i dati meteo riguardanti le città di interesse ricercate dall'utente; per far questo utilizza più precisamente l'API "api.openweathermap.org/data/2.5/weather?lat={lat}&lon={lon}&appid={API key}", la quale descrizione è disponibile al seguente [link](https://openweathermap.org/current#geo)

L'applicazione permette quindi di:

- [x] Ottenere informazioni meteo (umidità, temp effettiva e percepita) della città inserita dall'utente
- [x] Salvare dati in locale
- [x] Gestire possibili errori dovuti a inserimento di coordinate errate
- [x] Facilitare inserimento delle coordinate con possibilità di ricercarle tramite un dizionario statico
- [x] Ottenere statistiche riguardanti valori min max media e varianza di umidità, temp effettiva e percepita
- [x] Scegliere filtraggio delle statistiche in base alla periodicità: range personalizzabile

## UML Diagrams

### UML UsingCase Diagram

![Project UsingCase UML](https://github.com/AdrianoCruciani/Progetto_Grifondoro/blob/main/OpenWeather/immagini/UsageUML.png)

## Java Classes

Nel nostro Package OpenWeather sono presenti numerose classi:

- Classe ***RequestController.java*** 
  - metodi
    * getWeatherByCoordinates(lat,lon)
    * getPeriodWeatherByCoordinates(lat,lon,start,end)
    * getWeatherByName(name)
    * getPeriodWeatherByName(name,start,end)
    * getWeatherById(id)
    * getPeriodWeatherById(id,start,end)
    * Search(request,toFind)

- Classe ***OutPanel.java***
  - costruttori
    * OutPanel(UI frame)
  - metodi
    * Init(JPanel mainList)
    * Success(StatisticData sd)
    * Success(WeatherData wd)
    * Fail()
    * Search(String request, String toFind)

- Classe ***UI.java*** 
  - costruttori
    * UI()
  - metodi
    * initComponents(), (AutoGenerata) 
    * PutParams(RubData rd)
    * GetOpenWeather()
    * BSearchActionPerformed(java.awt.event.ActionEvent evt), (AutoGenerata) 
    * sBarActionPerformed(java.awt.event.ActionEvent evt), (AutoGenerata) 
    * CBPeriodActionPerformed(java.awt.event.ActionEvent evt), (AutoGenerata)

- Classe ***OpenWeather.java***
  - metodi
    * Query(String iLat, String iLon)
    * SaveWeatherData()
    * SaveRubData()
    * CalculateAverage(LocalDate start, LocalDate end
    * GetWeatherData()
    * GetStatisticData()
    * GetRubData()
    * GetDBPath()

- Classe ***Pinfo.java***, 
  - costruttori
    * PInfo()
    * PInfo(StatisticData sd)
    * PInfo(WeatherData wd)
  - metodi
    * initComponents(), (AutoGenerata)

- Classe ***BRub.java***
  - costruttori
    * BRub(RubData rd)
    * BRub(String text)
  - metodi
    * GetRubData()
    
- Classe ***RubData.java***(dizionario)
  - costruttori
    * RubData(name, id, lat, lon)
  - metodi
    * GetName(); GetId(); GetLat(); GetLon();
    * SetName(); SetId(); SetLat(); SetLon();
    
- Classe ***StatisticData.java***
  - costruttori
    * StatisticData()
  - metodi
    * GetMax(); GetMin; GetAverageWeatherData(); GetVarianceWeatherData();
    * SetMax(); SetMin; SetAverageWeatherData(WeatherData ad); SetVarianceWeatherData(WeatherData vd);

- Classe ***WeatherData.java*** 
  - costruttori
    * WeatherData(humidity, temp, fells_like)
  - metodi
    * GetHumidity(); GetTemp(); GetFeels_like();
    * SetHumidity(); SetTemp(); setFeels_like();

[Back To The Top](#index)

---

## Starting

![App Image](https://github.com/AdrianoCruciani/Progetto_Grifondoro/blob/main/OpenWeather/immagini/Percorso.jpeg)

Una volta che l'utente avrà completato i campi del file config.json sarà pronto ad eseguire il programma:

Verranno letti dal file config.JSON, vari parametri per la configurazzione dell'applicazione, questi sono:

* **"API_key"** che contiene la chiave per le API di OpenWeather da inserire nell'URL delle richieste di dati
* **"Path_Rubrica"** che contiene il percorso della rubrica
* **"Path_DataBase"** che contiene il percorso per il DataBase

[Back To The Top](#index)

---

## Available Routes

Dal programma vengono rese disponibili le seguenti rotte sulla porta 8080 del localhost:

|N. |Rotta |Metodo |Descrizione |
|:----------: |:----------: |:----------: |:----------: |
| 1 | getWeatherByCoordinates() |GET|Restituisce un JSONObject contenente le informazioni meteo ricercate per coordinate |
| 2 | getPeriodWeatherByCoordinates() |GET|Restituisce un JSONObject contenente le informazioni meteo delle città ricercate per coordinate in un determinato periodo |
| 3 | getWeatherByName() |GET|Restituisce un JSONObject contenente le informazioni meteo delle città delle città ricercate per nome |
| 4 | getPeriodWeatherByName() |GET|Restituisce un JSONObject contenente le informazioni meteo delle città ricercate per nome in un determinato periodo |
| 5 | getWeatherById() |GET|Restituisce un JSONObject contenente le informazioni meteo delle città ricercate per ID |
| 6 | getPeriodWeatherById() |GET|Restituisce un JSONObject contenente le informazioni meteo delle città ricercate per ID in un determinato periodo |

### Using Examples

Le rotte "GetWeather" restituiscono un file JSON con questa struttura:

![App Image](https://github.com/AdrianoCruciani/Progetto_Grifondoro/blob/main/OpenWeather/immagini/getW.jpeg)

- Abbiamo utilizzato come esempio, i dati meteorologici relativi alla città di Milano, con coordinate Latitudine = 45.4643 e Longitudine = 9.1895

Mentre le rotte "GetPeriodWeather" restituiscono un file JSON strutturato in questo modo:

![App Image](https://github.com/AdrianoCruciani/Progetto_Grifondoro/blob/main/OpenWeather/immagini/getPW.jpeg)

- Con periodo Start = 2022-02-20 e End = 2022-02-25

Inoltre, dopo la richiesta, sarà effettuato automaticamente un salvataggio in locale dei dati in un file di testo, rubrica.json(che viene salvato nel path costruito precedentemente)

![App Image](https://github.com/AdrianoCruciani/Progetto_Grifondoro/blob/main/OpenWeather/immagini/rubrica.jpeg)

**NOTA!**
- I parametri riferiti al periodo di interesse sono espressi in formato ISO_LOCAL_DATE (yyyy-mm-dd)

[Back To The Top](#index)

---

## How To Use

Questa è la nostra interfaccia GUI:

![App Image](https://github.com/AdrianoCruciani/Progetto_Grifondoro/blob/main/OpenWeather/immagini/app.jpeg)

L'utente in questa interfaccia potrà inserire le coordinate relative ad una città ed ottenere i primi risultati

![App Image](https://github.com/AdrianoCruciani/Progetto_Grifondoro/blob/main/OpenWeather/immagini/App2.jpeg)

Ovviamente nel caso in cui l'utente non riuscisse ad inserire le coordinate correttamente lo potrà notare

![App Image](https://github.com/AdrianoCruciani/Progetto_Grifondoro/blob/main/OpenWeather/immagini/fail.jpeg)

In questo caso l'utente potrà consultare il dizionario statico tramite il quale potrà ottenere in output le coordinate corrette inserendo il nome o l'identificativo della città di interesse.

![App Image](https://github.com/AdrianoCruciani/Progetto_Grifondoro/blob/main/OpenWeather/immagini/nome.jpeg)![App Image](https://github.com/AdrianoCruciani/Progetto_Grifondoro/blob/main/OpenWeather/immagini/id.jpeg)

Successivamente potrà selezionare un periodo di filtraggio dei dati meteorologici per giorno, settimana, mese ed anno, ottenendo così anche le varie statistiche come temperatura massima e minima

![App Image](https://github.com/AdrianoCruciani/Progetto_Grifondoro/blob/main/OpenWeather/immagini/custom.jpeg)


[Back To The Top](#index)

---

## Errors Managment

- Sono stati gestiti i casi in cui 

  1. la query non è andata a buon fine
  2. la ricerca abbia fallito
  3. dati statistici errati per il calcolo

[Back To The Top](#index)

---

## Test Units

Per verificare il corretto funzionamento di alcuni metodi e alcune eccezioni, sono stati implementati i seguenti test:

- [x] Test SaveRubData, verifica la funzione che salva i dati in rubrica (db_path)
- [x] Test SaveWeatherData, verifica il salvataggio dei dati meteo (sd_path)

![App Image](https://github.com/AdrianoCruciani/Progetto_Grifondoro/blob/main/OpenWeather/immagini/test.jpeg)


[Back To The Top](#index)

---

## Documentation

Tutto il progetto è documentato in **Javadoc**.


[Back To The Top](#index)

---

### Technologies used

- *Visual Studio Code*, per l'editing e il rendering in tempo reale del documento di markdown per il README
- *NetBeans*, ambiente di sviluppo sfruttato principalmente per la parte relativa all'interfaccia
- *GitHub*, per il versioning del codice
- *Eclipse*, per lo sviluppo di tutto l'applicativo scritto in java
- *Diagrams.net*, per la modellazione e la generazione dei diagrammi UML
- *Maven*, Software per gestione del progetto Java e dipendenze
- *Postman*, per richiamare e testare le API esposte dal nostro servizio
- *Libreria JSON.simple*, Libreria per parsing, lettura e scrittura di oggetti e array in formato JSON
- *Spring*, Framework per sviluppo di applicazioni in Java
- *JUnit 5*, Framework per lo unit testing

[Back To The Top](#index)

---

## Authors Info

- **Cruciani Adriano**, ha dato un maggior supporto nella parte organizzativa e presentativa del progetto.
- **Cocci Grifoni Daniele**, si è dedicato principalmente allo sviluppo del codice con diligenza.

Il progetto è stato portato a termine in esattamente 18 giorni. In questo lasso di tempo i 2 studenti si sono confrontati giornalmente per cercare le soluzioni migliori alle varie problematiche, sfruttando mezzi e conoscenze regresse e apprese nel corso di studi da loro frequentato.

[Back To The Top](#index)