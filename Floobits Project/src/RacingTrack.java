
public class RacingTrack {

    private Thread[] runners;
    private int trackLength;
    private int numfinished;

    public RacingTrack(int numRunners, int trackLength) {
        this.runners = new Thread[numRunners];
        this.trackLength = trackLength;

        for (int i = 0; i < numRunners; i++) {
            Runner run = new Runner(this, i, trackLength);
            Thread thread1 = new Thread(run);
            runners[i] = thread1;
        }

        numfinished = 0;
    }

    public void go() {
        for (int i = 0; i < runners.length; i++) {
            runners[i].start();
        }
    }

    public int runDistance(Runner runner) {
        return 5;
    }

    public synchronized void finishRace(Runner runner) {
        System.out.println(runner.getRunnerId() + " finished the race!");
    }

}