import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player extends Actor
{
    private enum PlayerState{PLAYING,PENALIZED};
    private PlayerState state = PlayerState.PLAYING;
    private int penaltyFrames = 30;
    
    public void resetPenaltyFrames(){
        this.penaltyFrames = 30;
    }
    /**
     * Act - do whatever the Player wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        switch(state)
        { 
            case PLAYING:
            {                  
                actPlaying();   
                break;
            }            
            case PENALIZED:
            {
                actPenalized();
                break;
            }
        }
    }
    public void actPlaying(){
        MyWorld world = (MyWorld)this.getWorld();
        
        if (world.isActionTime() ){
            if (Greenfoot.isKeyDown("left")){
                setRotation (getRotation() - 30);
                
            }    
            if (Greenfoot.isKeyDown("right")){
                setRotation (getRotation() + 30);
            }       
        }else{ 
           if(world.checkOffBeatInput()){
               this.state = PlayerState.PENALIZED;
           }
        }
        
      
    }
    public void actPenalized(){
        // 15 frames penalty for offbeat input
        this.penaltyFrames--;
        if(penaltyFrames == 0){
            this.state = PlayerState.PLAYING;
            this.resetPenaltyFrames();
        }
    }
}
