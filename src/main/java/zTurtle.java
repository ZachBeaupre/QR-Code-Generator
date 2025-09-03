public class zTurtle {
    private int x;
    private int y;
    //test
    public zTurtle() {
        this(0, 0);
    }

    public zTurtle(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int sniff(int[][] arr, int x, int y) {
        return arr[y][x];
    }

    public void setPos(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    /*  POS:
     *   1 2 3
     *   4 5 6
     *   7 8 9
     * 5 is the default
     *
     */

    public void ArrMan(int[][] arr, int pos, int val) {
        move(pos);
        arr[y][x] = val;
    }

    public int checker(int[][] arr, int pos) {
        int tx = x;
        int ty = y;
        switch (pos) {
            case 1:
                tx = x - 1;
                ty = y - 1;
                break;
            case 2:
                ty = y - 1;
                break;
            case 3:
                tx = x + 1;
                ty = y - 1;
                break;
            case 4:
                tx = x - 1;

                break;
            case 6:
                tx = x + 1;
                break;
            case 7:
                tx = x - 1;
                ty = y + 1;
                break;
            case 8:
                ty = y + 1;
                break;
            case 9:
                tx = x + 1;
                ty = y + 1;
                break;
            default:
                break;
        }
        if(ty >= arr.length || ty < 0 || tx >= arr[0].length || tx < 0){
            return -1;
        }else {
            return arr[ty][tx];
        }
    }




    /*  POS:
     *   1 2 3
     *   4 5 6
     *   7 8 9
     * 5 is the default
     *
     */

    public void move(int pos) {
        switch (pos) {
            case 1:
                setPos(x - 1, y - 1);
                break;
            case 2:
                setPos(x, y - 1);
                break;
            case 3:
                setPos(x + 1, y - 1);
                break;
            case 4:
                setPos(x - 1, y);
                break;
            case 6:
                setPos(x + 1, y);
                break;
            case 7:
                setPos(x - 1, y + 1);
                break;
            case 8:
                setPos(x, y + 1);
                break;
            case 9:
                setPos(x + 1, y + 1);
                break;
            default:
                break;
        }

    }
}