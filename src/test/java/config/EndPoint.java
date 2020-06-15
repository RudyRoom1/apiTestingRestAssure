package config;

public enum EndPoint {
    END_POINT_VIDEOGAMES("/videogames"),
    END_POINT_SINGLE_VIDEOGAMES("/videogames/{videoGameId}"),
    END_POINT_FOOTBALL_COMPETITIONS("competitions/");

    private String endPoint;

    EndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    public String getEndPoint() {
        return endPoint;
    }

    public static boolean contains(String test) {

        for (EndPoint endPoint : EndPoint.values()) {
            if (endPoint.toString().contains(test)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public String toString() {
        return "EndPoint{" +
                "endPoint='" + endPoint + '\'' +
                '}';
    }
}
