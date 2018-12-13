import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Life here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Life extends Actor
{
    private int life = 3;
    
    public Life()
    {               
       updateImage();
    }  
    
    public void takeLife()
    {
        life--;
        updateImage();
    }    
    
    public void updateImage()
    {
        GreenfootImage image = new GreenfootImage("Lifes: "+life,          
                                   30,
                                   new Color(0,0,0,200),
                                   new Color(255,255,255,0)); 
                                   
        setImage(image);  
    }
    
    public boolean isDead()
    {
        return life==0;
    }
}
