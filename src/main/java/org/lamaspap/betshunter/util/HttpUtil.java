package org.lamaspap.betshunter.util;

import okhttp3.HttpUrl;
import org.lamaspap.betshunter.exception.UnwrapException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

public final class HttpUtil {
    private static final Logger LOG = LoggerFactory.getLogger(HttpUtil.class);

    private HttpUtil() {
    }

    public static <T> T executeCallAndUnwrapResponse(Call<T> call) {
        LOG.info("Executing REST call: {}", call.request().url());
        try {
            Response<T> response = call.execute();
            if (response.isSuccessful()) {
                return response.body();
            }

            HttpUrl url = response.raw().request().url();
            String message = String.format("REST call to %s, failed with code: %d and error: %s. Probably should check your VPN settings!",
                    url, response.code(), response.message());

            throw new UnwrapException(message, response.code());
        }
        catch (IOException e) {
            throw new UnwrapException(e);
        }
    }
}
