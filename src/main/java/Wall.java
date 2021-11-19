import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;


public class Wall {
    private Position position;
    private int x;
    private int y;
    public Wall(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public Wall(Position position){
        this.position=position;
    }
    public void draw(TextGraphics graphics){
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(x, y),"W");
    }
    public Position getPosition() {
        Position position = new Position (x,y);
        return position;
    }
}
