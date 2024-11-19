import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Fork {
    private final Lock lock = new ReentrantLock();

    public void pickUp() {
        lock.lock(); // Захват вилки
    }

    public void putDown() {
        lock.unlock(); // Освобождение вилки
    }
}
