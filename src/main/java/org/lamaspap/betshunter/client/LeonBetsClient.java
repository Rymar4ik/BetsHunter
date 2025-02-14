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

/**
 * This interface defines the client for interacting with the LeonBets API.
 * It provides methods to retrieve sports, events for leagues, and detailed event data.
 * The responses are wrapped in {@link Call} objects to support asynchronous execution.
 */
@Produces(value = MediaType.APPLICATION_JSON)
public interface LeonBetsClient {

    /**
     * Retrieves all sports available from the API.
     *
     * @param ctag  the country tag specifying the region/language context
     * @param flags additional flags to customize the response
     * @return a {@link Call} containing a list of {@link Sport} objects
     */
    @GET("api-2/betline/sports")
    Call<List<Sport>> getAllSports(@Query("ctag") String ctag,
                                   @Query("flags") String flags);

    /**
     * Retrieves all events for a specific league.
     *
     * @param leagueId   the ID of the league to retrieve events for
     * @param ctag       the country tag specifying the region/language context
     * @param vtag       the version tag for API consistency
     * @param hideClosed whether to hide closed events
     * @param flags      additional flags to customize the response
     * @return a {@link Call} containing an {@link EventsResponse} object with the events list
     */
    @GET("api-2/betline/events/all")
    Call<EventsResponse> getAllEventsForLeague(@Query("league_id") String leagueId,
                                               @Query("ctag") String ctag,
                                               @Query("vtag") String vtag,
                                               @Query("hideClosed") boolean hideClosed,
                                               @Query("flags") String flags);

    /**
     * Retrieves detailed data for a specific event.
     *
     * @param eventId the ID of the event to retrieve
     * @param ctag    the country tag specifying the region/language context
     * @param vtag    the version tag for API consistency
     * @param flags   additional flags to customize the response
     * @return a {@link Call} containing a detailed {@link Event} object
     */
    @GET("api-2/betline/event/all")
    Call<Event> geFullEventData(@Query("eventId") String eventId,
                                @Query("ctag") String ctag,
                                @Query("vtag") String vtag,
                                @Query("flags") String flags);

}
