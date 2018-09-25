package com.apredazzi.randomapplication;

public interface BaseContract {

    interface View<T extends Presenter> {
        void setPresenter(T presenter);
    }

    interface Presenter {
        void start();
        void stop();
    }

}
