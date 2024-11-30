import java.util.function.IntConsumer;

class ZeroEvenOdd {
    private int n;
    private int num;
    private int last;

    public ZeroEvenOdd(int n) {
        this.n = n;
        num = 0;
        last = 0;
    }

    public void zero(IntConsumer printNumber) throws InterruptedException {
        while (num <= n) {
            synchronized (this) {
                if (num == 0 || last != num) {
                    printNumber.accept(0);
                    if (num == 0)
                        num++;
                    last = num;
                } else
                    this.wait();
                this.notifyAll();
            }
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        while (num <= n) {
            synchronized (this) {
                if (num % 2 == 0 && num != 0 && num == last) {
                    printNumber.accept(num);
                    num++;
                } else
                    this.wait();
                this.notifyAll();
            }
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        while (num <= n) {
            synchronized (this) {
                if (num % 2 == 1 && num == last) {
                    printNumber.accept(num);
                    num++;
                } else
                    this.wait();
                this.notifyAll();
            }
        }
    }
}