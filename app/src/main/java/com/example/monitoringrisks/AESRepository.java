package com.example.monitoringrisks;

import androidx.lifecycle.LiveData;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class AESRepository {
    private static AESRepository instance;
    private AESNetwork aesnetwork = new AESNetwork();
    private DaoAES daoAES = StaticTables.getInstance().daoAES;
    private List<AES> data;
    private AESRepository() {
        data = daoAES.findAll();
    }
    public static AESRepository getInstance(){
        if(instance==null){
            instance =new AESRepository();
        }
        return instance;
    }
    public void refresh(){
        Subscription subscription = Observable.create(new Observable.OnSubscribe<List<AES>>() {
            @Override
            public void call(Subscriber<? super List<AES>> subscriber) {
                subscriber.onNext(aesnetwork.getAll());
                subscriber.onCompleted();
            }
        }).onErrorResumeNext(new Func1<Throwable, Observable<? extends List<AES>>>() {
            @Override
            public Observable<? extends List<AES>> call(Throwable throwable) {

                return Observable.just(daoAES.findAll());
            }
        }).doOnNext(new Action1<List<AES>>() {
            @Override
            public void call(List<AES> aes) {
                daoAES.add(aes);
            }
        }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<AES>>() {
                    @Override
                    public void call(List<AES> aes) {

                    }
                });
    }

    public LiveData<AES> getAES(int hcName) {
        return daoAES.getbyName(hcName);
    }

    public List<AES> getData() {
        return data;
    }

    public void setData(List<AES> data) {
        this.data = data;
    }
}
