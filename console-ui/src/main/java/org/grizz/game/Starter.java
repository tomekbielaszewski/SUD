package org.grizz.game;

import org.grizz.game.model.PlayerResponse;
import org.grizz.game.service.simple.Notifier;
import org.grizz.game.ui.OutputFormatter;
import org.grizz.game.ui.impl.OutputFormatterImpl;

import java.util.Scanner;

/**
 * Created by Grizz on 2015-04-17.
 */
public class Starter {

    public static void main(String[] args) {
        OutputFormatter formatter = new OutputFormatterImpl();
        Game game = GameFactory.getInstance(new Notifier() {
            @Override
            public void notify(String playerName, String event) {
                System.out.println(String.format("%s --> %s", playerName, event));
            }
        });

        Scanner sc = new Scanner(System.in);

        try {
//        System.out.println("Podaj nazwe gracza...");
//        String player = sc.nextLine();
            String player = "Grizz";
            String command = "spojrz";
//            String command = "wykuj sztabka zlota 1";

            PlayerResponse response = game.runCommand(command, player);
            System.out.println(formatter.format(response));

            while (!(command = sc.nextLine()).equals("q")) {
                response = game.runCommand(command, player);
                System.out.println(formatter.format(response));
            }
        } finally {
            sc.close();
        }
    }
}
