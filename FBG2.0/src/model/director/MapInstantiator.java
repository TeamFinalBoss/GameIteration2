package model.director;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import controller.keyBindings.KeyBindings;
import model.entity.Entity;
import model.factories.AreaEffectFactory;
import model.factories.AvatarReader;
import model.factories.BindingReader;
import model.factories.EntityFactory;
import model.factories.InteractiveFactory;
import model.factories.ObstacleFactory;
import model.factories.OneShotFactory;
import model.factories.TakeableFactory;
import model.factories.TileFactory;
import model.factories.TrapFactory;
import model.gameObject.MapObject;
import model.item.Item;
import model.map.GameMap;
import model.map.areaEffect.AreaEffect;
import model.map.areaEffect.TeleportAreaEffect;
import model.map.pair.CoordinatePair;
import model.map.tile.trap.Trap;

public class MapInstantiator {
	private static MapInstantiator me = null;
	
	public MapInstantiator() {
		
	}
	
	public static MapInstantiator getInstance() {
		if(me == null) me = new MapInstantiator();
		return me;
	}
	
	public boolean checkValidity(File f) {
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
		
		NodeList nodes = head.getElementsByTagName("gamesave");
		Element wanted = (Element) nodes.item(0);
		
		if(wanted.hasAttribute("dontloadme")) return false;
		return true;
	}
	
	public KeyBindings createKeyBindingsFromFile(File f) {
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
		
		return new BindingReader().generate(head);
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
	
	public void loadFullGame(File f) {
		
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
		
		List<GameMap> maps = new ArrayList<GameMap>();
		File[] list = new File("./src/resources/maps/").listFiles();
		for(File file : list) {
			if(file.isFile()) {
				maps.add(createMap(file));
			}
		}
		
		Element head = doc.getDocumentElement();
		
		NodeList nodes = head.getElementsByTagName("map");
		
		for(int i = 0; i < nodes.getLength(); i++)
		{
			Element e = (Element) nodes.item(i);
			
			int mapid = Integer.parseInt(e.getAttribute("id"));
			
			for(MapObject o : new EntityFactory().generate(e)) {
				Entity n = (Entity) o;
				
				addEntity(maps, mapid, n, n.getLocation());
			}
			
			for(MapObject o : new InteractiveFactory().generate(e)) {
				Item n = (Item) o;
				
				addItem(maps, mapid, n, n.getLocation());
			}
			
			for(MapObject o : new TakeableFactory().generate(e)) {
				Item n = (Item) o;
				
				addItem(maps, mapid, n, n.getLocation());
			}
			
			for(MapObject o : new OneShotFactory().generate(e)) {
				Item n = (Item) o;
				
				addItem(maps, mapid, n, n.getLocation());
			}
			
			for(MapObject o : new ObstacleFactory().generate(e)) {
				Item n = (Item) o;
				
				addItem(maps, mapid, n, n.getLocation());
			}
			
			for(MapObject o : new TrapFactory().generate(e)) {
				Trap n = (Trap) o;
				
				addTrap(maps, mapid, n, n.getLocation());
			}
			
			for(MapObject o : new AreaEffectFactory().generate(e)) {
				AreaEffect n = (AreaEffect) o;
				
				addAreaEffect(maps, mapid, n, n.getLocation());
			}
		}
			
		for(GameMap m : maps) {
			ActiveMapManager.getInstance().addMap(m);
		}
	}
	
	private void addTrap(List<GameMap> maps, int mapid, Trap t, CoordinatePair location) {
		for(GameMap m : maps) {
			if(m.getID() == mapid) {
				m.addTrap(t, location);
			}
		}
	}
	
	private void addAreaEffect(List<GameMap> maps, int mapid, AreaEffect ae, CoordinatePair location) {
		for(GameMap m : maps) {
			if(m.getID() == mapid) {
				m.addAreaEffect(ae, location);
				
				if(ae.getName().equals("teleport")) {
					((TeleportAreaEffect) ae).setMap(m);
				}
			}
		}
	}
	
	private void addEntity(List<GameMap> maps, int mapid, Entity e, CoordinatePair location) {
		for( GameMap m : maps) {
			if(m.getID() == mapid) {
				m.addEntity(e, location);
				return;
			}
		}
	}
	
	private void addItem(List<GameMap> maps, int mapid, Item i, CoordinatePair location) {
		for( GameMap m : maps) {
			if(m.getID() == mapid) {
				m.addItem(i, location);
				return;
			}
		}
	}
	
	private GameMap createMap(File f) {
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
		
		Element maphead = (Element) head.getElementsByTagName("layer").item(0);
		
		GameMap m = new GameMap(new TileFactory().generate(maphead));
		m.setID(Integer.parseInt(maphead.getAttribute("id")));
		
		return m;
	}
}
