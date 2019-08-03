package root;

import gpu.GameFrame;
import gpu.WellcomePanel;

public class Main {
    public static void main(String[] args) {
        GameFrame frame = new GameFrame();
        frame.setJpanel(new WellcomePanel());
    }
}
