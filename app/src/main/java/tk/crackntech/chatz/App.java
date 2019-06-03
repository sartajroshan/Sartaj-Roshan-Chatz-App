package tk.crackntech.chatz;

import android.app.Application;

import com.treebo.internetavailabilitychecker.InternetAvailabilityChecker;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        InternetAvailabilityChecker.init(this);
    }
}
