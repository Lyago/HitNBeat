import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{
   
    
    
        
    // Controlling actors
    
    // Game state
    private enum GameState{MENU,PLAYING,GAMEOVER};    
    private GameState state;
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1);
        Greenfoot.setSpeed(50);
        prepare();
    }
    
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        Metronome metronome = new Metronome();
        addObject(metronome,this.getWidth()/2,360);
        TempoUnit tempoUnit = new RightTempoUnit(this.getWidth());
        addObject(tempoUnit,this.getWidth(),360);
        TempoUnit tempoUnit2 = new LeftTempoUnit(0);
        addObject(tempoUnit2,0,360);
        Player player = new Player();
        addObject(player,this.getWidth()/3,this.getHeight()/2);
        Player player2 = new Player();
        addObject(player2,2*this.getWidth()/3,this.getHeight()/2);
        
        // Set the game state
        state = GameState.PLAYING;
    }
    // ========================================================================== //
    // Game events                                                                //
    // These methods are invoked to proccess main game events.                    //
    // ========================================================================== //   
    public boolean isActionTime()
    {
        Metronome metronome = getObjects(Metronome.class).get(0);
        if(metronome.isActionTime()){
            return true;
        }
        return false;
    }
    public void playerHit()
    {
        
    }
    
    public void playerOffBeatInput()
    {
       
    }
    public void act()
    {
        switch(state)
        {
            case MENU:
            {
                actMenu();
                break;
            }            
            case PLAYING:
            {                  
                actPlaying();   
                break;
            }            
            case GAMEOVER:
            {
                actGameOver();
                break;
            }
        }
    }
    public void actMenu()
    {
        if(true)
        {
            
        }
    }
    
    public void actPlaying()
    {
        Metronome metronome = (Metronome)this.getObjects(Metronome.class).get(0);
        metronome.startMetronome(123);
    }
    
    public void actGameOver()
    {
        if(true)
        {
            
        }
    }
}

