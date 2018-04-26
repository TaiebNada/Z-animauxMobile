/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Zanimaux.SOS;

import com.codename1.ui.Form;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.list.MultiList;
import com.esprit.services.AnimalService;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author ADMIN
 */
public class DetailAdoption {
     static int id;
    Form f;

    public DetailAdoption() {

        f = new Form("Detail Animal", new BorderLayout());
        // lb = new SpanLabel("");
//        f.add(lb);
        AnimalService serviceTask = new AnimalService();
        ArrayList<Map<String, Object>> lis = serviceTask.getList2();
        // lb.setText(lis.toString());
        DefaultListModel<Map<String, Object>> model = new DefaultListModel<>(lis);
        MultiList ml = new MultiList(model);
        f.add(BorderLayout.CENTER,ml);
        f.getToolbar().addCommandToRightBar("back", null, (ev) -> {


        });
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        DetailAdoption.id = id;
    }

    
}
