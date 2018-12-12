import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{
    private int tempo; //in bpm
    private long frame = 1;// 1/60 second
    private long lastTouchingFrame = 0;
    private int actionFrameWindow = 11;
    private int missinputFrame = 0;
    // Controlls the beat
    private int tempoUnitsCount = 0;
    private GreenfootSound backgroundMusic;
    
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
    public void setTempo(int tempo){
        this.tempo = tempo;
    }
    public void resetMetronome(){
        this.frame = 1;
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
        Greenfoot.start();
        backgroundMusic = new GreenfootSound("18 - Knight to C-Sharp (Deep Blues).mp3");
        backgroundMusic.setVolume(30);
        backgroundMusic.play();
    }
    // ========================================================================== //
    // Game events                                                                //
    // These methods are invoked to proccess main game events.                    //
    // ========================================================================== //   
    //Dictates when a input is gonna be considered on or off beat
    public boolean isActionTime()
    {   
        Metronome metronome = this.getObjects(Metronome.class).get(0);
        TempoUnit unit = metronome.getNearTempoUnit();
        if(unit != null){
            if(unit.isActionTime()){
            lastTouchingFrame = frame;    
            return true;
            }
        //Right now there is a 15 frame window for the input be considered on beat.
        //4 frames of touching tempo units + 11 frames form actionFrameWindow
        }else if(frame - lastTouchingFrame <= actionFrameWindow){
            return true;
        }
        
        return false;
    }
    public void createTempoUnit(int tempo){
        if (tempoUnitsCount == 0)
        {   
            TempoUnit rightUnit = new RightTempoUnit(this.getWidth());
            TempoUnit leftUnit = new LeftTempoUnit(0);
            int y = 360;
            this.addObject(rightUnit,this.getWidth(),y);
            this.addObject(leftUnit,0,y);
                     
        }
        
        // Synchronizes spawn time off TempoUnits with tempo (60frames x 60sec/bpm)
        if (tempoUnitsCount++ == 3600/tempo)
        {
            tempoUnitsCount=0   ;
        }   
    }
    public void playerHit()
    {
        
    }
    
    public boolean checkOffBeatInput()
    {
       if(Greenfoot.isKeyDown("left") || Greenfoot.isKeyDown("right")){
           //TODO: file is playing multiple times when a offbeat input happens

               Greenfoot.playSound("sfx_missedbeat_01.mp3");
               

           return true;
       }
       return false;
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
        createTempoUnit(123);
        if(Greenfoot.isKeyDown("escape")){
            Greenfoot.stop();
            backgroundMusic.stop();
        }
        frame++;
    }
        
    public void actGameOver()
    {
        if(true)
        {
            
        }
    }
}

