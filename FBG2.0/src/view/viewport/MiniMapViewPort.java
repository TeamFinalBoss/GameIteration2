package view.viewport;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import model.director.GameDirector;
import model.gameObject.entity.Avatar;
import model.gameObject.entity.Entity;
import model.gameObject.projectile.Projectile;
import model.map.tile.Tile;

/**
 *
 * @author ChrisMoscoso
 */
public class MiniMapViewPort implements ViewPort, Observer {

    private int width, height;
    private final int tileWidth = 64, tileHeight = 64;
    private int widthInTiles = 0, heightInTiles = 0;
    private Tile[][] tiles;
    
    private double scale = 0.08;

    private ArrayList<Entity> entityList;
    private ArrayList<Projectile> projectileList;

    @Override
    public void draw(Graphics g) {
        width = (int) (widthInTiles * tileWidth * scale);//(GameDirector.getSize().width * 0.2);
        height = (int) (heightInTiles * tileHeight * scale);//(GameDirector.getSize().height * 0.2);
        
        int startX =  GameDirector.getSize().width - width;
        int startY =  0;
        
        for(int i = 0; i < widthInTiles; i++){
            for(int j = 0; j < heightInTiles; j++){
                
                for(Entity e : entityList){
                    if(e.getLocation().equals(new Point(i,j))){
                        if(e.equals(Avatar.getAvatar())){
                            g.setColor(Color.red);
                        }else{
                            g.setColor(Color.blue);
                        }
                        
                        g.drawRect( startX + (int) (i * tileWidth * scale), startY + (int) (j * tileHeight *scale), (int) (tileWidth * scale), (int) (tileHeight * scale) );
                    }
                }
                if(Avatar.getAvatar().getLocation().equals(new Point(i,j))){
                    
                    g.setColor(Color.red);
                    g.drawRect( startX + (int) (i * tileWidth * scale), startY + (int) (j * tileHeight *scale), (int) (tileWidth * scale), (int) (tileHeight * scale) );
                }
            }
        }
        
        
        
        g.setColor(Color.blue);
        g.drawRect(startX, startY, width, height);
        
        /*
        for(int i = 0; i < widthInTiles; i++){
            for(int j = 0; j < heightInTiles; j++){
                if(Avatar.getAvatar().getLocation().equals(new Point(i,j))){
                    g.setColor(Color.red);
                }else{
                    g.setColor(Color.white);
                }
                g.drawRect((int) (i * tileWidth * .2), (int) (j * tileHeight * .2), width, height);
            }
        }*/
        
    }

    @Override
    public void update(Observable o, Object arg) {
        Object[] mapObjects = (Object[]) arg;
        tiles = (Tile[][]) mapObjects[0];
        widthInTiles = tiles.length;
        heightInTiles = tiles[0].length;

        ArrayList<Entity> e = (ArrayList<Entity>) mapObjects[1];
        entityList = e;

        ArrayList<Projectile> p = (ArrayList<Projectile>) mapObjects[2];
        projectileList = p;
    }

}
