package ronandoyle.ie.ufcrankings;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * TODO Update this line
 *
 */
public class FighterCard extends RelativeLayout {

    private TextView mTextViewFirstName;
    private TextView mTextViewNickname;
    private TextView mTextViewLastName;
    private TextView mTextViewRank;
    private TextView mTextViewRecord;
    private ImageView mImageViewFighterThumbnail;

    private String mFirstName;
    private String mLastName;
    private String mNickame;
    private String mThumbnail;
    private String mRank;
    private String mRecord;

    public FighterCard(Context context, String firstName, String nickname, String lastName,
                       String thumbnail, String record) {
        super(context);
        setFirstName(firstName);
        setNickame(nickname);
        setLastName(lastName);
        setThumbnail(thumbnail);
        setRecord(record);
        initViews();
    }

    void initViews() {
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.view_fighter_card, this, true);

        mTextViewFirstName = (TextView) findViewById(R.id.tv_first_name);
        mTextViewNickname = (TextView) findViewById(R.id.tv_nickname);
        mTextViewLastName = (TextView) findViewById(R.id.tv_last_name);
        mTextViewRank = (TextView) findViewById(R.id.tv_rank);
        mTextViewRecord = (TextView) findViewById(R.id.tv_record);
        mImageViewFighterThumbnail = (ImageView) findViewById(R.id.fighter_thumbnail);

        updateFirstName();
        updateNickname();
        updateLastname();
        updateRecord();
        updateThumbnail();

    }

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String firstName) {
        this.mFirstName = firstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String lastName) {
        this.mLastName = lastName;
    }

    public String getNickame() {
        return mNickame;
    }

    public void setNickame(String nickame) {
        this.mNickame = nickame;
    }

    public String getThumbnail() {
        return mThumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.mThumbnail = thumbnail;
    }

    public String getRank() {
        return mRank;
    }

    public void setRank(String rank) {
        this.mRank = rank;
    }

    public String getRecord() {
        return mRecord;
    }

    public void setRecord(String record) {
        this.mRecord = record;
    }

    private void updateFirstName() {
        if (mTextViewFirstName == null) {
            return;
        }

        if (getFirstName() != null) {
            mTextViewFirstName.setText(getFirstName());
        }
    }

    private void updateNickname() {
        if (mTextViewNickname == null) {
            return;
        }

        if (getNickame() != null) {
            mTextViewNickname.setText("\" " + getNickame() + " \"");
        } else {
            mTextViewNickname.setVisibility(GONE);
        }
    }

    private void updateLastname() {
        if (mTextViewLastName == null) {
            return;
        }

        if (getLastName() != null) {
            mTextViewLastName.setText(getLastName());
        }
    }

    private void updateRank() {
        if (mTextViewRank == null) {
            return;
        }

        if (getRank() != null) {
            mTextViewRank.setText(getRank());
        }
    }

    private void updateRecord() {
        if (mTextViewRecord == null) {
            return;
        }

        if (getRecord() != null) {
            mTextViewRecord.setText(getRecord());
        }
    }

    private void updateThumbnail() {
        if (mImageViewFighterThumbnail == null) {
            return;
        }

        if (getThumbnail() != null) {
            Picasso.with(getContext())
                    .load(mThumbnail)
                    .into(mImageViewFighterThumbnail);
        }
    }
}
