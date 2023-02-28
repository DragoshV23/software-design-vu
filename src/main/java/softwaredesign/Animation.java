package softwaredesign;

import javafx.util.Duration;

enum State { IDLE, SLEEP, ANGRY }

public class Animation {
    private Race race;
    private State state;
    private String fileName;
    private int framesPerRow;
    private Duration channelDuration;
    private int startFrame;
    private int endFrame;

    public Animation(Race petRace, State animState, String name, int frames, Duration dur) {
        race = petRace;
        state = animState;
        fileName = name;
        framesPerRow = frames;
        channelDuration = dur;
        startFrame = 0;
        endFrame = frames - 1;
    }

    public Race getRace() {return race; }
    public State getState() {return state; }
    public String getFileName() {return fileName; }
    public int getFramesPerRow() {return framesPerRow; }
    public Duration getChannelDuration() {return  channelDuration; }
    public int getStartFrame() {return  startFrame; }
    public int getEndFrame() {return endFrame; }

}
