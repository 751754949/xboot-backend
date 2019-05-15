import org.junit.Test;

import java.util.*;

import static java.util.Arrays.asList;


public class StackTest {
    Map ma;
    @Test
    public void stackTest(){
        Stack<String> stack = new Stack<>();
        List list = new ArrayList();
        Iterator iterator = list.iterator();
        String[] strs = ("+u+n+c---+" +
                "e+r+t").split("");
        for (int i = 0; i < strs.length; i++) {
            if ("+".equals(strs[i])){
                stack.push(strs[i+1]);
            }else if ("-".equals(strs[i])){
                String pop = stack.pop();
                System.out.println(pop);
            }
        }
        System.out.println(stack);
    }

    @Test
    public void itrTest(){
        ReverseItertorArrayList<String> strings = new ReverseItertorArrayList<>(asList( "a", "c", "d"));
        Iterator<String> iterator = strings.reverse();
        Iterator<String> order = strings.iterator();
        while (order.hasNext()) System.out.println(order.next());
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}

class ReverseItertorArrayList<T> extends ArrayList<T>{
    public ReverseItertorArrayList(Collection<T> c){
        super(c);
    }
    public Iterator<T> reverse() {
        return new Iterator<T>() {
            int current = size()-1;
            @Override
            public boolean hasNext() {
                return current > -1;
            }
            public T previous(){
                return this.next();
            }
            @Override
            public T next() {
                return get(current--);
            }
        };
    }
}