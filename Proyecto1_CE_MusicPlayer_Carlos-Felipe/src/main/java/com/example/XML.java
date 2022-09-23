package com.example;


import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

public class XML {

    public static void main(String[] args) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            DOMImplementation implementation = builder.getDOMImplementation();

            Document documento = implementation.createDocument(null, "biblioteca", null);
            documento.setXmlVersion("1.0");

            Element canciones = documento.createElement("Canciones");


            Element path = documento.createElement("path");
            Text textPath = documento.createTextNode(" path");
            path.appendChild(textPath);
            canciones.appendChild(path);

            Element nombre = documento.createElement("nombre");
            Text textNombre= documento.createTextNode(" nombree");
            nombre.appendChild(textNombre);
            canciones.appendChild(nombre);

            Element favorita = documento.createElement("favorita");
            Text textFav = documento.createTextNode(" favorita");
            favorita.appendChild(textFav);
            canciones.appendChild(favorita);

            documento.getDocumentElement().appendChild(canciones);

            Source source = new DOMSource(documento);
            Result result = new StreamResult(new File("prueba.xml"));

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, result);



        }catch (ParserConfigurationException ex){
            Logger.getLogger(XML.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }
    }



}
