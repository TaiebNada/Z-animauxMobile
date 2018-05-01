/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.entities;

import com.codename1.googlemaps.MapContainer;
import com.codename1.maps.Coord;
import com.codename1.maps.MapListener;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.geom.Point;
import com.codename1.ui.layouts.Layout;

/**
 *
 * @author ADMIN
 */
public class MapLayout extends Layout implements MapListener
     {
    private static final String COORD_KEY = "$coord";
    private MapContainer map;
    private Container actual;
    public MapLayout(MapContainer map, Container actual) {
        this.map = map;
        this.actual = actual;
        map.addMapListener(this);
    }

    @Override
    public void addLayoutComponent(Object value, Component comp, Container c) {
        comp.putClientProperty(COORD_KEY, (Coord)value);
    }

    @Override
    public boolean isConstraintTracking() {
        return true;
    }

    @Override
    public Object getComponentConstraint(Component comp) {
        return comp.getClientProperty(COORD_KEY);
    }

    @Override
    public boolean isOverlapSupported() {
        return true;
    }

    @Override
    public void layoutContainer(Container parent) {
        for(Component current : parent) {
            Coord crd = (Coord)current.getClientProperty(COORD_KEY);
            Point p = map.getScreenCoordinate(crd);
            current.setSize(current.getPreferredSize());
            current.setX(p.getX() - current.getWidth() / 2);
            current.setY(p.getY() - current.getHeight());
        }
    }

    @Override
    public Dimension getPreferredSize(Container parent) {
        return new Dimension(100, 100);
    }

    @Override
    public void mapPositionUpdated(Component source, int zoom, Coord center) {
        actual.setShouldCalcPreferredSize(true);
        actual.revalidate();
    }


}

