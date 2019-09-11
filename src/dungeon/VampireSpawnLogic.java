/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeon;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Davor
 */
public class VampireSpawnLogic {

    private Vampire vampire;
    private int numberOfVampires;
    private int xOsLimit;
    private int yOsLimit;
    private Random random;
    private List<Vampire> vampires;
    private List<Vampire> vampiresToBeRemoved;

    public VampireSpawnLogic(int numberOfVampires, int length, int height) {
        this.numberOfVampires = numberOfVampires;
        this.xOsLimit = length;
        this.yOsLimit = height;
        this.random = new Random();
        this.vampires = new ArrayList<Vampire>();
        this.vampiresToBeRemoved = new ArrayList<Vampire>();        
        this.createVampires();
    }

    private void createVampires() {
        for (int i = 0; i < this.numberOfVampires; i++) {
            this.vampires.add(new Vampire(this.xOsLimit, this.yOsLimit));
        }
    }

    public List<Vampire> allVampires() {
        return this.vampires;
    }

    public void placeVampire(Vampire vamp) {
        int x = random.nextInt(xOsLimit);
        int y = random.nextInt(yOsLimit);
        vamp.setXCoordinate(x);
        vamp.setYCoordinate(y);
    }
    
    public void addVampiresToRemove(Vampire vamp) {
        this.vampiresToBeRemoved.add(vamp);
    }
    
    public void removeVampires() {
        this.vampires.removeAll(this.vampiresToBeRemoved);
    }
}
