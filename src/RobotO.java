import java.awt.*;
import java.awt.event.InputEvent;
import java.util.Random;

public class RobotO {

    public static Robot robot;

    public RobotO(Robot robot) {
        this.robot = robot;
    }

    public Robot getRobot() {return robot;}
    public void setRobot(Robot robot) {this.robot = robot;}

    public static void getInfo() {
        int x = MouseInfo.getPointerInfo().getLocation().x;
        int y = MouseInfo.getPointerInfo().getLocation().y;
        Color color = robot.getPixelColor(x, y);
        System.out.println("X: " + x + ", Y: " + y + ", Colour: " + color.getRed() + ", " + color.getGreen() + ", " + color.getBlue());
        System.out.println("robot.mouseMove(" + MouseInfo.getPointerInfo().getLocation().x + "," + MouseInfo.getPointerInfo().getLocation().y+ ");");
    }

    public static void Wait(int x) {
        try {
            Thread.sleep(x);
        } catch (InterruptedException e) {
            System.out.println("Error using Thread.sleep()" + e);
        }
    }

    public static void click() {
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
    }

    public static void StartA2V2() {
        System.out.println("StartA2V2();");
        Wait(2000);
        robot.mouseMove( -870,648);
        Wait(300);
        click();
        robot.mouseMove(-852,579);
        Wait(300);
        click();

    }

    public static boolean CheckIfInAGame() {
        //System.out.println("checkIfInAGame();");
        boolean inGame;
        Color color = new Color(254, 236,173);
        if (robot.getPixelColor(-711,62).getRGB() == color.getRGB()) {
            inGame = true;
            //System.out.println("In a game");
        } else {
            //System.out.println(color);
            inGame = false;
        }
        return inGame;
    }

    public static boolean CheckIfOnHomePage() {
        boolean onHomeScreen = false;
        Color color = new Color(48, 169, 18);
        if (robot.getPixelColor(-721, 66).getRGB() == color.getRGB()) {
            onHomeScreen = true;
        } else {
            onHomeScreen = false;
        }
        return onHomeScreen;
    }

    public static void playACard() {
        Random rand = new Random();
        int cardNo = rand.nextInt(4) + 1;
        //System.out.println(cardNo);
        switch(cardNo) {
            case 1: robot.mouseMove(-1061,888); Wait(300); click(); break;
            case 2: robot.mouseMove(-963,888); Wait(300); click(); break;
            case 3: robot.mouseMove(-861,888); Wait(300); click(); break;
            case 4: robot.mouseMove(-758,888); Wait(300); click(); break;
        }

        int placePositon = rand.nextInt(7)+1;
        switch(placePositon) {
            case 1: robot.mouseMove(-1151,609); Wait(300); click(); break;
            case 2: robot.mouseMove(-1042,600); Wait(300); click(); break;
            case 3: robot.mouseMove(-1039,656); Wait(300); click(); break;
            case 4: robot.mouseMove(-1163,694); Wait(300); click(); break;
            case 5: robot.mouseMove(-1110,697); Wait(300); click(); break;
            case 6: robot.mouseMove(-1019,761); Wait(300); click(); break;
            case 7: robot.mouseMove(-1157,748); Wait(300); click(); break;
        }
    }

    public static void exitGame() {
        boolean atMenu = false;
        exit:
        do {
            for(int i = 0; i<=3; i++) {
                robot.mouseMove(-1150, 945);
                click();
                Wait(5000);
                atMenu = CheckIfOnHomePage();
                if (atMenu) {
                    break exit;
                }
            }
            robot.mouseMove(-961,822);
            Wait(300);
            click();
            Wait(200);
            robot.mouseMove(-959,875);
            Wait(300);
            click();
        } while(!atMenu);
    }

}


