public class Mouse extends Creature {
    private int timeSinceBorn;

    public Mouse(int x, int y, City city){
        super(x,y,city);
        lab = LAB_BLUE;
        stepLen = 1;
        timeSinceBorn = 0;
    }

    public void step(){
        if(timeSinceBorn == 22){
            this.dead = true;
            return;
        } else {
            timeSinceBorn++;
            if(city.getNextRandomTurn() <= 1) {
                this.randomTurn();
            }
            super.step();
        }
    }

    public void takeAction(){
        if(timeSinceBorn == 20){
            city.addMouse(this.getX(),this.getY());
        }
    }


}
