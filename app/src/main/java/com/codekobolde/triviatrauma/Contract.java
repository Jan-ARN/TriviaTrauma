package com.codekobolde.triviatrauma;

import android.content.Context;

public interface Contract {


    interface Presenter {

        void newUser(String Username, String Password, Context context);
        void authenticateUser(String Username, String Password, Context context);


    }


    interface Model {
        void newUser(String Username, String Password, Context context);
        void authenticateUser(String Username, String Password, Context context);

    }

    interface View {

        interface Login {
            void authenticateUser(String Username, String Password, Context context);
        }

        interface Register {
            void newUser(String Username, String Password, Context context);
        }





    }





}
