/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeon;

/**
 *
 * @author Davor
 */
public class Player {

    private int xCoordinate;
    private int yCoordinate;
    private char playerSign = '@';
    private int length;
    private int height;

    public Player(int length, int height) {
        this.xCoordinate = 0;
        this.yCoordinate = 0;
        this.length = length;
        this.height = height;
    }

    public char playerSign() {
        return this.playerSign;
    }

    public int getXCoordinate() {
        return this.xCoordinate;
    }

    public int getYCoordinate() {
        return this.yCoordinate;
    }

    public String toString() {
        return this.playerSign + " " + this.getXCoordinate() + " " + this.getYCoordinate();
    }

    public void moveUp() {
        if (this.yCoordinate > 0) {
            this.yCoordinate--;
        }
    }

    public void moveDown() {
        if (this.yCoordinate < this.height - 1) {
            this.yCoordinate++;
        }
    }

    public void moveLeft() {
        if (this.xCoordinate > 0) {
            this.xCoordinate--;
        }
    }

    public void moveRight() {
        if (this.xCoordinate < this.length - 1) {
            this.xCoordinate++;
        }
    }

    public void moveDirection(char keyMove) {
        if (keyMove == 'w') {
            this.moveUp();
        }
        if (keyMove == 's') {
            this.moveDown();
        }
        if (keyMove == 'a') {
            this.moveLeft();
        }
        if (keyMove == 'd') {
            this.moveRight();
        }
    }
}
