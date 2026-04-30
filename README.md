# osu-api4j

### osu! web api v2 wrapper for Java, now WIP

First you should put your client id and secret in src/main/resources/auth.properties:

```.properties
client_id=12345
client_secret=your_client_secret
tokenUrl = https://osu.ppy.sh/oauth/token
apiBaseUrl = https://osu.ppy.sh/api/v2
scope = public
```

Then use like this:

```.java
BeatmapExtended beatmap = client.beatmaps().getBeatmap(
        beatmapId,
        new QueryMap().put("mode", "mania")
);
```
