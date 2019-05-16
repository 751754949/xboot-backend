import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Description:
 * Author: ${Author}
 * Date: 2019-04-19
 * Time: 13:54
 */
public class ListTest {

    @Test
    public void foreachTest(){
        List<String> list = new ArrayList<String>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");

        for (String s : list) {
            if ("3".equals(s)) {
                list.remove(s);
            }
        }
//        System.out.println(list);
    }

    @Test
    public void iteratorTest(){
        List<String> list = new ArrayList<String>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        List<String> test2 = test2(list);
        System.out.println(list == test2);
        Iterator<String> it = list.iterator();
        while (it.hasNext()){
            String s = it.next();
            if ("3".equals(s)){
                it.remove();
            }
        }
        System.out.println(list);
    }

    private List<String> test2(List<String> list) {
//        list.add("100");
        return list;
    }

}
