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
    Position position= new Position(10,10);
    Hero hero= new Hero(position);
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
    private void moveHero(Position position) {
        hero.setPosition(position);
    }
        private void draw (){
            screen.clear();
            hero.draw(screen);
            try {
                screen.refresh();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        public void run (){
            while(1==1) {
                draw();
                try {
                    KeyStroke key = screen.readInput();
                    if (key.getKeyType()==KeyType.EOF) break;
                    if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q') screen.close();
                    switch (key.getKeyType()) {
                        case ArrowUp:
                            moveHero(hero.moveUp());
                            break;
                        case ArrowDown:
                            moveHero(hero.moveDown());
                            break;
                        case ArrowLeft:
                            moveHero(hero.moveLeft());
                            break;
                        case ArrowRight:
                            moveHero(hero.moveRight());
                            break;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
    }
}
