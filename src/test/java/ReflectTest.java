import org.junit.Test;

import java.lang.reflect.*;
import java.util.HashMap;
import java.util.Map;

public class ReflectTest {
    public static void main(String[] args) {
        Class c = Toy.class;
        System.out.println("class name"+c.getName());
        System.out.println("simple name"+c.getSimpleName());
        Object o= null;
        try {
            Constructor[] constructors = c.getConstructors();
            for (Constructor constructor : constructors) {
               o= constructor.newInstance("小宝");
            }
//            o = c.newInstance();
            System.out.println("class name"+o.getClass().getName());
            System.out.println("simple name"+o.getClass().getSimpleName());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDynamicProxy(){
        iToy toy = (iToy) Proxy.newProxyInstance(iToy.class.getClassLoader(), new Class[]{iToy.class}, new MyHandler(new Toy("aaa")));
        toy.jump();        toy.jump();
        toy.jump();
toy.smile();
    }
}

class MyHandler implements InvocationHandler{
    private Object o;
    public MyHandler(Object o){
        this.o=o;
    }
    public static Map<String,Integer> countMap = new HashMap<>();
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (countMap.get(method.getName())==null) {
            countMap.put(method.getName(),1);
        }else {
            countMap.put(method.getName(),countMap.get(method.getName())+1);
        }
        System.out.println(args);
        return method.invoke(o, args);
    }
}
interface iToy{
    void jump();
    void smile();

}
class Toy implements  iToy{
    String name;
    public Toy(){}
    public Toy(String name){
        super();
        this.name= name;
    }

    @Override
    public void jump() {
        System.out.println(this.name+"jump");
    }

    @Override
    public void smile() {
        System.out.println(this.name+"smile");

    }
}