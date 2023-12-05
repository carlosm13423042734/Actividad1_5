package actividad_1_5;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.*;
import java.io.File;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class Actividad_1_5 {

    //Heredamos de la classe DefaultHandler para sobreescribir los métodos startElement, endElement y characters
    public class LibrosSAXhandler extends DefaultHandler {

        public LibrosSAXhandler() {
        }
        //Comienzo de etiqueta
        @Override
        public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {

            if (qName.equals("Libro")) {
                System.out.println("Publicado en:" + atts.getValue(atts.getQName(0)));

            } else if (qName.equals("Titulo")) {
                System.out.print("\n " + "El título es: ");

            } else if (qName.equals("Autor")) {
                System.out.print("\n " + "El author es:");
            }
        }
        //Final de etiqueta
        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {

            if (qName.equals("Libro")) {
                System.out.println("\n---------------------- - ");
            }
        }
        //Método de texto dentro de una etiqueta
        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {

            String car = new String(ch, start, length);
            car = car.replaceAll("\t", ";");//quita todos los tabuladores
            car = car.replaceAll("\n", ";");
            System.out.print(car);
        }

    }
    
    public class AccesoXMLSAX {

        SAXParser parser;

        public int parsearXMLconLibrosSAXhandler(File f) {
            try {
                //Parseamos con el manejador
                SAXParserFactory factory = SAXParserFactory.newInstance();
                parser = factory.newSAXParser();
                LibrosSAXhandler sh = new LibrosSAXhandler();
                //Hacemos que el archivo de entrada(XML) pase a ser un archivo SAX con este método. Si lo consigue devolverá 0 y si da error devolverá -1
                parser.parse(f, sh);
                return 0;
            } catch (Exception e) {
                e.printStackTrace();
                return -1;
            }
        }
    }
}
