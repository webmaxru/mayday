import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class FileParserTest {

    FileParser fileParser = new FileParser("test.txt");

    @Test
    public void testReadFileData() throws Exception {
        assertNotNull(fileParser);
    }

    @Test
    public void testGetColumnNames() throws Exception {
        String[] expectedColumnNames = {"Navn", "Adresse", "Alder"};

        for (int i = 0; i < expectedColumnNames.length; i++) {
            assertEquals(fileParser.getColumnNames()[i], expectedColumnNames[i]);
        }
    }

    @Test
    public void testGetColumnWidths() throws Exception {
        String[] expectedColumnWidths = {"128", "128", "3"};

        for (int i = 0; i < expectedColumnWidths.length; i++) {
            assertEquals(fileParser.getColumnWidths()[i], expectedColumnWidths[i]);
        }

    }

    @Test
    public void testGetColumnDataTypes() throws Exception {
        String[] expectedColumnDataTypes = {"CHAR", "CHAR", "INTEGER"};
        for (int i = 0; i < expectedColumnDataTypes.length; i++) {
            assertEquals(fileParser.getColumnDataTypes()[i], expectedColumnDataTypes[i]);
        }

    }

    @Test
    public void testGetTableData() throws Exception {

    }

    @Test
    public void testPrepareCreateTableQuery() throws Exception {

    }

    @Test
    public void testPrepareInsertQuery() throws Exception {

    }
}