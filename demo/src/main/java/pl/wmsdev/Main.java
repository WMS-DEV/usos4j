package pl.wmsdev;

import pl.wmsdev.usos4j.client.Usos;
import pl.wmsdev.usos4j.model.auth.UsosAccessToken;
import pl.wmsdev.usos4j.model.auth.UsosRequestToken;
import pl.wmsdev.usos4j.model.user.UsosUser;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Usos usos = Usos.builder()
                .baseUrl(System.getenv("usos_baseurl"))
                .consumerKey(System.getenv("usos_consumerKey"))
                .consumerSecret(System.getenv("usos_consumerSecret"))
                .build();
        UsosRequestToken requestToken = usos.getRequestToken();
        String authorizationUrl = usos.getAuthorizationUrl(requestToken);

        System.out.println("Please visit the following URL to authorize your application:");
        System.out.println(authorizationUrl);
        System.out.print("After authorization, please enter the verifier code: ");
        Scanner scanner = new Scanner(System.in);
        String verifier = scanner.nextLine();
        UsosAccessToken accessToken = usos.getAccessToken(requestToken, verifier);
        System.out.println("= Access token: " + accessToken.toFrameworkToken().getToken());
        System.out.println("= Access token secret: " + accessToken.toFrameworkToken().getTokenSecret());

        UsosUser user = usos.getUserApi(accessToken).users().user();
        System.out.println();
        System.out.println("Hello " + user.firstName() + " " + user.lastName());
        System.out.println("These are some information about you from Usos:");
        System.out.println(user);
    }

}