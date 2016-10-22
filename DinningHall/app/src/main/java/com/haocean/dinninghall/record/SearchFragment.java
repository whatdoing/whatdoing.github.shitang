package com.haocean.dinninghall.record;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.haocean.dinninghall.R;


/**
 * Created by haocean on 2016/7/7.
 */
public class SearchFragment extends Fragment implements View.OnClickListener{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_title_search, container, false);//初始化fragment
//        LinearLayout back= (LinearLayout) view.findViewById(R.id.back);
//        ImageView search= (ImageView) view.findViewById(R.id.search);
//        ImageView function= (ImageView) view.findViewById(R.id.function);
//        search.setOnClickListener(this);
//        back.setOnClickListener(this);
//        function.setOnClickListener(this);
        return view;
    }
    private int replaceFragment(Fragment fragment, String stackName) {
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragment_title, fragment);
        transaction.addToBackStack(stackName);
        return transaction.commit();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
//        switch (id) {
//            case R.id.back: {
//                getActivity().finish();
//            }
//             break;
//        }

    }
}
