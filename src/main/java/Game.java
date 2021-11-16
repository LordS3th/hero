import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import javax.swing.plaf.basic.BasicArrowButton;
import java.io.IOException;

public class Game {
    Arena arena = new Arena(20, 10);
    Screen screen;

    public Game() {
        try {
            Terminal terminal = new DefaultTerminalFactory().createTerminal();
            screen = new TerminalScreen(terminal);
            screen.setCursorPosition(null);   // we don't need a cursor
            screen.startScreen();             // screens must be started
            screen.doResizeIfNecessary();     // resize screen if necessary
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void processKey(KeyStroke key) {
        arena.processKey(key);
    }

    private void draw() {
        screen.clear();
        arena.draw(screen.newTextGraphics());
        try {
            screen.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        while (1 == 1) {
            draw();
            try {
                KeyStroke key = screen.readInput();
                if (key.getKeyType() == KeyType.EOF) break;
                if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q') screen.close();
                processKey(key);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
