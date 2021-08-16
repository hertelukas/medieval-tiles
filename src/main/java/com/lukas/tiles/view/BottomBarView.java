package com.lukas.tiles.view;

import com.lukas.tiles.viewModel.BottomBarViewModel;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class BottomBarView extends HBox {

    private final BottomBarViewModel bottomBarViewModel;

    public BottomBarView() {
        bottomBarViewModel = new BottomBarViewModel();
        setupBindings();
    }

    private void setupBindings() {
        Label cashLabel = new Label();
        cashLabel.textProperty().bind(bottomBarViewModel.moneyPropertyProperty());
        this.getChildren().add(cashLabel);
    }
}
