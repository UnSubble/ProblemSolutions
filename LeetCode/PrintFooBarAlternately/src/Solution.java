class FooBar {
    private int n;
    private int s;

    public FooBar(int n) {
        this.n = n;
        this.s = 0;
    }

    public void foo(Runnable printFoo) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            synchronized(this) {
                if (s % 2 == 1)
                    this.wait();
        	// printFoo.run() outputs "foo". Do not change or remove this line.
        	printFoo.run();
            s++;
            this.notify();
            }
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        
        for (int i = 0; i < n; i++) {
            synchronized(this) {
                if (s % 2 == 0)
                    this.wait();
            // printBar.run() outputs "bar". Do not change or remove this line.
        	printBar.run();
            s++;
            this.notify();
            }
        }
    }
}