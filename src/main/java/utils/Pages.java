package utils;

import pages.LoginPage;

public class Pages {


    private final LoginPage LOGIN_PAGE;


    public Pages() {
        this.LOGIN_PAGE = new LoginPage();
    }

    public LoginPage loginPage() {

        return LOGIN_PAGE;
    }


}

