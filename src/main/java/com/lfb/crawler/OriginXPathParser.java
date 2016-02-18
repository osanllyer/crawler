package com.lfb.crawler;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class OriginXPathParser {

	public static String parse(String xmlStr, String xpathStr){
		
		Document doc = getDoc(xmlStr);
		XPath xpath = XPathFactory.newInstance().newXPath();
		
		try {
			String res = (String)xpath.evaluate(xpathStr, 
					doc, 
					XPathConstants.STRING);
			
			return res;
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	//get doc
	private static Document getDoc(String xmlStr){
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setValidating(false);
        Document doc = null;
		try {
	        DocumentBuilder db = dbf.newDocumentBuilder();
			doc = db.parse(new ByteArrayInputStream(xmlStr.getBytes()));
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return doc;
	}
	
	
}
