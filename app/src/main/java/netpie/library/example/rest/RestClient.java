package netpie.library.example.rest;

//import WeatherService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import netpie.library.example.rest.service.NetPieService;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

public class RestClient
{
    private static final String BASE_URL = "https://api.netpie.io/";

    //private WeatherService apiService;
    private NetPieService apiService;

    public RestClient()
    {
        Gson gson = new GsonBuilder()
            .registerTypeAdapterFactory(new ItemTypeAdapterFactory())
            .create();

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(BASE_URL)
                .setConverter(new GsonConverter(gson))
                .build();

        //apiService = restAdapter.create(WeatherService.class);
        apiService = restAdapter.create(NetPieService.class);
    }

    public NetPieService getNetPieService() {
        return apiService;
    }


}
