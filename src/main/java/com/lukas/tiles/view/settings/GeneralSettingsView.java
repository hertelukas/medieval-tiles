package com.lukas.tiles.view.settings;

import com.lukas.tiles.Language;
import com.lukas.tiles.viewModel.SettingsViewModel;
import com.sun.javafx.collections.ImmutableObservableList;
import javafx.beans.property.StringProperty;
import javafx.scene.control.ChoiceBox;

public class GeneralSettingsView extends AbstractSettingsView {

    private ChoiceBox<Language> languageChoiceBox;

    public GeneralSettingsView(SettingsViewModel settingsViewModel, StringProperty title) {
        super(settingsViewModel, title);
    }

    @Override
    void initialize() {
        //Language Box
        languageChoiceBox = new ChoiceBox<>();
        languageChoiceBox.setItems(new ImmutableObservableList<>(Language.values()));
        languageChoiceBox.getSelectionModel().select(getSettingsViewModel().getConfig().getLanguage());
        languageChoiceBox.setOnAction(e -> getSettingsViewModel().setLanguage(languageChoiceBox.getSelectionModel().getSelectedItem()));
        this.getChildren().add(languageChoiceBox);
    }

}
