package test.listview.demo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by bulent.turkmen on 8/5/2016.
 */
public class CustomListAdapter extends BaseAdapter {
    private ArrayList<Item> itemList;
    private LayoutInflater inflater;
    private OnGoClickListener listener;

    public CustomListAdapter(Context context, ArrayList<Item> itemList, OnGoClickListener listener) {
        this.itemList = itemList;
        this.inflater = LayoutInflater.from(context);
        this.listener = listener;

    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public Item getItem(int i) {
        return itemList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return getItem(i).hashCode();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_layout_item, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();

        Item item = getItem(position);
        holder.txtName.setText(item.getName());
        holder.txtCount.setText(String.format("Count: %d", item.getCount()));
        holder.bind(getItem(position), listener);

        return convertView;
    }

    static class ViewHolder {
        private TextView txtCount;
        private TextView txtName;
        private Button btnGo;

        public ViewHolder(View view) {
            txtName = (TextView) view.findViewById(R.id.txt_name);
            txtCount = (TextView) view.findViewById(R.id.txt_count);
            btnGo = (Button) view.findViewById(R.id.btn_go);
        }

        public void bind(final Item item, final OnGoClickListener listener) {
            btnGo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }

    public interface OnGoClickListener {
        void onItemClick(Item item);
    }
}
