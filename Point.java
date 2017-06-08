/**
 * Created by DELL on 2017/6/7.
 *
 * 表示一个点的类，x,y为横纵坐标
 */
public class Point {
    private double x;
    private double y;

    public Point(double x,double y){
        this.x=x;
        this.y=y;
    }

    public double Get_x(){
        return this.x;
    }

    public double Get_y(){
        return this.y;
    }
}
