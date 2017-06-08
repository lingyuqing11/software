/**
 * Created by DELL on 2017/6/7.
 *
 * 表示一条边界的类
 */
public class Edge {
    private double A;
    private double B;
    private double C;

    public Edge(Point point1,Point point2){
        this.A=point2.Get_y()-point1.Get_y(); //y2-y1
        this.B=point1.Get_x()-point2.Get_x(); //x1-x2
        this.C=point2.Get_x()*point1.Get_y()-point1.Get_x()*point2.Get_y(); //x2*y1-x1*y2
    }

    public double Get_A(){
        return this.A;
    }

    public double Get_B(){
        return this.B;
    }

    public double Get_C(){
        return this.C;
    }
}
