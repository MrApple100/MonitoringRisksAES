package com.example.monitoringrisks;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;
import java.util.stream.Collectors;

@Entity(tableName = "AESeS")
public class AES {
    @PrimaryKey
    private int id;
    @ColumnInfo(name = "Photo")
    private String photo;
    @ColumnInfo(name = "Name")
    private String name;
    @ColumnInfo(name = "Description")
    private String discription;
    @ColumnInfo(name = "isFavorite")
    private boolean is_favorite;
    @ColumnInfo(name = "isComparable")
    private boolean is_comparable;
   // @TypeConverters(ConverterSList.class)
   // private List<Long> list_ids_diagram = new ArrayList<>();

    public AES(String Name,boolean is_favorite,boolean is_comparable) {
        this.name = Name;
        this.id = Name.hashCode();
        this.is_favorite=is_favorite;
        this.is_comparable=is_comparable;
    }

    public AES() {
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getId() {
        return id;
    }

    public String getPhoto() {
        return photo;
    }

    public String getName() {
        return name;
    }

    public String getDiscription() {
        return discription;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
/*
    public void setList_ids_diagram(List<Long> list_ids_diagram) {

        this.list_ids_diagram = list_ids_diagram;
    }

    public List<Long> getList_ids_diagram() {
        return list_ids_diagram;
    }


 */

    public boolean getIs_favorite() {
        return is_favorite;
    }

    public void setIs_favorite(boolean is_favorite) {
        this.is_favorite = is_favorite;
    }

    public boolean getIs_comparable() {
        return is_comparable;
    }

    public void setIs_comparable(boolean is_comparable) {
        this.is_comparable = is_comparable;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
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

    @Override
    public String toString() {
        return "AES{" +
                "id=" + id +
                ", Photo='" + photo + '\'' +
                ", Name='" + name + '\'' +
                ", Discription='" + discription + '\'' +
                ", is_favorite=" + is_favorite +
                ", is_comparable=" + is_comparable +
                '}';
    }
}

