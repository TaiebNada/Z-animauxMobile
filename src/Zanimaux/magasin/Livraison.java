/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Zanimaux.magasin;

import com.codename1.components.MultiButton;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Form;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.GenericListCellRenderer;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Firqs
 */
public class Livraison extends BaseForm {

    public Livraison() {
        super("Newsfeed", BoxLayout.y());
//Form hi = new Form("ComboBox", new BoxLayout(BoxLayout.Y_AXIS));
        ComboBox<Map<String, Object>> combo = new ComboBox<>(
                createListEntry("A Game of Thrones", "1996"),
                createListEntry("A Clash Of Kings", "1998"),
                createListEntry("A Storm Of Swords", "2000"),
                createListEntry("A Feast For Crows", "2005"),
                createListEntry("A Dance With Dragons", "2011"),
                createListEntry("The Winds of Winter", "2016 (please, please, please)"),
                createListEntry("A Dream of Spring", "Ugh"));

        combo.setRenderer(new GenericListCellRenderer<>(new MultiButton(), new MultiButton()));
//        hi.show();
    }

    private Map<String, Object> createListEntry(String name, String date) {
        Map<String, Object> entry = new HashMap<>();
        entry.put("Line1", name);
        entry.put("Line2", date);
        return entry;
    }

}
