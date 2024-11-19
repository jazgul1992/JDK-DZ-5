public class DiningPhilosophers {
    public static void main(String[] args) {
        int numberOfPhilosophers = 5;
        Fork[] forks = new Fork[numberOfPhilosophers];
        Philosopher[] philosophers = new Philosopher[numberOfPhilosophers];

        // Инициализация вилок
        for (int i = 0; i < numberOfPhilosophers; i++) {
            forks[i] = new Fork();
        }

        // Инициализация философов
        for (int i = 0; i < numberOfPhilosophers; i++) {
            Fork leftFork = forks[i];
            Fork rightFork = forks[(i + 1) % numberOfPhilosophers];
            philosophers[i] = new Philosopher(i, leftFork, rightFork);
        }

        // Запуск потоков для философов
        Thread[] threads = new Thread[numberOfPhilosophers];
        for (int i = 0; i < numberOfPhilosophers; i++) {
            threads[i] = new Thread(philosophers[i]);
            threads[i].start();
        }

        // Ожидание завершения
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("Все философы поели 3 раза.");
    }
}