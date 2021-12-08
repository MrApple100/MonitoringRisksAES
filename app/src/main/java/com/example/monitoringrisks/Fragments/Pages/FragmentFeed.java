package com.example.monitoringrisks.Fragments.Pages;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.monitoringrisks.AES;
import com.example.monitoringrisks.AESRepository;
import com.example.monitoringrisks.Diagram;
import com.example.monitoringrisks.Fragments.FragmentManyAES;
import com.example.monitoringrisks.R;
import com.example.monitoringrisks.StaticTables;
import com.example.monitoringrisks.TypeDiagram;
import com.example.monitoringrisks.viewmodel.AESViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class FragmentFeed extends Fragment {
    private static FragmentFeed instance;
    private static FragmentManyAES activefragmentManyAES;

    public static FragmentFeed getInstance() {
        if(instance==null){
            instance = new FragmentFeed();
        }else{
            FragmentManager fm = instance.getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            if (!activefragmentManyAES.isAdded()) {
                ft.add(R.id.FLfeed, activefragmentManyAES);
            } else {
                ft.show(activefragmentManyAES);
            }
            ft.commit();
        }
        return instance;
    }
    private FragmentFeed(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feed_view,container,false);

        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.d("ATTACH","ATTACH");
        HashMap<String,Float> data = new HashMap<>();
        for(int i=0;i<6;i++){
            data.put((i+1)+"",i+1f);
        }
        Diagram diagramP = new Diagram("Pie", TypeDiagram.Pie,data,1);
        Diagram diagramR = new Diagram("Radar", TypeDiagram.Radar,data,2);
        Diagram diagramB = new Diagram("Bar", TypeDiagram.Bar,data,3);
        List<Diagram> diagramHashMap = new ArrayList<>();
        diagramHashMap.add(diagramP);
        diagramHashMap.add(diagramR);
        diagramHashMap.add(diagramB);
        System.out.println("dhm"+diagramHashMap);
        AES aes = new AES("AES3",true,true);
        aes.setDiscription("хорошая АЭС");
        aes.setList_diagram(diagramHashMap);

        new StaticTables().daoAES.insert(aes);
        System.out.println("KOK "+new StaticTables().daoAES.findAll().get(0));
        System.out.println("KOK "+new StaticTables().daoAES.findAll().get(0));
        AESRepository.getInstance().refresh();

        Subscription subscription = Observable.create(new Observable.OnSubscribe<List<AES>>() {
            @Override
            public void call(Subscriber<? super List<AES>> subscriber) {
                subscriber.onNext(AESRepository.getInstance().getData());
                subscriber.onCompleted();
            }
        }).onErrorResumeNext(new Func1<Throwable, Observable<? extends List<AES>>>() {
            @Override
            public Observable<? extends List<AES>> call(Throwable throwable) {
                System.out.println("ERROR");
                return Observable.just(new ArrayList<>());
            }
        }).map( new Func1<List<AES>,List<AESViewModel>>() {
            @Override
            public List<AESViewModel> call(List<AES> aess) {
               return aess.stream().map(i -> {
                    AESViewModel aesViewModel = new AESViewModel();
                    aesViewModel.aesLiveData.set(i);
                    return aesViewModel;
                }).collect(Collectors.toList());
            }
        }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe( new Action1<List<AESViewModel>>() {
                    @Override
                    public void call(List<AESViewModel> aesViewModels) {
                        System.out.println("OK");
                        FragmentManyAES fragmentManyAES = new FragmentManyAES(aesViewModels);
                        FragmentManager fm = getFragmentManager();
                        FragmentTransaction ft= fm.beginTransaction();
                        ft.replace(R.id.FLfeed,fragmentManyAES);
                        ft.commit();
                        activefragmentManyAES =fragmentManyAES;
                    }
                });





    }
}
