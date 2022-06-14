package com.example.myprojectmolegame;


import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Model {
    // TODO - lose situation - all holes are filled - stop hits.
    // TODO boolean gameOver() method
//TODO:fart in uur fac


    private Element[] holes;
    private List<Integer> holesList = new ArrayList<Integer>();
    private List<Element> elementList = new ArrayList<Element>();
    private int streak = 0, numOfHoles;
    String gameMode;
    Element element,element2;


    //constructor creating an array and list that r equal to the num of holes in the board
    public Model(int numHoles, String gameMode) {
        numOfHoles = numHoles;
        this.holes = new Element[numHoles];
        this.gameMode = gameMode;
        for (int j = 0; j < holes.length; j++) {
            Log.d("num",j+"");
            holesList.add(j);
        }

        for (int i = 0; i < holes.length; i++) {
            holes[i] = Element.HOLE;
        }
        elementList.add(Element.MOLE);
        elementList.add(Element.CARROT);
        elementList.add(Element.BOMB);

    }


    public int hitElement(int holeIndex) {
        if (holeIndex >= holes.length) {
            return streak;
        } else {
            if (holes[holeIndex] == Element.HOLE) {
                streak = 0;
                return streak;
            } else {
                element2 = holes[holeIndex];
                holes[holeIndex] = Element.HOLE;
                holesList.add(holeIndex);
                streak++;
                return streak;

            }

        }
    }


    //    generating mole in a random hole and returning the hole pos
    public int generateMole() {
        int putMoleInThisHole;
//       i did an if Because if my board is full of moles then my app crash so if its full return -1 and the controller wont ask the view to display a mole
        if (!holesList.isEmpty()) {
            Collections.shuffle(holesList);
//            // DEBUG catch the hole
//            if (holesList.get(0) == 8 && holesList.size() > 1)
//                return generateMole();

            putMoleInThisHole = holesList.remove(0);
            holes[putMoleInThisHole] = Element.MOLE;
        } else {
            putMoleInThisHole = -1;
        }
        return putMoleInThisHole;
    }

    public int generateElement() {
        int putElementInThisHole;
        if (!holesList.isEmpty()) {
            Collections.shuffle(holesList);
            Collections.shuffle(elementList);
            putElementInThisHole = holesList.remove(0);
            holes[putElementInThisHole] = elementList.get(0);


        } else {
            putElementInThisHole = -1;
        }
        return putElementInThisHole;
    }
    public Element getElement() {
        element= elementList.get(0);
        return element;
    }

    public Element getElementClicked() {
        return element2;
    }

    public void updateNumOfHoles(int numOfHoles) {
        holesList = new ArrayList<Integer>();
        elementList = new ArrayList<Element>();
        this.holes = new Element[numOfHoles];
        for (int j = 0; j < holes.length; j++) {
            Log.d("num",j+"");
            holesList.add(j);
        }

        for (int i = 0; i < holes.length; i++) {
            holes[i] = Element.HOLE;
        }
    }
}
