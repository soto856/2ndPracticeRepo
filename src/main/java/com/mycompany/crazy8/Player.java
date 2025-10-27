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

public class Player {
    private final String name;
    private final List<Card> hand = new ArrayList<>();

    public Player(String name) {
        this.name = name;
    }

    public String getName() { return name; }
    public List<Card> getHand() { return hand; }

    public void take(Card c) {
        hand.add(c);
    }

    public boolean hasWon() {
        return hand.isEmpty();
    }

    public boolean takeTurn(List<Card> discard, Deck deck, int[] currentSuit) {
        Card top = discard.isEmpty() ? null : discard.get(discard.size() - 1);
        int idxToPlay = -1;
        boolean playedEight = false;

        for (int i = 0; i < hand.size(); i++) {
            Card c = hand.get(i);
            if (c.matches(top, currentSuit[0])) {
                idxToPlay = i;
                playedEight = c.isEight();
                break;
            }
        }

        int draws = 0;
        while (idxToPlay == -1 && draws < 3 && !deck.isEmpty()) {
            Card d = deck.draw();
            System.out.println(name + " drew card from deck " + (52 - deck.size()) + " " + d.toString());
            hand.add(d);
            draws++;
            if (d.matches(top, currentSuit[0])) {
                idxToPlay = hand.size() - 1;
                playedEight = d.isEight();
                break;
            }
        }

        if (idxToPlay == -1) {
            if (draws == 3) System.out.println("No suitable card after 3 draws");
            return false;
        }

        Card played = hand.remove(idxToPlay);
        discard.add(played);

        if (top != null)
            System.out.println("(" + top.getRank() + " " + currentSuit[0] + "):");

        if (draws == 0)
            System.out.println(name + " has matching card in hand: " + played.shortString(top, currentSuit[0]));
        else
            System.out.println("Card now matches: " + played.shortString(top, currentSuit[0]));

        if (played.isEight()) {
            int[] counts = new int[4];
            for (Card c : hand) counts[c.getSuit()]++;
            int best = 0;
            for (int i = 1; i < 4; i++) if (counts[i] > counts[best]) best = i;
            currentSuit[0] = best;
        } else {
            currentSuit[0] = played.getSuit();
        }

        return true;
    }
}
