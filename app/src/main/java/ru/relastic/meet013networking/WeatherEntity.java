package ru.relastic.meet013networking;

import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class WeatherEntity {
    static final String FORMAT_DEFAULT_DATE = "dd/MM/yyyy";
    static final String FORMAT_DEFAULT_TIME = "HH:mm:ss";
    static final String FORMAT_DEFAULT_DATETIME = FORMAT_DEFAULT_DATE +" "+FORMAT_DEFAULT_TIME;
    static final String FORMAT_DEFAULT_DOUBLE = "%.1f";
    static final double VALUE_KELVIN_BASE = -273.15D;

    @SerializedName("city")
    private City city;

    @SerializedName("list")
    private List<ListArr> list;

    public City getCity() {
        return this.city;
    }
    public void setCity(City city) {
        this.city = city;
    }

    public List<ListArr> getList() {
        return this.list;
    }
    public void setList(List<ListArr> list) {
        this.list = list;
    }

    class City {
        @SerializedName("id")
        private long id;

        @SerializedName("name")
        private String name;

        @SerializedName("coord")
        private Coord coord;

        public long getId() {
            return this.id;
        }

        public void setId(long id) {
            this.id = id;
        }
        public String getName() {
            return this.name;
        }
        public void setName(String name) {
            this.name = name;
        }

        public Coord getCoord(){
            return this.coord;
        }

        public void setCoord(Coord coord) {
            this.coord = coord;
        }

        class Coord {
            @SerializedName("lat")
            private double lat;

            @SerializedName("lon")
            private double lon;

            public double getLat(){
                return this.lat;
            }

            public void setLat(double lat) {
                this.lat = lat;
            }

            public double getLon(){
                return this.lon;
            }

            public void setLon(double lon) {
                this.lon = lon;
            }
        }
    }

    class ListArr {
        @SerializedName("dt")
        private long dt;

        @SerializedName("dt_txt")
        private String list_dt_txt;

        @SerializedName("main")
        private Main main;

        @SerializedName("weather")
        private List<Weather> weather;

        @SerializedName("clouds")
        private Clouds clouds;

        @SerializedName("wind")
        private Wind wind;

        public long getDt() {
            return this.dt;
        }
        public void setDt(long dt) {
            this.dt = dt;
        }

        public String getList_dt_txt() {
            return this.list_dt_txt;
        }
        public void setList_dt_txt(String list_dt_txt) {
            this.list_dt_txt = list_dt_txt;
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

        public Clouds getClouds() {return this.clouds;}
        public void setClouds(Clouds clouds) {
            this.clouds = clouds;
        }

        public Wind getWind() {return this.wind;}
        public void setWind(Wind wind) {
            this.wind = wind;
        }


        public String getDateString(@Nullable String date_format){
            if (date_format==null) {date_format = FORMAT_DEFAULT_DATE;}
            SimpleDateFormat sdf = new SimpleDateFormat(date_format);
            sdf.setTimeZone(TimeZone.getTimeZone("Europe/Moscow"));
            return sdf.format(new Date(this.dt*1000L));
        }
        public String getTimeString(@Nullable String time_format){
            if (time_format==null) {time_format = FORMAT_DEFAULT_TIME;}
            SimpleDateFormat sdf = new SimpleDateFormat(time_format);
            sdf.setTimeZone(TimeZone.getTimeZone("Europe/Moscow"));
            return sdf.format(new Date(this.dt*1000L));
        }
        public String getDateTimeString(@Nullable String datetime_format){
            if (datetime_format==null) {datetime_format = FORMAT_DEFAULT_DATETIME;}
            SimpleDateFormat sdf = new SimpleDateFormat(datetime_format);
            sdf.setTimeZone(TimeZone.getTimeZone("Europe/Moscow"));
            return sdf.format(new Date(this.dt*1000L));
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

            @SerializedName("humidity")
            private double humidity;

            public double getTemp(@Nullable String format) {
                if (format==null) {format = FORMAT_DEFAULT_DOUBLE;}
                return Double.valueOf(String.format(format, (this.temp+VALUE_KELVIN_BASE)));
            }
            public void setTemp(double temp) {
                this.temp = temp;
            }

            public double getTemp_min(@Nullable String format) {
                if (format==null) {format = FORMAT_DEFAULT_DOUBLE;}
                return Double.valueOf(String.format(format, (this.temp_min+VALUE_KELVIN_BASE)));
            }
            public void setTemp_min(double temp_min) {
                this.temp_min = temp_min;
            }

            public double getTemp_max(@Nullable String format) {
                if (format==null) {format = FORMAT_DEFAULT_DOUBLE;}
                return Double.valueOf(String.format(format, (this.temp_max+VALUE_KELVIN_BASE)));
            }
            public void setTemp_max(double temp_max) {
                this.temp_max = temp_max;
            }

            public double getPressure() {
                return  this.pressure;
            }
            public void setPressure(double pressure) {
                this.pressure = pressure;
            }

            public double getHumidity() {
                return  this.humidity;
            }
            public void setHumidity(double humidity) {
                this.humidity = humidity;
            }
        }

        class Weather {
            @SerializedName("description")
            private String description;

            @SerializedName("icon")
            private String icon_id;

            public String getDescription() {
                return this.description;
            }
            public void setDescription(String description) {
                this.description = description;
            }

            public String getIcon_id() {
                return this.icon_id;
            }
            public void setIcon_id(String icon_id) {
                this.icon_id = icon_id;
            }
        }

        class Clouds {
            @SerializedName("all")
            private double all;

            public double getAll() {
                return this.all;
            }
            public void setAll(double all) {
                this.all = all;
            }
        }

        class Wind {
            @SerializedName("speed")
            private double speed;

            @SerializedName("deg")
            private double deg;

            public double getSpeed() {
                return this.speed;
            }
            public void setSpeed(double speed) {
                this.speed = speed;
            }

            public double getDeg() {
                return this.deg;
            }
            public void setDeg(double deg) {
                this.deg = deg;
            }
        }
    }
}
