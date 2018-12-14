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
    private adventurer p1;
    private Life       life;
    private Score      score;
    private MenuButton button;
    private GameOver   gameOver;
    private FinalScore finalScore;
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
        // Set the initial world state to MENU
        prepareMenu();  
    }
    
    private void prepareMenu(){
        // Clear the world
        removeObjects(getObjects(Actor.class));
                
        // Create the menu
        button = new MenuButton();        
        addObject(new MenuBackground(),getWidth()/2,getHeight()/2);
        addObject(button,getWidth()-60,getHeight()-60);
        
        // Set the game state
        state = GameState.MENU;
    }
    private void preparePlaying(){
        // Clear the world
        removeObjects(getObjects(Actor.class));
        
        Metronome metronome = new Metronome();
        addObject(metronome,getWidth()/3,4*getHeight()/7);
        Dagger dagger = new Dagger(getWidth());
        addObject(dagger,getWidth(),4*getHeight()/7);
        p1 = new adventurer("right","up","left", 6);
        addObject(p1,getWidth()/6,getHeight()/2);
        score= new Score();
        life = new Life();
        addObject(score,getWidth()/2,15);
        
        
        // Set the game state
        state = GameState.PLAYING;
        Greenfoot.start();
        setTempo(123);
        backgroundMusic = new GreenfootSound("18 - Knight to C-Sharp (Deep Blues).mp3");
        backgroundMusic.setVolume(30);
        backgroundMusic.play();
    }
    private void prepareGameOver(){
        // Clear the world
        removeObjects(getObjects(Actor.class)); 
      
        // Add gameover screen
        gameOver = new GameOver();
        addObject(gameOver,getWidth()/2,getHeight()/2);
        
        // Add the final score
        int fscore = score.getScore();
        finalScore = new FinalScore(fscore);
        addObject(finalScore,getWidth()-75,60);
                                
        // Set the game state
        state = GameState.GAMEOVER;           
    }
    public void setTempo(int tempo){
        this.tempo = tempo;
    }
    public void resetMetronome(){
        this.frame = 1;
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
    public void playerHit()
    {
        score.addScore(5);
    }
    
    public void playerGotHit()
    {
        life.takeLife();
        
        if (life.isDead())
        {
            prepareGameOver();
        }
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
        if(Greenfoot.mouseClicked(button))
        {
            preparePlaying();
        }
    }
    
    public void actPlaying()
    {
        createTempoUnit(tempo, Greenfoot.getRandomNumber(3));
        
        if(Greenfoot.isKeyDown("escape")){
            Greenfoot.stop();
            backgroundMusic.stop();
        }
        
        if(isActionTime()){
            
        }
      
        frame++;
    }
    public void createTempoUnit(int tempo, int classe){
        if (tempoUnitsCount == 0)
        {   
            TempoUnit unit;
            if (classe == 0){
                unit = new Arrow(this.getWidth());
                unit.setImage("arrow.png");
            }
            else{
                unit = new Dagger(this.getWidth());
                unit.setImage("dagger.png");
            }
            
            
            this.addObject(unit,this.getWidth(),4*getHeight()/7);

                     
        }
        
        // Synchronizes spawn time off TempoUnits with tempo (60frames x 60sec/bpm)
        if (tempoUnitsCount++ == 3600/tempo)
        {
            tempoUnitsCount=0   ;
        }   
    }
    
    public void actGameOver()
    {
        if(Greenfoot.mouseClicked(gameOver) ||
           Greenfoot.mouseClicked(finalScore))
        {
            prepareMenu();
        }
    }
    public boolean checkOffBeatInput()
    {
       if(Greenfoot.isKeyDown("left") || Greenfoot.isKeyDown("right") || Greenfoot.isKeyDown("up") ){
           //TODO: file is playing multiple times when a offbeat input happens

               Greenfoot.playSound("sfx_missedbeat_01.mp3");
               

           return true;
       }
       return false;
    }
}

