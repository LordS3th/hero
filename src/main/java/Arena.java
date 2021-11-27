import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Arena {
    private int width;
    private int height;
    Position position = new Position(10, 8);
    Hero hero = new Hero( position );
    private List<Wall> walls;
    private List<Coins> coins;

    private List<Wall> createWalls() {
        List<Wall> walls = new ArrayList<>();
        for (int c = 0; c < width; c++) {
            Position position_1=new Position(c,0);
            walls.add(new Wall(position_1));
            Position position_2=new Position(c,height-1);
            walls.add(new Wall(position_2));
        }
        for (int r = 1; r < height - 1; r++) {
            Position position_3=new Position(0,r);
            walls.add(new Wall(position_3));
            Position position_4=new Position(width-1, r);
            walls.add(new Wall(position_4));
        }
        return walls;
    }
    private List<Coins> createCoins() {
        Random random = new Random();
        ArrayList<Coins> coins = new ArrayList<>();
        for (int i = 0; i < 5; i++){
            Position position_5= new Position(random.nextInt(width - 2) + 1,random.nextInt(height - 2) + 1);
            coins.add(new Coins(position_5));
        }
        return coins;
    }
    public Arena(int width, int height) {
        this.width = width;
        this.height = height;
        this.walls = createWalls();
        this.coins = createCoins();
    }

    public void draw(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#220287"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');
        hero.draw(graphics);
        for (Wall wall : walls) {
            wall.draw(graphics);
        }
        for (Coins coin: coins){
            coin.draw(graphics);
        }
    }

    public void processKey(KeyStroke key) {
        switch (key.getKeyType()) {
            case ArrowUp:
                moveHero(hero.moveUp());
                moveMonsters();
                break;
            case ArrowDown:
                moveHero(hero.moveDown());
                moveMonsters();
                break;
            case ArrowLeft:
                moveHero(hero.moveLeft());
                moveMonsters();
                break;
            case ArrowRight:
                moveHero(hero.moveRight());
                moveMonsters();
                break;
        }
    }

    public void moveHero(Position position) {
        if (canHeroMove(position))
            hero.setPosition(position);
            retrieveCoins();
    }

    boolean canHeroMove(Position position) {
        for (Wall wall:walls){
            if (wall.getPosition().equals(position)){
                return false;
            }
        }
        if (position.getX() > width || position.getX() < 0) {
            return false;
        } else if (position.getY() > height || position.getY() < 0) {
            return false;
        }
        else return true;
    }
    public void retrieveCoins(){
        for(Coins coin:coins){
            if (coin.getPosition().equals(position)){
                coins.remove(coin);
                break;
            }
        }
    }
    public void moveMonsters(){

    }
}


