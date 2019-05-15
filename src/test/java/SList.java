public class SList<T> {

    public static void main(String[] args) {
//        HashMap
        SList<String> sList = new SList<String>();
        SList.SLinkedIterator iterator = sList.iterator();
        for (String s : "a b c d e f".split(" ")) {
            iterator.add(s);
        }
        System.out.println(sList);
        String p;
        while (((p = (String) iterator.remove()) != null)) {

        }
        iterator.add("bbb");


    }
    SLinkedIterator it = new SLinkedIterator();

    public SLinkedIterator iterator(){
        return it;
    }
    public class SLinkedIterator<T>{
        @Override
        public String toString() {
            return "SLinkedIterator{" +
                    "item=" + item +
                    ", next=" + next +
                    '}';
        }

        T item;
        SLinkedIterator next;
        public SLinkedIterator(){
            item = null;
            next = null;
        }
        public SLinkedIterator(T item,SLinkedIterator next){
            this.next = next;
            this.item = item;
        }
        boolean end(){
            return item ==null && next==null;
        }
        boolean add(T t){
            it = new SLinkedIterator<>(t, it);
            return  true;
        }

        T remove(){
            T t = (T) it.item;
            if (!it.end()) {
                it = it.next;
            }
            return  t;
        }
    }

    @Override
    public String toString() {
        return "SList{" +
                "it=" + it +
                '}';
    }
}
