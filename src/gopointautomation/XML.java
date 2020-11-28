/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gopointautomation;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 *
 * @author daniel
 */
public class XML
{

    public static String filePath = "config.xml";

    public static String ReadXML(String Node)
    {
        String Content = "";
        
        java.io.File File = new java.io.File(filePath);

        if (File.exists())
        {
            File xmlFile = new File(filePath);

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();

            DocumentBuilder dBuilder;

            try
            {
                dBuilder = dbFactory.newDocumentBuilder();

                Document doc = dBuilder.parse(xmlFile);

                doc.getDocumentElement().normalize();

                String rootNode = doc.getDocumentElement().getNodeName();

                NodeList nodeList = doc.getElementsByTagName(rootNode);

                Content = (String) ((Element) nodeList.item(0)).getElementsByTagName(Node).item(0).getChildNodes().item(0).getNodeValue();
            }
            catch (Exception e)
            {
                Content = e.getMessage();
            }
        }
        else
        {
            Content = "No File Exist!";
        }
        
        return Content;
    }
}
