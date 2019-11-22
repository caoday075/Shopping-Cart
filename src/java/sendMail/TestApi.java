package sendMail;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class TestApi {   
public static void main(String[] args) throws MalformedURLException, IOException, XPathExpressionException, ParserConfigurationException, SAXException  {
	String idClient = "Your Customer ID"; 
	String APIKey = " API Key";   //On demand  
	String BodyHtml ="<html><head></head><body> Send from API </body></html>";
	String Subject = "Send from API with Java";
	String Email = "example@mailpro.com,,,,,,,,,,,,,,,,,,,,,,,,,";
	String Title = "Name of the AddressBook";

	String AddressBookID = AddAddressBook(APIKey,idClient,Title);
	AddEmail(APIKey, idClient, Email, AddressBookID);
	String MessageID = AddMessage(APIKey, idClient, BodyHtml, Subject);
	String IdExpediteur = GetEmailSender(APIKey, idClient);
	AddSend (APIKey, idClient, IdExpediteur, AddressBookID, MessageID);
}  
public static String AddAddressBook(String APIKey,String idClient,String Title) throws MalformedURLException, IOException, XPathExpressionException, ParserConfigurationException, SAXException{
	String urlParameters = "APIKey="+APIKey+"&IDClient="+idClient+"&Title="+Title;
	String posturl = "https://DomainName/v2/addressbook/add.xml";
	String TagName = "AddressBook";
	String TagValue = "AddressBookId";
	String content = postData(posturl,urlParameters);       
	return XmlRead(content,  TagName,  TagValue);
}
public static String GetEmailSender(String APIKey,String idClient) throws XPathExpressionException, ParserConfigurationException, SAXException, IOException{
	String getUrl = "https://DomainName/v2/senderEmail/list.xml?APIKey="+APIKey+"&IDClient="+idClient;
	String TagName = "ExpEmail";
	String TagValue = "ExpEmailId";
	String content = getData(getUrl);
	String IdExp = XmlRead(content,  TagName,  TagValue);
	return IdExp;
}  
public static void AddEmail(String APIKey,String idClient,String Email,String AddressBookID) throws MalformedURLException, IOException, ParserConfigurationException, SAXException{
	String urlParameters = "APIKey="+APIKey+"&IDClient="+idClient+"&emailList="+Email+"&AddressBookID="+AddressBookID;
	String posturl = "https://DomainName/v2/email/add.xml?";
	postData(posturl,urlParameters); 
} 
public static String AddMessage(String APIKey,String idClient,String BodyHtml,String Subject)throws MalformedURLException, IOException, ParserConfigurationException, SAXException, XPathExpressionException{
	String urlParameters = "APIKey="+APIKey+"&IDClient="+idClient+"&BodyHTML="+ BodyHtml +"&Subject="+ Subject +"&Language=EN&linkAlign=center&LinkUp=1";
	String posturl = "https://DomainName/v2/message/add.xml";
	String TagName = "Message";
	String TagValue = "MessageId";
	String content = postData(posturl,urlParameters);
	String IdMessage = XmlRead(content,  TagName,  TagValue);
	return IdMessage;
}  
public static void AddSend(String APIKey,String idClient,String IdExpediteur,String AddressBookID,String MessageID )throws MalformedURLException, IOException, ParserConfigurationException, SAXException{
	String urlParameters = "APIKey="+APIKey+"&IDClient="+idClient+"&IDAddressBook="+AddressBookID+"&IDMessage="+MessageID+"&IDEmailExp="+IdExpediteur+"&campaign=1";
	String posturl = "https://DomainName/v2/send/add.xml";
	postData(posturl,urlParameters);      
}  
public static String getData (String getUrl) throws MalformedURLException, IOException{
	URL website = new URL(getUrl);
	URLConnection connection = website.openConnection();
	BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	StringBuilder response = new StringBuilder();
	String inputLine;
	while ((inputLine = in.readLine()) != null) 
		response.append(inputLine);
	in.close();
	return response.toString();
} 
public static String XmlRead(String content, String TagName, String TagValue) throws XPathExpressionException, ParserConfigurationException, SAXException, IOException  {
	DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
	DocumentBuilder b = f.newDocumentBuilder();
	Document doc = b.parse(new ByteArrayInputStream(content.getBytes("UTF-8")));
	NodeList nl = doc.getElementsByTagName(TagName);
	Element element = (Element) nl.item(0);
	Node Id = element.getElementsByTagName(TagValue).item(0); 
	String response = Id.getTextContent();
	return response;        
}
public static String postData(String posturl,String urlParameters) throws IOException, ParserConfigurationException, SAXException{
	URL url = new URL(posturl);
	URLConnection conn = url.openConnection();
	conn.setDoOutput(true);
	OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
	writer.write(urlParameters);
	writer.flush();
	String line;
	StringBuffer answer = new StringBuffer();
	BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream())); 
	while ((line = reader.readLine()) != null) {
		answer.append(line);    
	}
	String response = answer.toString();                       
	writer.close();
	reader.close(); 
	return response;
}
}