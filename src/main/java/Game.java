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
    private int x = 10;
    private int y = 10;
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
        private void draw () {
            screen.clear();
            screen.setCharacter(x, y, TextCharacter.fromCharacter('X')[0]);
            try {
                screen.refresh();
            } catch (IOException e) {        //Need to understand exceptions
                e.printStackTrace();
            }
        }
        public void run () {
            while(1==1) {
                draw();
                try {
                    KeyStroke key = screen.readInput();
                    if (key.getKeyType()==KeyType.EOF) break;
                    if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q') screen.close();
                    switch (key.getKeyType()) {
                        case ArrowUp:
                            y -= 1;
                            break;

                        case ArrowDown:
                            y += 1;
                            break;

                        case ArrowLeft:
                            x -= 1;
                            break;
                        case ArrowRight:
                            x += 1;
                            break;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
    }
}
