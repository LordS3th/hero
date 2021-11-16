import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;

public class Arena {
    private int width;
    private int height;
    Position position= new Position(10,10);
    Hero hero= new Hero(position);
    public Arena(int width, int height){
        this.width=width;
        this.height=height;
    }
    public void draw(Screen screen){
        hero.draw(screen);
    }
    public void processKey(KeyStroke key){
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
    }
    public void moveHero(Position position) {
        if (canHeroMove(position))
            hero.setPosition(position);
    }
    boolean canHeroMove(Position position){
        if (position.getX()> width || position.getX()<0){
            return false;
        }
        else if (position.getY()>height || position.getY()<0){
            return false;
        }
        else return true;
    }
}
