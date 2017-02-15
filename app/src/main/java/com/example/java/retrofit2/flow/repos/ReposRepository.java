package com.example.java.retrofit2.flow.repos;

import android.content.Context;

import com.example.java.retrofit2.model.Repo;

import java.util.List;

import rx.Single;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;

/**
 * Created by java on 13.02.2017.
 */

public class ReposRepository implements ReposDataSource {

    ReposLocalDataSource mLocalSource = new ReposLocalDataSource();
    ReposRemouteDataSource mRemoteSource = new ReposRemouteDataSource();

    @Override
    public Single<List<Repo>> getRepos(String user) {
        return mRemoteSource.getRepos(user)
                .flatMap(new Func1<List<Repo>, Single<? extends List<Repo>>>() {
                    @Override
                    public Single<? extends List<Repo>> call(List<Repo> repos) {
                        return mRemoteSource.getRepos(user);
                    }
                })
                .onErrorResumeNext(mLocalSource.getRepos(user))
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public void clearRepos() {

    }

    @Override
    public void init(Context context) {
        mLocalSource.init(context);
        mRemoteSource.init(context);
    }
}
