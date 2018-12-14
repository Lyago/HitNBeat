import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Text here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Text extends Actor
{
    public Text(String text){
        GreenfootImage image = new GreenfootImage(text,          
                                    30,
                                   new Color(0,0,0,200),
                                   new Color(255,255,255,0)); 
                                            
        image.scale(image.getWidth()-15, image.getHeight()-15);
        setImage(image); 
    }
}
