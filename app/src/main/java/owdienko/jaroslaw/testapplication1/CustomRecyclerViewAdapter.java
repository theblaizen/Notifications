package owdienko.jaroslaw.testapplication1;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Jaroslaw Owdienko on 4/11/2017. All rights reserved TestApplication1!
 */

public class CustomRecyclerViewAdapter extends RecyclerView.Adapter<CustomRecyclerViewAdapter.ViewHolder> {

    private Context context;
    private List<String> content;

    public CustomRecyclerViewAdapter(Context context, List<String> content) {
        this.context = context;
        this.content = content;
    }

    private Context getContext() {
        return this.context;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView title;

        public ViewHolder(Context context, View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.custom_row_textView);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        final View custom_view = inflater.inflate(R.layout.custom_row, parent, false);
        final CustomRecyclerViewAdapter.ViewHolder holder = new CustomRecyclerViewAdapter.ViewHolder(getContext(), custom_view);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final TextView titleView = holder.title;

        if (!(getItemCount() < 1)) {
            titleView.setText(content.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return content.size();
    }

}
