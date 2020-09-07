package Common;

public enum FilePaths {

    Partner1("Partner1","src/test/java/JsonFiles/User.json");

    private String apiKey;
    private String JsonPath;

    FilePaths(String apiKey, String jsonPath) {
        this.JsonPath =jsonPath;
        this.apiKey = apiKey;
    }


    public String getFilePath() {
        return JsonPath;
    }

    public static String getFilePath(String apiKey)
    {
        return FilePaths.valueOf(apiKey).getFilePath();
    }
}
