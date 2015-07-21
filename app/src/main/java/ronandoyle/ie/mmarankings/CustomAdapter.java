package ronandoyle.ie.mmarankings;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * TODO Update this line
 *
 */
public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder>{


    private List<Fighter> mFighterList;
    private Context mContext;

    public CustomAdapter(Context context, List<Fighter> fighterList) {
        mContext = context;
        mFighterList = fighterList;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.view_fighter_card, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Fighter fighter = mFighterList.get(position);

        if (fighter.getThumbnail() != null) {
            holder.thumbnail.setVisibility(View.VISIBLE);

            Picasso.with(mContext).load(fighter.getThumbnail()).into(holder.thumbnail);
        }

        holder.firstName.setText(fighter.getFirstName());


        if (fighter.getNickname() != null) {
            holder.nickName.setText("\"" + fighter.getNickname() + "\"");
            holder.nickName.setVisibility(View.VISIBLE);
        } else {
            holder.nickName.setText(fighter.getLastName());
            holder.lastName.setVisibility(View.INVISIBLE);
        }

        holder.weightclass.setText(fighter.getWeightClass());
    }

    @Override
    public int getItemCount() {
        return mFighterList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout cardRelativeLayout;
        ImageView thumbnail;
        TextView firstName;
        TextView nickName;
        TextView lastName;
        TextView weightclass;

        public ViewHolder(View v) {
            super(v);
            cardRelativeLayout = (RelativeLayout) v.findViewById(R.id.relative_layout_fighter_card);
            thumbnail = (ImageView) v.findViewById(R.id.fighter_thumbnail);
            firstName = (TextView) v.findViewById(R.id.tv_first_name);
            nickName = (TextView) v.findViewById(R.id.tv_nickname);
            lastName = (TextView) v.findViewById(R.id.tv_last_name);
            weightclass = (TextView) v.findViewById(R.id.tv_weightclass);
        }
    }
}
