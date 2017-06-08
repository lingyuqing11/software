import java.util.ArrayList;

/**
 * Created by DELL on 2017/6/8.
 */
public class Queue {
    private ArrayList<Surroundings> Q; //队列数组
    int front;  //队首
    int tail;   //队尾
    int len;    //队长

    public Queue(){
        this.Q=new ArrayList<Surroundings>();
        this.front=0;
        this.tail=0;
        this.len=0;
    }

    public int getLen(){
        return this.len;
    }

    /**将元素加入队尾**/
    public void append(Surroundings surrounding){
        this.Q.add(surrounding);
        this.tail++;
        this.len++;
    }
    /**将队首元素出队**/
    public Surroundings pop(){
        Surroundings s=this.Q.get(0);
        this.Q.remove(0);
        //this.front++;
        this.len--;
        return s;
    }
    /**获取下标为num的元素**/
    public double getR(int num){
        return this.Q.get(num).getNew_circle().Get_r();
    }
    /**在下标为i的位置**/
    public void insert(int i,Surroundings s){
        this.Q.add(i,s);
        this.len++;
    }
}
