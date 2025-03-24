package com.routine.fragments;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.database.*;
import com.google.firebase.database.annotations.NotNull;
import com.routine.R;
import com.routine.RoutineAdapter;
import com.routine.RoutineModel;

import java.util.ArrayList;

public class MonFragment extends Fragment {

    private RoutineAdapter adapter;
    private ArrayList<RoutineModel> subjectList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mon, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.subListView);

        subjectList = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        adapter = new RoutineAdapter(subjectList);
        recyclerView.setAdapter(adapter);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference()
                .child("routine").child("cst").child("sem6").child("day").child("mon");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                subjectList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    RoutineModel rm = dataSnapshot.getValue(RoutineModel.class);
                    if (rm != null) {
                        subjectList.add(rm);
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

        return view;
    }
}