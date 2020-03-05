import static java.lang.Thread.sleep;

public class Test {
    public static void main(String[] args) {
        CommonRessource commonRessource = new CommonRessource();

        for (int i = 1; i < 6; i++) {
            CounterThreadSync ct = new CounterThreadSync(commonRessource);
            Thread t = new Thread(ct);
            t.setName("Thread " + i);
            System.out.println(t.getName());
            t.start();
        }
    }
}

class CommonRessource {
    int x = 0;
}

class CounterThreadSync implements Runnable {

    CommonRessource res;

    public CounterThreadSync (CommonRessource res) {
        this.res=res;
    }

    @Override
    public void run() {
        System.out.println("*");
        //int y = res.x *2;
        synchronized (res) {
            for (int i = 0; i < 100 ; i++) {
                res.x++;
                System.out.println(Thread.currentThread().getName() +" " + res.x);
            }
            try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        }
    }
}

