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
    private int frame = 0;
    private long lastTouchingFrame = 0;
    private int actionFrameWindow = 15;
    private int missinputFrame = 0;
    // Controlls the beat
    private int tempoUnitsCount = 0;
    private GreenfootSound backgroundMusic;


    // Controlling actors
    private Adventurer p1;
    private Life       life;
    private Score      score;
    private MenuButton button;
    private GameOver   gameOver;
    private Completed   completed;
    private FinalScore finalScore;
    // Game state
    private enum GameState{MENU,PLAYING,GAMEOVER, COMPLETED};
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
        // background
        PlayingBackground background = new PlayingBackground();
        GreenfootImage bgImage = new GreenfootImage("menu_background.png");
        bgImage.scale(bgImage.getWidth()+100, bgImage.getHeight()+100 );
        background.setImage(bgImage);
        addObject(background,1*getWidth()/8,getHeight()/2);
        MenuAdventurer adventurer = new MenuAdventurer();
        GreenfootImage adventurerImage = new GreenfootImage("Adventurer-die-04.png");
        adventurerImage.scale(adventurerImage.getWidth()+30, adventurerImage.getHeight()+30 );
        adventurer.setImage(adventurerImage);
        addObject(adventurer,2*getWidth()/8+10,getHeight()/2-20);
        // Create the menu button
        button = new MenuButton();
        addObject(button,getWidth()-60,getHeight()-60);



        // Set the game state
        state = GameState.MENU;
    }
    private void preparePlaying(){
        // Clear the world
        removeObjects(getObjects(Actor.class));

        // background
        PlayingBackground background = new PlayingBackground();
        GreenfootImage bgImage = new GreenfootImage("playing_background.png");
        bgImage.scale(bgImage.getWidth() + 800, bgImage.getHeight() +800);
        background.setImage(bgImage);
        addObject(background,3*getWidth()/4,2*getHeight()/3);

        Metronome metronome = new Metronome();
        addObject(metronome,getWidth()/3,4*getHeight()/7);
        p1 = new Adventurer("right","up","left", 4);
        addObject(p1,getWidth()/6,getHeight()/2);
        score= new Score();
        life = new Life();
        addObject(score,getWidth()/2,15);
        addObject(life,getWidth()/10,15);


        // Set the game state
        state = GameState.PLAYING;
        Greenfoot.start();
        setTempo(100);
        //backgroundMusic = new GreenfootSound("18 - Knight to C-Sharp (Deep Blues).mp3");
        backgroundMusic = new GreenfootSound("01 - Tombtorial (Tutorial).mp3");
        backgroundMusic.setVolume(30);
        backgroundMusic.play();
    }
    private void prepareGameOver(){
        // Clear the world
        removeObjects(getObjects(Actor.class));

        //Stop music
        backgroundMusic.stop();

        // Add gameover screen
        gameOver = new GameOver();
        addObject(gameOver,getWidth()/2,getHeight()/2);

        // Add the final score
        int fscore = score.getScore();
        finalScore = new FinalScore(fscore);
        addObject(finalScore,getWidth()/2,getHeight()/2);

        // Set the game state
        state = GameState.GAMEOVER;
    }
    private void prepareCompleted(){
        // Clear the world
        removeObjects(getObjects(Actor.class));

        //Stop music
        backgroundMusic.stop();

        // Add gameover screen
        completed = new Completed();
        addObject(completed,getWidth()/2,getHeight()/2);

        // Add the final score
        int fscore = score.getScore();
        finalScore = new FinalScore(fscore);
        addObject(finalScore,getWidth()/2,getHeight()/2);

        // Set the game state
        state = GameState.GAMEOVER;
    }
    public void setTempo(int tempo){
        this.tempo = tempo;
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
                frame++;
                return true;
            }
        //Right now there is a 15 frame window for the input be considered on beat.
        //4 frames of touching tempo units + 11 frames form actionFrameWindow
        }else if(frame + 10 > actionFrameWindow ){
            frame = 0;
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
            case COMPLETED:
            {
                actCompleted();
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

        if(!backgroundMusic.isPlaying()){
            prepareCompleted();
        }

    }
    public void createTempoUnit(int tempo, int classe){
        if (tempoUnitsCount == 0)
        {
            TempoUnit unit;
            if (classe == 0){
                unit = new Arrow(this.getWidth());
                unit.setImage("arrow.png");
            }
            else if(classe == 1){
                unit = new Dagger(this.getWidth());
                unit.setImage("dagger.png");
                GreenfootImage dagger = new GreenfootImage("dagger.png");
                dagger.scale(dagger.getWidth() + 20, dagger.getHeight() +20);
                unit.setImage(dagger);
            }else{
                unit = new Handaxe(this.getWidth());
                GreenfootImage axe = new GreenfootImage("axe.png");
                axe.scale(axe.getWidth() - 100, axe.getHeight() - 100);
                unit.setImage(axe);
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
    public void actCompleted()
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

