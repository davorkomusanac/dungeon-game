/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeon;

import java.util.HashMap;
import java.util.Scanner;
import java.util.Random;

/**
 *
 * @author Davor
 */
public class Dungeon {

    private int length;
    private int height;
    private int numberOfVampires;
    private int moves;
    private boolean vampiresMove;
    private Random random;
    private Scanner read;
    private Player player;
    private HashMap<Integer, char[]> dungeonDimension;
    private VampireSpawnLogic vampSpawn;

    public Dungeon(int length, int height, int vampires, int moves, boolean vampiresMove) {
        this.length = length;
        this.height = height;
        this.numberOfVampires = vampires;
        this.moves = moves;
        this.vampiresMove = vampiresMove;
        this.dungeonDimension = new HashMap<Integer, char[]>();
        this.player = new Player(length, height);
        this.vampSpawn = new VampireSpawnLogic(vampires, length, height);
        this.read = new Scanner(System.in);
        this.random = new Random();
    }

    public void run() {
        this.createDungeon();
        this.placePlayer();
        this.placeVampires();
        this.printTablePositions();
        this.printDungeon();
        this.moveWithCounting();
    }

    public void createDungeon() {
        for (int j = 0; j < this.height; j++) {
            this.dungeonDimension.put(j, new char[this.length]);
        }
        this.cleanDungeon();
    }

    public void printDungeon() {
        for (Integer key : this.dungeonDimension.keySet()) {
            System.out.println(this.dungeonDimension.get(key));
        }
        System.out.println("");
    }

    public void cleanDungeon() {
        for (Integer key : this.dungeonDimension.keySet()) {
            for (int i = 0; i < this.length; i++) {
                this.dungeonDimension.get(key)[i] = '.';
            }
        }
    }

    public void placePlayer() {
        this.dungeonDimension.get(this.player.getYCoordinate())[this.player.getXCoordinate()] = this.player.playerSign();
    }

    public void placeVampires() {
        for (Vampire vamp : this.vampSpawn.allVampires()) {
            while (this.checkForDuplicateVamp(vamp)) {
                this.vampSpawn.placeVampire(vamp);
            }
            this.dungeonDimension.get(vamp.getYCoordinate())[vamp.getXCoordinate()] = vamp.getVampireSign();
        }
    }

    public void move() {
        String moveList = read.nextLine().toLowerCase();
        for (int i = 0; i < moveList.length(); i++) {
            this.player.moveDirection(moveList.charAt(i));
            for (Vampire vamp : this.vampSpawn.allVampires()) {
                int randomMoveDirection = this.random.nextInt(4);
                if(this.vampiresMove) {
                    vamp.moveDirection(randomMoveDirection, this.dungeonDimension);
                }                
                this.PlayerTouchesVampire(this.player, vamp);
            }
            this.vampSpawn.removeVampires();
            this.updateDungeonBoard();
        }
    }

    public void PlayerTouchesVampire(Player player, Vampire vamp) {
        if (player.getXCoordinate() == vamp.getXCoordinate() && player.getYCoordinate() == vamp.getYCoordinate()) {
            this.vampSpawn.addVampiresToRemove(vamp);
        }
    }

    public void moveWithCounting() {
        while (this.moves > 0) {
            this.moves--;
            this.move();
            if (this.vampSpawn.allVampires().isEmpty()) {
                System.out.println("YOU WIN");
                return;
            }
            this.printTablePositions();
            this.printDungeon();
            if (this.moves == 0) {
                System.out.println("YOU LOSE");
            }
        }
    }

    public void updateDungeonBoard() {
        this.cleanDungeon();
        this.placePlayer();
        this.moveVampires();
    }

    public void moveVampires() {
        for (Vampire vamp : this.vampSpawn.allVampires()) {
            if (!this.checkForDuplicateVamp(vamp)) {
                this.dungeonDimension.get(vamp.getYCoordinate())[vamp.getXCoordinate()] = vamp.getVampireSign();
            }
        }
    }

    private boolean checkForDuplicateVamp(Vampire vampire) {
        return this.dungeonDimension.get(vampire.getYCoordinate())[vampire.getXCoordinate()] != '.';
    }

    public void printTablePositions() {
        System.out.println(this.moves + "\n");
        System.out.println(this.player);
        for (Vampire vamp : this.vampSpawn.allVampires()) {
            System.out.println(vamp);
        }
        System.out.println("");
    }
}
