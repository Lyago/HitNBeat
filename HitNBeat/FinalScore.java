import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class FinalScore here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FinalScore extends Actor
{
    public FinalScore(int finalScore)
    {
        GreenfootImage image = new GreenfootImage(String.valueOf(finalScore),          
                                                  50,
                                                  new Color(100,100,100,200),
                                                  new Color(255,255,255,0));                                          
                                                  
        setImage(image);  
    }
}
