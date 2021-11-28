import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.Random;

class Monster extends Element {
    Monster(Position position) {
        super(position);
    }
    public void draw(TextGraphics graphics){
        graphics.setForegroundColor(TextColor.Factory.fromString("#00FF00"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(position.getX(), position.getY()),"M");
    }
    Position move(){
        Random rand = new Random();
        int rand_int= rand.nextInt(4);
        if (rand_int==1){
           Position new_position= new Position(position.getX()+1,position.getY());
           return new_position;
        }
        else if (rand_int==2){
            Position new_position= new Position(position.getX(),position.getY()+1);
            return new_position;
        }
        else if (rand_int==3){
            Position new_position= new Position(position.getX()-1, position.getY());
            return new_position;
        }
        else{
            Position new_position= new Position(position.getX(), position.getY()-1);
            return new_position;
        }
    }
}

