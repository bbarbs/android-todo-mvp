package com.tokagestudio.todo;


import android.app.Application;

import com.tokagestudio.todo.di.components.ApplicationComponent;
import com.tokagestudio.todo.di.components.DaggerApplicationComponent;
import com.tokagestudio.todo.di.modules.ApplicationModule;
import com.tokagestudio.todo.di.modules.DataModule;
import com.tokagestudio.todo.data.migration.DBMigration;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Android main application.
 * <p>
 * Created by bbarbero on 3/24/2018
 */

public class TodoApplication extends Application {

    /**
     * Realm db name.
     */
    private static final String DB_NAME = "todo.realm";

    /**
     * Singleton dagger component.
     */
    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initRealm();
        setupComponentInjector();
    }

    /**
     * Setup dagger application component.
     */
    public void setupComponentInjector() {
        this.applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .dataModule(new DataModule(this))
                .build();
    }

    /**
     * Realm instance initialization.
     */
    private void initRealm() {
        Realm.init(this);
        RealmConfiguration configuration;
        // Migration config.
        configuration = new RealmConfiguration.Builder()
                .schemaVersion(0)
                .name(DB_NAME)
                .migration(new DBMigration())
                .build();
        // Auto delete db.
        /*configuration = new RealmConfiguration.Builder()
                .name(DB_NAME)
                .deleteRealmIfMigrationNeeded()
                .build();*/
        Realm.setDefaultConfiguration(configuration);
    }

    /**
     * Expose the application component dependency.
     *
     * @return
     */
    public ApplicationComponent getApplicationComponent() {
        return this.applicationComponent;
    }
}
