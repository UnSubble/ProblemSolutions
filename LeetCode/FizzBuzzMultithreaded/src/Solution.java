class FizzBuzz {
    private int n;
    private int num;

    public FizzBuzz(int n) {
        this.n = n;
        num = 1;
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        while (num <= n) {
            synchronized (this) {
                if (num % 3 != 0 || num % 5 == 0) {
                    this.wait();
                    continue;
                }
                printFizz.run();
                num++;
                this.notifyAll();
            }
        }
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        while (num <= n) {
            synchronized (this) {
                if (num % 5 != 0 || num % 3 == 0) {
                    this.wait();
                    continue;
                }
                printBuzz.run();
                num++;
                this.notifyAll();
            }
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        while (num <= n) {
            synchronized (this) {
                if (num % 5 != 0 || num % 3 != 0) {
                    this.wait();
                    continue;
                }
                printFizzBuzz.run();
                num++;
                this.notifyAll();
            }
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        while (num <= n) {
            synchronized (this) {
                if (num % 5 == 0 || num % 3 == 0) {
                    this.wait();
                    continue;
                }
                printNumber.accept(num);
                num++;
                this.notifyAll();
            }
        }
    }
}