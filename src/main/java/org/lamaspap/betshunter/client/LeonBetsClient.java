package org.lamaspap.betshunter.client;

import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.lamaspap.betshunter.model.leonsbet.Event;
import org.lamaspap.betshunter.model.leonsbet.EventsResponse;
import org.lamaspap.betshunter.model.leonsbet.Sport;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.util.List;

@Produces(value = MediaType.APPLICATION_JSON)
public interface LeonBetsClient {

    @GET("api-2/betline/sports")
    Call<List<Sport>> getAllSports(@Query("ctag") String ctag,
                                   @Query("flags") String flags);

    @GET("api-2/betline/events/all")
    Call<EventsResponse> getAllEventsForLeague(@Query("league_id") String leagueId,
                                               @Query("ctag") String ctag,
                                               @Query("vtag") String vtag,
                                               @Query("hideClosed") boolean hideClosed,
                                               @Query("flags") String flags);

    @GET("api-2/betline/event/all")
    Call<Event> geFullEventData(@Query("eventId") String eventId,
                                @Query("ctag") String ctag,
                                @Query("vtag") String vtag,
                                @Query("flags") String flags);

}
