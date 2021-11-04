package com.example.monitoringrisks;

import android.content.Context;
import android.graphics.Color;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.monitoringrisks.Converter.ConverterEnumTypeDiagram;
import com.example.monitoringrisks.Converter.ConverterHashmapStringFloat;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.ChartData;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.stream.Collectors;
@Entity(tableName = "diagrams")
public class Diagram {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "idAES")
    private int idAES;
    @ColumnInfo(name = "STARTCOLOR")
    private int STARTCOLOR = 100;
    @ColumnInfo(name = "name")
    private String name;
    @TypeConverters(ConverterEnumTypeDiagram.class)
    @ColumnInfo(name = "Type")
    private TypeDiagram Type;
    @TypeConverters(ConverterHashmapStringFloat.class)
    @ColumnInfo(name = "inputdata")
    private HashMap<String, Float> inputdata;
    @Ignore
    private Chart chart;
    @ColumnInfo(name = "num")
    private Integer num;
    @Ignore
    public Diagram(String name, HashMap<String, Float> inputdata,Integer num) {
        this.name = name;
        this.inputdata = inputdata;
        this.num = num;
    }

    public Diagram(String name,TypeDiagram Type, HashMap<String, Float> inputdata, Integer num) {
        this.name = name;
        this.Type = Type;
        this.inputdata = inputdata;
        this.num = num;
    }
    public Chart createChart(TypeDiagram type){
        this.Type = type;
        return createChart();
    }
    public Chart createChart(){
        if(Type!=null)
            chart = innerCreateChart(Type);
        else
            chart = innerCreateChart(TypeDiagram.Bar);
        return chart;
    }

    public Integer getNum() {
        return num;
    }

    public int getSTARTCOLOR() {
        return STARTCOLOR;
    }

    /**
    startcolor - 0..255
     **/
    public boolean setstartcolor(int startcolor){
        if(startcolor>255 || startcolor<0){
            return false;
        }
        STARTCOLOR = startcolor;
        return true;
    }

    public int getId() {
        return id;
    }

    public int getIdAES() {
        return idAES;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdAES(int idAES) {
        this.idAES = idAES;
    }

    public void setSTARTCOLOR(int STARTCOLOR) {
        this.STARTCOLOR = STARTCOLOR;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TypeDiagram getType() {
        return Type;
    }

    public void setType(TypeDiagram type) {
        Type = type;
    }

    public HashMap<String, Float> getInputdata() {
        return inputdata;
    }

    public void setInputdata(HashMap<String, Float> inputdata) {
        this.inputdata = inputdata;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    private Chart innerCreateChart(TypeDiagram type) {
        ArrayList<Float> arrayCollection = new ArrayList(inputdata.values());
        ArrayList<String> labels = new ArrayList<>(inputdata.keySet());

        Float max = arrayCollection.stream().max(Float::compareTo).get();

        int[] colors = getColorsbySize(arrayCollection,max);

        Chart chart =null ;
        ChartData data=null;
        switch (type){
            case Pie:
                chart = new PieChart(getContext());
                ArrayList<PieEntry> pieEntries =(ArrayList<PieEntry>) arrayCollection.stream().map(i ->new PieEntry(Float.parseFloat(i+""),labels)).collect(Collectors.toList());
                PieDataSet pieDataSet = new PieDataSet(pieEntries,"");
                pieDataSet.setColors(ColorTemplate.createColors(colors));

                data = new PieData(pieDataSet);

                break;
            case Radar:
                chart = new RadarChart(getContext());
                ArrayList<RadarEntry> radarEntries =(ArrayList<RadarEntry>) arrayCollection.stream().map(i ->new RadarEntry(Float.parseFloat(i+""),labels)).collect(Collectors.toList());
                RadarDataSet radardataSet = new RadarDataSet(radarEntries,"");
                radardataSet.setColors(Color.RED);
                radardataSet.setFillColor(Color.RED);

                radardataSet.setDrawFilled(true);


                data = new RadarData(radardataSet);
                break;
            case Bar:
                chart = new BarChart(getContext());
                ArrayList<BarEntry> barEntries =(ArrayList<BarEntry>) arrayCollection.stream().map(i ->new BarEntry(arrayCollection.indexOf(i)+1,Float.parseFloat(i+""),labels)).collect(Collectors.toList());
                BarDataSet bardataSet = new BarDataSet(barEntries,"");
                bardataSet.setColors(ColorTemplate.createColors(colors));

                data = new BarData(bardataSet);
                break;
        }
        Description description = new Description();
        description.setText(name);
        chart.setDescription(description);
        chart.setData(data);

        this.chart = chart;
        return chart;
    }
    private Context getContext() {
        return MainActivity.getInstance();
    }

    private int[] getColorsbySize(ArrayList<Float> arrayCollection,Float max) {
        int[] colors = new int[arrayCollection.size()];

        for(int i=0;i<arrayCollection.size();i++) {
            Random random = new Random();
            random.setSeed(13);
            int randomplus = (int)Math.floor(random.nextInt()/100);
            System.out.println("MAx"+max);
            float d = (float)((1-arrayCollection.get(i)/(float)max)*(255-STARTCOLOR+randomplus));
            int plus = (int)Math.floor(d);
            System.out.println(arrayCollection.get(i)+"/"+d+"/"+ plus);



            colors[i] = Color.rgb(STARTCOLOR-randomplus+plus,STARTCOLOR-randomplus+plus,STARTCOLOR-randomplus+plus);
        }
        return colors;
    }
}
