import com.googlecode.lanterna.graphics.TextGraphics;

abstract class Element {
    protected Position position;
    public Element(Position position){
        this.position=position;
    }
    public abstract void draw(TextGraphics graphics);
    void setPosition(Position position){
        this.position.setX(position.getX());
        this.position.setY(position.getY());
    }
    public Position getPosition(){
        return position;
    }
}
