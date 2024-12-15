package Sokoban.Controller;

import javafx.application.Platform;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.util.Objects;

public class AudioManager {

    // 存储所有音效的实例
    private final AudioClip moveSound;
    private final AudioClip pushBox;
    private final AudioClip win;
    private final AudioClip lose;
    private static final MediaPlayer backgroundPeace;
    private static final MediaPlayer backgroundFG;


    static {//静态初始化块
        Media media1 = new Media(Objects.requireNonNull(AudioManager.class.getResource("/Sokoban/Music/TownTheme.mp3")).toExternalForm());
        backgroundPeace = new MediaPlayer(media1);
        Media media2 = new Media(Objects.requireNonNull(AudioManager.class.getResource("/Sokoban/Music/The History.mp3")).toExternalForm());
        backgroundFG = new MediaPlayer(media2);
        backgroundPeace.setCycleCount(MediaPlayer.INDEFINITE);
        backgroundFG.setCycleCount(MediaPlayer.INDEFINITE);
    }

    public AudioManager() {
        // 短音效（使用 AudioClip）
        moveSound = new AudioClip(Objects.requireNonNull(getClass().getResource("/Sokoban/Music/step.mp3")).toExternalForm());
        pushBox = new AudioClip(Objects.requireNonNull(getClass().getResource("/Sokoban/Music/scrape.MP3")).toExternalForm());
        win = new AudioClip(Objects.requireNonNull(getClass().getResource("/Sokoban/Music/win.wav")).toExternalForm());
        lose = new AudioClip(Objects.requireNonNull(getClass().getResource("/Sokoban/Music/lose.wav")).toExternalForm());
        // 长音效（使用 MediaPlayer）


    }

    public void playWin() {
        Platform.runLater(win::play);
    }

    public void playLose() {
        Platform.runLater(lose::play);
    }

    public void playmoveSound() {
        Platform.runLater(moveSound::play);
    }

    public void playpushBox() {
        Platform.runLater(pushBox::play);

    }


    public static void playbackgroundPeace() {
        Platform.runLater(() -> {
            backgroundPeace.play();
        });
    }


    public static void playbackgroundFG() {
        Platform.runLater(() -> {
            backgroundFG.play();
        });
    }


    public static void stop() {
        Platform.runLater(() -> {
            backgroundPeace.stop();
            backgroundFG.stop();
        });

    }
}
