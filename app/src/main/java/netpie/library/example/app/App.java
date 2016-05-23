package netpie.library.example.app;

import android.app.Application;

import netpie.library.example.rest.RestClient;

public class App extends Application {

    private static RestClient restClient;

    @Override
    public void onCreate() {
        super.onCreate();
        restClient = new RestClient();
    }

    public static RestClient getRestClient() {
        return restClient;
    }
}
