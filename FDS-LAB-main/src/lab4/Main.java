
public class Main {

    public static void reverseString(String s) {
        arrayStack<Character> st = new arrayStack<>(s.length());
        for (int i = 0; i < s.length(); i++) {
            st.push(s.charAt(i));
        }
        for (int i = 0; i < s.length(); i++) {
            System.out.print(st.pop());
        }
    }



    public static void main(String[] args) {
        reverseString("alphabet");
        System.out.println();
        roundRobin r = new roundRobin(5);
        r.addTask("T1",3);
        r.addTask("T2",2);
        r.addTask("T3",5);
        r.addTask("T4",6);
        r.addTask("T5",1);
        try {
            r.start();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}