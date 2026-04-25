# How to create a new endpoint

For example, we create a Scores API (GET /scores/{score}/{mode?}):

### Step 1：Create a data model (if need)

As we know, the osu! web api will return an HTTP form,  so we need a compatible data structure.

```.java
  // model/scores/ScoreInfo.java
  package kashi.koi.model.scores;

  import com.fasterxml.jackson.annotation.JsonProperty; // if need
  import java.time.OffsetDateTime;

  public record ScoreInfo(
          Long id,
          Integer userId,
          Float accuracy,
          @JsonProperty("created_at") OffsetDateTime createdAt  // if need
  ) {}
```

### Step 2：Create an API entry point

According to the API type, put it in the appropriate class, for easier unified management.

```.java
  // api/scores/ScoresApi.java
  package kashi.koi.api.scores;

  import kashi.koi.http.ApiHttpClient;
  import kashi.koi.http.QueryMap;
  import kashi.koi.model.scores.ScoreInfo;
  import java.util.Map;

  public class ScoresApi {
      private final ApiHttpClient httpClient;

      public ScoresApi(ApiHttpClient httpClient) {
          this.httpClient = httpClient;
      }

      public ScoreInfo getScore(Integer scoreId, String mode, QueryMap query) {
          if (scoreId == null) throw new IllegalArgumentException("scoreId must not be null");
          String m = mode != null && !mode.isBlank() ? mode.trim() : "";
          return httpClient.get("/scores/{score}/{mode}",
                  Map.of("score", scoreId, "mode", m), query, ScoreInfo.class);
      }
  }
```

### Step 3：Add the entry point to OsuClient.java

The OsuClient class is used as the SDK facade.

```.java
  // OsuClient.java - Add code in these position:

  // 1. field
  private final ScoresApi scoresApi;

  // 2. struct param
  private OsuClient(BeatmapsApi beatmapsApi, UsersApi usersApi, ScoresApi scoresApi) {
      // ...
      this.scoresApi = scoresApi;
  }

  // 3. createDefault() Auto write
  return new OsuClient(
          new BeatmapsApi(apiHttpClient),
          new UsersApi(apiHttpClient),
          new ScoresApi(apiHttpClient)     // <- here
  );

  // 4. getter
  public ScoresApi scores() { return scoresApi; }
```

### Step 4：Call it

Hello, osu!

```.java
  ScoreInfo s = client.scores().getScore(12345, "osu",
          new QueryMap().put("limit", 10));
```
