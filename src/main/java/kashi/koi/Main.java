package kashi.koi;

import kashi.koi.api.users.GetUserKudosuRequest;
import kashi.koi.model.users.KudosuHistory;

public class Main {
    public static void main(String[] args) {
        int userId = 2193881;

        OsuClient client = OsuClient.createDefault();

        GetUserKudosuRequest request = GetUserKudosuRequest.builder()
                .build();

        KudosuHistory response = client.users().getUserKudosu(userId, request);

        if (response != null) {
            System.out.println("Amount:" + response.amount());
        }
    }
}