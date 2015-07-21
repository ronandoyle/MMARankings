package ronandoyle.ie.mmarankings;

import java.net.URL;

/**
 * This class contains information on a fighter.
 *
 */
public class Fighter {
    private int id;
    private String nickname;
    private int wins;
    private int statid;
    private int losses;
    private String last_name;
    private String weight_class;
    private boolean title_holder;
    private int draws;
    private String first_name;
    private String fighter_status;
    private String rank;
    private String pound_for_pound_rank;
    private String thumbnail;
    private URL belt_thumbnail;
    private URL left_full_body_image;
    private URL right_full_body_image;
    private URL profile_image;
    private URL link;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        if (nickname != null && !nickname.equals("null")) {
            this.nickname = nickname;
        } else {
            this.nickname = null;
        }

    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getStatid() {
        return statid;
    }

    public void setStatid(int statid) {
        this.statid = statid;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public String getLastName() {
        return last_name;
    }

    public void setLastName(String last_name) {
        this.last_name = last_name;
    }

    public String getWeightClass() {
        return weight_class;
    }

    public void setWeightClass(String weight_class) {
        this.weight_class = weight_class;
    }

    public boolean isTitleHolder() {
        return title_holder;
    }

    public void setTitleHolder(boolean title_holder) {
        this.title_holder = title_holder;
    }

    public int getDraws() {
        return draws;
    }

    public void setDraws(int draws) {
        this.draws = draws;
    }

    public String getFirstName() {
        return first_name;
    }

    public void setFirstName(String first_name) {
        this.first_name = first_name;
    }

    public String getFighterStatus() {
        return fighter_status;
    }

    public void setFighterStatus(String fighter_status) {
        this.fighter_status = fighter_status;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getPoundForPoundRank() {
        return pound_for_pound_rank;
    }

    public void setPoundForPoundRank(String pound_for_pound_rank) {
        this.pound_for_pound_rank = pound_for_pound_rank;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public URL getBeltThumbnail() {
        return belt_thumbnail;
    }

    public void setBeltThumbnail(URL belt_thumbnail) {
        this.belt_thumbnail = belt_thumbnail;
    }

    public URL getLeftFullBodyImage() {
        return left_full_body_image;
    }

    public void setLeftFullBodyImage(URL left_full_body_image) {
        this.left_full_body_image = left_full_body_image;
    }

    public URL getRightFullBodyImage() {
        return right_full_body_image;
    }

    public void setRightFullBodyImage(URL right_full_body_image) {
        this.right_full_body_image = right_full_body_image;
    }

    public URL getProfileImage() {
        return profile_image;
    }

    public void setProfileImage(URL profile_image) {
        this.profile_image = profile_image;
    }

    public URL getLink() {
        return link;
    }

    public void setLink(URL link) {
        this.link = link;
    }
}
