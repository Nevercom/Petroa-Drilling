package com.nevercom.android.petroleum;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLParser {
	
	
	public static void parse(String xmlURL){
		
	URL url;
	
	try {
		
		url = new URL(xmlURL);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		
		if(conn.getResponseCode() == HttpURLConnection.HTTP_OK){
              DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
              DocumentBuilder db = dbf.newDocumentBuilder();
              Document doc;
              doc = db.parse(url.openStream());
              doc.getDocumentElement().normalize();
              NodeList itemLst = doc.getElementsByTagName("dic");
               /*
				<?xml version="1.0" encoding="UTF-8" ?>
				<dic query="casing">
					<result>3 Result(s) was found in dictionary :)</result>
					<mean id=0>n: 1. steel pipe placed in an oil...</mean>
					<image id=0>http://dic.iran-spe.com/img/words/casing.jpg</image>
					<mean id=1>n: Steel pipe placed in an oil...</mean>
					<mean id=2>one of several strings of steel pipe...</mean>
				</dic>
               */
               
//              WordDef.PoetName    = new String[itemLst.getLength()];
//              WordDef.PoetID      = new String[itemLst.getLength()];
//              WordDef.DownloadURL = new String[itemLst.getLength()];
//              WordDef.CategoryID  = new String[itemLst.getLength()];
             
              for(int i=0; i < itemLst.getLength(); i++){
                   
                    Node item = itemLst.item(i);
                    if(item.getNodeType() == Node.ELEMENT_NODE){
                          Element ielem = (Element)item;
                          NodeList catName     = ielem.getElementsByTagName("CatName");
                          NodeList poetID      = ielem.getElementsByTagName("PoetID");
                          NodeList catID       = ielem.getElementsByTagName("CatID"); 
                          NodeList downloadURL = ielem.getElementsByTagName("DownloadUrl");
                          
                          
                          
//                          WordDef.PoetName[i]    = catName.item(0).getChildNodes().item(0).getNodeValue();
//                          WordDef.PoetID[i]      = poetID.item(0).getChildNodes().item(0).getNodeValue();
//                          WordDef.DownloadURL[i] = downloadURL.item(0).getChildNodes().item(0).getNodeValue();
//                          WordDef.CategoryID[i]  = catID.item(0).getChildNodes().item(0).getNodeValue();
 
                    }
                   
              }
              
        }
		
	} catch (MalformedURLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (DOMException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (ParserConfigurationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SAXException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	}
	

}