package tiles.text;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import tiles.Config;
import tiles.Language;

public class MenuText implements LanguageObserver {

    public MenuText() {
        Config.getInstance().subscribe(this);
    }

    private static class MenuTextHolder {
        private static final MenuText INSTANCE = new MenuText();
    }

    public static MenuText getInstance() {
        return MenuTextHolder.INSTANCE;
    }

    @Override
    public void update(Language language) {
        System.out.println("Updating...");
        switch (language) {
            case ENGLISH -> {
                setPlay("Play");
                setLoad("Load");
                setSettings("Settings");
                setExit("Exit");
            }
            case GERMAN -> {
                setPlay("Spielen");
                setLoad("Laden");
                setSettings("Einstellungen");
                setExit("Beenden");
            }
        }
    }

    private final StringProperty play = new SimpleStringProperty("Play");
    private final StringProperty load = new SimpleStringProperty("Load");
    private final StringProperty settings = new SimpleStringProperty("Settings");
    private final StringProperty exit = new SimpleStringProperty("Exit");

    public StringProperty playProperty() {
        return play;
    }

    public String getPlay() {
        return play.get();
    }

    private void setPlay(String play) {
        this.play.set(play);
    }

    public StringProperty loadProperty() {
        return load;
    }

    public String getLoad() {
        return load.get();
    }

    private void setLoad(String load) {
        this.load.set(load);
    }

    public StringProperty settingsProperty() {
        return settings;
    }

    public String getSettings() {
        return settings.get();
    }

    private void setSettings(String settings) {
        this.settings.set(settings);
    }

    public StringProperty exitProperty() {
        return exit;
    }

    public String getExit() {
        return exit.get();
    }

    private void setExit(String exit) {
        this.exit.set(exit);
    }
}
