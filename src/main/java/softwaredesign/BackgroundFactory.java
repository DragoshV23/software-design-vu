package softwaredesign;

public class BackgroundFactory {
    public Background getBackground(String name){
        switch (name) {
            case "Default":
                return new Background("Default", 0, "white.png");
            case "1996 Tamagotchi":
                return new Background("1996 Tamagotchi", 50, "ogbg.png");
            case "VU \"NU\" Building":
                return new Background("VU \"NU\" Building", 100, "VU.png");
            case "Gameboy":
                return new Background("Gameboy", 25, "gameboy.png");
            default:
                return null;
        }
    }
}
