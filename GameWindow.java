package soccerGame;

import java.awt.Color;
import java.util.Random;

import CS2114.Button;
import CS2114.CircleShape;
import CS2114.Shape;
import CS2114.TextShape;
import CS2114.Window;
import CS2114.WindowSide;

public class GameWindow {
    private final int WIDTH_UNIT;
    private final int HEIGHT_UNIT;
    private Window gameWindow;
    private int xGoalie;
    private int yGoalie;
    private int xBall;
    private int yBall;
    private int xTarget;
    private int yTarget;
    
    
    public GameWindow()
    {
        xGoalie = 3;
        yGoalie = 2;
        xBall = 3;
        yBall=4;
        xTarget = 3;
        yTarget = 3;      
        gameWindow = new Window();
        WIDTH_UNIT = 250;
        HEIGHT_UNIT = 200;
        gameWindow.setSize(7 * WIDTH_UNIT, 5 * HEIGHT_UNIT);
        this.createBackground();
        this.createBall(xBall, yBall);
        this.goalie(xGoalie, yGoalie);
        this.createTarget(xTarget, yTarget);
        Button quit;
        Button next;
        Button prev;
        Button shoot;
        quit = new Button();
        next = new Button();
        prev = new Button();
        shoot = new Button();
        this.createButton(quit, next, prev, shoot);
    }

    
    private void createButton(Button quit, Button next,
            Button prev, Button shoot)
    {
        gameWindow.addButton(quit, WindowSide.SOUTH);
        quit.setTitle("QUIT");
        quit.onClick(this, "clickedQuit");
        gameWindow.addButton(prev, WindowSide.NORTH);
        prev.setTitle("PREV");
        prev.onClick(this, "clickedPrev");
        gameWindow.addButton(shoot, WindowSide.NORTH);
        shoot.setTitle("SHOOT!");
        shoot.onClick(this, "clickedShoot");
        gameWindow.addButton(next, WindowSide.NORTH);
        next.setTitle("NEXT");
        next.onClick(this, "clickedNext");
    }
    
    public void clickedShoot(Button button)
    {
        gameWindow.removeAllShapes();
        this.createBackground();
        Random rand = new Random();
        int y = rand.nextInt(2) + 1;
        int x = rand.nextInt(5) + 1;
        int compY = y+1;
        int compX = x;
        this.goalie(x, y);
        this.createBall(xTarget, yTarget);
        if (compY == yTarget)
        {
            if((compX == xTarget) || (compX+1 == xTarget) || (compX-1 == xTarget))
            {
                TextShape cont = new TextShape(WIDTH_UNIT + 100, HEIGHT_UNIT - 100, "SAVED! Press Next to try again!", Color.black);
                this.addToGame(cont);
            }
            else
            {
                TextShape cont = new TextShape(WIDTH_UNIT + 100, HEIGHT_UNIT - 100, "GOAL! Press Next to play again!", Color.black);
                this.addToGame(cont);
            }
        }
        else
        {
            TextShape cont = new TextShape(WIDTH_UNIT + 100, HEIGHT_UNIT - 100, "GOAL! Press Next to play again!", Color.black);
            this.addToGame(cont);
        }
    }
    
    public void clickedPrev(Button button)
    {
        if (yTarget == 3)
        {
            if (xTarget == 1)
            {
                yTarget = 2;
            }
            else
            {
            xTarget = xTarget - 1;
            }
        }
        else
        {
            if (xTarget == 5)
            {
                yTarget = 3;
            }
            else
            {
            xTarget = xTarget + 1;
            }
        }
        gameWindow.removeAllShapes();
        this.createBackground();
        this.createBall(xBall, yBall);
        this.goalie(xGoalie, yGoalie);
        this.createTarget(xTarget, yTarget);
    }

    public void clickedNext(Button button)
    {
        if (yTarget == 3)
        {
            if (xTarget == 5)
            {
                yTarget = 2;
            }
            else
            {
            xTarget = xTarget + 1;
            }
        }
        else
        {
            if (xTarget == 1)
            {
                yTarget = 3;
            }
            else
            {
            xTarget = xTarget - 1;
            }
        }
        gameWindow.removeAllShapes();
        this.createBackground();
        this.createBall(xBall, yBall);
        this.goalie(xGoalie, yGoalie);
        this.createTarget(xTarget, yTarget);
    }
    
    public void clickedQuit(Button button) {
        System.exit(0);
    }

    public void createTarget(int xPos, int yPos)
    {
       
        xPos = (xPos * WIDTH_UNIT) + 100;
        yPos = (yPos * HEIGHT_UNIT) - 100;
        TextShape text = new TextShape(xPos, yPos, "H E R E", Color.black);
        this.addToGame(text);
        
    }
    
    public void addToGame(Shape shape)
    {
        gameWindow.addShape(shape);
        gameWindow.moveToFront(shape);
    }
    
    public void createBackground()
    {
        Shape backGround = new Shape(0, 0, 7 * WIDTH_UNIT, 5 * HEIGHT_UNIT, Color.cyan);
        gameWindow.addShape(backGround);
        Shape grass = new Shape(0, 2 * HEIGHT_UNIT, 7 * WIDTH_UNIT, 3 * HEIGHT_UNIT, Color.green);
        gameWindow.addShape(grass);
        gameWindow.moveToFront(grass);
        Shape leftBar = new Shape(WIDTH_UNIT - 25, HEIGHT_UNIT, 25, 2 * HEIGHT_UNIT, Color.white);
        Shape middleBar = new Shape(WIDTH_UNIT - 25, HEIGHT_UNIT - 25, 5 * WIDTH_UNIT + 50, 25, Color.white);
        Shape rightBar = new Shape(6 * WIDTH_UNIT, HEIGHT_UNIT, 25, 2 * HEIGHT_UNIT, Color.white);
        gameWindow.addShape(leftBar);
        gameWindow.moveToFront(leftBar);
        gameWindow.addShape(middleBar);
        gameWindow.moveToFront(middleBar);
        gameWindow.addShape(rightBar);
        gameWindow.moveToFront(rightBar);
    }
    
    
    public void goalie(int xPos, int yPos)
    {
        
        xPos = xPos * WIDTH_UNIT;
        yPos = yPos * HEIGHT_UNIT;
        createFace(xPos, yPos);
        rightHand(xPos, yPos);
        leftHand(xPos + 250, yPos);
        
    }
    
    private void createFace(int xPos, int yPos)
    { 
       
        CircleShape leftEye = new CircleShape(xPos + 25, yPos, 100, Color.black);
        CircleShape rightEye = new CircleShape(xPos + 100 + 25, yPos, 100, Color.black);
        CircleShape mouth = new CircleShape(xPos + 75, yPos + 100, 100, Color.red);
        this.addToGame(mouth);
        this.addToGame(rightEye);
        this.addToGame(leftEye);
        
        
    }
    
    private void rightHand(int xPos, int yPos)
    {
        Shape palm = new Shape(xPos - 30, yPos + 50, 30, HEIGHT_UNIT - 50, Color.gray);
        Shape thumb = new Shape(xPos - 40, yPos, 40, 30, Color.gray);
        Shape point = new Shape(xPos - 100 - 40, yPos + 50, 100, 30, Color.gray);
        Shape middle = new Shape(xPos - 150 - 40, yPos + 90, 150, 30, Color.gray);
        Shape ring = new Shape(xPos - 100 - 40, yPos + 130, 100, 30, Color.gray);
        Shape pinky = new Shape(xPos - 50 - 40, yPos + 170, 50, 30, Color.gray);
        this.addToGame(palm);
        this.addToGame(pinky);
        this.addToGame(ring);
        this.addToGame(middle);
        this.addToGame(point);
        this.addToGame(thumb);
    }
    
    private void leftHand(int xPos, int yPos)
    {
        Shape palm = new Shape(xPos, yPos + 50, 30, HEIGHT_UNIT - 50, Color.gray);
        Shape thumb = new Shape(xPos, yPos, 40, 30, Color.gray);
        Shape point = new Shape(xPos + 40, yPos + 50, 100, 30, Color.gray);
        Shape middle = new Shape(xPos + 40, yPos + 90, 150, 30, Color.gray);
        Shape ring = new Shape(xPos + 40, yPos + 130, 100, 30, Color.gray);
        Shape pinky = new Shape(xPos + 40, yPos + 170, 50, 30, Color.gray);
        this.addToGame(palm);
        this.addToGame(pinky);
        this.addToGame(ring);
        this.addToGame(middle);
        this.addToGame(point);
        this.addToGame(thumb);
    }
    
    public void createBall(int xPos, int yPos)
    {
        xPos = xPos * WIDTH_UNIT;
        yPos = (yPos * HEIGHT_UNIT) - 75;
        CircleShape c1 = new CircleShape(xPos + 100, yPos, 20, Color.black);
        this.addToGame(c1);//3
        this.addToGame(this.createLeftPortion(c1));//2
        this.addToGame(this.createLeftPortion(this.createLeftPortion(c1))); //1
        this.addToGame(this.createRightPortion(c1));//4
        this.addToGame(this.createRightPortion(this.createRightPortion(c1)));//5
        
        this.addToGame(this.createTopPortion(this.createLeftPortion(this.createLeftPortion(c1))));
        this.addToGame(this.createTopPortion(this.createLeftPortion(c1)));
        this.addToGame(this.createTopPortion(c1));
        this.addToGame(this.createTopPortion(this.createRightPortion(c1)));
        
        this.addToGame(this.createBottomPortion(this.createLeftPortion(this.createLeftPortion(c1))));
        this.addToGame(this.createBottomPortion(this.createLeftPortion(c1)));
        this.addToGame(this.createBottomPortion(c1));
        this.addToGame(this.createBottomPortion(this.createRightPortion(c1)));
        
        this.addToGame(this.createTopPortion(this.createTopPortion(this.createLeftPortion(this.createLeftPortion(c1)))));
        this.addToGame(this.createTopPortion(this.createTopPortion(this.createLeftPortion(c1))));
        this.addToGame(this.createTopPortion(this.createTopPortion(c1)));
        
        this.addToGame(this.createBottomPortion(this.createBottomPortion(this.createLeftPortion(this.createLeftPortion(c1)))));
        this.addToGame(this.createBottomPortion(this.createBottomPortion(this.createLeftPortion(c1))));
        this.addToGame(this.createBottomPortion(this.createBottomPortion(c1)));

    }
    
    private CircleShape createTopPortion(CircleShape circle)
    {
        CircleShape tp = new CircleShape(circle.getX() + 10, circle.getY() - 20, circle.getHeight(), Color.black);
        return tp;
    }
    
    private CircleShape createBottomPortion(CircleShape circle)
    {
        CircleShape bp = new CircleShape(circle.getX() + 10, circle.getY() + 20, circle.getHeight(), Color.black);
        return bp;
    }
    
    private CircleShape createLeftPortion(CircleShape circle)
    {
        CircleShape lp = new CircleShape(circle.getX() - 20, circle.getY(), circle.getHeight(), Color.black);
        return lp;
    }
    
    private CircleShape createRightPortion(CircleShape circle)
    {
        CircleShape rp = new CircleShape(circle.getX() + 20, circle.getY(), circle.getHeight(), Color.black);
        return rp;
    }
    
    
    }
    


