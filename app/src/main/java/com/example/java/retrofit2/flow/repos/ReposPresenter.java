package com.example.java.retrofit2.flow.repos;

import android.content.Context;

import com.example.java.retrofit2.model.Repo;

import java.util.List;

import rx.Single;
import rx.Subscription;
import rx.functions.Action1;
import rx.internal.util.SubscriptionList;

/**
 * Created by java on 13.02.2017.
 */

public class ReposPresenter implements ReposDataSource {

    private ReposRepository mReposRepository = new ReposRepository();
    private ReposView mView;
    private SubscriptionList mSubscriptionList = new SubscriptionList();

    public void onAttach(ReposView view) {
        mView = view;
        mReposRepository.init(mView.getContext());
    }

    public void onDettach() {
        mView = null;
        if (mSubscriptionList != null) {
            mSubscriptionList.unsubscribe();
        } ;
    }


   /* @Override
    public Single<List<Repo>> getRepos(String user) {
        return mReposRepository.getRepos(user);
    }
    */

    @Override
    public Single<List<Repo>> getRepos(String user) {
        Single<List<Repo>> single = mReposRepository.getRepos(user);
        Subscription subscription = single.subscribe(
                new Action1<List<Repo>>() {
                    @Override
                    public void call(List<Repo> list) {
                        mView.showRepos(list);
                    }
                },
                Throwable::printStackTrace);
        mSubscriptionList.add(subscription);
        return single;
    }

    @Override
    public void clearRepos() {
        mReposRepository.clearRepos();
    }

    @Override
    public void init(Context context) {

    }
}
