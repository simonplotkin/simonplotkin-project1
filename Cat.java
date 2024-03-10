public class Cat extends Creature {
    private int timeSinceAte;

    public Cat(int x, int y, City city) {
        super(x,y,city);
        lab = LAB_YELLOW;
        stepLen = 2;
        timeSinceAte = 0;
    }

    public void takeAction() {

        if(dead) {
            return;
        }

        this.lab = LAB_YELLOW;
        Mouse targetMouse = null;
        int closest = 20;

        for(Creature c : city.creatures) {
            GridPoint creature_gridPoint = c.getGridPoint();
            int dist = this.getGridPoint().dist(creature_gridPoint);
            if((c instanceof Mouse) && (dist < closest) && (!c.dead)){
                targetMouse = (Mouse) c;
                closest = dist;
            } 
        }

        if(targetMouse != null && !targetMouse.dead ) {
            this.lab = LAB_CYAN;
            pursuitMode(targetMouse);
        } else {
            this.lab = LAB_YELLOW;
        }
    }

    public void step(){

        if(timeSinceAte == 50){
            this.dead = true;
            return;
        } else {

            timeSinceAte++;
            if(city.getNextRandomTurn() == 0) {
                this.randomTurn();
            } 
            super.step();
        }
    }

    private void pursuitMode(Mouse targetMouse) {
        if(!(targetMouse.getGridPoint().equals(this.getGridPoint()))){
            int xDist = targetMouse.getX() - this.getX();
            int yDist = targetMouse.getY() - this.getY();
            if(Math.abs(yDist) > Math.abs(xDist)) {
                if(yDist > 0) {
                    this.specificTurn(2);
                } else {
                    this.specificTurn(0);
                }
            } else {
                if(xDist > 0) {
                    this.specificTurn(1);
                } else {
                    this.specificTurn(3);
                }
            }

        } else {
            this.lab = LAB_YELLOW;
            targetMouse.dead = true;
            timeSinceAte = 0;
        }
        
    }
}