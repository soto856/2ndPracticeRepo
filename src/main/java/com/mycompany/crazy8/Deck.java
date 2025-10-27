/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.crazy8;

/**
 *
 * @author santi
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Deck {
    private final List<Card> cards = new ArrayList<>();
    private final Random rng = new Random(System.nanoTime());

    public Deck() {
        build52();
    }

    public final void build52() {
        cards.clear();
        for (int r = 1; r <= 13; r++) {
            for (int s = 0; s < 4; s++) {
                cards.add(new Card(r, s));
            }
        }
    }

    public void shuffle() {
        for (int i = cards.size() - 1; i > 0; i--) {
            int j = rng.nextInt(i + 1);
            Card tmp = cards.get(i);
            cards.set(i, cards.get(j));
            cards.set(j, tmp);
        }
    }

    public boolean isEmpty() { return cards.isEmpty(); }

    public Card draw() {
        if (cards.isEmpty()) return null;
        return cards.remove(cards.size() - 1);
    }

    public int size() { return cards.size(); }
}
