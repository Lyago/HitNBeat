import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class FinalScore here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FinalScore extends Actor
{
    private static int maxScore;
    
    public FinalScore(int finalScore)
    {
        GreenfootImage image = new GreenfootImage("Total hits: "+String.valueOf(finalScore)+"/"+ maxScore,          
                                                  50,
                                                  new Color(0,0,0,200),
                                                  new Color(255,255,255,0));                                          
                                                  
        setImage(image);  
    }
    public void addMaxScore(int newPoints)
    {
        maxScore += newPoints;
    }
}
