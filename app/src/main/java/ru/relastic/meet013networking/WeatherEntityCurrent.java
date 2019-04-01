package ru.relastic.meet013networking;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherEntityCurrent {
    @SerializedName("id")
    private long city_id;

    @SerializedName("name")
    private String city_name;

    @SerializedName("dt")
    private long dt;

    @SerializedName("main")
    private Main main;

    @SerializedName("weather")
    private List<Weather> weather;

    @SerializedName("wind")
    private Wind wind;

    public long getCity_id() {
        return this.city_id;
    }

    public void setCity_id(long city_id) {
        this.city_id = city_id;
    }

    public String getCity_name() {
        return this.city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public long getDt() {
        return this.dt;
    }

    public void setDt(long dt) {
        this.dt = dt;
    }

    public Main getMain() {
        return this.main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public List<Weather> getWeather() {
        return this.weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }


    class Main {
        @SerializedName("temp")
        private double temp;

        @SerializedName("temp_min")
        private double temp_min;

        @SerializedName("temp_max")
        private double temp_max;

        @SerializedName("pressure")
        private double pressure;

        public double getTemp() {
            return this.temp;
        }

        public void setTemp(double temp) {
            this.temp = temp;
        }

        public double getTemp_min() {
            return temp_min;
        }

        public void setTemp_min(double temp_min) {
            this.temp_min = temp_min;
        }

        public double getTemp_max() {
            return this.temp_max;
        }

        public void setTemp_max(double temp_max) {
            this.temp_max = temp_max;
        }
        public double getPressure() {
            return this.pressure;
        }
        public void setPressure(double pressure) {
            this.pressure = pressure;
        }
    }

    class Weather {
        @SerializedName("description")
        private String description;

        @SerializedName("icon")
        private String icon;

        public String getDescription() {
            return this.description;
        }
        public void setDescription(String description) {
            this.description = description;
        }
        public String getIcon () {
            return this.icon;
        }
        public void setIcon(String icon) {
            this.icon = icon;
        }
    }

    class Wind {
        @SerializedName("speed")
        private double wind_speed;

        public double getWind_speed(){
            return this.wind_speed;
        }
        public void setWind_speed(double wind_speed) {
            this.wind_speed = wind_speed;
        }
    }

}
