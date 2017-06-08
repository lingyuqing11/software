/**
 * Created by DELL on 2017/6/7.
 *
 * 表示待填充空隙的类
 */
public class Surroundings extends Calculate {
    private int type;
    private Edge edges[];
    private Circle1 circles[];
    private Circle1 new_circle;

    public Surroundings(int type,Edge edges[],Circle1 circles[]){
        this.type=type;
        this.edges=edges;
        this.circles=circles;
        this.new_circle=this.new_Circle();
    }

    public Circle1 getNew_circle(){
        return this.new_circle;
    }

    /**计算空隙中填充的圆**/
    private Circle1 new_Circle(){
        if(this.type==1){
            return super.twoEdges_oneCircle(this.edges[0],this.edges[1],this.circles[0]);
        }
        else if(this.type==2){
            return super.twoCircles_oneEdge(this.circles[0],this.circles[1],this.edges[0]);
        }
        else{
            return super.threeCircles(this.circles[0],this.circles[1],this.circles[2]);
        }

    }
    /**计算新产生的空隙**/
    public Surroundings[] new_surroundings(){
        Edge[] edges1=new Edge[3];
        Edge[] edges2=new Edge[3];
        Edge[] edges3=new Edge[3];
        Circle1[] circles1=new Circle1[3];
        Circle1[] circles2=new Circle1[3];
        Circle1[] circles3=new Circle1[3];
        if(this.type==1){
            edges1[0]=this.edges[0];
            edges1[1]=this.edges[1];
            circles1[0]=this.new_circle;

            edges2[0]=this.edges[0];
            circles2[0]=this.circles[0];
            circles2[1]=this.new_circle;

            edges3[0]=this.edges[1];
            circles3[0]=this.circles[0];
            circles3[1]=this.new_circle;

            Surroundings s[]={new Surroundings(1,edges1,circles1),new Surroundings(2,edges3,circles3),new Surroundings(2,edges3,circles3) };
            return  s;
        }
        else if(this.type==2){
            edges1[0]=this.edges[0];
            circles1[0]=this.circles[0];
            circles1[1]=this.new_circle;

            edges2[0]=this.edges[0];
            circles2[0]=this.circles[1];
            circles2[1]=this.new_circle;

            circles3[0]=this.circles[0];
            circles3[1]=this.circles[1];
            circles3[2]=this.new_circle;

            Surroundings s[]={new Surroundings(2,edges1,circles1),new Surroundings(2,edges3,circles3),new Surroundings(3,edges3,circles3) };
            return  s;
        }
        else{
            circles1[0]=this.new_circle;
            circles1[1]=this.circles[1];
            circles1[2]=this.circles[2];

            circles2[0]=this.circles[0];
            circles2[1]=this.new_circle;
            circles2[2]=this.circles[2];

            circles3[0]=this.circles[0];
            circles3[1]=this.circles[1];
            circles3[2]=this.new_circle;

            Surroundings s[]={new Surroundings(3,edges1,circles1),new Surroundings(3,edges3,circles3),new Surroundings(3,edges3,circles3) };
            return  s;
        }

    }
}
