import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import static java.sql.Types.*;

/**
 * Created by Ekaterina Orlova on 12/10/14.
 */
public class TableHandler {

    int columnCount;
    ArrayList<Integer> columnDataTypes;
    ArrayList<String> columnDataTypeNames;
    ArrayList<String> columnNames;
    ArrayList<ArrayList<String>> tableRows;
    ResultSet resultSet;


    //Constructor with metadata only
   /* TableHandler(ResultSetMetaData rsmd) throws SQLException {
        columnCount = rsmd.getColumnCount();
        columnNames = new ArrayList<>();
        columnDataTypes = new ArrayList<>();
        columnDataTypeNames = new ArrayList<>();
        tableRows = new ArrayList<>();
        for (int i = 1; i <= columnCount; i++) {
            columnDataTypes.add(rsmd.getColumnType(i));
            columnDataTypeNames.add(rsmd.getColumnTypeName(i));
            columnNames.add(rsmd.getColumnName(i));
        }
    }*/

    TableHandler(ResultSet resultSet) throws SQLException {
        this.resultSet = resultSet;
        ResultSetMetaData rsmd = resultSet.getMetaData();
        columnCount = rsmd.getColumnCount();
        columnNames = new ArrayList<>();
        columnDataTypes = new ArrayList<>();
        columnDataTypeNames = new ArrayList<>();
        tableRows = new ArrayList<>();
        for (int i = 1; i <= columnCount; i++) {
            columnDataTypes.add(rsmd.getColumnType(i));
            columnDataTypeNames.add(rsmd.getColumnTypeName(i));
            columnNames.add(rsmd.getColumnName(i));
        }
        ArrayList<String> singleRowData = new ArrayList<>();
        while (resultSet.next()) {
            singleRowData.clear();
            for (int i = 0; i < columnCount; i++) {
                //Choose proper get Method according to SQL datatype
                switch (columnDataTypes.get(i)) {
                    case INTEGER:
                    case BIGINT:
                    case SMALLINT:
                        singleRowData.add(String.valueOf(resultSet.getInt(i + 1)));
                        break;
                    case CHAR:
                    case VARCHAR:
                        singleRowData.add(resultSet.getString(i + 1));
                        break;
                    case 7://FLOAT
                        singleRowData.add(resultSet.getString(i + 1));
                        break;
                    default:
                        System.out.print("Error:" + columnDataTypes.get(i).toString());
                        break;
                }
            }
            tableRows.add(singleRowData);
        }
    }


    //Initial method, prints table data to console
    void showTable() throws SQLException {
        resultSet.beforeFirst();
        while (resultSet.next()) {
            for (int i = 0; i < columnCount; i++) {
                switch (columnDataTypes.get(i)) {
                    case INTEGER:
                    case BIGINT:
                    case SMALLINT:
                        System.out.print(resultSet.getInt(i + 1));
                        break;
                    case CHAR:
                    case VARCHAR:
                        System.out.print(resultSet.getString(i + 1));
                        break;
                    case 7://FLOAT
                        System.out.print(resultSet.getString(i + 1));
                        break;
                    default:
                        System.out.print("Error:" + columnDataTypes.get(i).toString());
                        break;
                }
                System.out.print(" ");
            }
            System.out.println();
        }
    }



    //TODO
    public String prepareSelectQuery(String tableName) {
        String query = "SELECT ";
        for (String type : columnNames) {
            query += String.format("%s, ", type);
        }
        query = String.format("%s from %s;", query.substring(0, query.length() - 2), tableName);
        System.out.println(query);

        return query;
    }

}
