package com.platzi.javatests.movies.data;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

public class DataSourceUtil {

    public static DataSource getDataSourceInPath() {

        // H2 will create the database here
        final String dbPath = "~/dev/platzi/moviesapp";

        return new DriverManagerDataSource(
                "jdbc:h2:" + dbPath + ";MODE=MYSQL",
                "dbuser", "dbpass");
    }
    public static DataSource getDataSourceInMem() {

        return new DriverManagerDataSource(
                "jdbc:h2:mem:test;MODE=MYSQL",
                "dbuser", "dbpass");
    }
}
