package model.director;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import model.entity.Entity;
import model.factories.AvatarReader;
import model.factories.EntityFactory;
import model.factories.InteractiveFactory;
import model.factories.ObstacleFactory;
import model.factories.OneShotFactory;
import model.factories.TakeableFactory;
import model.gameObject.MapObject;
import model.item.Item;
import model.map.GameMap;

public class MapInstantiator {
	private static MapInstantiator me = null;
	
	public MapInstantiator() {
		
	}
	
	public static MapInstantiator getInstance() {
		if(me == null) me = new MapInstantiator();
		return me;
	}
	
	public Entity createAvatarFromFile(File f) {
		
		Document doc = null;
		
		try{

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = null;
			//Get the DOM builder
			builder = factory.newDocumentBuilder();
			doc = builder.parse(f);
		} catch (ParserConfigurationException e) {
            // TODO Auto-generated catch block
            System.out.println("Parser Coniguration Exception");
            e.printStackTrace();
        } catch (SAXException e) {
            // TODO Auto-generated catch block
            System.out.println("SAXException");
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.println("IO Exception");
            e.printStackTrace();
        }
		
		Element head = doc.getDocumentElement();
		
		return new AvatarReader().generateAndAddToMap(head);
	}
	
	public void createMapsFromFile(File f) {
		
		//clear maps
		ActiveMapManager.getInstance().clearMaps();
		
		Document doc = null;
		
		try{

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = null;
			//Get the DOM builder
			builder = factory.newDocumentBuilder();
			doc = builder.parse(f);
		} catch (ParserConfigurationException e) {
            // TODO Auto-generated catch block
            System.out.println("Parser Coniguration Exception");
            e.printStackTrace();
        } catch (SAXException e) {
            // TODO Auto-generated catch block
            System.out.println("SAXException");
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.println("IO Exception");
            e.printStackTrace();
        }
		
		Element head = doc.getDocumentElement();
		
		NodeList nodes = head.getElementsByTagName("map");
		
		for(int i = 0; i < nodes.getLength(); i++)
		{
			Element e = (Element) nodes.item(i);
			
			GameMap m = new GameMap(Integer.parseInt(e.getAttribute("id")));
			
			for(MapObject o : new EntityFactory().generate(e)) {
				Entity n = (Entity) o;
				
				m.addEntity(n, n.getLocation());
			}
			
			for(MapObject o : new InteractiveFactory().generate(e)) {
				Item n = (Item) o;
				
				m.addItem(n, n.getLocation());
			}
			
			for(MapObject o : new TakeableFactory().generate(e)) {
				Item n = (Item) o;
				
				m.addItem(n, n.getLocation());
			}
			
			for(MapObject o : new OneShotFactory().generate(e)) {
				Item n = (Item) o;
				
				m.addItem(n, n.getLocation());
			}
			
			for(MapObject o : new ObstacleFactory().generate(e)) {
				Item n = (Item) o;
				
				m.addItem(n, n.getLocation());
			}
			
			ActiveMapManager.getInstance().addMap(m);
		}
	}
}
