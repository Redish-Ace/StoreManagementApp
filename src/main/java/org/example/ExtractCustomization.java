package org.example;

import java.io.File;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;

public class ExtractCustomization {
    public String[] getFont(String objectFind){
        String[] atributes = new String[3];
        try {
            File file = new File("D:\\projects\\java\\IndividualStudy\\src\\main\\java\\org\\example\\Font.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(file);

            NodeList infoNodes = document.getElementsByTagName("font");
            for (int i = 0; i < infoNodes.getLength(); i++) {
                Node infoNode = infoNodes.item(i);
                if (infoNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element infoElement = (Element) infoNode;
                    String object = infoElement.getElementsByTagName("object").item(0).getTextContent();

                    if (objectFind.equals(object)) {
                        atributes[0] = infoElement.getElementsByTagName("name").item(0).getTextContent();
                        String format = infoElement.getElementsByTagName("format").item(0).getTextContent();
                        switch (format){
                            case "Plain" -> atributes[1] = "0";
                            case "Bold" -> atributes[1] = "1";
                            case "Italic" -> atributes[1] = "2";
                        }
                        atributes[2] = infoElement.getElementsByTagName("size").item(0).getTextContent();
                    }
                }
            }
        }catch(Exception ex){
                ex.printStackTrace();
        }
        return atributes;
    }

    public void updateFont(String objectFind, String name, String format, String size) {
        try {
            File file = new File("D:\\projects\\java\\IndividualStudy\\src\\main\\java\\org\\example\\Font.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(file);

            Node nameNode, formatNode, sizeNode;
            NodeList infoNodes = document.getElementsByTagName("font");
            for (int i = 0; i < infoNodes.getLength(); i++) {
                Node infoNode = infoNodes.item(i);
                if (infoNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element infoElement = (Element) infoNode;
                    String object = infoElement.getElementsByTagName("object").item(0).getTextContent();

                    if (objectFind.equals(object)) {
                        nameNode = infoElement.getElementsByTagName("name").item(0);
                        nameNode.setTextContent(name);
                        formatNode = infoElement.getElementsByTagName("format").item(0);
                        formatNode.setTextContent(format);
                        sizeNode = infoElement.getElementsByTagName("size").item(0);
                        sizeNode.setTextContent(size);
                    }
                }
            }

            // Write the updated document back to the file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "no");
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(file);
            transformer.transform(source, result);


        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public int getColor(String objectFind){
        int rgb = 0;
        try {
            File file = new File("D:\\projects\\java\\IndividualStudy\\src\\main\\java\\org\\example\\Color.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(file);

            NodeList infoNodes = document.getElementsByTagName("color");
            for (int i = 0; i < infoNodes.getLength(); i++) {
                Node infoNode = infoNodes.item(i);
                if (infoNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element infoElement = (Element) infoNode;
                    String object = infoElement.getElementsByTagName("object").item(0).getTextContent();

                    if (objectFind.equals(object)) {
                        rgb = Integer.parseInt(infoElement.getElementsByTagName("shade").item(0).getTextContent());
                    }
                }
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return rgb;
    }

    public void updateColor(String objectFind, int rgb) {
        try {
            File file = new File("D:\\projects\\java\\IndividualStudy\\src\\main\\java\\org\\example\\Color.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(file);

            Node shadeNode;
            NodeList infoNodes = document.getElementsByTagName("color");
                for (int i = 0; i < infoNodes.getLength(); i++) {
                    Node infoNode = infoNodes.item(i);
                    if (infoNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element infoElement = (Element) infoNode;
                        String object = infoElement.getElementsByTagName("object").item(0).getTextContent();

                        if (objectFind.equals(object)) {
                            shadeNode = infoElement.getElementsByTagName("shade").item(0);
                            shadeNode.setTextContent(rgb + "");
                        }
                    }
                }

                // Write the updated document back to the file
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                transformer.setOutputProperty(OutputKeys.INDENT, "no");
                DOMSource source = new DOMSource(document);
                StreamResult result = new StreamResult(file);
                transformer.transform(source, result);


        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}