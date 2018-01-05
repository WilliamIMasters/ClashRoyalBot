import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.beans.EventHandler;

public class Main {

    public static void main (String[] args) {
        launchFX();
    }

    public static void launchFX() {
        new JFXPanel();
        Platform.runLater(() -> initialiseGUI());
    }

    public static void initialiseGUI(){
        Robot roboboi = null;
        try {
            roboboi = new Robot();
        } catch(AWTException e) {
            System.out.println("Creating robot error: " + e);
        }
        RobotO robot = new RobotO(roboboi);

        Stage stage = new Stage();
        stage.setTitle("Clash royal bot");
        stage.setHeight(300);
        stage.setWidth(600);
        stage.setResizable(false);
        stage.setOnCloseRequest((WindowEvent we) -> terminate());

        Pane rootPane = new Pane();
        Scene scene = new Scene(rootPane);
        scene.setOnKeyPressed((event) -> {
            switch((event.getCode())) {
                case I: RobotO.getInfo();
            }
        });
        //scene.getStylesheets().add("CSS.css");
        stage.setScene(scene);

        Button twoVTwo = new Button("Start 2v2");
        twoVTwo.setOnAction((event) -> {
            start(robot);
        });

        Button testBtn = new Button("TEST");
        testBtn.setOnAction((event) -> {
            robot.playACard();
        });

        HBox buttons = new HBox();
        buttons.getChildren().addAll(twoVTwo, testBtn);


        rootPane.getChildren().add(buttons);

        stage.show();
    }

    public static void start(RobotO robot) {
        boolean carryOn = true;
        while (carryOn) {

            robot.StartA2V2();
            robot.Wait(300);
            robot.getRobot().mouseMove(-965,644);
            boolean inAGame = false;
            checkIfInGame:
            while(!inAGame) {
                for(int i=0; i<= 10; i++) {
                    robot.click();
                    inAGame = robot.CheckIfInAGame();
                    if(inAGame) {
                        break checkIfInGame;
                    }
                    robot.Wait(2000);
                }
                if(robot.CheckIfOnHomePage()) {
                    robot.StartA2V2();
                }
            }
            System.out.println("Game Found");
            while(inAGame) {
                robot.playACard();
                robot.Wait(3000);
                inAGame = robot.CheckIfInAGame();
            }
            robot.Wait(2000);

            robot.exitGame();

        }
    }

    private static void terminate()
    {
        System.out.println("bye bye!");
        System.exit(0);
    }

}
