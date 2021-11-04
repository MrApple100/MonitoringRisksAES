package com.example.monitoringrisks;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;
import java.util.stream.Collectors;

@Entity(tableName = "AESS")
public class AES {
    @PrimaryKey
    private int id;
    @ColumnInfo(name = "Photo")
    private String Photo;
    @ColumnInfo(name = "Name")
    private String Name;
    @ColumnInfo(name = "Description")
    private String Discription;
   // @TypeConverters(ConverterSList.class)
   // private List<Long> list_ids_diagram = new ArrayList<>();

    public AES(String Name) {
        this.Name = Name;
        this.id = Name.hashCode();
    }

    public void setPhoto(String photo) {
        this.Photo = photo;
    }

    public int getId() {
        return id;
    }

    public String getPhoto() {
        return Photo;
    }

    public String getName() {
        return Name;
    }

    public String getDiscription() {
        return Discription;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String Name) {
        this.Name = Name;
    }
/*
    public void setList_ids_diagram(List<Long> list_ids_diagram) {

        this.list_ids_diagram = list_ids_diagram;
    }

    public List<Long> getList_ids_diagram() {
        return list_ids_diagram;
    }


 */
    public void setDiscription(String discription) {
        this.Discription = discription;
        StaticTables.getInstance().daoAES.update(this);
    }

    public void setList_diagram(List<Diagram> list_diagram) {

        list_diagram = list_diagram.stream().map(diagram -> {diagram.setIdAES(id); return diagram;}).collect(Collectors.toList());
        System.out.println(list_diagram.get(0).getIdAES());
        StaticTables.getInstance().daoDiagram.deleteByidAES(Integer.valueOf(id));
        StaticTables.getInstance().daoDiagram.add(list_diagram);
        System.out.println(StaticTables.getInstance().daoDiagram.getdiagramsbyidAES(id).size());

    }

    public List<Diagram> getList_diagram() {
        return StaticTables.getInstance().daoDiagram.getdiagramsbyidAES(id);
    }


}
