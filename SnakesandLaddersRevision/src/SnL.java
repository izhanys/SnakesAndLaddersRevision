/**
 * -----------------------------------------------------
 * ES234211 - Programming Fundamental
 * Genap - 2023/2024
 * Group Capstone Project: Snake and Ladder Game
 * -----------------------------------------------------
 * Class    : Q
 * Group    : 5
 * Members  :
 * 1. 5026231060 - Hafizhan Yusra Sulistyo
 * 2. 5026221049 - Justin Ariyuna
 * ------------------------------------------------------
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class SnL {

    // States, variables, or properties
    private int boardSize;
    private ArrayList<Player> players;
    private ArrayList<Snake> snakes;
    private ArrayList<Ladder> ladders;
    private int gameStatus;
    private int currentTurn;

    // Constructor
    public SnL(int size) {
        this.boardSize = size;
        this.snakes = new ArrayList<Snake>();
        this.ladders = new ArrayList<Ladder>();
        this.players = new ArrayList<Player>();
        this.gameStatus = 0;
    }

    public void initiateGame() {
        int[][] ladders = {
                {2, 23}, {8, 34}, {20, 77}, {32, 68},
                {41, 79}, {74, 88}, {82, 100}, {85, 95}
        };
        setLadders(ladders);
        int[][] snakes = {
                {47, 5}, {29, 9}, {38, 15}, {97, 25},
                {53, 33}, {92, 70}, {86, 54}, {97, 25}
        };
        setSnakes(snakes);
    }

    public Player getTurn() {
        if (this.gameStatus == 0) {
            double r = Math.random();
            if (r < 0.5) {
                this.currentTurn = 0;
                return this.players.get(0);
            } else {
                this.currentTurn = 1;
                return this.players.get(1);
            }
        } else {
            this.currentTurn = (this.currentTurn + 1) % this.players.size();
            return this.players.get(this.currentTurn);
        }
    }

    // Setter methods
    public void setSizeBoard(int size) {
        this.boardSize = size;
    }

    public void addPlayer(Player p) {
        this.players.add(p);
    }

    public void setLadders(int[][] ladders) {
        int s = ladders.length;
        for (int i = 0; i < s; i++) {
            this.ladders.add(new Ladder(ladders[i][0], ladders[i][1]));
        }
    }

    public void setSnakes(int[][] snakes) {
        int s = snakes.length;
        for (int i = 0; i < s; i++) {
            this.snakes.add(new Snake(snakes[i][0], snakes[i][1]));
        }
    }

    public int getBoardSize() {
        return this.boardSize;
    }

    public ArrayList<Player> getPlayers() {
        return this.players;
    }

    public ArrayList<Snake> getSnakes() {
        return this.snakes;
    }

    public ArrayList<Ladder> getLadders() {
        return this.ladders;
    }

    public int getGameStatus() {
        return this.gameStatus;
    }

    public void play() {
        initiateGame();

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of players:");
        int numberOfPlayers = sc.nextInt();
        sc.nextLine(); // Consume the newline

        for (int i = 1; i <= numberOfPlayers; i++) {
            System.out.println("Enter player " + i + " name:");
            String playerName = sc.nextLine();
            Player p = new Player(playerName);
            addPlayer(p);
        }

        Player nowPlaying;
        do {
            System.out.println("----------------------------------------------");
            nowPlaying = getTurn();
            System.out.println("Now Playing: " + nowPlaying.getName() + " the current position is " + nowPlaying.getPosition());
            System.out.println(nowPlaying.getName() + " it's your turn, please press enter to roll dice");

            String input = sc.nextLine();
            int x = 0;
            if (input.isEmpty()) {
                x = nowPlaying.rollDice();
            }

            System.out.println(nowPlaying.getName() + " is rolling dice and gets number: " + x);
            movePlayer(nowPlaying, x);
            System.out.println(nowPlaying.getName() + " new position is " + nowPlaying.getPosition());
        } while (getGameStatus() != 2);

        System.out.println("The Game is Over, the winner is: " + nowPlaying.getName());

        // Add confirmation before ending the game
        System.out.println("Do you want to view the final board state before ending the game? (yes/no)");
        String confirmation = sc.nextLine();
        if (confirmation.equalsIgnoreCase("yes")) {
            showFinalBoardState();
            displayBoard();
        }

        System.out.println("Thank you for playing!");
    }

    public void movePlayer(Player p, int x) {
        this.gameStatus = 1;
        p.moveAround(x, this.boardSize);
        for (Ladder l : this.ladders) {
            if (l.getFromPosition() == p.getPosition()) {
                p.setPosition(l.getToPosition());
                System.out.println(p.getName() + " got a ladder and jumps to " + p.getPosition());
            }
        }

        for (Snake s : this.snakes) {
            if (s.getHead() == p.getPosition()) {
                p.setPosition(s.getTail());
                System.out.println(p.getName() + " got a snake and slides down to " + p.getPosition());
            }
        }

        if (p.getPosition() == this.boardSize) {
            this.gameStatus = 2;
        }
    }

    // Method to show the final board state
    private void showFinalBoardState() {
        System.out.println("Final Board State:");
        for (Player p : players) {
            System.out.println(p.getName() + " is at position " + p.getPosition());

        }
    }
        public void displayBoard() {
            char[] display = new char[boardSize + 1];
            Arrays.fill(display, '-');

            // Place players on the board
            for (Player p : players) {
                int position = p.getPosition();
                int playerNumber = p.getNumber();

                // Convert player number to character, handling cases where number > 9
                if (display[position] == '-') {
                    display[position] = Character.forDigit(playerNumber, 10);
                } else {
                    display[position] = 'P';
                }
            }

            // Display the board
            for (int i = 9; i >= 0; i--) {
                if (i % 2 == 0) {
                    for (int j = 10 * i + 1; j <= 10 * (i + 1); j++) {
                        System.out.print(display[j] + " ");
                    }
                } else {
                    for (int j = 10 * (i + 1); j > 10 * i; j--) {
                        System.out.print(display[j] + " ");
                    }
                }
                System.out.println();
            }
        }
}
