/*  * To change this license header, choose License Headers in Project Properties.  * To change this template file, choose Tools | Templates  * and open the template in the editor.  */
package com.pi.socialpro.controllers;

import Entity.Publication;
import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.CategorySeries;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.charts.renderers.SimpleSeriesRenderer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.charts.views.PieChart;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 *
 * @author Navoxx
 */
public class Piechart1 extends Component {
    
    
    Form f = new Form("Statistique", new BorderLayout());
    double[] x = {};
    ArrayList<double[]> db_results = new ArrayList<double[]>(); 

    private DefaultRenderer buildCategoryRenderer(int[] colors) {
    DefaultRenderer renderer = new DefaultRenderer();
    renderer.setLabelsTextSize(15);
    renderer.setLegendTextSize(15);
    renderer.setMargins(new int[]{20, 30, 15, 0});
    for (int color : colors) {
        SimpleSeriesRenderer r = new SimpleSeriesRenderer();
        r.setColor(color);
        renderer.addSeriesRenderer(r);
    }
    return renderer;
}

/**
 * Builds a category series using the provided values.
 *
 * @param titles the series titles
 * @param values the values
 * @return the category series
 */
protected CategorySeries buildCategoryDataset(String title, double[] values) {
    CategorySeries series = new CategorySeries(title);
    int k = 0;
    for (double value : values) {
        series.add("user " + ++k, value);
    }

    return series;
}


    

        

        
    
public Form createPieChartForm() {
    ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/php/selectPub.php");
                NetworkManager.getInstance().addToQueue(con);
con.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            for (Publication t : getListEtudiant(new String(con.getResponseData())))
                 {
                     for (int i = 0; i < 20; i++){
                         
                     double d = (double)t.getJaime();
                         System.out.println("tttttttttt"+d);
                         double val = Double.parseDouble(String.valueOf(t.getJaime()));
                       //  x[i]=val;
                         

                     
}
                 }
               }
    });
    // Generate the values
    double[] values = new double[]{14, 11};

    // Set up the renderer
    int[] colors = new int[]{ColorUtil.BLUE, ColorUtil.GREEN};
    DefaultRenderer renderer = buildCategoryRenderer(colors);
    renderer.setZoomButtonsVisible(true);
    renderer.setZoomEnabled(true);
    renderer.setChartTitleTextSize(20);
    renderer.setDisplayValues(true);
    renderer.setShowLabels(true);
    SimpleSeriesRenderer r = renderer.getSeriesRendererAt(0);
    r.setGradientEnabled(true);
    r.setGradientStart(0, ColorUtil.BLUE);
    r.setGradientStop(0, ColorUtil.GREEN);
    r.setHighlighted(true);

    // Create the chart ... pass the values and renderer to the chart object.
    PieChart chart;
    
                 
        chart = new PieChart(buildCategoryDataset("Statistique",values ), renderer);
         
                 
    // Wrap the chart in a Component so we can add it to a form
   

    // Create a form and show it.
    

                 
    ChartComponent c = new ChartComponent(chart);

    f.add(BorderLayout.CENTER, c);
    
   Style s =UIManager.getInstance().getComponentStyle("Title");
   
Button back =new Button();
    back.getAllStyles().setAlignment(Component.RIGHT);
    FontImage backIcon= FontImage.createMaterial(FontImage.MATERIAL_ARROW_BACK,s);
    
    f.getToolbar().addCommandToLeftBar("",backIcon,(e)->{new AffichagePublication().getAll();});
     
  return f; 
}




 public ArrayList<Publication> getListEtudiant(String json) {
        ArrayList<Publication> listEtudiants = new ArrayList<>();

        try {

            JSONParser j = new JSONParser();

            Map<String, Object> etudiants = j.parseJSON(new CharArrayReader(json.toCharArray()));

            System.out.println();
            List<Map<String, Object>> list = (List<Map<String, Object>>) etudiants.get("publication");
                            listEtudiants.clear();

            for (Map<String, Object> obj : list) {
                Publication e = new Publication();
                e.setId(Integer.parseInt(obj.get("id").toString()));
                e.setText(obj.get("text").toString());
                e.setPath(obj.get("path").toString());
                
                e.setUsername(obj.get("username").toString());
                e.setJaime(Integer.parseInt(obj.get("jaime").toString()));
                 e.setJaimePas(Integer.parseInt(obj.get("jaimePas").toString()));
                
                
                listEtudiants.add(e);

            }
            

        } catch (IOException ex) {
         }
        return listEtudiants;
    }
    
}


