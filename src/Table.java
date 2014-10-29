import java.util.ArrayList;

/**
 * Created by Ekaterina Orlova on 12/10/14.
 */
public class Table {

    int columnCount;
    ArrayList<String> columnNames = new ArrayList<>();
    ArrayList<ArrayList<String>> tableData = new ArrayList<>();
    ArrayList<String> columnDataTypeNames;
    ArrayList<Integer> columnDataTypes;


    public Table(TableHandler tableHandler) {
        this.columnCount = tableHandler.columnCount;
        this.columnDataTypeNames = tableHandler.columnDataTypeNames;
        this.columnNames = tableHandler.columnNames;
        this.columnDataTypes = tableHandler.columnDataTypes;
    }

    //public boolean insertData ()
}
