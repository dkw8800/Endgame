package sample;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.util.ArrayList;

public class Main extends Application {
    //todolist:
    //fix displayed card orientation, changing images
    //add the rest of the images for the cards
    //figure out adding up movement of poacher and movement of player
    //figure out showing movement of poacher and of player
    //change background to be one board, not repeating when expanded

    Scene openingpage, intropage, mainpage;
    int playerposition = 0;
    int poacherposition = 3;
    int pxpoacher = 300;
    int pxplayer = 100;
    int pypoacher = 200;
    int pyplayer = 200;
    int turncount;

    Button player = new Button("");
    Button poacher = new Button("");

    int endboard = 34;
    boolean playerwins = false;
    public static ArrayList<Cards> carddecks = new ArrayList<Cards>();
    Cards currentcard;
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        setupscenes(primaryStage);

        primaryStage.setTitle("Hello World");
        primaryStage.setScene(openingpage);
        primaryStage.show();
    }

    public void setupscenes(Stage primaryStage)
    {
        Pane titlepage = new Pane();
        Button startgame = new Button("Start");
        Label title = new Label("Endgame");
        startgame.setOnAction(event -> primaryStage.setScene(intropage));
        //intropage should be here
        titlepage.getChildren().addAll(title, startgame);
        openingpage = new Scene(titlepage, 800, 700);
        titlepage.setId("titlepg");
        titlepage.getStylesheets().add("/sample/endgame.css");
        startgame.relocate(300,300);
        startgame.setId("starter");


        Pane intro = new Pane();
        Button begin = new Button("Begin Game");
        Label pagetitle = new Label("Instructions \n Welcome to Endangered Game: The End Game! Help support the cause so endangered animals may have another chance at flourishing. \n To play this game, click the card icon to get a randomly generated card. This will help you move. On the game board, you will see another game piece. That is the poacher piece. \n You must pass the poacher AND make it around the board to be able to win. If you fail to pass the poacher, you must keep going around the board until you do. Enjoy our game!");
        begin.setOnAction(event -> primaryStage.setScene(mainpage));
        intro.getChildren().addAll(pagetitle, begin);
        intropage = new Scene(intro, 800, 700);
        intro.setId("intropage");
        intro.getStylesheets().add("/sample/endgame.css");
        begin.relocate(300,300);
        begin.setId("beginner");


        Button carddisplayed = new Button("");
        Label textfield = new Label("The card events will be shown here. Click the lower deck button to pull out a card!");

        player.relocate(40,540);
        player.setId("player");
        poacher.relocate(225,540);
        poacher.setId("poacher");
        textfield.relocate(120,375);
        textfield.setId("textfield");
        carddisplayed.relocate(425,250);
        carddisplayed.setId("carddisplayed");

        Pane board = new Pane();
        Button deckofcards = new Button("");
        deckofcards.setId("carddeckbutton");
        deckofcards.setOnAction(event -> dealcard(carddisplayed,textfield));
        deckofcards.relocate(425,375);
        mainpage = new Scene(board, 800, 700);
        board.setId("mainpage");
        board.getStylesheets().add("/sample/endgame.css");

        board.getChildren().addAll(deckofcards, player, poacher, carddisplayed, textfield);
        textfield.setWrapText(true);
        textfield.setMaxWidth(250.0);
        setupcards();
    }


    public static void setupcards() {
        String animal[] = {"Elephant","Tiger","Panda","Rhino","Green Turtle","Gorilla","Monkey","Baby Jaguar","GoFundMe","Lumberjack","Plastic"};
        String animalpic[] = {"elephant.jpg","tiger.jpg","panda.jpg","rhino.jpg","greenturtle.jpg","gorilla.jpg","monkey.jpg","babyjaguar.jpg","gofundme.jpg","lumberjack.jpg","plastic.jpg"};
        String info[] = {"You come across an injured elephant with a thorn stuck in its foot. You help pull it out and advance 4 spaces.",
                "You make eye contact with a tiger who looks like he hasn’t eaten in  a while. The tiger chases you and forces you up a tree. Lose a turn",
                "The pandas are hungry. You try to feed them bamboo and it becomes happy. Move ahead 2 spaces.",
                "You notice a group of intersecting tracks. The tracks lead you to a herd of rhinos. They seem to be missing their horns. You decide to bring them back to the animal sanctuary for relief. Move 6 spaces but lose your next turn.",
                "You failed to notice where you were going and stepped on some Green Turtle eggs. Move 1 space and lose a turn.",
                "You notice a gorilla watch you as you eat a banana. You offer him the rest to avoid his death stare and slowly back away. Move forward two spaces.",
                "You see a group of cool monkeys. You give them a bunch of bananas and they show you towards a shortcut. Move forward 5 spaces.",
                "You find an baby jaguar on one of your expeditions. After confirming that it is indeed abandoned, you bring it back to your research team to assist its survival. Move forward 3 spaces.",
                "Your go-fund-me page for endangered animals has reached its goal and raised $50,000. Move forward 5 spaces.",
                "A group of lumberjacks are headed towards a rainforest. You attempt to persuade them to move elsewhere. Move forward 6 spaces but lose a turn.",
                "You notice pieces of plastic washed up on the shore. You spot a turtle eating a soda can ring around its neck. You remove the ring and send him on its way. Move forward 3 spaces."};
        int moves[] = {4,0,2,60,10,2,5,3,5,60,3};

        for(int i = 0; i < animal.length;i++)
        {
            Cards c = new Cards(animal[i],animalpic[i],info[i],moves[i]);
            carddecks.add(c);
        }


        //creating the cards deck
    }

    public void poachermove()
    {
        int poachermovement = (int)(Math.random()*4+1);
        poacherposition += poachermovement;
        updateposition(player,pxpoacher,pypoacher,poachermovement);
    }

    public void playermove()
    {
        int playermovement = currentcard.retmoves();
        playermovement += playermovement;
        updateposition(player,pxplayer,pyplayer,playermovement);
    }

    public void updateposition(Button piece, int xval, int yval, int moves)
    {
        for(int i = 0; i < moves; i ++)
        {
            if(xval == 665 && yval != 800)
                piece.relocate(xval,yval+62.5);
            else if(xval == 665 && yval == 800)
                piece.relocate(xval - 62.5,yval);
            else if(xval == 40 && yval == 800)
                piece.relocate(xval,yval-62.5);
            else if(yval == 40 && xval == 540)
                checkwin();
            else
                piece.relocate(xval + 62.5,yval);
        }
        //double[] xspots = {40,102.5,165,227.5,290,352.5,415,477.5,540};
        //double[] yspots = {540,477.5,415,352.5,290,227.5,165,102.5,40};

        //for(int i = 0; i < moves; i++)
        //{
        //    piece.relocate(xspots[i])
        //}
    }

    public void dealcard(Button carddisplayed, Label textfield)
    {
        int dealtcard = (int)(Math.random()*10+1);
        this.currentcard = carddecks.get(dealtcard);
        carddisplayed.setStyle("-fx-background-image: url('/sample/EndgamePictures/" + currentcard.retanimalpic() + "')");
        textfield.setText(currentcard.retanimal() + "\n" + currentcard.retinfo());
        playermove();
        turncount++;
        poachermove();
    }

    public boolean checkwin()
    {
        if(playerposition == endboard && playerposition > poacherposition)
        {
            playerwins = true;
        }
        else
        {
            playerwins = false;
        }
        return playerwins;
    }

}
