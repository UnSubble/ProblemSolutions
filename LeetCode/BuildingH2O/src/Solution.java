class H2O {
    private int n;

    public H2O() {
        n = 0;
    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        boolean loop = true;
        while (loop) {
            synchronized (this) {
                if (n == 0) {
                    this.wait();
                    continue;
                }
                // releaseOxygen.run() outputs "O". Do not change or remove this line.
                releaseHydrogen.run();
                n--;
                loop = false;
                this.notifyAll();
            }
        }
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        boolean loop = true;
        while (loop) {
            synchronized (this) {
                if (n > 0) {
                    this.wait();
                    continue;
                }
                // releaseOxygen.run() outputs "O". Do not change or remove this line.
                releaseOxygen.run();
                n = 2;
                loop = false;
                this.notifyAll();
            }
        }
    }
}