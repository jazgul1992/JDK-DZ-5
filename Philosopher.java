class Philosopher implements Runnable {
    private final int id;
    private final Fork leftFork;
    private final Fork rightFork;
    private int mealsEaten = 0;

    public Philosopher(int id, Fork leftFork, Fork rightFork) {
        this.id = id;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
    }

    private void think() throws InterruptedException {
        System.out.println("Философ " + id + " размышляет.");
        Thread.sleep((long) (Math.random() * 1000));
    }

    private void eat() throws InterruptedException {
        System.out.println("Философ " + id + " ест.");
        Thread.sleep((long) (Math.random() * 1000));
        mealsEaten++;
        System.out.println("Философ " + id + " закончил есть (раз: " + mealsEaten + ").");
    }

    @Override
    public void run() {
        try {
            while (mealsEaten < 3) {
                think();

                // Попытка взять вилки
                if (id % 2 == 0) {
                    // Четный философ берет сначала левую вилку
                    leftFork.pickUp();
                    System.out.println("Философ " + id + " взял левую вилку.");
                    rightFork.pickUp();
                    System.out.println("Философ " + id + " взял правую вилку.");
                } else {
                    // Нечетный философ берет сначала правую вилку
                    rightFork.pickUp();
                    System.out.println("Философ " + id + " взял правую вилку.");
                    leftFork.pickUp();
                    System.out.println("Философ " + id + " взял левую вилку.");
                }

                eat();

                // Освобождение вилок
                leftFork.putDown();
                rightFork.putDown();
                System.out.println("Философ " + id + " положил вилки.");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

