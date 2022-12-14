package com.example.Controller;


import com.example.LinkedList;
import com.example.Nodos;
import com.example.XML;
import com.example.songs;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortException;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Controlador de la pantalla principal, en este se mostraran los datos del usuario, agregar y crear bibliotecas
 * musicales, y reproducirlas
 */
public class MP3Controller implements Initializable{
    private Stage stage;
    private LoginController controlladorLogin;
    private Media musica;
    private MediaPlayer reproductor;


    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="explote"
    private Button cerrar2; // Value injected by FXMLLoader

    @FXML // fx:id="logout"
    private Button logout; // Value injected by FXMLLoader
    @FXML
    private Label user1;
    @FXML
    private Label correo1;
    @FXML
    private Label prov1;

    @FXML
    private TextField biblioname;

    @FXML
    private TextField biblioselec;

    @FXML
    private Button btnbuscar;

    @FXML
    private Button btnagregar;

    @FXML
    private Label fecha;

    @FXML
    private TableView<songs> agregar;
    @FXML
    private TableColumn<songs, String> nombree;

    @FXML
    private TableColumn<songs, String> pathh;

    @FXML
    private TableColumn<songs, String> pos;

    @FXML
    private CheckBox fav;

    @FXML
    private CheckBox fav2;

    @FXML
    private Label nombresong;
    @FXML
    private Label nssa1;
    @FXML
    private Label nssa2;
    @FXML
    private Label nssa3;
    @FXML
    private Button charge;
    @FXML
    private Button borrar;
    @FXML
    private ProgressBar progreso;
    @FXML
    private ChoiceBox<String> volumen;
    private String[] volumenes = {"0%","20%","40%","60%","80%","100%" };
    @FXML
    private Label favorita;
    @FXML
    private Button arduino;
    private String usuario;
    private Timer timer;
    private TimerTask task;
    private boolean running;
    private static SerialPort sp;
    /**
     * Variable para obtener Files de canciones
     */
    private File canci;
    public LinkedList playlist = new LinkedList("1");

    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert cerrar2 != null : "fx:id=\"cerrar2\" was not injected: check your FXML file 'MP3Screen.fxml'.";
        assert logout != null : "fx:id=\"logout\" was not injected: check your FXML file 'MP3Screen.fxml'.";

    }

    /**
     * M??todo para cerrar la pantalla
     */
    public void btncerrar2(ActionEvent event) {
        stage.close();
    }

    /**
     * M??todo para cerrar sesi??n
     */
    public void regresar(ActionEvent event) {
        controlladorLogin.show();
        stage.close();
    }

    /**
     * M??todo para mostrar en pantalla los datos del usuario, utilizando una lista simple.
     */
    public void init(Stage stage, LoginController loginController, String[] list) {
        stage.getIcons().add(new Image("C:\\Users\\Yoshant\\Desktop\\Proyecto--1---CE-Music-Player\\Proyecto1_CE_MusicPlayer_Carlos-Felipe\\src\\Imagenes\\icono.png"));
        user1.setText(list[0]);
        correo1.setText(list[3]);
        prov1.setText(list[2]);
        usuario = list[0];
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        fecha.setText(dtf.format(LocalDateTime.now()));
        this.controlladorLogin = loginController;
        this.stage = stage;
    }

    FileChooser seleccionador = new FileChooser();

    static List<songs> cancion = new ArrayList<>();

    String nombrec;

    /**
     * M??todo para crear un ??lbum, solo se puede agregar una canci??n mientras se crea el ??lbum
     * Si se crean dos con el mismo nombre, se reescribe uno. Se selecciona una canci??n, y nos da tres objetos,
     * Su nombre, path y si es favorita o no [esto ??iltimo con una chechbox]
     */
    @FXML
    public void getText(MouseEvent event){
        int i = 0;
        if (fav.isSelected()) {
            i = 1;
        } else {
            i = 0;
        }
        //FileChooser.ExtensionFilter ex1= new FileChooser.ExtensionFilter("MP3 files", ".mp3");
        String biblioteca = biblioname.getText();
        //seleccionador.getExtensionFilters().addAll(ex1);
        canci = seleccionador.showOpenDialog(new Stage());
        if (canci == null) {
            nssa2.setText("No se pueden crear biliotecas, sin minimo una canci??n dentro");
        } else {
            nssa2.setText("Biblioteca Creada");

            nombrec = canci.getName();
            String cancan = String.valueOf(canci);

            try {
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                DOMImplementation implementation = builder.getDOMImplementation();

                Document documento = implementation.createDocument(null, "biblioteca", null);
                documento.setXmlVersion("1.0");


                Element canciones = documento.createElement("Cancion");


                Element path = documento.createElement("path");
                Text textPath = documento.createTextNode(cancan);
                path.appendChild(textPath);
                canciones.appendChild(path);

                Element nombre = documento.createElement("nombre");
                Text textNombre = documento.createTextNode(nombrec);
                nombre.appendChild(textNombre);
                canciones.appendChild(nombre);

                Element favorita = documento.createElement("favorita");
                Text textFav = documento.createTextNode(Integer.toString(i));
                favorita.appendChild(textFav);
                canciones.appendChild(favorita);

                documento.getDocumentElement().appendChild(canciones);

                Source source = new DOMSource(documento);
                Result result = new StreamResult(new File(String.valueOf(usuario) + "/" + biblioteca + ".xml"));

                Transformer transformer = TransformerFactory.newInstance().newTransformer();
                transformer.transform(source, result);


            } catch (ParserConfigurationException ex) {
                Logger.getLogger(XML.class.getName()).log(Level.SEVERE, null, ex);
            } catch (TransformerException e) {
                throw new RuntimeException(e);
            }

        }
    }

    /**
     * M??todo para agregar canciones a una biblioteca. Se tiene que poner el nombre exacto de la biblioteca a agregar la
     * canci??n.
     */
    @FXML
    public void addTo(MouseEvent event) throws ParserConfigurationException, IOException, SAXException, TransformerException {
        int i = 0;
        if (fav.isSelected()) {
            i = 1;
        } else {
            i = 0;
        }
        String biblioteca = biblioselec.getText();
        canci = seleccionador.showOpenDialog(new Stage());
        if (canci == null) {
            nssa1.setText("No se seleccion?? ning??n archivo");
        } else {
            nssa1.setText("Canci??n agregada");
            nombrec = canci.getName();
            String cancan = String.valueOf(canci);


            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(new File(String.valueOf(usuario) + "/" + biblioteca + ".xml"));
            doc.getDocumentElement().normalize();
            Node nodoRaiz = doc.getDocumentElement();

            Element nuevaCancion = doc.createElement("cancion");


            Element nuevoPath = doc.createElement("path");
            nuevoPath.setTextContent(cancan);
            nuevaCancion.appendChild(nuevoPath);

            Element nuevoNombre = doc.createElement("nombre");
            nuevoNombre.setTextContent(nombrec);
            nuevaCancion.appendChild(nuevoNombre);

            Element nuevoFavorita = doc.createElement("favorita");
            nuevoFavorita.setTextContent(Integer.toString(i));
            nuevaCancion.appendChild(nuevoFavorita);

            nodoRaiz.appendChild(nuevaCancion);

            TransformerFactory transFactory = TransformerFactory.newInstance();
            Transformer transformer = transFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(usuario + "/" + biblioteca + ".xml"));
            transformer.transform(source, result);

        }

    }

    public void borrarPlaylist(ActionEvent event) {
        seleccionador.setInitialDirectory(new File("C:/Users/Yoshant/Desktop/Proyecto--1---CE-Music-Player" + "/" + usuario));
        File biblio = seleccionador.showOpenDialog(new Stage());
        biblio.delete();
    }

    public void cargarbiblio(MouseEvent mouseEvent) throws ParserConfigurationException,
            SAXException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        seleccionador.setInitialDirectory(new File("C:/Users/Yoshant/Desktop/Proyecto--1---CE-Music-Player" + "/" + usuario));
        File biblio = seleccionador.showOpenDialog(new Stage());
        if (biblio == null) {
            nssa3.setText("No se seleccion?? ning??n archivo");
        } else {
            nssa3.setText("Biblioteca agregada a la cola de reproducci??n");

            String biblioteca = String.valueOf(biblio);
            Document document = builder.parse(new File(biblioteca));
            NodeList nodeList = document.getDocumentElement().getChildNodes();
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element elem = (Element) node;
                    String direccion = elem.getElementsByTagName("path")
                            .item(0).getChildNodes().item(0).getNodeValue();
                    String namesong = elem.getElementsByTagName("nombre").item(0)
                            .getChildNodes().item(0).getNodeValue();
                    String favorite = elem.getElementsByTagName("favorita").item(0)
                            .getChildNodes().item(0).getNodeValue();
                    Nodos can = new Nodos(direccion, namesong, favorite);

                    playlist.a??adir(direccion, namesong, favorite);
                }
            }
            String arch1 = playlist.current.getPath();
            String arch = arch1.replace("/", "\\\\");
            File f = new File(arch);
            URI dir = f.toURI();
            musica = new Media(dir.toString());
            reproductor = new MediaPlayer(musica);
        }

    }

    /**
     * M??todo reproducir la m??sica presionando el bot??n "play"
     */
    public void reproducir(ActionEvent event) {
        playmusic();
        nombresong.setText(playlist.current.getNombrecan().replace(".mp3", ""));
    }
    /**
     * M??todo que reproduce la canci??n, sin necesidad de presionar un bot??n, para contrarrestar los "action event", ya
     * que provoca conflictos con el arduino
     */
    public void playmusic(){
        favorita.setVisible(false);
        reproductor.play();
        empezarTimer();
        if (playlist.getCurrent().getFav().equals("1")) {
            favorita.setVisible(true);
        }
    }
    /**
     * M??todo para pausar el audio reproduciendose, presionando un bot??n
     */
    public void pausar(ActionEvent event) {
        parar();
    }
    /**
     * M??todo para pausar el audio reproduciendose, sin necesidad de presionar un bot??n, para contrarrestar los
     * "action event" ya que provoca conflictos con el arduino
     */
    public void parar(){
        reproductor.pause();
        cancelTimer();
    }
    /**
     * M??todo para reiniciar el audio reproduciendose, presionando un bot??n.
     */
    public void reiniciar(ActionEvent event) {
        regresar();
    }
    /**
     * M??todo para reiniciar el audio reproduciendose, sin necesidad de presionar un bot??n, para contrarrestar
     * los "action event" ya que provoca conflictos con el arduino
     */
    public void regresar(){
        reproductor.stop();
        progreso.setProgress(0);
    }
    /**
     * M??todo para reproducir el siguiente audio en la biblioteca musical, presionando un bot??n.
     */
    public void sigSong(ActionEvent event) {
        siguiente();
        nombresong.setText(playlist.current.getNombrecan().replace(".mp3", ""));
    }
    /**
     * M??todo para reproducir el siguiente audio en la biblioteca musical, sin la necesidad de presionar un bot??n, esto
     * para contrarrestar los "action event" ya que provoca conflictos con el arduino.
     */
    public void siguiente(){
        favorita.setVisible(false);
        reproductor.stop();
        playlist.adelanteCurrent();
        String arch1 = playlist.current.getPath();
        String arch = arch1.replace("/", "\\\\");
        File f = new File(arch);
        URI dir = f.toURI();
        musica = new Media(dir.toString());
        reproductor = new MediaPlayer(musica);
        reproductor.play();
        nombresong.setText(playlist.current.getNombrecan().replace(".mp3", ""));
        progreso.setProgress(0);
        empezarTimer();
        if (running) {
            cancelTimer();
        }
        if (playlist.getCurrent().getFav().equals("1")) {
            favorita.setVisible(true);
        }
    }
    /**
     * M??todo para reproducir el audio anterior en la biblioteca musical, presionando un bot??n.
     */
    public void antSong(ActionEvent event) {
        anterior();
        nombresong.setText(playlist.current.getNombrecan().replace(".mp3", ""));
    }
    /**
     * M??todo para reproducir el audio anterior en la biblioteca musical, sin la necesidad de presionar un bot??n, esto
     * para contrarrestar los "action event" ya que provoca conflictos con el arduino.
     */
    public void anterior(){
        favorita.setVisible(false);
        reproductor.stop();
        playlist.atrasCurrent();
        String arch1 = playlist.current.getPath();
        String arch = arch1.replace("/", "\\\\");
        File f = new File(arch);
        URI dir = f.toURI();
        musica = new Media(dir.toString());
        reproductor = new MediaPlayer(musica);
        reproductor.play();
        progreso.setProgress(0);
        empezarTimer();
        if (running) {
            cancelTimer();
        }
        if (playlist.getCurrent().getFav().equals("1")) {
            favorita.setVisible(true);
        }
    }
    /**
     * M??todo para iniciar la barra de progreso de la canci??n.
     */
    public void empezarTimer() {
        timer = new Timer();
        task = new TimerTask() {
            @Override
            public void run() {
                running = true;
                double current1 = reproductor.getCurrentTime().toSeconds();
                double end = musica.getDuration().toSeconds();
                progreso.setProgress(current1 / end);
                if (current1 / end == 1) {
                    cancelTimer();
                    reproductor.stop();
                    playlist.adelanteCurrent();
                    String arch1 = playlist.current.getPath();
                    String arch = arch1.replace("/", "\\\\");
                    File f = new File(arch);
                    URI dir = f.toURI();
                    musica = new Media(dir.toString());
                    reproductor = new MediaPlayer(musica);
                    reproductor.play();
                    nombresong.setText(playlist.current.getNombrecan().replace(".mp3", ""));
                    progreso.setProgress(0);
                    empezarTimer();
                    if (running) {
                        cancelTimer();
                    }
                }
            }

        };
        timer.scheduleAtFixedRate(task, 1000, 1000);

    }
    /**
     * M??todo para pader cancelar el aumento de la barra de progreso, para poder hacer acciones como pausar, o reiniciar
     * la canci??n.
     */
    public void cancelTimer() {
        running = false;
        timer.cancel();
    }
    /**
     * M??todo para poder ajustar la barra de sonido.
     */
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        volumen.getItems().addAll(volumenes);
        volumen.setOnAction(this::getVolume);
        progreso.setStyle("-fx-accent: #252525");
    }
    public void getVolume(ActionEvent event){
        String volume = (volumen.getValue().replace("%", ""));
        int volumeSelected = Integer.valueOf(volume);
        reproductor.setVolume(volumeSelected*0.01);
    }
    public void setVolume1(int volumeS){
        reproductor.setVolume(volumeS*0.01);
    }
    /**
     * M??todo para inicializar el arduino al presionar un bot??n
     */
    public void activar(ActionEvent event){
        conectar();

    }

    /**
     * M??todo para inicializar y conectar el arduino.
     */
    public void conectar(){
        SerialPort port = new SerialPort("COM3");
        try {
            port.openPort();
            port.setParams(SerialPort.BAUDRATE_9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
            port.addEventListener((SerialPortEvent event) -> {
                if (event.isRXCHAR()) {
                    try {
                        String p = port.readString();
                        if(p.equals("1")){
                            playmusic();
                        }
                        if(p.equals("2")){
                            parar();
                        }
                        if(p.equals("3")){
                            regresar();
                        }
                        if(p.equals("4")){
                            siguiente();
                        }
                        if(p.equals("5")){
                            anterior();
                        }
                        if(p.equals("a")){
                            setVolume1(0);
                        }
                        if(p.equals("b")){
                            setVolume1(20);
                        }
                        if(p.equals("c")){
                            setVolume1(40);
                        }
                        if(p.equals("d")){
                            setVolume1(60);
                        }
                        if(p.equals("e")){
                            setVolume1(80);
                        }
                        if(p.equals("f")){
                            setVolume1(100);
                        }
                    } catch (SerialPortException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch(SerialPortException e){
            e.printStackTrace();
        }
    }
    @FXML
    public void pago(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Premium/Pagopremium.fxml"));
        Parent root = loader.load();
        Premium controller = loader.getController();
        Scene scene = new Scene (root);
        Stage stage = new Stage ();
        stage.setScene(scene);
        controller.init(stage, this);
        stage.show();
        this.stage.close();
    }

    public void show() {
        stage.show();
    }
}
