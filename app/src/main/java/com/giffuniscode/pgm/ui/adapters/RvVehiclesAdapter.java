package com.giffuniscode.pgm.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.giffuniscode.pgm.core.models.Vehicle;

import java.util.List;

public class RvVehiclesAdapter extends RecyclerView.Adapter<RvVehiclesAdapter.ViewHolder>{

    private final LayoutInflater inflater;
    private List<Vehicle> vehicles;

    public RvVehiclesAdapter(Context context, List<Vehicle> vehicles) {
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.vehicles = vehicles;
    }

    @NonNull
    @Override
    public RvVehiclesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RvVehiclesAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
