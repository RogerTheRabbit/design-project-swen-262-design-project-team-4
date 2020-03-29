public class Runner implements Runnable{
    private RacingTrack racingTrack;
    private int id;
    private int distanceRun;
    private int trackLength;

    public Runner(RacingTrack racingTrack, int id, int trackLength) {
        this.racingTrack = racingTrack;
        this.trackLength = trackLength;
        this.id = id;
    }


    public int getDistanceRun() {
        return distanceRun;
    }

    public int getRunnerId() {
        return id;
    }

    @Override
    public void run() {
        while(distanceRun < trackLength) {
            distanceRun += this.racingTrack.runDistance(this);
        }
        this.racingTrack.finishRace(this);
    }
}

