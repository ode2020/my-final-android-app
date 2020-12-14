package com.example.visitrwanda;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HotAdapter extends RecyclerView.Adapter<HotAdapter.HotAdapterViewHolder>{
    private List<HotModel> hotModelList;
    private Context context;
    private SelectedHotel selectedHotel;

    public HotAdapter(List<HotModel> hotModelList, SelectedHotel selectedHotel) {
        this.hotModelList = hotModelList;
        this.selectedHotel = selectedHotel;
    }

    @NonNull
    @Override
    public HotAdapter.HotAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new HotAdapterViewHolder(LayoutInflater.from(context).inflate(R.layout.row_hotel, null));
    }

    @Override
    public void onBindViewHolder(@NonNull HotAdapter.HotAdapterViewHolder holder, int position) {
        HotModel hotModel = hotModelList.get(position);

        String hotName = hotModel.getHotelName();
        String prefix = hotModel.getHotelName().substring(0,1);

        holder.tvHotName.setText(hotName);
        holder.tvPrefix.setText(prefix);
    }

    @Override
    public int getItemCount() {
        return hotModelList.size();
    }

    public interface SelectedHotel{
        void selectedHotel(HotModel hotModel);
    }

    public class HotAdapterViewHolder extends RecyclerView.ViewHolder {
        TextView tvPrefix;
        TextView tvHotName;
        ImageView imIcon;

        public HotAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            tvHotName = itemView.findViewById(R.id.hotel_name);
            tvPrefix = itemView.findViewById(R.id.hotel_prefix);
            imIcon = itemView.findViewById(R.id.icon_next);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectedHotel.selectedHotel(hotModelList.get(getAdapterPosition()));
                }
            });

        }
    }
}
