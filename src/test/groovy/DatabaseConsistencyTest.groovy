import spock.lang.Specification

import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.ResultSetMetaData
import java.sql.Statement

class DatabaseConsistencyTest extends Specification {
    private static String JDBC_URL = "jdbc:postgresql://172.17.177.22:5432/foobar"
    private static String USER = "django"
    private static String PASSWORD = "secret2345"

    private static Connection DB_CONNECTION
    private static Statement STATEMENT

    def setupSpec() {
        Class.forName("org.postgresql.Driver")
        DB_CONNECTION = DriverManager.getConnection(JDBC_URL, USER, PASSWORD)
        STATEMENT = DB_CONNECTION.createStatement();

    }

    def cleanupSpec() {
        DB_CONNECTION.close()
    }

    def "Database contains Helmut Schmidt" () {
        def result = []

        when:
        ResultSet resultSet = STATEMENT.executeQuery("SELECT id, name FROM person");
        while (resultSet.next()) {
            result << resultSet.getString("name")
        }

        then:
        result.contains("Helmut Schmidt")
    }

    def "Database contains correct number of entries" () {
        when:
        ResultSet resultSet = STATEMENT.executeQuery("SELECT count(*) as entries FROM person");
        resultSet.next()
        def actualNumberOfPersons = resultSet.getInt("entries")

        then:
        actualNumberOfPersons == 4
    }

    def "Table person contains expected number of columns and column names" () {
        when:
        ResultSet resultSet = STATEMENT.executeQuery("SELECT * FROM person LIMIT 1");
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData()

        then:
        resultSetMetaData.getColumnCount() == 2
        resultSetMetaData.getColumnName(1) == "id"
        resultSetMetaData.getColumnName(2) == "name"
    }


}
