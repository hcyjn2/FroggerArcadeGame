package p4_group_8_repo;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import p4_group_8_repo.ui_components.BackToMenuButton;
import p4_group_8_repo.ui_components.Button;
import p4_group_8_repo.ui_components.MusicButton;

public class Main extends Application {
	//initialize variables
	AnimationTimer timer;
	MyStage backgroundStage;
	Frogger frogger;
	private int currentLevel = 1;
	private int score = 0;
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		//game window is fixed so the aspect ratio / game asset will not look weird
		primaryStage.setResizable(false);
		backgroundStage = new MyStage();
		frogger = new Frogger("file:src/main/resources/froggerUp.png",score);

		Scene scene  = new Scene(backgroundStage,566,800);
		BackgroundImage gameMenuImage = new BackgroundImage("file:src/main/resources/GameMenu1.png");
		BackgroundImage aboutScreenImage = new BackgroundImage("file:src/main/resources/AboutScreen.png");
		Button startButton = new Button("file:src/main/resources/start.png", -28, 310);
		Button aboutButton = new Button("file:src/main/resources/about.png", -28, 430);
		Button exitButton = new Button("file:src/main/resources/exit.png", -28, 530);
		Button backButton = new Button("file:src/main/resources/back.png", -28, 530);

		backgroundStage.generateLevel(currentLevel);

		backgroundStage.add(frogger);
		backgroundStage.add(new Digit(0, 30, 532, 33));

		backgroundStage.add(gameMenuImage);
		backgroundStage.add(startButton);
		backgroundStage.add(aboutButton);
		backgroundStage.add(exitButton);

		primaryStage.setScene(scene);
		primaryStage.show();

		startButton.setOnMouseClicked(event -> {
			backgroundStage.remove(gameMenuImage);
			backgroundStage.remove(startButton);
			backgroundStage.remove(aboutButton);
			backgroundStage.remove(exitButton);
			//start asset timer
			backgroundStage.start();
			//score and music start
			start();
		});

		aboutButton.setOnMouseClicked(event -> {
			backgroundStage.remove(gameMenuImage);
			backgroundStage.remove(startButton);
			backgroundStage.remove(aboutButton);
			backgroundStage.remove(exitButton);
			backgroundStage.add(aboutScreenImage);
			backgroundStage.add(backButton);

		});

		backButton.setOnMouseClicked(event -> {
			backgroundStage.remove(aboutScreenImage);
			backgroundStage.remove(backButton);
			backgroundStage.add(gameMenuImage);
			backgroundStage.add(startButton);
			backgroundStage.add(aboutButton);
			backgroundStage.add(exitButton);
		});

		exitButton.setOnMouseClicked(event -> System.exit(0));

	}

	public void createTimer(){
		timer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				if(frogger.getStop()){
					currentLevel++;
					backgroundStage.generateLevel(currentLevel);
					frogger = new Frogger("file:src/main/resources/froggerUp.png", score);
					backgroundStage.add(frogger);
				}

				if (frogger.changeScore()){
					setNumber(frogger.getPoints());
					score = frogger.getPoints();
				}

				if (currentLevel == 10) {
					System.out.print("STOP:");
					backgroundStage.stopMusic();
					stop();
					backgroundStage.stop();
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setTitle("You Have Won The Game!");
					alert.setHeaderText("Your High Score: " + frogger.getPoints() + "!");
					alert.setContentText("Highest Possible Score: 800");
					alert.show();
				}
			}
		};
	}

	public void start() {
		backgroundStage.playMusic();
		createTimer();
		timer.start();
	}

	public void setNumber(int n) {
		int shift = 0;
		while (n > 0) {
			int d = n / 10;
			int k = n - d * 10;
			n = d;
			backgroundStage.add(new Digit(k, 30, 532 - shift, 33));
			shift+=27;
		}
	}

    public void stop() {
        timer.stop();
    }



}


//if (frogger.getStop()) {
//		System.out.print("STOP:");
//		backgroundStage.stopMusic();
//		stop();
//		backgroundStage.stop();
//		Alert alert = new Alert(Alert.AlertType.INFORMATION);
//		alert.setTitle("You Have Won The Game!");
//		alert.setHeaderText("Your High Score: " + frogger.getPoints() + "!");
//		alert.setContentText("Highest Possible Score: 800");
//		alert.show();
//		}



//		backgroundStage.add(new Log("file:src/main/resources/log3.png", 150, 0, 166, 0.75));
//				backgroundStage.add(new Log("file:src/main/resources/log3.png", 150, 220, 166, 0.75));
//				backgroundStage.add(new Log("file:src/main/resources/log3.png", 150, 440, 166, 0.75));
//				//background.add(new Log("file:src/main/resources/log3.png", 150, 0, 166, 0.75));
//				backgroundStage.add(new Log("file:src/main/resources/logs.png", 300, 0, 276, -2));
//				backgroundStage.add(new Log("file:src/main/resources/logs.png", 300, 400, 276, -2));
//				//background.add(new Log("file:src/main/resources/logs.png", 300, 800, 276, -2));
//				backgroundStage.add(new Log("file:src/main/resources/log3.png", 150, 50, 329, 0.75));
//				backgroundStage.add(new Log("file:src/main/resources/log3.png", 150, 270, 329, 0.75));
//				backgroundStage.add(new Log("file:src/main/resources/log3.png", 150, 490, 329, 0.75));
//				//background.add(new Log("file:src/main/resources/log3.png", 150, 570, 329, 0.75));
//
//				backgroundStage.add(new Turtle(500, 376, -1, 130, 130));
//				backgroundStage.add(new Turtle(300, 376, -1, 130, 130));
//				backgroundStage.add(new WetTurtle(700, 376, -1, 130, 130));
//				backgroundStage.add(new WetTurtle(600, 217, -1, 130, 130));
//				backgroundStage.add(new WetTurtle(400, 217, -1, 130, 130));
//				backgroundStage.add(new WetTurtle(200, 217, -1, 130, 130));
//				//background.add(new Log("file:src/main/resources/log2.png", 200, 100, 1));
//				//background.add(new Log("file:src/main/resources/log2.png", 0, 100, 1));
//				//background.add(new Log("file:src/main/resources/log2.png", 100, 120, -1));
//				//background.add(new Log("file:src/main/resources/log2.png", 200, 120, -1));
//				//background.add(new Log("file:src/main/resources/log2.png", 100, 140, 1));
//				//background.add(new Log("file:src/main/resources/log2.png", 200, 140, 1));
//				//background.add(new Log("file:src/main/resources/log2.png", 100, 160, -1));
//				//background.add(new Log("file:src/main/resources/log2.png", 300, 160, -1));
//				//background.add(new Log("file:src/main/resources/log2.png", 100, 180, 1));
//				//background.add(new Log("file:src/main/resources/log2.png", 200, 180, 1));
//				//background.add(new Log("file:src/main/resources/main/resources/log2.png", 100, 200, -1));
//				//background.add(new Log("file:src/main/resources/log2.png", 200, 200, -1));
//				//background.add(new Log("file:src/main/resources/log2.png", 100, 220, 1));
//				//background.add(new Log("file:src/main/resources/log2.png", 200, 220, 1));
//				//background.add(new Log("file:src/main/resources/log2.png", 400, 220, 1));
//				//End end2 = new End();
//				//End end3 = new End();
//				//End end4 = new End();
//				//End end5 = new End();
//				backgroundStage.add(new End(10,96));
//				backgroundStage.add(new End(130,96));
//				backgroundStage.add(new End(130 + 130-10,96));
//				backgroundStage.add(new End(130 + 130-10+130-10+1,96));
//				backgroundStage.add(new End(130 + 130-10+130-10+130-10+3,96));
//				frogger = new Frogger("file:src/main/resources/froggerUp.png");
//				backgroundStage.add(frogger);
//				backgroundStage.add(new Obstacle("file:src/main/resources/truck1"+"Right.png", 0, 649, 1, 120, 120));
//				backgroundStage.add(new Obstacle("file:src/main/resources/truck1"+"Right.png", 300, 649, 1, 120, 120));
//				backgroundStage.add(new Obstacle("file:src/main/resources/truck1"+"Right.png", 600, 649, 1, 120, 120));
//				//background.add(new Obstacle("file:src/main/resources/truck1"+"Right.png", 720, 649, 1, 120, 120));
//				backgroundStage.add(new Obstacle("file:src/main/resources/car1Left.png", 100, 597, -1, 50, 50));
//				backgroundStage.add(new Obstacle("file:src/main/resources/car1Left.png", 250, 597, -1, 50, 50));
//				backgroundStage.add(new Obstacle("file:src/main/resources/car1Left.png", 400, 597, -1, 50, 50));
//				backgroundStage.add(new Obstacle("file:src/main/resources/car1Left.png", 550, 597, -1, 50, 50));
//				backgroundStage.add(new Obstacle("file:src/main/resources/truck2Right.png", 0, 540, 1, 200, 200));
//				backgroundStage.add(new Obstacle("file:src/main/resources/truck2Right.png", 500, 540, 1, 200, 200));
//				backgroundStage.add(new Obstacle("file:src/main/resources/car1Left.png", 500, 490, -5, 50, 50));
//				backgroundStage.add(new Digit(0, 30, 532, 33));
////background.add(obstacle);
////background.add(obstacle1);
////background.add(obstacle2);