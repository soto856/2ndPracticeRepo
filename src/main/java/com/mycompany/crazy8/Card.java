/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.crazy8;

/**
 *
 * @author santi
 */
public class Card {
    private final int rank; 
    private final int suit; 

    public Card(int rank, int suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public int getRank() { return rank; }
    public int getSuit() { return suit; }

    public boolean isEight() { return rank == 8; }

    public boolean matches(Card top, int currentSuit) {
        if (isEight()) return true;
        if (top == null) return true;
        return rank == top.rank || suit == currentSuit;
    }

    public String shortString(Card top, int currentSuit) {
        boolean markRank = false, markSuit = false;
        if (top != null) {
            markRank = (this.rank == top.rank);
            markSuit = (this.suit == currentSuit);
        }
        String r = (markRank ? "*" : "") + rank;
        String s = (markSuit ? "*" : "") + suit;
        return r + " " + s;
    }

    @Override
    public String toString() {
        return "(" + rank + " " + suit + ")";
    }
}
