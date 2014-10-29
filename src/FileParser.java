
import java.io.File;
import java.util.ArrayList;

/**
 * Created by Ekaterina Orlova on 09/10/14.
 */
public class FileParser {


    private String fileName;
    private In input;
    private final ArrayList<String> fileData = new ArrayList<>();
    private final ArrayList<String[]> tableData = new ArrayList<>();
    private int columnCount;
    private File file;

    //Constructor
    FileParser(String fileName) {
        setFileName(fileName);
        setFile(new File(getFileName()));
        setInput(new In(getFile()));
    }

    //Reads a file to a String Array
    public ArrayList<String> readFileData() {
        if (getInput().exists()) {
            while (getInput().hasNextLine()) {
                getFileData().add(getInput().readLine());
            }
        }
        return fileData;
    }

    //Get an array of column names from the first line of the file
    String[] getColumnNames() {
        String columns = this.readFileData().get(0);
        return columns.split("/");
    }

    public String[] getColumnWidths() {
        String columns = this.readFileData().get(2);
        return columns.split("/");
    }

    //Parse Data Types, convert java types to SQL
    public String[] getColumnDataTypes() {
        String[] columnTypes;

        String columns = this.readFileData().get(1);
        columnTypes = columns.split("/");
        for (int i = 0; i < columnTypes.length; i++) {
            String type = columnTypes[i];

            if (type.equalsIgnoreCase("string")) {
                columnTypes[i] = "CHAR";
            } else if (type.equalsIgnoreCase("int")) {
                columnTypes[i] = "INTEGER";

            //Support some xtra datatypes that might exist in file
            } else if (type.equalsIgnoreCase("float")) {
                columnTypes[i] = "FLOAT";
            }
            else if (type.equalsIgnoreCase("boolean")) {
                columnTypes[i] = "BOOLEAN";
            }
            else if (type.equalsIgnoreCase("date")) {
                columnTypes[i] = "DATE";
            }
        }

        return columnTypes;
    }

    //Returns data to be sent to the table (lines after third line) as a list of string arrays
    public ArrayList<String[]> getTableData() {
        ArrayList<String> dataToParse = new ArrayList<>();
        String[] parsedLine;

        //Read all the data lines except metadata to the String list
        for (int i = 3; i < this.readFileData().size(); i++) {
            dataToParse.add(this.readFileData().get(i));
        }

        //Split each line according to the separator
        for (String line : dataToParse) {
            parsedLine = line.split("/");
            tableData.add(parsedLine);
        }

        return tableData;
    }

    //prepare Create Table Query
    public String prepareCreateTableQuery(String tableName) {
        String query = String.format("CREATE TABLE IF NOT EXISTS %s (", tableName);
        for (int i = 0; i < getColumnCount(); i++) {
            query += String.format("%s %s(%s), ", this.getColumnNames()[i], this.getColumnDataTypes()[i], this.getColumnWidths()[i]);
        }

        //Cut off unnecessary commas and space
        query = query.substring(0, query.length() - 2);
        query += ");";
        return query;
    }

    public String prepareInsertQuery(String tableName) {
        String query = String.format("INSERT INTO %s (", tableName);
        for (int i = 0; i < getColumnCount(); i++) {
            query += String.format("%s, ", this.getColumnNames()[i]);
        }
        //Cut off unnecessary commas and space
        query = String.format("%s)\nVALUES ", query.substring(0, query.length() - 2));

        for (String[] line : this.getTableData()) {
            query += "(";
            for (int i = 0; i < getColumnCount(); i++) {
                query += "'" + line[i] + "', ";
            }
            query = String.format("%s),\n", query.substring(0, query.length() - 2));
        }
        query = query.substring(0, query.length() - 2) + ";";
        return query;
    }


    //Getters and Setters

    ArrayList<String> getFileData() {
        return fileData;
    }

    String getFileName() {
        return fileName;
    }

    void setFileName(String fileName) {
        this.fileName = fileName;
    }


    public int getColumnCount() {
        columnCount = this.getColumnNames().length;
        return columnCount;
    }

    public void setColumnCount(int columnCount) {
        this.columnCount = columnCount;
    }

    File getFile() {
        return file;
    }

    void setFile(File file) {
        this.file = file;
    }

    In getInput() {
        return input;
    }

    void setInput(In input) {
        this.input = input;

    }
}
