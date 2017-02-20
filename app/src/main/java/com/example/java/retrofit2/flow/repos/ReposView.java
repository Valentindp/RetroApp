package com.example.java.retrofit2.flow.repos;

import android.content.Context;

import com.example.java.retrofit2.model.Repo;

import java.util.List;

/**
 * Created by java on 13.02.2017.
 */

public interface ReposView {

    Context getContext();

    void showRepos(List<Repo> list);
}
