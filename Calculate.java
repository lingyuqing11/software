import java.util.Map;

/**
 * Created by DELL on 2017/6/8.
 *
 * 包含计算的方法的类
 */
public class Calculate {

    private double pow(double n){
        return Math.pow(n,2);
    }
    private double sqrt(double n){
        return Math.sqrt(n);
    }

    /**计算两条边的交点**/
    public Point point_with_2edges(Edge edge1,Edge edge2){
        double A1=edge1.Get_A();
        double A2=edge2.Get_A();
        double B1=edge1.Get_B();
        double B2=edge2.Get_B();
        double C1=edge1.Get_C();
        double C2=edge2.Get_C();

        double x=0.0,y=0.0;
        if((C1*B2-B1*C2)!=0){
            x=(C1*B2-B1*C2)/(A2*B1-A1*B2);
        }
        else x=0;
        if((A1*C2-A2*C1)!=0){
            y=(A1*C2-A2*C1)/(A2*B1-A1*B2);
        }
        else y=0;

        return new Point(x,y);
    }

    /**计算两点间距离**/
    public double point_to_point(Point point1,Point point2){
        return Math.hypot((point1.Get_x()-point2.Get_x()),(point1.Get_y()-point2.Get_y()));
    }

    /**计算点到直线的距离**/
    public double point_to_edge(Point point,Edge edge){
        return Math.abs(edge.Get_A()*point.Get_x()+edge.Get_B()*point.Get_y()+edge.Get_C())/Math.hypot(edge.Get_A(),edge.Get_B());
    }

    /**第一类空隙：两边加一圆**/
    public Circle1 twoEdges_oneCircle(Edge edge1,Edge edge2,Circle1 circle){
        Point point1=point_with_2edges(edge1,edge2);
        Point point2=circle.Get_point();
        double r=(point_to_point(point1,point2)-circle.Get_r())/(1+sqrt(2));
        //double x=point1.Get_x()+(sqrt(2)*(point2.Get_x()-point1.Get_x())/(circle.Get_r()+(1+sqrt(2)*r))*r);
        //double y=point1.Get_y()+(sqrt(2)*(point2.Get_y()-point1.Get_y())/(circle.Get_r()+(1+sqrt(2)*r))*r);
        double x=point1.Get_x()+(r*(point2.Get_x()-point1.Get_x()))/circle.Get_r();
        double y=point1.Get_y()+(r*(point2.Get_y()-point1.Get_y()))/circle.Get_r();
        Point point=new Point(x,y);

        return new Circle1(point,r);
    }

    /**第二类空隙：两圆加一边**/
    public Circle1 twoCircles_oneEdge(Circle1 circle1,Circle1 circle2,Edge edge){
        double A=edge.Get_A();
        double B=edge.Get_B();
        double C=edge.Get_C();
        double r1=circle1.Get_r();
        double r2=circle2.Get_r();
        double x1=circle1.Get_point().Get_x();
        double x2=circle2.Get_point().Get_x();
        double y1=circle1.Get_point().Get_y();
        double y2=circle2.Get_point().Get_y();

        double r=pow((sqrt(r1*r2)/(sqrt(r1)+sqrt(r2))));
        double theta1=(pow(r1)-pow(r2)+2*r*(r1-r2)-pow(x1)+pow(x2)-pow(y1)+pow(y2))/2;
        double theta2=r*sqrt(pow(A)+pow(B))-C;
        if(C==0&&(B==-1||A==-1)){
            theta1= -theta2;
        }
        double y=(theta2*(x2-x1)-theta1*A)/(B*(x2-x1)-A*(y2-y1));
        double x=(theta1-y*(y2-y1))/(x2-x1);

        return new Circle1(new Point(x,y),r);
    }

    /**第三类空隙：三圆**/
    public Circle1 threeCircles(Circle1 circle1,Circle1 circle2,Circle1 circle3){
        double x1=circle1.Get_point().Get_x();
        double x2=circle2.Get_point().Get_x();
        double x3=circle3.Get_point().Get_x();
        double y1=circle1.Get_point().Get_y();
        double y2=circle2.Get_point().Get_y();
        double y3=circle3.Get_point().Get_y();
        double r1=circle1.Get_r();
        double r2=circle2.Get_r();
        double r3=circle3.Get_r();
        double k1=((r2-r3)/(x2-x3)-(r1-r2)/(x1-x2))/((y1-y2)/(x1-x2)-(y2-y3)/(x2-x3));
        double b1=((pow(x1)-pow(x2)+pow(y1)-pow(y2)-pow(r1)+pow(r2))/(2*(x1-x2))-(pow(x2)-pow(x3)+pow(y2)-pow(y3)-pow(r2)-pow(r3))/(2*(x2-x3)))/((y1-y2)/(x1-x2)-(y2-y3)/(x2-x3));
        double k2=(r3-r2)/(x2-x3)-k1*(y2-y3)/(x2-x3);
        double b2=(pow(x2)-pow(x3)+pow(y2)-pow(y3)-pow(r2)+pow(r3))/(2*(x2-x3))-b1*(y2-y3)/(x2-x3);

        double a=pow(k2)+pow(k1)-1;
        double b=2*k2*(b2-x1)+2*k1*(b1-y1)-2*r1;
        double c=pow(b2-x1)+pow(b1-y1)-pow(r1);

        double r=-c/b;
        double x=k2*r+b2;
        double y=k1*r+b1;

        return new Circle1(new Point(x,y),r);

    }





}
