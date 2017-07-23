package JAVASETEST;


import org.junit.Test;

/**
 * Created by Administrator on 2017/6/1.
 */
public class Label {
        @Test
        public void a(){
            retray:
            for(int i=0;i<10;i++){
                System.out.println("==i=="+i);
                for(int j=0;j<20;j++){
                    System.out.println("==j=="+j);
                    if(j==3){
                        continue retray;
                    }
                    if(i==3){
                        break retray;
                    }
                }
            }
        }

}
