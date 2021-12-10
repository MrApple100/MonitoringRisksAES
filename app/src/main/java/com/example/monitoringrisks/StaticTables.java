package com.example.monitoringrisks;

import com.example.monitoringrisks.Fragments.DaoFavoriteAES;

public class StaticTables {
    private static StaticTables instance;
    private  TableAES tableAES =(TableAES) TableAES.getInstance(MainActivity.getInstance(),"AES10").allowMainThreadQueries().build();
    public DaoAES daoAES = tableAES.DaoAES();

    public DaoFavoriteAES daoFavoriteAES = tableAES.DaoFavoriteAES();

    private  TableDiagram tableDiagram =(TableDiagram) TableDiagram.getInstance(MainActivity.getInstance(),"Diagram5").allowMainThreadQueries().build();
    public DaoDiagram daoDiagram = tableDiagram.DaoDiagram();

    private  TableUser tableUser =(TableUser) TableUser.getInstance(MainActivity.getInstance(),"User1").allowMainThreadQueries().build();
    public DaoUser daoUser = tableUser.DaoUser();

    public static StaticTables getInstance() {
        if(instance==null){
            instance = new StaticTables();
        }
        return instance;
    }

    private StaticTables() {
    }


}
