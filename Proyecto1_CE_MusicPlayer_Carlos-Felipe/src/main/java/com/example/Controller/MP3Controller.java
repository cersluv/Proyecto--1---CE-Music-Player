package com.example.Controller;


import com.example.XML;
import com.example.songs;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
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
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MP3Controller {
    private Stage stage;
    private LoginController controlladorLogin;


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




    private File canci;


    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert cerrar2 != null : "fx:id=\"cerrar2\" was not injected: check your FXML file 'MP3Screen.fxml'.";
        assert logout != null : "fx:id=\"logout\" was not injected: check your FXML file 'MP3Screen.fxml'.";

    }

    public void btncerrar2(ActionEvent event) {
        stage.close();
    }

    public void regresar(ActionEvent event) {
        controlladorLogin.show();
        stage.close();
    }

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


            Element canciones = documento.createElement("Canciones");


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

    @FXML
    public void addTo(MouseEvent event) throws ParserConfigurationException, IOException, SAXException {
        int i = 0;
        if (fav.isSelected()) {
            i = 1;
        } else {
            i = 0;
        }
        String biblioteca = biblioselec.getText();
        canci = seleccionador.showOpenDialog(new Stage());
        nombrec = canci.getName();
        String cancan = String.valueOf(canci);


        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc = docBuilder.parse(new File(biblioselec+".xml"));
        doc.getDocumentElement().normalize();
        Node nodoRaiz = doc.getDocumentElement();

        Element nuevaCancion=doc.createElement("cancion");
        Element nuevoPath = doc.createElement("path");



    }

}