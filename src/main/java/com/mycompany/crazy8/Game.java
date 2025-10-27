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

public class Game {
    private final List<Player> players = new ArrayList<>();
    private final Deck deck = new Deck();
    private final List<Card> discard = new ArrayList<>();
    private final int[] currentSuit = new int[1]; 

    public Game(String... names) {
        for (String n : names) players.add(new Player(n));
    }

    public void deal(int each) {
        for (int r = 0; r < each; r++) {
            for (Player p : players) {
                p.take(deck.draw());
            }
        }
    }

    public String run() {
        StringBuilder log = new StringBuilder();

        log.append("Building deck...\n");
        deck.build52();
        log.append("Shuffling deck...\n");
        deck.shuffle();
        log.append("Dealing cards...\n");

        deal(5);

        for (Player p : players) {
            log.append("\n").append(p.getName()).append(":\n");
            for (Card c : p.getHand()) {
                log.append("  .").append(c.getRank()).append(" ").append(c.getSuit()).append("\n");
            }
        }

        Card starter = deck.draw();
        discard.add(starter);
        currentSuit[0] = starter.getSuit();

        log.append("\n(").append(starter.getRank()).append(" ").append(starter.getSuit()).append("):\n");

        int turn = 0;
        while (true) {
            Player current = players.get(turn);
            current.takeTurn(discard, deck, currentSuit);

            if (current.hasWon()) {
                log.append("-------- ").append(current.getName()).append(" won ***********\n");
                break;
            }

            turn = (turn + 1) % players.size();
        }

        return log.toString();
    }
}
