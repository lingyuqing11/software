/**
 * Created by DELL on 2017/6/7.
 *
 *  表示圆的类，point为圆心，r为半径
 */
public class Circle1 {
    private Point point;
    private double r;

    public Circle1(Point point,double r){
        this.point=point;
        this.r=r;
    }

    public Point Get_point(){
        return this.point;
    }

    public double Get_r(){
        return this.r;
    }
}
