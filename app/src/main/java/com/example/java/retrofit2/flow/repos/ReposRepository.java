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
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(list -> mLocalSource.saveRepos(list))
                .onErrorResumeNext(error -> mLocalSource.getRepos(user));
    }

    @Override
    public void clearRepos() {
        mLocalSource.clearRepos();
    }

    @Override
    public void init(Context context) {
        mLocalSource.init(context);
        mRemoteSource.init(context);
    }
}
