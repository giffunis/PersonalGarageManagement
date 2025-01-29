package com.giffuniscode.pgm.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.toolbox.ImageLoader;
import com.giffuniscode.giffuniscode.pgm.R;
import com.giffuniscode.infraestructure.VolleyClient;
import com.giffuniscode.pgm.core.models.Vehicle;

import java.util.List;

public class RvVehiclesAdapter extends RecyclerView.Adapter<RvVehiclesAdapter.ViewHolder>{

    private final LayoutInflater inflater;
    private List<Vehicle> vehicles;
    protected View.OnClickListener onClickListener;

    public RvVehiclesAdapter(Context context, List<Vehicle> vehicles) {
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.vehicles = vehicles;
    }

    public void setOnItemClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public RvVehiclesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.simple_vehicle_item, parent, false);
        v.setOnClickListener(onClickListener);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RvVehiclesAdapter.ViewHolder holder, int position) {
        holder.licencePlate.setText(vehicles.get(position).getLicensePlate());
        VolleyClient.getInstance(this.inflater.getContext())
                .getImageLoader().get(
                        vehicles.get(position).getBrandLogoUrl()
                        , ImageLoader.getImageListener(holder.brandLogo, 0, 0)
                );
    }

    @Override
    public int getItemCount() {
        return vehicles.size();
    }

    public void updateRecycleView(List<Vehicle> newVehicles) {
        this.vehicles = newVehicles;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView licencePlate;
        public ImageView brandLogo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            licencePlate = itemView.findViewById(R.id.simple_vehicle_item_licensePlate);
            brandLogo = itemView.findViewById((R.id.simple_vehicle_item_image));
        }
    }
}
