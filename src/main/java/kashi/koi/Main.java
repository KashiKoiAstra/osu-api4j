package kashi.koi;

import kashi.koi.auth.AuthConfigLoader;
import kashi.koi.auth.OAuthTokenProvider;

public class Main {
    public static void main(String[] args) {

        OAuthTokenProvider provider = new OAuthTokenProvider(AuthConfigLoader.load());

        System.out.println(provider.getToken());
    }
}