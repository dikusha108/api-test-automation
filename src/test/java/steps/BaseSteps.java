package steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.opentest4j.AssertionFailedError;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static data.TestParams.*;
import static org.assertj.core.api.Assertions.assertThat;

public class BaseSteps {
    @Step("Check status code")
    public static void checkStatusCode(Response response, Integer expected) {
        Integer actual = response.then().extract().statusCode();
        assertThat(actual).isEqualTo(expected);
    }

    @Step("Check response body")
    public static void checkBody(Object actual, Object expected, String... ignoringFields) {
        assertThat(actual).usingRecursiveComparison().ignoringFields(ignoringFields).isEqualTo(expected);
    }

    @Step("Get value from DB")
    public static <T> List<T> getListFromDB(String query, String columnLabel, Class<T> typeOf) {
        List<T> items = new ArrayList<>();

        try {
            Class.forName("org.firebirdsql.jdbc.FBDriver");

            Connection conn = DriverManager.getConnection(getFirebirdUrl(), getFirebirdUsername(), getFirebirdPassword());

            if (conn == null) {
                System.err.println("Could not connect to database");
            } else {
                Statement stmt1 = conn.createStatement();
                ResultSet res = stmt1.executeQuery(query);

                while (res.next()) {
                    items.add(res.getObject(columnLabel, typeOf));
                }
                stmt1.close();
                conn.close();
            }
        } catch (Exception e) {
            throw new AssertionError("Connection failed:\n" + e.getMessage());
        }

        return items;
    }


    @Step("Check if lists are equal")
    public static <T> void checkLists(List<T> actualList, List<T> expectedList) {
        // List of elements that are not equal
        List<T> unequalElements = new ArrayList<>();
        String error = "";
        if (actualList.isEmpty() && expectedList.isEmpty()) {
            // If both lists are empty, then they are equal
            return;
        }

        if (actualList.size() == expectedList.size()) {
            for (T expectedItem : expectedList) {
                boolean found = false;
                for (T actualItem : actualList) {
                    // If elements are equal, then we found it
                    if (expectedItem.equals(actualItem)) {
                        found = true;
                        break;
                    }
                }
                // If element is not found, then add it to the list of unequal elements
                if (!found) {
                    unequalElements.add(expectedItem);
                    break;
                }
            }
        } else {
            throw new AssertionFailedError("Sizes are not equal");
        }

        // If there are unequal elements, then throw an error
        if (!unequalElements.isEmpty()) {
            StringBuilder errorMessage = new StringBuilder(error + "\nNot equal elements are found:\n");
            for (T element : unequalElements) {
                errorMessage.append(element).append("\n \n");
            }
            throw new AssertionFailedError(errorMessage.toString());
        }
    }

    @Step("Check if list contains item")
    public static <T> void checkIfContains(List<T> actalList, T item) {
        boolean result = false;

        for (T listItem : actalList) {
            if (listItem.equals(item)) {
                result = true;
                break;
            }
        }
        assertThat(result).isEqualTo(true);
    }
}