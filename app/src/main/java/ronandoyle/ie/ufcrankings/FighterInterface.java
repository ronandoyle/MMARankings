package ronandoyle.ie.ufcrankings;

import java.net.URI;

import retrofit.http.GET;

/**
 * TODO Update this line
 *
 */
public interface FighterInterface {

    String URL_FIGHTERS = "/api/v3/iphone/fighters/title_holders";


    @GET(URL_FIGHTERS)
    Fighter[] getFighters();
}
