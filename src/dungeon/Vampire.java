/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeon;

import java.util.HashMap;

/**
 *
 * @author Davor
 */
public class Vampire {

    private int xCoordinate;
    private int yCoordinate;
    private char vampireSign = 'v';
    private int length;
    private int height;

    public Vampire(int length, int height) {
        this.length = length;
        this.height = height;
    }

    public int getXCoordinate() {
        return this.xCoordinate;
    }

    public int getYCoordinate() {
        return this.yCoordinate;
    }

    public void setXCoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public void setYCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    public char getVampireSign() {
        return this.vampireSign;
    }

    public String toString() {
        return this.getVampireSign() + " " + this.getXCoordinate() + " " + this.getYCoordinate();
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

    public void moveDirection(int number, HashMap<Integer, char[]> dungeon) {
        if (number == 0) {
            if (this.yCoordinate != 0 && dungeon.get(this.yCoordinate - 1)[this.xCoordinate] == '.') {
                this.moveUp();
            }
        }
        if (number == 1) {
            if (this.yCoordinate < this.height - 1 && dungeon.get(this.yCoordinate + 1)[this.xCoordinate] == '.') {
                this.moveDown();
            }
        }
        if (number == 2) {
            if (this.xCoordinate != 0 && dungeon.get(this.yCoordinate)[this.xCoordinate - 1] == '.') {
                this.moveLeft();
            }
        }
        if (number == 3) {
            if (this.xCoordinate < this.length - 1 && dungeon.get(this.yCoordinate)[this.xCoordinate + 1] == '.') {
                this.moveRight();
            }
        }
    }
}
