package com.example.Controller;


import com.example.LinkedList;
import com.example.Nodos;
import com.example.XML;
import com.example.songs;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Controlador de la pantalla principal, en este se mostraran los datos del usuario, agregar y crear bibliotecas
 * musicales, y reproducirlas
 *
 */
public class MP3Controller {
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
    private Button charge;

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
     * Método para cerrar la pantalla
     */
    public void btncerrar2(ActionEvent event) {
        stage.close();
    }
    /**
     * Método para cerrar sesión
     */
    public void regresar(ActionEvent event) {
        controlladorLogin.show();
        stage.close();
    }

    /**
     * Método para mostrar en pantalla los datos del usuario, utilizando una lista simple.
     */
    public void init(Stage stage, LoginController loginController, String[] list) {
        user1.setText(list[0]);
        correo1.setText(list[3]);
        prov1.setText(list[2]);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        fecha.setText(dtf.format(LocalDateTime.now()));
        this.controlladorLogin = loginController;
        this.stage = stage;
            }

    FileChooser seleccionador = new FileChooser();

    static List<songs> cancion = new ArrayList<>();

    String nombrec;
    /**
     * Método para crear un álbum, solo se puede agregar una canción mientras se crea el álbum
     * Si se crean dos con el mismo nombre, se reescribe uno. Se selecciona una canción, y nos da tres objetos,
     * Su nombre, path y si es favorita o no [esto úiltimo con una chechbox]
     */
    @FXML
    public void getText(MouseEvent event) {
        int i = 0;
        if (fav.isSelected()) {
            i = 1;
        } else {
            i = 0;
        }
        String biblioteca = biblioname.getText();
        canci = seleccionador.showOpenDialog(new Stage());
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
            Text textNombre= documento.createTextNode(nombrec);
            nombre.appendChild(textNombre);
            canciones.appendChild(nombre);

            Element favorita = documento.createElement("favorita");
            Text textFav = documento.createTextNode(Integer.toString(i));
            favorita.appendChild(textFav);
            canciones.appendChild(favorita);

            documento.getDocumentElement().appendChild(canciones);

            Source source = new DOMSource(documento);
            Result result = new StreamResult(new File(biblioteca+ ".xml"));

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, result);



        }catch (ParserConfigurationException ex){
            Logger.getLogger(XML.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }

    }
    /**
     * Método para agregar canciones a una biblioteca. Se tiene que poner el nombre exacto de la biblioteca a agregar la
     * canción.
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
        System.out.println(biblioteca);
        canci = seleccionador.showOpenDialog(new Stage());
        nombrec = canci.getName();
        String cancan = String.valueOf(canci);


        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc = docBuilder.parse(new File(biblioteca+".xml"));
        doc.getDocumentElement().normalize();
        Node nodoRaiz = doc.getDocumentElement();

        Element nuevaCancion=doc.createElement("cancion");


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
        StreamResult result = new StreamResult(new File (biblioteca+".xml"));
        transformer.transform(source, result);


    }

    public void cargarbiblio(MouseEvent mouseEvent) throws ParserConfigurationException,
            SAXException, IOException{

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File("Pa perrear.xml"));
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

                playlist.añadir(direccion, namesong, favorite);
                //playlist.showPlaylist();
            }
        }
        File f = new File(playlist.current.getPath());
        System.out.println(playlist.current.getPath());
        URI dir = f.toURI();
        musica = new Media(String.valueOf(dir));
        reproductor = new MediaPlayer(musica);
    }
    public void reproducir(){
        reproductor.play();
    }
    public void pausar(){
        reproductor.pause();
    }
    public void reiniciar(){
        reproductor.seek(Duration.seconds(0));
        reproductor.play();
    }
    public void sigSong(){
    }
    public void antSong(){
    }


}