package com.lukas.tiles.viewModel;

import com.lukas.tiles.model.Tile;
import com.lukas.tiles.model.WorldMap;
import com.lukas.tiles.view.MapViewModelObserver;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;

import java.util.ArrayList;
import java.util.List;

public class MapViewModel implements MapObserver {
    private final WorldMap map;
    private final List<MapViewModelObserver> mapViewModelObservers;

    private double zoom = 1;
    private final static double MAX_ZOOM = 2;
    private final static double MIN_ZOOM = 0.5;
    private final static double SENSITIVITY = 240; //Higher is slower

    private final Tile[][] tiles;
    private final int width;
    private final int height;
    private final Hexagon[] hexagons;

    private double lastX;
    private double lastY;

    private double xOffset = 0;
    private double yOffset = 0;

    public MapViewModel(WorldMap map) {
        this.map = map;
        map.subscribe(this);


        mapViewModelObservers = new ArrayList<>();
        hexagons = new Hexagon[map.getSize()];

        width = map.getWidth();
        height = map.getHeight();
        tiles = map.getTiles();
        update();
    }


    public Hexagon[] getHexagons() {
        return hexagons;
    }

    public Tile[][] getTiles() {
        return tiles;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void subscribe(MapViewModelObserver observer) {
        mapViewModelObservers.add(observer);
    }

    public void handleScroll(ScrollEvent scrollEvent) {
        zoom += scrollEvent.getDeltaY() / SENSITIVITY;
        zoom = Math.max(MIN_ZOOM, Math.min(MAX_ZOOM, zoom));
        recalculate();
        updateMapView();
    }

    public void handleKey(KeyEvent keyEvent) {
        System.out.println("Pressed key");
    }

    public void handleDragged(MouseEvent mouseEvent, double width, double height) {

        double horizontalFactor = Math.sqrt(3) * zoom * Hexagon.getRelativeScale();
        double verticalFactor = 1.5 * zoom * Hexagon.getRelativeScale();
        yOffset = Math.max(-1 * (map.getWidth() + 1) * horizontalFactor + width, Math.min(0.5 * horizontalFactor, yOffset + mouseEvent.getSceneX() - lastY));
        xOffset = Math.max(-1 * (map.getHeight() + 1) * verticalFactor + height, Math.min(0.5 * verticalFactor, xOffset + mouseEvent.getSceneY() - lastX));

        lastY = mouseEvent.getSceneX();
        lastX = mouseEvent.getSceneY();

        recalculate();
        updateMapView();
    }

    public void mouseDown(MouseEvent mouseEvent) {
        lastY = mouseEvent.getSceneX();
        lastX = mouseEvent.getSceneY();
    }

    private void updateMapView() {
        for (MapViewModelObserver mapViewModelObserver : mapViewModelObservers) {
            mapViewModelObserver.update();
        }
    }


    @Override
    public void update() {
        recalculate();
        updateMapView();
    }

    private void recalculate() {
        for (int i = 0; i < hexagons.length; i++) {
            hexagons[i] = new Hexagon(i / width, i % width, zoom, xOffset, yOffset);
        }
    }


}
