import java.util.ArrayList;
import java.util.List;
/**
 * Created by DELL on 2017/6/8.
 */
public class Test {
    public static void main(String args[]){
        int N=10;
        List<Circle1> result=new ArrayList<Circle1>();
        Queue queue=new Queue();  //待填充的圆按半径从大到小排序的队列

        /**初始化第一个圆和正方形边界**/
        Circle1 circle0=new Circle1(new Point(0.5,0.5),0.5);
        Edge edge1=new Edge(new Point(0,0),new Point(1,0));
        Edge edge2=new Edge(new Point(0,0),new Point(0,1));
        Edge edge3=new Edge(new Point(0,1),new Point(1,1));
        Edge edge4=new Edge(new Point(1,1),new Point(1,0));

        Edge[] edges1=new Edge[3];
        Edge[] edges2=new Edge[3];
        Edge[] edges3=new Edge[3];
        Edge[] edges4=new Edge[3];
        edges1[0]=edge1;
        edges1[1]=edge2;
        edges2[0]=edge2;
        edges2[1]=edge3;
        edges3[0]=edge3;
        edges3[1]=edge4;
        edges4[0]=edge4;
        edges4[1]=edge1;

        Circle1[] circles=new Circle1[3];
        circles[0]=circle0;

        result.add(circle0);
        queue.append(new Surroundings(1,edges1,circles));
        queue.append(new Surroundings(1,edges2,circles));
        queue.append(new Surroundings(1,edges3,circles));
        queue.append(new Surroundings(1,edges4,circles));

        while(queue.getLen()>0&&(N>0||N==0)){   //插入新产生的空隙
            Surroundings s=queue.pop();
            Circle1 new_circle=s.getNew_circle();
            Surroundings new_list[]=s.new_surroundings();
            for(int i=0;i<2;i++){
                double r=new_list[i].getNew_circle().Get_r();

                for(int j=0;j<queue.getLen();j++){
                    if(r>=queue.getR(j)){
                        queue.insert(j,new_list[i]);
                        break;
                    }
                    if(j==queue.getLen()-1){
                        queue.append(new_list[i]);
                    }
                }

            }

            result.add(new_circle);
            N--;
        }

        for(int i=0;i<result.size();i++) {
            System.out.printf("第%d个圆，圆心坐标（%f ,%f),",i,result.get(i).Get_point().Get_x(),result.get(i).Get_point().Get_y());
            System.out.printf("半径%f\n",result.get(i).Get_r());
        }


    }
}
