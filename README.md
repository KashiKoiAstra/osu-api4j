# osu-api4j

osu! web api v2 wrapper for Java, now WIP

### How to use

First, setup your client id and client secret. There're various ways, select one:
```java
        // load from environment variables "OSU_CLIENT_ID" and "OSU_CLIENT_SECRET"
        OsuClient client = OsuClient.createDefault();   

        // builder
        OsuClient client = OsuClient.builder()
            .clientId("12345")
            .clientSecret("...")
            .build();   

        // custom TokenStore (e.g. database/redis)
        OsuClient client = OsuClient.builder()
            .clientId("12345")
            .clientSecret("...")
            .tokenStore(myDatabaseTokenStore)
            .build();
```

It will fetch access token, and save it to ~/.osu-api4j/token.json by default, and automatically refresh token when expired
you can also implement your own TokenStore (database/redis)

Then, you can call api methods. For example:
```java
        // use QueryMap to build query parameters
        Score[] scores = client.users().getUserScores(userId, "best", new QueryMap().put("limit", "20"));      

        if (scores != null) {
            for (Score score : scores) {
                System.out.printf(
                        "PP: %.2f | Accuracy: %.2f | %s (%s) - %s [%s]  \n",
                        score.pp(),
                        score.accuracy() * 100,
                        score.beatmapset().source(),
                        score.beatmapset().artist(),
                        score.beatmapset().title(),
                        score.beatmap().version());
                // this prints your b20
            }
        }
```