import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player extends Actor
{
    /**
     * Act - do whatever the Player wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        MyWorld world = (MyWorld)this.getWorld();
        
        if (world.isActionTime()){
            if (Greenfoot.isKeyDown("left")){
                setRotation (getRotation() - 30);
            }    
            if (Greenfoot.isKeyDown("right")){
                setRotation (getRotation() + 30);
            }       
        }else{
           
        }
        
      
    }    
}
